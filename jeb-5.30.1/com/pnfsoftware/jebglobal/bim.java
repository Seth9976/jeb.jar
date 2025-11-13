package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bim {
   @SerId(1)
   bjw q;
   @SerId(2)
   byte[] RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;

   public bim(bjw var1, byte[] var2, int var3, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public bil q() {
      return new bil(this.q, this.RF, this.xK, this.Dw, false);
   }
}
