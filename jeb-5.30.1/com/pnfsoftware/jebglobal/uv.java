package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class uv implements yu {
   private static final Je[] LK = new Je[]{new Qg("SHA512SU0", lm, zz).q(QJ.PQ), new Qg("SM4E", gO, nf).q(QJ.bl), null, null};
   private static final Je[] io = new Je[]{
      new Qg(0, "SHA512H", XD.Bu, XD.IN, JY).q(QJ.PQ),
      new Qg(1, "SHA512H2", XD.Bu, XD.IN, JY).q(QJ.PQ),
      new Qg(2, "SHA512SU1", lm, zz, JY).q(QJ.PQ),
      new Qg(3, "RAX1", lm, zz, JY).q(QJ.fQ),
      new Qg(4, "SM3PARTW1", gO, nf, gP).q(QJ.fi),
      new Qg(4, "SM3PARTW2", gO, nf, gP).q(QJ.fi),
      new Qg(4, "SM4EKEY", gO, nf, gP).q(QJ.bl),
      null
   };
   private static final Ef qa = new Df(16, Dm.xK, DirectEncodedMemoryArea.get(12, 2));
   private static final Je[] Hk = new Je[]{
      new Qg("SM3TT1A", gO, nf, qa).q(QJ.fi),
      new Qg("SM3TT1B", gO, nf, qa).q(QJ.fQ),
      new Qg("SM3TT2A", gO, nf, qa).q(QJ.fi),
      new Qg("SM3TT2B", gO, nf, qa).q(QJ.fi)
   };
   private static final Je[] Me = new Je[]{
      new Qg("EOR3", q, RF, xK, Dw).q(QJ.fQ), new Qg("BCAX", q, RF, xK, Dw).q(QJ.fQ), new Qg("SM3SS1", gO, nf, gP, za).q(QJ.fi), null
   };
   private static final Je PV = new Qg("XAR", lm, zz, JY, go.If).q(QJ.fQ);

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 120) >>> 3;
      int var3 = DirectEncodedMemoryArea.decodeInt(10, 9, var0);
      int var4 = (var0[2] & 64) >>> 6;
      int var5 = (var0[2] & 12) >>> 2;
      if (var1 == 0) {
         if ((var3 & 32) == 0) {
            int var6 = (var0[1] & 96) >>> 5;
            return Qg.q(Me, var6, var0, "Cryptographic four-register");
         }

         if ((var2 & 12) == 8 && (var3 & 48) == 32) {
            return Qg.q(Hk, var5, var0, "Cryptographic tree-register, imm2");
         }

         if ((var2 & 12) == 12 && (var3 & 44) == 32) {
            return Qg.q(io, var4 << 2 | var5, var0, "Cryptographic tree-register SHA 512");
         }
      } else if (var1 == 1) {
         if (var2 < 4) {
            return PV;
         }

         if (var2 == 8 && (var3 & 508) == 32) {
            return Qg.q(LK, var5, var0, "Cryptographic two-register SHA 512");
         }
      }

      return Qg.q(var0, "Cryptographic");
   }
}
