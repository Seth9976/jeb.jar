package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aby extends IL {
   public aby() {
      super(new aay.eo().q("elf").q(CodeGapAnalysisStyle.NONE).q());
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      return var2 instanceof com.pnfsoftware.jeb.corei.parsers.elf.vb && ((com.pnfsoftware.jeb.corei.parsers.elf.vb)var2).getHeader().getType() == 1;
   }
}
