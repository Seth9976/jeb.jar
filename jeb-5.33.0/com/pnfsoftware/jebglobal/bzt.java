package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collection;
import java.util.Iterator;

public class bzt extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;
      boolean var2 = false;

      for (BasicBlock var4 : this.cfg) {
         int var5 = 0;

         label82:
         while (var5 < var4.size()) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            if (!var6.isAssignToVar()) {
               var5++;
            } else {
               IDVar var7 = var6.getAssignDestination().asVar();
               IDExpression var8 = var6.getAssignSource();
               if (!bpl.UT(var8)) {
                  var5++;
               } else {
                  long var9 = var6.getOffset();
                  if (!var2) {
                     this.analyzeChains(false);
                     var2 = true;
                  }

                  int var11 = var7.getId();
                  Collection var12 = this.dfa.getDefUses(var9, var11);
                  int var13 = 0;
                  Iterator var14 = var12.iterator();

                  while (true) {
                     long var15;
                     while (true) {
                        if (!var14.hasNext()) {
                           if (var13 > 0) {
                              this.dfa.notifyInstructionUpdate(var9);
                              var1++;
                           }

                           var5++;
                           continue label82;
                        }

                        var15 = (Long)var14.next();
                        Collection var17 = this.dfa.getUseDefs(var15, var11);
                        if (var17.size() == 1 && (Long)var17.iterator().next() == var9) {
                           break;
                        }

                        if (var17.size() >= 2) {
                           boolean var18 = false;

                           for (long var20 : var17) {
                              IDInstruction var22 = (IDInstruction)this.cfg.getInstruction(var20);
                              if (var22 == null || !var22.isAssignToVar(var11) || !var22.getAssignSource().equalsEx(var8, false)) {
                                 var18 = true;
                                 break;
                              }
                           }

                           if (!var18) {
                              break;
                           }
                        }
                     }

                     IDInstruction var23 = (IDInstruction)this.cfg.getInstruction(var15);
                     IDExpression var24 = var8.duplicate();
                     int var25 = var23.replaceUsedVariable(var7, var24);
                     if (var25 > 0) {
                        this.dfa.notifyInstructionUpdate(var23.getOffset());
                     }

                     var13 += var25;
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
