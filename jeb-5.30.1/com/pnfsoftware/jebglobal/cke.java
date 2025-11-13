package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class cke {
   public int q;
   public int RF;
   public int xK;
   public int Dw;
   public long Uv;
   public long oW;
   public long gO;
   public long nf;
   public long gP;
   public long za;
   public long lm;

   public static cke q(ByteBuffer var0, boolean var1) {
      cke var2 = new cke();
      var2.q = var0.getInt();
      var2.RF = var0.getInt();
      var2.xK = var0.getInt();
      if (var1) {
         var2.Dw = var0.getInt();
      }

      var2.Uv = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.oW = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.gO = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.nf = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.gP = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.za = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.lm = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      return var2;
   }
}
