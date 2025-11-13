package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;

public class aqd {
   IERoutineContext q;
   IEMasterOptimizer RF;

   public aqd(IERoutineContext var1, IEMasterOptimizer var2) {
      if (var1 != null && var2 != null) {
         this.q = var1;
         this.RF = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public IEGeneric q(IEGeneric var1) {
      int var2 = 0;
      int var3 = 10;

      while (var3-- > 0) {
         int var4 = 0;

         for (OptimizerEntry var6 : this.RF.getRegisteredOptimizers()) {
            IOptimizer var7 = var6.getOptimizer();
            if (var7 instanceof AbstractEOptimizer) {
               IEGeneric var8 = ((AbstractEOptimizer)var7).performOnExpression(var1, this.q);
               if (var8 != null) {
                  var4++;
                  var1 = var8;
               }
            }
         }

         if (var4 == 0) {
            break;
         }

         var2 += var4;
      }

      return var2 <= 0 ? null : var1;
   }
}
