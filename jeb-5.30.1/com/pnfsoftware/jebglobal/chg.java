package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class chg extends AbstractDOptimizer {
   @Override
   public int perform() {
      this.cfg.getInstructionCount();
      List var1 = this.ctx.getParameterVariables();
      if (var1.isEmpty()) {
         return 0;
      } else {
         HashSet var2 = new HashSet();
         HashSet var3 = new HashSet();
         HashMap var4 = new HashMap();

         for (IDInstruction var6 : this.cfg.instructions()) {
            if (var6.isAssignToVar()) {
               IDVar var7 = var6.getAssignDestination().asVar();
               if (!var2.contains(var7)) {
                  if (var1.contains(var7)) {
                     var2.add(var7);
                     var3.add(var7);
                  } else if (var6.getAssignSource() instanceof IDVar var8) {
                     if (!var1.contains(var8)) {
                        var2.add(var7);
                     } else {
                        IDVar var16 = (IDVar)var4.put(var7, var8);
                        if (var16 != null && var16 != var8) {
                           var4.remove(var7);
                           var2.add(var7);
                        }
                     }
                  } else {
                     var2.add(var7);
                  }
               }
            }
         }

         int var12 = 0;

         label75:
         for (IDVar var14 : var4.keySet()) {
            IDVar var15 = (IDVar)var4.get(var14);
            if (!var3.contains(var15)) {
               for (IDInstruction var10 : this.cfg.instructions()) {
                  if (var10.isAssignToVar(var14.getId()) && var10.getAssignSource() != var15) {
                     continue label75;
                  }
               }

               int var18 = 0;

               for (IDInstruction var11 : this.cfg.instructions()) {
                  var18 += var11.replaceVariable(var14, var15);
               }

               if (var18 > 0) {
                  var12++;
               }
            }
         }

         if (var12 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var12;
      }
   }
}
