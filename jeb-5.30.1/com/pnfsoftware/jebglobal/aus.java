package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;

public class aus extends AbstractEOptimizer {
   public aus() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.insize() == 1 && var3.size() == 1 && ((IEStatement)var3.get(0)).isSwitch()) {
            BasicBlock var4 = var3.getInputBlock(0);

            while (var4 != null && ((IEStatement)var4.getLast()).isConditionalJump()) {
               IESwitch var5 = ((IEStatement)var3.get(0)).asSwitch();
               IEGeneric var6 = var5.getControlExpression();
               if (EUtil.hasNoSideEffect(var6)) {
                  IEJump var7 = ((IEStatement)var4.getLast()).asJump();
                  IEGeneric var8 = var7.getCondition();
                  int var9 = var7.getBranchAddress();
                  if (var8.isOperation(OperationType.LOG_EQ)) {
                     IEOperation var10 = var8.asOperation();
                     IEGeneric var11 = var10.getOperand1();
                     IEGeneric var12 = var10.getOperand2();
                     if (var11.equals(var6) && var12 instanceof IEImm) {
                        boolean var13 = true;
                        if (var5.getDefaultAddress() == var9) {
                           var13 = false;
                        } else {
                           for (Couple var15 : var5.getCases()) {
                              if (((IEGeneric)var15.getFirst()).equals(var12)) {
                                 if ((Integer)var15.getSecond() != var9) {
                                    break;
                                 }

                                 var13 = false;
                                 break;
                              }
                           }
                        }

                        this.cfg.deleteOutEdges(var4);
                        this.cfg.addEdge(var4, var3);
                        var4.set(var4.size() - 1, this.ectx.createNop(var7));
                        if (var13) {
                           var5.addCase(var12, var9);
                           this.cfg.addEdge(var3, this.cfg.getBlockAt((long)var9));
                        }

                        this.cfg.invalidateDataFlowAnalysis();
                        var1++;
                        if (var4.size() == 1 && var4.insize() == 1) {
                           var4 = var4.getInputBlock(0);
                           continue;
                        }
                     }
                  }
               }

               var4 = null;
            }
         }
      }

      return this.postPerform(var1);
   }
}
