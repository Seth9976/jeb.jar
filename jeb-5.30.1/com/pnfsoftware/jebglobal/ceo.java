package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;

public class ceo extends AbstractDOptimizer {
   public ceo() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         if (var3.size() > 1) {
            for (int var4 = 0; var4 < var3.size() - 1; var4++) {
               IDInstruction var5 = (IDInstruction)var3.get(var4);
               if (var5.isAssign() && var5.getAssignDestination() instanceof IDField var6) {
                  IDVar var17 = null;
                  if (var6 instanceof IDInstanceField var8) {
                     IDExpression var9 = var8.getInstance();
                     if (!(var9 instanceof IDVar)) {
                        continue;
                     }

                     var17 = (IDVar)var9;
                  } else if (!(var6 instanceof IDStaticField)) {
                     continue;
                  }

                  IDExpression var18 = var5.getAssignSource();
                  if (!var18.hasSideEffects(this.ctx, true) && (var18 instanceof IDImm || bto.oW(var18))) {
                     for (int var19 = var4 + 1; var19 < var3.size(); var19++) {
                        IDInstruction var10 = (IDInstruction)var3.get(var19);
                        boolean var11 = !var10.visitInstruction(new cep(this, var6), true);
                        if (!var11) {
                           if (var10.hasUseSideEffects(false)) {
                              break;
                           }
                        } else {
                           ArrayList var12 = new ArrayList();
                           var11 = !var10.visitInstructionPostOrder(new ceq(this, var6, var12), true);

                           for (Couple var14 : var12) {
                              IDExpression var15 = (IDExpression)var14.getFirst();
                              IDExpression var16 = (IDExpression)var14.getSecond();
                              if (var16.replaceSubExpression(var15, var18.duplicate())) {
                                 var1++;
                              }
                           }

                           if (var11 || var17 != null && var10.isAssignToVar(var17.getId())) {
                              break;
                           }
                        }
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
