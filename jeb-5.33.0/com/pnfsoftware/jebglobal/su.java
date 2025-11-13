package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class su {
   private static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(18, 2);
   private static final ji A = new Uf.Av(null, pC, 2);
   private static final ji kS = new Uf.Av(null, pC, 1);
   private static final ji wS = new Uf.Av(null, pC, 0);
   private static final ji UT = new Uf.Av('S', pC, 2);
   private static final ji E = new Uf.Av('U', pC, 2);
   private static final ji sY = new Uf.Av('I', pC, 2);
   private static final ji ys = new Uf.Av('I', pC, 0, 2, 1);
   private static final ji ld = new Uf.Av('S', pC, 0, 2, 1);
   private static final ji gp = new Uf.Av('U', pC, 0, 2, 1);
   private static final ji oT = new Uf.Av(null, pC, 2, 2, 0);
   private static final ji fI = new Uf.Av('U', pC, 1, 2, 0);
   private static final ji WR = new Uf.Av('S', pC, 1, 2, 0);
   private static final ji NS = new Uf.Av('F', pC, 1, 2, 0);
   private static final ji vP = new Uf.Av('U', pC, 2, 2, 0);
   private static final Uf.Sv xC = new Uf.Sv(WR, NS);
   private static final Uf.Sv ED = new Uf.Sv(fI, NS);
   private static final Uf.Sv Sc = new Uf.Sv(NS, WR);
   private static final Uf.Sv ah = new Uf.Sv(NS, fI);
   private static final Hu eP = new Zs(var0 -> 8 << pC.decodeInt(var0));
   private static final tz[] UO = new tz[]{
      new UC(0, "VREV64", A, BS.ED),
      new UC(1, "VREV32", kS, BS.ED),
      new UC(2, "VREV16", wS, BS.ED),
      null,
      new UC(4, "VPADDL", UT, BS.ED),
      new UC(5, "VPADDL", E, BS.ED),
      null,
      null,
      new UC(8, "VCLS", UT, BS.ED),
      new UC(9, "VCLZ", sY, BS.ED),
      new UC(10, "VCNT", wS, BS.ED),
      new UC(11, "VMVN", BS.ED).pC(pC),
      new UC(12, "VPADAL", UT, BS.ED),
      new UC(13, "VPADAL", E, BS.ED),
      new UC(14, "VQABS", UT, BS.ED),
      new UC(15, "VQNEG", UT, BS.ED)
   };
   private static final ZW Ab = new ZW(pC, 512L, 1L, 1L, 1L);
   private static final tz[] rl = new tz[]{
      new UC("AESE", BS.Ab).pC(Uf.vP).pC(Ab),
      new UC("AESD", BS.Ab).pC(Uf.vP).pC(Ab),
      new UC("AESMC", BS.Ab).pC(Uf.vP).pC(Ab),
      new UC("AESIMC", BS.Ab).pC(Uf.vP).pC(Ab)
   };
   private static final tz[] z = new tz[]{
      new UC(0, "VCGT", UT, BS.NS, BS.xC, IV.DQ),
      new UC(1, "VCGE", UT, BS.NS, BS.xC, IV.DQ),
      new UC(2, "VCEQ", sY, BS.NS, BS.xC, IV.DQ),
      new UC(3, "VCLE", UT, BS.NS, BS.xC, IV.DQ),
      new UC(4, "VCLT", UT, BS.NS, BS.xC, IV.DQ),
      null,
      new UC(6, "VABS", UT, BS.ED),
      new UC(7, "VNEG", UT, BS.ED)
   };
   private static final ZW Ek = new ZW(pC, 1L, 16L, 0L, 1L);
   private static final tz[] hK = new tz[]{
      new UC(8, "VCGT", NS, BS.NS, BS.xC, IV.DQ).pC(Ek),
      new UC(9, "VCGE", NS, BS.NS, BS.xC, IV.DQ).pC(Ek),
      new UC(10, "VCEQ", NS, BS.NS, BS.xC, IV.DQ).pC(Ek),
      new UC(11, "VCLE", NS, BS.NS, BS.xC, IV.DQ).pC(Ek),
      new UC(12, "VCLT", NS, BS.NS, BS.xC, IV.DQ).pC(Ek),
      null,
      new UC(14, "VABS", NS, BS.ED).pC(Ek),
      new UC(15, "VNEG", NS, BS.ED).pC(Ek)
   };
   private static final tz[] Er = new tz[]{null, new UC("SHA1H", BS.Ab).pC(oT)};
   private static final tz[] FE = new tz[]{
      new UC(0, "VSWP", BS.ED).pC(pC),
      new UC(1, "VSWP", BS.ED).pC(pC),
      new UC(2, "VTRN", A, BS.ED),
      new UC(3, "VTRN", A, BS.ED),
      new UC(4, "VUZP", kS, BS.ED),
      new UC(5, "VUZP", A, BS.ED),
      new UC(6, "VZIP", kS, BS.ED),
      new UC(7, "VZIP", A, BS.ED),
      new UC(8, "VMOVN", ys, BS.z, BS.UO),
      new UC(9, "VQMOVUN", ld, BS.z, BS.UO),
      new UC(10, "VQMOVN", ld, BS.z, BS.UO),
      new UC(11, "VQMOVN", gp, BS.z, BS.UO),
      new UC(12, "VSHLL", sY, BS.ah, BS.hK, eP),
      null,
      new UC(14, "SHA1SU1", oT, BS.Ab),
      new UC(15, "SHA256SU0", oT, BS.Ab)
   };
   private static final tz[] Aj = new tz[]{
      new UC(8, "VRINTN", NS, BS.ED).pC(ze.pC),
      new UC(9, "VRINTX", NS, BS.ED).pC(ze.pC),
      new UC(10, "VRINTA", NS, BS.ED).pC(ze.pC),
      new UC(11, "VRINTZ", NS, BS.ED).pC(ze.pC),
      null,
      new UC(13, "VRINTM", NS, BS.ED).pC(ze.pC),
      null,
      new UC(15, "VRINTP", NS, BS.ED).pC(ze.pC)
   };
   private static final ZW EX = new ZW(pC, 1L, 0L, 1L, 1L);
   private static final tz[] LM = new tz[]{
      new UC(0, "VCVT", Uf.LM, BS.z, BS.UO).pC(EX), new UC(0, "VCVT", Uf.pg, BS.z, BS.UO).pC(EX, ze.UT), new UC(1, "VCVT", Uf.Er, BS.ah, BS.hK).pC(EX), null
   };
   private static final tz[] mv = new tz[]{
      new UC(0, "VCVTA", xC, BS.ED).pC(Ek),
      new UC(1, "VCVTA", ED, BS.ED).pC(Ek),
      new UC(2, "VCVTN", xC, BS.ED).pC(Ek),
      new UC(3, "VCVTN", ED, BS.ED).pC(Ek),
      new UC(4, "VCVTP", xC, BS.ED).pC(Ek),
      new UC(5, "VCVTP", ED, BS.ED).pC(Ek),
      new UC(6, "VCVTM", xC, BS.ED).pC(Ek),
      new UC(7, "VCVTM", ED, BS.ED).pC(Ek),
      new UC(8, "VRECPE", vP, BS.ED).pC(Ek),
      new UC(9, "VRSQRTE", vP, BS.ED).pC(Ek),
      new UC(10, "VRECPE", NS, BS.ED).pC(Ek),
      new UC(11, "VRSQRTE", NS, BS.ED).pC(Ek),
      new UC(12, "VCVT", Sc, BS.ED).pC(Ek),
      new UC(13, "VCVT", ah, BS.ED).pC(Ek),
      new UC(14, "VCVT", xC, BS.ED).pC(Ek),
      new UC(15, "VCVT", ED, BS.ED).pC(Ek)
   };

   public static tz pC(byte[] var0, int var1) {
      int var2 = var0[1] & 3;
      int var3 = (var0[2] & 7) << 2 | (var0[3] & 192) >>> 6;
      switch (var2) {
         case 0:
            if (var3 >= 12 && var3 <= 15) {
               return rl[var3 & 3];
            }

            return UO[var3 >>> 1];
         case 1:
            if ((var3 & 30) == 10) {
               return Er[var3 & 1];
            } else {
               if (var3 >= 16) {
                  return hK[(var3 & 14) >>> 1];
               }

               return z[var3 >>> 1];
            }
         case 2:
            if (var3 >= 16) {
               if ((var3 & 26) == 24) {
                  int var4 = (var0[2] & 1) << 1 | var3 & 1;
                  return LM[var4];
               }

               return Aj[(var3 & 14) >>> 1];
            }

            return FE[var3];
         case 3:
            return mv[var3 >>> 1];
         default:
            return null;
      }
   }
}
