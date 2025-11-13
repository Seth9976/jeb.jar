package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class tl implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   long[] Uv = new long[29];
   @SerId(6)
   long oW;
   @SerId(7)
   long gO;
   @SerId(8)
   long nf;
   @SerId(9)
   long gP;
   @SerId(10)
   int za;

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static tl q(ByteBuffer var0) {
      tl var1 = new tl();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getInt();
      var1.Dw = var0.getInt();

      for (int var2 = 0; var2 < 29; var2++) {
         var1.Uv[var2] = var0.getLong();
      }

      var1.oW = var0.getLong();
      var1.gO = var0.getLong();
      var1.nf = var0.getLong();
      var1.gP = var0.getLong();
      var1.za = var0.getInt();
      return var1;
   }
}
