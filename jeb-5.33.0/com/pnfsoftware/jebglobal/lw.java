package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class lw extends LC {
   @SerId(1)
   private String A;
   @SerId(2)
   private String kS = ".";

   public lw(int var1, int var2, int var3, int var4, String var5) {
      super(A(var1, var3), A(var1, var2, var3), var3, var4);
      this.A = var5;
   }

   public lw pC(String var1) {
      this.kS = var1;
      return this;
   }

   @Override
   public String getSuffix(IInstruction var1) {
      return this.A != null && !this.A.isEmpty() ? this.kS + this.A + super.getSuffix(var1) : super.getSuffix(var1);
   }

   public String pC() {
      return this.A;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         lw var2 = (lw)var1;
         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }
}
