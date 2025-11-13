package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.DUI;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.List;

public class chf extends AbstractDOptimizer {
   @Override
   public int perform() {
      BasicBlock var1 = this.cfg.get(0);
      if (var1.allinsize() != 0) {
         return 0;
      } else {
         List var2 = this.ctx.getParameterVariables();
         if (var2.isEmpty()) {
            return 0;
         } else {
            int var3 = 0;
            IDFA var4 = this.analyzeChains();

            label91:
            for (int var5 = 0; var5 < var1.size(); var5++) {
               IDInstruction var6 = (IDInstruction)var1.get(var5);
               if (!var6.isNop()) {
                  if (!var6.isAssignFromVarToVar()) {
                     break;
                  }

                  IDVar var7 = var6.getAssignSource().asVar();
                  if (!var2.contains(var7)) {
                     break;
                  }

                  IDVar var8 = var6.getAssignDestination().asVar();
                  if (!var8.getType().equals(var7.getType()) || !var4.checkSingleDef(var6.getOffset(), var7.getId(), -1L)) {
                     break;
                  }

                  for (BasicBlock var10 : this.cfg) {
                     int var11 = 0;
                     if (var10 == var1) {
                        var11 = var5 + 1;
                     }

                     while (var11 < var10.size()) {
                        IDInstruction var12 = (IDInstruction)var10.get(var11);
                        DUI var13 = var4.getDUI(var12.getOffset());
                        if (var13.getUse().contains(var7.getId()) || var13.getDef().contains(var7.getId())) {
                           break label91;
                        }

                        var11++;
                     }
                  }

                  int var14 = 0;

                  for (BasicBlock var16 : this.cfg) {
                     int var17 = 0;
                     if (var16 == var1) {
                        var6.transformToNop();
                        var14++;
                        var17 = var5 + 1;
                     }

                     while (var17 < var16.size()) {
                        IDInstruction var18 = (IDInstruction)var16.get(var17);
                        var14 += var18.replaceVariable(var8, var7);
                        var17++;
                     }
                  }

                  if (var14 > 0) {
                     var3++;
                  }
               }
            }

            if (var3 > 0) {
               this.cfg.invalidateDataFlowAnalysis();
            }

            return var3;
         }
      }
   }
}
