package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ahc extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5 && var5.size() == 2 && var5.hasDefaultBlock()) {
            ICBlock var6 = var5.getBranchBody(0);
            ICBlock var7 = var5.getDefaultBlock();
            if (var6.size() == 1 && var7.size() == 1) {
               ICStatement var8 = var6.get(0);
               ICStatement var9 = var7.get(0);
               if (var8 instanceof ICAssignment && var9 instanceof ICAssignment) {
                  ICAssignment var10 = (ICAssignment)var8;
                  ICAssignment var11 = (ICAssignment)var9;
                  if (var10.isSimpleAssignment() && var11.isSimpleAssignment()
                     || var10.isCombinedOperatorAssignment()
                        && var11.isCombinedOperatorAssignment()
                        && var10.getCombinedOperator().equals(var11.getCombinedOperator())) {
                     boolean var12 = false;
                     if (!var10.getLeft().equals(var11.getLeft())
                        && (!(var10.getLeft() instanceof ICDecl) || !((ICDecl)var10.getLeft()).getIdentifier().equals(var11.getLeft()))) {
                        if (var11.getLeft() instanceof ICDecl && ((ICDecl)var11.getLeft()).getIdentifier().equals(var10.getLeft())) {
                           ICOperation var14 = this.ef
                              .createOperation(this.of.get(COperatorType.COND), var5.getBranchPredicate(0), var10.getRight(), var11.getRight());
                           var11.setRight(var14);
                           var1.set(var3, var11);
                           var12 = true;
                        }
                     } else {
                        ICOperation var13 = this.ef
                           .createOperation(this.of.get(COperatorType.COND), var5.getBranchPredicate(0), var10.getRight(), var11.getRight());
                        var10.setRight(var13);
                        var1.set(var3, var10);
                        var12 = true;
                     }

                     if (var12) {
                        var2++;
                     }
                  }
               }
            }
         }
      }

      return var2;
   }
}
