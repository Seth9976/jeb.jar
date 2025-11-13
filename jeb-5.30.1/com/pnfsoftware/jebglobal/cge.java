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

public class cge extends AbstractDOptimizer {
   Set q = new HashSet();

   public cge() {
      this.addTag("deobfuscator");
      bto.Dw(this);
   }

   @Override
   public int perform() {
      bvp var1 = new bvp(this.ctx);
      var1.q(this.q);
      var1.q(true);
      cge.eo var2 = new cge.eo();
      var2.q = var1;
      var2.RF = false;

      for (BasicBlock var4 : this.cfg) {
         var2.xK = var4;
         int var5 = 0;

         for (IDInstruction var7 : var4) {
            var2.Dw = var5;
            var7.visitInstruction(var2, true);
            var5++;
         }
      }

      int var8 = var2.Uv;
      if (var8 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var8;
   }

   class eo implements IDVisitor {
      bvp q;
      boolean RF;
      BasicBlock xK;
      int Dw;
      int Uv;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (!(var2 instanceof IDOperation var4 && !var4.getOperatorType().isLogical() && !var4.isCast())) {
            if (var1 instanceof IDOperation var7) {
               if (!var1.getType().isBoolean()) {
                  if (!var7.isCast()) {
                     String var5 = var1.toString();
                     if (cge.this.q == null || !cge.this.q.contains(var5)) {
                        IDExpression var6 = this.q.q(var1, this.xK, this.Dw);
                        if (var6 == null) {
                           if (cge.this.q != null) {
                              cge.this.q.add(var5);
                           }
                        } else if (var2.replaceSubExpression(var1, var6)) {
                           var3.setReplacedNode(var6);
                           this.Uv++;
                        }
                     }

                     if (this.RF) {
                        var3.skipChildren();
                     }
                  }
               }
            }
         }
      }
   }
}
