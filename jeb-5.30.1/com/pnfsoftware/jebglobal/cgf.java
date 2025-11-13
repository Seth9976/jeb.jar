package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class cgf extends AbstractDOptimizer {
   public cgf() {
      this.setPriority(-100.0);
   }

   @Override
   public int perform() {
      cgf.eo var1 = new cgf.eo();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstruction(var1);
      }

      return var1.q;
   }

   class eo implements IDVisitor {
      int q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1.isOperation(JavaOperatorType.CONCAT)) {
            IDOperation var4 = var1.asOperation();
            IDExpression var5 = var4.getOperand1();
            IDExpression var6 = var4.getOperand2();
            if (var5 instanceof IDCallInfo var7
               && var6.getType().isJavaLangString()
               && var7.getInvokeType() == DInvokeType.STATIC
               && var7.getMethodSignature().startsWith("Ljava/lang/String;->valueOf(")
               && var7.getMethodSignature().endsWith(")Ljava/lang/String;")) {
               var5 = var7.getArgument(0);
               var4.setOperand1(var5);
               this.q++;
            }

            if (var6 instanceof IDCallInfo var9
               && var5.getType().isJavaLangString()
               && var9.getInvokeType() == DInvokeType.STATIC
               && var9.getMethodSignature().startsWith("Ljava/lang/String;->valueOf(")
               && var9.getMethodSignature().endsWith(")Ljava/lang/String;")) {
               var6 = var9.getArgument(0);
               var4.setOperand2(var6);
               this.q++;
            }
         }
      }
   }
}
