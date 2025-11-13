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
public class ajo implements IEGlobalContext {
   @SerId(1)
   AbstractConverter pC;
   @SerId(2)
   List A = new ArrayList();
   @SerId(3)
   IWildcardTypeManager kS;
   @SerId(4)
   INativeObjectTracker wS;
   @SerId(5)
   int UT;
   @SerId(6)
   int E;
   @SerId(7)
   boolean sY;
   @SerId(8)
   AddressSegmentMap ys = new AddressSegmentMap(64);
   @SerId(9)
   List ld = new ArrayList();
   @SerId(10)
   akw gp = new akw(0, true);
   @SerId(11)
   FunctionOptypeFactory oT;
   @SerId(12)
   Map fI = new HashMap();
   @SerTransient
   private Map WR;

   public ajo(AbstractConverter var1, int var2, int var3, boolean var4) {
      this.pC = var1;
      this.UT = var2;
      this.E = var3;
      this.sY = var4;
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
      return new aki(this);
   }

   @Override
   public IERoutineContext createRoutineContext(INativeMethodItem var1) {
      return new aki(this, var1);
   }

   @Override
   public List getRoutineContexts() {
      return this.A;
   }

   @Override
   public IERoutineContext getRoutineContext(int var1) {
      return (IERoutineContext)this.A.get(var1);
   }

   @Override
   public void addRoutineContext(IERoutineContext var1) {
      this.A.add(var1);
   }

   @Override
   public boolean removeRoutineContext(IERoutineContext var1, boolean var2) {
      aki var3 = (aki)var1;
      if (var2) {
         this.wS.releaseAllFromUser(var1.getRoutine());
      }

      var3.pC();
      return this.A.remove(var1);
   }

   @Override
   public int getRegisterBitsize() {
      return this.UT;
   }

   @Override
   public int getAddressBitsize() {
      return this.E;
   }

   @Override
   public boolean isBigEndian() {
      return this.sY;
   }

   @Override
   public AbstractConverter getConverter() {
      return this.pC;
   }

   @Override
   public INativeContext getNativeContext() {
      return this.pC.getNativeContext();
   }

   @Override
   public IVirtualMemory getNativeMemory() {
      return this.pC.getNativeContext().getMemory();
   }

   public void pC(IWildcardTypeManager var1) {
      if (this.kS != null && this.kS != var1) {
         throw new IllegalStateException("The wildcard type manager was already set");
      } else {
         this.kS = var1;
      }
   }

   @Override
   public IWildcardTypeManager getWildcardTypeManager() {
      return this.kS;
   }

   public void pC(INativeObjectTracker var1) {
      this.wS = var1;
   }

   @Override
   public INativeObjectTracker getObjectTracker() {
      return this.wS;
   }

   public EBranchDetails pC(List var1, List var2, List var3, int var4) {
      return this.pC(var1, var2, var3, var4, null, null);
   }

