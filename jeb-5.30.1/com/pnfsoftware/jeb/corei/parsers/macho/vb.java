package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class vb implements oM {
   @SerId(1)
   byte[] q = new byte[16];
   @SerId(2)
   byte[] RF = new byte[16];
   @SerId(3)
   long xK;
   @SerId(4)
   long Dw;
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

   public static vb q(ByteBuffer var0) {
      vb var1 = new vb();
      var0.get(var1.q);
      var0.get(var1.RF);
      var1.xK = var0.getLong();
      var1.Dw = var0.getLong();
      var1.Uv = var0.getInt();
      var1.oW = var0.getInt();
      var1.gO = var0.getInt();
      var1.nf = var0.getInt();
      var1.gP = var0.getInt();
      var1.za = var0.getInt();
      var1.lm = var0.getInt();
      var1.zz = var0.getInt();
      return var1;
   }

   @Override
   public byte[] q() {
      return this.q;
   }

   @Override
   public byte[] RF() {
      return this.RF;
   }

   @Override
   public long lm() {
      return this.xK;
   }

   @Override
   public long zz() {
      return this.Dw;
   }

   @Override
   public int oW() {
      return this.Uv;
   }

   @Override
   public int xK() {
      return this.oW;
   }

   @Override
   public int gO() {
      return this.gO;
   }

   @Override
   public int Uv() {
      return this.nf;
   }

   @Override
   public int Dw() {
      return this.gP;
   }

   @Override
   public int nf() {
      return this.za;
   }

   @Override
   public int gP() {
      return this.lm;
   }

   @Override
   public int za() {
      return this.zz;
   }
}
