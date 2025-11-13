package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableInformationProvider;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.AddressConversionLists;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVarCopyFinder;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.concurrent.TimedOperationVerifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class aki implements IVariableInformationProvider, IERoutineContext, INativeItemListener {
   private static final StructuredLogger fI = aco.pC(aki.class);
   @Deprecated
   public static Boolean pC;
   @SerId(1)
   int A;
   @SerId(2)
   int kS;
   @SerId(3)
   INativeMethodItem wS;
   @SerId(4)
   ajo UT;
   @SerId(5)
   private List WR;
   @SerId(value = 6, deprecated = true)
   private String NS;
   @SerId(value = 7, deprecated = true)
   private LinkedHashMap vP = new LinkedHashMap();
   @SerId(8)
   private Map xC = new HashMap();
   @SerId(9)
   private Map ED = new HashMap();
   @SerId(10)
   akw E;
   @SerId(11)
   private akl Sc;
   @SerId(12)
   private Map ah = new HashMap();
   @SerId(13)
   private SPDC eP = SPDC.Unknown;
   @SerId(14)
   private SPDC UO = SPDC.Unknown;
   @SerId(15)
   private akv Ab;
   @SerId(16)
   boolean sY;
   @SerId(17)
   boolean ys;
   @SerId(18)
   int ld;
   @SerId(19)
   Boolean gp;
   @SerId(20)
   private CFG rl;
   @SerTransient
   TimedOperationVerifier oT;
   @SerTransient
   private List z;
   @SerTransient
   private CacheMap Ek;
   @SerTransient
   private Map hK;

   @SerCustomInitPostGraph
   private void sY() {
      if (this.vP != null) {
         this.rl = (CFG)this.vP.get(this.NS);
      }
   }

   public aki(ajo var1) {
      this(var1, null);
   }

   aki(ajo var1, INativeMethodItem var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("A global context must be provided");
      } else {
         this.UT = var1;
         this.wS = var2;
         this.A = Integer.MAX_VALUE;
         this.kS = Integer.MAX_VALUE;
         this.E = new akw(1, false);
         this.Ab = new akv(this.E, var1.gp, this.A);
         this.Ab.pC(this);
      }
   }

   public aki(ajo var1, int var2, int var3) {
      this(var1);
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String getTargetName() {
      return this.wS == null ? null : this.wS.getSignature();
   }

   public void pC() {
      if (this.sY) {
         this.wS.getData().removeListener(this);
         this.sY = false;
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() == this.wS.getData()) {
         var1.getType().isDeepChange();
      }
   }

   @Override
   public void setRoutine(INativeMethodItem var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (this.wS != null && this.wS != var1) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
         Assert.a(var1.isInternal(), "Expected an internal routine");
      }
   }

   @Override
   public INativeMethodItem getRoutine() {
      return this.wS;
   }

   @Override
   public IWildcardPrototype getPrototype() {
      return this.UT.getCandidatePrototype(this.wS);
   }

   @Override
   public void setPrototype(IWildcardPrototype var1) {
      this.UT.setCandidatePrototype(this.wS, var1, 1);
   }

   public synchronized akl A() {
      if (this.Sc == null) {
         this.Sc = new akl(this);
      }

      return this.Sc;
   }

   @Override
   public INativeContext getNativeContext() {
      return this.UT.getConverter().getNativeContext();
   }

   @Override
   public INativeDecompilerContext getDecompiler() {
      return this.UT.getConverter().getDecompiler();
   }

   public AbstractConverter kS() {
      return this.UT.getConverter();
   }

   @Override
   public IEGlobalContext getGlobalContext() {
      return this.UT;
   }

   @Override
   public IWildcardTypeManager getWildcardTypeManager() {
      return this.UT.getWildcardTypeManager();
   }

   @Override
   public IEVar getProgramCounter() {
      return this.UT.getConverter().getProgramCounter();
   }

   @Override
   public int getProgramCounterId() {
      return this.A != Integer.MAX_VALUE ? this.A : this.UT.getConverter().getProgramCounter().getId();
   }

   @Override
   public IEVar getStackPointer() {
      return this.UT.getConverter().getStackPointer();
   }

   @Override
   public int getStackPointerId() {
      return this.kS != Integer.MAX_VALUE ? this.kS : this.UT.getConverter().getStackPointer().getId();
   }

   public SPDC pC(SPDC var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var2 && var1.compareTo(this.eP) <= 0) {
         return null;
      } else {
         SPDC var3 = this.eP;
         this.eP = var1;
         return var3;
      }
   }

   public SPDC wS() {
      return this.eP;
   }

   public SPDC A(SPDC var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var2 && var1.compareTo(this.UO) <= 0) {
         return null;
      } else {
         SPDC var3 = this.UO;
         this.UO = var1;
         return var3;
      }
   }

   public SPDC UT() {
      return this.UO;
   }

   @Override
   public boolean isAllowUnsafeAnalysis() {
      return this.gp == null ? true : this.gp;
   }

   @Override
   public Long convertNativeAddress(long var1) {
      return this.pC(var1, false);
   }

   Long pC(long var1, boolean var3) {
      if (this.rl == null) {
         if (this.xC == null) {
            throw new IllegalStateException();
         }

         Integer var4 = (Integer)this.xC.get(var1);
         if (var4 != null) {
            return var4.intValue() & 4294967295L;
         }
      } else if (this.z != null) {
         int var7 = 0;

         for (IEStatement var6 : this.z) {
            if (var6.getLowerLevelAddresses().contains(var1)) {
               return var7 & 4294967295L;
            }

            var7 += var6.getSize();
         }
      } else {
         if (this.Ek == null) {
            this.Ek = new CacheMap(200);
         } else {
            Long var8 = (Long)this.Ek.get(var1);
            if (var8 != null) {
               IEStatement var10 = (IEStatement)this.rl.getInstruction(var8);
               if (var10 != null && var10.getLowerLevelAddresses().contains(var1)) {
                  return var8 & 4294967295L;
               }

               this.Ek.remove(var1);
            }
         }

         for (AddressableInstruction var11 : this.rl.addressableInstructions()) {
            if (((IEStatement)var11.getInstruction()).getLowerLevelAddresses().contains(var1)) {
               this.Ek.put(var1, var11.getOffset());
               return var11.getOffset() & 4294967295L;
            }
         }
      }

      if (var3) {
         fI.debug("Cannot convert native address %Xh to IR offset", var1);
      }

      return null;
   }

   @Override
   public Long convertIntermediateOffset(int var1) {
      return this.pC(var1, false);
   }

   Long pC(int var1, boolean var2) {
      if (this.rl == null) {
         if (this.ED == null) {
            throw new IllegalStateException();
         } else {
            return (Long)this.ED.get(var1);
         }
      } else {
         if (this.z != null) {
            int var3 = 0;

            for (IEStatement var5 : this.z) {
               if (var3 == var1) {
                  return var5.getPrimaryLowerLevelAddress();
               }

               if (var3 > var1) {
                  break;
               }

               var3 += var5.getSize();
            }
         } else {
            IEStatement var6 = (IEStatement)this.rl.getInstruction(var1);
            if (var6 != null) {
               return var6.getPrimaryLowerLevelAddress();
            }
         }

         if (var2) {
            fI.debug("Cannot convert IR offset %Xh to native address", var1);
         }

         return null;
      }
   }

   @Override
   public List getIntermediateOffsetsMappingToNativeAddress(long var1) {
      if (this.rl == null) {
         throw new IllegalStateException();
      } else {
         ArrayList var3 = new ArrayList();

         for (AddressableInstruction var5 : this.rl.addressableInstructions()) {
            if (((IEStatement)var5.getInstruction()).getLowerLevelAddresses().contains(var1)) {
               var3.add(var5.getOffset() & 4294967295L);
            }
         }

         return var3;
      }
   }

   @Override
   public List getIntermediateOffsetsMappingToNativeAddresses(Collection var1) {
      if (this.rl == null) {
         throw new IllegalStateException();
      } else {
         ArrayList var2 = new ArrayList();

         for (AddressableInstruction var4 : this.rl.addressableInstructions()) {
            if (CollectionUtil.hasIntersection(((IEStatement)var4.getInstruction()).getLowerLevelAddresses(), var1)) {
               var2.add(var4.getOffset() & 4294967295L);
            }
         }

         return var2;
      }
   }

   @Override
   public void setStatements(List var1) {
      this.setStatements(var1, false, false, false);
   }

   @Override
   public void setStatements(List var1, boolean var2, boolean var3, boolean var4) {
      if (this.WR != null && this.WR != var1) {
         throw new IllegalStateException("The original IR-statements list was already set");
      } else {
         if (var2) {
            acm.pC(var1);
         }

         if (var3 && (pC == null || !pC)) {
            acm.A(var1);
         }

         this.WR = var1;
         AddressConversionLists var5 = AddressConversionLists.generateFromList(var1);
         this.xC = var5.getNativeToInter();
         this.ED = var5.getInterToNative();
         if (var4) {
            this.buildCfg(var1);
         }
      }
   }

   @Override
   public CFG buildCfg(List var1) {
      return this.buildCfg(var1, false, true);
   }

   @Override
   public CFG buildCfg(List var1, boolean var2, boolean var3) {
      this.z = var1;

      CFG var7;
      try {
         byte var4 = 2;
         if (var2) {
            var4 |= 1;
         }

         CFG var5 = new CFG(var1, null, null, 0L, 0L, var4);
         var5.setTimedOperationVerifier(this.oT);
         aoe.pC(var5);
         int var6 = var1.size() - var5.getInstructionCount();
         if (var6 != 0) {
            fI.iH(
               "WARNING! The IR-CFG that was just built does not use all instructions of the stmlist (unused: %d)\nThis is not necessarily a problem, but it should be investigated if problems arise, as it may hint of a bad conversion.",
               var6
            );
         }

         if (var3) {
            this.setCfg(var5);
         }

         var7 = var5;
      } catch (Exception var11) {
         Object[] var10000 = new Object[0];
         throw var11;
      } finally {
         this.z = null;
      }

      return var7;
   }

   @Override
   public void invalidateDataFlowAnalysis() {
      if (this.rl != null) {
         this.rl.invalidateDataFlowAnalysis();
      }
   }

   @Override
   public List getStatements() {
      return this.WR;
   }

   @Override
   public CFG getCfg() {
      return this.rl;
   }

   @Override
   public void setCfg(CFG var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.rl = var1;
         var1.setVariableInformationProvider(this);
         this.ED = null;
         this.xC = null;
      }
   }

   public boolean E() {
      return this.ys;
   }

   @Override
   public IEImm createImm(long var1, int var3) {
      return this.UT.createImm(var1, var3);
   }

   @Override
   public IEImm createImm(byte[] var1, int var2) {
      return this.UT.createImm(var1, var2);
   }

   @Override
   public IEImm createImm(BigInteger var1, int var2) {
      return this.UT.createImm(var1, var2);
   }

   @Override
   public IEImm createImm(String var1, int var2) {
      return this.UT.createImm(var1, var2);
   }

   @Override
   public IEImm createImm(float var1) {
      return this.UT.createImm(var1);
   }

   @Override
   public IEImm createImm(double var1) {
      return this.UT.createImm(var1);
   }

   @Override
   public Set getSame(int var1) {
      Set var2 = this.Ab.UT(var1);
      VarSrc var3 = this.Ab.pC(var1);
      if (var3 != null) {
         var2.remove(var1);
      }

      return var2;
   }

   @Override
   public String getName(int var1) {
      IEVar var2 = this.A(var1, false);
      return var2 != null ? var2.getName() : (var1 >= 0 ? Integer.toHexString(var1) : "-" + Integer.toHexString(-var1));
   }

   @Override
   public String getSliceName(int var1, int var2, int var3) {
      IEVar var4 = this.getVariableById(var1);
      return var4 != null ? this.kS().getSlicedRegisterName(var4.getName(), var2, var3) : null;
   }

   @Override
   public Integer getUnderlyingRegisterId(int var1) {
      if (var1 >= 0 && var1 < 131072) {
         return var1;
      } else {
         VarSrc var2 = this.Ab.pC(var1);
         if (var2 != null) {
            if (var2.isDuplicate()) {
               return var2.getAsDuplicate();
            }

            if (var2.isTruncated()) {
               return (Integer)var2.getAsTruncated().getFirst();
            }
         }

         return null;
      }
   }

   @Override
   public IEVar getRegisterMirror(IEVar var1) {
      if (!var1.isRegister()) {
         throw new RuntimeException(Strings.ff("Variable \"%s\" does not map to a register", var1));
      } else {
         int var2 = akv.A(var1.getId());
         IEVar var3 = this.E.A(var2);
         return var3 != null ? var3 : this.Ab.pC(var1);
      }
   }

   @Override
   public IEVar copyVariable(IEVar var1) {
      return this.Ab.pC(var1);
   }

   @Override
   public Couple copyTruncatedVariable(IEVar var1, int var2) {
      return this.Ab.pC(var1, var2);
   }

   @Override
   public IEVar copyPairOfVariables(IEVar var1, IEVar var2) {
      return this.Ab.pC(var1, var2);
   }

   @Override
   public EVarCopyFinder copyFinder(StorageEntry var1, Collection var2, Long var3) {
      return new EVarCopyFinder(this, var1, var2, var3);
   }

   @Override
   public VarSrc getSourceForVariable(int var1) {
      return this.Ab.pC(var1);
   }

   @Override
   public Set getCopiesOfVariable(int var1) {
      return this.Ab.kS(var1);
   }

   @Override
   public Set getCopiesUsingVariable(int var1) {
      return this.Ab.wS(var1);
   }

   @Override
   public boolean usesCopyVars() {
      return this.Ab.pC() > 0;
   }

   @Override
   public List getDuplicatesForRegister(int var1) {
      return this.Ab.E(var1);
   }

   public aku pC(String var1, int var2) {
      IEVar var3 = this.E.pC(2, 65536, var1, var2, null);
      Assert.a(var3 != null);
      return (aku)var3;
   }

   @Override
   public IEVar getVariableByName(String var1) {
      IEVar var2 = this.E.pC(var1);
      if (var2 == null) {
         var2 = this.UT.getVariableByName(var1);
      }

      return var2;
   }

   @Override
   public IEVar createVirtualVar(String var1, int var2) {
      IEVar var3 = this.E.pC(16777216, 33554432, var1, var2, null);
      Assert.a(var3 != null);
      return var3;
   }

   @Override
   public IEVar getStackReference(long var1) {
      return (IEVar)this.ah.get(var1);
   }

   @Override
   public IEVar removeStackReference(long var1) {
      IEVar var3 = (IEVar)this.ah.remove(var1);
      if (var3 == null) {
         return null;
      } else {
         IEVar var4 = this.E.pC(-var3.getId());
         Assert.a(var4 == var3);
         return var3;
      }
   }

   @Override
   public IEVar createStackReference(long var1) {
      return this.createStackReference(var1, null);
   }

   @Override
   public IEVar createStackReference(long var1, IWildcardType var3) {
      IEVar var4 = (IEVar)this.ah.get(var1);
      if (var4 != null) {
         return var4;
      } else {
         String var5 = "ptr_" + (var1 >= 0L ? Strings.ff("par%02X", var1) : Strings.ff("var%02X", -var1));
         int var6 = this.UT.getAddressBitsize();
         var4 = this.E.pC(8388608, 8454144, var5, var6, null);
         ((aku)var4).pC(var1);
         this.ah.put(var1, var4);
         if (var3 == null) {
            var3 = this.getWildcardTypeManager().createPointer(0);
         }

         var4.setType(var3);
         return var4;
      }
   }

   public int pC(long var1) {
      int var3 = 1073741824 + (int)(var1 * 8L);
      return -var3;
   }

   public IEVar pC(int var1) {
      return this.A(var1, true);
   }

   public IEVar A(int var1, boolean var2) {
      IEVar var3;
      if (var1 < 0) {
         var3 = this.E.A(-var1);
      } else {
         var3 = this.UT.getVarSafe(var1);
      }

      if (var3 == null && var2) {
         throw new RuntimeException("The variable does not exist: id=" + var1);
      } else {
         return (aku)var3;
      }
   }

   @Override
   public IEVar getVariableById(int var1) {
      return this.A(var1, false);
   }

   public IEVar A(int var1) {
      IEVar var2;
      if (var1 < 0) {
         var2 = this.E.kS(-var1);
      } else {
         var2 = this.UT.gp.kS(var1);
      }

      if (var2 == null) {
         throw new RuntimeException("The variable does not exist: vbit=" + var1);
      } else {
         return (aku)var2;
      }
   }

   @Override
   public Collection getRoutineVariablesInRange(int var1, int var2) {
      if (var1 < 0) {
         var1 = -var1;
         var2 = -var2;
      }

      return (Collection)(var2 < var1 ? Collections.emptyList() : Collections.unmodifiableCollection(this.E.pC(var1, var2)));
   }

   @Override
   public Collection getStackVariables() {
      return this.E.pC(33554432, Integer.MAX_VALUE);
   }

   @Override
   public IEVar getStackVariable(int var1) {
      int var2 = 1073741824 + var1 * 8;
      return var2 >= 33554432 && var2 < Integer.MAX_VALUE ? this.E.A(var2) : null;
   }

   @Override
   public Collection getStackVariables(int var1, int var2) {
      if (var1 >= var2) {
         return Collections.emptyList();
      } else {
         int var3 = 1073741824 + var1 * 8;
         if (var3 < 33554432) {
            var3 = 33554432;
         } else if (var3 >= Integer.MAX_VALUE) {
            return Collections.emptyList();
         }

         int var4 = 1073741824 + var2 * 8;
         if (var4 > Integer.MAX_VALUE) {
            var4 = Integer.MAX_VALUE;
         } else if (var4 <= 33554432) {
            return Collections.emptyList();
         }

         return this.E.pC(var3, var4);
      }
   }

   @Override
   public Collection getMemoryVariables() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.E.pC(33554432, Integer.MAX_VALUE));
      var1.addAll(this.UT.getGlobalVariables());
      return var1;
   }

   @Override
   public IEGeneric retrieveVariableForRegister(IEGeneric var1, Collection var2, boolean var3) {
      return EVarCopyFinder.retrieveVariableForRegister(this, var1, var2, var3, null);
   }

   @Override
   public IEGeneric getInputVariableForRegister(IDFA var1, IEVar var2) {
      Collection var3 = var1.getInputs();
      return this.retrieveVariableForRegister(var2, var3, false);
   }

   @Override
   public IEGeneric getInputVariableForRegister(IDFA var1, long var2) {
      IEGeneric var4 = this.kS().getRegisterVariableFromNativeRegisterId(var2);
      Collection var5 = var1.getInputs();
      return this.retrieveVariableForRegister(var4, var5, false);
   }

   @Override
   public IEGeneric getOutputVariableForRegister(IDFA var1, long var2, IEVar var4) {
      IBasicBlock var5 = var1.getCfg().getBlockByLastAddress(var2);
      Collection var6 = var1.getOutputs(var5);
      return this.retrieveVariableForRegister(var4, var6, false);
   }

   @Override
   public IEGeneric getOutputVariableForRegister(IDFA var1, long var2, int var4) {
      IEGeneric var5 = this.kS().getRegisterVariableFromNativeRegisterId(var4);
      IBasicBlock var6 = var1.getCfg().getBlockByLastAddress(var2);
      Collection var7 = var1.getOutputs(var6);
      return this.retrieveVariableForRegister(var5, var7, false);
   }

   @Override
   public List getRoutineInputVariables() {
      IEPrototypeHandler var1 = this.kS().getPrototypeHandler(this);
      ArrayList var2 = new ArrayList();
      return !var1.retrieveFromPrototype(var2, null) ? null : var2;
   }

   @Override
   public IEVar createSymbolForRoutine(INativeMethodItem var1) {
      return this.UT.createSymbolForRoutine(var1, this);
   }

   @Override
   public IEVar createSymbolForField(INativeFieldItem var1) {
      throw new RuntimeException();
   }

   @Override
   public IEVar createSymbolForGlobalVariable(INativeDataItem var1) {
      return this.UT.createSymbolForData(var1, this);
   }

   @Override
   public void acquireNativeItem(INativeItem var1) {
      this.UT.getObjectTracker().acquire(var1, this.wS);
   }

   @Override
   public IEAssign createAssign(IEGeneric var1, IEGeneric var2) {
      return new ajg(this, var1, var2);
   }

   @Override
   public IEAssign createBranchAssign(IEGeneric var1, IEGeneric var2, boolean var3) {
      return new ajg(this, var1, var2, var3 ? 2 : 1);
   }

   @Override
   public IEAssign createAssignIf(IEAssign var1, IEGeneric var2) {
      var1.isBreakingFlow();
      byte var3;
      if (var1.isRoutineCall()) {
         var3 = 2;
      } else {
         var3 = 0;
      }

      return this.pC(var1.getDstOperand(), var1.getSrcOperand(), var2, var3);
   }

   private IEAssign pC(IEGeneric var1, IEGeneric var2, IEGeneric var3, int var4) {
      return new ajg(this, var1, this.createCond(var3, var2, var1), var4);
   }

   @Override
   public IEGroupElt createGroupElt(IEGroup var1, IEGeneric var2) {
      return this.UT.createGroupElt(var1, var2);
   }

   @Override
   public IESwitch createSwitch(IEGeneric var1, int var2) {
      return new ako(this, var1, var2);
   }

   @Override
   public IEJump createJump(int var1) {
      return new ajx(this, var1);
   }

   @Override
   public IEJump createJump(int var1, IEGeneric var2) {
      return new ajx(this, var1, var2);
   }

   @Override
   public IEJumpFar createJumpFar(IEGeneric var1) {
      return new ajy(this, var1);
   }

   @Override
   public IEJumpFar createJumpFar(IEGeneric var1, IEGeneric var2) {
      return new ajy(this, var1, var2);
   }

   @Override
   public IENop createNop() {
      return new akb(this);
   }

   @Override
   public IENop createNop(IEStatement var1) {
      return new akb(this, var1);
   }

   @Override
   public IEReturn createReturn() {
      return new akh(this);
   }

   @Override
   public IEReturn createReturn(IEGeneric var1) {
      return new akh(this, var1);
   }

   @Override
   public IEReturn createReturn(List var1) {
      return new akh(this, var1);
   }

   @Override
   public IECall createCall(IEGeneric var1, IEGeneric var2, List var3, List var4, int var5, List var6, IWildcardPrototype var7) {
      return new ajk(this, var1, null, var7, var2, var3, var4, var5, var6);
   }

   @Override
   public IECall createCall(IEGeneric var1, List var2, IWildcardPrototype var3, List var4, boolean var5) {
      return new ajk(this, var1, var2, var3, var4, var5);
   }

   @Override
   public IEUntranslatedInstruction createUntranslatedInstruction(long var1, String var3, IEGeneric... var4) {
      return new aks(this, var1, var3, var4);
   }

   @Override
   public IEUntranslatedInstruction createUntranslatedInstruction(long var1, String var3, List var4) {
      return new aks(this, var1, var3, var4);
   }

   @Override
   public IEMem createMem(IEGeneric var1, int var2) {
      return this.UT.createMem(var1, var2);
   }

   @Override
   public IEMem createMem(IEGeneric var1, IEGeneric var2, int var3) {
      return this.UT.pC(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2) {
      return this.UT.createOperation(var1, var2);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2, IEGeneric var3) {
      return this.UT.createOperation(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(FunctionOptype var1, IEGeneric... var2) {
      return this.UT.createOperation(var1, var2);
   }

   @Override
   public IEOperation createResizeOperation(IEGeneric var1, int var2, boolean var3) {
      return this.UT.createResizeOperation(var1, var2, var3);
   }

   @Override
   public IEOperation createConversionOperation(OperationType var1, IEGeneric var2, int var3) {
      return this.UT.createConversionOperation(var1, var2, var3);
   }

   @Override
   public IECompose createCompose(IEGeneric... var1) {
      return this.UT.createCompose(var1);
   }

   @Override
   public IECompose createCompose(Collection var1) {
      return this.UT.createCompose(var1);
   }

   @Override
   public IECond createCond(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      return this.UT.createCond(var1, var2, var3);
   }

   @Override
   public EState buildEmptyState() {
      EState var1 = this.UT.buildEmptyState();
      var1.setRoutineContext(this);
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("IR-ctx{%s}", this.wS == null ? "not bound" : this.wS.getName());
   }

   @Override
   public void log(String var1, Object... var2) {
      String var3 = Strings.f(var1, var2);
      fI.iH("[INFO] %s", var3);
   }

   @Override
   public void logUnsafeOpt(String var1, Object... var2) {
      String var3 = Strings.f(var1, var2);
      fI.iHH("[UNSAFE OPTIMIZATION] %s", var3);
   }

   @Override
   public void setData(Object var1, Object var2) {
      if (this.hK == null) {
         this.hK = new HashMap();
      }

      this.hK.put(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.hK == null ? null : this.hK.get(var1);
   }

   @Override
   public boolean addNote(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         Object var3 = this.getData("__$internal$_NOTES");
         Object var2;
         if (!(var3 instanceof List)) {
            var2 = new ArrayList();
            this.setData("__$internal$_NOTES", var2);
         } else {
            var2 = (List)var3;
         }

         if (var2.contains(var1)) {
            return true;
         } else {
            var2.add(var1);
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean removeNote(String var1) {
      return !(this.getData("__$internal$_NOTES") instanceof List var3) ? false : var3.remove(var1);
   }

   @Override
   public List getNotes() {
      return !(this.getData("__$internal$_NOTES") instanceof List var2) ? Collections.emptyList() : Collections.unmodifiableList(var2);
   }

   @Override
   public void setTypeForSame(IEGeneric var1, IWildcardType var2) {
      if (!(var1 instanceof IEVar) && !(var1 instanceof IEImm)) {
         for (IEStatement var4 : this.getCfg().instructions()) {
            var4.visitDepthPost(new akj(this, var1, var2));
         }
      } else {
         var1.setType(var2);
      }
   }

   public void pC(TimedOperationVerifier var1) {
      this.oT = var1;
   }

   public IEVar pC(String var1) {
      String var2;
      switch (var1) {
         case "strcpy":
            var2 = "char*(char*, char*)";
            break;
         case "memcpy":
            var2 = "void*(void*, void*, unsigned int)";
            break;
         case "memset":
            var2 = "void*(void*, int, unsigned int)";
            break;
         default:
            return null;
      }

      INativeMethodItem var3 = this.getNativeContext().getRoutineByName("__builtin_" + var1);
      if (var3 == null) {
         IPrototypeItem var5 = TypeUtil.buildQuickPrototype(this.getNativeContext().getTypeManager(), var2);
         var3 = this.getNativeContext().createMethodReference("__builtin_" + var1, var5, null);
      }

      return this.createSymbolForRoutine(var3);
   }

   @Override
   public IECall createBuiltinMethodCall(String var1, IEGeneric var2, IEGeneric... var3) {
      IEVar var4 = this.pC(var1);
      return this.createCall(var4, null, var2 == null ? null : new ArrayList(Arrays.asList(var2)), new ArrayList(Arrays.asList(var3)), 0, new ArrayList(), null);
   }
}
