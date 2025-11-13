package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import java.util.HashMap;
import org.apache.commons.lang3.BooleanUtils;

public class aiu extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size()) {
         if (var1.get(var3) instanceof ICIfStm var5) {
            int var6 = var5.sizeWithoutDefault();
            if (var6 >= 2) {
               int var7 = -1;
               int var8 = -1;
               HashMap var9 = new HashMap();
               HashMap var10 = new HashMap();
               boolean var11 = false;

               for (int var12 = 0; var12 < var6; var12++) {
                  ICBlock var13 = var5.getBranchBody(var12);
                  if (!var13.isEmpty()) {
                     ICStatement var14 = var13.get(0);
                     if (var14 instanceof ICLabel var15) {
                        var9.put(var15, var12);
                        if (var10.keySet().contains(var15)) {
                           var7 = (Integer)var10.get(var15);
                           var8 = var12;
                           var11 = true;
                           break;
                        }
                     } else if (var14 instanceof ICGoto && var13.size() == 1) {
                        ICLabel var22 = ((ICGoto)var14).getLabel();
                        var10.put(var22, var12);
                        if (var9.keySet().contains(var22)) {
                           var7 = (Integer)var9.get(var22);
                           var8 = var12;
                           var11 = false;
                           break;
                        }
                     }
                  }
               }

               if (var7 >= 0 && var8 >= 0 && var7 != var8) {
                  boolean var17 = true;
                  if (var7 + 1 != var8) {
                     ICPredicate var18 = var5.getBranchPredicate(var8);
                     ICPredicate var20 = var5.getBranchPredicate(var7);

                     for (int var23 = var7 + 1; var23 < var8; var23++) {
                        ICPredicate var16 = var5.getBranchPredicate(var23);
                        if (!CUtil.hasNoSideEffects(var16)) {
                           var20 = null;
                           var18 = null;
                           break;
                        }

                        if (this.q(var18, var16)) {
                           var18 = null;
                           break;
                        }
                     }

                     if (var18 == null) {
                        var17 = false;
                        if (var20 == null) {
                           var3++;
                           continue;
                        }

                        for (int var24 = var7 + 1; var24 < var8; var24++) {
                           ICPredicate var25 = var5.getBranchPredicate(var24);
                           if (this.q(var20, var25)) {
                              var20 = null;
                              break;
                           }
                        }

                        if (var20 == null) {
                           var3++;
                           continue;
                        }
                     }
                  }

                  ICOperation var19 = CUtil.orL(this.m, var5.getBranchPredicate(var7).getExpression(), var5.getBranchPredicate(var8).getExpression());
                  ICPredicate var21 = this.ef.createPredicate(var19);
                  if (var17) {
                     var5.setBranchPredicate(var7, var21);
                     if (var11) {
                        var5.setBranchBody(var7, var5.getBranchBody(var8));
                     }

                     var5.removeBranch(var8);
                  } else {
                     var5.setBranchPredicate(var8, var21);
                     if (!var11) {
                        var5.setBranchBody(var8, var5.getBranchBody(var7));
                     }

                     var5.removeBranch(var7);
                     var3--;
                  }

                  var2++;
               }
            }
         }

         var3++;
      }

      return var2;
   }

   private boolean q(ICPredicate var1, ICPredicate var2) {
      Boolean var3 = aew.q(var1, var2);
      return BooleanUtils.toBooleanDefaultIfNull(var3, true);
   }
}
