package com.pnfsoftware.jebglobal;

public class Nd extends jD {
   public static final dD q = new dD(0.0);
   public static final dD RF = new dD(0.5);
   public static final dD xK = new dD(0.0);
   public static final dD Dw = new dD(32);
   public static final dD Uv = new dD(64);
   private final dD oW;
   private final dD gO;

   public Nd(dD var1, dD var2) {
      this.oW = var1;
      this.gO = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) {
      int var3 = (Integer)this.oW.RF(var1);
      if (var3 <= 0) {
         var3 = 64;
      }

      double var4 = (Double)this.gO.RF(var1);
      return CW.q(var3, 0, var4);
   }

   public static Nd q(dD var0) {
      return new Nd(var0, q);
   }
}
