package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aay extends YA {
   public aay() {
      super(new VM.Av().pC(ProcessorType.X86, ProcessorType.X86_64).pC("winpe").pC(ICompiler.COMP_VISUAL_STUDIO).pC(CodeGapAnalysisStyle.PROLOGUES_ONLY).pC());
   }

   @Override
   public boolean pC(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      return var2 == null ? false : CodeObjectUnitUtil.findSegmentByName(var2, ".rdata") == null;
   }
}
