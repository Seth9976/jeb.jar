package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jebglobal.aeg;
import com.pnfsoftware.jebglobal.azm;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

@SerDisabled
public class EState {
   private static final StructuredLogger logger = aeg.q(EState.class);
   private static final int maxStackDepth = 30;
   private IEGlobalContext gctx;
   private boolean bigEndian;
   private int maxEvalcnt = 100;
   private int evalcnt;
   private Map segmentBases;
   private IVirtualMemory vm;
   private IVirtualMemory bad_vm;
   private IERoutineContext ectx;
   private int virtualPC;
   private Map gvars;
   private long nativeStackStart = -1L;
   private long nativeStackEnd = -1L;
   private Deque frameStack;
   private boolean executeSubRoutines;
   private Map memwriteHitmap;
   private boolean recordBadWrites = false;
   private boolean memAutoAllocOnWrite = true;
   private EEmulator primaryEmulator;
   private List memhooks = new ArrayList();
   private boolean disabledHooks;
   public static final int MEMREAD_OK = 0;
   public static final int MEMREAD_FAILED = -1;
   public static final int MEMREAD_BADDATA = -2;
   private byte[] tmp2 = new byte[2];
   private byte[] tmp4 = new byte[4];
   private byte[] tmp8 = new byte[8];
   public static final int POLICY_MEMWRITE_BADHIT_DEFAULT = 0;
   public static final int POLICY_MEMWRITE_BADHIT_FORCE_RECORDING = 1;
   public static final int POLICY_MEMWRITE_BADHIT_FORCE_NO_RECORDING = 2;
   public static final int POLICY_MEMWRITE_BADHIT_NEED_STACK_ADDRESS = 3;
   private EState.PointerSanitizer pointerSanitizer;

   public void pushFrame() {
      if (this.frameStack == null) {
         this.frameStack = new ArrayDeque();
      } else if (this.frameStack.size() >= 30) {
         throw new EvaluationException("Exceeded recursion limits (maxStackDepth=30)");
      }

      EState.Frame var1 = new EState.Frame(this.ectx, this.virtualPC, this.gvars, this.nativeStackStart, this.nativeStackEnd);
      this.frameStack.push(var1);
      this.ectx = null;
      this.virtualPC = 0;
      this.gvars = var1.gvars == null ? null : new HashMap(var1.gvars);
      this.nativeStackStart = -1L;
      this.nativeStackEnd = -1L;
   }

   public EState.Frame popFrame() {
      if (this.frameStack != null && !this.frameStack.isEmpty()) {
         EState.Frame var1 = new EState.Frame(this.ectx, this.virtualPC, this.gvars, this.nativeStackStart, this.nativeStackEnd);
         EState.Frame var2 = (EState.Frame)this.frameStack.pop();
         this.ectx = var2.ectx;
         this.virtualPC = var2.virtualPC;
         this.gvars = var2.gvars;
         this.nativeStackStart = var2.nativeStackStart;
         this.nativeStackEnd = var2.nativeStackEnd;
         return var1;
      } else {
         return null;
      }
   }

   public int countFrames() {
      return this.frameStack == null ? 0 : this.frameStack.size();
   }

   public EState(IEGlobalContext var1) {
      this.gctx = var1;
      this.vm = new azm(var1.getAddressBitsize());
      this.bigEndian = var1.isBigEndian();
      this.vm.setStandardEndianness(this.bigEndian ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN);
      this.gvars = new HashMap();
   }

   public EState(Endianness var1) {
      this.bigEndian = var1.isBig();
      this.gvars = new HashMap();
   }

   public EState(EState var1) {
      this(var1, true);
   }

