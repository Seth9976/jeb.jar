package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class HE implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   byte[] kS = new byte[16];
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
   @SerId(9)
   int ld;
   @SerId(10)
   int gp;
   @SerId(11)
   int oT;

   public static HE pC(ByteBuffer var0) {
      HE var1 = new HE();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var0.get(var1.kS);
      var1.wS = var0.getInt();
      var1.UT = var0.getInt();
      var1.E = var0.getInt();
      var1.sY = var0.getInt();
      var1.ys = var0.getInt();
      var1.ld = var0.getInt();
      var1.gp = var0.getInt();
      var1.oT = var0.getInt();
      return var1;
   }
}
