package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class wZ {
   private static final String[] go = new String[]{"NONE", "f", "i", "if", "a", "af", "ai", "aif"};
   private static final Ag JF = new Ag(go, DirectEncodedMemoryArea.get(6, 3));
   private static final Ag Nq = new Ag(go, DirectEncodedMemoryArea.get(0, 3));
   private static final Ag pg = new Ag(go, DirectEncodedMemoryArea.get(5, 3));
   private static final String[] gj = new String[]{"LE", "BE"};
   public static final Ag pC = new Ag(gj, DirectEncodedMemoryArea.get(9, 1));
   public static final Ag A = new Ag(gj, DirectEncodedMemoryArea.get(3, 1));
   public static final String[] kS = new String[]{
      "S1E1R",
      "S1E1W",
      "S1E0R",
      "S1E0W",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "S1E2R",
      "S1E2W",
      null,
      null,
      "S12E1R",
      "S12E1W",
      "S12E0R",
      "S12E0W",
      "S1E3R",
      "S1E3W",
      null,
      null,
      null,
      null,
      null,
      null,
      "S1E1RP",
      "S1E1WP",
      "S1E1A",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "S1E2A",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "S1E3A",
      null,
      null,
      null,
      null,
      null
   };
   public static final String[][][] wS = new String[][][]{
      {
            null,
            null,
            null,
            null,
            null,
            null,
            {null, "IVAC", "ISW", "IGVAC", "IGSW", "IGDVAC", "IGDSW"},
            null,
            null,
            null,
            {null, null, "CSW", null, "CGSW", null, "CGDSW"},
            null,
            null,
            null,
            {null, null, "CISW", null, "CIGSW", null, "CIGDSW"},
            {null, "CIVAPS", null, null, null, "CIGDVAPS"}
      },
      null,
      null,
      {
            null,
            null,
            null,
            null,
            {null, "ZVA", null, "GVA", "GZVA"},
            null,
            null,
            null,
            null,
            null,
            {null, "CVAC", null, "CGVAC", null, "CGDVAC"},
            {"CVAOC", "CVAU", null, null, null, null, null, "CGDVAOC"},
            {null, "CVAP", null, "CGVAP", null, "CGDVAP"},
            {null, "CVADP", null, "CGVADP", null, "CGDVADP"},
            {null, "CIVAC", null, "CIGVAC", null, "CIGDVAC"},
            {"CIVAOC", null, null, null, null, null, null, "CIGDVAOC"}
      },
      {null, null, null, null, null, null, null, null, null, null, null, null, null, null, {"CIPAE", null, null, null, null, null, null, "CIGDPAE"}},
      null,
      {null, null, null, null, null, null, null, null, null, null, null, null, null, null, {null, "CIPAPA", null, null, null, "CIGDPAPA"}}
   };
   public static final String[] UT = new String[]{"IALLUIS", null, "IALLU", null, null, null, null, "IVAU"};
   public static final String[][][] E = new String[][][]{
      {
            null,
            {"VMALLE1OS", "VAE1OS", "ASIDE1OS", "VAAE1OS", null, "VALE1OS", null, "VAALE1OS"},
            {null, "RVAE1IS", null, "RVAAE1IS", null, "RVALE1IS", null, "RVAALE1IS"},
            {"VMALLE1IS", "VAE1IS", "ASIDE1IS", "VAAE1IS", null, "VALE1IS", null, "VAALE1IS"},
            null,
            {null, "RVAE1OS", null, "RVAAE1OS", null, "RVALE1OS", null, "RVAALE1OS"},
            {null, "RVAE1", null, "RVAAE1", null, "RVALE1", null, "RVAALE1"},
            {"VMALLE1", "VAE1", "ASIDE1", "VAAE1", null, "VALE1", null, "VAALE1"}
      },
      null,
      {
            {null, "IPAS2E1IS", "RIPAS2E1IS", null, null, "IPAS2LE1IS", "RIPAS2LE1IS", null},
            {"ALLE2OS", "VAE2OS", null, null, "ALLE1OS", "VALE2OS", "VMALLS12E1OS", null},
            {null, "RVAE2IS", "VMALLWS2E1IS", null, null, "RVALE2IS", null, null},
            {"ALLE2IS", "VAE2IS", null, null, "ALLE1IS", "VALE2IS", "VMALLS12E1IS", null},
            {"IPAS2E1OS", "IPAS2E1", "RIPAS2E1", "RIPAS2E1OS", "IPAS2LE1OS", "IPAS2LE1", "RIPAS2LE1", "RIPAS2LE1OS"},
            {null, "RVAE2OS", "VMALLWS2E1OS", null, null, "RVALE2OS", null, null},
            {null, "RVAE2", "VMALLWS2E1", null, null, "RVALE2", null, null},
            {"ALLE2", "VAE2", null, null, "ALLE1", "VALE2", "VMALLS12E1", null}
      },
      {
            null,
            {"ALLE3OS", "VAE3OS", null, null, "PAALLOS", "VALE3OS", null, null},
            {null, "RVAE3IS", null, null, null, "RVALE3IS", null, null},
            {"ALLE3IS", "VAE3IS", null, null, null, "VALE3IS", null, null},
            {null, null, null, "RPAOS", null, null, null, "RPALOS"},
            {null, "RVAE3OS", null, null, null, "RVALE3OS", null, null},
            {null, "RVAE3", null, null, null, "RVALE3", null, null},
            {"ALLE3", "VAE3", null, null, "PAALL", "VALE3", null, null}
      }
   };
   public static final String[] sY = new String[]{
      null,
      null,
      null,
      "UAO",
      "PAN",
      "SPSel",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "SSBS",
      "DIT",
      null,
      "TCO",
      null,
      "DAIFSet",
      "DAIFClr"
   };
   public static final String[][][] ys = new String[][][]{null, {{"ALLINT", "PM"}}, null, {null, null, null, {null, "SVCRSM", "SVCRZA", "SVCRSMZA"}}};
   public static final tz[] ld = new tz[]{
      new UC("MRS", wT.UT, US.pC).ys(),
      new UC("MRS", wT.UT, US.kS).ys(),
      new UC("MSR", US.A, wT.pC).pC(cT.kS),
      new UC("MSR", US.kS, wT.pC).pC(cT.kS),
      null,
      null,
      new UC("MSR", US.A, com.pnfsoftware.jebglobal.NS.UT),
      new UC("MSR", US.A, com.pnfsoftware.jebglobal.NS.UT)
   };
   public static final tz gp = new UC("MRS", wT.wS, US.wS).ys();
   public static final tz oT = new UC("MRS", wT.wS, US.sY).ys();
   public static final tz fI = new UC("MSR", US.UT, wT.ys).pC(cT.kS);
   public static final tz WR = new UC("MSR", US.E, wT.ys).pC(cT.kS);
   public static final tz[] NS = new tz[]{
      new UC("CPS UNPREDICTABLE").pC("imod=0,M=0"),
      new UC("CPS", IV.WR),
      new UC("CPS UNPREDICTABLE").pC("imod=01"),
      new UC("CPS UNPREDICTABLE").pC("imod=01"),
      new UC("CPSIE", JF),
      new UC("CPSIE", JF, IV.WR),
      new UC("CPSID", JF),
      new UC("CPSID", JF, IV.WR)
   };
   public static final tz[] vP = new tz[]{new UC("CPSIE", Nq).pC(), new UC("CPSID", Nq).pC()};
   public static final tz[] xC = new tz[]{
      null,
      new UC("CPS", IV.WR).pC(),
      new UC("CPS UNPREDICTABLE").pC("imod=01"),
      new UC("CPS UNPREDICTABLE").pC("imod=01"),
      new UC("CPSIE", pg).pC(ji.rl).pC(),
      new UC("CPSIE", pg, IV.WR).pC(),
      new UC("CPSID", pg).pC(ji.rl).pC(),
      new UC("CPSID", pg, IV.WR).pC()
   };
   public static final tz ED = new UC("SETEND", pC);
   public static final tz Sc = new UC("SETEND", A).pC();
   public static final Hu ah = new IV(DirectEncodedMemoryArea.get(9, 1));
   public static final Hu eP = new IV(DirectEncodedMemoryArea.get(3, 1));
   public static final tz UO = new UC("SETPAN", ah).pC(ze.E);
   public static final tz Ab = new UC("SETPAN", eP).pC(ze.E).pC();
   public static final tz rl = new UC("SYS", IV.wS, wT.WR, wT.fI, IV.A, sQ.pC);
   public static final tz z = new UC("SYSL", sQ.A, IV.wS, wT.WR, wT.fI, IV.A);
   public static final tz Ek = new UC(
      "AT",
      new Ag(kS, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 1), DirectEncodedMemoryArea.get(17, 2), DirectEncodedMemoryArea.get(5, 3))),
      sQ.A
   );
   public static final tz hK = new UC(
      "DC", new vt(wS, DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(8, 4), DirectEncodedMemoryArea.get(5, 3)), sQ.A
   );
   public static final tz Er = new UC("IC", new Ag(UT, EncodedMemoryAreaList.fromBits(16, 10, 5)), sQ.pC);
   private static final Hu ZD = new s(E, DirectEncodedMemoryArea.get(17, 2), DirectEncodedMemoryArea.get(8, 3), DirectEncodedMemoryArea.get(5, 3));
   public static final tz FE = new UC("TLBI", ZD, sQ.pC);
   public static final tz Aj = new UC("SYSP", IV.wS, wT.WR, wT.fI, IV.A, sQ.kS, sQ.wS).pC(Le.wS);
   public static final tz EX = new UC("SYSP", IV.wS, wT.WR, wT.fI, IV.A).pC(Le.wS);
   public static final tz LM = new UC("TLBIP", ZD, sQ.kS, sQ.wS).pC(Le.wS);
   public static final tz mv = new UC("TLBIP", ZD).pC(Le.wS);
   private static final Hu DL = new VP("RCTX");
   private static final Hu UH = new Ag(new String[]{null, null, null, null, "IALL", "INJ", null, null}, DirectEncodedMemoryArea.get(5, 3));
   public static final tz sO = new UC("CFP", DL, sQ.A);
   public static final tz os = new UC("COSP", DL, sQ.A);
   public static final tz Cu = new UC("CPP", DL, sQ.A);
   public static final tz hZ = new UC("DVP", DL, sQ.A);
   public static final tz UW = new UC("BRB", UH, sQ.pC);
   public static final tz PR = new UC("GCSPUSHX", sQ.pC);
   public static final tz cX = new UC("GCSPOPCX", sQ.pC);
   public static final tz DQ = new UC("GCSPOPX", sQ.pC);
   public static final tz ZN = new UC("GCSPUSHM", sQ.pC);
   public static final tz OB = new UC("GCSPOPM", sQ.pC);
   public static final tz pF = new UC("GCSSS1", sQ.A);
   public static final tz Bc = new UC("GCSSS2", sQ.A);
   public static final tz OI = new UC("TRCIT", sQ.A);
   public static final tz Bf = new UC("MRS", sQ.A, mt.pC);
   public static final tz Pe = new UC("MSR", mt.A, sQ.A);
   public static final tz ck = new UC(
      "MSR", new Ag(sY, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(5, 3))), IV.ys
   );
   public static final tz RW = new UC(
      "MSR", new vt(ys, DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(5, 3), DirectEncodedMemoryArea.get(9, 2)), IV.pC
   );
   public static final tz[] e = new tz[]{new UC("CFINV").pC(Le.hK), new UC("XAFLAG").pC(Le.Er), new UC("AXFLAG").pC(Le.Er)};
   public static final tz[] xM = new tz[]{new UC("TSTART", sQ.A).pC(Le.UH), new UC("TTEST", sQ.A).pC(Le.UH)};
   public static final tz kU = new UC("MRRS", sQ.kS, sQ.wS, mt.pC).pC(Le.kS);
   public static final tz Kq = new UC("MSRR", mt.A, sQ.kS, sQ.wS).pC(Le.kS);
   private static final wZ.Av[] VD = new wZ.Av[]{wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.oT, wZ.Av.fI, wZ.Av.WR, wZ.Av.ys};
   private static final wZ.Av[] Xs = new wZ.Av[]{wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.xC};
   private static final wZ.Av[] KV = new wZ.Av[]{wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.UT, wZ.Av.sY, wZ.Av.gp, wZ.Av.E};
   private static final wZ.Av[] FK = new wZ.Av[]{wZ.Av.NS, null, wZ.Av.vP, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys, wZ.Av.ys};

   public static boolean pC(byte[] var0) {
      return pC(
         DirectEncodedMemoryArea.get(16, 3).decodeInt(var0),
         DirectEncodedMemoryArea.get(8, 4).decodeInt(var0),
         DirectEncodedMemoryArea.get(5, 3).decodeInt(var0)
      );
   }

   public static boolean pC(int var0, int var1, int var2) {
      if (var0 == 6) {
         if (var1 == 4) {
            return true;
         }

         if (var2 == 4 && (var1 == 1 || var1 == 7)) {
            return true;
         }
      }

      return false;
   }

   private static int UT(byte[] var0) {
      return (var0[0] & 2) << 1 | (var0[1] & 32) >>> 4 | (var0[2] & 2) >>> 1;
   }

   public static tz A(byte[] var0) {
      int var1 = UT(var0);
      return (var1 != 0 || (var0[1] & 15) != 15 || (var0[2] & 13) != 0 || (var0[3] & 15) != 0)
            && (var1 != 1 || (var0[2] & 12) != 0 || (var0[3] & 15) != 0)
            && (var1 != 2 || (var0[2] & 253) != 240)
            && (var1 != 3 || (var0[2] & 252) != 240)
            && (var1 < 6 || (var0[2] & 240) != 240)
         ? null
         : ld[var1];
   }

   public static tz kS(byte[] var0) {
      int var1 = var0[1] & 7;
      int var2 = (var0[2] & 240) >>> 4;
      int var3 = var0[2] & 15;
      int var4 = (var0[3] & 224) >>> 5;
      wZ.Av var5 = pC(var1, var2, var3, var4);
      switch (var5) {
         case pC:
            return Ek;
         case A:
            return hK;
         case kS:
            return Er;
         case wS:
            return FE;
         case UT:
            return sO;
         case gp:
            return os;
         case E:
            return Cu;
         case sY:
            return hZ;
         case ld:
            return UW;
         case oT:
            if (Le.OI.pC(var0) == 0) {
               return PR;
            }
            break;
         case fI:
            if (Le.OI.pC(var0) == 0) {
               return cX;
            }
            break;
         case WR:
            if (Le.OI.pC(var0) == 0) {
               return DQ;
            }
            break;
         case NS:
            if (Le.OI.pC(var0) == 0) {
               return ZN;
            }
            break;
         case vP:
            if (Le.OI.pC(var0) == 0) {
               return pF;
            }
            break;
         case xC:
            return OI;
         default:
            return rl;
      }

      return rl;
   }

   private static wZ.Av pC(int var0, int var1, int var2, int var3) {
      if (var1 != 7) {
         if ((var1 & 14) == 8 && var2 < 8 && (var0 & 1) == 0) {
            if (pC(var0, var2, var3) && var1 != 8) {
               return wZ.Av.ys;
            }

            if (ArrayUtil.getSafe3(E, (var0 & 6) >>> 1, var2, var3, null) != null) {
               return wZ.Av.wS;
            }
         }
      } else {
         if ((var2 & 14) == 8 && (var0 & 1) == 0) {
            int var4 = (var2 & 1) << 5 | (var0 & 6) << 2 | var3;
            if (kS[var4] != null) {
               return wZ.Av.pC;
            }
         }

         if (ArrayUtil.getSafe3(wS, var0, var2, var3, null) != null) {
            return wZ.Av.A;
         }

         if (var0 == 0 || var0 == 3) {
            if ((var2 & 11) == 1 && (var3 & 6) == 0) {
               int var5 = (var0 & 1) << 2 | (var2 & 4) >>> 1 | var3 & 1;
               if (UT[var5] != null) {
                  return wZ.Av.kS;
               }
            }

            if (var0 == 0 && var2 == 7) {
               return VD[var3];
            }

            if (var0 == 3 && var2 == 2) {
               return Xs[var3];
            }

            if (var0 == 3 && var2 == 3) {
               return KV[var3];
            }

            if (var0 == 3 && var2 == 7) {
               return FK[var3];
            }
         }

         if (var0 == 1 && var2 == 2 && (var3 == 4 || var3 == 5)) {
            return wZ.Av.ld;
         }
      }

      return wZ.Av.ys;
   }

   public static tz wS(byte[] var0) {
      int var1 = var0[1] & 7;
      int var2 = (var0[2] & 240) >>> 4;
      int var3 = var0[2] & 15;
      int var4 = (var0[3] & 224) >>> 5;
      wZ.K var5 = A(var1, var2, var3, var4);
      switch (var5) {
         case A:
            if (Le.OI.pC(var0) == 0) {
               return OB;
            }
            break;
         case kS:
            if (Le.OI.pC(var0) == 0) {
               return Bc;
            }
            break;
         default:
            return z;
      }

      return z;
   }

   private static wZ.K A(int var0, int var1, int var2, int var3) {
      if (var0 == 3 && var1 == 7 && var2 == 7) {
         if (var3 == 1) {
            return wZ.K.A;
         }

         if (var3 == 3) {
            return wZ.K.kS;
         }
      }

      return wZ.K.pC;
   }

   public static tz pC(byte[] var0, int var1, String var2) throws ProcessorException {
      if ((var0[1] & 32) == 0) {
         int var3 = var0[1] & 7;
         int var4 = (var0[2] & 240) >>> 4;
         int var5 = var0[2] & 15;
         int var6 = (var0[3] & 224) >>> 5;
         wZ.Sv var7 = kS(var3, var4, var5, var6);
         switch (var7) {
            case A:
               return var1 == 31 ? mv : LM;
            default:
               return var1 == 31 ? EX : Aj;
         }
      } else {
         return UC.pC(var0, var2);
      }
   }

   private static wZ.Sv kS(int var0, int var1, int var2, int var3) {
      if ((var1 & 14) == 8 && ArrayUtil.getSafe3(E, (var0 & 6) >>> 1, var2, var3, null) != null) {
         if (var0 == 0 && (var2 == 1 || var2 == 3 || var2 == 7 || var2 == 2 || var2 == 5 || var2 == 6) && (var3 == 1 || var3 == 3 || var3 == 5 || var3 == 7)) {
            return wZ.Sv.A;
         }

         if ((var0 == 4 || var0 == 6) && (var2 == 1 || var2 == 3 || var2 == 7 || var2 == 2 || var2 == 5 || var2 == 6) && (var3 == 1 || var3 == 5)) {
            return wZ.Sv.A;
         }

         if (var0 == 4 && var2 == 0 && (var3 == 1 || var3 == 5 || var3 == 2 || var3 == 6)) {
            return wZ.Sv.A;
         }

         if (var0 == 4 && var2 == 4) {
            return wZ.Sv.A;
         }
      }

      return wZ.Sv.pC;
   }

   private static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp,
      oT,
      fI,
      WR,
      NS,
      vP,
      xC;
   }

   private static enum K {
      pC,
      A,
      kS;
   }

   private static enum Sv {
      pC,
      A;
   }
}