   public EState(EState var1, boolean var2) {
      this.gctx = var1.gctx;
      this.bigEndian = var1.bigEndian;
      this.maxEvalcnt = var1.maxEvalcnt;
      this.evalcnt = var1.evalcnt;
      this.segmentBases = var1.segmentBases == null ? null : new HashMap(var1.segmentBases);
      this.vm = !var2 ? var1.vm : (var1.vm == null ? null : var1.vm.duplicate());
      this.bad_vm = !var2 ? var1.bad_vm : (var1.bad_vm == null ? null : var1.bad_vm.duplicate());
      this.ectx = var1.ectx;
      this.virtualPC = var1.virtualPC;
      this.gvars = var1.gvars == null ? null : new HashMap(var1.gvars);
      this.nativeStackStart = var1.nativeStackStart;
      this.nativeStackEnd = var1.nativeStackEnd;
      this.frameStack = null;
      if (var1.frameStack != null) {
         this.frameStack = new ArrayDeque(var1.frameStack.size());

         for (EState.Frame var4 : var1.frameStack) {
            this.frameStack
               .add(
                  new EState.Frame(var4.ectx, var4.virtualPC, var4.gvars == null ? null : new HashMap(var4.gvars), var4.nativeStackStart, var4.nativeStackEnd)
               );
         }
      }

      this.executeSubRoutines = var1.executeSubRoutines;
      this.memwriteHitmap = var1.memwriteHitmap == null ? null : new HashMap(var1.memwriteHitmap);
      this.recordBadWrites = var1.recordBadWrites;
      this.memAutoAllocOnWrite = var1.memAutoAllocOnWrite;
   }

   public void setExecuteSubRoutines(boolean var1) {
      this.executeSubRoutines = var1;
   }

   public boolean isExecuteSubRoutines() {
      return this.executeSubRoutines;
   }

   public int adjustVirtualPC(int var1) {
      this.virtualPC += var1;
      return this.virtualPC;
   }

   public void setVirtualPC(int var1) {
      this.virtualPC = var1;
   }

   public int getVirtualPC() {
      return this.virtualPC;
   }

   public void setRoutineContext(IERoutineContext var1) {
      this.ectx = var1;
   }

   public IERoutineContext getRoutineContext() {
      return this.ectx;
   }

   public IEGlobalContext getGlobalContext() {
      return this.gctx;
   }

   public void setMaxEvaluationCount(int var1) {
      this.maxEvalcnt = var1;
   }

   public int getMaxEvaluationCount() {
      return this.maxEvalcnt;
   }

   public boolean incrementEvaluationCount() {
      this.evalcnt++;
      return this.evalcnt <= this.maxEvalcnt;
   }

   public int getEvaluationCount() {
      return this.evalcnt;
   }

   public void enableMemoryWriteHitmap(boolean var1, boolean var2) {
      if (var1) {
         this.memwriteHitmap = new HashMap();
         this.recordBadWrites = var2;
      } else {
         this.memwriteHitmap = null;
         this.recordBadWrites = false;
      }
   }

   public void setRecordBadWrites(boolean var1) {
      this.recordBadWrites = var1;
   }

   public boolean isRecordBadWrites() {
      return this.recordBadWrites;
   }

   public Map getMemoryWriteHitmap() {
      return this.memwriteHitmap;
   }

   public void setPrimaryEmulator(EEmulator var1) {
      this.primaryEmulator = var1;
   }

   public EEmulator getPrimaryEmulator() {
      return this.primaryEmulator;
   }

   public void registerHooks(IEStateHooks var1, boolean var2) {
      if (var1 != null) {
         int var3 = -1;
         int var5 = var1.getPriority();

         int var4;
         for (var4 = 0; var4 < this.memhooks.size(); var4++) {
            int var6 = ((IEStateHooks)this.memhooks.get(var4)).getPriority();
            if (var6 < var5) {
               break;
            }

            if (var6 == var5 && var3 == -1) {
               var3 = var4;
            }
         }

         if (var2 && var3 >= 0) {
            this.memhooks.add(var3, var1);
         } else {
            this.memhooks.add(var4, var1);
         }
      }
   }

   public void unregisterHooks(IEStateHooks var1) {
      if (var1 != null) {
         this.memhooks.remove(var1);
      }
   }

   public boolean setDisabledHooks(boolean var1) {
      boolean var2 = this.disabledHooks;
      this.disabledHooks = var1;
      return var2;
   }

   public boolean isDisabledHooks() {
      return this.disabledHooks;
   }

