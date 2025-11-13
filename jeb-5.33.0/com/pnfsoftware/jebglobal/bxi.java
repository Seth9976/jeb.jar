package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collection;

public class bxi extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         if (var3.irrinsize() >= 1) {
            IDInstruction var4 = (IDInstruction)var3.get(0);
            if (var4.isStoreException()) {
               IDVar var5 = var4.getStoredExceptionVariable();
               this.analyzeChains();
               Collection var6 = this.dfa.getDefUses(var4.getOffset(), var5.getId(), 1);
               if (var6.isEmpty()) {
                  var4.transformToNop();
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
