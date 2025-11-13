package com.pnfsoftware.jebglobal;

public class bsy implements bsv {
   public bsz pC;
   public bta A;
   public bsv[] kS;
   volatile int wS = -1;
   volatile int UT = -1;

   public bsy(bsz var1, bsv... var2) {
      if (var1 != null && var2 != null) {
         this.pC = var1;
         this.kS = var2;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.pC != null ? this.pC.toString() : this.A.toString());
      var1.append("(");
      int var2 = 0;

      for (bsv var6 : this.kS) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         var1.append(var6.toString());
         var2++;
      }

      var1.append(")");
      return var1.toString();
   }
}
