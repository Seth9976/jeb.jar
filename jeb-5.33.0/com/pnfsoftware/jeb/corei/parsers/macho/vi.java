package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class vi implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   int[] UT = new int[13];
   @SerId(6)
   int E;
   @SerId(7)
   int sY;
   @SerId(8)
   int ys;
   @SerId(9)
   int ld;

   public static vi pC(ByteBuffer var0) {
      vi var1 = new vi();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getInt();
      var1.wS = var0.getInt();

      for (int var2 = 0; var2 < 13; var2++) {
         var1.UT[var2] = var0.getInt();
      }

      var1.E = var0.getInt();
      var1.sY = var0.getInt();
      var1.ys = var0.getInt();
      var1.ld = var0.getInt();
      return var1;
   }
}
