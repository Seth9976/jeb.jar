package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class zN {
   private static final de q = new Zo.eo('F', DirectEncodedMemoryArea.get(8, 2), 1, 3, 0);
   private static final de RF = new Zo.eo('F', DirectEncodedMemoryArea.get(8, 2), 2, 3, 0);
   private static final IEncodedMemoryArea xK = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 4), DirectEncodedMemoryArea.get(0, 4));
   private static final Ef Dw = new ub(16, xK);
   private static final Ef Uv = new ub(32, xK);
   private static final Ef oW = new ub(64, xK);
   private static final Ef gO = new jO(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(0, 4), DirectEncodedMemoryArea.get(5, 1)), 16);
   private static final Ef nf = new jO(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(0, 4), DirectEncodedMemoryArea.get(5, 1)), 32);
   private static final Je[] gP = new Je[]{
      null,
      new Qg(1, "VMLA", q, Y.IN),
      new Qg(2, "VMLA", q, Y.IN),
      new Qg(3, "VMLA", q, Y.jq),
      null,
      new Qg(5, "VMLS", q, Y.IN),
      new Qg(6, "VMLS", q, Y.IN),
      new Qg(7, "VMLS", q, Y.jq),
      null,
      new Qg(9, "VNMLS", q, Y.IN),
      new Qg(10, "VNMLS", q, Y.IN),
      new Qg(11, "VNMLS", q, Y.jq),
      null,
      new Qg(13, "VNMLA", q, Y.IN),
      new Qg(14, "VNMLA", q, Y.IN),
      new Qg(15, "VNMLA", q, Y.jq),
      null,
      new Qg(17, "VMUL", q, Y.IN),
      new Qg(18, "VMUL", q, Y.IN),
      new Qg(19, "VMUL", q, Y.jq),
      null,
      new Qg(21, "VNMUL", q, Y.IN),
      new Qg(22, "VNMUL", q, Y.IN),
      new Qg(23, "VNMUL", q, Y.jq),
      null,
      new Qg(25, "VADD", q, Y.IN),
      new Qg(26, "VADD", q, Y.IN),
      new Qg(27, "VADD", q, Y.jq),
      null,
      new Qg(29, "VSUB", q, Y.IN),
      new Qg(30, "VSUB", q, Y.IN),
      new Qg(31, "VSUB", q, Y.jq),
      null,
      new Qg(33, "VDIV", q, Y.IN),
      new Qg(34, "VDIV", q, Y.IN),
      new Qg(35, "VDIV", q, Y.jq),
      null,
      null,
      null,
      null,
      null,
      new Qg(41, "VFNMS", q, Y.IN),
      new Qg(42, "VFNMS", q, Y.IN),
      new Qg(43, "VFNMS", q, Y.jq),
      null,
      new Qg(45, "VFNMA", q, Y.IN),
      new Qg(46, "VFNMA", q, Y.IN),
      new Qg(47, "VFNMA", q, Y.jq),
      null,
      new Qg(49, "VFMA", q, Y.IN),
      new Qg(50, "VFMA", q, Y.IN),
      new Qg(51, "VFMA", q, Y.jq),
      null,
      new Qg(53, "VFMS", q, Y.IN),
      new Qg(54, "VFMS", q, Y.IN),
      new Qg(55, "VFMS", q, Y.jq),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
   };
   private static final Je[] za = new Je[]{null, new Qg(1, "VMOV", q, Y.TX, Dw), new Qg(2, "VMOV", q, Y.TX, Uv), new Qg(3, "VMOV", q, Y.sH, oW)};
   private static final Ef[] lm = new Ef[]{Y.TX, Nd.q(Nd.Dw)};
   private static final Ef[] zz = new Ef[]{Y.sH, Nd.q(Nd.Uv)};
   private static final Je[] JY = new Je[]{
      null,
      null,
      new Qg(2, "VMOV", RF, Y.Bu),
      new Qg(3, "VMOV", RF, Y.mI),
      null,
      new Qg(5, "VABS", q, Y.Bu),
      new Qg(6, "VABS", q, Y.Bu),
      new Qg(7, "VABS", q, Y.mI),
      null,
      new Qg(9, "VNEG", q, Y.Bu),
      new Qg(10, "VNEG", q, Y.Bu),
      new Qg(11, "VNEG", q, Y.mI),
      null,
      new Qg(13, "VSQRT", q, Y.Bu),
      new Qg(14, "VSQRT", q, Y.Bu),
      new Qg(15, "VSQRT", q, Y.mI),
      null,
      null,
      new Qg(18, "VCVTB", Zo.sH, Y.Bu),
      new Qg(19, "VCVTB", Zo.wF, Y.eJ),
      null,
      null,
      new Qg(22, "VCVTT", Zo.sH, Y.Bu),
      new Qg(23, "VCVTT", Zo.wF, Y.eJ),
      null,
      new Qg(25, "VCVTB", Zo.lF, Y.Bu).q(MX.HF),
      new Qg(26, "VCVTB", Zo.Dz, Y.Bu),
      new Qg(27, "VCVTB", Zo.mI, Y.rL),
      null,
      new Qg(29, "VCVTT", Zo.lF, Y.Bu).q(MX.HF),
      new Qg(30, "VCVTT", Zo.Dz, Y.Bu),
      new Qg(30, "VCVTT", Zo.mI, Y.rL),
      null,
      new Qg(33, "VCMP", q, Y.Bu),
      new Qg(34, "VCMP", q, Y.Bu),
      new Qg(35, "VCMP", q, Y.mI),
      null,
      new Qg(37, "VCMPE", q, Y.Bu),
      new Qg(38, "VCMPE", q, Y.Bu),
      new Qg(39, "VCMPE", q, Y.mI),
      null,
      new Qg(41, "VCMP", q, lm),
      new Qg(42, "VCMP", q, lm),
      new Qg(43, "VCMP", q, zz),
      null,
      new Qg(45, "VCMPE", q, lm),
      new Qg(46, "VCMPE", q, lm),
      new Qg(47, "VCMPE", q, zz),
      null,
      new Qg(49, "VRINTR", q, Y.Bu),
      new Qg(50, "VRINTR", q, Y.Bu),
      new Qg(51, "VRINTR", q, Y.mI),
      null,
      new Qg(53, "VRINTZ", q, Y.Bu),
      new Qg(54, "VRINTZ", q, Y.Bu),
      new Qg(55, "VRINTZ", q, Y.mI),
      null,
      new Qg(57, "VRINTX", q, Y.Bu),
      new Qg(58, "VRINTX", q, Y.Bu),
      new Qg(59, "VRINTX", q, Y.mI),
      null,
      null,
      new Qg(62, "VCVT", Zo.If, Y.eJ),
      new Qg(63, "VCVT", Zo.CE, Y.rL)
   };
   private static final Je[] HF = new Je[]{
      null,
      null,
      new Qg(2, "VCVT", Zo.ui, Y.Bu).q(MX.za),
      new Qg(3, "VCVT", Zo.Rr, Y.Bu).q(MX.za),
      new Qg(4, "VCVT", Zo.IN, Y.Bu),
      new Qg(5, "VCVT", Zo.Xo, Y.Bu),
      new Qg(6, "VCVT", Zo.WI, Y.eJ),
      new Qg(7, "VCVT", Zo.AB, Y.eJ),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(15, "VJCVT", Zo.Yp, Y.rL).q(MX.JY),
      null,
      null,
      new Qg(18, "VCVT", Zo.TX, Y.TX, Y.TX, gO).q(MX.za),
      new Qg(19, "VCVT", Zo.Rr, Y.TX, Y.TX, nf).q(MX.za),
      new Qg(20, "VCVT", Zo.EB, Y.TX, Y.TX, gO),
      new Qg(21, "VCVT", Zo.Xo, Y.TX, Y.TX, nf),
      new Qg(22, "VCVT", Zo.Wx, Y.sH, Y.sH, gO),
      new Qg(23, "VCVT", Zo.AB, Y.sH, Y.sH, nf),
      null,
      null,
      new Qg(26, "VCVT", Zo.jq, Y.TX, Y.TX, gO).q(MX.za),
      new Qg(27, "VCVT", Zo.ui, Y.TX, Y.TX, nf).q(MX.za),
      new Qg(28, "VCVT", Zo.Bu, Y.TX, Y.TX, gO),
      new Qg(29, "VCVT", Zo.IN, Y.TX, Y.TX, nf),
      new Qg(30, "VCVT", Zo.CY, Y.sH, Y.sH, gO),
      new Qg(31, "VCVT", Zo.WI, Y.sH, Y.sH, nf),
      null,
      null,
      new Qg(34, "VCVTR", Zo.ZT, Y.Bu).q(MX.za),
      new Qg(35, "VCVT", Zo.ZT, Y.Bu).q(MX.za),
      new Qg(36, "VCVTR", Zo.Rv, Y.Bu),
      new Qg(37, "VCVT", Zo.Rv, Y.Bu),
      new Qg(38, "VCVTR", Zo.nY, Y.rL),
      new Qg(39, "VCVT", Zo.nY, Y.rL),
      null,
      null,
      new Qg(42, "VCVTR", Zo.zx, Y.Bu).q(MX.za),
      new Qg(43, "VCVT", Zo.zx, Y.Bu).q(MX.za),
      new Qg(44, "VCVTR", Zo.eJ, Y.Bu),
      new Qg(45, "VCVT", Zo.eJ, Y.Bu),
      new Qg(46, "VCVTR", Zo.Yp, Y.rL),
      new Qg(47, "VCVT", Zo.Yp, Y.rL),
      null,
      null,
      new Qg(50, "VCVT", Zo.Ri, Y.TX, Y.TX, gO).q(MX.za),
      new Qg(51, "VCVT", Zo.zx, Y.TX, Y.TX, nf).q(MX.za),
      new Qg(52, "VCVT", Zo.rL, Y.TX, Y.TX, gO),
      new Qg(53, "VCVT", Zo.eJ, Y.TX, Y.TX, nf),
      new Qg(54, "VCVT", Zo.Tq, Y.sH, Y.sH, gO),
      new Qg(55, "VCVT", Zo.Yp, Y.sH, Y.sH, nf),
      null,
      null,
      new Qg(58, "VCVT", Zo.GY, Y.TX, Y.TX, gO).q(MX.za),
      new Qg(59, "VCVT", Zo.ZT, Y.TX, Y.TX, nf).q(MX.za),
      new Qg(60, "VCVT", Zo.YN, Y.TX, Y.TX, gO),
      new Qg(61, "VCVT", Zo.Rv, Y.TX, Y.TX, nf),
      new Qg(62, "VCVT", Zo.Gu, Y.sH, Y.sH, gO),
      new Qg(63, "VCVT", Zo.nY, Y.sH, Y.sH, nf)
   };

   public static Je q(byte[] var0, int var1) {
      int var2 = (var0[1] & 240) >>> 4;
      int var3 = (var0[3] & 64) >>> 6;
      int var5 = var0[2] & 3;
      if ((var2 & 11) == 11) {
         int var10 = (var0[3] & 128) >>> 7;
         if (var3 == 0) {
            if ((var0[3] & 160) != 0) {
               return null;
            } else {
               return MX.q(var0) && !MX.RF(var0) ? null : za[var5];
            }
         } else {
            int var7 = var0[1] & 8;
            int var8 = var0[1] & 7;
            if (var7 == 0) {
               int var11 = var8 << 3 | var10 << 2 | var5;
               if (var8 != 3 && MX.q(var0) && !MX.RF(var0)) {
                  return null;
               } else {
                  return var8 == 5 && (var0[3] & 47) != 0 ? null : JY[var11];
               }
            } else {
               int var9 = var8 << 3 | var5 << 1 | var10;
               return MX.q(var0) && !MX.RF(var0) ? null : HF[var9];
            }
         }
      } else {
         int var6 = (var0[1] & 128) >>> 5 | (var0[1] & 48) >>> 4;
         return MX.q(var0) && !MX.RF(var0) ? null : gP[var6 << 3 | var3 << 2 | var5];
      }
   }
}
