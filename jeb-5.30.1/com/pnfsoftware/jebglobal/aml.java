package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableInformationProvider;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableProvider;
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
public class aml implements IVariableInformationProvider, IERoutineContext, INativeItemListener {
   private static final StructuredLogger zz = aeg.q(aml.class);
   @Deprecated
   public static Boolean q;
   @SerId(1)
   int RF;
   @SerId(2)
   int xK;
   @SerId(3)
   INativeMethodItem Dw;
   @SerId(4)
   alr Uv;
   @SerId(5)
   private List JY;
   @SerId(value = 6, deprecated = true)
   private String HF;
   @SerId(value = 7, deprecated = true)
   private LinkedHashMap LK = new LinkedHashMap();
   @SerId(8)
   private Map io = new HashMap();
   @SerId(9)
   private Map qa = new HashMap();
   @SerId(10)
   ana oW;
   @SerId(11)
   private amo Hk;
   @SerId(12)
   private Map Me = new HashMap();
   @SerId(13)
   private SPDC PV = SPDC.Unknown;
   @SerId(14)
   private SPDC oQ = SPDC.Unknown;
   @SerId(15)
   private amz xW;
   @SerId(16)
   boolean gO;
   @SerId(17)
   boolean nf;
   @SerId(18)
   int gP;
   @SerId(19)
   Boolean za;
   @SerId(20)
   private CFG KT;
   @SerTransient
   TimedOperationVerifier lm;
   @SerTransient
   private List Gf;
   @SerTransient
   private Map Ef;
   private static final String cC = "__$internal$_NOTES";

   @SerCustomInitPostGraph
   private void zz() {
      if (this.LK != null) {
         this.KT = (CFG)this.LK.get(this.HF);
      }
   }

   public aml(alr var1) {
      this(var1, null);
   }

