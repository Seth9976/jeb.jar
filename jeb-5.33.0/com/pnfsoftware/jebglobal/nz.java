package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class nz {
   private static final Hu[] pC = new Hu[]{BS.NS, BS.xC, BS.vP};
   private static final ji A = new Uf.Av(null, Uf.pC, 2);
   private static final ji kS = new Uf.Av('F', DirectEncodedMemoryArea.get(20, 1), 0, 1, 1, true);
   private static final tz[] wS = new tz[]{
      new UC(0, "VHADD", Uf.A, BS.Sc),
      new UC(1, "VHADD", Uf.UT, BS.Sc),
      new UC(2, "VQADD", Uf.kS, BS.Sc),
      new UC(3, "VQADD", Uf.E, BS.Sc),
      new UC(4, "VRHADD", Uf.A, BS.Sc),
      new UC(5, "VRHADD", Uf.UT, BS.Sc),
      UC.pC,
      UC.pC,
      new UC(8, "VHSUB", Uf.A, BS.Sc),
      new UC(9, "VHSUB", Uf.UT, BS.Sc),
      new UC(10, "VQSUB", Uf.kS, BS.Sc),
      new UC(11, "VQSUB", Uf.E, BS.Sc),
      new UC(12, "VCGT", Uf.A, BS.Sc),
      new UC(13, "VCGT", Uf.UT, BS.Sc),
      new UC(14, "VCGE", Uf.A, BS.Sc),
      new UC(15, "VCGE", Uf.UT, BS.Sc),
      new UC(16, "VSHL", Uf.kS, pC),
      new UC(17, "VSHL", Uf.E, pC),
      new UC(18, "VQSHL", Uf.kS, pC),
      new UC(19, "VQSHL", Uf.E, pC),
      new UC(20, "VRSHL", Uf.kS, pC),
      new UC(21, "VRSHL", Uf.E, pC),
      new UC(22, "VQRSHL", Uf.kS, pC),
      new UC(23, "VQRSHL", Uf.E, pC),
      new UC(24, "VMAX", Uf.A, BS.Sc),
      new UC(25, "VMAX", Uf.UT, BS.Sc),
      new UC(26, "VMIN", Uf.A, BS.Sc),
      new UC(27, "VMIN", Uf.UT, BS.Sc),
      new UC(28, "VABD", Uf.A, BS.Sc),
      new UC(29, "VABD", Uf.UT, BS.Sc),
      new UC(30, "VABA", Uf.A, BS.Sc),
      new UC(31, "VABA", Uf.UT, BS.Sc),
      new UC(32, "VADD", Uf.ld, BS.Sc),
      new UC(33, "VSUB", Uf.ld, BS.Sc),
      new UC(34, "VTST", A, BS.Sc),
      new UC(36, "VCEQ", Uf.ys, BS.Sc),
      new UC(36, "VMLA", Uf.ys, BS.Sc),
      new UC(37, "VMLS", Uf.ys, BS.Sc),
      new UC(38, "VMUL", Uf.ys, BS.Sc),
      new UC(39, "VMUL", Uf.fI, BS.Sc),
      new UC(40, "VPMAX", Uf.A, BS.Sc),
      new UC(41, "VPMAX", Uf.UT, BS.Sc),
      new UC(42, "VPMIN", Uf.A, BS.Sc),
      new UC(43, "VPMIN", Uf.UT, BS.Sc),
      new UC(44, "VQDMULH", Uf.wS, BS.Sc),
      new UC(45, "VQRDMULH", Uf.wS, BS.Sc),
      new UC(46, "VPADD", Uf.ys, BS.Sc),
      new UC(47, "VQRDMLAH", Uf.wS, BS.Sc)
   };
   private static final tz[] UT = new tz[]{
      new UC("VAND", BS.Sc),
      new UC("VBIC", BS.Sc),
      new UC("VORR", BS.Sc),
      new UC("VORN", BS.Sc),
      new UC("VEOR", BS.Sc),
      new UC("VBSL", BS.Sc),
      new UC("VBIT", BS.Sc),
      new UC("VBIF", BS.Sc)
   };
   private static final tz[] E = new tz[]{
      new UC("SHA1C", BS.Sc).pC(Uf.ED).pC(),
      new UC("SHA1P", BS.Sc).pC(Uf.ED),
      new UC("SHA1M", BS.Sc).pC(Uf.ED),
      new UC("SHA1SU0", BS.Sc).pC(Uf.ED),
      new UC("SHA256H", BS.Sc).pC(Uf.ED),
      new UC("SHA256H2", BS.Sc).pC(Uf.ED),
      new UC("SHA256SU1", BS.Sc).pC(Uf.ED),
      null
   };
   private static final tz[] sY = new tz[]{new UC(0, "VFMA", kS, BS.Sc), new UC(1, "VFMS", kS, BS.Sc)};
   private static final tz ys = new UC("VQRDMLSH", BS.Sc).pC(Uf.wS);
   private static final tz[] ld = new tz[]{
      new UC(104, "VADD", kS, BS.Sc),
      new UC(105, "VSUB", kS, BS.Sc),
      new UC(106, "VPADD", kS, BS.Sc),
      new UC(107, "VABD", kS, BS.Sc),
      new UC(108, "VMLA", kS, BS.Sc),
      new UC(109, "VMLS", kS, BS.Sc),
      new UC(110, "VMUL", kS, BS.Sc),
      UC.pC,
      new UC(112, "VCEQ", kS, BS.Sc),
      UC.pC,
      new UC(114, "VCGE", kS, BS.Sc),
      new UC(115, "VCGT", kS, BS.Sc),
      UC.pC,
      UC.pC,
      new UC(118, "VACGE", kS, BS.Sc),
      new UC(119, "VACGT", kS, BS.Sc),
      new UC(120, "VMAX", kS, BS.Sc),
      new UC(121, "VMIN", kS, BS.Sc),
      new UC(122, "VPMAX", kS, BS.Sc),
      new UC(123, "VPMIN", kS, BS.Sc),
      new UC(124, "VRECPS", kS, BS.Sc),
      new UC(125, "VRSQRTS", kS, BS.Sc),
      new UC(126, "VMAXNM", kS, BS.Sc),
      new UC(127, "VMINNM", kS, BS.Sc)
   };
   private static final tz gp = new UC("VMOV", BS.ED);

   public static tz pC(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = (var0[3] & 16) >>> 4;
      int var4 = (var0[1] & 48) >>> 4;
      int var5 = (var0[1] & 32) >>> 5;
      int var6 = Uf.pC(var0, var1);
      int var7 = var0[3] & 64;
      if (var2 < 12) {
         if (var2 == 1 && var3 == 1) {
            int var8 = var6 << 2 | var4;
            return var8 == 2 && Gq.A(var0, BS.ys) == Gq.A(var0, BS.WR) ? gp : UT[var8];
         } else {
            return var2 == 10 && var7 != 0 ? null : wS[var2 << 2 | var3 << 1 | var6];
         }
      } else if (var2 == 12) {
         if (var3 == 0) {
            return var7 == 0 ? null : E[var6 << 2 | var4];
         } else if (var6 == 0) {
            return (var4 & 1) != 0 && !ze.pC() ? null : sY[var5];
         } else {
            return ys;
         }
      } else if (var7 != 1 || (var2 != 13 || var3 != 0 || var6 != 1 || var4 != 0) && (var2 != 15 || var3 != 0 || var6 != 1)) {
         return (var4 & 1) != 0 && !ze.pC() ? null : ld[var2 - 13 << 3 | var3 << 2 | var6 << 1 | var5];
      } else {
         return null;
      }
   }
}
