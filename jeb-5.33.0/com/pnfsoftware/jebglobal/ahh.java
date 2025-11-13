package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger32;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger64;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ahh extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICAssignment var5) {
            ICLeftExpression var6 = var5.getLeft();
            if (var5.isSimpleAssignment() && var5.getRight() instanceof ICOperation) {
               ICOperation var7 = (ICOperation)var5.getRight();
               ICOperator var8 = var7.getOperator();
               if (var8.isValidForCombinedAssignment()) {
                  Object var9 = null;
                  if (var7.getFirstOperand().equals(var6)) {
                     var9 = var7.getSecondOperand();
                  } else if (var8.checkType(COperatorType.ADD, COperatorType.MUL, COperatorType.OR, COperatorType.AND, COperatorType.XOR)
                     && var7.getSecondOperand().equals(var6)) {
                     var9 = var7.getFirstOperand();
                  }

                  if (var9 != null) {
                     if (var8.checkType(COperatorType.ADD) && CUtil.isIntegerConstant((ICExpression)var9)) {
                        Long var10 = CUtil.getConstantAsLong((ICConstantInteger)var9);
                        if (var10 != null && var10 < 0L) {
                           var8 = this.of.get(COperatorType.SUB);
                           if (var9 instanceof ICConstantInteger32) {
                              var9 = this.cf.createInt32(-var10.intValue());
                           }

                           if (var9 instanceof ICConstantInteger64) {
                              var9 = this.cf.createInt64(-var10);
                           }
                        }
                     }

                     var5.setCombinedOperatorAssignment(var8, (ICExpression)var9);
                     var2++;
                  }
               }
            }

            if (var5.isCombinedOperatorAssignment() && var5.getRight() instanceof ICConstantInteger) {
               ICOperator var11 = var5.getCombinedOperator();
               if (var11.checkType(COperatorType.ADD, COperatorType.SUB)) {
                  ICConstantInteger var12 = (ICConstantInteger)var5.getRight();
                  Boolean var13 = null;
                  if (var12.getValueAsLong() == 1L) {
                     var13 = var11 == this.of.get(COperatorType.ADD);
                  } else if (var12.getValueAsLong() == -1L) {
                     var13 = var11 == this.of.get(COperatorType.SUB);
                  }

                  if (var13 != null) {
                     var5.setUnaryOperator(var13, true);
                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }
}
