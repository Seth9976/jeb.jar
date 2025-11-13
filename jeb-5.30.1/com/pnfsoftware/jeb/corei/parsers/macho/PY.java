package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class PY implements oM {
   @SerId(1)
   byte[] q = new byte[16];
   @SerId(2)
   byte[] RF = new byte[16];
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

   public static PY q(ByteBuffer var0) {
      PY var1 = new PY();
      var0.get(var1.q);
      var0.get(var1.RF);
      var1.xK = var0.getInt();
      var1.Dw = var0.getInt();
      var1.Uv = var0.getInt();
      var1.oW = var0.getInt();
      var1.gO = var0.getInt();
      var1.nf = var0.getInt();
      var1.gP = var0.getInt();
      var1.za = var0.getInt();
      var1.lm = var0.getInt();
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
      return this.xK & 4294967295L;
   }

   @Override
   public long zz() {
      return this.Dw & 4294967295L;
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
      return 0;
   }
}
