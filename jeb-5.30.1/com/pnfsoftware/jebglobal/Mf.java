package com.pnfsoftware.jebglobal;

import java.util.function.Function;

public class Mf extends jD {
   private final int q;
   private final Function RF;

   public Mf(Function var1) {
      this(32, var1);
   }

   public Mf(int var1, Function var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) {
      Integer var3 = (Integer)this.RF.apply(var1);
      return var3 == null ? null : CW.q(this.q, (long)var3.intValue());
   }
}
