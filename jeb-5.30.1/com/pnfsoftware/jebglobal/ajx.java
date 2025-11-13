package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConditionalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class ajx extends AbstractCOptimizer {
   private static final StructuredLogger q = aeg.q(ajx.class);

   @Override
   protected int perform() {
      return this.q(this.m.getBody(), null);
   }

   private int q(ICBlock var1, ICStatement var2) {
      return this.q(var1, var2, false);
   }

   private int q(ICBlock var1, ICStatement var2, boolean var3) {
      int var4 = 0;
      int var5 = 0;

      while (var5 < var1.size()) {
         ICStatement var6 = var1.get(var5);
         if (var6 instanceof ICConditionalStatement) {
            ICStatement var7 = var5 == var1.size() - 1 ? var2 : this.q(var1, var5 + 1);

            for (ICBlock var9 : ((ICConditionalStatement)var6).getBlocks()) {
               var4 += this.q(var9, var7, var6 instanceof ICSwitchStm);
            }
         } else if (var6 instanceof ICGenericLoop) {
            if (!CUtil.isWhileTrue(var6) && !CUtil.isDoWhileTrue(var6)) {
               var4 += this.q((ICBlock)((ICCompound)var6).getBlocks().get(0), null);
            } else {
               ICBlock var10 = (ICBlock)((ICCompound)var6).getBlocks().get(0);
               if (var10.size() >= 1) {
                  var4 += this.q(var10, this.q(var10, 0));
               } else {
                  var4 += this.q(var10, null);
               }
            }
         } else if (var6 instanceof ICGoto) {
            ICLabel var11 = ((ICGoto)var6).getLabel();
            ICStatement var12 = var5 == var1.size() - 1 ? var2 : this.q(var1, var5 + 1);
            if (var12 != null && var11.equals(var12)) {
               if (var3 && var5 == var1.size() - 1) {
                  Object[] var13 = new Object[]{var6};
                  var1.set(var5, this.m.getGlobalContext().getElementFactory().createBreak(null));
               } else {
                  Object[] var10000 = new Object[]{var6};
                  var1.remove(var5);
               }

               var4++;
               continue;
            }
         }

         var5++;
      }

      return var4;
   }

   private ICStatement q(ICBlock var1, int var2) {
      ICStatement var3 = CUtil.getFirstRealStatement(var1, var2);
      return (ICStatement)(var3 instanceof ICGoto ? ((ICGoto)var3).getLabel() : var3);
   }
}
