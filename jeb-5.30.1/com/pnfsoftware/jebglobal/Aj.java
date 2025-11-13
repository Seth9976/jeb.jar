package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.TextBuilder;

public class Aj {
   int q;
   String RF;

   Aj(int var1, String var2) {
      this.q = var1;
      this.RF = var2;
   }

   public int q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   public void q(TextBuilder var1) {
      var1.appendLine("LibraryEntry:%d,'%s'", this.q, this.RF);
   }
}
