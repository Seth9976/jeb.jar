package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aah extends YA {
   public aah() {
      super(new VM.Av().pC("elf").pC(ICompiler.COMP_ANDROID_ART).pC(CodeGapAnalysisStyle.PROLOGUES_ONLY).pC());
   }
}
