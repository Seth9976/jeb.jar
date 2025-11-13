package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class bwo extends bwi {
   public bwo() {
      super(DOptimizerType.UNSAFE);
   }

   @Override
   public brn pC(IDExpression var1) {
      if (var1 instanceof IDOperation var2) {
         JavaOperatorType var3 = var2.getOperatorType();
         if (var3 != JavaOperatorType.EQ && var3 != JavaOperatorType.NE) {
            return null;
         } else if (var2.getOperand2() instanceof IDImm var5 && var5.isZero()) {
            IDExpression var6 = var2.getOperand1();
            if (var6 instanceof IDOperation var7) {
               JavaOperatorType var12 = var7.getOperatorType();
               if (var12 != JavaOperatorType.AND && var12 != JavaOperatorType.OR) {
                  return null;
               } else {
                  IDExpression var9 = var7.getOperand1();
                  if (var9 instanceof IDVar && var9.getType().isBoolean()) {
                     IDExpression var10 = var7.getOperand2();
                     if (var10 instanceof IDVar && var10.getType().isBoolean()) {
                        IDOperation var11 = this.g
                           .createOperation(
                              this.tf.getBoolean(), var12 == JavaOperatorType.AND ? JavaOperatorType.LOG_AND : JavaOperatorType.LOG_OR, var9, var10
                           );
                        if (var3 == JavaOperatorType.EQ) {
                           var11 = this.g.createOperation(this.tf.getBoolean(), JavaOperatorType.LOG_NOT, null, var11);
                        }

                        return brn.pC(var11, false);
                     } else {
                        return null;
                     }
                  } else {
                     return null;
                  }
               }
            } else if (var6.getType().isBoolean()) {
               Object var8 = var6;
               if (var3 == JavaOperatorType.EQ) {
                  var8 = this.g.createOperation(this.tf.getBoolean(), JavaOperatorType.LOG_NOT, null, var6);
               }

               return brn.pC((IDExpression)var8, false);
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
}
