package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class nI implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   long xK;
   @SerId(4)
   long Dw;

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static nI q(ByteBuffer var0) {
      nI var1 = new nI();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getLong();
      var1.Dw = var0.getLong();
      return var1;
   }
}
