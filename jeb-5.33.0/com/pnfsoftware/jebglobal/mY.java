package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class mY {
   private static final ji pC = new Uf.Av('F', DirectEncodedMemoryArea.get(8, 2), 1, 3, 0);
   private static final ji A = new Uf.Av('F', DirectEncodedMemoryArea.get(8, 2), 2, 3, 0);
   private static final IEncodedMemoryArea kS = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 4), DirectEncodedMemoryArea.get(0, 4));
   private static final Hu wS = new eK(16, kS);
   private static final Hu UT = new eK(32, kS);
   private static final Hu E = new eK(64, kS);
   private static final Hu sY = new OC(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(0, 4), DirectEncodedMemoryArea.get(5, 1)), 16);
   private static final Hu ys = new OC(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(0, 4), DirectEncodedMemoryArea.get(5, 1)), 32);
   private static final tz[] ld = new tz[]{
      null,
      new UC(1, "VMLA", pC, BS.UW),
      new UC(2, "VMLA", pC, BS.UW),
      new UC(3, "VMLA", pC, BS.EX),
      null,
      new UC(5, "VMLS", pC, BS.UW),
      new UC(6, "VMLS", pC, BS.UW),
      new UC(7, "VMLS", pC, BS.EX),
      null,
      new UC(9, "VNMLS", pC, BS.UW),
      new UC(10, "VNMLS", pC, BS.UW),
      new UC(11, "VNMLS", pC, BS.EX),
      null,
      new UC(13, "VNMLA", pC, BS.UW),
      new UC(14, "VNMLA", pC, BS.UW),
      new UC(15, "VNMLA", pC, BS.EX),
      null,
      new UC(17, "VMUL", pC, BS.UW),
      new UC(18, "VMUL", pC, BS.UW),
      new UC(19, "VMUL", pC, BS.EX),
      null,
      new UC(21, "VNMUL", pC, BS.UW),
      new UC(22, "VNMUL", pC, BS.UW),
      new UC(23, "VNMUL", pC, BS.EX),
      null,
      new UC(25, "VADD", pC, BS.UW),
      new UC(26, "VADD", pC, BS.UW),
      new UC(27, "VADD", pC, BS.EX),
      null,
      new UC(29, "VSUB", pC, BS.UW),
      new UC(30, "VSUB", pC, BS.UW),
      new UC(31, "VSUB", pC, BS.EX),
      null,
      new UC(33, "VDIV", pC, BS.UW),
      new UC(34, "VDIV", pC, BS.UW),
      new UC(35, "VDIV", pC, BS.EX),
      null,
      null,
      null,
      null,
      null,
      new UC(41, "VFNMS", pC, BS.UW),
      new UC(42, "VFNMS", pC, BS.UW),
      new UC(43, "VFNMS", pC, BS.EX),
      null,
      new UC(45, "VFNMA", pC, BS.UW),
      new UC(46, "VFNMA", pC, BS.UW),
      new UC(47, "VFNMA", pC, BS.EX),
      null,
      new UC(49, "VFMA", pC, BS.UW),
      new UC(50, "VFMA", pC, BS.UW),
      new UC(51, "VFMA", pC, BS.EX),
      null,
      new UC(53, "VFMS", pC, BS.UW),
      new UC(54, "VFMS", pC, BS.UW),
      new UC(55, "VFMS", pC, BS.EX),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
   };
   private static final tz[] gp = new tz[]{null, new UC(1, "VMOV", pC, BS.mv, wS), new UC(2, "VMOV", pC, BS.mv, UT), new UC(3, "VMOV", pC, BS.z, E)};
   private static final Hu[] oT = new Hu[]{BS.mv, bI.pC(bI.wS)};
   private static final Hu[] fI = new Hu[]{BS.z, bI.pC(bI.UT)};
   private static final tz[] WR = new tz[]{
      null,
      null,
      new UC(2, "VMOV", A, BS.hZ),
      new UC(3, "VMOV", A, BS.Aj),
      null,
      new UC(5, "VABS", pC, BS.hZ),
      new UC(6, "VABS", pC, BS.hZ),
      new UC(7, "VABS", pC, BS.Aj),
      null,
      new UC(9, "VNEG", pC, BS.hZ),
      new UC(10, "VNEG", pC, BS.hZ),
      new UC(11, "VNEG", pC, BS.Aj),
      null,
      new UC(13, "VSQRT", pC, BS.hZ),
      new UC(14, "VSQRT", pC, BS.hZ),
      new UC(15, "VSQRT", pC, BS.Aj),
      null,
      null,
      new UC(18, "VCVTB", Uf.Er, BS.hZ),
      new UC(19, "VCVTB", Uf.Aj, BS.cX),
      null,
      null,
      new UC(22, "VCVTT", Uf.Er, BS.hZ),
      new UC(23, "VCVTT", Uf.Aj, BS.cX),
      null,
      new UC(25, "VCVTB", Uf.pg, BS.hZ).pC(ze.UT),
      new UC(26, "VCVTB", Uf.LM, BS.hZ),
      new UC(27, "VCVTB", Uf.mv, BS.PR),
      null,
      new UC(29, "VCVTT", Uf.pg, BS.hZ).pC(ze.UT),
      new UC(30, "VCVTT", Uf.LM, BS.hZ),
      new UC(30, "VCVTT", Uf.mv, BS.PR),
      null,
      new UC(33, "VCMP", pC, BS.hZ),
      new UC(34, "VCMP", pC, BS.hZ),
      new UC(35, "VCMP", pC, BS.Aj),
      null,
      new UC(37, "VCMPE", pC, BS.hZ),
      new UC(38, "VCMPE", pC, BS.hZ),
      new UC(39, "VCMPE", pC, BS.Aj),
      null,
      new UC(41, "VCMP", pC, oT),
      new UC(42, "VCMP", pC, oT),
      new UC(43, "VCMP", pC, fI),
      null,
      new UC(45, "VCMPE", pC, oT),
      new UC(46, "VCMPE", pC, oT),
      new UC(47, "VCMPE", pC, fI),
      null,
      new UC(49, "VRINTR", pC, BS.hZ),
      new UC(50, "VRINTR", pC, BS.hZ),
      new UC(51, "VRINTR", pC, BS.Aj),
      null,
      new UC(53, "VRINTZ", pC, BS.hZ),
      new UC(54, "VRINTZ", pC, BS.hZ),
      new UC(55, "VRINTZ", pC, BS.Aj),
      null,
      new UC(57, "VRINTX", pC, BS.hZ),
      new UC(58, "VRINTX", pC, BS.hZ),
      new UC(59, "VRINTX", pC, BS.Aj),
      null,
      null,
      new UC(62, "VCVT", Uf.EX, BS.cX),
      new UC(63, "VCVT", Uf.FE, BS.PR)
   };
   private static final tz[] NS = new tz[]{
      null,
      null,
      new UC(2, "VCVT", Uf.os, BS.hZ).pC(ze.pC),
      new UC(3, "VCVT", Uf.hZ, BS.hZ).pC(ze.pC),
      new UC(4, "VCVT", Uf.DQ, BS.hZ),
      new UC(5, "VCVT", Uf.PR, BS.hZ),
      new UC(6, "VCVT", Uf.kU, BS.cX),
      new UC(7, "VCVT", Uf.e, BS.cX),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(15, "VJCVT", Uf.go, BS.PR).pC(ze.wS),
      null,
      null,
      new UC(18, "VCVT", Uf.Cu, BS.mv, BS.mv, sY).pC(ze.pC),
      new UC(19, "VCVT", Uf.hZ, BS.mv, BS.mv, ys).pC(ze.pC),
      new UC(20, "VCVT", Uf.UW, BS.mv, BS.mv, sY),
      new UC(21, "VCVT", Uf.PR, BS.mv, BS.mv, ys),
      new UC(22, "VCVT", Uf.RW, BS.z, BS.z, sY),
      new UC(23, "VCVT", Uf.e, BS.z, BS.z, ys),
      null,
      null,
      new UC(26, "VCVT", Uf.sO, BS.mv, BS.mv, sY).pC(ze.pC),
      new UC(27, "VCVT", Uf.os, BS.mv, BS.mv, ys).pC(ze.pC),
      new UC(28, "VCVT", Uf.cX, BS.mv, BS.mv, sY),
      new UC(29, "VCVT", Uf.DQ, BS.mv, BS.mv, ys),
      new UC(30, "VCVT", Uf.xM, BS.z, BS.z, sY),
      new UC(31, "VCVT", Uf.kU, BS.z, BS.z, ys),
      null,
      null,
      new UC(34, "VCVTR", Uf.Bf, BS.hZ).pC(ze.pC),
      new UC(35, "VCVT", Uf.Bf, BS.hZ).pC(ze.pC),
      new UC(36, "VCVTR", Uf.Bc, BS.hZ),
      new UC(37, "VCVT", Uf.Bc, BS.hZ),
      new UC(38, "VCVTR", Uf.Nq, BS.PR),
      new UC(39, "VCVT", Uf.Nq, BS.PR),
      null,
      null,
      new UC(42, "VCVTR", Uf.OI, BS.hZ).pC(ze.pC),
      new UC(43, "VCVT", Uf.OI, BS.hZ).pC(ze.pC),
      new UC(44, "VCVTR", Uf.OB, BS.hZ),
      new UC(45, "VCVT", Uf.OB, BS.hZ),
      new UC(46, "VCVTR", Uf.go, BS.PR),
      new UC(47, "VCVT", Uf.go, BS.PR),
      null,
      null,
      new UC(50, "VCVT", Uf.Pe, BS.mv, BS.mv, sY).pC(ze.pC),
      new UC(51, "VCVT", Uf.OI, BS.mv, BS.mv, ys).pC(ze.pC),
      new UC(52, "VCVT", Uf.ZN, BS.mv, BS.mv, sY),
      new UC(53, "VCVT", Uf.OB, BS.mv, BS.mv, ys),
      new UC(54, "VCVT", Uf.Kq, BS.z, BS.z, sY),
      new UC(55, "VCVT", Uf.go, BS.z, BS.z, ys),
      null,
      null,
      new UC(58, "VCVT", Uf.ck, BS.mv, BS.mv, sY).pC(ze.pC),
      new UC(59, "VCVT", Uf.Bf, BS.mv, BS.mv, ys).pC(ze.pC),
      new UC(60, "VCVT", Uf.pF, BS.mv, BS.mv, sY),
      new UC(61, "VCVT", Uf.Bc, BS.mv, BS.mv, ys),
      new UC(62, "VCVT", Uf.JF, BS.z, BS.z, sY),
      new UC(63, "VCVT", Uf.Nq, BS.z, BS.z, ys)
   };

   public static tz pC(byte[] var0, int var1) {
      int var2 = (var0[1] & 240) >>> 4;
      int var3 = (var0[3] & 64) >>> 6;
      int var5 = var0[2] & 3;
      if ((var2 & 11) == 11) {
         int var10 = (var0[3] & 128) >>> 7;
         if (var3 == 0) {
            if ((var0[3] & 160) != 0) {
               return null;
            } else {
               return ze.pC(var0) && !ze.A(var0) ? null : gp[var5];
            }
         } else {
            int var7 = var0[1] & 8;
            int var8 = var0[1] & 7;
            if (var7 == 0) {
               int var11 = var8 << 3 | var10 << 2 | var5;
               if (var8 != 3 && ze.pC(var0) && !ze.A(var0)) {
                  return null;
               } else {
                  return var8 == 5 && (var0[3] & 47) != 0 ? null : WR[var11];
               }
            } else {
               int var9 = var8 << 3 | var5 << 1 | var10;
               return ze.pC(var0) && !ze.A(var0) ? null : NS[var9];
            }
         }
      } else {
         int var6 = (var0[1] & 128) >>> 5 | (var0[1] & 48) >>> 4;
         return ze.pC(var0) && !ze.A(var0) ? null : ld[var6 << 3 | var3 << 2 | var5];
      }
   }
}
