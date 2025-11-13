package com.pnfsoftware.jebglobal;

import java.security.MessageDigest;

public abstract class cjh extends MessageDigest implements cjg {
   private int q;
   private int RF;
   private int xK;
   private byte[] Dw;
   private byte[] Uv;
   private long oW;

   @Override
   protected abstract void engineReset();

   protected abstract void q(byte[] var1);

   protected abstract void q(byte[] var1, int var2);

   protected abstract void xK();

   public cjh(String var1) {
      super(var1);
      this.xK();
      this.q = this.engineGetDigestLength();
      this.RF = this.Dw();
      this.Dw = new byte[this.RF];
      this.Uv = new byte[this.q];
      this.xK = 0;
      this.oW = 0L;
   }

   private void nf() {
      if (this.q == 0) {
         this.q = this.engineGetDigestLength();
         this.Uv = new byte[this.q];
      }
   }

   @Override
   public byte[] digest() {
      this.nf();
      byte[] var1 = new byte[this.q];
      this.digest(var1, 0, this.q);
      return var1;
   }

   @Override
   public byte[] digest(byte[] var1) {
      this.update(var1, 0, var1.length);
      return this.digest();
   }

   @Override
   public int digest(byte[] var1, int var2, int var3) {
      this.nf();
      if (var3 >= this.q) {
         this.q(var1, var2);
         this.reset();
         return this.q;
      } else {
         this.q(this.Uv, 0);
         System.arraycopy(this.Uv, 0, var1, var2, var3);
         this.reset();
         return var3;
      }
   }

   @Override
   public void reset() {
      this.engineReset();
      this.xK = 0;
      this.oW = 0L;
   }

   @Override
   public void update(byte var1) {
      this.Dw[this.xK++] = var1;
      if (this.xK == this.RF) {
         this.q(this.Dw);
         this.oW++;
         this.xK = 0;
      }
   }

   @Override
   public void update(byte[] var1) {
      this.update(var1, 0, var1.length);
   }

   @Override
   public void update(byte[] var1, int var2, int var3) {
      while (var3 > 0) {
         int var4 = this.RF - this.xK;
         if (var4 > var3) {
            var4 = var3;
         }

         System.arraycopy(var1, var2, this.Dw, this.xK, var4);
         var2 += var4;
         this.xK += var4;
         var3 -= var4;
         if (this.xK == this.RF) {
            this.q(this.Dw);
            this.oW++;
            this.xK = 0;
         }
      }
   }

   protected int Dw() {
      return this.RF();
   }

   protected final int Uv() {
      return this.xK;
   }

   protected final byte[] oW() {
      return this.Dw;
   }

   protected long gO() {
      return this.oW;
   }

   protected cjg q(cjh var1) {
      var1.xK = this.xK;
      var1.oW = this.oW;
      System.arraycopy(this.Dw, 0, var1.Dw, 0, this.Dw.length);
      this.nf();
      var1.nf();
      System.arraycopy(this.Uv, 0, var1.Uv, 0, this.Uv.length);
      return var1;
   }
}
