package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class oL implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   byte[] xK = new byte[16];
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

   public static oL q(ByteBuffer var0) {
      oL var1 = new oL();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var0.get(var1.xK);
      var1.Dw = var0.getInt();
      var1.Uv = var0.getInt();
      var1.oW = var0.getInt();
      var1.gO = var0.getInt();
      var1.nf = var0.getInt();
      var1.gP = var0.getInt();
      var1.za = var0.getInt();
      var1.lm = var0.getInt();
      return var1;
   }
}
