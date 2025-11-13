package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpWithOptionalCondition;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class atr extends AbstractEOptimizer {
   public atr() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected int perform() {
      atr.eo var1 = new atr.eo();

      for (IEStatement var3 : this.cfg.instructions()) {
         var1.q(var3, null);
      }

      return this.postPerform(var1.q, var1.RF);
   }

   private class eo {
      int q;
      boolean RF;

      void q(IEGeneric var1, IEGeneric var2) {
         if (var1 instanceof IEJumpWithOptionalCondition) {
            IEGeneric var3 = ((IEJumpWithOptionalCondition)var1).getCondition();
            if (var3 != null) {
               this.RF(var3, var1);
            }
         } else if (var1 instanceof IECond) {
            IEGeneric var6 = ((IECond)var1).getCondition();
            if (var6 != null) {
               this.RF(var6, var1);
            }
         } else if (var1 instanceof IEOperation var7) {
            if (var7.getOperationType().isAnyOf(OperationType.LOG_OR, OperationType.LOG_AND)) {
               IEGeneric var4 = var7.getOperand1();
               IEGeneric var5 = var7.getOperand2();
               this.RF(var4, var7);
               this.RF(var5, var7);
            } else if (var7.getOperationType().isAnyOf(OperationType.LOG_NOT)) {
               IEGeneric var9 = var7.getOperand1();
               this.RF(var9, var7);
            }
         }

         for (IEGeneric var10 : EUtil.getSubExpressions(var1)) {
            this.q(var10, var1);
         }
      }

      private int RF(IEGeneric var1, IEGeneric var2) {
         Object var3 = null;
         if (EUtil.isZeroExtend(var1)) {
            var3 = ((IECompose)var1).getLowPart();
         } else if (var1 instanceof IEOperation var4) {
            OperationType var5 = var4.getOperationType();
            if (var5 == OperationType.LOG_NOT) {
               if (var4.getOperand1().isOperation(OperationType.SUB)) {
                  IEOperation var6 = (IEOperation)var4.getOperand1();
                  var3 = atr.this.ectx.createOperation(OperationType.LOG_EQ, var6.getOperand1(), var6.getOperand2());
                  ((IEGeneric)var3).copyProperties(var1);
               }
            } else if (var5 == OperationType.SUB) {
               var3 = atr.this.ectx.createOperation(OperationType.LOG_NEQ, var4.getOperand1(), var4.getOperand2());
               ((IEGeneric)var3).copyProperties(var1);
            }
         }

         if (var3 != null && var2.replaceSubExpression(var1, (IEGeneric)var3)) {
            this.q++;
            return 1;
         } else {
            return 0;
         }
      }
   }
}
