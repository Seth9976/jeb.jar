package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class azt extends azo {
   @SerId(1)
   private final String q;

   public azt(String var1) {
      this.q = var1;
   }

   @Override
   public Object getValue() {
      return this.q;
   }

   @Override
   public String getType() {
      return "unmangled name";
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
         azt var2 = (azt)var1;
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
