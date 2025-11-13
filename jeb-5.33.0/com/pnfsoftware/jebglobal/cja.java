package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;

public class cja {
   public static com.pnfsoftware.jeb.corei.parsers.x86.wn pC(INativeCodeAnalyzer var0) {
      return var0.getProcessor().getType() != ProcessorType.X86 && var0.getProcessor().getType() != ProcessorType.X86_64
         ? null
         : new cjb(var0, ((a)var0).wS().gp());
   }
}
