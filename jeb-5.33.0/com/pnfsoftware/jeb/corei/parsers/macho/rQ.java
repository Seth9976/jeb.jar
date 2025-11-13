package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class rQ {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   int UT;
   @SerId(6)
   int E;
   @SerId(7)
   int sY;
   @SerId(8)
   int ys;

   public static rQ pC(ByteBuffer var0, boolean var1) {
      rQ var2 = new rQ();
      var2.pC = var0.getInt();
      var2.A = var0.getInt();
      var2.kS = var0.getInt();
      var2.wS = var0.getInt();
      var2.UT = var0.getInt();
      var2.E = var0.getInt();
      var2.sY = var0.getInt();
      if (var1) {
         var2.ys = var0.getInt();
      }

      return var2;
   }
}
