package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class tw {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   int Uv;
   @SerId(6)
   int oW;
   @SerId(7)
   int gO;
   @SerId(8)
   int nf;

   public static tw q(ByteBuffer var0, boolean var1) {
      tw var2 = new tw();
      var2.q = var0.getInt();
      var2.RF = var0.getInt();
      var2.xK = var0.getInt();
      var2.Dw = var0.getInt();
      var2.Uv = var0.getInt();
      var2.oW = var0.getInt();
      var2.gO = var0.getInt();
      if (var1) {
         var2.nf = var0.getInt();
      }

      return var2;
   }
}
