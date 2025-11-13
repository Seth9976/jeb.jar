package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;

public class cfm extends AbstractDOptimizer {
   public boolean q;
   boolean RF = true;
   boolean xK = true;

   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      int var2 = 0;
      if (this.RF) {
         for (BasicBlock var4 : this.cfg) {
            int var5 = 0;

            while (var5 < var4.size()) {
               if (var4.size() >= 2 && ((IDInstruction)var4.get(var5)).isNop()) {
                  var2++;
                  if (DUtil.removeInstruction(var4, var5)) {
                     continue;
                  }
               }

               var5++;
            }
         }
      }

      if (this.xK) {
         int var12 = 0;

         while (var12 < this.cfg.size()) {
            BasicBlock var13 = this.cfg.get(var12);
            IDInstruction var14;
            if (var13.size() == 1 && (var14 = (IDInstruction)var13.get(0)).isNop()) {
               BasicBlock var6 = var13.getOutputBlock(0);
               if (var6.irrinsize() > 0) {
                  if (((IDInstruction)var6.get(0)).isStoreException()) {
                     com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
                        new RuntimeException("Unexpected store-exception (reachable from a regular edge)"), this.ctx.getMethodSignature()
                     );
                     var12++;
                     continue;
                  }

                  var1.updateHandlerAddress((int)var6.getBase(), (int)var13.getBase());
               }

               this.cfg.deleteIrregularOutEdges(var13);
               var1.unprotectBlock((int)var13.getAddress());
               this.cfg.deleteEdge(var13, var6);
               IDInstruction var7 = (IDInstruction)var6.get(0);
               int var8 = (int)var7.getOffset();
               int var9 = (int)var14.getOffset();

               for (BasicBlock var11 : var6.getInputBlocks()) {
                  this.q((IDInstruction)var11.getLast(), var8, var9);
               }

               for (BasicBlock var17 : var13.getInputBlocks()) {
                  this.cfg.reconnectEdges(var17, var13, var6);
                  this.cfg.removeDuplicateEdges(var17, var6);
               }

               for (BasicBlock var18 : var13.getIrregularInputBlocks()) {
                  this.cfg.reconnectIrregularEdges(var18, var13, var6);
               }

               var1.moveProtectedBlock((int)var6.getAddress(), (int)var13.getAddress());
               var7.setOffset(var14.getOffset());
               var7.adjustSize(var14.getSize());
               this.cfg.removeBlock(var13);
               var2++;
            } else {
               var12++;
            }
         }
      }

      if (var2 > 0) {
         this.cleanGraph(true, !this.q);
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var2;
   }

   private void q(IDInstruction var1, int var2, int var3) {
      if (var1.isJump() || var1.isJcond()) {
         int var7 = var1.getBranchTarget();
         if (var7 == var2) {
            var1.setBranchTarget(var3);
         }
      } else if (var1.isSwitch()) {
         IDSwitchData var4 = var1.getSwitchData();

         for (IDTarget var6 : var4.getTargets(false)) {
            if (var6.getOffset() == var2) {
               var6.setOffset(var3);
            }
         }
      }
   }
}
