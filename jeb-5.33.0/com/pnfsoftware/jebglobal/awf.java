package com.pnfsoftware.jebglobal;

import java.util.Objects;

class awf {
   private awe kS;
   boolean pC;
   boolean A;

   public awf(awe var1) {
      this.kS = var1;
   }

   public awe pC() {
      return this.kS;
   }

   public awe A() {
      if (this.pC) {
         return this.kS.pC();
      } else {
         return this.A ? this.kS.A() : this.kS;
      }
   }

   public void pC(awe var1) {
      this.kS = var1;
   }

   public void pC(awe var1, boolean var2, boolean var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void A(awe var1) {
      if (this.pC) {
         this.kS.wS.oT().pC = var1;
      } else if (this.A) {
         this.kS.wS.oT().A = var1;
      } else {
         this.kS = var1;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.pC, this.kS, this.A);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         awf var2 = (awf)var1;
         return this.pC == var2.pC && Objects.equals(this.kS, var2.kS) && this.A == var2.A;
      }
   }
}
