package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ahk extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICIfStm && ((ICIfStm)var4).hasDefaultBlock()) {
            while (this.pC(var1, var3, true)) {
               var2++;
               var3++;
            }

            while (this.pC(var1, var3, false)) {
               var2++;
            }
         }
      }

      return var2;
   }

   private boolean pC(ICBlock var1, int var2, boolean var3) {
      if (var2 >= var1.size()) {
         return false;
      } else {
         ICStatement var4 = var1.get(var2);
         if (var4 instanceof ICIfStm && ((ICIfStm)var4).hasDefaultBlock()) {
            ICIfStm var5 = (ICIfStm)var4;
            if (CUtil.hasEmptyBranch(var5)) {
               return false;
            } else {
               ICStatement var6 = this.pC(var5, var3);
               if (var6 == null) {
                  return false;
               } else {
                  var1.insert(var3 ? var2 : var2 + 1, var6);
                  if (var5.getDefaultBlock().size() == 1) {
                     boolean var7 = true;

                     for (int var8 = 0; var8 < var5.getBranchPredicates().size(); var8++) {
                        if (var5.getBranchBody(var8).size() != 1) {
                           var7 = false;
                           break;
                        }
                     }

                     if (var7) {
                        var1.remove(var5);
                        return true;
                     }

                     var5.setDefaultBlock(null);
                  } else {
                     var5.getDefaultBlock().remove(var3 ? 0 : var5.getDefaultBlock().size() - 1);
                  }

                  for (int var9 = 0; var9 < var5.getBranchPredicates().size(); var9++) {
                     var5.getBranchBody(var9).remove(var3 ? 0 : var5.getBranchBody(var9).size() - 1);
                  }

                  return true;
               }
            }
         } else {
            return false;
         }
      }
   }

   private ICStatement pC(ICIfStm var1, boolean var2) {
      ICStatement var3 = var1.getDefaultBlock().get(var2 ? 0 : var1.getDefaultBlock().size() - 1);
      if (var2 && !CUtil.hasNoCall(var3)) {
         return null;
      } else {
         Set var4 = null;
         if (var2) {
            var4 = this.pC(var3);
         }

         for (int var5 = 0; var5 < var1.sizeWithoutDefault(); var5++) {
            if (var1.getBranchBody(var5).isEmpty() || !var3.equals(var1.getBranchBody(var5).get(var2 ? 0 : var1.getBranchBody(var5).size() - 1))) {
               return null;
            }

            if (var2) {
               ICPredicate var6 = var1.getBranchPredicate(var5);
               if (!CUtil.hasNoCall(var6)) {
                  return null;
               }

               Set var7 = this.pC(var6);

               for (ICIdentifier var9 : var4) {
                  if (var7.contains(var9)) {
                     return null;
                  }
               }
            }
         }

         return var3;
      }
   }

   private Set pC(ICElement var1) {
      HashSet var2 = new HashSet();
      ArrayList var3 = new ArrayList();
      var3.add(var1);
      this.pC(var1.getSubElements(), var2);
      return var2;
   }

   private void pC(List var1, Set var2) {
      for (ICElement var4 : var1) {
         if (!CUtil.isClassMethodField(var4)) {
            if (var4 instanceof ICIdentifier) {
               var2.add((ICIdentifier)var4);
            } else {
               this.pC(var4.getSubElements(), var2);
            }
         }
      }
   }
}
