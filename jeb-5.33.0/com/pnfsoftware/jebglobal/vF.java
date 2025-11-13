package com.pnfsoftware.jebglobal;

public class vF extends ks {
   private Integer pC;
   private Integer A;
   private wR.Sv kS;

   @Override
   final wR pC() {
      String var1 = "";
      if (this.A == null) {
         var1 = String.valueOf("").concat(" size");
      }

      if (this.kS == null) {
         var1 = String.valueOf(var1).concat(" type");
      }

      if (this.pC == null) {
         var1 = String.valueOf(var1).concat(" data");
      }

      if (!var1.isEmpty()) {
         throw new IllegalStateException();
      } else {
         return new wR.Av(this.A, this.kS, this.pC);
      }
   }

   @Override
   final ks pC(int var1) {
      this.A = var1;
      return this;
   }

   @Override
   final ks pC(wR.Sv var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.kS = var1;
         return this;
      }
   }

   @Override
   final ks A(int var1) {
      this.pC = var1;
      return this;
   }
}
