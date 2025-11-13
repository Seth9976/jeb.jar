package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

public class ccx extends AbstractDOptimizer {
   @Override
   public int perform() {
      ccx.eo var1 = new ccx.eo();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var1.q = var3;
         var3.visitDepthPre(var1);
      }

      return var1.RF;
   }

   private class eo implements IDVisitor {
      IDInstruction q;
      int RF;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1.getCustomCanThrow() != null) {
            var3.skipChildren();
         } else {
            if (var1 instanceof IDArrayElt var4 && var4.getIndex().isConstantImm()) {
               Boolean var5 = null;
               int var6 = (int)var4.getIndex().asImm().getRawValue();
               if (var6 < 0) {
                  var5 = true;
               } else {
                  Integer var7 = this.q(var4.getArray(), this.q.getOffset());
                  if (var7 != null) {
                     var5 = var6 >= var7;
                  }
               }

               if (var5 != null) {
                  var1.setCustomCanThrow(var5);
                  this.RF++;
                  var3.skipChildren();
               }
            }
         }
      }

      private Integer q(IDExpression var1, long var2) {
         if (var1 instanceof IDNewArrayInfo var4 && var4.getSize().isConstantImm()) {
            int var10 = (int)var4.getSize().asImm().getRawValue();
            return var10;
         } else {
            if (var1 instanceof IDVar var9) {
               ccx.this.analyzeChains();
               int var5 = var9.getId();
               Long var6 = ccx.this.dfa.checkSingleDef(var2, var5);
               if (var6 != null && var6 != -1L) {
                  IDInstruction var7 = (IDInstruction)ccx.this.cfg.getInstructionAt(var6);
                  if (var7.isAssignToVar(var5)) {
                     IDExpression var8 = var7.getAssignSource();
                     return this.q(var8, var6);
                  }
               }
            }

            return null;
         }
      }
   }
}
