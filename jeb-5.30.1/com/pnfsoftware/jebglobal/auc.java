package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class auc extends AbstractEStatementOptimizer {
   public auc() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   protected IEStatement optimizeStatement(IEStatement var1) {
      if (var1 instanceof IEJump) {
         IEGeneric var2 = ((IEJump)var1).getCondition();
         if (var2 != null) {
            boolean var3 = false;
            if (EUtil.isOperation(var2, OperationType.LOG_NOT)) {
               var2 = ((IEOperation)var2).getOperand1();
               var3 = true;
            }

            if (EUtil.isOperation(var2, OperationType.SUB)) {
               IEGeneric var9 = ((IEOperation)var2).getOperand1();
               IEGeneric var10 = ((IEOperation)var2).getOperand2();
               OperationType var11 = var3 ? OperationType.LOG_EQ : OperationType.LOG_NEQ;
               ((IEJump)var1).setCondition(this.ectx.createOperation(var11, var9, var10));
               return var1;
            }

            if (var2 instanceof IECond && ((IECond)var2).getExpressionTrue() instanceof IEImm && ((IECond)var2).getExpressionFalse() instanceof IEImm) {
               IEGeneric var8 = ((IECond)var2).getCondition();
               IEImm var5 = (IEImm)((IECond)var2).getExpressionTrue();
               IEImm var6 = (IEImm)((IECond)var2).getExpressionFalse();
               Object var7;
               if (var5._signum() != 0 && var6._signum() == 0) {
                  var7 = var8;
               } else {
                  if (var5._signum() != 0 || var6._signum() == 0) {
                     return null;
                  }

                  var7 = var8;
                  var3 = !var3;
               }

               if (var3) {
                  var7 = this.ectx.createOperation(OperationType.LOG_NOT, (IEGeneric)var7);
               }

               ((IEJump)var1).setCondition((IEGeneric)var7);
               return var1;
            }

            if (EUtil.isZeroExtend(var2)) {
               Object var4 = ((IECompose)var2).getLowPart();
               if (var3) {
                  var4 = this.ectx.createOperation(OperationType.LOG_NOT, (IEGeneric)var4);
               }

               ((IEJump)var1).setCondition((IEGeneric)var4);
               return var1;
            }
         }
      }

      return null;
   }
}
