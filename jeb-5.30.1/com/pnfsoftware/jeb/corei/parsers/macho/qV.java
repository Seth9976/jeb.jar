package com.pnfsoftware.jeb.corei.parsers.macho;

import java.nio.ByteBuffer;

public class qV {
   int q;
   byte RF;
   byte xK;
   short Dw;
   int Uv;

   public static qV q(ByteBuffer var0) {
      qV var1 = new qV();
      var1.q = var0.getInt();
      var1.RF = var0.get();
      var1.xK = var0.get();
      var1.Dw = var0.getShort();
      var1.Uv = var0.getInt();
      return var1;
   }
}
