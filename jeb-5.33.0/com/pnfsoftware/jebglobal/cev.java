package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class cev {
   public int pC;
   public int A;

   public static cev pC(ByteBuffer var0) {
      cev var1 = new cev();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      return var1;
   }
}
