package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atv extends atf {
   private static final StructuredLogger WR = aco.pC(atv.class);

   public atv() {
      super(atg.gp);
   }

   @Override
   protected boolean kS() {
      IEVar var1 = this.ys.getVariableByName("SP0");
      if (var1 == null) {
         Object[] var18 = new Object[0];
         return true;
      } else {
         anc var2 = new anc(this.ys);
         var2.pC(var1);
         int var3 = var2.A(var1);
         Object[] var10000 = new Object[]{var3};
         var3 = var2.pC();
         var10000 = new Object[]{var3};
         var3 = 0;

         try {
            WR.beginSection("Performing dead-code removal");
            apa var4 = (apa)this.gp.getOptimizerObject(apa.class);
            if (var4 != null) {
               var3 += var4.performOnTarget(this.ys);
            }
         } finally {
            WR.closeSection();
         }

         try {
            WR.beginSection("Performing variable propagation");
            aqv var15 = (aqv)this.gp.getOptimizerObject(aqv.class);
            if (var15 != null) {
               var3 += var15.performOnTarget(this.ys);
            }
         } finally {
            WR.closeSection();
         }

         var10000 = new Object[]{var3};
         return true;
      }
   }
}
