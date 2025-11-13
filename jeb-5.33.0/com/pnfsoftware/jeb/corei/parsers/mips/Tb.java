package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum Tb {
   pC(0),
   A(16),
   kS(32),
   wS(48),
   UT(64),
   E(80),
   sY(96),
   ys(112),
   ld(128),
   gp(144),
   oT(160);

   private final int fI;

   private Tb(int var3) {
      this.fI = var3;
   }

   public static Tb pC(int var0) {
      for (Tb var4 : values()) {
         if (var0 == var4.fI) {
            return var4;
         }
      }

      return null;
   }

   public int pC() {
      return this.fI;
   }

   public boolean A() {
      return this.pC() >= gp.pC();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean kS() {
      switch (this) {
         case kS:
         case wS:
         case UT:
         case sY:
         case ld:
         case oT:
            return true;
         default:
            return false;
      }
   }
}
