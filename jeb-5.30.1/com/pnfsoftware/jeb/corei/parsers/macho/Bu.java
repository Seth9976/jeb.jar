package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Bu implements ej {
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

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static Bu q(ByteBuffer var0) {
      Bu var1 = new Bu();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getInt();
      var1.Dw = var0.getInt();
      var1.Uv = var0.getInt();
      var1.oW = var0.getInt();
      return var1;
   }
}
