package com.pnfsoftware.jebglobal;

public class bI extends Ll {
   public static final ZW pC = new ZW(0.0);
   public static final ZW A = new ZW(0.5);
   public static final ZW kS = new ZW(0.0);
   public static final ZW wS = new ZW(32);
   public static final ZW UT = new ZW(64);
   private final ZW E;
   private final ZW sY;

   public bI(ZW var1, ZW var2) {
      this.E = var1;
      this.sY = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) {
      int var3 = (Integer)this.E.A(var1);
      if (var3 <= 0) {
         var3 = 64;
      }

      double var4 = (Double)this.sY.A(var1);
      return Yg.pC(var3, 0, var4);
   }

   public static bI pC(ZW var0) {
      return new bI(var0, pC);
   }
}
