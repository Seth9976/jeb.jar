package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class ceu {
   public long pC;
   public long A;
   public long kS;

   public static ceu pC(ByteBuffer var0, boolean var1) {
      ceu var2 = new ceu();
      var2.pC = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.A = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.kS = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      return var2;
   }
}
