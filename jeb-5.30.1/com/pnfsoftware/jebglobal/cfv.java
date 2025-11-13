package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class cfv extends AbstractDOptimizer {
   public cfv() {
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      int var1 = 0;
      ArrayList var2 = new ArrayList();

      for (BasicBlock var4 : this.cfg) {
         label71:
         for (IDInstruction var6 : var4) {
            var2.clear();
            IDVar var7 = this.q(var6, var2);
            if (var7 != null) {
               int var8 = var7.getId();
               this.analyzeChains(false);
               Collection var9 = this.dfa.getDefUses(var6.getOffset(), var8);

               for (long var11 : var9) {
                  if (!this.dfa.checkSingleDef(var11, var8, var6.getOffset())) {
                     continue label71;
                  }
               }

               for (long var18 : var9) {
                  IDInstruction var13 = (IDInstruction)this.cfg.getInstruction(var18);
                  if (var13.isAssign() && var13.getAssignDestination() instanceof IDArrayElt var14 && var14.getArray().equals(var7) || !this.q(var13, var7)) {
                     continue label71;
                  }
               }

               cfx var17 = new cfx(this, var7, var2);

               for (long var12 : var9) {
                  IDInstruction var20 = (IDInstruction)this.cfg.getInstruction(var12);
                  var20.visitDepthPost(var17);
               }

               if (var17.q > 0) {
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

   boolean q(IDInstruction var1, IDVar var2) {
      return var1.visitDepthPost(new cfw(this, var2));
   }

   IDVar q(IDInstruction var1, List var2) {
      if (var1.isAssign()
         && var1.getAssignDestination() instanceof IDVar var3
         && var1.getAssignSource() instanceof IDNewArrayInfo var9
         && var9.getType().getSignature().equals("[I")) {
         for (IDExpression var6 : var9.getInitialValues()) {
            if (!(var6 instanceof IDImm var7)) {
               return null;
            }

            int var8 = (int)var7.getRawValue();
            var2.add(var8);
         }

         return var3;
      } else {
         return null;
      }
   }
}
