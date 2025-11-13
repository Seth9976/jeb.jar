package com.pnfsoftware.jebglobal;

public class bxm implements bxj {
   public bxn q;
   public bxo RF;
   public bxj[] xK;
   int Dw = -1;

   public bxm(bxn var1, bxj... var2) {
      if (var1 != null && var2 != null) {
         this.q = var1;
         this.xK = var2;
      } else {
         throw new NullPointerException();
      }
   }

   public bxm(bxo var1, bxj... var2) {
      if (var1 != null && var2 != null) {
         this.RF = var1;
         this.xK = var2;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.q != null ? this.q.toString() : this.RF.toString());
      var1.append("(");
      int var2 = 0;

      for (bxj var6 : this.xK) {
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
