package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class zp {
   public static final zp pC = new zp(zp.Av.pC, 0);
   public static final zp A = new zp(zp.Av.A, 0);
   public static final zp kS = new zp(zp.Av.kS, 0);
   public static final zp wS = new zp(zp.Av.wS, 0);
   public static final zp UT = new zp(zp.Av.UT, 0);
   @SerId(1)
   private zp.Av E;
   @SerId(2)
   private int sY;

   public static zp pC(zp.Av var0, int var1) {
      if (var1 == 0) {
         switch (var0) {
            case pC:
               return pC;
            case A:
               return A;
            case kS:
               return kS;
            case wS:
               return wS;
            case UT:
               return UT;
            default:
               throw new RuntimeException();
         }
      } else {
         return new zp(var0, var1);
      }
   }

   private zp(zp.Av var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.E = var1;
         this.sY = var2;
      }
   }

   public boolean pC() {
      return this.E.pC();
   }

   @Override
   public String toString() {
      String var1 = this.E.toString();
      if (this.sY != 0) {
         var1 = var1 + Strings.ff("[flags=0x%X]", this.sY);
      }

      return var1;
   }

   @Ser
   public static enum Av {
      pC("Unknown"),
      A("Solidity<=0.4.21"),
      kS("Solidity>=0.4.22"),
      wS("Vyper"),
      UT("Yul");

      private final String E;

      private Av(String var3) {
         this.E = var3;
      }

      public boolean pC() {
         return this == A || this == kS;
      }

      @Override
      public String toString() {
         return this.E;
      }
   }
}
