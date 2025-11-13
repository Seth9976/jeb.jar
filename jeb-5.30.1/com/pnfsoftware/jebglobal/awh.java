package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.concurrent.TimedOperationVerifier;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awh extends avx {
   private static final StructuredLogger JY = aeg.q(awh.class);

   public awh() {
      super(avy.RF);
   }

   @Override
   public boolean Dw() {
      if (this.nf == null) {
         this.nf = this.Uv.convert(this.gP);
      }

      this.RF.q(this.nf);
      TimedOperationVerifier var1 = this.RF.LK();
      ((aml)this.nf).q(var1);
      return true;
   }
}