   public int mergeWith(EState var1, IMergeController var2, Set var3, Set var4) {
      if (var1.bigEndian != this.bigEndian) {
         throw new IllegalStateException("States cannot be merged, endianness differs");
      } else {
         if (var2 == null) {
            var2 = new StandardMergeController();
         }

         Object[] var10000 = new Object[]{this, var1};
         Map var5 = this.gvars;
         Map var6 = var1.gvars;
         HashSet var7 = new HashSet();
         int var8 = 0;
         int var9 = 0;

         for (int var11 : var6.keySet()) {
            if (var5.containsKey(var11)) {
               var7.add(var11);
               IEImm var12 = (IEImm)var5.get(var11);
               IEImm var13 = (IEImm)var6.get(var11);
               if (var12 != null && var13 != null && var12.equals(var13)) {
                  ((IMergeController)var2).onMatch(var11);
               } else {
                  if (((IMergeController)var2).onMismatch(var11)) {
                     var5.remove(var11);
                  }

                  var8++;
                  if (var3 != null) {
                     var3.add(var11);
                  }
               }
            } else if (((IMergeController)var2).onOutputOnly(var11)) {
               var5.put(var11, (IEImm)var6.get(var11));
               var9++;
               if (var4 != null) {
                  var4.add(var11);
               }
            }
         }

         Iterator var14 = var5.keySet().iterator();

         while (var14.hasNext()) {
            int var15 = (Integer)var14.next();
            if (!var7.contains(var15) && ((IMergeController)var2).onInputOnly(var15)) {
               var14.remove();
               var8++;
               if (var3 != null) {
                  var3.add(var15);
               }
            }
         }

         return var8 + var9;
      }
   }

   public int mergeWithOld(EState var1, Collection var2, boolean var3, Collection var4, Collection var5) {
      if (var1.bigEndian != this.bigEndian) {
         throw new IllegalStateException("States cannot be merged, endianness differs");
      } else {
         Object[] var10000 = new Object[]{this, var1};
         Map var6 = this.gvars;
         Map var7 = var1.gvars;
         HashSet var8 = new HashSet();
         int var9 = 0;
         int var10 = 0;

         for (int var12 : var7.keySet()) {
            if (var6.containsKey(var12)) {
               var8.add(var12);
               if (!((IEImm)var6.get(var12)).equals(var7.get(var12))) {
                  var6.remove(var12);
                  var9++;
                  if (var5 != null) {
                     var5.add(var12);
                  }
               }
            } else if (var3 && (var4 == null || !var4.contains(var12))) {
               var6.put(var12, (IEImm)var7.get(var12));
               var10++;
            }
         }

         if (var2 != null) {
            Iterator var13 = var6.keySet().iterator();

            while (var13.hasNext()) {
               int var14 = (Integer)var13.next();
               if (!var8.contains(var14) && var2.contains(var14)) {
                  var13.remove();
                  var9++;
                  if (var5 != null) {
                     var5.add(var14);
                  }
               }
            }
         }

         return var9 + var10;
      }
   }

   public boolean isBigEndian() {
      return this.bigEndian;
   }

   public Map getVariables() {
      return this.gvars;
   }

   public void setVariables(Map var1) {
      this.gvars = var1;
   }

   public IEImm getValueSafe(IEVar var1) {
      return this.getValueInternal(var1.getId());
   }

   public IEImm getValueSafe(int var1) {
      return this.getValueInternal(var1);
   }

   public IEImm getValue(IEVar var1) {
      return this.getValue(var1.getId());
   }

   public IEImm getValue(int var1) {
      IEImm var2 = this.getValueInternal(var1);
      if (var2 == null) {
         throw new EvaluationException("Variable is uninitialized or does not exist: " + EUtil.formatVars(this.ectx, var1));
      } else {
         return var2;
      }
   }

   public long getValueAsLong(int var1) {
      return this.getValue(var1).getValueAsLong();
   }

   public Long getValueAsLongSafe(int var1) {
      IEImm var2 = this.getValueSafe(var1);
      return var2 == null ? null : var2.getValueAsLong();
   }

   public long getValueAsUnsignedLong(int var1) {
      return this.getValue(var1).getValueAsUnsignedLong();
   }

   private IEImm getValueInternal(int var1) {
      return (IEImm)this.gvars.get(var1);
   }

   public void setValue(int var1, long var2) {
      IEVar var4;
      if (this.ectx != null) {
         var4 = this.ectx.getVariableById(var1);
      } else {
         var4 = this.gctx.getVar(var1);
      }

      this.setValue(var4, var2);
   }

