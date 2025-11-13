package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class acq extends IL {
   public acq() {
      super(new aay.eo().q(ProcessorType.X86, ProcessorType.X86_64).q("winpe").q(ICompiler.COMP_VISUAL_STUDIO).q(CodeGapAnalysisStyle.PROLOGUES_ONLY).q());
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      return var2 == null ? false : CodeObjectUnitUtil.findSegmentByName(var2, ".rdata") == null;
   }
}
