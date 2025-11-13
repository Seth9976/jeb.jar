package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aai extends YA {
   public aai() {
      super(
         new VM.Av()
            .pC("elf")
            .pC(ICompiler.COMP_UNKNOWN_LINUX, ICompiler.COMP_ANDROID_ART, ICompiler.COMP_ANDROID_NDK)
            .pC(CodeGapAnalysisStyle.LINEAR_SWEEP)
            .pC()
      );
   }

   @Override
   public List wS() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.elf.cq());
      return var1;
   }
}
