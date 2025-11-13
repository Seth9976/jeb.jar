package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;

public class adj {
   public static adk q(INativeCodeAnalyzer var0) {
      ICompiler var1 = ((aae)var0).getDetectedCompiler();
      ICodeObjectUnit var2 = var0.getContainer();
      if (var1 instanceof add) {
         if (var0.getProcessor().getType().isIntel() && var0.getProcessor().getMode() == 32) {
            return new cus(var0);
         }

         if (var0.getProcessor().getType().isIntel() && var2 != null && var2.getLoaderInformation().getTargetProcessor().is64Bit()) {
            return new cup(var0);
         }
      }

      return null;
   }
}
