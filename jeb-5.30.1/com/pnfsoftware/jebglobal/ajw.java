package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCStatementOptimizer;

public class ajw extends AbstractCStatementOptimizer {
   @Override
   protected ICStatement optimizeStatement(ICStatement var1) {
      if (var1 instanceof ICAssignment var2) {
         ICLeftExpression var3 = var2.getLeft();
         if (var3 instanceof ICIdentifier) {
            ICExpression var4 = var2.getRight();
            if (var4 instanceof ICOperation && ((ICOperation)var4).getOperatorType() == COperatorType.CAST) {
               ICType var5 = ((ICOperation)var4).getOperator().getCastType();
               if (var5 == ((ICIdentifier)var3).getType()) {
                  ICExpression var6 = ((ICOperation)var4).getFirstOperand();
                  var2.setRight(var6);
                  return var2;
               }
            }
         }
      }

      return null;
   }
}
