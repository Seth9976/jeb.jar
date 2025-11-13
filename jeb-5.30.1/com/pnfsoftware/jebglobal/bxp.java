package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bxp {
   bxj q;
   bxj RF;

   public bxp(bxj var1, bxj var2) {
      this.q = var1;
      this.RF = var2;
   }

   public bxj q() {
      return this.q;
   }

   public bxj RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      return Strings.ff("%s  ==>  %s", this.q, this.RF);
   }
}
