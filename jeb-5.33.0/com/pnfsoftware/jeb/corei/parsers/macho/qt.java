package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class qt implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   byte[] kS = new byte[16];
   @SerId(4)
   long wS;
   @SerId(5)
   long UT;
   @SerId(6)
   long E;
   @SerId(7)
   long sY;
   @SerId(8)
   int ys;
   @SerId(9)
   int ld;
   @SerId(10)
   int gp;
   @SerId(11)
   int oT;

   public static qt pC(ByteBuffer var0) {
      qt var1 = new qt();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var0.get(var1.kS);
      var1.wS = var0.getLong();
      var1.UT = var0.getLong();
      var1.E = var0.getLong();
      var1.sY = var0.getLong();
      var1.ys = var0.getInt();
      var1.ld = var0.getInt();
      var1.gp = var0.getInt();
      var1.oT = var0.getInt();
      return var1;
   }
}
