package com.pnfsoftware.jebglobal;

public class qD {
   private int q;
   private int RF;
   private int xK;
   private int Dw;
   private int Uv;

   public qD(int var1, int var2, int var3, int var4, int var5) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
   }

   public wc q() {
      return new wc(this.q, this.RF, this.xK, this.Dw, this.Uv);
   }

   public Hv q(byte[] var1) {
      return new Hv(var1, this.q, this.RF, this.xK, this.Dw, this.Uv);
   }
}
