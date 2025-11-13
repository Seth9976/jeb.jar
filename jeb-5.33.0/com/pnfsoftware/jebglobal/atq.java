package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atq extends atf {
   private static final StructuredLogger WR = aco.pC(atq.class);

   public atq() {
      super(atg.UT);
   }

   @Override
   protected boolean kS() {
      if (!this.UT.canCreateCalls()) {
         Object[] var6 = new Object[0];
         return true;
      } else {
         alm var1 = this.A.E();
         var1.pC(false);
         Object[] var10000 = new Object[0];
         ali var2 = var1.pC(null);
         acj.pC(this.ys, "Temporary CFG after first-attempt resolution (%d)", var2.pC);
         if (var1.pC() && this.wS()) {
            return false;
         } else {
            var10000 = new Object[0];
            var1.pC(true);
            var2 = var1.kS();
            var10000 = new Object[]{var2.pC};
            return !var1.pC() || !this.wS();
         }
      }
   }
}
