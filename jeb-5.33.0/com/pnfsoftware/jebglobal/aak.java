package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class aak extends YA {
   public aak() {
      super(new VM.Av().pC("elf").pC(ICompiler.COMP_UNKNOWN_LINUX, ICompiler.COMP_ANDROID_ART, ICompiler.COMP_ANDROID_NDK).pC(true).pC());
   }

   @Override
   public boolean pC(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = var1.getCodeObjectContainer();
      return var2 == null ? false : ((com.pnfsoftware.jeb.corei.parsers.elf.sy)var2).getHeader().getType() == 3;
   }
}
