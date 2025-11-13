package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class ami {
   private static final StructuredLogger pC = aco.pC(ami.class);

   public static int pC(IERoutineContext var0, amh var1, IEMasterOptimizer var2) {
      int var3 = 0;

      for (int var4 = 1; var4 <= 5; var4++) {
         Object[] var10000 = new Object[]{var4};
         int var5 = var1.A();
         var10000 = new Object[]{var5};
         if (var5 <= 0) {
            break;
         }

         var3 += var5;
         if (var2 == null) {
            break;
         }

         var5 = acj.pC(var0, var2, null, null, pC, null);
         if (var5 <= 0) {
            break;
         }
      }

      return var3;
   }
}
