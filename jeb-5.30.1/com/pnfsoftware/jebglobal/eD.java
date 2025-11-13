package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class eD implements Ef {
   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = DirectEncodedMemoryArea.get(13, 3).decodeInt(var1);
      int var4 = DirectEncodedMemoryArea.get(10, 3).decodeInt(var1);
      int var5 = var1[0] & 128;
      int var6 = (var1[0] & 32) >>> 5;
      int var7 = DirectEncodedMemoryArea.get(0, 5).decodeInt(var1);
      int var8 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var1);
      CW var9;
      if (var5 == 0) {
         var9 = YH.Ef.buildOperand(var1, var2);
      } else if (var3 != 3 && var3 != 7) {
         var9 = YH.Ef.buildOperand(var1, var2);
      } else {
         var9 = YH.zz.buildOperand(var1, var2);
      }

      DH.eo var10 = DH.eo.values()[var3 + 6];
      if ((var5 == 0 && var3 == 2 || var5 != 0 && var3 == 3) && (var6 == 0 && (var7 == 31 || var8 == 31) || var6 != 0 && var8 == 31)) {
         if (var4 == 0) {
            return var9;
         }

         var10 = DH.eo.q;
      }

      if (var4 == 0 && var3 != 8) {
         return ZD.q(var9, var10);
      } else if (var4 > 4) {
         throw new ProcessorException("Illegal shift value for Register extended");
      } else {
         return ZD.q(var9, var10, var4);
      }
   }
}
