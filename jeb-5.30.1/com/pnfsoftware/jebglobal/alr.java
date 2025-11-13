package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeObjectTracker;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class alr implements IEGlobalContext {
   private static final long JY = 1048576L;
   private static final long HF = -1048576L;
   private static final int LK = 8388608;
   @SerId(1)
   AbstractConverter q;
   @SerId(2)
   List RF = new ArrayList();
   @SerId(3)
   IWildcardTypeManager xK;
   @SerId(4)
   INativeObjectTracker Dw;
   @SerId(5)
   int Uv;
   @SerId(6)
   int oW;
   @SerId(7)
   boolean gO;
   @SerId(8)
   AddressSegmentMap nf = new AddressSegmentMap(64);
   @SerId(9)
   List gP = new ArrayList();
   @SerId(10)
   ana za = new ana(0, true);
   @SerId(11)
   FunctionOptypeFactory lm;
   @SerId(12)
   Map zz = new HashMap();
   @SerTransient
   private Map io;

   public alr(AbstractConverter var1, int var2, int var3, boolean var4) {
      this.q = var1;
      this.Uv = var2;
      this.oW = var3;
      this.gO = var4;
   }

   public alr(int var1, boolean var2) {
      this(null, var1, var1, var2);
   }

   @Override
   public IWildcardPrototype getCandidatePrototype(INativeMethodItem var1) {
      return this.getObjectTracker().getCandidatePrototype(var1);
   }

   @Override
   public boolean setCandidatePrototype(INativeMethodItem var1, IWildcardPrototype var2, int var3) {
      return this.getObjectTracker().setCandidatePrototype(var1, var2, var3);
   }

   @Override
   public IERoutineContext createRoutineContext() {
      return new aml(this);
   }

   @Override
   public IERoutineContext createRoutineContext(INativeMethodItem var1) {
      return new aml(this, var1);
   }

   @Override
   public List getRoutineContexts() {
      return this.RF;
   }

   @Override
   public IERoutineContext getRoutineContext(int var1) {
      return (IERoutineContext)this.RF.get(var1);
   }

   @Override
   public void addRoutineContext(IERoutineContext var1) {
      this.RF.add((aml)var1);
   }

   @Override
   public boolean removeRoutineContext(IERoutineContext var1, boolean var2) {
      aml var3 = (aml)var1;
      if (var2) {
         this.Dw.releaseAllFromUser(var1.getRoutine());
      }

      var3.q();
      return this.RF.remove(var1);
   }

   @Override
   public int getRegisterBitsize() {
      return this.Uv;
   }

   @Override
   public int getAddressBitsize() {
      return this.oW;
   }

   @Override
   public boolean isBigEndian() {
      return this.gO;
   }

   @Override
   public AbstractConverter getConverter() {
      return this.q;
   }

   @Override
   public INativeContext getNativeContext() {
      return this.q.getNativeContext();
   }

   @Override
   public IVirtualMemory getNativeMemory() {
      return this.q.getNativeContext().getMemory();
   }

   public void q(IWildcardTypeManager var1) {
      if (this.xK != null && this.xK != var1) {
         throw new IllegalStateException("The wildcard type manager was already set");
      } else {
         this.xK = var1;
      }
   }

   @Override
   public IWildcardTypeManager getWildcardTypeManager() {
      return this.xK;
   }

   public void q(INativeObjectTracker var1) {
      this.Dw = var1;
   }

   @Override
   public INativeObjectTracker getObjectTracker() {
      return this.Dw;
   }

   public EBranchDetails q(List var1, List var2, List var3, int var4) {
      return this.q(var1, var2, var3, var4, null, null);
   }

   public EBranchDetails q(List var1, List var2, List var3, int var4, IPrototypeItem var5, List var6) {
      return new EBranchDetails(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public IECompose createCompose(IEGeneric... var1) {
      return new alo(var1);
   }

   @Override
   public IECompose createCompose(Collection var1) {
      return new alo(var1);
   }

   @Override
   public IECond createCond(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      return new alp(var1, var2, var3);
   }

   @Override
   public IEGroupElt createGroupElt(IEGroup var1, IEGeneric var2) {
      return new alt(var1, var2);
   }

   @Override
   public IEImm createImm(long var1, int var3) {
      return alu.q(var1, var3);
   }

   @Override
   public IEImm createImm(byte[] var1, int var2) {
      return alu.q(var1, var2);
   }

   @Override
   public IEImm createImm(BigInteger var1, int var2) {
      return alu.q(var1, var2);
   }

   @Override
   public IEImm createImm(String var1, int var2) {
      return alu.q(var1, var2);
   }

   @Override
   public IEImm createImm(float var1) {
      return alu.q(var1);
   }

   @Override
   public IEImm createImm(double var1) {
      return alu.q(var1);
   }

   @Override
   public IEMem createMem(IEGeneric var1, int var2) {
      return new amc(var1, var2);
   }

   public amc q(IEGeneric var1, IEGeneric var2, int var3) {
      return new amc(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2) {
      return new amf(var1, var2);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2, IEGeneric var3) {
      return new amf(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(FunctionOptype var1, IEGeneric... var2) {
      return new amf(var1, var2);
   }

   @Override
   public FunctionOptype createFunctionType(String var1, int var2, int var3, int var4, int var5) {
      return this.q().create(var1, var2, var3, var4, var5);
   }

   @Override
   public FunctionOptype getFunctionType(String var1) {
      return this.q().get(var1);
   }

   private FunctionOptypeFactory q() {
      if (this.lm == null) {
         synchronized (this) {
            if (this.lm == null) {
               FunctionOptypeFactory var2 = new FunctionOptypeFactory();
               this.lm = var2;
            }
         }
      }

      return this.lm;
   }

   @Override
   public IEOperation createResizeOperation(IEGeneric var1, int var2, boolean var3) {
      return amf.q(var1, var2, var3);
   }

   @Override
   public IEOperation createConversionOperation(OperationType var1, IEGeneric var2, int var3) {
      return amf.q(var1, var2, var3);
   }

   @Override
   public IERange createRange(int var1, int var2) {
      return new amj(var1, var2);
   }

   @Override
   public IESlice createSlice(IEGeneric var1, int var2, int var3) {
      return new amn(var1, var2, var3);
   }

   @Override
   public IESlice createSlice(IEGeneric var1, IERange var2) {
      return new amn(var1, var2.getBegin(), var2.getEnd());
   }

   @Override
   public IEVar getVariableByName(String var1) {
      return this.za.q(var1);
   }

   @Override
   public IEVar createRegister(int var1, String var2, int var3) {
      if (var1 >= 0 && var1 < 65536) {
         return this.za.q(var1, var2, var3, null, false);
      } else {
         throw new RuntimeException("Illegal register variable id: " + var1 + " out of bounds [0,65536]");
      }
   }

   @Override
   public IEVar createRegister(String var1, int var2) {
      return this.za.q(0, 65536, var1, var2, null);
   }

   @Override
   public IEVar createVirtualRegister(int var1, String var2, int var3) {
      if (var1 >= 65536 && var1 < 131072) {
         return this.za.q(var1, var2, var3, null, false);
      } else {
         throw new RuntimeException("Illegal synthetic register id: " + var1);
      }
   }

   @Override
   public IEVar createVirtualRegister(String var1, int var2) {
      return this.za.q(65536, 131072, var1, var2, null);
   }

   private alr.eo q(long var1, boolean var3) {
      long var4 = var1 & -1048576L;
      alr.eo var6 = (alr.eo)this.nf.get(var4);
      if (var6 == null && var3) {
         int var7 = 16777216 + this.gP.size() * 8388608;
         var6 = new alr.eo(var4, var7);
         this.nf.put(var4, var6);
         this.gP.add(var6);
      }

      return var6;
   }

   public IEVar q(int var1) {
      IEVar var2 = this.za.q(var1);
      if (var2 != null && var2.isGlobalReference() && var2.getAddress() != null) {
         this.zz.remove(var2.getAddress());
      }

      return var2;
   }

   @Override
   public IEVar createGlobalReference(String var1, Long var2) {
      if (var2 != null) {
         Integer var4 = (Integer)this.zz.get(var2);
         if (var4 != null) {
            IEVar var3 = this.za.RF(var4);
            if (var3 != null) {
               return var3;
            }
         }
      }

      if (var1 == null) {
         Assert.a(var2 != null);
         var1 = Strings.ff("ptr_g%X", var2);
      }

      IEVar var5 = this.za.q(131072, 196608, var1, this.oW, var2);
      Assert.a(var5 != null);
      if (var2 != null) {
         this.zz.put(var2, var5.getId());
      }

      return var5;
   }

   public int q(long var1) {
      alr.eo var3 = this.q(var1, true);
      return var3.xK() + (int)(var1 - var3.q()) * 8;
   }

   @Override
   public IEVar createGlobalVariable(long var1, int var3) {
      alr.eo var4 = this.q(var1, true);
      int var5 = var4.xK() + (int)(var1 - var4.q()) * 8;
      if (var5 + var3 > var4.Dw()) {
         throw new RuntimeException();
      } else {
         String var6 = Strings.ff("g%X", var1);
         return this.za.q(var5, var6, var3, var1, false);
      }
   }

   @Override
   public IEVar getGlobalVariable(long var1) {
      alr.eo var3 = this.q(var1, false);
      if (var3 == null) {
         return null;
      } else {
         int var4 = var3.xK() + (int)(var1 - var3.q()) * 8;
         return this.za.RF(var4);
      }
   }

   @Override
   public Collection getGlobalVariables() {
      return this.za.q(16777216, Integer.MAX_VALUE);
   }

   @Override
   public IEVar createSymbolForRoutine(INativeMethodItem var1, IERoutineContext var2) {
      return this.Dw.getSymbolForNativeItem(var1, var2.getRoutine(), var2);
   }

   @Override
   public INativeMethodItem retrieveRoutineFromSymbol(IEVar var1) {
      return (INativeMethodItem)this.Dw.getNativeItemFromVar(var1);
   }

   @Override
   public IEVar createSymbolForData(INativeDataItem var1, IERoutineContext var2) {
      return this.Dw.getSymbolForNativeItem(var1, var2.getRoutine(), var2);
   }

   @Override
   public INativeDataItem retrieveDataFromSymbol(IEVar var1) {
      return (INativeDataItem)this.Dw.getNativeItemFromVar(var1);
   }

   @Override
   public IEVar getVarSafe(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         return this.za.RF(var1);
      }
   }

   @Override
   public IEVar getVar(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("A routine-scope variable cannot be retrieved from the global decompilation context; id= " + var1);
      } else {
         IEVar var2 = this.za.RF(var1);
         if (var2 == null) {
            throw new RuntimeException("The global variable does not exist: " + var1);
         } else {
            return var2;
         }
      }
   }

   @Override
   public boolean canCreateVariable(int var1, int var2) {
      return this.za.Dw(var1, var2);
   }

   @Override
   public Collection getVariables(int var1, int var2) {
      return this.za.q(var1, var2);
   }

   @Override
   public Collection getAllVariables() {
      return this.za.xK();
   }

   @Override
   public Collection getAllRegisters() {
      return this.za.q(0, 65536);
   }

   @Override
   public Collection getAllRegisters(Set var1) {
      ArrayList var2 = new ArrayList();

      for (IEVar var4 : this.za.q(0, 65536)) {
         if (var1 == null || !var1.contains(var4.getId())) {
            var2.add(var4);
         }
      }

      return var2;
   }

   @Override
   public EState buildState(boolean var1, boolean var2, boolean var3) {
      EState var4 = new EState(this);
      if (var1) {
         for (IEVar var6 : this.za.q(0, 131072)) {
            var4.setValue(var6, 0L);
         }
      }

      if (var2) {
         for (IEVar var9 : this.za.q(131072, 196608)) {
            var4.setValue(var9, 0L);
         }
      }

      if (var3) {
         for (IEVar var10 : this.za.q(16777216, Integer.MAX_VALUE)) {
            var4.setValue(var10, 0L);
         }
      }

      return var4;
   }

   @Override
   public void setData(Object var1, Object var2) {
      if (this.io == null) {
         this.io = new HashMap();
      }

      this.io.put(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.io == null ? null : this.io.get(var1);
   }

   @Ser
   public static class eo implements ISegment {
      @SerId(1)
      long q;
      @SerId(2)
      int RF;

      public eo(long var1, int var3) {
         this.q = var1;
         this.RF = var3;
      }

      public Long q() {
         return this.q;
      }

      public Long RF() {
         return this.q + 1048576L;
      }

      public int xK() {
         return this.RF;
      }

      public int Dw() {
         return this.RF + 8388608;
      }
   }
}
