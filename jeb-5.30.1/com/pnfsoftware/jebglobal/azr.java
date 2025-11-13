package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class azr extends azo {
   @SerId(1)
   private final String q;
   @SerId(2)
   private final String RF;

   public azr(String var1) {
      this(var1, null);
   }

   public azr(String var1, String var2) {
      this.q = var1;
      this.RF = var2;
   }

   public String q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   @Override
   public String getType() {
      return "file name";
   }

   public String xK() {
      StringBuilder var1 = new StringBuilder();
      if (this.RF != null) {
         var1.append(this.RF);
         var1.append(">");
      }

      var1.append(this.q);
      return var1.toString();
   }

   @Override
   public boolean importTo(INativeItem var1) {
      return false;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         azr var2 = (azr)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

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

   @Override
   public boolean isPrintable() {
      return true;
   }
}
