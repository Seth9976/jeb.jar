package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class p implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   long[] UT = new long[29];
   @SerId(6)
   long E;
   @SerId(7)
   long sY;
   @SerId(8)
   long ys;
   @SerId(9)
   long ld;
   @SerId(10)
   int gp;

   public static p pC(ByteBuffer var0) {
      p var1 = new p();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getInt();
      var1.wS = var0.getInt();

      for (int var2 = 0; var2 < 29; var2++) {
         var1.UT[var2] = var0.getLong();
      }

      var1.E = var0.getLong();
      var1.sY = var0.getLong();
      var1.ys = var0.getLong();
      var1.ld = var0.getLong();
      var1.gp = var0.getInt();
      return var1;
   }
}
