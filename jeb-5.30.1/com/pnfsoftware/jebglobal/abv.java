package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abv extends IL {
   public abv() {
      super(
         new aay.eo()
            .q(DataGapAnalysisStyle.DEFAULT)
            .q(CodeGapAnalysisStyle.PROLOGUES_ONLY)
            .q(aap.eo.RF)
            .q(ctk.eo.RF)
            .q(false)
            .RF(true)
            .q(0)
            .xK(false)
            .RF(20)
            .xK(6)
            .Dw(32)
            .q()
      );
   }
}
