package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aag extends YA {
   public aag() {
      super(new VM.Av().pC("elf").pC(CodeGapAnalysisStyle.NONE).pC());
   }

   @Override
   public boolean pC(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      return var2 instanceof com.pnfsoftware.jeb.corei.parsers.elf.sy && ((com.pnfsoftware.jeb.corei.parsers.elf.sy)var2).getHeader().getType() == 1;
   }
}
