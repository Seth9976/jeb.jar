package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class cfk extends AbstractDOptimizer {
   public cfk() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      if (!this.ctx.isSSA()) {
         return 0;
      } else {
         bup var1 = new bup(this.ctx);
         if (!var1.Uv()) {
            return 0;
         } else {
            Set var2 = var1.RF();
            Set var3 = var1.Dw();
            HashSet var4 = new HashSet();

            for (IDInstruction var6 : this.cfg.instructions()) {
               var6.visitInstruction(new cfl(this, var4, var3, var2));
            }

            this.analyzeChains();

            int var23;
            do {
               var23 = 0;

               for (IDInstruction var7 : this.cfg.instructions()) {
                  if (var7.isAssignToVar()) {
                     IDVar var8 = var7.getAssignDestination().asVar();
                     if (var4.contains(var8.getId())) {
                        Collection var9 = this.dfa.getInstructionUses(var7.getOffset());
                        if (var4.addAll(var9)) {
                           var23++;
                        }
                     }
                  }
               }
            } while (var23 > 0);

            HashSet var25 = new HashSet();

            for (IDInstruction var28 : this.cfg.instructions()) {
               Collection var30 = this.dfa.getInstructionUses(var28.getOffset());
               var25.addAll(var30);
            }

            if (var4.equals(var25)) {
               return 0;
            } else {
               Iterator var27 = this.cfg.iterator();

               BasicBlock var29;
               IDInstruction var31;
               BasicBlock var34;
               BasicBlock var36;
               label158:
               while (true) {
                  if (!var27.hasNext()) {
                     return 0;
                  }

                  var29 = (BasicBlock)var27.next();
                  if (var29.outsize() == 2 && var29.irroutsize() == 0) {
                     var31 = (IDInstruction)var29.getLast();
                     if (var31.isJcond() && var31.getJcondCondition() instanceof IDOperation var10) {
                        Set var32 = var10.getVarIds();
                        if (var32.size() == 1) {
                           int var12 = (Integer)var32.iterator().next();
                           if (!var4.contains(var12) && this.q(var10, var12)) {
                              bvm var13 = new bvm(this.cfg);
                              if (var13.q(var29, var12)
                                 && var13.xK().size() == 1
                                 && var13.Uv().size() == 1
                                 && (Long)var13.xK().iterator().next() == var29.getBase()) {
                                 Set var14 = var13.q();
                                 HashSet var15 = new HashSet();

                                 for (long var17 : var14) {
                                    for (IDInstruction var21 : this.cfg.getBlockAt(var17)) {
                                       if (var21.hasUseSideEffects(false)) {
                                          continue label158;
                                       }

                                       Collection var22 = this.dfa.getInstructionUses(var21.getOffset());
                                       if (CollectionUtil.hasIntersection(var22, var4)) {
                                          continue label158;
                                       }

                                       var15.addAll(var22);
                                    }
                                 }

                                 for (BasicBlock var35 : this.cfg) {
                                    if (!var14.contains(var35.getBase())) {
                                       for (IDInstruction var38 : var35) {
                                          Collection var40 = this.dfa.getInstructionUses(var38.getOffset());
                                          if (CollectionUtil.hasIntersection(var40, var4)) {
                                             continue label158;
                                          }
                                       }
                                    }
                                 }

                                 var36 = this.cfg.getBlockAt((Long)var13.Uv().iterator().next());
                                 BasicBlock var37 = var29.getOutputBlock(0);
                                 BasicBlock var39 = var29.getOutputBlock(1);
                                 if (var37 == var36) {
                                    var34 = var39;
                                    break;
                                 }

                                 if (var39 == var36) {
                                    var34 = var37;
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               this.cfg.deleteEdge(var29, var34);
               var31.transformJcondToJump();
               var31.setBranchTarget((int)var36.getBase());
               this.cleanGraph();
               this.cfg.invalidateDataFlowAnalysis();
               return 1;
            }
         }
      }
   }

   private boolean q(IDOperation var1, int var2) {
      JavaOperatorType var3 = var1.getOperatorType();
      if (var3 == JavaOperatorType.LT || var3 == JavaOperatorType.LE || var3 == JavaOperatorType.GT || var3 == JavaOperatorType.GE) {
         IDExpression var4 = var1.getOperand1();
         IDExpression var5 = var1.getOperand2();
         if (var4.isVar(var2) && var5 instanceof IDImm || var5.isVar(var2) && var4 instanceof IDImm) {
            return true;
         }
      }

      return false;
   }
}
