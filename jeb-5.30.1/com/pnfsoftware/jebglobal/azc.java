package com.pnfsoftware.jebglobal;

import java.util.Objects;

class azc {
   private azb xK;
   boolean q;
   boolean RF;

   public azc(azb var1) {
      this.xK = var1;
   }

   public azb q() {
      return this.xK;
   }

   public azb RF() {
      if (this.q) {
         return this.xK.q();
      } else {
         return this.RF ? this.xK.RF() : this.xK;
      }
   }

   public void q(azb var1) {
      this.xK = var1;
   }

   public void q(azb var1, boolean var2, boolean var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void RF(azb var1) {
      if (this.q) {
         this.xK.Dw.lm().q = var1;
      } else if (this.RF) {
         this.xK.Dw.lm().RF = var1;
      } else {
         this.xK = var1;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.q, this.xK, this.RF);
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
         azc var2 = (azc)var1;
         return this.q == var2.q && Objects.equals(this.xK, var2.xK) && this.RF == var2.RF;
      }
   }
}
