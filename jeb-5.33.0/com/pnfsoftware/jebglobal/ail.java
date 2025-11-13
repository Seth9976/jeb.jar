package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConditionalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCStatementOptimizer;
import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ail extends AbstractCStatementOptimizer {
   @Override
   protected ICStatement optimizeStatement(ICStatement var1) {
      int var2 = 0;
      if (var1 instanceof ICSwitchStm var3) {
         if (var3.hasDefaultBlock() && this.pC(var3.getDefaultBlock())) {
            var3.setDefaultBlock(null);
            var2++;
            Object[] var10000 = new Object[0];
         }

         for (int var4 = 0; var4 < var3.sizeWithoutDefault(); var4++) {
            ICBlock var5 = (ICBlock)var3.getBlocks().get(var4);
            if (var5.isEmpty()) {
               Collection var6;
               if (var4 + 1 == var3.sizeWithoutDefault()) {
                  var6 = var3.removeCasesFromBlock(var5, null);
               } else {
                  ICBlock var7 = (ICBlock)var3.getBlocks().get(var4 + 1);
                  var6 = var3.removeCasesFromBlock(var5, null);
                  var3.addCase(var6, var7);
               }

               var2++;
               Object[] var18 = new Object[]{Strings.joinList(var6)};
               var4--;
            }
         }

         ICExpression var12 = var3.getSwitchedExpression();

         for (int var13 = 0; var13 < var3.size(); var13++) {
            List var14 = var3.getBlocks();
            boolean var8 = false;
            ICBlock var15;
            if (var3.hasDefaultBlock() && var13 == var3.size() - 1) {
               var8 = true;
               var15 = var3.getDefaultBlock();
            } else {
               var15 = (ICBlock)var14.get(var13);
            }

            if ((var8 || var12 != null) && !var15.isEmpty() && var15.get(0) instanceof ICConditionalStatement) {
               Object var9 = (ICConditionalStatement)var15.get(0);
               if (pC((ICConditionalStatement)var9, var12)) {
                  if (var9 instanceof ICIfStm) {
                     var9 = aha.pC(this.ef, (ICIfStm)var9, 0);
                     if (var9 == null) {
                        continue;
                     }
                  }

                  ICBlock var10 = this.ef.createBlock();
                  if (((ICConditionalStatement)var9).getDefaultBlock() != null) {
                     for (int var11 = 0; var11 < ((ICConditionalStatement)var9).getDefaultBlock().size(); var11++) {
                        var10.add(((ICConditionalStatement)var9).getDefaultBlock().get(var11));
                     }
                  }

                  if (var15.size() > 1) {
                     if (!aii.A((ICConditionalStatement)var9)) {
                        continue;
                     }

                     if (!var10.isEmpty()) {
                        ICStatement var16 = var10.get(var10.size() - 1);
                        if (var16 instanceof ICBreak && ((ICBreak)var16).getLabel() == null) {
                           var10.remove(var10.size() - 1);
                        }
                     }

                     for (int var17 = 1; var17 < var15.size(); var17++) {
                        var10.add(var15.get(var17));
                     }
                  }

                  if (this.pC(var3, var13, var15, (ICConditionalStatement)var9, var10)) {
                     var2++;
                     var13--;
                  }
               }
            }
         }
      }

      return var2 > 0 ? var1 : null;
   }

   private static boolean pC(ICConditionalStatement var0, ICExpression var1) {
      ICExpression var2;
      if (var0 instanceof ICIfStm) {
         var2 = aii.pC(((ICIfStm)var0).getBranchPredicate(0).getExpression());
      } else {
         if (!(var0 instanceof ICSwitchStm)) {
            return false;
         }

         var2 = aii.pC(var0);
      }

      return var2 != null && var2 == var1;
   }

   private boolean pC(ICSwitchStm var1, int var2, ICBlock var3, ICConditionalStatement var4, ICBlock var5) {
      boolean var6 = var1.hasDefaultBlock() && var2 == var1.size() - 1;
      if (var4 instanceof ICSwitchStm var7) {
         if (!pC(this.ef, var1) && !pC(this.ef, var7)) {
            if (var3.size() != 1 && pC(var7)) {
               return false;
            } else {
               if (var6) {
                  this.pC(var1, var7, null, var5);
               } else {
                  this.pC(var1, var7, var3, var5);
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private int pC(ICSwitchStm var1, ICSwitchStm var2, ICBlock var3, ICBlock var4) {
      int var5 = 0;
      Set var6 = var1.getCaseKeys();
      ArrayList var7 = null;
      if (var3 != null) {
         Collection var8 = var1.removeCasesFromBlock(var3, null);
         var7 = new ArrayList(var8);
      }

      for (ICBlock var9 : var2.getConditionalBlocks()) {
         List var10 = var2.getKeysForBlock(var9);
         ArrayList var11 = new ArrayList();

         for (BigInteger var13 : var10) {
            if (var3 != null) {
               if (var7.contains(var13)) {
                  var11.add(var13);
               }
            } else if (!var6.contains(var13)) {
               var11.add(var13);
            }
         }

         if (!var11.isEmpty()) {
            var1.addCase(var11, var9);
            var5++;
            if (var7 != null) {
               var7.removeAll(var11);
            }
         }
      }

      if (var3 != null) {
         if (!var7.isEmpty()) {
            var1.addCase(var7, var4);
         }
      } else {
         if (var4.isEmpty()) {
            var4 = null;
         }

         var1.setDefaultBlock(var4);
      }

      return var5;
   }

   static boolean pC(ICElementFactory var0, ICSwitchStm var1) {
      List var2 = var1.getConditionalBlocks();

      for (int var3 = 0; var3 < var2.size(); var3++) {
         ICBlock var4 = (ICBlock)var2.get(var3);
         if (CUtil.canFallthrough(var4, var0.createBreak(), false)) {
            return true;
         }
      }

      return false;
   }

   static boolean pC(ICSwitchStm var0) {
      List var1 = var0.getBlocks();

      for (int var2 = 0; var2 < var1.size(); var2++) {
         ICBlock var3 = (ICBlock)var1.get(var2);
         if (!CUtil.visitICStatementDepthPost(new aim(var3), var3, null)) {
            return true;
         }
      }

      return false;
   }

   private boolean pC(ICBlock var1) {
      if (var1.size() == 1) {
         ICStatement var2 = var1.get(0);
         if (var2 instanceof ICBreak && ((ICBreak)var2).getLabel() == null) {
            return true;
         }
      }

      return false;
   }
}