   aml(alr var1, INativeMethodItem var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("A global context must be provided");
      } else {
         this.Uv = var1;
         this.Dw = var2;
         this.RF = Integer.MAX_VALUE;
         this.xK = Integer.MAX_VALUE;
         this.oW = new ana(1, false);
         this.xW = new amz(this.oW, var1.za, this.RF);
         this.xW.q(this);
      }
   }

   public aml(alr var1, int var2, int var3) {
      this(var1);
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public String getTargetName() {
      return this.Dw == null ? null : this.Dw.getSignature();
   }

   public void q() {
      if (this.gO) {
         this.Dw.getData().removeListener(this);
         this.gO = false;
      }
   }

   public boolean RF() {
      if (this.Dw.getData() == null) {
         return false;
      } else {
         if (!this.gO) {
            this.Dw.getData().addListener(this);
            this.gO = true;
         }

         return true;
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() == this.Dw.getData()) {
         var1.getType().isDeepChange();
      }
   }

   @Override
   public void setRoutine(INativeMethodItem var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (this.Dw != null && this.Dw != var1) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
         Assert.a(var1.isInternal(), "Expected an internal routine");
      }
   }

   @Override
   public INativeMethodItem getRoutine() {
      return this.Dw;
   }

   public long xK() {
      return this.Dw.getMemoryAddress();
   }

   @Override
   public IWildcardPrototype getPrototype() {
      return this.Uv.getCandidatePrototype(this.Dw);
   }

   @Override
   public void setPrototype(IWildcardPrototype var1) {
      this.Uv.setCandidatePrototype(this.Dw, var1, 1);
   }

   public synchronized amo Dw() {
      if (this.Hk == null) {
         this.Hk = new amo(this);
      }

      return this.Hk;
   }

   @Override
   public INativeContext getNativeContext() {
      return this.Uv.getConverter().getNativeContext();
   }

   @Override
   public INativeDecompilerContext getDecompiler() {
      return this.Uv.getConverter().getDecompiler();
   }

   public AbstractConverter Uv() {
      return this.Uv.getConverter();
   }

   @Override
   public IEGlobalContext getGlobalContext() {
      return this.Uv;
   }

   @Override
   public IWildcardTypeManager getWildcardTypeManager() {
      return this.Uv.getWildcardTypeManager();
   }

   @Override
   public IEVar getProgramCounter() {
      return this.Uv.getConverter().getProgramCounter();
   }

   @Override
   public int getProgramCounterId() {
      return this.RF != Integer.MAX_VALUE ? this.RF : this.Uv.getConverter().getProgramCounter().getId();
   }

   @Override
   public IEVar getStackPointer() {
      return this.Uv.getConverter().getStackPointer();
   }

   @Override
   public int getStackPointerId() {
      return this.xK != Integer.MAX_VALUE ? this.xK : this.Uv.getConverter().getStackPointer().getId();
   }

   public SPDC q(SPDC var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var2 && var1.compareTo(this.PV) <= 0) {
         return null;
      } else {
         SPDC var3 = this.PV;
         this.PV = var1;
         return var3;
      }
   }

   public SPDC oW() {
      return this.PV;
   }

   public SPDC RF(SPDC var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var2 && var1.compareTo(this.oQ) <= 0) {
         return null;
      } else {
         SPDC var3 = this.oQ;
         this.oQ = var1;
         return var3;
      }
   }

   public SPDC gO() {
      return this.oQ;
   }

   @Override
   public boolean isAllowUnsafeAnalysis() {
      return this.za == null ? true : this.za;
   }

   public void q(Boolean var1) {
      this.za = var1;
   }

   @Override
   public Long convertNativeAddress(long var1) {
      return this.q(var1, false);
   }

   Long q(long var1, boolean var3) {
      if (this.KT == null) {
         if (this.io == null) {
            throw new IllegalStateException();
         }

         Integer var4 = (Integer)this.io.get(var1);
         if (var4 != null) {
            return var4.intValue() & 4294967295L;
         }
      } else if (this.Gf != null) {
         int var7 = 0;

         for (IEStatement var6 : this.Gf) {
            if (var6.getLowerLevelAddresses().contains(var1)) {
               return var7 & 4294967295L;
            }

            var7 += var6.getSize();
         }
      } else {
         for (AddressableInstruction var9 : this.KT.addressableInstructions()) {
            if (((IEStatement)var9.getInstruction()).getLowerLevelAddresses().contains(var1)) {
               return var9.getOffset() & 4294967295L;
            }
         }
      }

      if (var3) {
         zz.debug("Cannot convert native address %Xh to IR offset", var1);
      }

      return null;
   }

   @Override
   public Long convertIntermediateOffset(int var1) {
      return this.q(var1, false);
   }

   Long q(int var1, boolean var2) {
      if (this.KT == null) {
         if (this.qa == null) {
            throw new IllegalStateException();
         } else {
            return (Long)this.qa.get(var1);
         }
      } else {
         if (this.Gf != null) {
            int var3 = 0;

            for (IEStatement var5 : this.Gf) {
               if (var3 == var1) {
                  return var5.getPrimaryLowerLevelAddress();
               }

               if (var3 > var1) {
                  break;
               }

               var3 += var5.getSize();
            }
         } else {
            IEStatement var6 = (IEStatement)this.KT.getInstruction(var1);
            if (var6 != null) {
               return var6.getPrimaryLowerLevelAddress();
            }
         }

         if (var2) {
            zz.debug("Cannot convert IR offset %Xh to native address", var1);
         }

         return null;
      }
   }

   @Override
   public List getIntermediateOffsetsMappingToNativeAddress(long var1) {
      if (this.KT == null) {
         throw new IllegalStateException();
      } else {
         ArrayList var3 = new ArrayList();

         for (AddressableInstruction var5 : this.KT.addressableInstructions()) {
            if (((IEStatement)var5.getInstruction()).getLowerLevelAddresses().contains(var1)) {
               var3.add(var5.getOffset() & 4294967295L);
            }
         }

         return var3;
      }
   }

   @Override
   public List getIntermediateOffsetsMappingToNativeAddresses(Collection var1) {
      if (this.KT == null) {
         throw new IllegalStateException();
      } else {
         ArrayList var2 = new ArrayList();

         for (AddressableInstruction var4 : this.KT.addressableInstructions()) {
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
      if (this.JY != null && this.JY != var1) {
         throw new IllegalStateException("The original IR-statements list was already set");
      } else {
         if (var2) {
            aee.q(var1);
         }

         if (var3 && (q == null || !q)) {
            aee.xK(var1);
         }

         this.JY = var1;
         AddressConversionLists var5 = AddressConversionLists.generateFromList(var1);
         this.io = var5.getNativeToInter();
         this.qa = var5.getInterToNative();
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
      this.Gf = var1;

      CFG var7;
      try {
         byte var4 = 2;
         if (var2) {
            var4 |= 1;
         }

         CFG var5 = new CFG(var1, null, null, 0L, 0L, var4);
         var5.setTimedOperationVerifier(this.lm);
         aqq.q(var5);
         int var6 = var1.size() - var5.getInstructionCount();
         if (var6 != 0) {
            zz.iH(
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
         this.Gf = null;
      }

      return var7;
   }

   @Override
   public void invalidateDataFlowAnalysis() {
      if (this.KT != null) {
         this.KT.invalidateDataFlowAnalysis();
      }
   }

   @Override
   public List getStatements() {
      return this.JY;
   }

   @Override
   public CFG getCfg() {
      return this.KT;
   }

   @Override
   public void setCfg(CFG var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.KT = var1;
         var1.setVariableInformationProvider(this);
         this.qa = null;
         this.io = null;
      }
   }

   public boolean nf() {
      return this.nf;
   }

   public void q(boolean var1) {
      if (var1) {
         throw new RuntimeException("Bit-level data flow analysis should not be activated yet");
      } else {
         this.nf = var1;
      }
   }

   @Override
   public IEImm createImm(long var1, int var3) {
      return this.Uv.createImm(var1, var3);
   }

   @Override
   public IEImm createImm(byte[] var1, int var2) {
      return this.Uv.createImm(var1, var2);
   }

   @Override
   public IEImm createImm(BigInteger var1, int var2) {
      return this.Uv.createImm(var1, var2);
   }

   @Override
   public IEImm createImm(String var1, int var2) {
      return this.Uv.createImm(var1, var2);
   }

   @Override
   public IEImm createImm(float var1) {
      return this.Uv.createImm(var1);
   }

   @Override
   public IEImm createImm(double var1) {
      return this.Uv.createImm(var1);
   }

   @Override
   public Set getSame(int var1) {
      Set var2 = this.xW.oW(var1);
      VarSrc var3 = this.xW.q(var1);
      if (var3 != null) {
         var2.remove(var1);
      }

      return var2;
   }

   @Override
   public String getName(int var1) {
      IEVar var2 = this.RF(var1, false);
      return var2 != null ? var2.getName() : (var1 >= 0 ? Integer.toHexString(var1) : "-" + Integer.toHexString(-var1));
   }

   @Override
   public String getSliceName(int var1, int var2, int var3) {
      IEVar var4 = this.getVariableById(var1);
      return var4 != null ? this.Uv().getSlicedRegisterName(var4.getName(), var2, var3) : null;
   }

   @Override
   public Integer getUnderlyingRegisterId(int var1) {
      if (var1 >= 0 && var1 < 131072) {
         return var1;
      } else {
         VarSrc var2 = this.xW.q(var1);
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
         int var2 = amz.RF(var1.getId());
         IEVar var3 = this.oW.RF(var2);
         return var3 != null ? var3 : this.xW.RF(var1);
      }
   }

   @Override
   public IEVar copyVariable(IEVar var1) {
      return this.xW.RF(var1);
   }

   @Override
   public Couple copyTruncatedVariable(IEVar var1, int var2) {
      return this.xW.q(var1, var2);
   }

   @Override
   public IEVar copyPairOfVariables(IEVar var1, IEVar var2) {
      return this.xW.q(var1, var2);
   }

   @Override
   public EVarCopyFinder copyFinder(StorageEntry var1, Collection var2, Long var3) {
      return new EVarCopyFinder(this, var1, var2, var3);
   }

   @Override
   public VarSrc getSourceForVariable(int var1) {
      return this.xW.q(var1);
   }

   @Override
   public Set getCopiesOfVariable(int var1) {
      return this.xW.Dw(var1);
   }

   @Override
   public Set getCopiesUsingVariable(int var1) {
      return this.xW.Uv(var1);
   }

   @Override
   public boolean usesCopyVars() {
      return this.xW.RF() > 0;
   }

   @Override
   public List getDuplicatesForRegister(int var1) {
      return this.xW.nf(var1);
   }

   public Collection gP() {
      return this.oW.q(0, Integer.MAX_VALUE);
   }

   public amy q(String var1, int var2) {
      IEVar var3 = this.oW.q(2, 65536, var1, var2, null);
      Assert.a(var3 != null);
      return (amy)var3;
   }

   @Override
   public IEVar getVariableByName(String var1) {
      IEVar var2 = this.oW.q(var1);
      if (var2 == null) {
         var2 = this.Uv.getVariableByName(var1);
      }

      return var2;
   }

   @Override
   public IEVar createVirtualVar(String var1, int var2) {
      IEVar var3 = this.oW.q(16777216, 33554432, var1, var2, null);
      Assert.a(var3 != null);
      return var3;
   }

   @Override
   public IEVar getStackReference(long var1) {
      return (IEVar)this.Me.get(var1);
   }

   @Override
   public IEVar removeStackReference(long var1) {
      IEVar var3 = (IEVar)this.Me.remove(var1);
      if (var3 == null) {
         return null;
      } else {
         IEVar var4 = this.oW.q(-var3.getId());
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
      IEVar var4 = (IEVar)this.Me.get(var1);
      if (var4 != null) {
         return var4;
      } else {
         String var5 = "ptr_" + (var1 >= 0L ? Strings.ff("par%02X", var1) : Strings.ff("var%02X", -var1));
         int var6 = this.Uv.getAddressBitsize();
         var4 = this.oW.q(8388608, 8454144, var5, var6, null);
         ((amy)var4).q(var1);
         this.Me.put(var1, var4);
         if (var3 == null) {
            var3 = this.getWildcardTypeManager().createPointer(0);
         }

         var4.setType(var3);
         return var4;
      }
   }

   public int q(long var1) {
      int var3 = 1073741824 + (int)(var1 * 8L);
      return -var3;
   }

   public IEVar q(int var1) {
      return this.RF(var1, true);
   }

   public IEVar RF(int var1, boolean var2) {
      IEVar var3;
      if (var1 < 0) {
         var3 = this.oW.RF(-var1);
      } else {
         var3 = this.Uv.getVarSafe(var1);
      }

      if (var3 == null && var2) {
         throw new RuntimeException("The variable does not exist: id=" + var1);
      } else {
         return (amy)var3;
      }
   }

   @Override
   public IEVar getVariableById(int var1) {
      return this.RF(var1, false);
   }

   public IEVar RF(int var1) {
      IEVar var2;
      if (var1 < 0) {
         var2 = this.oW.xK(-var1);
      } else {
         var2 = this.Uv.za.xK(var1);
      }

      if (var2 == null) {
         throw new RuntimeException("The variable does not exist: vbit=" + var1);
      } else {
         return (amy)var2;
      }
   }

   @Override
   public Collection getRoutineVariablesInRange(int var1, int var2) {
      if (var1 < 0) {
         var1 = -var1;
         var2 = -var2;
      }

      return (Collection)(var2 < var1 ? Collections.emptyList() : Collections.unmodifiableCollection(this.oW.q(var1, var2)));
   }

   public IEVar q(String var1) {
      if (var1 == null) {
         return null;
      } else {
         for (IEVar var3 : this.oW.xK()) {
            if (var1.equals(var3.getName())) {
               return var3;
            }
         }

         return null;
      }
   }

   @Override
   public Collection getStackVariables() {
      return this.oW.q(33554432, Integer.MAX_VALUE);
   }

   @Override
   public IEVar getStackVariable(int var1) {
      int var2 = 1073741824 + var1 * 8;
      return var2 >= 33554432 && var2 < Integer.MAX_VALUE ? this.oW.RF(var2) : null;
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

         return this.oW.q(var3, var4);
      }
   }

   @Override
   public Collection getMemoryVariables() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.oW.q(33554432, Integer.MAX_VALUE));
      var1.addAll(this.Uv.getGlobalVariables());
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
      IEGeneric var4 = this.Uv().getRegisterVariableFromNativeRegisterId(var2);
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
      IEGeneric var5 = this.Uv().getRegisterVariableFromNativeRegisterId(var4);
      IBasicBlock var6 = var1.getCfg().getBlockByLastAddress(var2);
      Collection var7 = var1.getOutputs(var6);
      return this.retrieveVariableForRegister(var5, var7, false);
   }

   @Override
   public List getRoutineInputVariables() {
      IEPrototypeHandler var1 = this.Uv().getPrototypeHandler(this);
      ArrayList var2 = new ArrayList();
      return !var1.retrieveFromPrototype(var2, null) ? null : var2;
   }

   @Override
   public IEVar createSymbolForRoutine(INativeMethodItem var1) {
      return this.Uv.createSymbolForRoutine(var1, this);
   }

   @Override
   public IEVar createSymbolForField(INativeFieldItem var1) {
      throw new RuntimeException();
   }

   @Override
   public IEVar createSymbolForGlobalVariable(INativeDataItem var1) {
      return this.Uv.createSymbolForData(var1, this);
   }

   @Override
   public void acquireNativeItem(INativeItem var1) {
      this.Uv.getObjectTracker().acquire(var1, this.Dw);
   }

   @Override
   public IEAssign createAssign(IEGeneric var1, IEGeneric var2) {
      return new alj(this, var1, var2);
   }

   @Override
   public IEAssign createBranchAssign(IEGeneric var1, IEGeneric var2, boolean var3) {
      return new alj(this, var1, var2, var3 ? 2 : 1);
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

      return this.q(var1.getDstOperand(), var1.getSrcOperand(), var2, var3);
   }

   private IEAssign q(IEGeneric var1, IEGeneric var2, IEGeneric var3, int var4) {
      return new alj(this, var1, this.createCond(var3, var2, var1), var4);
   }

   @Override
   public IEGroupElt createGroupElt(IEGroup var1, IEGeneric var2) {
      return this.Uv.createGroupElt(var1, var2);
   }

   @Override
   public IESwitch createSwitch(IEGeneric var1, int var2) {
      return new amr(this, var1, var2);
   }

   @Override
   public IEJump createJump(int var1) {
      return new ama(this, var1);
   }

   @Override
   public IEJump createJump(int var1, IEGeneric var2) {
      return new ama(this, var1, var2);
   }

   @Override
   public IEJumpFar createJumpFar(IEGeneric var1) {
      return new amb(this, var1);
   }

   @Override
   public IEJumpFar createJumpFar(IEGeneric var1, IEGeneric var2) {
      return new amb(this, var1, var2);
   }

   @Override
   public IENop createNop() {
      return new ame(this);
   }

   @Override
   public IENop createNop(IEStatement var1) {
      return new ame(this, var1);
   }

   @Override
   public IEReturn createReturn() {
      return new amk(this);
   }

   @Override
   public IEReturn createReturn(IEGeneric var1) {
      return new amk(this, var1);
   }

   @Override
   public IEReturn createReturn(List var1) {
      return new amk(this, var1);
   }

   @Override
   public IECall createCall(IEGeneric var1, IEGeneric var2, List var3, List var4, int var5, List var6, IWildcardPrototype var7) {
      return new aln(this, var1, null, var7, var2, var3, var4, var5, var6);
   }

   @Override
   public IECall createCall(IEGeneric var1, List var2, IWildcardPrototype var3, List var4, boolean var5) {
      return new aln(this, var1, var2, var3, var4, var5);
   }

   @Override
   public IEUntranslatedInstruction createUntranslatedInstruction(long var1, String var3, IEGeneric... var4) {
      return new amv(this, var1, var3, var4);
   }

   @Override
   public IEMem createMem(IEGeneric var1, int var2) {
      return this.Uv.createMem(var1, var2);
   }

   @Override
   public IEMem createMem(IEGeneric var1, IEGeneric var2, int var3) {
      return this.Uv.q(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2) {
      return this.Uv.createOperation(var1, var2);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2, IEGeneric var3) {
      return this.Uv.createOperation(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(FunctionOptype var1, IEGeneric... var2) {
      return this.Uv.createOperation(var1, var2);
   }

   @Override
   public IEOperation createResizeOperation(IEGeneric var1, int var2, boolean var3) {
      return this.Uv.createResizeOperation(var1, var2, var3);
   }

   @Override
   public IEOperation createConversionOperation(OperationType var1, IEGeneric var2, int var3) {
      return this.Uv.createConversionOperation(var1, var2, var3);
   }

   @Override
   public IECompose createCompose(IEGeneric... var1) {
      return this.Uv.createCompose(var1);
   }

   @Override
   public IECompose createCompose(Collection var1) {
      return this.Uv.createCompose(var1);
   }

   @Override
   public IECond createCond(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      return this.Uv.createCond(var1, var2, var3);
   }

   @Override
   public EState buildEmptyState() {
      EState var1 = this.Uv.buildEmptyState();
      var1.setRoutineContext(this);
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("IR-ctx{%s}", this.Dw == null ? "not bound" : this.Dw.getName());
   }

   @Override
   public void log(String var1, Object... var2) {
      String var3 = Strings.f(var1, var2);
      zz.iH("[INFO] %s", var3);
   }

   @Override
   public void logUnsafeOpt(String var1, Object... var2) {
      String var3 = Strings.f(var1, var2);
      zz.iHH("[UNSAFE OPTIMIZATION] %s", var3);
   }

   public IVariableProvider za() {
      return new anc(this);
   }

   @Override
   public void setData(Object var1, Object var2) {
      if (this.Ef == null) {
         this.Ef = new HashMap();
      }

      this.Ef.put(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.Ef == null ? null : this.Ef.get(var1);
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
            var4.visitDepthPost(new amm(this, var1, var2));
         }
      } else {
         var1.setType(var2);
      }
   }

   public void q(TimedOperationVerifier var1) {
      this.lm = var1;
   }

   public TimedOperationVerifier lm() {
      return this.lm;
   }

   public IEVar RF(String var1) {
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
      IEVar var4 = this.RF(var1);
      return this.createCall(var4, null, var2 == null ? null : new ArrayList(Arrays.asList(var2)), new ArrayList(Arrays.asList(var3)), 0, new ArrayList(), null);
   }
}
