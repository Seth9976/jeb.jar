package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class ccg extends AbstractDOptimizer {
   public ccg() {
      this.setPriority(-100.0);
   }

   @Override
   public int perform() {
      ccg.Av var1 = new ccg.Av();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstruction(var1);
      }

      return var1.pC;
   }

   class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo var4) {
            if (var2 != null && !(var2 instanceof IDInstruction var5 && var5.isInvoke())) {
               String var9 = var4.getMethodSignature();
               if (var9.equals("Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;")) {
                  IDExpression var6 = var4.getArgument(0);
                  IDExpression var7 = var4.getArgument(1);
                  IDOperation var8 = ccg.this.g.createOperation(ccg.this.tf.getJavaLangString(), JavaOperatorType.CONCAT, var6, var7);
                  if (var2.replaceSubExpression(var1, var8)) {
                     var3.setReplacedNode(var8);
                     this.pC++;
                  }
               }
            }
         }
      }
   }
}
