package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class acb extends IL {
   public acb() {
      super(
         new aay.eo()
            .q("elf")
            .q(ICompiler.COMP_UNKNOWN_LINUX, ICompiler.COMP_ANDROID_ART, ICompiler.COMP_ANDROID_NDK)
            .q(0)
            .q(CodeGapAnalysisStyle.PROLOGUES_ONLY)
            .q(false)
            .q()
      );
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      if (var2 == null) {
         return false;
      } else if (var2.getSectionCount() == 0) {
         return true;
      } else {
         ISegmentInformation var3 = CodeObjectUnitUtil.findSectionByName(var2, ".text");
         if (var3 == null) {
            return true;
         } else {
            int var4 = var3.getFlags();
            return (var4 & 2) == 0 || (var4 & 4) == 0;
         }
      }
   }
}
