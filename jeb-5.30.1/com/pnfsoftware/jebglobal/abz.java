package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abz extends IL {
   public abz() {
      super(new aay.eo().q("elf").q(ICompiler.COMP_ANDROID_ART).q(CodeGapAnalysisStyle.PROLOGUES_ONLY).q());
   }
}
