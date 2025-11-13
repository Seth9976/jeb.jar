package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.HashSet;
import java.util.Set;

public class cbi extends AbstractDOptimizer {
   Set pC = new HashSet();

   public cbi() {
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      brg var1 = new brg(this.ctx);
      var1.pC(this.pC);
      var1.pC(true);
      cbi.Av var2 = new cbi.Av();
      var2.pC = var1;
      var2.A = false;

      for (BasicBlock var4 : this.cfg) {
         var2.kS = var4;
         int var5 = 0;

         for (IDInstruction var7 : var4) {
            var2.wS = var5;
            var7.visitInstruction(var2, true);
            var5++;
         }
      }

      int var8 = var2.UT;
      if (var8 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var8;
   }

   class Av implements IDVisitor {
      brg pC;
      boolean A;
      BasicBlock kS;
      int wS;
      int UT;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (!(var2 instanceof IDOperation var4 && !var4.getOperatorType().isLogical() && !var4.isCast())) {
            if (var1 instanceof IDOperation var7) {
               if (!var1.getType().isBoolean()) {
                  if (!var7.isCast()) {
                     String var5 = var1.toString();
                     if (cbi.this.pC == null || !cbi.this.pC.contains(var5)) {
                        IDExpression var6 = this.pC.pC(var1, this.kS, this.wS);
                        if (var6 == null) {
                           if (cbi.this.pC != null) {
                              cbi.this.pC.add(var5);
                           }
                        } else if (var2.replaceSubExpression(var1, var6)) {
                           var3.setReplacedNode(var6);
                           this.UT++;
                        }
                     }

                     if (this.A) {
                        var3.skipChildren();
                     }
                  }
               }
            }
         }
      }
   }
}
