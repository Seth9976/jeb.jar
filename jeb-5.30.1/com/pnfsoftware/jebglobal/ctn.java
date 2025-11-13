package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;

public class ctn {
   public static ctk q(INativeCodeAnalyzer var0) {
      return var0.getProcessor().getType() != ProcessorType.X86 && var0.getProcessor().getType() != ProcessorType.X86_64
         ? null
         : new cto(var0, ((aae)var0).Dw().zz());
   }
}