   public void setValue(IEVar var1, long var2) {
      this.setValueInternal(var1.getId(), EUtil.imm(var2, var1.getBitsize()));
   }

   public void setValue(IEVar var1, IEImm var2) {
      if (var2 != null && var1.getBitsize() != var2.getBitsize()) {
         throw new IllegalArgumentException("Unexpected bitsize for value, doe not match variable's!");
      } else {
         this.setValueInternal(var1.getId(), var2);
      }
   }

   private void setValueInternal(int var1, IEImm var2) {
      this.gvars.put(var1, var2);
   }

   public boolean removeValue(int var1) {
      return this.gvars.remove(var1) != null;
   }

   public boolean hasValue(int var1) {
      return this.gvars.get(var1) != null;
   }

   public boolean hasBadValue(int var1) {
      return this.gvars.containsKey(var1) && this.gvars.get(var1) == null;
   }

   public IEImm getProgramCounter() {
      IEVar var1 = this.gctx.getConverter().getProgramCounter();
      return this.getValue(var1);
   }

   public void setProgramCounter(IEImm var1) {
      IEVar var2 = this.gctx.getConverter().getProgramCounter();
      this.setValue(var2, var1);
   }

   public IEImm getStackPointer() {
      IEVar var1 = this.gctx.getConverter().getStackPointer();
      return this.getValue(var1);
   }

   public void setStackPointer(IEImm var1) {
      IEVar var2 = this.gctx.getConverter().getStackPointer();
      this.setValue(var2, var1);
   }

   public boolean hasSegmentBases() {
      return this.segmentBases != null;
   }

   public void setSegmentBase(int var1, long var2) {
      if (this.segmentBases == null) {
         this.segmentBases = new HashMap();
      }

      this.segmentBases.put(var1, var2);
   }

   public long getSegmentBase(int var1) {
      if (this.segmentBases == null) {
         return 0L;
      } else {
         Long var2 = (Long)this.segmentBases.get(var1);
         return var2 == null ? 0L : var2;
      }
   }

   public void setSegmentMapping(String var1, int var2, long var3) {
      IEVar var5 = this.gctx.getVariableByName(var1);
      this.setValue(var5, var2);
      this.setSegmentBase(var2, var3);
   }

   public IVirtualMemory getMemory() {
      return this.vm;
   }

   public void setMemory(IVirtualMemory var1) {
      this.vm = var1;
   }

   public void setNativeStackStart(long var1) {
      this.nativeStackStart = var1;
   }

   public long getNativeStackStart() {
      return this.nativeStackStart;
   }

   public void setNativeStackEnd(long var1) {
      this.nativeStackEnd = var1;
   }

   public long getNativeStackEnd() {
      return this.nativeStackEnd;
   }

   public void setSoftFailMode(boolean var1) {
      if (var1) {
         IVirtualMemory var2 = this.getMemory();
         if (var2 == null) {
            throw new IllegalStateException("Soft-fail mode requires a vmem!");
         }

         this.bad_vm = VirtualMemoryUtil.createMemory(var2.getSpaceBits(), var2.getPageBits(), var2.getStandardEndianess());
      } else {
         this.bad_vm = null;
      }
   }

   public boolean isSoftFailMode() {
      return this.bad_vm != null;
   }

   public IVirtualMemory getBadMemory() {
      return this.bad_vm;
   }

   public void setBadMemory(IVirtualMemory var1) {
      this.bad_vm = var1;
   }

   public void setMemoryAutoAllocOnWrite(boolean var1) {
      this.memAutoAllocOnWrite = var1;
   }

   public boolean isMemoryAutoAllocOnWrite() {
      return this.memAutoAllocOnWrite;
   }

   public boolean readMemory(long var1, byte[] var3) {
      return this.readMemory2(var1, var3) == 0;
   }

