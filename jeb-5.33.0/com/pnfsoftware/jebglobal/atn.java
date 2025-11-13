package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.concurrent.TimedOperationVerifier;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class atn extends atf {
   private static final StructuredLogger WR = aco.pC(atn.class);

   public atn() {
      super(atg.A);
   }

   @Override
   public boolean kS() {
      if (this.ys == null) {
         this.ys = this.UT.convert(this.ld);
      }

      this.A.pC(this.ys);
      TimedOperationVerifier var1 = this.A.WR();
      ((aki)this.ys).pC(var1);
      return true;
   }
}
