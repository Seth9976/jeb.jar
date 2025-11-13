package com.pnfsoftware.jebglobal;

import java.util.Map;

public class vy extends wv {
   private Integer pC;
   private Integer A;
   private Integer kS;
   private MS wS;
   private Integer UT;
   private wR E;
   private Map sY;

   @Override
   final MS.Av pC() {
      return new MS.Av.Av(this.A, this.pC, this.kS, this.E, this.sY, this.UT, this.wS);
   }

   @Override
   final wv pC(int var1) {
      this.A = var1;
      return this;
   }

   @Override
   final wv pC(wR var1) {
      this.E = var1;
      return this;
   }

   @Override
   final wv pC(MS var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.wS = var1;
         return this;
      }
   }

   @Override
   final wv pC(Map var1) {
      this.sY = var1;
      return this;
   }

   @Override
   final wv A(int var1) {
      this.pC = var1;
      return this;
   }

   @Override
   final wv kS(int var1) {
      this.kS = var1;
      return this;
   }

   @Override
   final wv wS(int var1) {
      this.UT = var1;
      return this;
   }
}
