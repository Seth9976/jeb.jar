package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class AD implements Hu {
   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = DirectEncodedMemoryArea.get(13, 3).decodeInt(var1);
      int var4 = DirectEncodedMemoryArea.get(10, 3).decodeInt(var1);
      int var5 = var1[0] & 128;
      int var6 = (var1[0] & 32) >>> 5;
      int var7 = DirectEncodedMemoryArea.get(0, 5).decodeInt(var1);
      int var8 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var1);
      Yg var9;
      if (var5 == 0) {
         var9 = sQ.Ab.buildOperand(var1, var2);
      } else if (var3 != 3 && var3 != 7) {
         var9 = sQ.Ab.buildOperand(var1, var2);
      } else {
         var9 = sQ.ld.buildOperand(var1, var2);
      }

      Z.Av var10 = Z.Av.values()[var3 + 6];
      if ((var5 == 0 && var3 == 2 || var5 != 0 && var3 == 3) && (var6 == 0 && (var7 == 31 || var8 == 31) || var6 != 0 && var8 == 31)) {
         if (var4 == 0) {
            return var9;
         }

         var10 = Z.Av.pC;
      }

      if (var4 == 0 && var3 != 8) {
         return ZV.pC(var9, var10);
      } else if (var4 > 4) {
         throw new ProcessorException("Illegal shift value for Register extended");
      } else {
         return ZV.pC(var9, var10, var4);
      }
   }
}
