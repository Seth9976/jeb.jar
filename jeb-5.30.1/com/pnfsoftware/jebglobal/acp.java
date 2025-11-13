package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class acp extends IL {
   public acp() {
      super(new aay.eo().q(ProcessorType.X86, ProcessorType.X86_64).q("winpe").q(ICompiler.COMP_VISUAL_STUDIO).q(CodeGapAnalysisStyle.PROLOGUES_ONLY).q());
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      return !(var2 instanceof com.pnfsoftware.jeb.corei.parsers.winpe.vn)
         ? false
         : ((com.pnfsoftware.jeb.corei.parsers.winpe.vn)var2).getPEOptionalHeader() != null
            && ((com.pnfsoftware.jeb.corei.parsers.winpe.vn)var2).getPEOptionalHeader().getSubsystem() == 1;
   }
}
