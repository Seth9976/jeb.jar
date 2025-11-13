package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Xv
public abstract class hZ implements vv {
   public static final int q = 20;

   public abstract int q();

   public abstract int RF();

   public abstract int xK();

   public abstract Gn Dw();

   public abstract zW Uv();

   public final String gO() {
      return this.q(this.q());
   }

   public final String nf() {
      return this.q(this.RF());
   }

   public final String gP() {
      return this.q(this.xK());
   }

   public static hZ q(ByteBuffer var0, zW var1) {
      int var2 = var0.getInt();
      int var3 = var0.getInt();
      int var4 = var0.getInt();
      Gn var5 = Gn.q(var0);
      return new hZ.eo(var2, var3, var4, var5, var1);
   }

   private String q(int var1) {
      return this.Uv().RF(var1);
   }

   @Override
   public byte[] oW() {
      return this.q(false);
   }

   @Override
   public byte[] q(boolean var1) {
      ByteBuffer var2 = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN);
      var2.putInt(this.q());
      var2.putInt(this.RF());
      var2.putInt(this.xK());
      var2.put(this.Dw().q(var1));
      return var2.array();
   }

   static class eo extends hZ {
      int RF;
      int xK;
      int Dw;
      Gn Uv;
      zW oW;

      public eo(int var1, int var2, int var3, Gn var4, zW var5) {
         this.RF = var1;
         this.xK = var2;
         this.Dw = var3;
         this.Uv = var4;
         this.oW = var5;
      }

      @Override
      public int q() {
         return this.RF;
      }

      @Override
      public int RF() {
         return this.xK;
      }

      @Override
      public int xK() {
         return this.Dw;
      }

      @Override
      public Gn Dw() {
         return this.Uv;
      }

      @Override
      public zW Uv() {
         return this.oW;
      }
   }
}
