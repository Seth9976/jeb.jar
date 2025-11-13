package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class acc extends IL {
   public acc() {
      super(new aay.eo().q("elf").q(ICompiler.COMP_UNKNOWN_LINUX, ICompiler.COMP_ANDROID_ART, ICompiler.COMP_ANDROID_NDK).q(true).q());
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      return var2 == null ? false : ((com.pnfsoftware.jeb.corei.parsers.elf.vb)var2).getHeader().getType() == 3;
   }
}
