package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jebglobal.OA;

public class Pj {
   HE pC;
   IERoutineContext A;

   public Pj(HE var1) {
      this.pC = var1;
   }

   IEGeneric pC(int var1, OA var2, OA var3, OA var4) {
      IEGeneric var5 = var2.A(var1);
      IEGeneric var6 = EUtil.notB(var4.A(var1));
      IEGeneric var7 = var3.A(var1);
      return this.pC(var5, var6, var7);
   }

   IEGeneric A(int var1, OA var2, OA var3, OA var4) {
      IEGeneric var5 = var2.A(var1);
      IEGeneric var6 = var4.A(var1);
      IEGeneric var7 = var3.A(var1);
      return this.pC(var5, var6, var7);
   }

   IEGeneric kS(int var1, OA var2, OA var3, OA var4) {
      IEGeneric var5 = var4.A(var1);
      IEGeneric var6 = var2.A(var1);
      IEGeneric var7 = var3.A(var1);
      return this.pC(var5, var6, var7);
   }

   private IEGeneric pC(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      return this.A
         .createOperation(OperationType.XOR, var1, this.A.createOperation(OperationType.AND, this.A.createOperation(OperationType.XOR, var1, var3), var2));
   }
}
