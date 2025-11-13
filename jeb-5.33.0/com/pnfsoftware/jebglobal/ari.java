package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.Collection;
import java.util.Iterator;

public class ari extends AbstractEOptimizer {
   public ari() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      Iterator var2 = this.cfg.iterator();

      label128:
      while (true) {
         BasicBlock var3;
         IEStatement var5;
         IECond var6;
         IEVar var8;
         IEJump var9;
         byte var11;
         while (true) {
            if (!var2.hasNext()) {
               return this.postPerform(var1);
            }

            var3 = (BasicBlock)var2.next();
            if (var3.size() >= 2) {
               IEStatement var4 = (IEStatement)var3.getLast();
               if (var4.isConditionalJump()) {
                  var5 = (IEStatement)var3.get(-2);
                  if (var5.isAssign() && var5.asAssign().getRightOperand().isCond() && var5.asAssign().getLeftOperand().isVar()) {
                     var6 = var5.asAssign().getRightOperand().asCond();
                     IEGeneric var7 = var6.getCondition();
                     var8 = var5.asAssign().getLeftOperand().asVar();
                     var9 = var4.asJump();
                     IEGeneric var10 = var9.getCondition();
                     if (EUtil.hasNoSideEffect(var6)) {
                        if (var7.equalsEx(var10, false)) {
                           var11 = 0;
                           break;
                        }

                        if (var7.isOperation() && var7.asOperation().getOperationType() == OperationType.LOG_NOT) {
                           var7 = var7.asOperation().getOperand1();
                           var11 = 1;
                        } else if (var10.isOperation() && var10.asOperation().getOperationType() == OperationType.LOG_NOT) {
                           var10 = var10.asOperation().getOperand1();
                           var11 = 2;
                        } else {
                           var10 = EUtil.notL(var10, true);
                           if (!var7.equalsEx(var10, false)) {
                              continue;
                           }

                           var11 = 1;
                        }

                        if (var7.equalsEx(var10, false)) {
                           break;
                        }
                     }
                  }
               }
            }
         }

         IDFA var12 = this.cfg.doDataFlowAnalysis();
         Collection var13 = var12.getDefUses(var3.getAddressOfInstruction(var3.size() - 2), var8.getId(), 3);
         if (var13 != null && var13.size() == 2) {
            long var14 = var3.getAddressOfInstruction(var3.size() - 2);
            BasicBlock var16 = this.cfg.getBlockAt((long)var9.getBranchAddress());
            BasicBlock var17 = this.cfg.getBlockAt(var3.getEndAddress());
            Long var18 = null;
            Long var19 = null;
            IEStatement var20 = null;
            IEStatement var21 = null;

            for (long var23 : var13) {
               Couple var25 = this.cfg.getInstructionLocation(var23);
               BasicBlock var26 = (BasicBlock)var25.getFirst();
               int var27 = (Integer)var25.getSecond();
               if (var26 == var16) {
                  if (var18 != null) {
                     continue label128;
                  }

                  var18 = var26.getAddressOfInstruction(var27);
                  if (!var12.checkSingleDef(var18, var8.getId(), var14)) {
                     continue label128;
                  }

                  var20 = (IEStatement)var26.get(var27);
                  if (EUtil.countVariablePresence(var20, var8) != 1) {
                     continue label128;
                  }
               } else {
                  if (var26 != var17 || var19 != null) {
                     continue label128;
                  }

                  var19 = var26.getAddressOfInstruction(var27);
                  if (!var12.checkSingleDef(var19, var8.getId(), var14)) {
                     continue label128;
                  }

                  var21 = (IEStatement)var26.get(var27);
                  if (EUtil.countVariablePresence(var21, var8) != 1) {
                     continue label128;
                  }
               }
            }

            IEGeneric var28 = var11 == 0 ? var6.getExpressionTrue() : var6.getExpressionFalse();
            var20.replaceUsedVar(var8, var28);
            var28 = var11 == 0 ? var6.getExpressionFalse() : var6.getExpressionTrue();
            var21.replaceUsedVar(var8, var28);
            var3.set(var3.size() - 2, this.ectx.createNop(var5));
            var1++;
            this.cfg.invalidateDataFlowAnalysis();
         }
      }
   }
}
