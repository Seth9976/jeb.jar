package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

public class nI {
   public static final nI q = new nI(null, 1, 0, null);
   private final String nf;
   private final int gP;
   private final int za;
   private final String lm;
   Integer RF;
   Integer xK;
   boolean Dw;
   Long Uv;
   Integer oW;
   boolean gO;

   public nI(String var1, int var2, int var3, String var4) {
      this.nf = var1;
      this.gP = var2;
      this.za = var3;
      this.lm = var4;
   }

   public nI(String var1, int var2, int var3, String var4, boolean var5) {
      this(var1, var2, var3, var4);
      this.Dw = var5;
   }

   public void q(long var1) {
      this.Uv = var1;
   }

   public void q(boolean var1) {
      this.gO = var1;
   }

   public void q(int var1) {
      this.oW = var1;
   }

   public String q() {
      return this.nf;
   }

   public int RF() {
      return this.gP;
   }

   public int xK() {
      return this.za;
   }

   public String Dw() {
      return this.lm;
   }

   public Integer Uv() {
      return this.RF;
   }

   public Integer oW() {
      return this.xK;
   }
}
