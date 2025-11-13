package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class bxb extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size() - 1; var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         BasicBlock var4 = null;
         BasicBlock var5 = null;
         if (((IDInstruction)var3.getLast()).isSwitch()) {
            BasicBlock var6 = this.cfg.get(var2 + 1);
            if (var6.size() == 1) {
               if (((IDInstruction)var6.get(0)).isSwitch()) {
                  var5 = var3;
                  var4 = var6;
               } else if (((IDInstruction)var6.get(0)).isJump() && var6.insize() == 1) {
                  BasicBlock var7 = var6.getOutputBlock(0);
                  if (var7.size() == 1 && ((IDInstruction)var7.get(0)).isSwitch()) {
                     var5 = var6;
                     var4 = var7;
                  }
               }
            }
         }

         if (var4 != null) {
            IDInstruction var17 = (IDInstruction)var3.getLast();
            IDInstruction var18 = (IDInstruction)var4.getLast();
            if (var17.getSwitchData().isRegularSwitch() && var18.getSwitchData().isRegularSwitch()) {
               IDExpression var8 = var17.getSwitchExpression();
               if (var8 instanceof IDVar && var8.equals(var18.getSwitchExpression())) {
                  IDTryData var9 = this.ctx.getExceptionData();
                  if (var9.compareHandlers((int)var3.getAddress(), (int)var4.getAddress())) {
                     if (var4.insize() != 1) {
                        IDVar var10 = (IDVar)var8;
                        if (!this.pC(var4, var5, var10.getId())) {
                           continue;
                        }

                        Assert.a(var4.insize() == 1);
                     }

                     BasicBlock var19 = var4.getOutputBlock(0);
                     IDSwitchData var11 = var17.getSwitchData();
                     IDSwitchData var12 = var18.getSwitchData();

                     for (Integer var14 : var11.getCasesAsInt()) {
                        IDTarget var15 = var11.getTargetForCase(var14);
                        BasicBlock var16 = this.cfg.getBlockAt((long)var15.getOffset());
                        if (var16 == var19) {
                           var12.deleteCase(var14);
                        } else {
                           var12.addCase(var14, var15, true);
                        }
                     }

                     this.cfg.deleteOutEdges(var4);
                     this.cfg.addEdge(var4, var19);

                     for (IDTarget var21 : var12.getTargets(true)) {
                        this.cfg.addEdge(var4, this.cfg.getBlockAt((long)var21.getOffset()));
                     }

                     this.cfg.deleteOutEdges(var3);
                     this.cfg.addEdge(var3, this.cfg.getBlockAt(var3.getEndAddress()));
                     var17.transformToNop();
                     var1++;
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean pC(BasicBlock var1, BasicBlock var2, int var3) {
      brt var4 = new brt(this.ctx);
      brt.K var5 = var4.pC();
      HashMap var6 = new HashMap();
      Iterator var7 = var1.getInputBlocks().iterator();

      while (true) {
         if (!var7.hasNext()) {
            for (BasicBlock var14 : var6.keySet()) {
               int var15 = (Integer)var6.get(var14);
               IDInstruction var16 = (IDInstruction)var14.getLast();
               this.cfg.reconnectEdge(var14, var1, this.cfg.getBlockAt((long)var15));
               var16.setBranchTarget(var15);
            }

            this.cfg.invalidateDataFlowAnalysis();
            return true;
         }

         BasicBlock var8 = (BasicBlock)var7.next();
         if (var8 != var2) {
            Map var9 = var5.pC(var8.getBase()).kS();
            if (!var9.containsKey(var3) || !((IDInstruction)var8.getLast()).isJump()) {
               break;
            }

            Assert.a(var8.getOutputBlock(0) == var1);
            IDInstruction var10 = (IDInstruction)var1.get(0);

            Integer var11;
            try {
               var11 = var10.evaluate(var9);
            } catch (DexDecEvaluationException var12) {
               var11 = null;
            }

            if (var11 == null) {
               break;
            }

            var6.put(var8, var11);
         }
      }

      return false;
   }
}
