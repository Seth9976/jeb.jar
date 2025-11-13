package com.pnfsoftware.jeb.corei.parsers.macho;

import java.nio.ByteBuffer;

public class yt {
   int pC;
   byte A;
   byte kS;
   short wS;
   long UT;

   public static yt pC(ByteBuffer var0) {
      yt var1 = new yt();
      var1.pC = var0.getInt();
      var1.A = var0.get();
      var1.kS = var0.get();
      var1.wS = var0.getShort();
      var1.UT = var0.getLong();
      return var1;
   }
}
