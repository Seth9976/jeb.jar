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

public class aut extends AbstractEOptimizer {
   public aut() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      label82:
      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.insize() == 1 && var3.size() == 1 && ((IEStatement)var3.get(0)).isConditionalJump()) {
            BasicBlock var4 = var3.getInputBlock(0);

            while (true) {
               do {
                  if (var3 == null || !((IEStatement)var4.getLast()).isSwitch()) {
                     continue label82;
                  }

                  IESwitch var5 = ((IEStatement)var4.getLast()).asSwitch();
                  IEGeneric var6 = var5.getControlExpression();
                  if (var5.getDefaultAddress() != var4.getEndAddress() || !EUtil.hasNoSideEffect(var6)) {
                     break;
                  }

                  IEJump var7 = ((IEStatement)var3.get(0)).asJump();
                  IEGeneric var8 = var7.getCondition();
                  int var9 = var7.getBranchAddress();
                  if (!var8.isOperation(OperationType.LOG_EQ)) {
                     break;
                  }

                  IEOperation var10 = var8.asOperation();
                  IEGeneric var11 = var10.getOperand1();
                  IEGeneric var12 = var10.getOperand2();
                  if (!var11.equals(var6) || !(var12 instanceof IEImm)) {
                     break;
                  }

                  boolean var13 = true;
                  if (var5.getDefaultAddress() == var9) {
                     continue label82;
                  }

                  for (Couple var15 : var5.getCases()) {
                     if (((IEGeneric)var15.getFirst()).equals(var12)) {
                        var13 = false;
                        break;
                     }
                  }

                  BasicBlock var16 = var3.getOutputBlock(0);
                  this.cfg.deleteOutEdges(var3);
                  this.cfg.addEdge(var3, var16);
                  var3.set(0, this.ectx.createNop(var7));
                  if (var13) {
                     var5.addCase(var12, var9);
                     this.cfg.addEdge(var4, this.cfg.getBlockAt((long)var9));
                  }

                  this.cfg.invalidateDataFlowAnalysis();
                  var1++;
                  if (var3.outsize() != 1) {
                     break;
                  }

                  var3 = var3.getOutputBlock(0);
               } while (var3.insize() == 1 && var3.size() == 1 && ((IEStatement)var3.get(0)).isConditionalJump());

               var3 = null;
            }
         }
      }

      return this.postPerform(var1);
   }
}
