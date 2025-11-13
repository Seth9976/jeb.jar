package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class cD {
   public static String pC(ByteBuffer var0, int var1) {
      Charset var2 = Charset.forName("UTF-16LE");
      String var3 = new String(var0.array(), var1, 256, var2);
      var0.position(var1 + 256);
      return var3;
   }

   public static void pC(ByteBuffer var0, String var1) {
      var0.put(var1.getBytes(Charset.forName("UTF-16LE")), 0, 256);
   }
}
