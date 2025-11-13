package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.Set;

public class cay extends AbstractDOptimizer {
   boolean pC = true;

   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         IDInstruction var4 = (IDInstruction)var3.getLast();
         if (var4.isJcond()) {
            BasicBlock var5 = this.cfg.getBlockAt((long)var4.getBranchTarget());
            Integer var6 = null;
            if (this.pC) {
               BasicBlock var7 = this.cfg.get(var2 + 1);
               IDInstruction var8;
               if (var7.size() == 1 && (var8 = (IDInstruction)var7.get(0)).isAssignToVar() && var8.getAssignSource() instanceof IDImm) {
                  var6 = var8.getAssignDestination().asVar().getId();
               }

               if (var6 == null || var5.getBase() != var7.getEndAddress() || var5.insize() != 2) {
                  continue;
               }
            }

            IDInstruction var17;
            if (var5.size() == 1 && (var17 = (IDInstruction)var5.get(0)).isJcond()) {
               IDExpression var18 = var17.getJcondCondition();
               Set var9 = var18.getVarIds();
               if (var9.size() == 1 && !var18.hasSideEffects(this.ctx, true)) {
                  int var10 = (Integer)var9.iterator().next();
                  if (var6 == null || var6 == var10) {
                     this.analyzeChains();
                     Long var11 = this.dfa.checkSingleSource(var4.getOffset(), var10);
                     if (var11 != null && var11 >= 0L) {
                        IDInstruction var12 = (IDInstruction)this.cfg.getInstructionAt(var11);
                        if (var12.isAssignToVar(var10) && var12.getAssignSource() instanceof IDImm var13) {
                           boolean var19;
                           try {
                              IDImm var15 = var18.evaluate(this.g, Maps.toMap(var10, var13));
                              var19 = (var15.toLong() & 1L) != 0L;
                           } catch (DexDecEvaluationException var16) {
                              continue;
                           }

                           int var20 = var19 ? var17.getBranchTarget() : (int)var5.getEndAddress();
                           this.cfg.reconnectEdge(var3, var5, this.cfg.getBlockAt((long)var20));
                           var4.setBranchTarget(var20);
                           this.cfg.invalidateDataFlowAnalysis();
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cleanGraph();
      }

      return var1;
   }
}
