package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cji extends BinaryPattern {
   public cji() {
      super(new byte[]{72, -1, 37});
   }

   @Override
   public boolean validate(INativeCodeAnalyzer var1, long var2, byte[] var4, int var5, int var6) {
      long var7;
      if (var5 + 7 <= var4.length) {
         var7 = EndianUtil.littleEndianBytesToInt(var4, var5 + 3) & 4294967295L;
      } else {
         int[] var9 = new int[1];
         if (!VirtualMemoryUtil.readLEIntSafe(var1.getMemory(), var2 + 3L, var9)) {
            return false;
         }

         var7 = var9[0];
      }

      int var12 = var1.getMemory().getSpaceBits();
      if (var12 == 64) {
         long var10 = var2 + 7L + var7;
         return var1.getAnalysisRanges().contains(var10);
      } else {
         return false;
      }
   }
}
