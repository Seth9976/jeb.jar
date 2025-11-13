package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class BY extends yS {
   private static sJ gP;

   public BY(CW var1, Integer var2) {
      super(var1.getOperandBitsize(), 0L, 0, var1, q(var2));
   }

   public BY(CW var1, CW... var2) {
      super(var1.getOperandBitsize(), 0L, 0, var1, new TD(var2));
   }

   @Override
   public CW[] q() {
      CW[] var1 = new CW[]{this.RF()};
      if (this.oW()[1] instanceof yS) {
         CW[] var2 = ((yS)this.oW()[1]).q();
         if (var2.length > 0) {
            CW[] var3 = new CW[var1.length + var2.length];
            var3[0] = var1[0];
            System.arraycopy(var2, 0, var3, 1, var2.length);
            var1 = var3;
         }
      }

      return var1;
   }

   public CW RF() {
      return this.oW()[0];
   }

   public Integer xK() {
      CW var1 = this.oW()[1];
      if (var1.getOperandType() == 6 && var1.getAlias(0L).equals("")) {
         return null;
      } else {
         return var1.getOperandType() == 1 ? (int)var1.getOperandValue() : null;
      }
   }

   private static CW q(Integer var0) {
      return var0 == null ? gO() : CW.q((long)var0.intValue(), 1);
   }

   private static CW gO() {
      if (gP == null) {
         gP = new sJ(0L, 1, "");
      }

      return gP;
   }
}
