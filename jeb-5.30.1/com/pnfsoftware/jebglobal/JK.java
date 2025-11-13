package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class JK extends yS {
   public JK(int var1, int var2, CW var3, Integer var4) {
      super(var1, var2 | 1024, var3, q(var4));
   }

   @Override
   public CW[] q() {
      return new CW[]{this.oW()[0]};
   }

   private static CW q(Integer var0) {
      return CW.q((long)var0.intValue(), 524288);
   }
}
