package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class CodeConstant {
   @SerId(1)
   String name;
   @SerId(2)
   Object value;

   public CodeConstant(String var1, Object var2) {
      if (var1 != null && var2 != null) {
         this.name = var1;
         this.value = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public String getName() {
      return this.name;
   }

   public Object getValue() {
      return this.value;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.name == null ? 0 : this.name.hashCode());
      return 31 * var1 + (this.value == null ? 0 : this.value.hashCode());
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
         CodeConstant var2 = (CodeConstant)var1;
         if (this.name == null) {
            if (var2.name != null) {
               return false;
            }
         } else if (!this.name.equals(var2.name)) {
            return false;
         }

         if (this.value == null) {
            if (var2.value != null) {
               return false;
            }
         } else if (!this.value.equals(var2.value)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s", this.name, this.value);
   }
}
