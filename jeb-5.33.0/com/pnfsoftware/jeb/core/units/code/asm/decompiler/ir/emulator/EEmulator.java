package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.LinuxSyscallResolver;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemoryShim;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryChanges;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jebglobal.ajo;
import com.pnfsoftware.jebglobal.ajr;
import com.pnfsoftware.jebglobal.aki;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.auz;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class EEmulator {
   private static final ILogger logger = GlobalLog.getLogger(EEmulator.class);
   public static boolean debugLogEnabled = false;
   public static boolean extraVerboseLogInsn = false;
   public static boolean extraVerboseLogIR = false;
   private static final int MODE_ROUTINE = 1;
   private static final int MODE_STUB = 2;
   private static final int MODE_FULL_ROUTINE = 3;
   private static final int MODE_FULL_STUB = 4;
   public static final int defaultMaxIterCount = 10000;
   public static final boolean defaultAllowOpt = false;
   public static final long defaultHeapBase = 2080374784L;
   public static final long defaultStackBase = 2113929216L;
   public static final boolean defaultResetUnknownRegisters = true;
   public static final long defaultReturnAddr = 287454020L;
   public static final boolean defaultRecordMemoryWrites = false;
   private static final long baseAddressForExterns = 2130706432L;
   private static final boolean defaultPerformFreshDecompilations = false;
   private int internalState = 0;
   private INativeDecompilerContext decomp;
   private INativeContext code;
   private IEGlobalContext gctx;
   private IEConverter cv;
   private ProcessorType proctype;
   private ITypeManager typeman;
   private int regsize;
   private int addrsize;
   private int slotsize;
   private IEVar SP;
   private int idSP;
   private IEVar PC;
   private int idPC;
   private EState state;
   private long stackPreferredBase = 2113929216L;
   private long stackStart = -1L;
   private int stackSize = 0;
   private boolean resetUnknownRegisters = true;
   private long currentAddrForPseudoRoutines = 2130706432L;
   private long retAddr = 287454020L;
   private boolean recordMemoryWrites = false;
   private boolean performFreshDecompilations = false;
   private long heapPreferredBase = 2080374784L;
   private long heapCurrent = -1L;
   private long heapEnd = -1L;
   private int execmode;
   private INativeMethodItem routine0;
   private IPrototypeItem proto0;
   private List args = new ArrayList();
   private Long pcStart;
   private Long pcStop;
   private Integer irOffsetStart;
   private Integer irOffsetStop;
   private Map ctxmap = new HashMap();
   private Deque callstack = new ArrayDeque();
   private IEImm lastEvalResult;
   private EEmulator.Metadata metadata = new EEmulator.Metadata();
   aki pseudoIRContext;
   private CacheMap mapConverterCachedEntries;
   private Map mapRegisteredRoutines = new HashMap();
   private Map mapRegisteredRoutinesByName = new HashMap();
   private MemoryWrites memwrites;
   private final AtomicLong currentRequestId = new AtomicLong(1L);
   private volatile long lastRequestId = 0L;
   private List emuhooks = new ArrayList();

   private static void log(String var0, Object... var1) {
   }

   public static EEmulator createStandard(IEGlobalContext var0) {
      return createStandard(var0, 10000);
   }

   public static EEmulator createStandard(IEGlobalContext var0, int var1) {
      IVirtualMemoryShim var2 = VirtualMemoryUtil.getCopyOnWriteShim(var0.getNativeMemory());
      EState var3 = var0.buildEmptyState();
      var3.setMaxEvaluationCount(var1);
      var3.setExecuteSubRoutines(true);
      var3.setMemory(var2);
      return new EEmulator(var3);
   }

   public EEmulator(EState var1) {
      if (var1 != null && var1.getGlobalContext() != null) {
         this.state = var1;
         this.gctx = var1.getGlobalContext();
         this.proctype = this.gctx.getNativeContext().getProcessor().getType();
         this.typeman = this.gctx.getNativeContext().getTypeManager();
         this.cv = this.gctx.getConverter();
         this.decomp = this.cv.getDecompiler();
         this.code = this.decomp.getNativeContext();
         this.regsize = this.cv.getRegisterBitsize() / 8;
         this.addrsize = this.cv.getAddressBitsize() / 8;
         this.slotsize = this.cv.getStackSlotSize();
         this.SP = this.cv.getStackPointer();
         this.idSP = this.SP.getId();
         this.PC = this.cv.getProgramCounter();
         this.idPC = this.PC.getId();
         if (this.proctype == ProcessorType.ARM64) {
            var1.getMemory().setAddressMassagerType(1);
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   private void verifyState(boolean var1) {
      if (!var1) {
         throw new IllegalStateException();
      }
   }

   private boolean isExecRoutine() {
      return (this.execmode & 1) == 1;
   }

   private boolean isExecFull() {
      return this.execmode >= 3;
   }

   public IEGlobalContext getGlobalContext() {
      return this.gctx;
   }

   public int getRegisterSize() {
      return this.regsize;
   }

   public int getAddressSize() {
      return this.addrsize;
   }

   public int getStackSlotSize() {
      return this.slotsize;
   }

   public void setRecordMemoryWrites(boolean var1) {
      this.verifyState(this.internalState == 0);
      this.recordMemoryWrites = var1;
   }

   public void setResetUnknownRegisters(boolean var1) {
      this.verifyState(this.internalState == 0);
      this.resetUnknownRegisters = var1;
   }

   public void setPreferredStackBase(long var1) {
      this.verifyState(this.internalState == 0);
      this.stackPreferredBase = var1;
   }

   public void setPerformFreshDecompilations(boolean var1) {
      this.performFreshDecompilations = var1;
   }

   public void setStubExecution(IERoutineContext var1, int var2, int var3) {
      this.execmode = 2;
      this.routine0 = var1.getRoutine();
      this.ctxmap.put(this.routine0, var1);
      this.irOffsetStart = var2;
      this.irOffsetStop = var3;
   }

   public void setStubExecution(INativeMethodItem var1, long var2, long var4) {
      this.execmode = 2;
      this.routine0 = var1;
      this.pcStart = var2;
      this.pcStop = var4;
   }

   public void setTargetRoutine(INativeMethodItem var1) {
      this.execmode = 1;
      this.routine0 = var1;
   }

   public void setPreferredTargetPrototype(IPrototypeItem var1) {
      this.proto0 = var1;
   }

   public void setGlobalRoutineEmulation(INativeMethodItem var1) {
      this.setGlobalRoutineEmulation(var1.getMemoryAddress(), var1.getPrototype());
   }

   public void setGlobalRoutineEmulation(long var1, IPrototypeItem var3) {
      this.execmode = 3;
      this.pcStart = var1;
      this.pcStop = null;
      this.proto0 = var3;
      this.routine0 = null;
      this.ctxmap = null;
      this.callstack = null;
   }

   public void setGlobalStubEmulation(long var1, Long var3) {
      this.execmode = 4;
      this.pcStart = var1;
      this.pcStop = var3;
      this.proto0 = null;
      this.routine0 = null;
      this.ctxmap = null;
      this.callstack = null;
   }

   public void setReturnAddress(long var1) {
      this.retAddr = var1;
   }

   public void clearArgument() {
      this.args.clear();
   }

   public void addArgument(long var1, INativeType var3) {
      this.args.add(ajr.pC(var1, var3.getSize() * 8));
   }

   public void addArgument(byte[] var1, INativeType var2) {
      this.args.add(ajr.pC(var1, var2.getSize() * 8));
   }

   public void addArgument(IEImm var1) {
      this.args.add(var1);
   }

   public void setArguments(Collection var1) {
      this.args.clear();
      this.args.addAll(var1);
   }

   public static IPrototypeItem retrievePrototype(INativeDecompilerContext var0, INativeMethodItem var1) {
      if (var1.getPrototype() != null) {
         return var1.getPrototype();
      } else {
         IWildcardPrototype var2 = var0.getGlobalContext().getCandidatePrototype(var1);
         return var2 != null ? var2.resolve() : null;
      }
   }

   private IPrototypeItem retrieveTargetPrototype() {
      this.verifyState(this.isExecRoutine());
      if (this.proto0 == null) {
         if (this.routine0 == null) {
            throw new IllegalStateException("No target routine was specified, the prototype cannot be retrieved. You may set it manually.");
         }

         this.proto0 = retrievePrototype(this.decomp, this.routine0);
      }

      return this.proto0;
   }

   public void setup() {
      if (this.internalState == 0) {
         this.internalState = 1;
         if (this.state.getPrimaryEmulator() == null) {
            this.state.setPrimaryEmulator(this);
            this.state.registerHooks(new EEmulator.MemHooks(), true);
            if (this.recordMemoryWrites) {
               this.memwrites = new MemoryWrites();
            }
         } else {
            this.state.pushFrame();
         }

         boolean var1 = this.state.setDisabledHooks(true);
         Long var2 = null;
         if (this.routine0 != null) {
            var2 = this.routine0.getMemoryAddress();
         } else if (this.pcStart != null) {
            var2 = this.pcStart;
         }

         if (var2 != null) {
            this.state.setValue(this.idPC, var2);
         }

         this.cv.customInitStateRegisters(this.state, var2);
         if (this.stackPreferredBase != -1L) {
            this.stackStart = (this.stackPreferredBase & -4096L) - 65536L;
            this.stackSize = 1048576;
            this.stackStart = VirtualMemoryUtil.findAvailableRange(this.state.getMemory(), this.stackStart, this.stackSize);
            long var3 = this.stackStart + this.stackSize / 2;
            VirtualMemoryUtil.allocateFillGaps(this.state.getMemory(), this.stackStart, this.stackSize, 3);
            this.state.setValue(this.SP, var3);
            this.state.setNativeStackStart(this.stackStart);
            this.state.setNativeStackEnd(this.stackStart + this.stackSize);
            this.state.writeMemoryBad(this.stackStart, this.stackSize * 8, 2);
         }

         if (this.resetUnknownRegisters) {
            for (IEVar var4 : this.gctx.getAllRegisters()) {
               if (!this.state.hasValue(var4.getId())) {
                  this.state.setValue(var4, 0L);
               }
            }
         }

         this.state.setDisabledHooks(var1);
      }
   }

   public boolean setStack(long var1, int var3, Long var4) {
      boolean var5 = this.state.setDisabledHooks(true);

      try {
         try {
            this.state.getMemory().allocate(var1, var3, 3);
         } catch (MemoryException var11) {
            return false;
         }

         if (var4 != null) {
            this.state.setValue(this.SP, var4);
         }

         this.state.setNativeStackStart(var1);
         this.state.setNativeStackEnd(var1 + var3);
         this.state.writeMemoryBad(var1, var3 * 8, 2);
         return true;
      } finally {
         this.state.setDisabledHooks(var5);
      }
   }

   private void writeRoutineInputs() {
      if (this.isExecRoutine()) {
         IPrototypeItem var1 = this.retrieveTargetPrototype();
         if (var1 == null) {
            throw new IllegalStateException("The target routine does not have a prototype");
         } else {
            boolean var2 = this.state.setDisabledHooks(true);

            try {
               ICallingConvention var3 = var1.getCallingConvention();
               IStorageEntryGenerator var4 = var3.getInputsGenerator();
               int var5 = 0;
               List var6 = var1.getParameterTypes();

               for (IEImm var8 : this.args) {
                  TypeLayoutInfo var9 = TypeLayoutInfo.i1;
                  if (var5 < var6.size()) {
                     INativeType var10 = (INativeType)var6.get(var5);
                     var9 = TypeUtil.getLayoutInfo(var10);
                  }

                  StorageEntry var15 = var4.next(var9);
                  this.writeStorage(var15, var8);
                  var5++;
               }

               StorageEntry var14 = var3.getReturnAddressSlot();
               this.writeStorage(var14, ajr.pC(this.retAddr, this.addrsize * 8));
               this.args.clear();
            } finally {
               this.state.setDisabledHooks(var2);
            }
         }
      }
   }

   public void teardown() {
      this.verifyState(this.internalState == 1 || this.internalState == 2);
      if (!this.isPrimaryEmulator()) {
         this.state.popFrame();
      }

      this.internalState = 3;
   }

   public boolean isPrimaryEmulator() {
      return this == this.state.getPrimaryEmulator();
   }

   public EState getState() {
      return this.state;
   }

   public IVirtualMemory getVirtualMemory() {
      return this.getState().getMemory();
   }

   public MemoryChanges getMemoryChanges() throws UnsupportedOperationException {
      IVirtualMemory var1 = this.state.getMemory();
      if (!(var1 instanceof IVirtualMemoryShim)) {
         throw new UnsupportedOperationException();
      } else {
         return ((IVirtualMemoryShim)var1).getChanges();
      }
   }

   public long getSPAddress() {
      return this.state.getValueAsUnsignedLong(this.idSP);
   }

   public long updateSPAddress(int var1) {
      long var2 = this.state.getValueAsUnsignedLong(this.idSP);
      var2 += var1;
      this.state.setValue(this.idSP, var2);
      return var2;
   }

   public long getPCAddress() {
      return this.state.getValueAsUnsignedLong(this.idPC);
   }

   public void setPCAddress(long var1) {
      this.state.setValue(this.idPC, var1);
   }

   public void run() throws EvaluationException {
      this.verifyState(this.internalState <= 2);
      if (this.internalState == 0) {
         this.setup();
      }

      this.internalState = 2;
      long var1;
      if (this.routine0 != null) {
         var1 = this.routine0.getMemoryAddress();
      } else {
         if (this.pcStart == null) {
            throw new IllegalStateException("No initial PC");
         }

         var1 = this.pcStart;
      }

      this.state.setValue(this.idPC, var1);
      this.writeRoutineInputs();
      if (this.isExecFull()) {
         this.runFull();
      } else {
         this.runLocal();
      }
   }

   private void runLocal() {
      this.verifyState(!this.isExecFull());
      boolean var1 = this.execmode == 2;
      INativeMethodItem var2 = this.routine0;
      long var3 = var2.getMemoryAddress();
      Integer var5 = null;
      if (var1) {
         if (this.irOffsetStart != null) {
            var5 = this.irOffsetStart;
         } else {
            if (this.pcStart == null) {
               throw new EvaluationException();
            }

            IERoutineContext var6 = this.getIRContext(var3);
            if (var6 == null) {
               throw new EvaluationException();
            }

            this.irOffsetStart = var6.convertNativeAddress(this.pcStart).intValue();
            this.irOffsetStop = var6.convertNativeAddress(this.pcStop).intValue();
            var5 = this.irOffsetStart;
         }
      }

      int var17 = 0;

      label91:
      while (true) {
         IERoutineContext var7 = this.getIRContext(var3);
         if (var7 == null) {
            EEmulator.RegisteredRoutine var18 = (EEmulator.RegisteredRoutine)this.mapRegisteredRoutines.get(var3);
            if (var18 == null) {
               log("Cannot retrieve routine at address 0x%X", var3);
               break;
            }

            if (!Booleans.toBoolean(this.hooksEvaluateExternal(var18.name, var18.routine))) {
               log("Cannot simulate extern %s", var18.name);
               break;
            }

            long var19 = var3;
            var3 = this.state.getProgramCounter().getValueAsAddress();
            if (var19 != var3) {
               var17 = 0;
            } else {
               if (var17 > 1000000) {
                  log("Cannot simulate extern %s: infinite loop detected", var18.name);
                  break;
               }

               var17++;
            }
         } else {
            var2 = var7.getRoutine();
            CFG var8 = var7.getCfg();
            this.state.setRoutineContext(var7);
            int var9;
            long var10;
            if (var5 != null) {
               var9 = var5;
               var10 = var7.convertIntermediateOffset(var5);
               var5 = null;
            } else {
               var10 = var3;
               var9 = var7.convertNativeAddress(var3).intValue();
            }

            this.state.setVirtualPC(var9);
            this.state.setValue(this.PC, var10);

            while (!var1 || var2 != this.routine0 || var9 != this.irOffsetStop) {
               var3 = var7.convertIntermediateOffset(var9);
               if (!var1 && var3 == this.retAddr) {
                  return;
               }

               IEStatement var12 = (IEStatement)var8.getInstruction(var9);
               if (var12 == null) {
                  return;
               }

               Strings.spaces(2 * this.state.countFrames());
               this.lastEvalResult = var12.evaluate(this.state);
               if (!this.state.incrementEvaluationCount()) {
                  log("Exceeding itercount");
                  break label91;
               }

               if (this.state.getRoutineContext() == null) {
                  long var13 = this.getPCAddress();
                  if (var13 == this.retAddr) {
                     return;
                  }

                  this.decomp.getNativeContext().getRoutine(var13);
                  var3 = var13;
                  continue label91;
               }

               var9 = this.state.getVirtualPC();
            }

            return;
         }
      }

      log("Emulation failed!");
      throw new EvaluationException();
   }

   private void runFull() {
      try {
         this.runFullUnsafe();
      } finally {
         this.dbgSafeCloseEmuVerifier();
      }
   }

   private void dbgSafeCloseEmuVerifier() {
   }

   private void runFullUnsafe() {
      this.verifyState(this.isExecFull());
      boolean var1 = this.execmode == 4;
      if (this.pseudoIRContext == null) {
         this.pseudoIRContext = new aki((ajo)this.gctx);
         ((AbstractConverter)this.cv).setCurrentContext(this.pseudoIRContext);
      }

      IVirtualMemory var2 = this.getVirtualMemory();
      IProcessor var3 = this.decomp.getNativeContext().getProcessor();
      long var4 = this.pcStart;
      ConverterInstructionEntry var6 = new ConverterInstructionEntry();
      ArrayList var7 = new ArrayList();
      var6.r = var7;
      this.setPCAddress(var4);
      if (this.mapConverterCachedEntries == null) {
         this.mapConverterCachedEntries = new CacheMap(10000, 2000, true);
      }

      int var8 = this.state.getEvaluationCount();

      while (true) {
         IInstruction var10;
         EEmulator.ConvertedInsnCachedIREntry var12;
         EEmulator.RegisteredRoutine var14;
         while (true) {
            int var9 = this.state.getEvaluationCount();
            var4 = this.getPCAddress();
            if (var1) {
               if (this.pcStop != null && this.pcStop == var4) {
                  log("Emulation COMPLETED - stop address reached (%d iter. - total in State: %d)", var9 - var8, var9);
                  return;
               }
            } else if (var4 == this.retAddr) {
               log("Emulation COMPLETED - routine returned (%d iter. - total in State: %d)", var9 - var8, var9);
               return;
            }

            var12 = (EEmulator.ConvertedInsnCachedIREntry)this.mapConverterCachedEntries.get(var4);
            if (var12 == null) {
               try {
                  var10 = var3.parseAt(var2, var4);
               } catch (ProcessorException var19) {
                  var10 = null;
               }

               var12 = new EEmulator.ConvertedInsnCachedIREntry(var4, var10, null);
               this.mapConverterCachedEntries.put(var4, var12);
            } else {
               var10 = var12.nativeInsn;
            }

            Boolean var13 = this.hooksEvaluateAt(var4, var12.nativeInsn);
            if (var13 == null) {
               if (var12.nativeInsn == null || !this.hooksEvaluateSyscall(var4, var12.nativeInsn)) {
                  var14 = (EEmulator.RegisteredRoutine)this.mapRegisteredRoutines.get(var4);
                  if (var14 == null) {
                     break;
                  }

                  var13 = this.hooksEvaluateExternal(var14.name, var14.routine);
                  if (var13 == null) {
                     break;
                  }

                  if (!var13) {
                     throw new EvaluationException(Strings.ff("evaluateExternal() failed at: 0x%X: %s", var4, var14.name));
                  }

                  this.state.incrementEvaluationCount();
               } else {
                  this.state.incrementEvaluationCount();
               }
            } else {
               if (!var13) {
                  throw new EvaluationException(Strings.ff("evaluateAt() failed at: 0x%X: %s", var4, var12.nativeInsn));
               }

               this.state.incrementEvaluationCount();
            }
         }

         if (var10 == null) {
            if (var14 != null) {
               throw new EvaluationException(Strings.ff("Cannot eval routine: %s", var14));
            }

            throw new EvaluationException(Strings.ff("Cannot parse at address: 0x%X", var4));
         }

         if (var12.stmlist == null) {
            List var11 = var6.r;
            var6.address = var4;
            var6.insn = var10;
            var6.irAddress = 0;
            var6.r.clear();
            ((AbstractConverter)this.cv).convertInstruction(var6);
            EUtil.setLowerLevelAddress(var4, var11);
            var12.stmlist = new ArrayList(var11);
         }

         List var22 = var12.stmlist;
         int var15 = 0;
         this.state.setVirtualPC(var15);
         boolean var16 = false;
         if (var22.size() == 1 && ((IEStatement)var22.get(0)).isUntranslatedInstruction()) {
            IEUntranslatedInstruction var17 = ((IEStatement)var22.get(0)).asUntranslated();
            Boolean var24 = this.hooksEvaluateUntranslated(var17, var10);
            if (var24 == null || !var24) {
               throw new EvaluationException(Strings.ff("evaluateUntranslated() could not do the job at: 0x%X: %s = %s", var4, var10, var17));
            }

            var16 = true;
         }

         if (!var16) {
            for (; var15 >= 0 && var15 < var22.size(); var15 = this.state.getVirtualPC()) {
               IEStatement var25 = (IEStatement)var22.get(var15);

               try {
                  this.setPCAddress(var4);
                  this.lastEvalResult = var25.evaluate(this.state);
               } catch (Exception var20) {
                  log("FAILED TO EVALUATE: %s  { from native insn: 0x%X: %s }", var25, var4, var10);
                  throw var20;
               }
            }
         }

         if (this.state.getValueSafe(this.idPC) == null) {
            this.setPCAddress(var4 + var10.getSize());
         }

         if (!this.state.incrementEvaluationCount()) {
            log("Exceeding itercount (%d)", this.state.getEvaluationCount());
            log("Emulation failed!");
            throw new EvaluationException();
         }
      }
   }

   public void setLastEvaluationResult(IEImm var1) {
      this.lastEvalResult = var1;
   }

   public IEImm getLastEvaluationResult() {
      return this.lastEvalResult;
   }

   private void callstackPush(INativeMethodItem var1, INativeMethodItem var2, long var3, int var5) {
      this.callstack.push(new EEmulator.CSE(var1, var2, var3, var5));
   }

   private EEmulator.CSE callstackPop() {
      return (EEmulator.CSE)this.callstack.pop();
   }

   private IERoutineContext getIRContext(long var1) {
      this.verifyState(!this.isExecFull());
      if (!this.isPrimaryEmulator()) {
         return this.state.getPrimaryEmulator().getIRContext(var1);
      } else {
         INativeMethodItem var3 = this.decomp.getNativeContext().getRoutineOver(var1);
         if (var3 == null) {
            return null;
         } else {
            IERoutineContext var4 = (IERoutineContext)this.ctxmap.get(var3);
            if (var4 == null) {
               IDecompiledMethod var5 = this.decompile(var3);
               if (var5 == null) {
                  return null;
               }

               var4 = var5.getIRContext();
               if (var4 == null) {
                  return null;
               }

               this.ctxmap.put(var3, var4);
            }

            return var4;
         }
      }
   }

   private IDecompiledMethod decompile(INativeMethodItem var1) {
      if (!(this.decomp instanceof INativeDecompilerUnit)) {
         return null;
      } else {
         log("Requesting IR conversion: %s", var1);
         GlobalLog.setGlobalFilter(true);

         Object var3;
         try {
            short var2 = 64;
            if (this.performFreshDecompilations) {
               var2 |= 128;
            }

            DecompilationContext var11 = new DecompilationContext(var2);
            NativeDecompilationStage var4 = NativeDecompilationStage.IR_CONVERSION;
            return ((INativeDecompilerUnit)this.decomp).decompileMethodEx(var1, var11, var4);
         } catch (Exception var9) {
            logger.error(S.L("Failed to generate IR for method: %s"), var1);
            var3 = null;
         } finally {
            GlobalLog.setGlobalFilter(false);
         }

         return (IDecompiledMethod)var3;
      }
   }

   private long generateAddressForPseudoRoutine() {
      long var1 = this.currentAddrForPseudoRoutines;
      this.currentAddrForPseudoRoutines += 16L;
      return var1;
   }

   public long findRegisteredRoutine(String var1) {
      EEmulator.RegisteredRoutine var2 = (EEmulator.RegisteredRoutine)this.mapRegisteredRoutinesByName.get(var1);
      return var2 == null ? 0L : var2.addr;
   }

   public String findRegisteredRoutineByAddress(long var1) {
      EEmulator.RegisteredRoutine var3 = (EEmulator.RegisteredRoutine)this.mapRegisteredRoutines.get(var1);
      return var3 == null ? null : var3.name;
   }

   public long createPseudoRoutine(String var1) {
      EEmulator.RegisteredRoutine var2 = (EEmulator.RegisteredRoutine)this.mapRegisteredRoutinesByName.get(var1);
      if (var2 != null) {
         return var2.addr;
      } else {
         long var3 = this.generateAddressForPseudoRoutine();
         return this.registerRoutine(var3, var1);
      }
   }

   public long createPseudoRoutine(INativeMethodItem var1) {
      EEmulator.RegisteredRoutine var2 = (EEmulator.RegisteredRoutine)this.mapRegisteredRoutinesByName.get(var1.getName(false));
      if (var2 != null) {
         return var2.addr;
      } else {
         long var3 = this.generateAddressForPseudoRoutine();
         return this.registerRoutine(var3, var1);
      }
   }

   public long registerRoutine(long var1, String var3) {
      EEmulator.RegisteredRoutine var4 = new EEmulator.RegisteredRoutine(var1, var3, null);
      this.mapRegisteredRoutines.put(var1, var4);
      this.mapRegisteredRoutinesByName.put(var3, var4);
      return var1;
   }

   public long registerRoutine(long var1, INativeMethodItem var3) {
      String var4 = var3.getName(false);
      EEmulator.RegisteredRoutine var5 = new EEmulator.RegisteredRoutine(var1, var4, var3);
      this.mapRegisteredRoutines.put(var1, var5);
      this.mapRegisteredRoutinesByName.put(var4, var5);
      return var1;
   }

   public final long currentRequestId() {
      return this.lastRequestId;
   }

   private final long getRequestId() {
      this.lastRequestId = this.currentRequestId.getAndIncrement();
      return this.lastRequestId;
   }

   public Boolean hooksEvaluateAt(long var1, IInstruction var3) {
      if (!this.emuhooks.isEmpty()) {
         long var4 = this.getRequestId();
         Boolean var6 = null;

         for (IEEmulatorHooks var8 : this.emuhooks) {
            var6 = var8.evaluateAt(this, var1, var3);
            if (var6 != null) {
               break;
            }
         }

         if (var6 != null) {
            boolean var10 = var6;

            for (IEEmulatorHooks var9 : this.emuhooks) {
               var9.postEvaluateAt(this, var1, var3, var4, var10);
            }

            if (!var10) {
               Object[] var10000 = new Object[]{var1, var3};
            }

            return var10;
         }
      }

      return null;
   }

   public Boolean hooksEvaluateExternal(String var1, INativeMethodItem var2) {
      if (!this.emuhooks.isEmpty()) {
         long var3 = this.getRequestId();
         Boolean var5 = null;

         for (IEEmulatorHooks var7 : this.emuhooks) {
            var5 = var7.evaluateExternal(this, var1, var2);
            if (var5 != null) {
               break;
            }
         }

         if (var5 != null) {
            boolean var9 = var5;

            for (IEEmulatorHooks var8 : this.emuhooks) {
               var8.postEvaluateExternal(this, var1, var2, var3, var9);
            }

            if (!var9) {
               Object[] var10000 = new Object[]{var1};
            }

            return var9;
         }
      }

      return null;
   }

   public Boolean hooksEvaluateUntranslated(IEUntranslatedInstruction var1, IInstruction var2) {
      if (!this.emuhooks.isEmpty()) {
         long var3 = this.getRequestId();
         Boolean var5 = null;

         for (IEEmulatorHooks var7 : this.emuhooks) {
            var5 = var7.evaluateUntranslated(this, var1, var2);
            if (var5 != null) {
               break;
            }
         }

         if (var5 != null) {
            boolean var9 = var5;

            for (IEEmulatorHooks var8 : this.emuhooks) {
               var8.postEvaluateUntranslated(this, var1, var2, var3, var9);
            }

            if (!var9) {
               Object[] var10000 = new Object[]{var1};
            }

            return var9;
         }
      }

      return null;
   }

   public boolean hooksEvaluateSyscall(long var1, IInstruction var3) {
      if (this.emuhooks.isEmpty()) {
         return false;
      } else {
         String var4 = var3.getMnemonic().toLowerCase();
         if (this.proctype == ProcessorType.X86_64) {
            if (var4.equals("syscall")) {
               LinuxSyscallResolver var5 = LinuxSyscallResolver.getInstance(this.proctype);
               IEImm var6 = this.state.getValue(this.gctx.getVariableByName("rax"));
               int var7 = (int)var6.getValueAsLong();
               String var8 = var5.getName(var7);
               INativeMethodItem var9 = var5.getRoutine(var7, this.typeman);
               if (var9 == null) {
                  Object[] var10000 = new Object[]{var7, Strings.safe(var8, "???")};
               }

               ArrayList var10 = new ArrayList();
               var10.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("rdi").getId()));
               var10.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("rsi").getId()));
               var10.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("rdx").getId()));
               var10.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("r10").getId()));
               var10.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("r8").getId()));
               var10.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("r9").getId()));
               long var11 = this.getRequestId();
               Long var13 = null;

               for (IEEmulatorHooks var15 : this.emuhooks) {
                  var13 = var15.evaluateSyscall(this, var1, var3, var7, var8, var9, var10);
                  if (var13 != null) {
                     break;
                  }
               }

               if (var13 != null) {
                  this.state.setValue(this.gctx.getVariableByName("rax"), var13);
                  this.setPCAddress(this.getPCAddress() + var3.getSize());

                  for (IEEmulatorHooks var37 : this.emuhooks) {
                     var37.postEvaluateSyscall(this, var1, var3, var7, var8, var9, var10, var11, var13);
                  }

                  return true;
               }

               Object[] var42 = new Object[]{var8};
            }
         } else if (this.proctype == ProcessorType.X86) {
            if (var4.equals("int")) {
               LinuxSyscallResolver var16 = LinuxSyscallResolver.getInstance(this.proctype);
               IEImm var18 = this.state.getValue(this.gctx.getVariableByName("eax"));
               int var20 = (int)var18.getValueAsLong();
               String var22 = var16.getName(var20);
               INativeMethodItem var24 = var16.getRoutine(var20, this.typeman);
               if (var24 == null) {
                  Object[] var43 = new Object[]{var20, Strings.safe(var22, "???")};
               }

               ArrayList var26 = new ArrayList();
               var26.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("ebx").getId()));
               var26.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("ecx").getId()));
               var26.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("edx").getId()));
               var26.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("esi").getId()));
               var26.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("edi").getId()));
               long var28 = this.getRequestId();
               Long var30 = null;

               for (IEEmulatorHooks var38 : this.emuhooks) {
                  var30 = var38.evaluateSyscall(this, var1, var3, var20, var22, var24, var26);
                  if (var30 != null) {
                     break;
                  }
               }

               if (var30 != null) {
                  this.state.setValue(this.gctx.getVariableByName("eax"), var30);
                  this.setPCAddress(this.getPCAddress() + var3.getSize());

                  for (IEEmulatorHooks var39 : this.emuhooks) {
                     var39.postEvaluateSyscall(this, var1, var3, var20, var22, var24, var26, var28, var30);
                  }

                  return true;
               }

               Object[] var44 = new Object[]{var22};
            }
         } else if (this.proctype == ProcessorType.ARM64 && var4.equals("svc")) {
            LinuxSyscallResolver var17 = LinuxSyscallResolver.getInstance(this.proctype);
            IEImm var19 = this.state.getValue(this.gctx.getVariableByName("X8"));
            int var21 = (int)var19.getValueAsLong();
            String var23 = var17.getName(var21);
            INativeMethodItem var25 = var17.getRoutine(var21, this.typeman);
            if (var25 == null) {
               Object[] var45 = new Object[]{var21, Strings.safe(var23, "???")};
            }

            ArrayList var27 = new ArrayList();
            var27.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("X0").getId()));
            var27.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("X1").getId()));
            var27.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("X2").getId()));
            var27.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("X3").getId()));
            var27.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("X4").getId()));
            var27.add(this.state.getValueAsLongSafe(this.gctx.getVariableByName("X5").getId()));
            long var29 = this.getRequestId();
            Long var31 = null;

            for (IEEmulatorHooks var40 : this.emuhooks) {
               var31 = var40.evaluateSyscall(this, var1, var3, var21, var23, var25, var27);
               if (var31 != null) {
                  break;
               }
            }

            if (var31 != null) {
               this.state.setValue(this.gctx.getVariableByName("X0"), var31);
               this.setPCAddress(this.getPCAddress() + var3.getSize());

               for (IEEmulatorHooks var41 : this.emuhooks) {
                  var41.postEvaluateSyscall(this, var1, var3, var21, var23, var25, var27, var29, var31);
               }

               return true;
            }

            Object[] var46 = new Object[]{var23};
         }

         return false;
      }
   }

   public void monitorHLSpecial(int var1, Object... var2) {
      if (!this.emuhooks.isEmpty()) {
         List var3 = Collections.unmodifiableList(Arrays.asList(var2));

         for (IEEmulatorHooks var5 : this.emuhooks) {
            try {
               var5.monitorHLSpecial(this, var1, var3);
            } catch (Exception var6) {
            }
         }
      }
   }

   public IEImm readStorage(StorageEntry var1) {
      switch (var1.getType()) {
         case STACK:
            long var7 = this.state.getStackPointer().getValueAsAddress();
            long var8 = var7 + var1.getValueAsStackIndex() * this.slotsize;

            try {
               if (this.slotsize == 4) {
                  return ajr.pC(this.state.getMemory().readInt(var8), this.slotsize * 8);
               }

               if (this.slotsize == 8) {
                  return ajr.pC(this.state.getMemory().readLong(var8), this.slotsize * 8);
               }
            } catch (Exception var6) {
            }

            return null;
         case REGISTER:
            long var2 = var1.getValue(this.state.isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN);
            IEVar var4 = (IEVar)this.state.getGlobalContext().getConverter().getRegisterVariableFromNativeRegisterId(var2);
            return this.state.getValue(var4);
         default:
            return null;
      }
   }

   private byte[] shortToBytes(short var1) {
      return this.gctx.isBigEndian() ? EndianUtil.shortToBEBytes(var1) : EndianUtil.shortToLEBytes(var1);
   }

   private byte[] intToBytes(int var1) {
      return this.gctx.isBigEndian() ? EndianUtil.intToBEBytes(var1) : EndianUtil.intToLEBytes(var1);
   }

   private byte[] longToBytes(long var1) {
      return this.gctx.isBigEndian() ? EndianUtil.longToBEBytes(var1) : EndianUtil.longToLEBytes(var1);
   }

   public boolean writeStorage(StorageEntry var1, IEImm var2) {
      switch (var1.getType()) {
         case STACK:
            long var9 = this.state.getStackPointer().getValueAsAddress();
            long var10 = var9 + var1.getValueAsStackIndex() * this.slotsize;
            if (this.slotsize == 2) {
               short var12 = (short)var2.getValueAsLong();
               return this.state.writeMemory(var10, this.shortToBytes(var12));
            } else if (this.slotsize == 4) {
               int var11 = (int)var2.getValueAsLong();
               return this.state.writeMemory(var10, this.intToBytes(var11));
            } else {
               if (this.slotsize == 8) {
                  long var7 = var2.getValueAsLong();
                  return this.state.writeMemory(var10, this.longToBytes(var7));
               }

               return false;
            }
         case REGISTER:
            long var3 = var1.getValue(this.state.isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN);
            IEVar var5 = (IEVar)this.state.getGlobalContext().getConverter().getRegisterVariableFromNativeRegisterId(var3);
            this.state.setValue(var5, var2.truncate(var5.getBitsize()));
            return true;
         default:
            return false;
      }
   }

   public boolean processStoredReturnAddress(StorageEntry var1) {
      return this.processStoredReturnAddress(var1, 0);
   }

   public boolean processStoredReturnAddress(StorageEntry var1, int var2) {
      switch (var1.getType()) {
         case STACK:
            if (var1.getValueAsStackIndex() != 0) {
               return false;
            } else {
               IEImm var9 = this.state.getValue(this.SP);

               long var4;
               try {
                  var4 = this.state.getMemory().readPointer(var9.getValueAsAddress());
               } catch (MemoryException var8) {
                  return false;
               }

               this.state.setValue(this.SP, var9._add(ajr.pC((1 + var2) * this.slotsize, var9.getBitsize())));
               this.state.setValue(this.PC, var4);
               return true;
            }
         case REGISTER:
            long var3 = var1.getValue(this.state.isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN);
            IEVar var5 = (IEVar)this.state.getGlobalContext().getConverter().getRegisterVariableFromNativeRegisterId(var3);
            IEImm var6 = this.state.getValue(var5);
            if (var2 != 0) {
               IEImm var7 = this.state.getValue(this.SP);
               this.state.setValue(this.SP, var7._add(ajr.pC(var2 * this.slotsize, var7.getBitsize())));
            }

            this.state.setValue(this.PC, var6.truncate(this.PC.getBitsize()));
            return true;
         default:
            return false;
      }
   }

   public Long readPointer(long var1) {
      this.verifyState(this.internalState >= 1);

      try {
         return this.state.getMemory().readPointer(var1);
      } catch (MemoryException var3) {
         return null;
      }
   }

   public boolean writePointer(long var1, long var3) {
      this.verifyState(this.internalState >= 1);

      try {
         this.state.getMemory().writePointer(var1, var3);
         return true;
      } catch (MemoryException var5) {
         return false;
      }
   }

   public MemoryWrites getMemoryWrites() {
      this.verifyState(this.internalState >= 2);
      if (this.memwrites == null) {
         return null;
      } else {
         if (!this.memwrites.isComplete()) {
            this.memwrites.complete();
         }

         return this.memwrites;
      }
   }

   public IEImm getReturnValue() {
      this.verifyState(this.internalState >= 2);
      if (this.proto0.getCountOfReturns() != 1) {
         return null;
      } else {
         INativeType var1 = this.proto0.getReturnType();
         TypeLayoutInfo var2 = TypeUtil.getLayoutInfo(var1);
         ICallingConvention var3 = this.proto0.getCallingConvention();
         StorageEntry var4 = var3.getOutput(var2, 0);
         return this.readStorage(var4);
      }
   }

   public IEImm getReturnAddress() {
      this.verifyState(this.internalState >= 2);
      ICallingConvention var1 = this.proto0.getCallingConvention();
      StorageEntry var2 = var1.getReturnAddressSlot();
      return this.readStorage(var2);
   }

   public IEImm getRegisterValue(String var1) {
      IEVar var2 = this.gctx.getVariableByName(var1);
      int var3 = var2.getId();
      IEImm var4 = this.state.getValueSafe(var3);
      return var4 != null ? var4 : EUtil.zero(var2.getBitsize());
   }

   public long getTruncatedRegisterValue(String var1) {
      int var2 = this.gctx.getVariableByName(var1).getId();
      IEImm var3 = this.state.getValueSafe(var2);
      return var3 == null ? 0L : var3.getValue().longValue();
   }

   public boolean commitMemoryChanges(boolean var1) {
      this.verifyState(this.internalState >= 2);
      if (!(this.state.getMemory() instanceof IVirtualMemoryShim var3)) {
         return false;
      } else {
         if (var1) {
            var3.commitChanges(true, false, false);
         } else {
            var3.commitChanges(true, true, true);
         }

         return true;
      }
   }

   public void registerHooks(IEEmulatorHooks var1, boolean var2) {
      if (var1 != null) {
         int var3 = -1;
         int var5 = var1.getPriority();

         int var4;
         for (var4 = 0; var4 < this.emuhooks.size(); var4++) {
            int var6 = ((IEEmulatorHooks)this.emuhooks.get(var4)).getPriority();
            if (var6 < var5) {
               break;
            }

            if (var6 == var5 && var3 == -1) {
               var3 = var4;
            }
         }

         if (var2 && var3 >= 0) {
            this.emuhooks.add(var3, var1);
         } else {
            this.emuhooks.add(var4, var1);
         }
      }
   }

   public void unregisterHooks(IEEmulatorHooks var1) {
      if (var1 != null) {
         this.emuhooks.remove(var1);
      }
   }

   public long heapAlloc(int var1) {
      if (var1 <= 0) {
         return 0L;
      } else {
         var1 += 4;
         IVirtualMemory var2 = this.state.getMemory();
         if (this.heapCurrent == -1L) {
            int var3 = (int)var2.roundToSize(var1);
            this.heapCurrent = VirtualMemoryUtil.allocate(var2, this.heapPreferredBase, var3, 3);
            this.heapEnd = this.heapCurrent + var3;
         } else {
            int var8 = (int)(this.heapEnd - this.heapCurrent);
            if (var8 < var1) {
               int var4 = (int)var2.roundToSize(var1 - var8);

               try {
                  var2.allocate(this.heapEnd, var4, 3);
               } catch (MemoryException var6) {
                  return 0L;
               }

               this.heapEnd += var4;
            }
         }

         long var9 = this.heapCurrent;
         this.heapCurrent += var1;

         try {
            var2.writeInt(var9, var1 - 4);
         } catch (MemoryException var5) {
            return 0L;
         }

         return var9 + 4L;
      }
   }

   public long heapRealloc(long var1, int var3) {
      IVirtualMemory var4 = this.state.getMemory();

      int var5;
      try {
         var5 = var4.readInt(var1 - 4L);
      } catch (MemoryException var9) {
         return 0L;
      }

      long var6 = this.heapAlloc(var3);
      if (var6 == 0L) {
         return 0L;
      } else {
         byte[] var8 = new byte[var5];
         if (VirtualMemoryUtil.readBytes(var4, var1, var8, 0, var5) && VirtualMemoryUtil.writeBytes(var4, var6, var8, 0, var5)) {
            this.heapFree(var1);
            return var6;
         } else {
            this.heapFree(var6);
            return 0L;
         }
      }
   }

   public void heapFree(long var1) {
   }

   public EEmulator.Metadata getMetadata() {
      return this.metadata;
   }

   static class CSE {
      INativeMethodItem target;
      INativeMethodItem caller;
      long callerPC;
      int callerVirtualPC;

      CSE(INativeMethodItem var1, INativeMethodItem var2, long var3, int var5) {
         this.target = var1;
         this.caller = var2;
         this.callerPC = var3;
         this.callerVirtualPC = var5;
      }
   }

   static class ConvertedInsnCachedIREntry {
      long nativeAddress;
      IInstruction nativeInsn;
      List stmlist;

      ConvertedInsnCachedIREntry(long var1, IInstruction var3, List var4) {
         this.nativeAddress = var1;
         this.nativeInsn = var3;
         this.stmlist = var4;
      }
   }

   private class MemHooks implements IEStateHooks {
      @Override
      public Integer onReadMemory(EState var1, long var2, byte[] var4) {
         INativeContinuousItem var5 = EEmulator.this.code.getNativeItemAt(var2);
         if (var5 != null && (var5.getMemorySize() == var4.length || var4.length == EEmulator.this.addrsize) && var5 instanceof auz) {
            INativeMethodItem var6 = ((auz)var5).UO();
            if (var6 != null && ((auu)var6).Cu() == null && var6.getMemoryAddress() == null) {
               long var7 = EEmulator.this.createPseudoRoutine(var6);

               try {
                  var1.getMemory().writePointer(var2, var7);
               } catch (MemoryException var9) {
                  return null;
               }
            }
         }

         return null;
      }

      @Override
      public Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
         if (EEmulator.this.memwrites != null && var2 != 0L && var4 != null && var4.length != 0) {
            EEmulator.log("MEM.WRITE: 0x%X: %s", var2, Formatter.byteArrayToHex(var4));
            EEmulator.this.memwrites.record(var2, var4);
         }

         if (EEmulator.this.mapConverterCachedEntries != null) {
            EEmulator.this.mapConverterCachedEntries.removeRange(var2, var2 + var4.length);
         }

         return null;
      }
   }

   public static class Metadata {
      public String processName;
   }

   static class RegisteredRoutine {
      long addr;
      String name;
      INativeMethodItem routine;

      public RegisteredRoutine(long var1, String var3, INativeMethodItem var4) {
         this.addr = var1;
         this.name = var3;
         this.routine = var4;
      }

      @Override
      public String toString() {
         return Strings.ff("%s (0x%X)", this.name, this.addr);
      }
   }
}
