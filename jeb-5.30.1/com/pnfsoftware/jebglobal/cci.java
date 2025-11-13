package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.util.base.Assert;

public class cci extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;
      int var2 = 0;

      label67:
      while (var2 < this.cfg.size()) {
         BasicBlock var3 = this.cfg.get(var2);

         for (int var4 = 0; var4 < var3.insize() - 1; var4++) {
            BasicBlock var5 = var3.getInputBlock(var4);
            if (var5.size() != 1 || !((IDInstruction)var5.get(0)).isJump()) {
               for (int var6 = var4 + 1; var6 < var3.insize(); var6++) {
                  BasicBlock var7 = var3.getInputBlock(var6);
                  if (this.q(var5, var7)) {
                     BasicBlock var8 = null;
                     BasicBlock var9 = null;
                     if (this.q(var5)) {
                        var8 = var5;
                        var9 = var7;
                     } else if (this.q(var7)) {
                        var8 = var7;
                        var9 = var5;
                     }

                     if (var8 != null && var8.insize() > 0) {
                        for (BasicBlock var11 : var8.getInputBlocks()) {
                           IDInstruction var12 = (IDInstruction)var11.getLast();
                           if (var12.isJump()) {
                              var12.setBranchTarget((int)var9.getBase());
                              this.cfg.reconnectEdge(var11, var8, var9);
                           } else {
                              this.cfg.deleteOutEdges(var8);
                              this.cfg.deleteIrregularOutEdges(var8);
                              this.ctx.getExceptionData().unprotectBlock((int)var8.getBase());
                              var8.forEach(var0 -> var0.transformToNop());
                              ((IDInstruction)var8.getLast()).transformToJump((int)var9.getBase());
                              this.cfg.addEdge(var8, var9);
                           }
                        }

                        var1++;
                        continue label67;
                     }
                  }
               }
            }
         }

         var2++;
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean q(BasicBlock var1, BasicBlock var2) {
      if (var1.irrinsize() == 0 && var2.irrinsize() == 0) {
         if (var1.outsize() == 1 && var2.outsize() == 1) {
            Assert.a(var1.getOutputBlock(0) == var2.getOutputBlock(0));
            if (!this.ctx.getExceptionData().compareHandlers((int)var1.getBase(), (int)var2.getBase())) {
               return false;
            } else {
               int var3 = var1.size();
               if (((IDInstruction)var1.getLast()).getBreakingFlow().isBroken()) {
                  if (!((IDInstruction)var1.getLast()).isJump()) {
                     return false;
                  }

                  var3--;
               }

               int var4 = var2.size();
               if (((IDInstruction)var2.getLast()).getBreakingFlow().isBroken()) {
                  if (!((IDInstruction)var2.getLast()).isJump()) {
                     return false;
                  }

                  var4--;
               }

               if (var3 != var4) {
                  return false;
               } else {
                  for (int var5 = 0; var5 < var3; var5++) {
                     IDInstruction var6 = (IDInstruction)var1.get(var5);
                     if (!((bub)var6).q((IDInstruction)var2.get(var5))) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean q(BasicBlock var1) {
      for (BasicBlock var3 : var1.getInputs()) {
         IDInstruction var4 = (IDInstruction)var3.getLast();
         if (!var4.isJump()) {
            if (var4.getBreakingFlow().isBroken()) {
               return false;
            }

            Assert.a(var3.getEndAddress() == var1.getBase());
         }
      }

      return true;
   }
}
