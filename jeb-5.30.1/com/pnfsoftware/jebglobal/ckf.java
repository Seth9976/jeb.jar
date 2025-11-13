package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class ckf {
   public long q;
   public long RF;
   public long xK;

   public static ckf q(ByteBuffer var0, boolean var1) {
      ckf var2 = new ckf();
      var2.q = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.RF = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.xK = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      return var2;
   }
}
