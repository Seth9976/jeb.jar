package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;

public class bxv extends AbstractDOptimizer {
   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;

         label58:
         for (BasicBlock var4 : this.cfg) {
            if (var4.irrinsize() > 0
               && var4.insize() == 0
               && var1.getHandledExceptionTypesAt((int)var4.getAddress()).contains(-1)
               && ((IDInstruction)var4.get(0)).isStoreException()
               && !((IDInstruction)var4.getLast()).isJump()) {
               for (int var5 = 1; var5 < var4.size(); var5++) {
                  if (!((IDInstruction)var4.get(var5)).isAssignFromVarToVar()) {
                     continue label58;
                  }
               }

               var2 += this.pC(var4);
            }
         }

         if (var2 > 0) {
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private int pC(BasicBlock var1) {
      int var2 = (int)var1.getOutputBlock(0).getAddress();
      int var3 = 0;

      label63:
      for (BasicBlock var5 : this.cfg) {
         if (var5.irrinsize() > 0 && var5.insize() == 0 && var5.size() == var1.size() + 1 && ((IDInstruction)var5.getLast()).isJumpTo(var2)) {
            for (int var6 = 0; var6 < var1.size(); var6++) {
               if (!((bpv)var1.get(var6)).pC((IDInstruction)var5.get(var6))) {
                  continue label63;
               }
            }

            for (BasicBlock var7 : var5.getIrregularInputBlocks()) {
               this.cfg.reconnectIrregularEdges(var7, var5, var1);
            }

            for (IDExceptionHandler var10 : this.ctx.getExceptionData().getHandlers((int)var5.getAddress())) {
               var10.setAddress((int)var1.getBase());
            }

            var3++;
         }
      }

      return var3;
   }
}
