package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.List;

public class avf extends AbstractEOptimizer {
   public avf() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      List var2 = this.cfg.getBlocksView();

      for (int var3 = 0; var3 < var2.size(); var3++) {
         BasicBlock var4 = (BasicBlock)var2.get(var3);
         if (var3 < var2.size() - 1 && var4.size() >= 2 && ((IEStatement)var4.getLast()).isConditionalJump()) {
            IEGeneric var5 = ((IEStatement)var4.getLast()).asJump().getCondition();
            IEStatement var6 = (IEStatement)var4.get(var4.size() - 2);
            if (var6 instanceof IEAssign var7 && var7.getDstOperand() instanceof IEVar var8) {
               IEGeneric var13 = var7.getSrcOperand();
               if (EUtil.countVariablePresence(var13, var8) == 0) {
                  BasicBlock var10 = (BasicBlock)var2.get(var3 + 1);
                  if (var10.size() == 1 && var10.insize() == 1) {
                     IEStatement var11 = (IEStatement)var10.get(0);
                     IEGeneric var12 = null;
                     if (EUtil.isPCAssign(var11)) {
                        var12 = var11.asAssign().getSrcOperand();
                     } else if (var11.isJumpFar()) {
                        var12 = var11.asJumpFar().getJumpsite();
                     }

                     if (var12 instanceof IEMem
                        && EUtil.isOperation(var5, OperationType.GE_U, OperationType.GT_U, OperationType.LE_U, OperationType.LT_U)
                        && EUtil.getUsedVarIds(var12).contains(var8.getId())
                        && EUtil.countVariablePresence(var12, var8) == 1
                        && var12.replaceVar(var8, var13.duplicate()) >= 1) {
                        var5.replaceVar(var8, var13.duplicate());
                        var1++;
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return this.postPerform(var1);
   }
}
