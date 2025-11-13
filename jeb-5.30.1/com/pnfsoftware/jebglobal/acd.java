package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public class acd extends IL {
   public acd() {
      super(new aay.eo().q("elf").q(ICompiler.COMP_ANDROID_NDK).q(CodeGapAnalysisStyle.LINEAR_SWEEP).q());
   }

   @Override
   public List Dw() {
      ArrayList var1 = new ArrayList();
      var1.add(new com.pnfsoftware.jeb.corei.parsers.apk.tw());
      return var1;
   }
}
