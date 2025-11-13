package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class azq extends azo {
   @SerId(1)
   private final String q;

   public azq(String var1) {
      this.q = var1;
   }

   @Override
   public String getType() {
      return "compiler name";
   }

   public String q() {
      return this.q;
   }

   @Override
   public boolean importTo(INativeItem var1) {
      return false;
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
         azq var2 = (azq)var1;
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
