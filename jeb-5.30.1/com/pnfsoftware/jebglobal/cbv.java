package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.Set;

public class cbv extends AbstractDOptimizer {
   private int q = 5;

   @Override
   public int perform() {
      int var1 = 0;
      boolean var2 = false;

      for (BasicBlock var4 : this.cfg) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            if (var6.isSwitchOnInt()) {
               IDExpression var7 = var6.getSwitchExpression();
               boolean var8 = var7.hasSideEffects(this.ctx, true);
               IDSwitchData var9 = var6.getSwitchData();
               int var10 = var9.getCaseCount();
               if (var10 == 0 && !var8) {
                  var6.transformToNop();
                  var1++;
               } else if (var10 == 1) {
                  if (var6.transformSwitchToJcond()) {
                     var1++;
                  }
               } else if (var10 <= this.q && var9.getTargetCount() == 1 && !var8) {
                  Set var20 = var9.getCasesAsInt();
                  Assert.a(var20.size() >= 1);
                  IDTarget var21 = null;
                  IDOperation var23 = null;
                  int var14 = 0;

                  for (int var16 : var20) {
                     IDExpression var17 = var14 == 0 ? var7 : var7.duplicate();
                     IDOperation var18 = this.g.createPredicate(var17, this.of.getStandardOperator(JavaOperatorType.EQ), this.g.createInt(var16));
                     if (var23 == null) {
                        var21 = var9.getTargetForCase(var16);
                        var23 = var18;
                     } else {
                        var23 = this.g.createPredicate(var23, this.of.getStandardOperator(JavaOperatorType.LOG_OR), var18);
                     }

                     var14++;
                  }

                  var6.morph(DOpcodeType.IR_JCOND, var21, var23);
                  var1++;
               } else if (var7 instanceof IDImm && !var8) {
                  int var11;
                  try {
                     var11 = (int)((IDImm)var7).toLong();
                  } catch (DexDecEvaluationException var19) {
                     continue;
                  }

                  IDTarget var12 = var9.getTargetForCase(var11);
                  if (var12 == null) {
                     long var13 = var4.getEndAddress();
                     this.cfg.deleteOutEdges(var4);
                     this.cfg.addEdge(var4, this.cfg.getBlockAt(var13));
                     var6.transformToNop();
                     var1++;
                     var2 = true;
                  } else {
                     long var22 = var12.getOffset();
                     this.cfg.deleteOutEdges(var4);
                     this.cfg.addEdge(var4, this.cfg.getBlockAt(var22));
                     var6.transformToJump(var12);
                     var1++;
                     var2 = true;
                  }
               }
            }
         }
      }

      if (var2) {
         this.cleanGraph();
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
