package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class cee extends AbstractDOptimizer {
   public cee() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 1; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() == 1 && ((IDInstruction)var3.get(0)).isJcond()) {
            IDInstruction var4 = (IDInstruction)var3.get(0);
            IDExpression var5 = var4.getJcondCondition();
            if (var5.isOperation(JavaOperatorType.LOG_IDENT, JavaOperatorType.LOG_NOT)) {
               var5 = var5.asOperation().getRight();
            }

            if (var5 instanceof IDCallInfo var6 && var6.getMethodSignature().equals("Ljava/util/Iterator;->hasNext()Z")) {
               IDExpression var7 = var6.getArgument(0);
               if (var7 instanceof IDVar && this.q(var2, var7.asVar())) {
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean q(int var1, IDVar var2) {
      BasicBlock var3 = this.cfg.getBlock(var1 - 1);
      IDVar var4 = null;
      int var5 = -1;

      for (int var6 = var3.size() - 1; var6 >= 0; var6--) {
         IDInstruction var7 = (IDInstruction)var3.get(var6);
         if (!var7.isAssign() || !var7.getAssignDestination().isVar()) {
            return false;
         }

         if (var7.getAssignDestination().equals(var2)
            && var7.getAssignSource() instanceof IDCallInfo var8
            && var8.getCountOfArguments() == 1
            && var8.getMethodName().equals("iterator")) {
            if (!(var8.getArgument(0) instanceof IDVar var15)) {
               return false;
            }

            var4 = var15;
            var5 = var6;
            break;
         }

         if (var7.hasUseSideEffects(true)) {
            return false;
         }
      }

      if (var5 >= 0 && var5 != var3.size() - 1) {
         for (int var11 = var5 + 1; var11 < var3.size(); var11++) {
            IDInstruction var13 = (IDInstruction)var3.get(var11);
            if (!q(var13, var2, var4)) {
               return false;
            }
         }

         IDInstruction var12 = (IDInstruction)var3.get(var5);
         long var14 = var12.getOffset();

         for (int var16 = var5; var16 < var3.size() - 1; var16++) {
            var3.set(var16, (IDInstruction)var3.get(var16 + 1), false, false);
         }

         var3.set(var3.size() - 1, var12, false, false);

         for (int var17 = var5; var17 < var3.size(); var17++) {
            IDInstruction var18 = (IDInstruction)var3.get(var17);
            var18.setOffset(var14);
            var14 += var18.getSize();
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean q(IDInstruction var0, IDVar var1, IDVar var2) {
      return var0.visitDepthPre(new cef(var1, var2));
   }
}
