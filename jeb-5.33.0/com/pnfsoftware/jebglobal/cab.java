package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class cab extends AbstractDOptimizer {
   private static boolean pC = true;
   private static boolean A = true;
   private static boolean kS = false;
   private static boolean wS = true;

   public cab() {
      this.addTag("slow");
   }

   @Override
   public int perform() {
      if (!pC && !A && !kS && !wS) {
         return 0;
      } else {
         int var1 = 0;
         brt var2 = new brt(this.ctx);
         brt.K var3 = var2.pC();
         if (pC) {
            int var4 = 0;
            this.analyzeChains();

            for (BasicBlock var6 : this.cfg) {
               Map var7 = var3.pC(var6.getBase()).pC();
               if (!var7.isEmpty()) {
                  HashSet var8 = new HashSet(var7.keySet());
                  long var9 = var6.getBase();

                  for (int var11 = 0; var11 < var6.size(); var11++) {
                     IDInstruction var12 = (IDInstruction)var6.get(var11);

                     for (Integer var15 : this.dfa.getInstructionUses(var9)) {
                        if (var8.contains(var15)) {
                           IDVar var16 = this.ctx.getVar(var15);
                           if (var16 != null) {
                              IDImm var17 = (IDImm)var7.get(var15);
                              var4 += var12.replaceUsedVariable(var16, var17);
                           }
                        }
                     }

                     Collection var39 = this.dfa.getInstructionAllDefs(var9);
                     var8.removeAll(var39);
                     var9 += var12.getSize();
                  }
               }
            }

            if (var4 > 0) {
               var1 += var4;
               this.cfg.invalidateDataFlowAnalysis();
            }
         }

         if (A) {
            cac var19 = new cac(this);

            for (BasicBlock var25 : this.cfg) {
               IDInstruction var28 = (IDInstruction)var25.getLast();
               if (var28.isJcond()) {
                  Set var31 = var3.pC(var25.getBase()).UT();
                  if (!var31.isEmpty()) {
                     var19.A = var31;
                     var28.visitInstructionPostOrder(var19, true);
                  }
               }
            }

            if (var19.pC > 0) {
               var1 += var19.pC;
               this.cfg.invalidateDataFlowAnalysis();
               DUtil.cleanGraph(this.ctx);
            }
         }

         if (kS) {
            int var20 = 0;

            for (BasicBlock var26 : this.cfg) {
               IDInstruction var29 = (IDInstruction)var26.getLast();
               if (var29.isJump()) {
                  Map var32 = var3.pC(var26.getBase()).kS();
                  if (!var32.isEmpty()) {
                     HashSet var34 = new HashSet(var32.keySet());
                     BasicBlock var10 = var26.getOutputBlock(0);
                     if (((IDInstruction)var10.get(0)).isJcondOrSwitch()) {
                        IDInstruction var36 = (IDInstruction)var10.get(0);
                        if (!var36.hasUseSideEffects(false)) {
                           Set var37 = DUtil.collectUniqueVarIds(var36);
                           if (var34.containsAll(var37)) {
                              Integer var38;
                              try {
                                 var38 = var36.evaluate(var32);
                              } catch (DexDecEvaluationException var18) {
                                 continue;
                              }

                              if (var38 != null) {
                                 this.cfg.reconnectEdge(var26, var10, this.cfg.getBlockAt((long)var38.intValue()));
                                 var29.setBranchTarget(var38);
                                 var20++;
                              }
                           }
                        }
                     }
                  }
               }
            }

            if (var20 > 0) {
               var1 += var20;
               this.cfg.invalidateDataFlowAnalysis();
               DUtil.cleanGraph(this.ctx);
            }
         }

         if (wS) {
            cad var21 = new cad(this);

            for (BasicBlock var27 : this.cfg) {
               var21.A = new HashSet(var3.pC(var27.getBase()).A());

               for (IDInstruction var33 : var27) {
                  var21.kS.clear();
                  var33.visitDepthPost(var21);
                  if (!var21.kS.isEmpty()) {
                     var21.A.addAll(var21.kS);
                  }

                  IDVar var35 = var33.getDefinedVariable();
                  if (var35 != null) {
                     var21.A.remove(var35.getId());
                  }
               }
            }

            if (var21.pC > 0) {
               var1 += var21.pC;
            }
         }

         return var1;
      }
   }
}
