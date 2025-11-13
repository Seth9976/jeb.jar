package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class Wb extends AbstractEExpressionOptimizer {
   public Wb() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (EUtil.getOperation(var1, OperationType.LOG_EQ, OperationType.LOG_NEQ) != null) {
         IEGeneric var2 = ((IEOperation)var1).getOperand1();
         IEGeneric var3 = ((IEOperation)var1).getOperand2();
         if (this.q(var2) && (this.q(var3) || var3 instanceof IEImm)) {
            IEGeneric var13 = ((IESlice)var2).getWholeExpression();
            if (EUtil.isOperation(var13, OperationType.AND) && this.q(var3)) {
               var2 = ((IEOperation)var1).getOperand2();
               var3 = ((IEOperation)var1).getOperand1();
               var13 = ((IESlice)var2).getWholeExpression();
            }

            boolean var7 = true;
            IEGeneric var10;
            Object var12;
            if (EUtil.isOperation(var13, OperationType.SUB)) {
               var7 = false;
               var10 = ((IEOperation)var13).getOperand1();
               var12 = ((IEOperation)var13).getOperand2();
            } else {
               var10 = var13;
               var12 = this.ectx.createImm(0L, var13.getBitsize());
            }

            if (EUtil.isImmZero(var3) || this.q(var3, var10, (IEGeneric)var12, var13, var7)) {
               if (EUtil.isOperation(var1, OperationType.LOG_NEQ)) {
                  return AbstractEExpressionOptimizer.EOR.create(EUtil.ltS(var10, (IEGeneric)var12));
               }

               return AbstractEExpressionOptimizer.EOR.create(EUtil.geS(var10, (IEGeneric)var12));
            }
         } else if (EUtil.isOperation(var2, OperationType.CARRY) && EUtil.isImmZero(var3)) {
            IEGeneric var4 = ((IEOperation)var2).getOperand1();
            IEGeneric var5 = ((IEOperation)var2).getOperand2();
            if (EUtil.isOperation(var4, OperationType.NOT)) {
               IEGeneric var6 = var4;
               var4 = var5;
               var5 = ((IEOperation)var6).getOperand1();
            } else {
               if (!EUtil.isOperation(var5, OperationType.NOT)) {
                  return null;
               }

               var5 = ((IEOperation)var5).getOperand1();
            }

            if (EUtil.isOperation(var4, OperationType.ADD) && EUtil.isImmValue(((IEOperation)var4).getOperand2(), 1L)) {
               var4 = ((IEOperation)var4).getOperand1();
            } else {
               var4 = EUtil.sub(var4, EUtil.one(var4.getBitsize()));
            }

            if (EUtil.isOperation(var1, OperationType.LOG_NEQ)) {
               return AbstractEExpressionOptimizer.EOR.create(EUtil.geU(var4, var5));
            }
         }
      }

      return null;
   }

   private boolean q(IEGeneric var1, IEGeneric var2, IEGeneric var3, IEGeneric var4, boolean var5) {
      auy var6 = new auy(this.ectx);
      IEGeneric var7 = EUtil.buildOverflowFlag(var2, var3, var4, var5);
      IEGeneric var8 = var6.q(var7);
      IEGeneric var9 = var6.q(var1.duplicate());
      return var8 != null ? var8.equals(var9) : var7.equals(var9);
   }

   private boolean q(IEGeneric var1) {
      return !(var1 instanceof IESlice var2) ? false : var2.getBitsize() == 1 && var2.getBitEnd() == var2.getWholeExpression().getBitsize();
   }
}