   public int readMemory2(long var1, byte[] var3) {
      if (!this.disabledHooks) {
         for (IEStateHooks var5 : this.memhooks) {
            Integer var6 = var5.onReadMemory(this, var1, var3);
            if (var6 != null) {
               return var6;
            }
         }
      }

      int var8 = this.readMemoryInternal(var1, var3);
      if (!this.disabledHooks) {
         for (IEStateHooks var10 : this.memhooks) {
            Integer var7 = var10.onReadMemoryPost(this, var1, var3, var8);
            if (var7 != null) {
               return var7;
            }
         }
      }

      return var8;
   }

   private int readMemoryInternal(long var1, byte[] var3) {
      boolean var4 = VirtualMemoryUtil.readSafe(this.vm, var1, var3);
      if (this.bad_vm != null && var4) {
         byte[] var5 = new byte[var3.length];
         boolean var6 = VirtualMemoryUtil.readSafe(this.bad_vm, var1, var5);
         if (!var6) {
            return var4 ? 0 : -1;
         } else {
            for (byte var10 : var5) {
               if (var10 == -1) {
                  return -2;
               }
            }

            return var4 ? 0 : -1;
         }
      } else {
         return var4 ? 0 : -1;
      }
   }

   private void memhit(long var1, int var3) {
      for (int var4 = 0; var4 < var3; var4++) {
         this.memwriteHitmap.put(var1 + var4, this.evalcnt);
      }
   }

   public synchronized boolean writeMemoryShort(long var1, short var3) {
      if (this.vm.getStandardEndianess().isLittle()) {
         EndianUtil.shortToLEBytes(var3, this.tmp2);
      } else {
         if (!this.vm.getStandardEndianess().isBig()) {
            return false;
         }

         EndianUtil.shortToBEBytes(var3, this.tmp2);
      }

      return this.writeMemory(var1, this.tmp2);
   }

   public synchronized boolean writeMemoryInt(long var1, int var3) {
      if (this.vm.getStandardEndianess().isLittle()) {
         EndianUtil.intToLEBytes(var3, this.tmp4);
      } else {
         if (!this.vm.getStandardEndianess().isBig()) {
            return false;
         }

         EndianUtil.intToBEBytes(var3, this.tmp4);
      }

      return this.writeMemory(var1, this.tmp4);
   }

   public synchronized boolean writeMemoryLong(long var1, long var3) {
      if (this.vm.getStandardEndianess().isLittle()) {
         EndianUtil.longToLEBytes(var3, this.tmp8);
      } else {
         if (!this.vm.getStandardEndianess().isBig()) {
            return false;
         }

         EndianUtil.longToBEBytes(var3, this.tmp8);
      }

      return this.writeMemory(var1, this.tmp8);
   }

   public synchronized boolean writeMemoryPointer(long var1, long var3) {
      int var5 = this.vm.getSpaceBits();
      if (var5 == 16) {
         return this.writeMemoryShort(var1, (short)var3);
      } else if (var5 == 32) {
         return this.writeMemoryInt(var1, (int)var3);
      } else if (var5 == 64) {
         return this.writeMemoryLong(var1, var3);
      } else if (var5 > 64) {
         byte[] var6 = new byte[var5 / 8];
         if (this.vm.getStandardEndianess().isLittle()) {
            EndianUtil.longToLEBytes(var3, var6);
         } else {
            if (!this.vm.getStandardEndianess().isBig()) {
               return false;
            }

            EndianUtil.longToBEBytes(var3, var6);
         }

         return this.writeMemory(var1, var6);
      } else {
         return false;
      }
   }

   public boolean writeMemory(long var1, byte[] var3) {
      if (!this.disabledHooks) {
         for (IEStateHooks var5 : this.memhooks) {
            Boolean var6 = var5.onWriteMemory(this, var1, var3);
            if (var6 != null) {
               return var6;
            }
         }
      }

      return this.writeMemoryInternal(var1, var3);
   }

   private boolean writeMemoryInternal(long var1, byte[] var3) {
      if (this.memwriteHitmap != null) {
         this.memhit(var1, var3.length);
      }

      if (!this.memAutoAllocOnWrite && this.vm.check(var1, var3.length, 2) < var3.length) {
         return false;
      } else {
         VirtualMemoryUtil.allocateFillGaps(this.vm, var1, var3.length, 7);
         boolean var4 = VirtualMemoryUtil.writeSafe(this.vm, var1, var3);
         if (this.bad_vm != null && var4) {
            byte[] var5 = new byte[var3.length];
            Arrays.fill(var5, (byte)0);
            VirtualMemoryUtil.allocateFillGaps(this.bad_vm, var1, var5.length, 7);
            return VirtualMemoryUtil.writeSafe(this.bad_vm, var1, var5);
         } else {
            return var4;
         }
      }
   }

