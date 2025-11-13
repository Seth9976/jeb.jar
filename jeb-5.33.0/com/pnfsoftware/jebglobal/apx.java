package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEMatchVerifier;

class apx implements IEMatchVerifier {
   @Override
   public boolean verify(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEGeneric var3 = var2.getMatchedLeaf(0);
      int var4 = var3.getBitsize();
      IEImm var5 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      if (!var5.canReadAsLong()) {
         return false;
      } else {
         long var6 = var5.getValueAsLong();
         IEImm var8 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         if (!var8.canReadAsLong()) {
            return false;
         } else {
            long var9 = 1L << (int)var8.getValueAsLong();
            if (var9 != var6 + 1L) {
               return false;
            } else {
               var2.getMatchMap().put(3, ajr.pC(var9, var4));
               return true;
            }
         }
      }
   }
}
