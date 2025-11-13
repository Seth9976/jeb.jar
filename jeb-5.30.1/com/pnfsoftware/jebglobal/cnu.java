package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;

public class cnu {
   public static cnt q(INativeCodeUnit var0) {
      if (var0.getCodeAnalyzer().getDetectedCompiler().isVisualStudio()) {
         return new coj((abg)var0);
      } else {
         return var0.getCodeAnalyzer().getDetectedCompiler().isLinuxCompatible() ? new cny((abg)var0) : null;
      }
   }
}
