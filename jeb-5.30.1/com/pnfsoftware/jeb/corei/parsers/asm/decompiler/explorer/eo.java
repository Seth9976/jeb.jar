package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import com.pnfsoftware.jeb.util.format.Strings;

public class eo extends ej {
   boolean q = false;
   String RF = null;

   @Override
   public String q() {
      return null;
   }

   @Override
   public void q(String var1) {
   }

   public void q(boolean var1) {
      this.q = var1;
   }

   public void RF(String var1) {
      this.RF = var1;
   }

   @Override
   public String RF() {
      if (this.q) {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "ASGN:AddNeg:%s%s", this.RF, Strings.LINESEP);
         return var1.toString();
      } else {
         return null;
      }
   }
}
