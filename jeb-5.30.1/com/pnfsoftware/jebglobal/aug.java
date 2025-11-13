package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class aug implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEImm var3 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      IEImm var4 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
      IEImm var5 = (IEImm)var2.getMatchedLeaf(3, IEImm.class);
      return !var4._or(var5._not()).isOnes() ? false : var3._not()._or(var4._xor(var5)._not()).isOnes();
   }
}
