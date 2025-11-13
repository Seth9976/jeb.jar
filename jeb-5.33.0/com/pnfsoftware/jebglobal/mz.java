package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class mz {
   private static final ji pC = new Uf.Av('F', DirectEncodedMemoryArea.get(8, 2), 1, 3, 0);
   private static final ji A = new Uf.Av('F', DirectEncodedMemoryArea.get(20, 1), 0, 1, 1);
   private static final IV kS = new IV(67174400, DirectEncodedMemoryArea.get(23, 2));
   private static final ZW wS = new ZW(DirectEncodedMemoryArea.get(20, 1), 16L, 0L);
   private static final tz UT = new UC("VCADD", BS.NS, BS.vP, BS.xC, kS).pC(A).pC(ze.kS).pC(wS);
   private static final tz E = new UC("VCMLA", BS.NS, BS.vP, BS.xC, kS).pC(A).pC(ze.kS).pC(wS);
   private static final tz[] sY = new tz[]{
      null,
      null,
      null,
      null,
      new UC(4, "VFMAL", Uf.Sc, BS.z, BS.sO, BS.os).pC(ze.A),
      new UC(5, "VFMAL", Uf.Sc, BS.ah, BS.Ek, BS.hK).pC(ze.A),
      new UC(6, "VFMAB", Uf.hK, BS.rl).pC(ze.UT),
      new UC(7, "VFMAT", Uf.hK, BS.rl).pC(ze.UT),
      null,
      null,
      null,
      null,
      new UC(12, "VFMSL", Uf.Sc, BS.z, BS.sO, BS.os).pC(ze.A),
      new UC(13, "VFMSL", Uf.Sc, BS.ah, BS.Ek, BS.hK).pC(ze.A),
      null,
      null
   };
   private static final IEncodedMemoryArea ys = new EncodedMemoryAreaList(BS.oT, BS.ld);
   private static final Hu ld = new Yu(new BS(ys, BS.A), BS.fI);
   private static final Hu gp = new Yu(BS.Er, new EncodedMemoryAreaList(BS.ld, BS.fI));
   private static final tz[] oT = new tz[]{
      new UC(0, "VFMAL", Uf.Sc, BS.z, BS.sO, ld).pC(ze.A),
      new UC(1, "VFMAL", Uf.Sc, BS.ah, BS.Ek, gp).pC(ze.A),
      new UC(2, "VFMSL", Uf.Sc, BS.z, BS.sO, ld).pC(ze.A),
      new UC(3, "VFMSL", Uf.Sc, BS.ah, BS.Ek, gp).pC(ze.A),
      null,
      null,
      new UC(6, "VFMAB", Uf.hK, BS.ah, BS.eP, gp).pC(ze.UT),
      new UC(7, "VFMAT", Uf.hK, BS.ah, BS.eP, gp).pC(ze.UT)
   };
   private static final Hu fI = new Yu(BS.FE, BS.ld);
   private static final Hu WR = new Yu(BS.hK, VirtualEncodedMemoryArea._0);
   private static final IV NS = new IV(67174400, DirectEncodedMemoryArea.get(20, 2));
   private static final tz[] vP = new tz[]{
      new UC("VCMLA", BS.NS, BS.vP, fI, NS).pC(Uf.Sc).pC(ze.kS, ze.pC), new UC("VCMLA", BS.NS, BS.vP, WR, NS).pC(Uf.ah).pC(ze.kS)
   };
   private static final tz[] xC = new tz[]{
      new UC(0, "VMMLA", Uf.hK, BS.Sc).pC(ze.UT),
      null,
      null,
      null,
      new UC(4, "VSMMLA", Uf.z, BS.rl).pC(ze.sY),
      new UC(5, "VUMMLA", Uf.Ek, BS.rl).pC(ze.sY),
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(12, "VUSMMLA", Uf.z, BS.rl).pC(ze.sY),
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
   private static final tz[] ED = new tz[]{
      new UC(0, "VDOT", Uf.hK, BS.EX).pC(ze.UT),
      null,
      new UC(2, "VDOT", Uf.hK, BS.rl).pC(ze.UT),
      null,
      null,
      null,
      null,
      null,
      new UC(8, "VSDOT", Uf.z, BS.EX).pC(ze.ys),
      new UC(9, "VUDOT", Uf.Ek, BS.EX).pC(ze.ys),
      new UC(10, "VSDOT", Uf.z, BS.rl).pC(ze.ys),
      new UC(11, "VUDOT", Uf.Ek, BS.rl).pC(ze.ys),
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
      new UC(24, "VUSDOT", Uf.z, BS.EX).pC(ze.sY),
      null,
      new UC(26, "VUSDOT", Uf.z, BS.rl).pC(ze.sY),
      null,
      null,
      null,
      null,
      null
   };
   private static final Yu Sc = new Yu(BS.FE, BS.ld);
   private static final tz[] ah = new tz[]{
      new UC(0, "VDOT", Uf.hK, BS.NS, BS.vP, Sc).pC(ze.UT),
      null,
      new UC(2, "VDOT", Uf.hK, BS.NS, BS.vP, Sc).pC(ze.UT),
      null,
      null,
      null,
      null,
      null,
      new UC(8, "VSDOT", Uf.z, BS.z, BS.Ek, Sc).pC(ze.ys),
      new UC(9, "VUDOT", Uf.Ek, BS.z, BS.Ek, Sc).pC(ze.ys),
      new UC(10, "VSDOT", Uf.z, BS.ah, BS.eP, Sc).pC(ze.ys),
      new UC(11, "VUDOT", Uf.Ek, BS.ah, BS.eP, Sc).pC(ze.ys),
      null,
      null,
      null,
      null,
      new UC(16, "VUSDOT", Uf.z, BS.z, BS.Ek, Sc).pC(ze.sY),
      new UC(17, "VSUDOT", Uf.Ek, BS.z, BS.Ek, Sc).pC(ze.sY),
      new UC(18, "VUSDOT", Uf.z, BS.ah, BS.eP, Sc).pC(ze.sY),
      new UC(19, "VSUDOT", Uf.Ek, BS.ah, BS.eP, Sc).pC(ze.sY),
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
   private static final tz[] eP = new tz[]{
      null,
      new UC("VSELEQ", BS.UW).pC(pC).pC(),
      new UC("VSELEQ", BS.UW).pC(pC).pC(),
      new UC("VSELEQ", BS.EX).pC(pC).pC(),
      null,
      new UC("VSELVS", BS.UW).pC(pC).pC(),
      new UC("VSELVS", BS.UW).pC(pC).pC(),
      new UC("VSELVS", BS.EX).pC(pC).pC(),
      null,
      new UC("VSELGE", BS.UW).pC(pC).pC(),
      new UC("VSELGE", BS.UW).pC(pC).pC(),
      new UC("VSELGE", BS.EX).pC(pC).pC(),
      null,
      new UC("VSELGT", BS.UW).pC(pC).pC(),
      new UC("VSELGT", BS.UW).pC(pC).pC(),
      new UC("VSELGT", BS.EX).pC(pC).pC()
   };
   private static final tz[] UO = new tz[]{
      null,
      new UC("VMAXNM", BS.UW).pC(pC).pC(),
      new UC("VMAXNM", BS.UW).pC(pC).pC(),
      new UC("VMAXNM", BS.EX).pC(pC).pC(),
      null,
      new UC("VMINNM", BS.UW).pC(pC).pC(),
      new UC("VMINNM", BS.UW).pC(pC).pC(),
      new UC("VMINNM", BS.EX).pC(pC).pC()
   };
   private static final tz[] Ab = new tz[]{
      null,
      new UC(1, "VRINTA", pC, BS.hZ),
      new UC(2, "VRINTA", pC, BS.hZ),
      new UC(3, "VRINTA", pC, BS.Aj),
      null,
      new UC(5, "VRINTN", pC, BS.hZ),
      new UC(6, "VRINTN", pC, BS.hZ),
      new UC(7, "VRINTN", pC, BS.Aj),
      null,
      new UC(9, "VRINTP", pC, BS.hZ),
      new UC(10, "VRINTP", pC, BS.hZ),
      new UC(11, "VRINTP", pC, BS.Aj),
      null,
      new UC(13, "VRINTM", pC, BS.hZ),
      new UC(14, "VRINTM", pC, BS.hZ),
      new UC(15, "VRINTM", pC, BS.Aj)
   };
   private static final tz[] rl = new tz[]{
      null,
      new UC(1, "VCVTA", Uf.Bf, BS.hZ),
      new UC(2, "VCVTA", Uf.Bc, BS.hZ),
      new UC(3, "VCVTA", Uf.Nq, BS.PR),
      null,
      new UC(5, "VCVTA", Uf.OI, BS.hZ),
      new UC(6, "VCVTA", Uf.OB, BS.hZ),
      new UC(7, "VCVTA", Uf.go, BS.PR),
      null,
      new UC(9, "VCVTN", Uf.Bf, BS.hZ),
      new UC(10, "VCVTN", Uf.Bc, BS.hZ),
      new UC(11, "VCVTN", Uf.Nq, BS.PR),
      null,
      new UC(13, "VCVTN", Uf.OI, BS.hZ),
      new UC(14, "VCVTN", Uf.OB, BS.hZ),
      new UC(15, "VCVTN", Uf.go, BS.PR),
      null,
      new UC(17, "VCVTP", Uf.Bf, BS.hZ),
      new UC(18, "VCVTP", Uf.Bc, BS.hZ),
      new UC(19, "VCVTP", Uf.Nq, BS.PR),
      null,
      new UC(21, "VCVTP", Uf.OI, BS.hZ),
      new UC(22, "VCVTP", Uf.OB, BS.hZ),
      new UC(23, "VCVTP", Uf.go, BS.PR),
      null,
      new UC(25, "VCVTM", Uf.Bf, BS.hZ),
      new UC(26, "VCVTM", Uf.Bc, BS.hZ),
      new UC(27, "VCVTM", Uf.Nq, BS.PR),
      null,
      new UC(29, "VCVTM", Uf.OI, BS.hZ),
      new UC(30, "VCVTM", Uf.OB, BS.hZ),
      new UC(31, "VCVTM", Uf.go, BS.PR)
   };
   private static final ZW z = new ZW(DirectEncodedMemoryArea.get(8, 2), 1L, 1L, 16L, 1L);
   private static final tz[] Ek = new tz[]{new UC("VMOVX", BS.hZ).pC(Uf.Sc).pC(z).pC(), new UC("VINS", BS.hZ).pC(Uf.Sc).pC(z).pC()};

   public static tz pC(byte[] var0, int var1) {
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
                           return UT;
                        }

                        if (var20 >= 2) {
                           return E;
                        }
                     } else if (var17 < 2) {
                        int var14 = var17 << 3 | var20 << 1 | var6;
                        return sY[var14];
                     }
                  }
               } else if (var11 == 0) {
                  if (var6 != 0) {
                     int var21 = var17 << 3 | var20 << 1 | var7;
                     return xC[var21];
                  }
               } else if (var17 < 2) {
                  int var22 = var17 << 4 | var20 << 2 | var6 << 1 | var7;
                  return ED[var22];
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
                     return vP[var8];
                  }

                  if (var8 == 0) {
                     return oT[var9 << 1 | var6];
                  }
               } else {
                  if (var2 == 4 && var6 == 0 && var7 == 0) {
                     return eP[(var3 & 48) >>> 2 | var5];
                  }

                  if (var2 == 5 && var7 == 0) {
                     if (var3 < 16) {
                        return UO[var6 << 2 | var5];
                     }

                     if (var3 == 48 && var6 != 0) {
                        return Ek[(var0[3] & 128) >>> 7];
                     }

                     if (var3 >= 56 && var6 != 0) {
                        int var15 = (var3 & 4) >>> 2;
                        int var18 = (var0[3] & 128) >>> 7;
                        if (var15 != 0) {
                           return rl[(var3 & 3) << 3 | var18 << 2 | var5];
                        }

                        if (var18 == 0) {
                           return Ab[(var3 & 3) << 2 | var5];
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
                  return ah[var13];
               }
            }
         case 6:
         case 7:
      }

      return null;
   }
}
