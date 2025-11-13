package com.pnfsoftware.jeb.corei.parsers.machofat;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class eo {
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

   public static eo q(ByteBuffer var0) {
      eo var1 = new eo();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getInt();
      var1.Dw = var0.getInt();
      var1.Uv = var0.getInt();
      return var1;
   }
}
