package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class qV {
   public static final qV q = new qV(qV.eo.q, 0);
   public static final qV RF = new qV(qV.eo.RF, 0);
   public static final qV xK = new qV(qV.eo.xK, 0);
   public static final qV Dw = new qV(qV.eo.Dw, 0);
   public static final qV Uv = new qV(qV.eo.Uv, 0);
   public static final int oW = 1;
   @SerId(1)
   private qV.eo gO;
   @SerId(2)
   private int nf;

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static qV q(qV.eo var0, int var1) {
      if (var1 == 0) {
         switch (var0) {
            case q:
               return q;
            case RF:
               return RF;
            case xK:
               return xK;
            case Dw:
               return Dw;
            case Uv:
               return Uv;
            default:
               throw new RuntimeException();
         }
      } else {
         return new qV(var0, var1);
      }
   }

   private qV(qV.eo var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1;
         this.nf = var2;
      }
   }

   public qV.eo q() {
      return this.gO;
   }

   public int RF() {
      return this.nf;
   }

   public boolean xK() {
      return this.gO.RF();
   }

   @Override
   public String toString() {
      String var1 = this.gO.toString();
      if (this.nf != 0) {
         var1 = var1 + Strings.ff("[flags=0x%X]", this.nf);
      }

      return var1;
   }

   @Ser
   public static enum eo {
      q("Unknown"),
      RF("Solidity<=0.4.21"),
      xK("Solidity>=0.4.22"),
      Dw("Vyper"),
      Uv("Yul");

      private final String oW;

      private eo(String var3) {
         this.oW = var3;
      }

      public String q() {
         return this.oW;
      }

      public boolean RF() {
         return this == RF || this == xK;
      }

      @Override
      public String toString() {
         return this.oW;
      }
   }
}
