package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class arq extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(arq.class);

   public arq() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      int var2 = 0;

      do {
         var1 += var2;
         var2 = 0;

         for (BasicBlock var4 : this.cfg.getBlocks()) {
            if (EUtil.isConditionalJump((IEStatement)var4.getLast())) {
               IEJump var5 = (IEJump)var4.getLast();
               IEGeneric var6 = var5.getCondition();
               if (!EUtil.containsUndeterminedInvocations(var6)) {
                  boolean var7 = this.q(var6, var4);
                  if (var7) {
                     aqq.q(this.ectx, var4, true);
                     var2++;
                     break;
                  }
               }
            }
         }
      } while (var2 > 0);

      return this.postPerform(var1);
   }

   protected boolean q(IEGeneric var1, BasicBlock var2) {
      IEJump var3 = (IEJump)var2.getLast();
      if (EUtil.isConditionalJump((IEStatement)var2.getOutputBlock(0).get(0))) {
         IEJump var4 = (IEJump)var2.getOutputBlock(0).get(0);
         IEGeneric var5 = var4.getCondition();
         if (var4.getBranchAddress() == var3.getBranchAddress() && !EUtil.containsUndeterminedInvocations(var5) && !EUtil.containsUndeterminedInvocations(var1)
            )
          {
            if (amw.q(var5, EUtil.reversePredicate(var1))) {
               return true;
            }

            if (var5 instanceof IEOperation && var1 instanceof IEOperation) {
               OperationType var6 = var1.asOperation().getOperationType();
               OperationType var7 = var5.asOperation().getOperationType();
               OperationType var8 = EUtil.getReverseOperation(var6);
               if (var8 == var7
                  && amw.q(var5.asOperation().getOperand1(), var1.asOperation().getOperand1())
                  && amw.q(var5.asOperation().getOperand2(), var1.asOperation().getOperand2())) {
                  return true;
               }
            }
         }
      }

      return false;
   }
}
