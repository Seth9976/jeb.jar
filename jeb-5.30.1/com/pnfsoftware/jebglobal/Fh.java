package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class Fh {
   private static final String[] Yp = new String[]{"NONE", "f", "i", "if", "a", "af", "ai", "aif"};
   private static final wJ Gu = new wJ(Yp, DirectEncodedMemoryArea.get(6, 3));
   private static final wJ nY = new wJ(Yp, DirectEncodedMemoryArea.get(0, 3));
   private static final wJ lF = new wJ(Yp, DirectEncodedMemoryArea.get(5, 3));
   private static final String[] nq = new String[]{"LE", "BE"};
   public static final wJ q = new wJ(nq, DirectEncodedMemoryArea.get(9, 1));
   public static final wJ RF = new wJ(nq, DirectEncodedMemoryArea.get(3, 1));
   public static final String[] xK = new String[]{
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
   public static final String[][][] Dw = new String[][][]{
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
            {null, null, "CISW", null, "CIGSW", null, "CIGDSW"}
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
            {null, "CVAU"},
            {null, "CVAP", null, "CGVAP", null, "CGDVAP"},
            {null, "CVADP", null, "CGVADP", null, "CGDVADP"},
            {null, "CIVAC", null, "CIGVAC", null, "CIGDVAC"}
      },
      {null, null, null, null, null, null, null, null, null, null, null, null, null, null, {"CIPAE", null, null, null, null, null, null, "CIGDPAE"}},
      null,
      {null, null, null, null, null, null, null, null, null, null, null, null, null, null, {null, "CIPAPA", null, null, null, "CIGDPAPA"}}
   };
   public static final String[] Uv = new String[]{"IALLUIS", null, "IALLU", null, null, null, null, "IVAU"};
   public static final String[][][] oW = new String[][][]{
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
            {null, "RVAE2IS", null, null, null, "RVALE2IS", null, null},
            {"ALLE2IS", "VAE2IS", null, null, "ALLE1IS", "VALE2IS", "VMALLS12E1IS", null},
            {"IPAS2E1OS", "IPAS2E1", "RIPAS2E1", "RIPAS2E1OS", "IPAS2LE1OS", "IPAS2LE1", "RIPAS2LE1", "RIPAS2LE1OS"},
            {null, "RVAE2OS", null, null, null, "RVALE2OS", null, null},
            {null, "RVAE2", null, null, null, "RVALE2", null, null},
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
   public static final String[] gO = new String[]{
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
   public static final String[][][] nf = new String[][][]{null, {{"ALLINT", "PM"}}, null, {null, null, null, {null, "SVCRSM", "SVCRZA", "SVCRSMZA"}}};
   public static final Je[] gP = new Je[]{
      new Qg("MRS", Pc.nf, ue.Dw).nf(),
      new Qg("MRS", Pc.nf, ue.oW).nf(),
      new Qg("MSR", ue.Uv, Pc.Dw).q(QI.xK),
      new Qg("MSR", ue.oW, Pc.Dw).q(QI.xK),
      null,
      null,
      new Qg("MSR", ue.Uv, lO.gO),
      new Qg("MSR", ue.Uv, lO.gO)
   };
   public static final Je za = new Qg("MRS", Pc.gO, ue.gO).nf();
   public static final Je lm = new Qg("MRS", Pc.gO, ue.za).nf();
   public static final Je zz = new Qg("MSR", ue.nf, Pc.lm).q(QI.xK);
   public static final Je JY = new Qg("MSR", ue.gP, Pc.lm).q(QI.xK);
   public static final Je[] HF = new Je[]{
      new Qg("CPS UNPREDICTABLE").q("imod=0,M=0"),
      new Qg("CPS", go.PV),
      new Qg("CPS UNPREDICTABLE").q("imod=01"),
      new Qg("CPS UNPREDICTABLE").q("imod=01"),
      new Qg("CPSIE", Gu),
      new Qg("CPSIE", Gu, go.PV),
      new Qg("CPSID", Gu),
      new Qg("CPSID", Gu, go.PV)
   };
   public static final Je[] LK = new Je[]{new Qg("CPSIE", nY).q(), new Qg("CPSID", nY).q()};
   public static final Je[] io = new Je[]{
      null,
      new Qg("CPS", go.PV).q(),
      new Qg("CPS UNPREDICTABLE").q("imod=01"),
      new Qg("CPS UNPREDICTABLE").q("imod=01"),
      new Qg("CPSIE", lF).q(de.KT).q(),
      new Qg("CPSIE", lF, go.PV).q(),
      new Qg("CPSID", lF).q(de.KT).q(),
      new Qg("CPSID", lF, go.PV).q()
   };
   public static final Je qa = new Qg("SETEND", q);
   public static final Je Hk = new Qg("SETEND", RF).q();
   public static final Ef Me = new go(DirectEncodedMemoryArea.get(9, 1));
   public static final Ef PV = new go(DirectEncodedMemoryArea.get(3, 1));
   public static final Je oQ = new Qg("SETPAN", Me).q(MX.LK);
   public static final Je xW = new Qg("SETPAN", PV).q(MX.LK).q();
   public static final Je KT = new Qg("SYS", go.lm, Pc.io, Pc.LK, go.gP, YH.Dw);
   public static final Je Gf = new Qg("SYSL", YH.Uv, go.lm, Pc.io, Pc.LK, go.gP);
   public static final Je Ef = new Qg(
      "AT",
      new wJ(xK, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 1), DirectEncodedMemoryArea.get(17, 2), DirectEncodedMemoryArea.get(5, 3))),
      YH.Uv
   );
   public static final Je cC = new Qg(
      "DC", new OZ(Dw, DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(8, 4), DirectEncodedMemoryArea.get(5, 3)), YH.Uv
   );
   public static final Je sH = new Qg("IC", new wJ(Uv, EncodedMemoryAreaList.fromBits(16, 10, 5)), YH.Dw);
   private static final Ef NX = new JM(oW, DirectEncodedMemoryArea.get(17, 2), DirectEncodedMemoryArea.get(8, 3), DirectEncodedMemoryArea.get(5, 3));
   public static final Je CE = new Qg("TLBI", NX, YH.Dw);
   public static final Je wF = new Qg("SYSP", go.lm, Pc.io, Pc.LK, go.gP, YH.oW, YH.gO).q(QJ.nq);
   public static final Je If = new Qg("SYSP", go.lm, Pc.io, Pc.LK, go.gP).q(QJ.nq);
   public static final Je Dz = new Qg("TLBIP", NX, YH.oW, YH.gO).q(QJ.nq);
   public static final Je mI = new Qg("TLBIP", NX).q(QJ.nq);
   private static final Ef br = new Cx("RCTX");
   private static final Ef tW = new wJ(new String[]{null, null, null, null, "IALL", "INJ", null, null}, DirectEncodedMemoryArea.get(5, 3));
   public static final Je jq = new Qg("CFP", br, YH.Uv);
   public static final Je ui = new Qg("COSP", br, YH.Uv);
   public static final Je TX = new Qg("CPP", br, YH.Uv);
   public static final Je Rr = new Qg("DVP", br, YH.Uv);
   public static final Je EB = new Qg("BRB", tW, YH.Dw);
   public static final Je Xo = new Qg("GCSPUSHX", YH.Dw);
   public static final Je Bu = new Qg("GCSPOPCX", YH.Dw);
   public static final Je IN = new Qg("GCSPOPX", YH.Dw);
   public static final Je rL = new Qg("GCSPUSHM", YH.Dw);
   public static final Je eJ = new Qg("GCSPOPM", YH.Dw);
   public static final Je YN = new Qg("GCSSS1", YH.Uv);
   public static final Je Rv = new Qg("GCSSS2", YH.Uv);
   public static final Je zx = new Qg("TRCIT", YH.Uv);
   public static final Je ZT = new Qg("MRS", YH.Uv, NR.Dw);
   public static final Je Ri = new Qg("MSR", NR.Uv, YH.Uv);
   public static final Je GY = new Qg(
      "MSR", new wJ(gO, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(5, 3))), go.LK
   );
   public static final Je Wx = new Qg(
      "MSR", new OZ(nf, DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(5, 3), DirectEncodedMemoryArea.get(9, 2)), go.nf
   );
   public static final Je[] AB = new Je[]{new Qg("CFINV").q(QJ.fw), new Qg("XAFLAG").q(QJ.Wp), new Qg("AXFLAG").q(QJ.Wp)};
   public static final Je[] CY = new Je[]{new Qg("TSTART", YH.Uv).q(QJ.Kn), new Qg("TTEST", YH.Uv).q(QJ.Kn)};
   public static final Je WI = new Qg("MRRS", YH.oW, YH.gO, NR.Dw).q(QJ.lF);
   public static final Je Tq = new Qg("MSRR", NR.Uv, YH.oW, YH.gO).q(QJ.lF);
   private static final Fh.eo[] ZA = new Fh.eo[]{Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.lm, Fh.eo.zz, Fh.eo.JY, Fh.eo.nf};
   private static final Fh.eo[] Ov = new Fh.eo[]{Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.io};
   private static final Fh.eo[] Lj = new Fh.eo[]{Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.Uv, Fh.eo.gO, Fh.eo.za, Fh.eo.oW};
   private static final Fh.eo[] nv = new Fh.eo[]{Fh.eo.HF, null, Fh.eo.LK, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf, Fh.eo.nf};

   public static boolean q(byte[] var0) {
      return q(
         DirectEncodedMemoryArea.get(16, 3).decodeInt(var0),
         DirectEncodedMemoryArea.get(8, 4).decodeInt(var0),
         DirectEncodedMemoryArea.get(5, 3).decodeInt(var0)
      );
   }

   public static boolean q(int var0, int var1, int var2) {
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

   private static int Uv(byte[] var0) {
      return (var0[0] & 2) << 1 | (var0[1] & 32) >>> 4 | (var0[2] & 2) >>> 1;
   }

   public static Je RF(byte[] var0) {
      int var1 = Uv(var0);
      return (var1 != 0 || (var0[1] & 15) != 15 || (var0[2] & 13) != 0 || (var0[3] & 15) != 0)
            && (var1 != 1 || (var0[2] & 12) != 0 || (var0[3] & 15) != 0)
            && (var1 != 2 || (var0[2] & 253) != 240)
            && (var1 != 3 || (var0[2] & 252) != 240)
            && (var1 < 6 || (var0[2] & 240) != 240)
         ? null
         : gP[var1];
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static Je xK(byte[] var0) {
      int var1 = var0[1] & 7;
      int var2 = (var0[2] & 240) >>> 4;
      int var3 = var0[2] & 15;
      int var4 = (var0[3] & 224) >>> 5;
      Fh.eo var5 = q(var1, var2, var3, var4);
      switch (var5) {
         case q:
            return Ef;
         case RF:
            return cC;
         case xK:
            return sH;
         case Dw:
            return CE;
         case Uv:
            return jq;
         case za:
            return ui;
         case oW:
            return TX;
         case gO:
            return Rr;
         case gP:
            return EB;
         case lm:
            if (QJ.Bs.q(var0) == 0) {
               return Xo;
            }
            break;
         case zz:
            if (QJ.Bs.q(var0) == 0) {
               return Bu;
            }
            break;
         case JY:
            if (QJ.Bs.q(var0) == 0) {
               return IN;
            }
            break;
         case HF:
            if (QJ.Bs.q(var0) == 0) {
               return rL;
            }
            break;
         case LK:
            if (QJ.Bs.q(var0) == 0) {
               return YN;
            }
            break;
         case io:
            return zx;
         default:
            return KT;
      }

      return KT;
   }

   private static Fh.eo q(int var0, int var1, int var2, int var3) {
      if (var1 != 7) {
         if ((var1 & 14) == 8 && var2 < 8 && (var0 & 1) == 0) {
            if (q(var0, var2, var3) && var1 != 8) {
               return Fh.eo.nf;
            }

            if (ArrayUtil.getSafe3(oW, (var0 & 6) >>> 1, var2, var3, null) != null) {
               return Fh.eo.Dw;
            }
         }
      } else {
         if ((var2 & 14) == 8 && (var0 & 1) == 0) {
            int var4 = (var2 & 1) << 5 | (var0 & 6) << 2 | var3;
            if (xK[var4] != null) {
               return Fh.eo.q;
            }
         }

         if (ArrayUtil.getSafe3(Dw, var0, var2, var3, null) != null) {
            return Fh.eo.RF;
         }

         if (var0 == 0 || var0 == 3) {
            if ((var2 & 11) == 1 && (var3 & 6) == 0) {
               int var5 = (var0 & 1) << 2 | (var2 & 4) >>> 1 | var3 & 1;
               if (Uv[var5] != null) {
                  return Fh.eo.xK;
               }
            }

            if (var0 == 0 && var2 == 7) {
               return ZA[var3];
            }

            if (var0 == 3 && var2 == 2) {
               return Ov[var3];
            }

            if (var0 == 3 && var2 == 3) {
               return Lj[var3];
            }

            if (var0 == 3 && var2 == 7) {
               return nv[var3];
            }
         }

         if (var0 == 1 && var2 == 2 && (var3 == 4 || var3 == 5)) {
            return Fh.eo.gP;
         }
      }

      return Fh.eo.nf;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static Je Dw(byte[] var0) {
      int var1 = var0[1] & 7;
      int var2 = (var0[2] & 240) >>> 4;
      int var3 = var0[2] & 15;
      int var4 = (var0[3] & 224) >>> 5;
      Fh.nI var5 = RF(var1, var2, var3, var4);
      switch (var5) {
         case RF:
            if (QJ.Bs.q(var0) == 0) {
               return eJ;
            }
            break;
         case xK:
            if (QJ.Bs.q(var0) == 0) {
               return Rv;
            }
            break;
         default:
            return Gf;
      }

      return Gf;
   }

   private static Fh.nI RF(int var0, int var1, int var2, int var3) {
      if (var0 == 3 && var1 == 7 && var2 == 7) {
         if (var3 == 1) {
            return Fh.nI.RF;
         }

         if (var3 == 3) {
            return Fh.nI.xK;
         }
      }

      return Fh.nI.q;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static Je q(byte[] var0, int var1, String var2) throws ProcessorException {
      if ((var0[1] & 32) == 0) {
         int var3 = var0[1] & 7;
         int var4 = (var0[2] & 240) >>> 4;
         int var5 = var0[2] & 15;
         int var6 = (var0[3] & 224) >>> 5;
         Fh.CU var7 = xK(var3, var4, var5, var6);
         switch (var7) {
            case RF:
               return var1 == 31 ? mI : Dz;
            default:
               return var1 == 31 ? If : wF;
         }
      } else {
         return Qg.q(var0, var2);
      }
   }

   private static Fh.CU xK(int var0, int var1, int var2, int var3) {
      if ((var1 & 14) == 8 && ArrayUtil.getSafe3(oW, (var0 & 6) >>> 1, var2, var3, null) != null) {
         if (var0 == 0 && (var2 == 1 || var2 == 3 || var2 == 7 || var2 == 2 || var2 == 5 || var2 == 6) && (var3 == 1 || var3 == 3 || var3 == 5 || var3 == 7)) {
            return Fh.CU.RF;
         }

         if ((var0 == 4 || var0 == 6) && (var2 == 1 || var2 == 3 || var2 == 7 || var2 == 2 || var2 == 5 || var2 == 6) && (var3 == 1 || var3 == 5)) {
            return Fh.CU.RF;
         }

         if (var0 == 4 && var2 == 0 && (var3 == 1 || var3 == 5 || var3 == 2 || var3 == 6)) {
            return Fh.CU.RF;
         }

         if (var0 == 4 && var2 == 4) {
            return Fh.CU.RF;
         }
      }

      return Fh.CU.q;
   }

   private static enum CU {
      q,
      RF;
   }

   private static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za,
      lm,
      zz,
      JY,
      HF,
      LK,
      io;
   }

   private static enum nI {
      q,
      RF,
      xK;
   }
}
