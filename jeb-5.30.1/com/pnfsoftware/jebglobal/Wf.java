package com.pnfsoftware.jebglobal;

import java.util.function.Function;

class Wf extends PZ {
   private Function xW;

   public Wf(int var1, Function var2) {
      super(var1);
      this.xW = var2;
   }

   @Override
   protected int q(byte[] var1) throws eK {
      return (Integer)this.xW.apply(var1);
   }
}
