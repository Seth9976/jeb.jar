package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;

public class cjh {
   public static com.pnfsoftware.jeb.corei.parsers.x86.wn pC(INativeCodeAnalyzer var0) {
      if (var0.getProcessor().getType() == ProcessorType.X86) {
         return new cjl(var0, ((a)var0).wS().gp());
      } else {
         return var0.getProcessor().getType() == ProcessorType.X86_64 ? new cjj(var0, ((a)var0).wS().gp()) : null;
      }
   }
}
