package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class Oq {
   public static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 240) >>> 4;
      switch (var1) {
         case 0:
         case 2:
         case 4:
         case 6:
            return Gh.pC(var0);
         case 1:
         case 3:
         case 9:
         case 11:
            return fo.pC(var0);
         case 5:
         case 7:
            return ZO.pC(var0);
         case 8:
         case 10:
         case 13:
         case 14:
         case 15:
         default:
            return UC.pC(var0, "SIMD");
         case 12:
            return hC.pC(var0);
      }
   }
}
