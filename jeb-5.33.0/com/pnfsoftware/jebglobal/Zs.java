package com.pnfsoftware.jebglobal;

import java.util.function.Function;

public class Zs extends Ll {
   private final int pC;
   private final Function A;

   public Zs(Function var1) {
      this(32, var1);
   }

   public Zs(int var1, Function var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) {
      Integer var3 = (Integer)this.A.apply(var1);
      return var3 == null ? null : Yg.pC(this.pC, (long)var3.intValue());
   }
}
