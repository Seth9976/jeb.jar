package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class awu extends awr {
   @SerId(1)
   private final String pC;
   @SerId(2)
   private final String A;

   public awu(String var1) {
      this(var1, null);
   }

   public awu(String var1, String var2) {
      this.pC = var1;
      this.A = var2;
   }

   public String pC() {
      return this.pC;
   }

   @Override
   public String getType() {
      return "file name";
   }

   public String A() {
      StringBuilder var1 = new StringBuilder();
      if (this.A != null) {
         var1.append(this.A);
         var1.append(">");
      }

      var1.append(this.pC);
      return var1.toString();
   }

   @Override
   public boolean importTo(INativeItem var1) {
      return false;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         awu var2 = (awu)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

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

   @Override
   public boolean isPrintable() {
      return true;
   }
}
