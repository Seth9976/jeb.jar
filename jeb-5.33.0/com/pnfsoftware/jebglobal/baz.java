package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;

public class baz extends bay {
   public String pC;

   baz(bbc var1) {
      super(var1);
      this.pC = (String)var1.pC("data", String.class);
      Assert.a(this.pC != null);
   }

   @Override
   void pC(bbb var1) {
   }

   @Override
   public String toString() {
      return this.pC;
   }
}
