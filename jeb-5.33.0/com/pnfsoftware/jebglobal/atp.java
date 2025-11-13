package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atp extends atf {
   private static final StructuredLogger WR = aco.pC(atp.class);

   public atp() {
      super(atg.wS);
   }

   @Override
   public boolean kS() {
      alu var1 = new alu(this.ys, this.kS);

      int var2;
      try {
         WR.beginSection("Simulation: starting...");
         var2 = var1.pC();
      } finally {
         WR.closeSection();
      }

      if (var2 > 0) {
         if (var2 == 2) {
            if (this.wS()) {
               return false;
            }

            return true;
         }

         Object[] var10000 = new Object[0];
         boolean[] var3 = new boolean[1];
         int var4 = var1.pC(var3);
         var10000 = new Object[]{var4};
         if (var3[0] && this.A.gp() == 0 && this.wS()) {
            return false;
         }

         var1.A();
      } else {
         WR.iHH("Simulation: failed!");
      }

      return true;
   }
}
