package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeAnalyzerUtil;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class Cx {
   private final INativeCodeAnalyzer pC;
   private final BinaryPatternVerifier A;

   public Cx(INativeCodeAnalyzer var1, BinaryPatternVerifier var2) {
      this.pC = var1;
      this.A = var2;
      var2.addPatterns(
         this.pC(new byte[]{0, 0}, new byte[]{-1, -1}, 2, 16),
         this.pC(new byte[]{0, 0, 0, 0}, new byte[]{-1, -1, -1, -1}, 4, 32),
         this.pC(new byte[]{-43, 3, 32, 31}, new byte[]{-1, -1, -1, -1}, 4, 64),
         this.pC(new byte[]{0, 0, 0, 0}, new byte[]{-1, -1, -1, -1}, 4, 64),
         this.pC(new byte[]{-31, -96, 0, 0}, new byte[]{-1, -1, -1, -1}, 4, 32),
         this.pC(new byte[]{-29, 32, -16, 0}, new byte[]{-1, -1, -1, -1}, 4, 32),
         this.pC(new byte[]{70, -64}, new byte[]{-1, -1}, 2, 16),
         this.pC(new byte[]{-65, 0}, new byte[]{-1, -1}, 2, 16),
         this.pC(new byte[]{-128, 0, -13, -81}, new byte[]{-1, -1, -1, -1}, 4, 16)
      );
   }

   private BinaryPattern pC(byte[] var1, byte[] var2, int var3, int var4) {
      return Iw.pC(((com.pnfsoftware.jeb.corei.parsers.arm.cq)this.pC.getProcessor()).pC(), var1, var2, var3, var4);
   }

   public int pC(long var1) {
      return this.pC(var1, var1 + 48L);
   }

   int pC(long var1, long var3) {
      byte var5 = 0;

      try {
         if (this.pC.getProcessor().getMode() == 64) {
            var5 = 64;
         } else if (var1 % 4L == 2L) {
            var5 = 16;
         } else if ((this.pC.getMemory().readInt(var1) & -536870912) == -536870912) {
            var5 = 32;
         }
      } catch (MemoryException var6) {
         var5 = 32;
      }

      return this.pC(var1, var3, var5);
   }

   public int pC(long var1, long var3, int var5) {
      IBinaryPattern var6 = CodeAnalyzerUtil.checkBinaryPattern(this.pC, this.A, var1, var1 + 4L, 0, var5);
      if (var6 == null) {
         return 0;
      } else {
         int var7 = var6.getBinary().length;
         long var8 = var1 + var7;
         return var8 < var3 ? var7 + this.pC(var8, var3, var5) : var7;
      }
   }
}
