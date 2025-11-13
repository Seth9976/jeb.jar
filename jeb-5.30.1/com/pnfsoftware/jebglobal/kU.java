package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public class kU {
   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 240) >>> 4;
      switch (var1) {
         case 0:
         case 2:
         case 4:
         case 6:
            return wS.q(var0);
         case 1:
         case 3:
         case 9:
         case 11:
            return GD.q(var0);
         case 5:
         case 7:
            return sf.q(var0);
         case 8:
         case 10:
         case 13:
         case 14:
         case 15:
         default:
            return Qg.q(var0, "SIMD");
         case 12:
            return uv.q(var0);
      }
   }

   public static Je q(byte[] var0, String var1) throws ProcessorException {
      throw new ProcessorException(Strings.ff("Instruction %s is WIP. See section [%s]", Formatter.byteArrayToHex(var0), var1));
   }
}
