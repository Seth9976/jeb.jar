package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Mh implements Ws {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   long UT;
   @SerId(6)
   long E;
   @SerId(7)
   long sY;
   @SerId(8)
   long ys;
   @SerId(9)
   long ld;
   @SerId(10)
   long gp;
   @SerId(11)
   long oT;
   @SerId(12)
   long fI;
   @SerId(13)
   long WR;
   @SerId(14)
   long NS;
   @SerId(15)
   long vP;
   @SerId(16)
   long xC;
   @SerId(17)
   long ED;
   @SerId(18)
   long Sc;
   @SerId(19)
   long ah;
   @SerId(20)
   long eP;
   @SerId(21)
   long UO;
   @SerId(22)
   long Ab;
   @SerId(23)
   long rl;
   @SerId(24)
   long z;
   @SerId(25)
   long Ek;

   public static Mh pC(ByteBuffer var0) {
      Mh var1 = new Mh();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getInt();
      var1.wS = var0.getInt();
      var1.UT = var0.getLong();
      var1.E = var0.getLong();
      var1.sY = var0.getLong();
      var1.ys = var0.getLong();
      var1.ld = var0.getLong();
      var1.gp = var0.getLong();
      var1.oT = var0.getLong();
      var1.fI = var0.getLong();
      var1.WR = var0.getLong();
      var1.NS = var0.getLong();
      var1.vP = var0.getLong();
      var1.xC = var0.getLong();
      var1.ED = var0.getLong();
      var1.Sc = var0.getLong();
      var1.ah = var0.getLong();
      var1.eP = var0.getLong();
      var1.UO = var0.getLong();
      var1.Ab = var0.getLong();
      var1.rl = var0.getLong();
      var1.z = var0.getLong();
      var1.Ek = var0.getLong();
      return var1;
   }
}
