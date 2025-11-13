package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aop {
   private static final StructuredLogger q = aeg.q(aop.class);

   public static int q(IERoutineContext var0, aoo var1, IEMasterOptimizer var2) {
      int var3 = 0;

      for (int var4 = 1; var4 <= 5; var4++) {
         Object[] var10000 = new Object[]{var4};
         int var5 = var1.xK();
         var10000 = new Object[]{var5};
         if (var5 <= 0) {
            break;
         }

         var3 += var5;
         if (var2 == null) {
            break;
         }

         var5 = aeb.q(var0, var2, null, null, q, null);
         if (var5 <= 0) {
            break;
         }
      }

      return var3;
   }
}
