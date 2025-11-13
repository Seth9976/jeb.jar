package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class auj implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEImm var3 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      IEImm var4 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
      IEImm var5 = (IEImm)var2.getMatchedLeaf(3, IEImm.class);
      if (!var3._and(var4)._not().isOnes()) {
         return false;
      } else if (!var3._or(var4)._and(var5)._not().isOnes()) {
         return false;
      } else {
         var2.getMatchMap().put(4, var3._or(var4)._or(var5));
         return true;
      }
   }
}
