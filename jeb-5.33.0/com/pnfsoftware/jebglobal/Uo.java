package com.pnfsoftware.jebglobal;

import java.util.function.Function;

class Uo extends nl {
   private Function Ab;

   public Uo(int var1, Function var2) {
      super(var1);
      this.Ab = var2;
   }

   @Override
   protected int pC(byte[] var1) throws oJ {
      return (Integer)this.Ab.apply(var1);
   }
}
