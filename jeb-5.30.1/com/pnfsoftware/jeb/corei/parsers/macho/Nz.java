package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Nz implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
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
   @SerId(12)
   int zz;
   @SerId(13)
   int JY;
   @SerId(14)
   int HF;
   @SerId(15)
   int LK;
   @SerId(16)
   int io;
   @SerId(17)
   int qa;
   @SerId(18)
   int Hk;
   @SerId(19)
   int Me;
   @SerId(20)
   int PV;

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static Nz q(ByteBuffer var0) {
      Nz var1 = new Nz();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getInt();
      var1.Dw = var0.getInt();
      var1.Uv = var0.getInt();
      var1.oW = var0.getInt();
      var1.gO = var0.getInt();
      var1.nf = var0.getInt();
      var1.gP = var0.getInt();
      var1.za = var0.getInt();
      var1.lm = var0.getInt();
      var1.zz = var0.getInt();
      var1.JY = var0.getInt();
      var1.HF = var0.getInt();
      var1.LK = var0.getInt();
      var1.io = var0.getInt();
      var1.qa = var0.getInt();
      var1.Hk = var0.getInt();
      var1.Me = var0.getInt();
      var1.PV = var0.getInt();
      return var1;
   }
}
