package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum clg {
   q(0),
   RF(16),
   xK(32),
   Dw(48),
   Uv(64),
   oW(80),
   gO(96),
   nf(112),
   gP(128),
   za(144),
   lm(160);

   private final int zz;

   private clg(int var3) {
      this.zz = var3;
   }

   public static clg q(int var0) {
      for (clg var4 : values()) {
         if (var0 == var4.zz) {
            return var4;
         }
      }

      return null;
   }

   public int q() {
      return this.zz;
   }

   public boolean RF() {
      return this.q() >= za.q();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean xK() {
      switch (this) {
         case xK:
         case Dw:
         case Uv:
         case gO:
         case gP:
         case lm:
            return true;
         default:
            return false;
      }
   }
}
