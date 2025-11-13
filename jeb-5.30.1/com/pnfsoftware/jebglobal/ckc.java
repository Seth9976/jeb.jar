package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class ckc {
   public long q;
   public long RF;
   public long xK;
   public long Dw;
   public long Uv;
   public long oW;

   public static ckc q(ByteBuffer var0) {
      ckc var1 = new ckc();
      var1.q = var0.getLong();
      var1.RF = var0.getLong();
      var1.xK = var0.getLong();
      var1.Dw = var0.getLong();
      var1.Uv = var0.getLong();
      var1.oW = var0.getLong();
      return var1;
   }
}
