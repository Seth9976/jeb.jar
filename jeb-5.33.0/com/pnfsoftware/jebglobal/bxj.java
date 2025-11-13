package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.Collection;
import java.util.HashSet;

public class bxj extends AbstractDOptimizer {
   public bxj() {
      this.addTag("slow");
      this.setPriority(-10.0);
   }

   @Override
   public int perform() {
      int var1 = 0;
      HashSet var2 = new HashSet();
      HashSet var3 = new HashSet();

      for (BasicBlock var5 : this.cfg) {
         for (int var6 = 0; var6 < var5.size(); var6++) {
            IDInstruction var7 = (IDInstruction)var5.get(var6);
            if (var7.isAssignToVar() && var7.getAssignSource() instanceof IDImm var8) {
               IDVar var10 = (IDVar)var7.getAssignDestination();
               if (!var3.add(new Couple(var10.getId(), var8)) && var2.add(var5.getAddressOfInstruction(var6)) && this.pC(var5, var6, var10.getId(), var8)) {
                  var1++;
               }
            }
         }
      }

      return var1;
   }

   boolean pC(BasicBlock var1, int var2, int var3, IDImm var4) {
      this.analyzeChains();
      Collection var5 = this.dfa.getReachChains(var1, var2, var3, 3);
      if (!var5.isEmpty() && var5.size() < 3) {
         for (long var7 : var5) {
            if (var7 < 0L) {
               return false;
            }

            IDInstruction var9 = (IDInstruction)this.cfg.getInstruction(var7);
            if (!var9.isAssignToVar(var3) || !var9.getAssignSource().equals(var4)) {
               return false;
            }
         }

         IDInstruction var10 = (IDInstruction)var1.get(var2);
         var10.transformToNop();
         this.cfg.invalidateDataFlowAnalysis(var10.getOffset());
         return true;
      } else {
         return false;
      }
   }
}
