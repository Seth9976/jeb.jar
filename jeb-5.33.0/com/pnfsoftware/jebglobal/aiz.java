package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class aiz {
   public int pC;
   public int A;
   public int kS;
   public aja wS;
   public int UT;

   public aiz(int var1, int var2, int var3, aja var4, int var5) {
      if (var1 > 0 && var2 > 0) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
      } else {
         throw new RuntimeException();
      }
   }

   public aiz(int var1, int var2, int var3, aja var4) {
      this(var1, var2, var3, var4, 0);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + this.A;
      var1 = 31 * var1 + this.kS;
      return 31 * var1 + this.UT;
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
         aiz var2 = (aiz)var1;
         if (this.wS != var2.wS) {
            return false;
         } else if (this.pC != var2.pC) {
            return false;
         } else if (this.A != var2.A) {
            return false;
         } else {
            return this.kS != var2.kS ? false : this.UT == var2.UT;
         }
      }
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%s:%d->%d->%d", this.wS, this.pC, this.A, this.kS);
      if (this.UT > 0) {
         var1 = var1 + "(" + this.UT + ")";
      }

      return var1;
   }
}
