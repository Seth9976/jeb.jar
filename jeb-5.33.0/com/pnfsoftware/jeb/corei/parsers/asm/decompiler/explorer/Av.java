package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import com.pnfsoftware.jeb.util.format.Strings;

public class Av extends Ws {
   boolean pC = false;
   String A = null;

   @Override
   public String pC() {
      return null;
   }

   public void pC(boolean var1) {
      this.pC = var1;
   }

   public void pC(String var1) {
      this.A = var1;
   }

   @Override
   public String A() {
      if (this.pC) {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "ASGN:AddNeg:%s%s", this.A, Strings.LINESEP);
         return var1.toString();
      } else {
         return null;
      }
   }
}
