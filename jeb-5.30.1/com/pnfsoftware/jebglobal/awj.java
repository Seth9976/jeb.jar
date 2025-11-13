package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awj extends avx {
   private static final StructuredLogger JY = aeg.q(awj.class);

   public awj() {
      super(avy.Dw);
   }

   @Override
   public boolean Dw() {
      anz var1 = new anz(this.nf, this.xK);

      int var2;
      try {
         JY.beginSection("Simulation: starting...");
         var2 = var1.xK();
      } finally {
         JY.closeSection();
      }

      if (var2 > 0) {
         if (var2 == 2) {
            if (this.oW()) {
               return false;
            }

            return true;
         }

         Object[] var10000 = new Object[0];
         boolean[] var3 = new boolean[1];
         int var4 = var1.q(var3);
         var10000 = new Object[]{var4};
         if (var3[0] && this.RF.lm() == 0 && this.oW()) {
            return false;
         }

         var1.oW();
      } else {
         JY.iHH("Simulation: failed!");
      }

      return true;
   }
}
