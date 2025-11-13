package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Vj implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   byte[] xK = new byte[16];
   @SerId(4)
   long Dw;
   @SerId(5)
   long Uv;
   @SerId(6)
   long oW;
   @SerId(7)
   long gO;
   @SerId(8)
   int nf;
   @SerId(9)
   int gP;
   @SerId(10)
   int za;
   @SerId(11)
   int lm;

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static Vj q(ByteBuffer var0) {
      Vj var1 = new Vj();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var0.get(var1.xK);
      var1.Dw = var0.getLong();
      var1.Uv = var0.getLong();
      var1.oW = var0.getLong();
      var1.gO = var0.getLong();
      var1.nf = var0.getInt();
      var1.gP = var0.getInt();
      var1.za = var0.getInt();
      var1.lm = var0.getInt();
      return var1;
   }
}
