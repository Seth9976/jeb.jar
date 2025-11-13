package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awt extends avx {
   private static final StructuredLogger JY = aeg.q(awt.class);

   public awt() {
      super(avy.LK);
   }

   @Override
   protected boolean Dw() {
      this.q(JY);
      if (this.RF.za() == 0) {
         try {
            anr var1 = this.RF.gO();
            var1.xK();
            var1.Uv();
            var1.oW();
            var1.gO();
            boolean var2 = this.Uv.resolveCustomCalls(this.nf);
            if ((var1.RF() || var2) && this.oW()) {
               return false;
            }
         } catch (RuntimeException var3) {
            JY.catching(var3);
            throw var3;
         }
      }

      return true;
   }
}
