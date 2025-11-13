package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atz extends atf {
   private static final StructuredLogger WR = aco.pC(atz.class);

   public atz() {
      super(atg.vP);
   }

   @Override
   protected boolean kS() {
      this.pC(WR);
      if (this.A.ld() == 0) {
         try {
            alm var1 = this.A.E();
            var1.A();
            var1.wS();
            var1.UT();
            var1.E();
            boolean var2 = this.UT.resolveCustomCalls(this.ys);
            if ((var1.pC() || var2) && this.wS()) {
               return false;
            }
         } catch (RuntimeException var3) {
            WR.catching(var3);
            throw var3;
         }
      }

      return true;
   }
}
