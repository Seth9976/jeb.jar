package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class axc extends avx {
   private static final StructuredLogger JY = aeg.q(axc.class);

   public axc() {
      super(avy.oQ);
   }

   @Override
   protected boolean Dw() {
      try {
         JY.beginSection("Structuring...");
         aes var1 = new aes(this.xK.getHighLevelContext(), this.nf);
         var1.q(this.xK.getOptions().structurerUseVersion);
         this.lm = var1.RF();
         this.RF.q(this.lm);
      } finally {
         JY.closeSection();
      }

      return true;
   }
}
