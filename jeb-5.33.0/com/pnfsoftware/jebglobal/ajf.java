package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class ajf extends auo {
   @SerId(1)
   protected akr pC;

   public ajf(akr var1) {
      super(1073741824, null);
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
         this.UT(3);
         this.pC(var1.A().wS());
      }
   }

   public akr pC() {
      return this.pC;
   }
}
