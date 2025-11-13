package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;

public class cap extends AbstractDOptimizer {
   public cap() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         if (var3.size() > 1) {
            label94:
            for (int var4 = var3.size() - 1; var4 >= 0; var4--) {
               IDInstruction var5 = (IDInstruction)var3.get(var4);
               if (var5.isAssign() && var5.getAssignDestination() instanceof IDField var6) {
                  IDVar var13 = null;
                  if (var6 instanceof IDInstanceField var8) {
                     IDExpression var9 = var8.getInstance();
                     if (!(var9 instanceof IDVar)) {
                        continue;
                     }

                     var13 = (IDVar)var9;
                  } else if (!(var6 instanceof IDStaticField)) {
                     continue;
                  }

                  for (int var14 = var4 - 1; var14 >= 0; var14--) {
                     IDInstruction var15 = (IDInstruction)var3.get(var14);
                     if (var15.isAssign() && var15.getAssignDestination().equals(var6)) {
                        IDExpression var10 = var15.getAssignSource();
                        if (var10.hasSideEffects(this.ctx, true)) {
                           break;
                        }

                        for (int var11 = var4 - 1; var11 > var14; var11--) {
                           IDInstruction var12 = (IDInstruction)var3.get(var11);
                           if (var12.hasUseSideEffects(true)) {
                              continue label94;
                           }
                        }

                        if (var13 != null) {
                           for (int var16 = var4 - 1; var16 > var14; var16--) {
                              IDInstruction var17 = (IDInstruction)var3.get(var16);
                              if (var17.isAssignToVar(var13.getId())) {
                                 break;
                              }
                           }
                        }

                        var15.transformToNop();
                        var1++;
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
