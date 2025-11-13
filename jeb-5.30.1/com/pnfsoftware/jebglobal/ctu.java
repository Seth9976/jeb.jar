package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;

public class ctu {
   public static ctk q(INativeCodeAnalyzer var0) {
      if (var0.getProcessor().getType() == ProcessorType.X86) {
         return new cty(var0, ((aae)var0).Dw().zz());
      } else {
         return var0.getProcessor().getType() == ProcessorType.X86_64 ? new ctw(var0, ((aae)var0).Dw().zz()) : null;
      }
   }
}
