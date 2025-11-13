package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class aru implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEImm var3 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      IEImm var4 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
      IEImm var5 = (IEImm)var2.getMatchedLeaf(3, IEImm.class);
      IEImm var6 = (IEImm)var2.getMatchedLeaf(4, IEImm.class);
      IEImm var7 = (IEImm)var2.getMatchedLeaf(5, IEImm.class);
      IEImm var8 = (IEImm)var2.getMatchedLeaf(6, IEImm.class);
      return var4._and(var6._xor(var8)).isZero()
         && var3._xor(var4)._and(var5._and(var6)._or(var7)._xor(var8)).isZero()
         && var4._add(var6._xor(var8))._xor(var3._xor(var4)._add(var5._and(var6)._or(var7)._xor(var8))).isZero();
   }
}
