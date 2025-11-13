package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class apw implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEGeneric var3 = var2.getMatchedLeaf(0);
      int var4 = var3.getBitsize();
      IEImm var5 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      if (var5.canReadAsLong() && var5.getValueAsLong() == var4 - 1) {
         IEImm var6 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         if (!var6.canReadAsLong()) {
            return false;
         } else {
            int var7 = var4 - (int)var6.getValueAsLong();
            IEImm var8 = (IEImm)var2.getMatchedLeaf(3, IEImm.class);
            if (var8.canReadAsLong() && (1L << var7) - 1L == var8._not().getValueAsLong()) {
               var2.getMatchMap().put(4, ajr.pC(1L << var7, var4));
               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }
}