   public boolean writeMemoryBad(long var1, int var3) {
      return this.writeMemoryBad(var1, var3, 0);
   }

   public boolean writeMemoryBad(long var1, int var3, int var4) {
      if (this.memwriteHitmap != null) {
         if (switch (var4) {
            case 0 -> this.recordBadWrites;
            case 1 -> true;
            case 2 -> false;
            case 3 -> this.recordBadWrites && var1 >= this.nativeStackStart && var1 < this.nativeStackEnd;
            default -> false;
         }) {
            this.memhit(var1, var3 / 8);
         }
      }

      if (this.bad_vm == null) {
         return false;
      } else {
         byte[] var6 = new byte[(var3 + 7) / 8];
         Arrays.fill(var6, (byte)-1);
         VirtualMemoryUtil.allocateFillGaps(this.bad_vm, var1, var6.length, 7);
         return VirtualMemoryUtil.writeSafe(this.bad_vm, var1, var6);
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "VPC:0x%X ", this.getVirtualPC());
      Strings.ff(var1, "{%s}", formatVars(this.gvars, this.gctx, this.ectx, null, null, true));
      Strings.ff(var1, " hasMem=%b", this.getMemory() != null);
      return var1.toString();
   }

   public String formatVars(boolean var1) {
      return formatVars(this.gvars, this.gctx, this.ectx, null, null, var1).toString();
   }

   public static CharSequence formatVars(Map var0, IEGlobalContext var1, IERoutineContext var2) {
      return formatVars(var0, var1, var2, null, null, false);
   }

   public static CharSequence formatVars(Map var0, IEGlobalContext var1, IERoutineContext var2, Collection var3, Collection var4, boolean var5) {
      StringBuilder var6 = new StringBuilder();
      TreeMap var7 = new TreeMap(var0);
      int var8 = 0;

      for (Entry var10 : var7.entrySet()) {
         int var11 = (Integer)var10.getKey();
         if ((var3 == null || var3.contains(var11)) && (var4 == null || !var4.contains(var11))) {
            IEImm var12 = (IEImm)var10.getValue();
            String var13 = null;
            if (var2 != null) {
               var13 = var2.getVariableById(var11).getName();
            } else if (var1 != null) {
               IEVar var14 = var1.getVarSafe(var11);
               if (var14 != null) {
                  var13 = var14.getName();
               }
            }

            if (var13 == null) {
               var13 = "r" + var11;
            }

            String var15;
            if (var12 == null) {
               var15 = "BAD!";
            } else {
               if (var5 && var12.isZero()) {
                  continue;
               }

               var15 = var12.toHexString().toUpperCase();
            }

            if (var8 >= 1) {
               var6.append(", ");
            }

            var6.append(var13).append(":").append(var15);
            var8++;
         }
      }

      return var6;
   }

   public void setPointerSanitizer(EState.PointerSanitizer var1) {
      this.pointerSanitizer = var1;
   }

   public EState.PointerSanitizer getPointerSanitizer() {
      return this.pointerSanitizer;
   }

   public long generateAddressFromPointer(long var1) {
      return this.pointerSanitizer != null ? this.pointerSanitizer.process(var1) : var1;
   }

   public static class Frame {
      IERoutineContext ectx;
      int virtualPC;
      Map gvars;
      long nativeStackStart;
      long nativeStackEnd;

      Frame(IERoutineContext var1, int var2, Map var3, long var4, long var6) {
         this.ectx = var1;
         this.virtualPC = var2;
         this.gvars = var3;
         this.nativeStackStart = var4;
         this.nativeStackEnd = var6;
      }

      public IERoutineContext getRoutineContext() {
         return this.ectx;
      }

      public int getVirtualPC() {
         return this.virtualPC;
      }

      public Map getVars() {
         return this.gvars;
      }
   }

   public interface PointerSanitizer {
      long process(long var1);
   }
}
