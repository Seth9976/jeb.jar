package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;

public class cer extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (IDInstruction var3 : this.cfg.instructions()) {
         if (var3.isAssign()) {
            IDExpression var4 = var3.getAssignDestination();
            IDExpression var5 = var3.getAssignSource();
            if (var4 instanceof IDVar && var5 instanceof IDNewArrayInfo) {
               IDVar var6 = (IDVar)var4;
               int var7 = var6.getId();
               IDNewArrayInfo var8 = (IDNewArrayInfo)var5;
               if (var8.areSubExpsAllImms()) {
                  this.analyzeChains();
                  Long var9 = this.dfa.checkSingleUse(var3.getOffset(), var7);
                  if (var9 != null && this.dfa.checkSingleDef(var9, var7, var3.getOffset())) {
                     IDInstruction var10 = (IDInstruction)this.cfg.getInstructionAt(var9);
                     if (var10 != null && var10.countUsedVariable(var6) == 1 && var10.replaceUsedVariable(var6, var8) >= 1) {
                        var1++;
                        this.cfg.invalidateDataFlowAnalysis(var10.getOffset());
                     }
                  }
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
