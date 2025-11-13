package com.pnfsoftware.jebglobal;

public enum jw {
   q(-1),
   RF(0),
   xK(1),
   Dw(2),
   Uv(3),
   oW(4);

   private final int gO;

   private jw(int var3) {
      this.gO = var3;
   }

   public int q() {
      return this.gO;
   }

   public static jw q(int var0) {
      for (jw var4 : values()) {
         if (var4.gO == var0) {
            return var4;
         }
      }

      return q;
   }
}
