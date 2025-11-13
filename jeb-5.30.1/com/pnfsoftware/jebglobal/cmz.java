package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;

public class cmz {
   public static Long q(IMachineContext var0, long var1, int var3) {
      return RF(var0, var1, var3 == 64 ? 64 : 32);
   }

   private static Long RF(IMachineContext var0, long var1, int var3) {
      if (var0 != null && var0.getRegisters() != null) {
         byte var4 = 4;
         byte[] var5 = var0.getRegisters().getValue((int)var1);
         if (var0.getInformation().getEndianness().isLittle()) {
            EndianUtil.swapByGroup(var5, var3 == 64 ? 8 : 4);
         }

         if (var5.length > var4) {
            byte[] var6 = new byte[var4];
            System.arraycopy(var5, var5.length - var4, var6, 0, var4);
            var5 = var6;
         }

         return EncodedMemoryAreaUtil.zeroExtend(var5, 0, var3);
      } else {
         return null;
      }
   }
}
