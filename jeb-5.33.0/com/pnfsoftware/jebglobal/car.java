package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class car extends AbstractDOptimizer {
   public car() {
      this.setPriority(-10.0);
   }

   @Override
   public int perform() {
      int var1 = 0;

      label104:
      for (BasicBlock var3 : this.cfg) {
         if (var3.size() == 1 && var3.irrinsize() == 0 && ((IDInstruction)var3.get(0)).isSwitchOnInt()) {
            IDInstruction var4 = (IDInstruction)var3.get(0);
            IDExpression var5 = var4.getSwitchExpression();
            if (var5.isVar()) {
               int var6 = var5.asVar().getId();
               this.analyzeChains();
               ArrayList var7 = null;

               for (BasicBlock var9 : var3.getInputs()) {
                  Collection var10 = this.dfa.getReachChains(var9, var9.size(), var6, 2);
                  if (var10.size() != 1) {
                     continue label104;
                  }

                  long var11 = (Long)var10.iterator().next();
                  IDInstruction var13;
                  if ((var13 = (IDInstruction)this.cfg.getInstruction(var11)) == null || !var13.isAssignToVar(var6) || !var13.getAssignSource().isConstantImm()
                     )
                   {
                     continue label104;
                  }

                  IDImm var14 = (IDImm)var13.getAssignSource();
                  int var15 = pC(var4, (int)var14.getRawValue());
                  if (var15 == var3.getBase()) {
                     continue label104;
                  }

                  if (var7 == null) {
                     var7 = new ArrayList();
                  }

                  var7.add(new car.Av(var9, var15));
               }

               if (var7 != null && !var7.isEmpty()) {
                  for (car.Av var17 : var7) {
                     if (var17.pC.getEndAddress() == var3.getBase()) {
                        var4.transformToJump(var17.A);
                        this.cfg.deleteOutEdges(var3);
                        this.cfg.addEdge(var3, this.cfg.getBlockAt((long)var17.A));
                     } else {
                        IDInstruction var18 = (IDInstruction)var17.pC.getLast();
                        if (!var18.isJump() && !var18.isJcond()) {
                           if (!var18.isSwitch()) {
                              throw new RuntimeException();
                           }

                           HashMap var19 = new HashMap();
                           var19.put((int)var3.getBase(), var17.A);
                           var18.updateTargets(var19);
                           this.cfg.reconnectEdge(var17.pC, var3, this.cfg.getBlockAt((long)var17.A));
                        } else {
                           var18.setBranchTarget(var17.A);
                           this.cfg.reconnectEdge(var17.pC, var3, this.cfg.getBlockAt((long)var17.A));
                        }
                     }
                  }

                  this.cfg.invalidateDataFlowAnalysis();
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cleanGraph();
      }

      return var1;
   }

   static int pC(IDInstruction var0, Object var1) {
      IDTarget var2 = var0.getSwitchData().getTargetForCase(var1);
      return var2 == null ? (int)var0.getOffsetEnd() : var2.getOffset();
   }

   static class Av {
      BasicBlock pC;
      int A;

      public Av(BasicBlock var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("BLK:0x%X => TARGET=0x%X", this.pC.getBase(), this.A);
      }
   }
}
