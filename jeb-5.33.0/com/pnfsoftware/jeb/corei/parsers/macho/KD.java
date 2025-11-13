package com.pnfsoftware.jeb.corei.parsers.macho;

import java.nio.ByteBuffer;

public class KD {
   int pC;
   byte A;
   byte kS;
   short wS;
   int UT;

   public static KD pC(ByteBuffer var0) {
      KD var1 = new KD();
      var1.pC = var0.getInt();
      var1.A = var0.get();
      var1.kS = var0.get();
      var1.wS = var0.getShort();
      var1.UT = var0.getInt();
      return var1;
   }
}
