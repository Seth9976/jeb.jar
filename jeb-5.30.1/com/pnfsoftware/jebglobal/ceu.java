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

public class ceu extends AbstractDOptimizer {
   private static boolean q = true;
   private static boolean RF = true;
   private static boolean xK = false;
   private static boolean Dw = true;

   public ceu() {
      this.addTag("slow");
   }

   @Override
   public int perform() {
      if (!q && !RF && !xK && !Dw) {
         return 0;
      } else {
         int var1 = 0;
         bwd var2 = new bwd(this.ctx);
         bwd.nI var3 = var2.RF();
         if (q) {
            int var4 = 0;
            this.analyzeChains();

            for (BasicBlock var6 : this.cfg) {
               Map var7 = var3.q(var6.getBase()).RF();
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

         if (RF) {
            cev var19 = new cev(this);

            for (BasicBlock var25 : this.cfg) {
               IDInstruction var28 = (IDInstruction)var25.getLast();
               if (var28.isJcond()) {
                  Set var31 = var3.q(var25.getBase()).gO();
                  if (!var31.isEmpty()) {
                     var19.RF = var31;
                     var28.visitInstructionPostOrder(var19, true);
                  }
               }
            }

            if (var19.q > 0) {
               var1 += var19.q;
               this.cfg.invalidateDataFlowAnalysis();
               DUtil.cleanGraph(this.ctx);
            }
         }

         if (xK) {
            int var20 = 0;

            for (BasicBlock var26 : this.cfg) {
               IDInstruction var29 = (IDInstruction)var26.getLast();
               if (var29.isJump()) {
                  Map var32 = var3.q(var26.getBase()).Dw();
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

         if (Dw) {
            cew var21 = new cew(this);

            for (BasicBlock var27 : this.cfg) {
               var21.RF = new HashSet(var3.q(var27.getBase()).xK());

               for (IDInstruction var33 : var27) {
                  var21.xK.clear();
                  var33.visitDepthPost(var21);
                  if (!var21.xK.isEmpty()) {
                     var21.RF.addAll(var21.xK);
                  }

                  IDVar var35 = var33.getDefinedVariable();
                  if (var35 != null) {
                     var21.RF.remove(var35.getId());
                  }
               }
            }

            if (var21.q > 0) {
               var1 += var21.q;
            }
         }

         return var1;
      }
   }
}
