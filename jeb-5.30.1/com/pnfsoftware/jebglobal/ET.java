package com.pnfsoftware.jebglobal;

import java.util.function.Function;

class ET extends kP {
   private Function xW;

   public ET(Function var1) {
      this.xW = var1;
   }

   @Override
   protected int q(byte[] var1) throws eK {
      return (Integer)this.xW.apply(var1);
   }
}
