package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.List;

public class cdf extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         this.checkInterrupted();
         int var4 = 0;

         label86:
         while (var4 < var3.size() - 1) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (var5.isAssignToVar() && var5.getAssignSource() instanceof IDNewArrayInfo) {
               IDVar var6 = (IDVar)var5.getAssignDestination();
               IDNewArrayInfo var7 = (IDNewArrayInfo)var5.getAssignSource();
               if (!(var7.getSize() instanceof IDImm)) {
                  var4++;
               } else {
                  int var8 = (int)((IDImm)var7.getSize()).getRawValue();
                  if (var8 == 0) {
                     var4++;
                  } else {
                     List var9 = var7.getInitialValues();
                     if (var9.isEmpty()) {
                        var4++;
                     } else {
                        for (int var10 = var4 + 1; var10 < var3.size(); var1++) {
                           IDInstruction var11 = (IDInstruction)var3.get(var10);
                           if (!var11.isAssign() || !(var11.getAssignDestination() instanceof IDArrayElt)) {
                              var4++;
                              break;
                           }

                           IDArrayElt var12 = (IDArrayElt)var11.getAssignDestination();
                           IDExpression var13 = var11.getAssignSource();
                           if (!var12.getArray().equals(var6)) {
                              var4++;
                              break;
                           }

                           IDExpression var14 = var12.getIndex();
                           if (!(var14 instanceof IDImm)) {
                              var4++;
                              break;
                           }

                           int var15 = (int)((IDImm)var14).getRawValue();
                           if (var15 >= var7.getCountOfInitialValues()) {
                              var4++;
                              break;
                           }

                           for (int var16 = var15; var16 < var9.size(); var16++) {
                              if (((IDExpression)var9.get(var16)).hasSideEffects(this.ctx, true)) {
                                 var4++;
                                 continue label86;
                              }
                           }

                           if (DUtil.hasVariable(var13, var6.getId())) {
                              var4++;
                              break;
                           }

                           var7.setInitialValue(var15, var13);
                           var11.transformToNop();
                           var5.adjustSize(var11.getSize());
                           var3.remove(var10);
                        }
                     }
                  }
               }
            } else {
               var4++;
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
