package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

public class fT {
   uq q;
   IERoutineContext RF;

   public fT(uq var1) {
      this.q = var1;
   }

   IEGeneric q(int var1, RX var2) {
      IEGeneric var3 = var2.RF(var1);
      IEImm var4 = this.q.Dw(var3.getBitsize());
      IEImm var5 = this.q.Uv(var3.getBitsize());
      Object var6 = this.RF.createCond(var3.bit(0), var4, var5);

      for (int var7 = 1; var7 < var3.getBitsize(); var7++) {
         var6 = EUtil.add((IEGeneric)var6, this.RF.createCond(var3.bit(var7), var4, var5));
      }

      return (IEGeneric)var6;
   }

   IEGeneric q(int var1, RX var2, RX var3, RX var4) {
      IEGeneric var5 = var2.RF(var1);
      IEGeneric var6 = EUtil.notB(var4.RF(var1));
      IEGeneric var7 = var3.RF(var1);
      return this.q(var5, var6, var7);
   }

   IEGeneric RF(int var1, RX var2, RX var3, RX var4) {
      IEGeneric var5 = var2.RF(var1);
      IEGeneric var6 = var4.RF(var1);
      IEGeneric var7 = var3.RF(var1);
      return this.q(var5, var6, var7);
   }

   IEGeneric xK(int var1, RX var2, RX var3, RX var4) {
      IEGeneric var5 = var4.RF(var1);
      IEGeneric var6 = var2.RF(var1);
      IEGeneric var7 = var3.RF(var1);
      return this.q(var5, var6, var7);
   }

   private IEGeneric q(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      return this.RF
         .createOperation(OperationType.XOR, var1, this.RF.createOperation(OperationType.AND, this.RF.createOperation(OperationType.XOR, var1, var3), var2));
   }

   IEGeneric q(int var1, RX var2, RX var3, RX var4, int var5) {
      return var1 < var2.RF() - var5 ? var3.RF(var5 + var1) : var4.RF(var1 - (var2.RF() - var5));
   }
}
