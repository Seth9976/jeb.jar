package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aad extends YA {
   public aad() {
      super(
         new VM.Av()
            .pC(DataGapAnalysisStyle.DEFAULT)
            .pC(CodeGapAnalysisStyle.PROLOGUES_ONLY)
            .pC(iL.Av.A)
            .pC(com.pnfsoftware.jeb.corei.parsers.x86.wn.Av.A)
            .pC(false)
            .A(true)
            .pC(0)
            .kS(false)
            .A(20)
            .kS(6)
            .wS(32)
            .pC()
      );
   }
}
