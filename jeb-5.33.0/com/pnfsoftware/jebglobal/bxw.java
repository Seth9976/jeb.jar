package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Triple;
import java.util.ArrayList;
import java.util.HashSet;

public class bxw extends AbstractDOptimizer {
   @Override
   public int perform() {
      HashSet var1 = new HashSet();
      HashSet var2 = new HashSet(this.ctx.getParameterVariables());

      for (IDInstruction var4 : this.cfg.instructions()) {
         if (var4.isAssignToVar()) {
            IDVar var5 = var4.getAssignDestination().asVar();
            IJavaType var6 = var5.getType();
            if (var6 != null && var6.isSmallInt()) {
               IDExpression var7 = var4.getAssignSource();
               if (var7.isConstantImm()) {
                  int var8 = (int)var7.asImm().getRawValue();
                  if (var8 == 0 || var8 == 1) {
                     var1.add(var5);
                     continue;
                  }
               } else if (var7.isCastOperation(var6) && var7.asOperation().getOperand2().getType().isBoolean()) {
                  var1.add(var5);
                  continue;
               }
            }

            var2.add(var5);
         }
      }

      var1.removeAll(var2);
      if (var1.isEmpty()) {
         return 0;
      } else {
         IJavaType var14 = this.tf.getBoolean();
         int var15 = 0;

         label66:
         for (IDVar var17 : var1) {
            ArrayList var18 = new ArrayList();

            for (IDInstruction var9 : this.cfg.instructions()) {
               if (var9.isAssignToVar(var17.getId())) {
                  IDExpression var10 = var9.getAssignSource();
                  if (var10 instanceof IDImm var12) {
                     var18.add(new Triple(var9, var12, var12.duplicateWithDifferentType(var14)));
                  } else {
                     if (!(var10 instanceof IDOperation var11)) {
                        throw new RuntimeException();
                     }

                     IDExpression var13 = var11.getOperand2();
                     var18.add(new Triple(var9, var11, var13));
                  }
               } else if (!var9.visitDepthPost(new bxx(this, var17, var18, var14))) {
                  continue label66;
               }
            }

            for (Triple var21 : var18) {
               IDExpression var22 = (IDExpression)var21.getFirst();
               IDExpression var23 = (IDExpression)var21.getSecond();
               IDExpression var24 = (IDExpression)var21.getThird();
               var22.replaceSubExpression(var23, var24);
            }

            var17.setType(this.ctx.getTypeFactory().getBoolean(), null, true);
            var15++;
         }

         if (var15 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var15;
      }
   }
}
