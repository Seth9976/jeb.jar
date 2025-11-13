package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Collection;

public class bzs extends AbstractDOptimizer {
   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1 != null && !var1.isEmpty()) {
         int var2 = 0;

         for (int var3 = 0; var3 < this.cfg.size(); var3++) {
            BasicBlock var4 = this.cfg.get(var3);
            if (bzp.Av.pC(var4) && this.pC(var4)) {
               var2++;
            }
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
            this.cleanGraph();
         }

         return var2;
      } else {
         return 0;
      }
   }

   boolean pC(BasicBlock var1) {
      bzp.Av var2 = new bzp.Av(var1);
      if (!var2.pC()) {
         return false;
      } else {
         int var3 = var2.kS.getId();
         BasicBlock var4 = var2.pC;
         this.analyzeChains();
         Collection var5 = this.dfa.getUseDefs(var2.wS, var3);
         if (var5.size() != 2) {
            return false;
         } else {
            ArrayList var6 = new ArrayList(var5);
            var6.sort(null);
            long var7 = (Long)var6.get(0);
            long var9 = (Long)var6.get(1);
            IDInstruction var11 = (IDInstruction)this.cfg.getInstruction(var7);
            if (var11 != null && var11.getOffsetEnd() == var9) {
               if (var11.isAssignToVar(var3) && var11.getAssignSource() instanceof IDImm var12 && var12.isZero()) {
                  IDInstruction var17 = (IDInstruction)this.cfg.getInstruction(var9);
                  Couple var14 = this.cfg.getInstructionLocation(var7);
                  BasicBlock var15 = (BasicBlock)var14.getFirst();
                  int var16 = (Integer)var14.getSecond();
                  if (var16 + 1 >= var15.size() || var15.get(var16 + 1) != var17) {
                     return false;
                  } else if (!bpl.pC(this.ctx, var15, var4)) {
                     return false;
                  } else if (var15.irroutsize() != 1) {
                     return false;
                  } else {
                     if (var16 + 2 < var15.size()) {
                        DUtil.splitBlock(this.ctx, var15, var16 + 2);
                     }

                     this.ctx.getExceptionData().unprotectBlock((int)var15.getBase(), (int)var4.getBase());
                     this.cfg.deleteIrregularEdge(var15, var4);
                     bpl.pC(var4, var15);
                     return true;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }
   }
}
