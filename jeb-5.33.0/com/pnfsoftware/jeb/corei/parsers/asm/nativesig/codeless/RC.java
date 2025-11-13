package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class RC implements rQ {
   @SerId(1)
   private final String pC;
   @SerId(2)
   private final DH A;

   public RC(String var1, DH var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public DH pC() {
      return this.A;
   }

   public String A() {
      return this.pC;
   }

   @Override
   public String toString() {
      return "StringFeature [value=" + this.pC + ", confidence=" + this.A + "]";
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         RC var2 = (RC)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         return true;
      }
   }
}
