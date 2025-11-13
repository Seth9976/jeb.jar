package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.Collection;
import java.util.Iterator;

public class bzu extends AbstractDOptimizer {
   private static long pC = -1L;
   private boolean A;

   public bzu(boolean var1) {
      this.getPluginInformation().setDescription(S.L("Constants propagation"));
      this.A = var1;
   }

   public bzu() {
      this(false);
   }

   @Override
   public int perform() {
      long var1 = System.currentTimeMillis();
      int var3 = 0;
      this.analyzeChains();

      for (BasicBlock var5 : this.cfg) {
         int var6 = 0;

         label88:
         while (var6 < var5.size()) {
            IDInstruction var7 = (IDInstruction)var5.get(var6);
            if (var7.isAssignToVar() && var7.getAssignSource() instanceof IDImm var8) {
               IDVar var23 = var7.getAssignDestination().asVar();
               long var10 = var7.getOffset();
               int var12 = var23.getId();
               Collection var13 = this.dfa.getDefUses(var10, var12);
               int var14 = 0;
               Iterator var15 = var13.iterator();

               while (true) {
                  long var16;
                  Collection var18;
                  label83:
                  while (true) {
                     if (!var15.hasNext()) {
                        if (var14 > 0) {
                           var3++;
                        }

                        var6++;
                        continue label88;
                     }

                     var16 = (Long)var15.next();
                     if (pC >= 0L && var3 == 0 && var14 == 0 && System.currentTimeMillis() - var1 >= pC) {
                        return 0;
                     }

                     var18 = this.dfa.getUseDefs(var16, var12);
                     if (var18.size() == 1 && (Long)var18.iterator().next() == var10) {
                        break;
                     }

                     if (var18.size() >= 2) {
                        Iterator var19 = var18.iterator();

                        while (true) {
                           if (!var19.hasNext()) {
                              break label83;
                           }

                           long var20 = (Long)var19.next();
                           IDInstruction var22 = (IDInstruction)this.cfg.getInstruction(var20);
                           if (var22 == null || !var22.isAssignToVar(var12) || !var22.getAssignSource().equalsEx(var8, false)) {
                              break;
                           }
                        }
                     }
                  }

                  IDInstruction var24 = (IDInstruction)this.cfg.getInstruction(var16);
                  if (this.A) {
                     var24 = var24.duplicate();
                     Couple var25 = this.cfg.getInstructionLocation(var16);
                     ((BasicBlock)var25.getFirst()).set((Integer)var25.getSecond(), var24);
                  }

                  IDImm var26 = var8.duplicateWithDifferentType(var23.getType());
                  int var21 = var24.replaceUsedVariable(var23, var26);
                  if (var21 != 0) {
                     this.dfa.invalidatePostSimpleSubstitutionWithMultiDefs(var18, var16, var12);
                     var14 += var21;
                  }
               }
            } else {
               var6++;
            }
         }
      }

      if (var3 > 0) {
         this.invalidateChains();
      }

      return var3;
   }
}
