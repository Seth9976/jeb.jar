package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class bsf {
   private IDMethodContext pC;

   public bsf(IDMethodContext var1) {
      this.pC = var1;
   }

   public Set pC(int var1) {
      CFG var2 = this.pC.getCfg();
      HashSet var3 = new HashSet();
      HashSet var4 = new HashSet();
      ArrayDeque var5 = new ArrayDeque();
      IDFA var6 = var2.doDataFlowAnalysis();

      for (IDInstruction var8 : var2.instructions()) {
         Collection var9 = var6.getInstructionUses(var8.getOffset());
         if (var9.contains(var1)) {
            var5.add(new bsj(var8.getOffset(), var1, true));
            break;
         }
      }

      while (!var5.isEmpty()) {
         bsj var16 = (bsj)var5.remove();
         if (var4.add(var16)) {
            int var17 = var16.wS();
            var3.add(var17);
            long var18 = var16.kS();
            if (var18 >= 0L) {
               IDInstruction var11 = (IDInstruction)var2.getInstructionAt(var18);
               if (var16.UT()) {
                  if (var11.isAssignToVar()) {
                     int var20 = var11.getAssignDestination().asVar().getId();
                     var5.add(new bsj(var18, var20, false));
                  }

                  for (long var23 : var6.getUseDefs(var18, var17)) {
                     var5.add(new bsj(var23, var17, false));
                  }
               } else {
                  if (var11.isAssignToVar()) {
                     IDExpression var12 = var11.getAssignSource();
                     HashSet var13 = new HashSet();
                     var12.collectVarIds(var13);

                     for (int var15 : var13) {
                        var5.add(new bsj(var18, var15, true));
                     }
                  }

                  for (long var22 : var6.getDefUses(var18, var17)) {
                     var5.add(new bsj(var22, var17, true));
                  }
               }
            }
         }
      }

      return var3;
   }
}
