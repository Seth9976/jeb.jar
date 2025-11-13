package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class GK implements Ws {
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
   @SerId(9)
   int ld;
   @SerId(10)
   int gp;
   @SerId(11)
   int oT;
   @SerId(12)
   int fI;
   @SerId(13)
   int WR;
   @SerId(14)
   int NS;
   @SerId(15)
   int vP;
   @SerId(16)
   int xC;
   @SerId(17)
   int ED;
   @SerId(18)
   int Sc;
   @SerId(19)
   int ah;
   @SerId(20)
   int eP;

   public static GK pC(ByteBuffer var0) {
      GK var1 = new GK();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getInt();
      var1.wS = var0.getInt();
      var1.UT = var0.getInt();
      var1.E = var0.getInt();
      var1.sY = var0.getInt();
      var1.ys = var0.getInt();
      var1.ld = var0.getInt();
      var1.gp = var0.getInt();
      var1.oT = var0.getInt();
      var1.fI = var0.getInt();
      var1.WR = var0.getInt();
      var1.NS = var0.getInt();
      var1.vP = var0.getInt();
      var1.xC = var0.getInt();
      var1.ED = var0.getInt();
      var1.Sc = var0.getInt();
      var1.ah = var0.getInt();
      var1.eP = var0.getInt();
      return var1;
   }
}
