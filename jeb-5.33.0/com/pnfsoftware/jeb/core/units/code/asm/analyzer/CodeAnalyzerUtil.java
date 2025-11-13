package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;

public class CodeAnalyzerUtil {
   public static IBinaryPattern checkBinaryPattern(INativeCodeAnalyzer var0, BinaryPatternVerifier var1, long var2, long var4) {
      return checkBinaryPattern(var0, var1, var2, var4, 0, 0);
   }

   public static IBinaryPattern checkBinaryPattern(INativeCodeAnalyzer var0, BinaryPatternVerifier var1, long var2, long var4, int var6, int var7) {
      int var8 = Math.min(var1.getLongestSize(), (int)(var4 - var2));
      byte[] var9 = new byte[var8];
      return !VirtualMemoryUtil.readSafe(var0.getMemory(), var2, var9) ? null : var1.verify(var0, var2, var9, 0, var9.length, var6, var7);
   }
}
