package com.pnfsoftware.jeb.corei.parsers.macho;

import java.nio.ByteBuffer;

public class vn {
   int q;
   byte RF;
   byte xK;
   short Dw;
   long Uv;

   public static vn q(ByteBuffer var0) {
      vn var1 = new vn();
      var1.q = var0.getInt();
      var1.RF = var0.get();
      var1.xK = var0.get();
      var1.Dw = var0.getShort();
      var1.Uv = var0.getLong();
      return var1;
   }
}
