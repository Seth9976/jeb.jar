package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class aqo extends AbstractEExpressionOptimizer {
   public aqo() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.addTag("deobfuscator");
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IEOperation)) {
         return null;
      } else {
         OperationType var2 = ((IEOperation)var1).getOperationType();
         IEGeneric var3 = ((IEOperation)var1).getOperand1();
         IEGeneric var4 = ((IEOperation)var1).getOperand2();
         if ((var2 == OperationType.LOG_EQ || var2 == OperationType.LOG_NEQ)
            && EUtil.isOperation(var3, OperationType.AND, OperationType.OR)
            && var4 instanceof IEImm) {
            IEOperation var5 = var3.asOperation();
            IEGeneric var6 = var5.getOperand1();
            IEGeneric var7 = var5.getOperand2();
            if (EUtil.hasNoSideEffect(var6) && var7 instanceof IEImm) {
               IEImm var8 = var7.asImm();
               IEImm var9 = var4.asImm();
               boolean var10 = false;
               if (var5.getOperationType() == OperationType.AND) {
                  var10 = !var8._not()._and(var9).isZero();
               } else if (var5.getOperationType() == OperationType.OR) {
                  var10 = !var8._and(var9._not()).isZero();
               }

               if (var10) {
                  IEImm var11 = var2 == OperationType.LOG_EQ ? EUtil.zero(var1.getBitsize()) : EUtil.one(var1.getBitsize());
                  return AbstractEExpressionOptimizer.EOR.create(var11, true);
               }
            }
         }

         return null;
      }
   }
}
