package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Lw {
   private static final de q = new Zo.eo('F', DirectEncodedMemoryArea.get(8, 2), 1, 3, 0);
   private static final de RF = new Zo.eo('F', DirectEncodedMemoryArea.get(20, 1), 0, 1, 1);
   private static final go xK = new go(67174400, DirectEncodedMemoryArea.get(23, 2));
   private static final dD Dw = new dD(DirectEncodedMemoryArea.get(20, 1), 16L, 0L);
   private static final Je Uv = new Qg("VCADD", Y.qa, Y.Hk, Y.Me, xK).q(RF).q(MX.zz).q(Dw);
   private static final Je oW = new Qg("VCMLA", Y.qa, Y.Hk, Y.Me, xK).q(RF).q(MX.zz).q(Dw);
   private static final Je[] gO = new Je[]{
      null,
      null,
      null,
      null,
      new Qg(4, "VFMAL", Zo.Hk, Y.sH, Y.Rr, Y.EB).q(MX.lm),
      new Qg(5, "VFMAL", Zo.Hk, Y.xW, Y.CE, Y.wF).q(MX.lm),
      new Qg(6, "VFMAB", Zo.cC, Y.cC).q(MX.HF),
      new Qg(7, "VFMAT", Zo.cC, Y.cC).q(MX.HF),
      null,
      null,
      null,
      null,
      new Qg(12, "VFMSL", Zo.Hk, Y.sH, Y.Rr, Y.EB).q(MX.lm),
      new Qg(13, "VFMSL", Zo.Hk, Y.xW, Y.CE, Y.wF).q(MX.lm),
      null,
      null
   };
   private static final IEncodedMemoryArea nf = new EncodedMemoryAreaList(Y.HF, Y.zz);
   private static final Ef gP = new VP(new Y(nf, Y.Uv), Y.LK);
   private static final Ef za = new VP(Y.If, new EncodedMemoryAreaList(Y.zz, Y.LK));
   private static final Je[] lm = new Je[]{
      new Qg(0, "VFMAL", Zo.Hk, Y.sH, Y.Rr, gP).q(MX.lm),
      new Qg(1, "VFMAL", Zo.Hk, Y.xW, Y.CE, za).q(MX.lm),
      new Qg(2, "VFMSL", Zo.Hk, Y.sH, Y.Rr, gP).q(MX.lm),
      new Qg(3, "VFMSL", Zo.Hk, Y.xW, Y.CE, za).q(MX.lm),
      null,
      null,
      new Qg(6, "VFMAB", Zo.cC, Y.xW, Y.KT, za).q(MX.HF),
      new Qg(7, "VFMAT", Zo.cC, Y.xW, Y.KT, za).q(MX.HF)
   };
   private static final Ef zz = new VP(Y.Dz, Y.zz);
   private static final Ef JY = new VP(Y.wF, VirtualEncodedMemoryArea._0);
   private static final go HF = new go(67174400, DirectEncodedMemoryArea.get(20, 2));
   private static final Je[] LK = new Je[]{new Qg("VCMLA", Y.qa, Y.Hk, zz, HF).q(Zo.Hk).q(MX.zz, MX.za), new Qg("VCMLA", Y.qa, Y.Hk, JY, HF).q(Zo.Me).q(MX.zz)};
   private static final Je[] io = new Je[]{
      new Qg(0, "VMMLA", Zo.cC, Y.oQ).q(MX.HF),
      null,
      null,
      null,
      new Qg(4, "VSMMLA", Zo.Gf, Y.cC).q(MX.io),
      new Qg(5, "VUMMLA", Zo.Ef, Y.cC).q(MX.io),
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(12, "VUSMMLA", Zo.Gf, Y.cC).q(MX.io),
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
      null
   };
   private static final Je[] qa = new Je[]{
      new Qg(0, "VDOT", Zo.cC, Y.jq).q(MX.HF),
      null,
      new Qg(2, "VDOT", Zo.cC, Y.cC).q(MX.HF),
      null,
      null,
      null,
      null,
      null,
      new Qg(8, "VSDOT", Zo.Gf, Y.jq).q(MX.qa),
      new Qg(9, "VUDOT", Zo.Ef, Y.jq).q(MX.qa),
      new Qg(10, "VSDOT", Zo.Gf, Y.cC).q(MX.qa),
      new Qg(11, "VUDOT", Zo.Ef, Y.cC).q(MX.qa),
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
      new Qg(24, "VUSDOT", Zo.Gf, Y.jq).q(MX.io),
      null,
      new Qg(26, "VUSDOT", Zo.Gf, Y.cC).q(MX.io),
      null,
      null,
      null,
      null,
      null
   };
   private static final VP Hk = new VP(Y.Dz, Y.zz);
   private static final Je[] Me = new Je[]{
      new Qg(0, "VDOT", Zo.cC, Y.qa, Y.Hk, Hk).q(MX.HF),
      null,
      new Qg(2, "VDOT", Zo.cC, Y.qa, Y.Hk, Hk).q(MX.HF),
      null,
      null,
      null,
      null,
      null,
      new Qg(8, "VSDOT", Zo.Gf, Y.sH, Y.CE, Hk).q(MX.qa),
      new Qg(9, "VUDOT", Zo.Ef, Y.sH, Y.CE, Hk).q(MX.qa),
      new Qg(10, "VSDOT", Zo.Gf, Y.xW, Y.KT, Hk).q(MX.qa),
      new Qg(11, "VUDOT", Zo.Ef, Y.xW, Y.KT, Hk).q(MX.qa),
      null,
      null,
      null,
      null,
      new Qg(16, "VUSDOT", Zo.Gf, Y.sH, Y.CE, Hk).q(MX.io),
      new Qg(17, "VSUDOT", Zo.Ef, Y.sH, Y.CE, Hk).q(MX.io),
      new Qg(18, "VUSDOT", Zo.Gf, Y.xW, Y.KT, Hk).q(MX.io),
      new Qg(19, "VSUDOT", Zo.Ef, Y.xW, Y.KT, Hk).q(MX.io),
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
      null
   };
   private static final Je[] PV = new Je[]{
      null,
      new Qg("VSELEQ", Y.IN).q(q).q(),
      new Qg("VSELEQ", Y.IN).q(q).q(),
      new Qg("VSELEQ", Y.jq).q(q).q(),
      null,
      new Qg("VSELVS", Y.IN).q(q).q(),
      new Qg("VSELVS", Y.IN).q(q).q(),
      new Qg("VSELVS", Y.jq).q(q).q(),
      null,
      new Qg("VSELGE", Y.IN).q(q).q(),
      new Qg("VSELGE", Y.IN).q(q).q(),
      new Qg("VSELGE", Y.jq).q(q).q(),
      null,
      new Qg("VSELGT", Y.IN).q(q).q(),
      new Qg("VSELGT", Y.IN).q(q).q(),
      new Qg("VSELGT", Y.jq).q(q).q()
   };
   private static final Je[] oQ = new Je[]{
      null,
      new Qg("VMAXNM", Y.IN).q(q).q(),
      new Qg("VMAXNM", Y.IN).q(q).q(),
      new Qg("VMAXNM", Y.jq).q(q).q(),
      null,
      new Qg("VMINNM", Y.IN).q(q).q(),
      new Qg("VMINNM", Y.IN).q(q).q(),
      new Qg("VMINNM", Y.jq).q(q).q()
   };
   private static final Je[] xW = new Je[]{
      null,
      new Qg(1, "VRINTA", q, Y.Bu),
      new Qg(2, "VRINTA", q, Y.Bu),
      new Qg(3, "VRINTA", q, Y.mI),
      null,
      new Qg(5, "VRINTN", q, Y.Bu),
      new Qg(6, "VRINTN", q, Y.Bu),
      new Qg(7, "VRINTN", q, Y.mI),
      null,
      new Qg(9, "VRINTP", q, Y.Bu),
      new Qg(10, "VRINTP", q, Y.Bu),
      new Qg(11, "VRINTP", q, Y.mI),
      null,
      new Qg(13, "VRINTM", q, Y.Bu),
      new Qg(14, "VRINTM", q, Y.Bu),
      new Qg(15, "VRINTM", q, Y.mI)
   };
   private static final Je[] KT = new Je[]{
      null,
      new Qg(1, "VCVTA", Zo.ZT, Y.Bu),
      new Qg(2, "VCVTA", Zo.Rv, Y.Bu),
      new Qg(3, "VCVTA", Zo.nY, Y.rL),
      null,
      new Qg(5, "VCVTA", Zo.zx, Y.Bu),
      new Qg(6, "VCVTA", Zo.eJ, Y.Bu),
      new Qg(7, "VCVTA", Zo.Yp, Y.rL),
      null,
      new Qg(9, "VCVTN", Zo.ZT, Y.Bu),
      new Qg(10, "VCVTN", Zo.Rv, Y.Bu),
      new Qg(11, "VCVTN", Zo.nY, Y.rL),
      null,
      new Qg(13, "VCVTN", Zo.zx, Y.Bu),
      new Qg(14, "VCVTN", Zo.eJ, Y.Bu),
      new Qg(15, "VCVTN", Zo.Yp, Y.rL),
      null,
      new Qg(17, "VCVTP", Zo.ZT, Y.Bu),
      new Qg(18, "VCVTP", Zo.Rv, Y.Bu),
      new Qg(19, "VCVTP", Zo.nY, Y.rL),
      null,
      new Qg(21, "VCVTP", Zo.zx, Y.Bu),
      new Qg(22, "VCVTP", Zo.eJ, Y.Bu),
      new Qg(23, "VCVTP", Zo.Yp, Y.rL),
      null,
      new Qg(25, "VCVTM", Zo.ZT, Y.Bu),
      new Qg(26, "VCVTM", Zo.Rv, Y.Bu),
      new Qg(27, "VCVTM", Zo.nY, Y.rL),
      null,
      new Qg(29, "VCVTM", Zo.zx, Y.Bu),
      new Qg(30, "VCVTM", Zo.eJ, Y.Bu),
      new Qg(31, "VCVTM", Zo.Yp, Y.rL)
   };
   private static final dD Gf = new dD(DirectEncodedMemoryArea.get(8, 2), 1L, 1L, 16L, 1L);
   private static final Je[] Ef = new Je[]{new Qg("VMOVX", Y.Bu).q(Zo.Hk).q(Gf).q(), new Qg("VINS", Y.Bu).q(Zo.Hk).q(Gf).q()};

   public static Je q(byte[] var0, int var1) {
      int var2 = (var0[0] & 3) << 1 | (var0[1] & 128) >>> 7;
      int var3 = var0[1] & 63;
      int var4 = (var0[2] & 4) >>> 2;
      int var5 = var0[2] & 3;
      int var6 = (var0[3] & 64) >>> 6;
      int var7 = (var0[3] & 16) >>> 4;
      switch (var2) {
         case 0:
         case 1:
         case 2:
         case 3:
            if (var5 < 2) {
               int var17 = var2 & 3;
               int var20 = (var3 & 48) >>> 4;
               int var11 = var5 & 1;
               if (var4 == 0) {
                  if (var11 == 0) {
                     if (var7 == 0) {
                        if ((var17 & 1) == 1 && var20 < 2) {
                           return Uv;
                        }

                        if (var20 >= 2) {
                           return oW;
                        }
                     } else if (var17 < 2) {
                        int var14 = var17 << 3 | var20 << 1 | var6;
                        return gO[var14];
                     }
                  }
               } else if (var11 == 0) {
                  if (var6 != 0) {
                     int var21 = var17 << 3 | var20 << 1 | var7;
                     return io[var21];
                  }
               } else if (var17 < 2) {
                  int var22 = var17 << 4 | var20 << 2 | var6 << 1 | var7;
                  return qa[var22];
               }
            }
            break;
         case 4:
         case 5:
            if (var4 == 0) {
               if (var5 == 0) {
                  int var8 = var2 & 1;
                  int var9 = (var3 & 48) >>> 4;
                  if (var7 == 0) {
                     return LK[var8];
                  }

                  if (var8 == 0) {
                     return lm[var9 << 1 | var6];
                  }
               } else {
                  if (var2 == 4 && var6 == 0 && var7 == 0) {
                     return PV[(var3 & 48) >>> 2 | var5];
                  }

                  if (var2 == 5 && var7 == 0) {
                     if (var3 < 16) {
                        return oQ[var6 << 2 | var5];
                     }

                     if (var3 == 48 && var6 != 0) {
                        return Ef[(var0[3] & 128) >>> 7];
                     }

                     if (var3 >= 56 && var6 != 0) {
                        int var15 = (var3 & 4) >>> 2;
                        int var18 = (var0[3] & 128) >>> 7;
                        if (var15 != 0) {
                           return KT[(var3 & 3) << 3 | var18 << 2 | var5];
                        }

                        if (var18 == 0) {
                           return xW[(var3 & 3) << 2 | var5];
                        }
                     }
                  }
               }
            } else if (var5 < 2) {
               int var16 = var2 & 1;
               int var19 = (var3 & 48) >>> 4;
               int var10 = var5 & 1;
               if (var10 != 0) {
                  int var13 = var16 << 4 | var19 << 2 | var6 << 1 | var7;
                  return Me[var13];
               }
            }
         case 6:
         case 7:
      }

      return null;
   }
}
