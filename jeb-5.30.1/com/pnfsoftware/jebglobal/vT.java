package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class vT {
   private static final IEncodedMemoryArea q = DirectEncodedMemoryArea.get(18, 2);
   private static final de RF = new Zo.eo(null, q, 2);
   private static final de xK = new Zo.eo(null, q, 1);
   private static final de Dw = new Zo.eo(null, q, 0);
   private static final de Uv = new Zo.eo('S', q, 2);
   private static final de oW = new Zo.eo('U', q, 2);
   private static final de gO = new Zo.eo('I', q, 2);
   private static final de nf = new Zo.eo('I', q, 0, 2, 1);
   private static final de gP = new Zo.eo('S', q, 0, 2, 1);
   private static final de za = new Zo.eo('U', q, 0, 2, 1);
   private static final de lm = new Zo.eo(null, q, 2, 2, 0);
   private static final de zz = new Zo.eo('U', q, 1, 2, 0);
   private static final de JY = new Zo.eo('S', q, 1, 2, 0);
   private static final de HF = new Zo.eo('F', q, 1, 2, 0);
   private static final de LK = new Zo.eo('U', q, 2, 2, 0);
   private static final Zo.CU io = new Zo.CU(JY, HF);
   private static final Zo.CU qa = new Zo.CU(zz, HF);
   private static final Zo.CU Hk = new Zo.CU(HF, JY);
   private static final Zo.CU Me = new Zo.CU(HF, zz);
   private static final Ef PV = new Mf(var0 -> 8 << q.decodeInt(var0));
   private static final Je[] oQ = new Je[]{
      new Qg(0, "VREV64", RF, Y.PV),
      new Qg(1, "VREV32", xK, Y.PV),
      new Qg(2, "VREV16", Dw, Y.PV),
      null,
      new Qg(4, "VPADDL", Uv, Y.PV),
      new Qg(5, "VPADDL", oW, Y.PV),
      null,
      null,
      new Qg(8, "VCLS", Uv, Y.PV),
      new Qg(9, "VCLZ", gO, Y.PV),
      new Qg(10, "VCNT", Dw, Y.PV),
      new Qg(11, "VMVN", Y.PV).q(q),
      new Qg(12, "VPADAL", Uv, Y.PV),
      new Qg(13, "VPADAL", oW, Y.PV),
      new Qg(14, "VQABS", Uv, Y.PV),
      new Qg(15, "VQNEG", Uv, Y.PV)
   };
   private static final dD xW = new dD(q, 512L, 1L, 1L, 1L);
   private static final Je[] KT = new Je[]{
      new Qg("AESE", Y.Ef).q(Zo.LK).q(xW), new Qg("AESD", Y.Ef).q(Zo.LK).q(xW), new Qg("AESMC", Y.Ef).q(Zo.LK).q(xW), new Qg("AESIMC", Y.Ef).q(Zo.LK).q(xW)
   };
   private static final Je[] Gf = new Je[]{
      new Qg(0, "VCGT", Uv, Y.qa, Y.Me, go.Ri),
      new Qg(1, "VCGE", Uv, Y.qa, Y.Me, go.Ri),
      new Qg(2, "VCEQ", gO, Y.qa, Y.Me, go.Ri),
      new Qg(3, "VCLE", Uv, Y.qa, Y.Me, go.Ri),
      new Qg(4, "VCLT", Uv, Y.qa, Y.Me, go.Ri),
      null,
      new Qg(6, "VABS", Uv, Y.PV),
      new Qg(7, "VNEG", Uv, Y.PV)
   };
   private static final dD Ef = new dD(q, 1L, 16L, 0L, 1L);
   private static final Je[] cC = new Je[]{
      new Qg(8, "VCGT", HF, Y.qa, Y.Me, go.Ri).q(Ef),
      new Qg(9, "VCGE", HF, Y.qa, Y.Me, go.Ri).q(Ef),
      new Qg(10, "VCEQ", HF, Y.qa, Y.Me, go.Ri).q(Ef),
      new Qg(11, "VCLE", HF, Y.qa, Y.Me, go.Ri).q(Ef),
      new Qg(12, "VCLT", HF, Y.qa, Y.Me, go.Ri).q(Ef),
      null,
      new Qg(14, "VABS", HF, Y.PV).q(Ef),
      new Qg(15, "VNEG", HF, Y.PV).q(Ef)
   };
   private static final Je[] sH = new Je[]{null, new Qg("SHA1H", Y.Ef).q(lm)};
   private static final Je[] CE = new Je[]{
      new Qg(0, "VSWP", Y.PV).q(q),
      new Qg(1, "VSWP", Y.PV).q(q),
      new Qg(2, "VTRN", RF, Y.PV),
      new Qg(3, "VTRN", RF, Y.PV),
      new Qg(4, "VUZP", xK, Y.PV),
      new Qg(5, "VUZP", RF, Y.PV),
      new Qg(6, "VZIP", xK, Y.PV),
      new Qg(7, "VZIP", RF, Y.PV),
      new Qg(8, "VMOVN", nf, Y.sH, Y.Gf),
      new Qg(9, "VQMOVUN", gP, Y.sH, Y.Gf),
      new Qg(10, "VQMOVN", gP, Y.sH, Y.Gf),
      new Qg(11, "VQMOVN", za, Y.sH, Y.Gf),
      new Qg(12, "VSHLL", gO, Y.xW, Y.wF, PV),
      null,
      new Qg(14, "SHA1SU1", lm, Y.Ef),
      new Qg(15, "SHA256SU0", lm, Y.Ef)
   };
   private static final Je[] wF = new Je[]{
      new Qg(8, "VRINTN", HF, Y.PV).q(MX.za),
      new Qg(9, "VRINTX", HF, Y.PV).q(MX.za),
      new Qg(10, "VRINTA", HF, Y.PV).q(MX.za),
      new Qg(11, "VRINTZ", HF, Y.PV).q(MX.za),
      null,
      new Qg(13, "VRINTM", HF, Y.PV).q(MX.za),
      null,
      new Qg(15, "VRINTP", HF, Y.PV).q(MX.za)
   };
   private static final dD If = new dD(q, 1L, 0L, 1L, 1L);
   private static final Je[] Dz = new Je[]{
      new Qg(0, "VCVT", Zo.Dz, Y.sH, Y.Gf).q(If), new Qg(0, "VCVT", Zo.lF, Y.sH, Y.Gf).q(If, MX.HF), new Qg(1, "VCVT", Zo.sH, Y.xW, Y.wF).q(If), null
   };
   private static final Je[] mI = new Je[]{
      new Qg(0, "VCVTA", io, Y.PV).q(Ef),
      new Qg(1, "VCVTA", qa, Y.PV).q(Ef),
      new Qg(2, "VCVTN", io, Y.PV).q(Ef),
      new Qg(3, "VCVTN", qa, Y.PV).q(Ef),
      new Qg(4, "VCVTP", io, Y.PV).q(Ef),
      new Qg(5, "VCVTP", qa, Y.PV).q(Ef),
      new Qg(6, "VCVTM", io, Y.PV).q(Ef),
      new Qg(7, "VCVTM", qa, Y.PV).q(Ef),
      new Qg(8, "VRECPE", LK, Y.PV).q(Ef),
      new Qg(9, "VRSQRTE", LK, Y.PV).q(Ef),
      new Qg(10, "VRECPE", HF, Y.PV).q(Ef),
      new Qg(11, "VRSQRTE", HF, Y.PV).q(Ef),
      new Qg(12, "VCVT", Hk, Y.PV).q(Ef),
      new Qg(13, "VCVT", Me, Y.PV).q(Ef),
      new Qg(14, "VCVT", io, Y.PV).q(Ef),
      new Qg(15, "VCVT", qa, Y.PV).q(Ef)
   };

   public static Je q(byte[] var0, int var1) {
      int var2 = var0[1] & 3;
      int var3 = (var0[2] & 7) << 2 | (var0[3] & 192) >>> 6;
      switch (var2) {
         case 0:
            if (var3 >= 12 && var3 <= 15) {
               return KT[var3 & 3];
            }

            return oQ[var3 >>> 1];
         case 1:
            if ((var3 & 30) == 10) {
               return sH[var3 & 1];
            } else {
               if (var3 >= 16) {
                  return cC[(var3 & 14) >>> 1];
               }

               return Gf[var3 >>> 1];
            }
         case 2:
            if (var3 >= 16) {
               if ((var3 & 26) == 24) {
                  int var4 = (var0[2] & 1) << 1 | var3 & 1;
                  return Dz[var4];
               }

               return wF[(var3 & 14) >>> 1];
            }

            return CE[var3];
         case 3:
            return mI[var3 >>> 1];
         default:
            return null;
      }
   }
}
