package com.pnfsoftware.jebglobal;

public enum aqb {
   q(0),
   RF(10),
   xK(50),
   Dw(90),
   Uv(100);

   private final int oW;

   private aqb(int var3) {
      this.oW = var3;
   }

   public int q() {
      return this.oW;
   }

   public boolean RF() {
      return this.oW > 50;
   }

   public int q(aqb var1) {
      return this.oW - var1.oW;
   }
}
