package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class hC implements Bf {
   private static final tz[] vP = new tz[]{new UC("SHA512SU0", oT, fI).pC(Le.WR), new UC("SM4E", sY, ys).pC(Le.xC), null, null};
   private static final tz[] xC = new tz[]{
      new UC(0, "SHA512H", ER.Cu, ER.hZ, WR).pC(Le.WR),
      new UC(1, "SHA512H2", ER.Cu, ER.hZ, WR).pC(Le.WR),
      new UC(2, "SHA512SU1", oT, fI, WR).pC(Le.WR),
      new UC(3, "RAX1", oT, fI, WR).pC(Le.NS),
      new UC(4, "SM3PARTW1", sY, ys, ld).pC(Le.vP),
      new UC(4, "SM3PARTW2", sY, ys, ld).pC(Le.vP),
      new UC(4, "SM4EKEY", sY, ys, ld).pC(Le.xC),
      null
   };
   private static final Hu ED = new PP(16, IX.kS, DirectEncodedMemoryArea.get(12, 2));
   private static final tz[] Sc = new tz[]{
      new UC("SM3TT1A", sY, ys, ED).pC(Le.vP),
      new UC("SM3TT1B", sY, ys, ED).pC(Le.NS),
      new UC("SM3TT2A", sY, ys, ED).pC(Le.vP),
      new UC("SM3TT2B", sY, ys, ED).pC(Le.vP)
   };
   private static final tz[] ah = new tz[]{
      new UC("EOR3", pC, A, kS, wS).pC(Le.NS), new UC("BCAX", pC, A, kS, wS).pC(Le.NS), new UC("SM3SS1", sY, ys, ld, gp).pC(Le.vP), null
   };
   private static final tz eP = new UC("XAR", oT, fI, WR, IV.rl).pC(Le.NS);

   public static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 120) >>> 3;
      int var3 = DirectEncodedMemoryArea.decodeInt(10, 9, var0);
      int var4 = (var0[2] & 64) >>> 6;
      int var5 = (var0[2] & 12) >>> 2;
      if (var1 == 0) {
         if ((var3 & 32) == 0) {
            int var6 = (var0[1] & 96) >>> 5;
            return UC.pC(ah, var6, var0, "Cryptographic four-register");
         }

         if ((var2 & 12) == 8 && (var3 & 48) == 32) {
            return UC.pC(Sc, var5, var0, "Cryptographic tree-register, imm2");
         }

         if ((var2 & 12) == 12 && (var3 & 44) == 32) {
            return UC.pC(xC, var4 << 2 | var5, var0, "Cryptographic tree-register SHA 512");
         }
      } else if (var1 == 1) {
         if (var2 < 4) {
            return eP;
         }

         if (var2 == 8 && (var3 & 508) == 32) {
            return UC.pC(vP, var5, var0, "Cryptographic two-register SHA 512");
         }
      }

      return UC.pC(var0, "Cryptographic");
   }
}
