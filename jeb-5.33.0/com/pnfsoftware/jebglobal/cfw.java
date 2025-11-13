package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class cfw implements com.pnfsoftware.jeb.corei.parsers.mips.Av {
   @Override
   public CharSequence pC(byte[] var1) {
      int var2 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var1);
      switch (var2) {
         case 16:
            return ".S";
         case 17:
            return ".D";
         case 18:
         case 19:
         default:
            return null;
         case 20:
            return ".W";
         case 21:
            return ".L";
         case 22:
            return ".PS";
      }
   }
}
