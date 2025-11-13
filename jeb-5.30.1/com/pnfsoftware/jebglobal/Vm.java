package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class Vm {
   public byte q;
   public int RF;
   public int xK;
   public long Dw;
   public long Uv;
   public String oW;
   public oG gO;
   public boolean nf;
   public boolean gP;
   public long za;
   public int lm;
   public int zz;
   public long JY;
   public String HF;

   private Vm(byte var1) {
      this.q = var1;
   }

   public static Vm q(int var0) {
      Vm var1 = new Vm((byte)1);
      var1.RF = var0;
      return var1;
   }

   public static Vm q(long var0) {
      Vm var2 = new Vm((byte)3);
      var2.Dw = var0;
      return var2;
   }

   public static Vm RF(long var0) {
      Vm var2 = new Vm((byte)4);
      var2.Uv = var0;
      return var2;
   }

   public static Vm q(String var0) {
      Vm var1 = new Vm((byte)5);
      var1.oW = var0;
      return var1;
   }

   public static Vm RF(String var0) {
      Vm var1 = new Vm((byte)6);
      var1.oW = var0;
      return var1;
   }

   public static Vm q(oG var0) {
      oG.q(var0);
      Vm var1 = new Vm((byte)7);
      var1.gO = var0;
      return var1;
   }

   public static Vm q(long var0, boolean var2, boolean var3) {
      Vm var4 = new Vm((byte)8);
      var4.Uv = var0;
      var4.nf = var2;
      var4.gP = var3;
      return var4;
   }

   public static Vm q(long var0, long var2) {
      Vm var4 = new Vm((byte)9);
      var4.Uv = var0;
      var4.za = var2;
      return var4;
   }

   public static Vm q(long var0, int var2, int var3) {
      sv.q(var2);
      eB.q(var3);
      Vm var4 = new Vm((byte)10);
      var4.Dw = var0;
      var4.lm = var2;
      var4.zz = var3;
      return var4;
   }

   public static Vm xK(long var0) {
      Vm var2 = new Vm((byte)11);
      var2.JY = var0;
      return var2;
   }

   @Override
   public String toString() {
      return Strings.ff("kind=%d", this.q);
   }
}
