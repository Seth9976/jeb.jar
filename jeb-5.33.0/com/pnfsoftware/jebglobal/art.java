package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class art implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEImm var3 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      return var3._isPowerOf2();
   }
}
