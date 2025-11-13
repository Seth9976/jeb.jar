package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axa extends awy {
   @SerId(1)
   protected awy kS;

   public axa(awy var1) {
      super(var1.pC, var1.A);
      this.kS = var1;
   }

   @Override
   protected boolean pC(auu var1) {
      return this.kS.pC(var1);
   }
}
