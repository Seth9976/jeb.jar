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

public class cdz extends AbstractDOptimizer {
   public static final Set q = new ConcurrentHashSet();
   private int RF;

   public cdz() {
      this(1);
   }

   public cdz(int var1) {
      this.getPluginInformation().setDescription(S.L("Opaque predicate resolution"));
      this.addTag("slow");
      this.addTag("deobfuscator");
      bto.Dw(this);
      this.RF = var1;
   }

   public void q(int var1) {
      this.RF = var1;
   }

   public int q() {
      return this.RF;
   }

   @Override
   public int perform() {
      if (this.RF == 0) {
         return 0;
      } else if (!bvy.q()) {
         return 0;
      } else {
         bvy var1 = new bvy(this.ctx);
         var1.q(q);
         cdz.eo var2 = new cdz.eo(var1);

         for (IDInstruction var4 : this.cfg.instructions()) {
            var2.q = var4;
            var4.visitInstruction(var2);
         }

         return var2.xK;
      }
   }

   class eo implements IDVisitor {
      IDInstruction q;
      bvy RF;
      int xK;

      eo(bvy var2) {
         this.RF = var2;
      }

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1.checkType(cdz.this.tf.getBoolean())) {
            IDImm var4 = this.RF.q(var1, this.q);
            if (var4 != null && var2.replaceSubExpression(var1, var4)) {
               var3.setReplacedNode(var4);
               cdz.this.cfg.invalidateDataFlowAnalysis();
               this.xK++;
            }
         }
      }
   }
}
