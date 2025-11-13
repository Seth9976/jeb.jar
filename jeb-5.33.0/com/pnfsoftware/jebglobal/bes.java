package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bes {
   @SerId(1)
   bgb pC;
   @SerId(2)
   byte[] A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;

   public bes(bgb var1, byte[] var2, int var3, int var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public ber pC() {
      return new ber(this.pC, this.A, this.kS, this.wS, false);
   }
}
