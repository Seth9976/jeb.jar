package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class RI extends GC {
   @SerId(1)
   private String cC;
   @SerId(2)
   private String sH = ".";

   public RI(int var1, int var2, int var3, int var4, String var5) {
      super(RF(var1, var3), RF(var1, var2, var3), var3, var4);
      this.cC = var5;
   }

   public RI q(String var1) {
      this.sH = var1;
      return this;
   }

   @Override
   public String getSuffix(IInstruction var1) {
      return this.cC != null && !this.cC.isEmpty() ? this.sH + this.cC + super.getSuffix(var1) : super.getSuffix(var1);
   }

   public String q() {
      return this.cC;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.sH == null ? 0 : this.sH.hashCode());
      return 31 * var1 + (this.cC == null ? 0 : this.cC.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         RI var2 = (RI)var1;
         if (this.sH == null) {
            if (var2.sH != null) {
               return false;
            }
         } else if (!this.sH.equals(var2.sH)) {
            return false;
         }

         if (this.cC == null) {
            if (var2.cC != null) {
               return false;
            }
         } else if (!this.cC.equals(var2.cC)) {
            return false;
         }

         return true;
      }
   }
}
