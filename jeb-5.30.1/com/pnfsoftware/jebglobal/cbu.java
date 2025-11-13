package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class cbu extends AbstractDOptimizer {
   private static final ILogger q = GlobalLog.getLogger(cbu.class, Integer.MAX_VALUE);

   public cbu() {
      this.getPluginInformation().setDescription(S.L("Control-flow clean-up"));
   }

   @Override
   public int perform() {
      int var1 = 0;

      int var2;
      for (var2 = 50; var2 > 0; var2--) {
         this.checkInterrupted();
         int var3 = this.q();
         if (var3 <= 0) {
            break;
         }

         var1 += var3;
      }

      if (var2 <= 0) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("spagh.rem. seems stuck"), this.ctx.getMethodSignature());
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   public int q() {
      int var1 = 0;
      var1 += this.RF();
      var1 += this.xK();
      return var1 + this.Dw();
   }

   public int RF() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg.getBlocks()) {
         IDInstruction var4 = (IDInstruction)var3.get(0);
         if (var4.isJump()) {
            IDInstruction var5 = var4;
            if (!Boolean.TRUE.equals(var4.getData("KEEP_INSTRUCTION"))) {
               if (var3.size() == 1 && var3.outsize() == 1 && var3.irrinsize() == 0 && var3.getOutputBlock(0) != var3) {
                  boolean var17 = false;

                  for (BasicBlock var19 : var3.getInputBlocks()) {
                     IDInstruction var20 = (IDInstruction)var19.getLast();
                     if (var20.isJump() && var19 != var3) {
                        var20.setBranchTarget(var5.getBranchTarget());
                        if (this.cfg.reconnectEdge(var19, var3, var3.getOutputBlock(0)) != 1) {
                           throw new RuntimeException("Edge reconnect failed");
                        }

                        var17 = DUtil.removeUnreachableTrampoline(this.cfg, var3);
                        var1++;
                     } else if (var20.isJcond() && var19.outsize() == 2) {
                        BasicBlock var21 = var19.getOutputBlock(0);
                        BasicBlock var22 = var3.getOutputBlock(0);
                        if (var21 == var3 && var22 == var19.getOutputBlock(1)) {
                           if (!var20.getJcondCondition().hasSideEffects(this.ctx, true)) {
                              var20.transformJcondToJump();
                              this.cfg.deleteEdge(var19, var3);
                              var17 = DUtil.removeUnreachableTrampoline(this.cfg, var3);
                              var1++;
                           }
                        } else if (var19.getOutputBlock(1) == var3 && var21 != var3) {
                           if (var22 == var21) {
                              if (!var20.getJcondCondition().hasSideEffects(this.ctx, true)) {
                                 var20.transformToNop();
                                 this.cfg.deleteEdge(var19, var3);
                                 var17 = DUtil.removeUnreachableTrampoline(this.cfg, var3);
                                 var1++;
                              }
                           } else if (var3 != var22) {
                              if (this.cfg.reconnectEdge(var19, var3, var22) != 1) {
                                 throw new RuntimeException("Edge reconnect failed");
                              }

                              var20.setBranchTarget(var5.getBranchTarget());
                              var17 = DUtil.removeUnreachableTrampoline(this.cfg, var3);
                              var1++;
                           }
                        } else if (var21 == var3
                           && var3.getEndAddress() == var19.getOutputBlock(1).getAddress()
                           && var22 != var19.getOutputBlock(1)
                           && var3.insize() == 1) {
                           BasicBlock var23 = var19.getOutputBlock(1);
                           var20.setBranchTarget(var5.getBranchTarget());
                           var5.setBranchTarget((int)var23.getBase());
                           var20.reverseJcondCondition();
                           if (this.cfg.reconnectEdge(var19, var23, var22) != 1 || this.cfg.reconnectEdge(var3, var22, var23) != 1) {
                              throw new RuntimeException("Edge reconnect failed");
                           }

                           var1++;
                        }
                     } else if (var20.isSwitch() && var20.getSwitchData().isRegularSwitch()) {
                        IDSwitchData var10 = var20.getSwitchData();
                        BasicBlock var11 = var19.getOutputBlock(0);
                        BasicBlock var12 = var3.getOutputBlock(0);
                        if (var11 == var3) {
                           if (var10.deleteCasesTo(var5.getBranchTarget()) >= 1) {
                              this.cfg.deleteEdges(var19, var12);
                              var1++;
                           }
                        } else if (var12 != var11) {
                           int var13 = this.cfg.reconnectEdges(var19, var3, var12);
                           if (var13 >= 1) {
                              this.cfg.removeDuplicateEdges(var19, var12);
                              var10.updateCases((int)var5.getOffset(), var5.getBranchTarget());
                              var17 = DUtil.removeUnreachableTrampoline(this.cfg, var3);
                              var1++;
                           }
                        } else if (var10.deleteCasesTo((int)var3.getBase()) >= 1) {
                           this.cfg.deleteEdges(var19, var3);
                           var17 = DUtil.removeUnreachableTrampoline(this.cfg, var3);
                           var1++;
                        }

                        if (var10.getCaseCount() == 1 && var19.outsize() != 1) {
                           if (var19.outsize() != 2) {
                              throw new RuntimeException();
                           }

                           var20.transformSwitchToJcond();
                        }
                     }

                     if (var17) {
                        break;
                     }
                  }
               } else if (var3.size() == 1 && var3.outsize() == 1 && var3.irrinsize() >= 1 && var3.getOutputBlock(0) != var3) {
                  int var6 = var4.getBranchTarget();
                  BasicBlock var7 = this.cfg.getBlockAt((long)var6);
                  if (var7.irrinsize() > 0) {
                     for (BasicBlock var9 : var3.getIrregularInputBlocks()) {
                        this.cfg.reconnectIrregularEdge(var9, var3, var7, 0);
                        this.ctx.getExceptionData().updateHandlerAddress((int)var3.getBase(), (int)var7.getBase());
                     }

                     var1++;
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cleanGraph();
      }

      for (BasicBlock var15 : this.cfg) {
         IDInstruction var16 = (IDInstruction)var15.getLast();
         if (var16.isJump() && !Boolean.TRUE.equals(var16.getData("KEEP_INSTRUCTION")) && var16.getBranchTarget() == var16.getOffsetEnd()) {
            if (var15.size() >= 2) {
               DUtil.removeInstruction(var15, var15.size() - 1);
               var1++;
            } else if (var15.size() == 1) {
               var16.transformToNop();
               var1++;
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   public int xK() {
      int var1 = 0;
      var1 += this.cfg.simplify();
      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   public int Dw() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         IDInstruction var4 = (IDInstruction)var3.getLast();
         if (var4.isJump()) {
            BasicBlock var5 = var3.getOutputBlock(0);
            if (var3 != var5) {
               Assert.a(var3.outsize() == 1);
               if (var5.insize() == 1
                  && var5.irrinsize() == 0
                  && this.ctx.getExceptionData().compareHandlers((int)var3.getBase(), (int)var5.getBase())
                  && (var5.outsize() == 0 || ((IDInstruction)var5.getLast()).isJump())) {
                  boolean var6 = var5.outsize() > 0 && !((IDInstruction)var5.getLast()).isJump();
                  int var7 = var5.size() + (var6 ? 1 : 0);
                  if (var4.getSize() != var7) {
                     DUtil.modifyInstructionSizes(this.ctx, var2x -> var2x == var4 ? var7 : null);
                  }

                  int var8 = (int)var5.getEndAddress();
                  int var9 = (int)var3.getLastAddress();
                  int var10 = var3.size() - 1;
                  var3.remove(var10);
                  this.cfg.deleteOutEdges(var3);
                  this.cfg.deleteIrregularOutEdges(var3);

                  for (IDInstruction var12 : var5) {
                     var3.add(var12.duplicateWithOffsetAndSize(var9, 1));
                     var9++;
                  }

                  if (var6) {
                     var3.add(this.ctx.createJump(var8).withOffset(var9));
                  }

                  for (BasicBlock var15 : var5.getOutputs()) {
                     this.cfg.addEdge(var3, var15);
                  }

                  for (BasicBlock var16 : var5.getIrregularOutputs()) {
                     this.cfg.addIrregularEdge(var3, var16, -1);
                  }

                  this.cleanGraph();
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
}
