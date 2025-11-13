package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class it {
   private static final Ef[] q = new Ef[]{Y.qa, Y.Me, Y.Hk};
   private static final de RF = new Zo.eo(null, Zo.q, 2);
   private static final de xK = new Zo.eo('F', DirectEncodedMemoryArea.get(20, 1), 0, 1, 1, true);
   private static final Je[] Dw = new Je[]{
      new Qg(0, "VHADD", Zo.RF, Y.oQ),
      new Qg(1, "VHADD", Zo.Uv, Y.oQ),
      new Qg(2, "VQADD", Zo.xK, Y.oQ),
      new Qg(3, "VQADD", Zo.oW, Y.oQ),
      new Qg(4, "VRHADD", Zo.RF, Y.oQ),
      new Qg(5, "VRHADD", Zo.Uv, Y.oQ),
      Qg.xK,
      Qg.xK,
      new Qg(8, "VHSUB", Zo.RF, Y.oQ),
      new Qg(9, "VHSUB", Zo.Uv, Y.oQ),
      new Qg(10, "VQSUB", Zo.xK, Y.oQ),
      new Qg(11, "VQSUB", Zo.oW, Y.oQ),
      new Qg(12, "VCGT", Zo.RF, Y.oQ),
      new Qg(13, "VCGT", Zo.Uv, Y.oQ),
      new Qg(14, "VCGE", Zo.RF, Y.oQ),
      new Qg(15, "VCGE", Zo.Uv, Y.oQ),
      new Qg(16, "VSHL", Zo.xK, q),
      new Qg(17, "VSHL", Zo.oW, q),
      new Qg(18, "VQSHL", Zo.xK, q),
      new Qg(19, "VQSHL", Zo.oW, q),
      new Qg(20, "VRSHL", Zo.xK, q),
      new Qg(21, "VRSHL", Zo.oW, q),
      new Qg(22, "VQRSHL", Zo.xK, q),
      new Qg(23, "VQRSHL", Zo.oW, q),
      new Qg(24, "VMAX", Zo.RF, Y.oQ),
      new Qg(25, "VMAX", Zo.Uv, Y.oQ),
      new Qg(26, "VMIN", Zo.RF, Y.oQ),
      new Qg(27, "VMIN", Zo.Uv, Y.oQ),
      new Qg(28, "VABD", Zo.RF, Y.oQ),
      new Qg(29, "VABD", Zo.Uv, Y.oQ),
      new Qg(30, "VABA", Zo.RF, Y.oQ),
      new Qg(31, "VABA", Zo.Uv, Y.oQ),
      new Qg(32, "VADD", Zo.gP, Y.oQ),
      new Qg(33, "VSUB", Zo.gP, Y.oQ),
      new Qg(34, "VTST", RF, Y.oQ),
      new Qg(36, "VCEQ", Zo.nf, Y.oQ),
      new Qg(36, "VMLA", Zo.nf, Y.oQ),
      new Qg(37, "VMLS", Zo.nf, Y.oQ),
      new Qg(38, "VMUL", Zo.nf, Y.oQ),
      new Qg(39, "VMUL", Zo.zz, Y.oQ),
      new Qg(40, "VPMAX", Zo.RF, Y.oQ),
      new Qg(41, "VPMAX", Zo.Uv, Y.oQ),
      new Qg(42, "VPMIN", Zo.RF, Y.oQ),
      new Qg(43, "VPMIN", Zo.Uv, Y.oQ),
      new Qg(44, "VQDMULH", Zo.Dw, Y.oQ),
      new Qg(45, "VQRDMULH", Zo.Dw, Y.oQ),
      new Qg(46, "VPADD", Zo.nf, Y.oQ),
      new Qg(47, "VQRDMLAH", Zo.Dw, Y.oQ)
   };
   private static final Je[] Uv = new Je[]{
      new Qg("VAND", Y.oQ),
      new Qg("VBIC", Y.oQ),
      new Qg("VORR", Y.oQ),
      new Qg("VORN", Y.oQ),
      new Qg("VEOR", Y.oQ),
      new Qg("VBSL", Y.oQ),
      new Qg("VBIT", Y.oQ),
      new Qg("VBIF", Y.oQ)
   };
   private static final Je[] oW = new Je[]{
      new Qg("SHA1C", Y.oQ).q(Zo.qa).q(),
      new Qg("SHA1P", Y.oQ).q(Zo.qa),
      new Qg("SHA1M", Y.oQ).q(Zo.qa),
      new Qg("SHA1SU0", Y.oQ).q(Zo.qa),
      new Qg("SHA256H", Y.oQ).q(Zo.qa),
      new Qg("SHA256H2", Y.oQ).q(Zo.qa),
      new Qg("SHA256SU1", Y.oQ).q(Zo.qa),
      null
   };
   private static final Je[] gO = new Je[]{new Qg(0, "VFMA", xK, Y.oQ), new Qg(1, "VFMS", xK, Y.oQ)};
   private static final Je nf = new Qg("VQRDMLSH", Y.oQ).q(Zo.Dw);
   private static final Je[] gP = new Je[]{
      new Qg(104, "VADD", xK, Y.oQ),
      new Qg(105, "VSUB", xK, Y.oQ),
      new Qg(106, "VPADD", xK, Y.oQ),
      new Qg(107, "VABD", xK, Y.oQ),
      new Qg(108, "VMLA", xK, Y.oQ),
      new Qg(109, "VMLS", xK, Y.oQ),
      new Qg(110, "VMUL", xK, Y.oQ),
      Qg.xK,
      new Qg(112, "VCEQ", xK, Y.oQ),
      Qg.xK,
      new Qg(114, "VCGE", xK, Y.oQ),
      new Qg(115, "VCGT", xK, Y.oQ),
      Qg.xK,
      Qg.xK,
      new Qg(118, "VACGE", xK, Y.oQ),
      new Qg(119, "VACGT", xK, Y.oQ),
      new Qg(120, "VMAX", xK, Y.oQ),
      new Qg(121, "VMIN", xK, Y.oQ),
      new Qg(122, "VPMAX", xK, Y.oQ),
      new Qg(123, "VPMIN", xK, Y.oQ),
      new Qg(124, "VRECPS", xK, Y.oQ),
      new Qg(125, "VRSQRTS", xK, Y.oQ),
      new Qg(126, "VMAXNM", xK, Y.oQ),
      new Qg(127, "VMINNM", xK, Y.oQ)
   };
   private static final Je za = new Qg("VMOV", Y.PV);

   public static Je q(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = (var0[3] & 16) >>> 4;
      int var4 = (var0[1] & 48) >>> 4;
      int var5 = (var0[1] & 32) >>> 5;
      int var6 = Zo.q(var0, var1);
      int var7 = var0[3] & 64;
      if (var2 < 12) {
         if (var2 == 1 && var3 == 1) {
            int var8 = var6 << 2 | var4;
            return var8 == 2 && k.RF(var0, Y.lm) == k.RF(var0, Y.io) ? za : Uv[var8];
         } else {
            return var2 == 10 && var7 != 0 ? null : Dw[var2 << 2 | var3 << 1 | var6];
         }
      } else if (var2 == 12) {
         if (var3 == 0) {
            return var7 == 0 ? null : oW[var6 << 2 | var4];
         } else if (var6 == 0) {
            return (var4 & 1) != 0 && !MX.q() ? null : gO[var5];
         } else {
            return nf;
         }
      } else if (var7 != 1 || (var2 != 13 || var3 != 0 || var6 != 1 || var4 != 0) && (var2 != 15 || var3 != 0 || var6 != 1)) {
         return (var4 & 1) != 0 && !MX.q() ? null : gP[var2 - 13 << 3 | var3 << 2 | var6 << 1 | var5];
      } else {
         return null;
      }
   }
}
