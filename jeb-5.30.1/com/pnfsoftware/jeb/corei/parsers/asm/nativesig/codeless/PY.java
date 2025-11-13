package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class PY implements tw {
   @SerId(1)
   private final String q;
   @SerId(2)
   private final iZ RF;

   public PY(String var1, iZ var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public iZ q() {
      return this.RF;
   }

   public String RF() {
      return this.q;
   }

   @Override
   public String toString() {
      return "StringFeature [value=" + this.q + ", confidence=" + this.RF + "]";
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
         PY var2 = (PY)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         return true;
      }
   }
}
