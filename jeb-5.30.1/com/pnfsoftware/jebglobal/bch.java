package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bch {
   bbd q;
   String RF;
   int xK;
   int Dw;

   public bch(bbd var1, String var2) {
      this(var1, var2, 0, 0);
   }

   public bch(bbd var1, String var2, int var3, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public bbd q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   public int Dw() {
      return this.Dw;
   }

   @Override
   public String toString() {
      String var1;
      if (this.RF == null) {
         var1 = this.q.toString();
      } else {
         var1 = Strings.ff("\"%s\":%s", this.RF, this.q);
      }

      if (this.xK > 0) {
         var1 = var1 + ":" + this.xK;
      }

      if (this.Dw > 0) {
         var1 = var1 + "(align:" + this.Dw + ")";
      }

      return var1;
   }
}
