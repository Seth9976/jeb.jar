package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abi implements ICompiler {
   @Override
   public String getName() {
      return "unknown";
   }

   @Override
   public CompilerType getType() {
      return CompilerType.UNKNOWN;
   }

   @Override
   public int getPropertyId() {
      return 1;
   }
}
