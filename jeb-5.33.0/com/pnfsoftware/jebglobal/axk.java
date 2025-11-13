package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axk extends axh {
   @SerId(1)
   private final String pC;
   @SerId(2)
   private final boolean A;
   @SerId(3)
   private final int kS;
   @SerId(4)
   private boolean wS;

   public axk(String var1, boolean var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String getType() {
      return "CalledRoutineName";
   }

   public String pC() {
      return this.pC;
   }

   public boolean A() {
      return this.A;
   }

   public int kS() {
      return this.kS;
   }

   public void pC(boolean var1) {
      this.wS = var1;
   }

   public boolean wS() {
      return this.wS;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType());
      var1.append(" -> ");
      var1.append(this.pC());
      Strings.ff(var1, "(#%d)", this.kS);
      if (this.A) {
         var1.append("(I)");
      }

      if (this.wS) {
         var1.append("(IMP)");
      }

      return var1.toString();
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof axj ? var1.match(this) : this.equals(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.kS;
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
         axk var2 = (axk)var1;
         if (this.kS != var2.kS) {
            return false;
         } else {
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

   @Override
   public INativeFeature deepCopy() {
      axk var1 = new axk(this.pC, this.A, this.kS);
      var1.pC(this.wS);
      return var1;
   }
}
