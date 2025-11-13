package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class azp {
   int pC;
   byte[] A;

   public static azp pC(ByteBuffer var0) {
      azp var1 = new azp();
      var1.pC = var0.getShort() & '\uffff';
      int var2 = var0.getInt();
      var1.A = new byte[var2];
      var0.get(var1.A);
      return var1;
   }
}
