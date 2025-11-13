package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class bK implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   int[] Uv = new int[13];
   @SerId(6)
   int oW;
   @SerId(7)
   int gO;
   @SerId(8)
   int nf;
   @SerId(9)
   int gP;

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static bK q(ByteBuffer var0) {
      bK var1 = new bK();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getInt();
      var1.Dw = var0.getInt();

      for (int var2 = 0; var2 < 13; var2++) {
         var1.Uv[var2] = var0.getInt();
      }

      var1.oW = var0.getInt();
      var1.gO = var0.getInt();
      var1.nf = var0.getInt();
      var1.gP = var0.getInt();
      return var1;
   }
}
