package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import java.util.List;

public class Tq {
   public static ICompiler q(ICodeObjectUnit var0) {
      if (var0 instanceof com.pnfsoftware.jeb.corei.parsers.elf.vb) {
         if (CodeObjectUnitUtil.findExportedSymbolByName(var0, "oatdata") != null
            && CodeObjectUnitUtil.findExportedSymbolByName(var0, "oatexec") != null
            && CodeObjectUnitUtil.findExportedSymbolByName(var0, "oatlastword") != null) {
            return ICompiler.COMP_ANDROID_ART;
         } else {
            return CodeObjectUnitUtil.findSectionByName(var0, ".note.android.ident") == null
                  && CodeObjectUnitUtil.findSectionByName(var0, ".note.gnu.gold-version") == null
               ? ICompiler.COMP_UNKNOWN_LINUX
               : ICompiler.COMP_ANDROID_NDK;
         }
      } else if (var0 instanceof com.pnfsoftware.jeb.corei.parsers.winpe.vn) {
         List var1 = UnitUtil.findChildrenByName(var0, "Rich header");
         return var1 != null && var1.size() == 1 ? ICompiler.COMP_VISUAL_STUDIO : ICompiler.COMP_UNKNOWN_WINDOWS;
      } else {
         return var0 instanceof com.pnfsoftware.jeb.corei.parsers.wincoff.nI ? ICompiler.COMP_VISUAL_STUDIO : ICompiler.COMP_UNKNOWN;
      }
   }

   public static ICompiler q(int var0) {
      switch (var0) {
         case 1:
            return ICompiler.COMP_UNKNOWN;
         case 2:
            return ICompiler.COMP_UNKNOWN_LINUX;
         case 3:
            return ICompiler.COMP_ANDROID_ART;
         case 4:
            return ICompiler.COMP_ANDROID_NDK;
         case 5:
            return ICompiler.COMP_UNKNOWN_WINDOWS;
         case 6:
            return ICompiler.COMP_VISUAL_STUDIO;
         default:
            return ICompiler.COMP_UNKNOWN;
      }
   }
}
