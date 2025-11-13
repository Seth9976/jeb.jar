package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface acz extends ICompiler {
   @Override
   default CompilerType getType() {
      return CompilerType.MSVC;
   }
}
