package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;

public class bxu extends AbstractDOptimizer {
   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1 != null && !var1.isEmpty()) {
         int var2 = 0;

         for (BasicBlock var4 : this.cfg) {
            if (var4.size() == 1 && var4.irrinsize() > 0 && var4.insize() == 0 && ((IDInstruction)var4.get(0)).isStoreException()) {
               IDVar var5 = ((IDInstruction)var4.get(0)).getStoredExceptionVariable();
               var2 += this.pC(var4, var5);
            }
         }

         for (BasicBlock var7 : this.cfg) {
            if (var7.size() == 2
               && var7.irrinsize() > 0
               && var7.insize() == 0
               && ((IDInstruction)var7.get(1)).isJump()
               && ((IDInstruction)var7.get(0)).isStoreException()) {
               IDVar var8 = ((IDInstruction)var7.get(0)).getStoredExceptionVariable();
               var2 += this.pC(var7, var8);
            }
         }

         if (var2 > 0) {
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      } else {
         return 0;
      }
   }

   int pC(BasicBlock var1, IDVar var2) {
      int var3 = 0;
      BasicBlock var4 = var1.getOutputBlock(0);

      for (BasicBlock var6 : var4.getInputs()) {
         if (var6 != var1
            && var6.size() == 2
            && var6.irrinsize() > 0
            && var6.insize() == 0
            && ((IDInstruction)var6.get(1)).isJump()
            && ((IDInstruction)var6.get(0)).isStoreException()
            && ((IDInstruction)var6.get(0)).getStoredExceptionVariable() == var2) {
            for (BasicBlock var8 : var6.getIrregularInputBlocks()) {
               this.cfg.reconnectIrregularEdges(var8, var6, var1);
            }

            for (IDExceptionHandler var10 : this.ctx.getExceptionData().getHandlers((int)var6.getAddress())) {
               var10.setAddress((int)var1.getBase());
            }

            var3++;
         }
      }

      return var3;
   }
}
