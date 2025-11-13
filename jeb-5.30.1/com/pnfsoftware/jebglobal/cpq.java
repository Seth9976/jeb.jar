package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class cpq {
   public int q;
   public int RF;
   public int xK;

   public cpq(int var1, int var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public int q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public int xK() {
      return this.xK;
   }

   @Override
   public String toString() {
      return Strings.ff("type:%X,len:%X,off:%X", this.q, this.RF, this.xK);
   }
}
