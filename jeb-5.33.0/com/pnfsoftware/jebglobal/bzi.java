package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import java.util.Set;

public class bzi extends AbstractDOptimizer {
   public static final Set pC = new ConcurrentHashSet();
   private int A;

   public bzi() {
      this(1);
   }

   public bzi(int var1) {
      this.getPluginInformation().setDescription(S.L("Opaque predicate resolution"));
      this.addTag("slow");
      this.addTag("deobfuscator");
      bpl.kS(this);
      this.A = var1;
   }

   public void pC(int var1) {
      this.A = var1;
   }

   @Override
   public int perform() {
      if (this.A == 0) {
         return 0;
      } else if (!bro.pC()) {
         return 0;
      } else {
         bro var1 = new bro(this.ctx);
         var1.pC(pC);
         bzi.Av var2 = new bzi.Av(var1);

         for (IDInstruction var4 : this.cfg.instructions()) {
            var2.pC = var4;
            var4.visitInstruction(var2);
         }

         return var2.kS;
      }
   }

   class Av implements IDVisitor {
      IDInstruction pC;
      bro A;
      int kS;

      Av(bro var2) {
         this.A = var2;
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1.checkType(bzi.this.tf.getBoolean())) {
            IDImm var4 = this.A.pC(var1, this.pC);
            if (var4 != null && var2.replaceSubExpression(var1, var4)) {
               var3.setReplacedNode(var4);
               bzi.this.cfg.invalidateDataFlowAnalysis();
               this.kS++;
            }
         }
      }
   }
}
