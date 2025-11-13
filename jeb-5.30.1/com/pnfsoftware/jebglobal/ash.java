package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class ash implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEImm var3 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      int var4 = var3.getBitsize();
      return var4 % 2 != 0 ? false : EUtil.isLegalUnsignedImmediate(var3, var4 / 2);
   }
}
