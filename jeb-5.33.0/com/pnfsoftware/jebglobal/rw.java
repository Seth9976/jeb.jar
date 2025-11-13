package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class rw extends FH {
   private static fj A;

   public rw(Yg var1, Integer var2) {
      super(var1.getOperandBitsize(), 0L, 0, var1, pC(var2));
   }

   public rw(Yg var1, Yg... var2) {
      super(var1.getOperandBitsize(), 0L, 0, var1, new fL(var2));
   }

   @Override
   public Yg[] pC() {
      Yg[] var1 = new Yg[]{this.A()};
      if (this.E()[1] instanceof FH) {
         Yg[] var2 = ((FH)this.E()[1]).pC();
         if (var2.length > 0) {
            Yg[] var3 = new Yg[var1.length + var2.length];
            var3[0] = var1[0];
            System.arraycopy(var2, 0, var3, 1, var2.length);
            var1 = var3;
         }
      }

      return var1;
   }

   public Yg A() {
      return this.E()[0];
   }

   public Integer kS() {
      Yg var1 = this.E()[1];
      if (var1.getOperandType() == 6 && var1.getAlias(0L).equals("")) {
         return null;
      } else {
         return var1.getOperandType() == 1 ? (int)var1.getOperandValue() : null;
      }
   }

   private static Yg pC(Integer var0) {
      return var0 == null ? sY() : Yg.pC((long)var0.intValue(), 1);
   }

   private static Yg sY() {
      if (A == null) {
         A = new fj(0L, 1, "");
      }

      return A;
   }
}
