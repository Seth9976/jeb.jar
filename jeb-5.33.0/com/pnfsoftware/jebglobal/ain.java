package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCElementOptimizer;
import java.math.BigInteger;

public class ain extends AbstractCElementOptimizer {
   @Override
   protected ICElement optimizeElement(ICElement var1, ICElement var2) {
      return !(var1 instanceof ICOperation) ? null : this.pC((ICOperation)var1);
   }

   private ICOperation pC(ICOperation var1) {
      if (!CUtil.isOperation(var1, COperatorType.COND)) {
         return null;
      } else {
         ICExpression var2 = var1.getFirstOperand();
         ICExpression var3 = var1.getSecondOperand();
         ICExpression var4 = var1.getThirdOperand();
         if (var3 instanceof ICConstantInteger && var4 instanceof ICConstantInteger) {
            BigInteger var5 = ((ICConstantInteger)var3).getIntegerValue();
            BigInteger var6 = ((ICConstantInteger)var4).getIntegerValue();
            if (var5.equals(BigInteger.ONE) && var6.equals(BigInteger.ZERO)) {
               if (var2 instanceof ICOperation) {
                  return (ICOperation)var2;
               }

               return this.ef.createOperation(COperatorType.LOG_IDENT, var2);
            }

            if (var5.equals(BigInteger.ZERO) && var6.equals(BigInteger.ONE)) {
               return this.ef.createOperation(COperatorType.LOG_NOT, var2);
            }
         }

         return null;
      }
   }
}
