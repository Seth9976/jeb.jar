package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class apr implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      return var2.getMatchedLeaf(0).getBitsize() == 1;
   }
}
