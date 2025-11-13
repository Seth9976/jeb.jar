package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEPatternReplacer;

class apu implements IEPatternReplacer {
   @Override
   public Boolean replace(EPatternMatcher var1, EPatternMatcher.Result var2) {
      IEGeneric var3 = var2.getMatchedLeaf(0);
      IEImm var4 = (IEImm)var2.getMatchedLeaf(1, IEImm.class);
      if (var4.getBitsize() != 8) {
         return false;
      } else {
         IEImm var5 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         if (var5.isZero()) {
            return false;
         } else {
            int var6 = var5.getBitsize();
            if (var6 <= 64 && var4.getValueAsUnsignedLong() < var6) {
               Object var7 = null;
               int var8 = 0;

               for (long var9 = var5.getValueAsLong(); var9 != 0L; var8++) {
                  if ((var9 & 1L) != 0L) {
                     IEOperation var11 = EUtil.eq(var3, EUtil.imm(var8, 8));
                     if (var7 == null) {
                        var7 = var11;
                     } else {
                        var7 = EUtil.orL((IEGeneric)var7, var11);
                     }
                  }

                  var9 >>>= 1;
               }

               if (var7 == null) {
                  return false;
               } else {
                  if (var2.getInputIndex() == 0) {
                     var7 = EUtil.notL((IEGeneric)var7);
                  }

                  IEGeneric var13 = var2.getMatchedExpression();
                  IEGeneric var12 = var2.getMatchedParent();
                  return var12 != null && var13 != null && var12.replaceSubExpression(var13, (IEGeneric)var7);
               }
            } else {
               return false;
            }
         }
      }
   }
}