   public EBranchDetails pC(List var1, List var2, List var3, int var4, IPrototypeItem var5, List var6) {
      return new EBranchDetails(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public IECompose createCompose(IEGeneric... var1) {
      return new ajl(var1);
   }

   @Override
   public IECompose createCompose(Collection var1) {
      return new ajl(var1);
   }

   @Override
   public IECond createCond(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      return new ajm(var1, var2, var3);
   }

   @Override
   public IEGroupElt createGroupElt(IEGroup var1, IEGeneric var2) {
      return new ajq(var1, var2);
   }

   @Override
   public IEImm createImm(long var1, int var3) {
      return ajr.pC(var1, var3);
   }

   @Override
   public IEImm createImm(byte[] var1, int var2) {
      return ajr.pC(var1, var2);
   }

   @Override
   public IEImm createImm(BigInteger var1, int var2) {
      return ajr.pC(var1, var2);
   }

   @Override
   public IEImm createImm(String var1, int var2) {
      return ajr.pC(var1, var2);
   }

   @Override
   public IEImm createImm(float var1) {
      return ajr.pC(var1);
   }

   @Override
   public IEImm createImm(double var1) {
      return ajr.pC(var1);
   }

   @Override
   public IEMem createMem(IEGeneric var1, int var2) {
      return new ajz(var1, var2);
   }

   public ajz pC(IEGeneric var1, IEGeneric var2, int var3) {
      return new ajz(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2) {
      return new akc(var1, var2);
   }

   @Override
   public IEOperation createOperation(OperationType var1, IEGeneric var2, IEGeneric var3) {
      return new akc(var1, var2, var3);
   }

   @Override
   public IEOperation createOperation(FunctionOptype var1, IEGeneric... var2) {
      return new akc(var1, var2);
   }

   @Override
   public FunctionOptype createFunctionType(String var1, int var2, int var3, int var4) {
      return this.pC().create(var1, var2, var3, var4);
   }

   @Override
   public FunctionOptype getFunctionType(String var1) {
      return this.pC().get(var1);
   }

   private FunctionOptypeFactory pC() {
      if (this.oT == null) {
         synchronized (this) {
            if (this.oT == null) {
               FunctionOptypeFactory var2 = new FunctionOptypeFactory();
               this.oT = var2;
            }
         }
      }

      return this.oT;
   }

   @Override
   public IEOperation createResizeOperation(IEGeneric var1, int var2, boolean var3) {
      return akc.pC(var1, var2, var3);
   }

   @Override
   public IEOperation createConversionOperation(OperationType var1, IEGeneric var2, int var3) {
      return akc.pC(var1, var2, var3);
   }

   @Override
   public IERange createRange(int var1, int var2) {
      return new akg(var1, var2);
   }

   @Override
   public IESlice createSlice(IEGeneric var1, int var2, int var3) {
      return new akk(var1, var2, var3);
   }

   @Override
   public IESlice createSlice(IEGeneric var1, IERange var2) {
      return new akk(var1, var2.getBegin(), var2.getEnd());
   }

   @Override
   public IEVar getVariableByName(String var1) {
      return this.gp.pC(var1);
   }

   @Override
   public IEVar createRegister(int var1, String var2, int var3) {
      if (var1 >= 0 && var1 < 65536) {
         return this.gp.pC(var1, var2, var3, null, false);
      } else {
         throw new RuntimeException("Illegal register variable id: " + var1 + " out of bounds [0,65536]");
      }
   }

   @Override
   public IEVar createRegister(String var1, int var2) {
      return this.gp.pC(0, 65536, var1, var2, null);
   }

   @Override
   public IEVar createVirtualRegister(int var1, String var2, int var3) {
      if (var1 >= 65536 && var1 < 131072) {
         return this.gp.pC(var1, var2, var3, null, false);
      } else {
         throw new RuntimeException("Illegal synthetic register id: " + var1);
      }
   }

   @Override
   public IEVar createVirtualRegister(String var1, int var2) {
      return this.gp.pC(65536, 131072, var1, var2, null);
   }

   private ajo.Av pC(long var1, boolean var3) {
      long var4 = var1 & -1048576L;
      ajo.Av var6 = (ajo.Av)this.ys.get(var4);
      if (var6 == null && var3) {
         int var7 = 16777216 + this.ld.size() * 8388608;
         var6 = new ajo.Av(var4, var7);
         this.ys.put(var4, var6);
         this.ld.add(var6);
      }

      return var6;
   }

   public IEVar pC(int var1) {
      IEVar var2 = this.gp.pC(var1);
      if (var2 != null && var2.isGlobalReference() && var2.getAddress() != null) {
         this.fI.remove(var2.getAddress());
      }

      return var2;
   }

   @Override
   public IEVar createGlobalReference(String var1, Long var2) {
      if (var2 != null) {
         Integer var4 = (Integer)this.fI.get(var2);
         if (var4 != null) {
            IEVar var3 = this.gp.A(var4);
            if (var3 != null) {
               return var3;
            }
         }
      }

      if (var1 == null) {
         Assert.a(var2 != null);
         var1 = Strings.ff("ptr_g%X", var2);
      }

      IEVar var5 = this.gp.pC(131072, 196608, var1, this.E, var2);
      Assert.a(var5 != null);
      if (var2 != null) {
         this.fI.put(var2, var5.getId());
      }

      return var5;
   }

   public int pC(long var1) {
      ajo.Av var3 = this.pC(var1, true);
      return var3.kS() + (int)(var1 - var3.pC()) * 8;
   }

   @Override
   public IEVar createGlobalVariable(long var1, int var3) {
      ajo.Av var4 = this.pC(var1, true);
      int var5 = var4.kS() + (int)(var1 - var4.pC()) * 8;
      if (var5 + var3 > var4.wS()) {
         throw new RuntimeException();
      } else {
         String var6 = Strings.ff("g%X", var1);
         return this.gp.pC(var5, var6, var3, var1, false);
      }
   }

   @Override
   public IEVar getGlobalVariable(long var1) {
      ajo.Av var3 = this.pC(var1, false);
      if (var3 == null) {
         return null;
      } else {
         int var4 = var3.kS() + (int)(var1 - var3.pC()) * 8;
         return this.gp.A(var4);
      }
   }

   @Override
   public Collection getGlobalVariables() {
      return this.gp.pC(16777216, Integer.MAX_VALUE);
   }

   @Override
   public IEVar createSymbolForRoutine(INativeMethodItem var1, IERoutineContext var2) {
      return this.wS.getSymbolForNativeItem(var1, var2.getRoutine(), var2);
   }

   @Override
   public INativeMethodItem retrieveRoutineFromSymbol(IEVar var1) {
      return (INativeMethodItem)this.wS.getNativeItemFromVar(var1);
   }

   @Override
   public IEVar createSymbolForData(INativeDataItem var1, IERoutineContext var2) {
      return this.wS.getSymbolForNativeItem(var1, var2.getRoutine(), var2);
   }

   @Override
   public INativeDataItem retrieveDataFromSymbol(IEVar var1) {
      return (INativeDataItem)this.wS.getNativeItemFromVar(var1);
   }

   @Override
   public IEVar getVarSafe(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         return this.gp.A(var1);
      }
   }

   @Override
   public IEVar getVar(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("A routine-scope variable cannot be retrieved from the global decompilation context; id= " + var1);
      } else {
         IEVar var2 = this.gp.A(var1);
         if (var2 == null) {
            throw new RuntimeException("The global variable does not exist: " + var1);
         } else {
            return var2;
         }
      }
   }

   @Override
   public boolean canCreateVariable(int var1, int var2) {
      return this.gp.wS(var1, var2);
   }

   @Override
   public Collection getVariables(int var1, int var2) {
      return this.gp.pC(var1, var2);
   }

   @Override
   public Collection getAllVariables() {
      return this.gp.pC();
   }

   @Override
   public Collection getAllRegisters() {
      return this.gp.pC(0, 65536);
   }

   @Override
   public Collection getAllRegisters(Set var1) {
      ArrayList var2 = new ArrayList();

      for (IEVar var4 : this.gp.pC(0, 65536)) {
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
         for (IEVar var6 : this.gp.pC(0, 131072)) {
            var4.setValue(var6, 0L);
         }
      }

      if (var2) {
         for (IEVar var9 : this.gp.pC(131072, 196608)) {
            var4.setValue(var9, 0L);
         }
      }

      if (var3) {
         for (IEVar var10 : this.gp.pC(16777216, Integer.MAX_VALUE)) {
            var4.setValue(var10, 0L);
         }
      }

      return var4;
   }

   @Override
   public void setData(Object var1, Object var2) {
      if (this.WR == null) {
         this.WR = new HashMap();
      }

      this.WR.put(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.WR == null ? null : this.WR.get(var1);
   }

   @Ser
   public static class Av implements ISegment {
      @SerId(1)
      long pC;
      @SerId(2)
      int A;

      public Av(long var1, int var3) {
         this.pC = var1;
         this.A = var3;
      }

      public Long pC() {
         return this.pC;
      }

      public Long A() {
         long var1 = this.pC + 1048576L;
         if (var1 == 0L) {
            var1 = -1L;
         }

         return var1;
      }

      public int kS() {
         return this.A;
      }

      public int wS() {
         return this.A + 8388608;
      }

      @Override
      public String toString() {
         return Long.toHexString(this.pC) + "[" + Long.toHexString(this.A) + "]";
      }
   }
}
