package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

public class cgc {
   static final cfh pC = new cfh(131329);
   static final cfh A = new cfh(257);
   static final cfh kS = new cfh(655617);
   static final cfh wS = new cfh(196865);
   static final cfh UT = new cfh(65793);
   static final cfh E = new cfh(721153);
   static final cfh sY = new cfh(459009);
   public static final cfs ys = cfs.pC("J", pC, null);
   public static final cfs ld = cfs.pC("JAL", wS, null);
   public static final cfs gp = cfs.pC("JALX", sY, cfs.A);
   public static final cfs oT = new cfs("B", pC, cfl.A);
   public static final cfs fI = new cfs("BC", A, cfl.E);
   public static final cfs WR = new cfs("BALC", UT, cfl.E);
   private static final cfk[] Er = new cfk[]{cfn.pC, cfn.kS, cfl.A};
   private static final cfk[] FE = new cfk[]{cfn.kS, cfl.A};
   public static final cfs NS = new cfs("BEQ", pC, cfo.A, null, Er);
   public static final cfs vP = new cfs("BNE", pC, cfo.kS, null, Er);
   public static final cfs xC = new cfs("BEQL", kS, cfo.A, cfs.ys, Er);
   public static final cfs ED = new cfs("BNEL", kS, cfo.kS, cfs.ys, Er);
   static final cfk[] Sc = new cfk[]{cfn.pC, cfl.A};
   public static final cfs ah = new cfs("BEQZ", pC, cfo.A, null, Sc);
   public static final cfs eP = new cfs("BNEZ", pC, cfo.kS, null, Sc);
   public static final cfs UO = new cfs("BLEZ", pC, cfo.UT, null, Sc);
   public static final cfs Ab = new cfs("BGTZ", pC, cfo.E, null, Sc);
   public static final cfs rl = new cfs("BLEZL", kS, cfo.UT, cfs.ys, Sc);
   public static final cfs z = new cfs("BGTZL", kS, cfo.E, cfs.ys, Sc);
   private static final cfs Aj = new cfs("BLEZALC", UT, cfo.UT, cfs.kS, FE);
   private static final cfs EX = new cfs("BGEZALC", UT, cfo.sY, cfs.kS, FE);
   private static final cfs LM = new cfs("BGEUC", A, cfo.oT, cfs.kS, Er);
   private static final cfs mv = new cfs("BGTZALC", UT, cfo.E, cfs.kS, FE);
   private static final cfs sO = new cfs("BLTZALC", UT, cfo.wS, cfs.kS, FE);
   private static final cfs os = new cfs("BLTUC", A, cfo.ys, cfs.kS, Er);
   private static final cfs Cu = new cfs("BEQZALC", UT, cfo.A, cfs.kS, FE);
   private static final cfs hZ = new cfs("BEQC", A, cfo.A, cfs.kS, Er);
   private static final cfs UW = new cfs("BOVC", A, cfo.fI, cfs.kS, Er);
   private static final cfs PR = new cfs("BLEZC", A, cfo.UT, cfs.kS, FE);
   private static final cfs cX = new cfs("BGEZC", A, cfo.sY, cfs.kS, FE);
   private static final cfs DQ = new cfs("BGEC", A, cfo.sY, cfs.kS, Er);
   private static final cfs ZN = new cfs("BGTZC", A, cfo.E, cfs.kS, FE);
   private static final cfs OB = new cfs("BLTZC", A, cfo.wS, cfs.kS, FE);
   private static final cfs pF = new cfs("BLTC", A, cfo.wS, cfs.kS, Er);
   private static final cfs Bc = new cfs("BNEZALC", UT, cfo.kS, cfs.kS, FE);
   private static final cfs OI = new cfs("BNEC", A, cfo.kS, cfs.kS, Er);
   private static final cfs Bf = new cfs("BNVC", A, cfo.WR, cfs.kS, Er);
   private static final cfk[] Pe = new cfk[]{cfn.pC, cfl.UT};
   static final cfh Ek = new cfh(513);
   private static final cfs ck = new cfs("JIC", Ek, cfs.kS, cfn.kS, cfl.ld);
   private static final cfs RW = new cfs("JRC", Ek, cfs.kS, cfn.kS);
   private static final cfs e = new cfs("BEQZC", A, cfo.A, cfs.kS, Pe);
   static final cfh hK = new cfh(66049);
   private static final cfs xM = new cfs("JIALC", hK, cfs.kS, cfn.kS, cfl.ld);
   private static final cfs kU = new cfs("JALRC", hK, cfs.kS, cfn.kS);
   private static final cfs Kq = new cfs("BNEZC", A, cfo.kS, cfs.kS, Pe);

   public static cfs pC(int var0, int var1) {
      if (var0 == 0) {
         return Aj;
      } else {
         return var0 == var1 ? EX : LM;
      }
   }

   public static cfs A(int var0, int var1) {
      if (var0 == 0) {
         return mv;
      } else {
         return var0 == var1 ? sO : os;
      }
   }

   public static cfs kS(int var0, int var1) {
      if (var0 == 0 && var1 != 0) {
         return Cu;
      } else if (var0 != 0 && var1 != 0 && var0 < var1) {
         return hZ;
      } else {
         return var0 >= var1 ? UW : null;
      }
   }

   public static cfs wS(int var0, int var1) {
      if (var0 == 0) {
         return PR;
      } else {
         return var0 == var1 ? cX : DQ;
      }
   }

   public static cfs UT(int var0, int var1) {
      if (var0 == 0) {
         return ZN;
      } else {
         return var0 == var1 ? OB : pF;
      }
   }

   public static cfs E(int var0, int var1) {
      if (var0 == 0 && var1 != 0) {
         return Bc;
      } else {
         return var0 < var1 ? OI : Bf;
      }
   }

   public static cfs pC(byte[] var0, int var1) {
      if (var1 == 0) {
         return DirectEncodedMemoryArea.get(0, 16).decodeInt(var0) == 0 ? RW : ck;
      } else {
         return e;
      }
   }

   public static cfs A(byte[] var0, int var1) {
      if (var1 == 0) {
         return DirectEncodedMemoryArea.get(0, 16).decodeInt(var0) == 0 ? kU : xM;
      } else {
         return Kq;
      }
   }
}
