package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCStatementOptimizer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class aha extends AbstractCStatementOptimizer {
   @Override
   protected ICStatement optimizeStatement(ICStatement var1) {
      return var1 instanceof ICIfStm ? pC(this.ef, (ICIfStm)var1, 3) : null;
   }

   static ICSwitchStm pC(ICElementFactory var0, ICIfStm var1, int var2) {
      if (var1.size() < var2) {
         return null;
      } else {
         ICPredicate var3 = var1.getBranchPredicate(0);
         ICExpression var4 = pC(var3.getExpression());
         if (var4 == null) {
            return null;
         } else {
            ICSwitchStm var5 = var0.createSwitchStm(var4);
            int var6 = -1;

            for (int var7 = 0; var7 < var1.sizeWithoutDefault(); var7++) {
               ICPredicate var8 = var1.getBranchPredicate(var7);
               ArrayList var9 = new ArrayList();
               if (!pC(var8.getExpression(), var4, var9)) {
                  if (var7 < var2) {
                     return null;
                  }

                  var6 = var7;
                  break;
               }

               ICBlock var10 = pC(var0, var1.getBranchBody(var7));
               if (var10 == null) {
                  return null;
               }

               var5.addCase(var9, var10);
            }

            if (var6 != -1) {
               ICIfStm var11 = var0.createIfStm(var1.getBranchPredicate(var6), var1.getBranchBody(var6));

               for (int var13 = var6 + 1; var13 < var1.sizeWithoutDefault(); var13++) {
                  var11.addBranch(var1.getBranchPredicate(var13), var1.getBranchBody(var13));
               }

               ICBlock var14 = pC(var0, var0.createBlock(var11));
               if (var14 == null) {
                  return null;
               }

               if (var1.hasDefaultBlock()) {
                  var11.setDefaultBlock(var1.getDefaultBlock());
               }

               var5.setDefaultBlock(var14);
            } else if (var1.hasDefaultBlock()) {
               ICBlock var12 = pC(var0, var1.getDefaultBlock());
               if (var12 == null) {
                  return null;
               }

               var5.setDefaultBlock(var12);
            }

            return var5;
         }
      }
   }

   private static ICBlock pC(ICElementFactory var0, ICBlock var1) {
      if (CUtil.containsBreak(var1)) {
         return null;
      } else if (var1.isEmpty()) {
         return var0.createBlock().add(var0.createBreak(null));
      } else {
         ICStatement var2 = var1.getLast();
         if (!(var2 instanceof ICBreak)
            && !(var2 instanceof ICGoto)
            && !(var2 instanceof ICReturn)
            && !(var2 instanceof ICJumpFar)
            && !(var2 instanceof ICContinue)) {
            ICBlock var3 = var0.createBlock();

            for (ICStatement var5 : var1) {
               var3.add(var5);
            }

            var3.add(var0.createBreak(null));
            return var3;
         } else {
            return var1;
         }
      }
   }

   private static ICExpression pC(ICExpression var0) {
      if (!(var0 instanceof ICOperation var1)) {
         return null;
      } else {
         if (var1.getOperatorType() == COperatorType.EQ) {
            if (var1.getSecondOperand() instanceof ICConstantInteger) {
               return ((ICOperation)var0).getFirstOperand();
            }
         } else if (var1.getOperatorType() == COperatorType.LOG_OR) {
            return pC(var1.getFirstOperand());
         }

         return null;
      }
   }

   private static boolean pC(ICExpression var0, ICExpression var1, List var2) {
      if (!(var0 instanceof ICOperation var3)) {
         return false;
      } else {
         if (var3.getOperatorType() == COperatorType.EQ) {
            if (var3.getFirstOperand().equals(var1) && var3.getSecondOperand() instanceof ICConstantInteger) {
               BigInteger var4 = ((ICConstantInteger)var3.getSecondOperand()).getIntegerValue();
               var2.add(var4);
               return true;
            }
         } else if (var3.getOperatorType() == COperatorType.LOG_OR) {
            boolean var5 = pC(var3.getFirstOperand(), var1, var2);
            if (!var5) {
               return false;
            }

            var5 = pC(var3.getSecondOperand(), var1, var2);
            if (!var5) {
               return false;
            }

            if (var3.getThirdOperand() != null) {
               var5 = pC(var3.getThirdOperand(), var1, var2);
               if (!var5) {
                  return false;
               }
            }

            return true;
         }

         return false;
      }
   }
}
