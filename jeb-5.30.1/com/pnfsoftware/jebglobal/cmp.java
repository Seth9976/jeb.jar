package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

public class cmp {
   static final clt q = new clt(131329);
   static final clt RF = new clt(257);
   static final clt xK = new clt(655617);
   static final clt Dw = new clt(196865);
   static final clt Uv = new clt(65793);
   static final clt oW = new clt(721153);
   static final clt gO = new clt(459009);
   public static final cmf nf = cmf.q("J", q, null);
   public static final cmf gP = cmf.q("JAL", Dw, null);
   public static final cmf za = cmf.q("JALX", gO, cmf.RF);
   public static final cmf lm = new cmf("B", q, clx.RF);
   public static final cmf zz = new cmf("BC", RF, clx.oW);
   public static final cmf JY = new cmf("BALC", Uv, clx.oW);
   private static final clw[] sH = new clw[]{clz.q, clz.xK, clx.RF};
   private static final clw[] CE = new clw[]{clz.xK, clx.RF};
   public static final cmf HF = new cmf("BEQ", q, cmb.RF, null, sH);
   public static final cmf LK = new cmf("BNE", q, cmb.xK, null, sH);
   public static final cmf io = new cmf("BEQL", xK, cmb.RF, cmf.nf, sH);
   public static final cmf qa = new cmf("BNEL", xK, cmb.xK, cmf.nf, sH);
   static final clw[] Hk = new clw[]{clz.q, clx.RF};
   public static final cmf Me = new cmf("BEQZ", q, cmb.RF, null, Hk);
   public static final cmf PV = new cmf("BNEZ", q, cmb.xK, null, Hk);
   public static final cmf oQ = new cmf("BLEZ", q, cmb.Uv, null, Hk);
   public static final cmf xW = new cmf("BGTZ", q, cmb.oW, null, Hk);
   public static final cmf KT = new cmf("BLEZL", xK, cmb.Uv, cmf.nf, Hk);
   public static final cmf Gf = new cmf("BGTZL", xK, cmb.oW, cmf.nf, Hk);
   private static final cmf wF = new cmf("BLEZALC", Uv, cmb.Uv, cmf.xK, CE);
   private static final cmf If = new cmf("BGEZALC", Uv, cmb.gO, cmf.xK, CE);
   private static final cmf Dz = new cmf("BGEUC", RF, cmb.lm, cmf.xK, sH);
   private static final cmf mI = new cmf("BGTZALC", Uv, cmb.oW, cmf.xK, CE);
   private static final cmf jq = new cmf("BLTZALC", Uv, cmb.Dw, cmf.xK, CE);
   private static final cmf ui = new cmf("BLTUC", RF, cmb.nf, cmf.xK, sH);
   private static final cmf TX = new cmf("BEQZALC", Uv, cmb.RF, cmf.xK, CE);
   private static final cmf Rr = new cmf("BEQC", RF, cmb.RF, cmf.xK, sH);
   private static final cmf EB = new cmf("BOVC", RF, cmb.zz, cmf.xK, sH);
   private static final cmf Xo = new cmf("BLEZC", RF, cmb.Uv, cmf.xK, CE);
   private static final cmf Bu = new cmf("BGEZC", RF, cmb.gO, cmf.xK, CE);
   private static final cmf IN = new cmf("BGEC", RF, cmb.gO, cmf.xK, sH);
   private static final cmf rL = new cmf("BGTZC", RF, cmb.oW, cmf.xK, CE);
   private static final cmf eJ = new cmf("BLTZC", RF, cmb.Dw, cmf.xK, CE);
   private static final cmf YN = new cmf("BLTC", RF, cmb.Dw, cmf.xK, sH);
   private static final cmf Rv = new cmf("BNEZALC", Uv, cmb.xK, cmf.xK, CE);
   private static final cmf zx = new cmf("BNEC", RF, cmb.xK, cmf.xK, sH);
   private static final cmf ZT = new cmf("BNVC", RF, cmb.JY, cmf.xK, sH);
   private static final clw[] Ri = new clw[]{clz.q, clx.Uv};
   static final clt Ef = new clt(513);
   private static final cmf GY = new cmf("JIC", Ef, cmf.xK, clz.xK, clx.gP);
   private static final cmf Wx = new cmf("JRC", Ef, cmf.xK, clz.xK);
   private static final cmf AB = new cmf("BEQZC", RF, cmb.RF, cmf.xK, Ri);
   static final clt cC = new clt(66049);
   private static final cmf CY = new cmf("JIALC", cC, cmf.xK, clz.xK, clx.gP);
   private static final cmf WI = new cmf("JALRC", cC, cmf.xK, clz.xK);
   private static final cmf Tq = new cmf("BNEZC", RF, cmb.xK, cmf.xK, Ri);

   public static cmf q(int var0, int var1) {
      if (var0 == 0) {
         return wF;
      } else {
         return var0 == var1 ? If : Dz;
      }
   }

   public static cmf RF(int var0, int var1) {
      if (var0 == 0) {
         return mI;
      } else {
         return var0 == var1 ? jq : ui;
      }
   }

   public static cmf xK(int var0, int var1) {
      if (var0 == 0 && var1 != 0) {
         return TX;
      } else if (var0 != 0 && var1 != 0 && var0 < var1) {
         return Rr;
      } else {
         return var0 >= var1 ? EB : null;
      }
   }

   public static cmf Dw(int var0, int var1) {
      if (var0 == 0) {
         return Xo;
      } else {
         return var0 == var1 ? Bu : IN;
      }
   }

   public static cmf Uv(int var0, int var1) {
      if (var0 == 0) {
         return rL;
      } else {
         return var0 == var1 ? eJ : YN;
      }
   }

   public static cmf oW(int var0, int var1) {
      if (var0 == 0 && var1 != 0) {
         return Rv;
      } else {
         return var0 < var1 ? zx : ZT;
      }
   }

   public static cmf q(byte[] var0, int var1) {
      if (var1 == 0) {
         return DirectEncodedMemoryArea.get(0, 16).decodeInt(var0) == 0 ? Wx : GY;
      } else {
         return AB;
      }
   }

   public static cmf RF(byte[] var0, int var1) {
      if (var1 == 0) {
         return DirectEncodedMemoryArea.get(0, 16).decodeInt(var0) == 0 ? WI : CY;
      } else {
         return Tq;
      }
   }
}
