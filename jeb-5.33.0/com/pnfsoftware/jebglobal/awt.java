package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class awt extends awr {
   @SerId(1)
   private final String pC;

   public awt(String var1) {
      this.pC = var1;
   }

   @Override
   public String getType() {
      return "compiler name";
   }

   public String pC() {
      return this.pC;
   }

   @Override
   public boolean importTo(INativeItem var1) {
      return false;
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
         awt var2 = (awt)var1;
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
