package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awk extends avx {
   private static final StructuredLogger JY = aeg.q(awk.class);

   public awk() {
      super(avy.Uv);
   }

   @Override
   protected boolean Dw() {
      if (!this.Uv.canCreateCalls()) {
         Object[] var6 = new Object[0];
         return true;
      } else {
         anr var1 = this.RF.gO();
         var1.q(false);
         Object[] var10000 = new Object[0];
         ann var2 = var1.q(null);
         aeb.q(this.nf, "Temporary CFG after first-attempt resolution (%d)", var2.q);
         if (var1.RF() && this.oW()) {
            return false;
         } else {
            var10000 = new Object[0];
            var1.q(true);
            var2 = var1.Dw();
            var10000 = new Object[]{var2.q};
            return !var1.RF() || !this.oW();
         }
      }
   }
}
