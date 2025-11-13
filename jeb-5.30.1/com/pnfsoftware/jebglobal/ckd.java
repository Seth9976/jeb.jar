package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class ckd {
   public long q;
   public long RF;
   public long xK;
   public long Dw;
   public long Uv;

   public static ckd q(ByteBuffer var0, boolean var1) {
      ckd var2 = new ckd();
      var2.q = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.RF = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.xK = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.Dw = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.Uv = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      return var2;
   }
}
