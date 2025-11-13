package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCStatementOptimizer;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collection;
import java.util.List;

public class akq extends AbstractCStatementOptimizer {
   @Override
   protected ICStatement optimizeStatement(ICStatement var1) {
      int var2 = 0;
      if (var1 instanceof ICSwitchStm var3) {
         var2 += this.q(var3);
         var2 += this.RF(var3);
         if (var2 > 0) {
            return var3;
         }
      }

      return null;
   }

   private int q(ICSwitchStm var1) {
      int var2 = 0;
      List var3 = var1.getConditionalBlocks();
      ICBlock var4 = var1.hasDefaultBlock() ? var1.getDefaultBlock() : this.ef.createBlock(this.ef.createBreak(null));

      for (ICBlock var6 : var3) {
         if (this.q(var1, var4, var6, false)) {
            var2++;
         }
      }

      if (var1.hasDefaultBlock()) {
         var3 = var1.getConditionalBlocks();
         ICStatement var10 = var4.get(0);
         if (var10 instanceof ICGoto) {
            ICLabel var11 = ((ICGoto)var10).getLabel();

            for (ICBlock var8 : var3) {
               if (var8.get(0).equals(var11)) {
                  this.q(var1, var8);
                  var1.setDefaultBlock(var8);
                  var2++;
                  break;
               }
            }
         } else if (var10 instanceof ICLabel var12) {
            for (ICBlock var14 : var3) {
               if (var14.get(0) instanceof ICGoto && ((ICGoto)var14.get(0)).getLabel().equals(var12)) {
                  this.q(var1, var14);
                  var2++;
               }
            }
         }
      }

      return var2;
   }

   private int RF(ICSwitchStm var1) {
      int var2 = 0;
      List var3 = var1.getConditionalBlocks();

      for (int var4 = 0; var4 < var1.sizeWithoutDefault(); var4++) {
         ICBlock var5 = (ICBlock)var3.get(var4);

         for (int var6 = var4 + 1; var6 < var1.sizeWithoutDefault(); var6++) {
            ICBlock var7 = (ICBlock)var3.get(var6);
            CUtil.BreakFlowStatus var8 = CUtil.getBreakingFlowResult((ICStatement)var3.get(var6 - 1)).getStatus();
            if ((var8 == CUtil.BreakFlowStatus.TRUE || var8 == CUtil.BreakFlowStatus.BREAK) && this.q(var1, var5, var7, true)) {
               var2++;
               var3 = var1.getConditionalBlocks();
               var6--;
            }
         }

         if (!var5.isEmpty()) {
            ICStatement var10 = var5.get(0);
            if (var10 instanceof ICGoto) {
               ICLabel var12 = ((ICGoto)var10).getLabel();

               for (int var14 = var4 + 1; var14 < var1.sizeWithoutDefault(); var14++) {
                  ICBlock var15 = (ICBlock)var3.get(var14);
                  if (var15.get(0).equals(var12)) {
                     this.q(var1, var15, var5);
                     var2++;
                     break;
                  }
               }
            } else if (var10 instanceof ICLabel var11) {
               for (int var13 = var4 + 1; var13 < var1.sizeWithoutDefault(); var13++) {
                  ICBlock var9 = (ICBlock)var3.get(var13);
                  if (var9.get(0) instanceof ICGoto && ((ICGoto)var9.get(0)).getLabel().equals(var11)) {
                     this.q(var1, var5, var9);
                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }

   private boolean q(ICSwitchStm var1, ICBlock var2, ICBlock var3, boolean var4) {
      if (var2.equals(var3)) {
         if (var4) {
            this.q(var1, var2, var3);
         } else {
            this.q(var1, var3);
         }

         return true;
      } else {
         return false;
      }
   }

   private void q(ICSwitchStm var1, ICBlock var2, ICBlock var3) {
      Collection var4 = var1.removeCasesFromBlock(var3, null);
      var1.addCase(var4, var2);
      Object[] var10000 = new Object[]{Strings.joinList(var4)};
   }

   private void q(ICSwitchStm var1, ICBlock var2) {
      Collection var3 = var1.removeCasesFromBlock(var2, null);
      Object[] var10000 = new Object[]{Strings.joinList(var3)};
   }
}
