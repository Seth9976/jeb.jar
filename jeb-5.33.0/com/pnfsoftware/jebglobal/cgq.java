package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;

public class cgq {
   public static cgp pC(INativeCodeUnit var0) {
      if (var0.getCodeAnalyzer().getDetectedCompiler().isVisualStudio()) {
         return new chf((C)var0);
      } else {
         return var0.getCodeAnalyzer().getDetectedCompiler().isLinuxCompatible() ? new cgu((C)var0) : null;
      }
   }
}
