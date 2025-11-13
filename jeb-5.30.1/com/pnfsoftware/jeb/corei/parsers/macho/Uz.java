package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Uz implements ej {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   long Uv;
   @SerId(6)
   long oW;
   @SerId(7)
   long gO;
   @SerId(8)
   long nf;
   @SerId(9)
   long gP;
   @SerId(10)
   long za;
   @SerId(11)
   long lm;
   @SerId(12)
   long zz;
   @SerId(13)
   long JY;
   @SerId(14)
   long HF;
   @SerId(15)
   long LK;
   @SerId(16)
   long io;
   @SerId(17)
   long qa;
   @SerId(18)
   long Hk;
   @SerId(19)
   long Me;
   @SerId(20)
   long PV;
   @SerId(21)
   long oQ;
   @SerId(22)
   long xW;
   @SerId(23)
   long KT;
   @SerId(24)
   long Gf;
   @SerId(25)
   long Ef;

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   public static Uz q(ByteBuffer var0) {
      Uz var1 = new Uz();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getInt();
      var1.Dw = var0.getInt();
      var1.Uv = var0.getLong();
      var1.oW = var0.getLong();
      var1.gO = var0.getLong();
      var1.nf = var0.getLong();
      var1.gP = var0.getLong();
      var1.za = var0.getLong();
      var1.lm = var0.getLong();
      var1.zz = var0.getLong();
      var1.JY = var0.getLong();
      var1.HF = var0.getLong();
      var1.LK = var0.getLong();
      var1.io = var0.getLong();
      var1.qa = var0.getLong();
      var1.Hk = var0.getLong();
      var1.Me = var0.getLong();
      var1.PV = var0.getLong();
      var1.oQ = var0.getLong();
      var1.xW = var0.getLong();
      var1.KT = var0.getLong();
      var1.Gf = var0.getLong();
      var1.Ef = var0.getLong();
      return var1;
   }
}
