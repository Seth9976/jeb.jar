package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class ces {
   public long pC;
   public long A;
   public long kS;
   public long wS;
   public long UT;

   public static ces pC(ByteBuffer var0, boolean var1) {
      ces var2 = new ces();
      var2.pC = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.A = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.kS = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.wS = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.UT = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      return var2;
   }
}
