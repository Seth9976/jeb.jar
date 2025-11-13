package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class cer {
   public long pC;
   public long A;
   public long kS;
   public long wS;
   public long UT;
   public long E;

   public static cer pC(ByteBuffer var0) {
      cer var1 = new cer();
      var1.pC = var0.getLong();
      var1.A = var0.getLong();
      var1.kS = var0.getLong();
      var1.wS = var0.getLong();
      var1.UT = var0.getLong();
      var1.E = var0.getLong();
      return var1;
   }
}
