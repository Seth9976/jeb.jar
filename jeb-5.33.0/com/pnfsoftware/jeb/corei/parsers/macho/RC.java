package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class RC implements bO {
   @SerId(1)
   byte[] pC = new byte[16];
   @SerId(2)
   byte[] A = new byte[16];
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

   public static RC pC(ByteBuffer var0) {
      RC var1 = new RC();
      var0.get(var1.pC);
      var0.get(var1.A);
      var1.kS = var0.getInt();
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

   @Override
   public byte[] pC() {
      return this.pC;
   }

   @Override
   public long UT() {
      return this.kS & 4294967295L;
   }

   @Override
   public long E() {
      return this.wS & 4294967295L;
   }

   @Override
   public int kS() {
      return this.UT;
   }

   @Override
   public int A() {
      return this.ld;
   }

   @Override
   public int wS() {
      return this.gp;
   }
}
