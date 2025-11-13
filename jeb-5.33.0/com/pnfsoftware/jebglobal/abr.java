package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;

public class abr {
   public static abs pC(INativeCodeAnalyzer var0) {
      ICompiler var1 = ((a)var0).getDetectedCompiler();
      ICodeObjectUnit var2 = var0.getContainer();
      if (var1 instanceof abl) {
         if (var0.getProcessor().getType().isIntel() && var0.getProcessor().getMode() == 32) {
            return new ckf(var0);
         }

         if (var0.getProcessor().getType().isIntel() && var2 != null && var2.getLoaderInformation().getTargetProcessor().is64Bit()) {
            return new ckc(var0);
         }
      }

      return null;
   }
}
