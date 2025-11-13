package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class ckg {
   public int q;
   public int RF;

   public static ckg q(ByteBuffer var0) {
      ckg var1 = new ckg();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      return var1;
   }
}
