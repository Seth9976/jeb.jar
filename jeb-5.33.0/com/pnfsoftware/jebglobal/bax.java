package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bax extends bay {
   public String pC;
   public String A;

   bax(bbc var1) {
      super(var1);
      this.pC = var1.UT("name");
      this.A = var1.UT("url");
   }

   @Override
   void pC(bbb var1) {
   }

   @Override
   public String toString() {
      return Strings.ff("%s (url: %s)", this.pC, this.A);
   }
}
