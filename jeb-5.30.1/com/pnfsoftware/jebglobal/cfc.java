package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class cfc extends AbstractDOptimizer {
   private int q = 0;

   public cfc() {
      this.addTag("reorderer");
   }

   @Override
   public int perform() {
      if (this.q >= 1) {
         return 0;
      } else {
         this.q++;
         if (bto.q(this.ctx)) {
            this.assignLocalFields(this.ctx);
         }

         return this.q();
      }
   }

   int q() {
      int var1 = 0;
      int var2 = 0;
      btb var3 = new btb(this.ctx);
      boolean var5 = false;
      int var6 = 100000;

      while (true) {
         boolean var4 = false;

         while (var2++ <= var6) {
            btc var7 = var3.nf();
            if (var7 == null) {
               break;
            }

            if (var7.q() != var7.RF()) {
               boolean var8 = false;
               if (!var8) {
                  var8 = this.q(var7, var3);
               }

               if (!var8) {
                  var8 = this.RF(var7, var3);
               }

               if (!var8) {
                  var8 = this.xK(var7, var3);
               }

               if (!var8) {
                  var8 = this.Uv(var7, var3);
               }

               if (!var8) {
                  var8 = this.gO(var7, var3);
               }

               if (var8) {
                  var4 = true;
                  this.cfg.invalidateDataFlowAnalysis();
                  var3.xK();
                  var1++;
               }
            }
         }

         if (var2 > var6) {
            RuntimeException var10 = new RuntimeException("RefL optimizer does not seem to terminate");
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var10, this.ctx.getMethodSignature());
            break;
         }

         if (var5 && !var4) {
            break;
         }

         boolean var9 = this.q(var3);
         if (!var9) {
            break;
         }

         var5 = true;
         this.cfg.invalidateDataFlowAnalysis();
         var3.xK();
         var1++;
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean q(btc var1, btb var2) {
      if (var1.Dw() != btd.q) {
         return false;
      } else {
         int var3 = var1.q();
         int var4 = var1.RF();
         Assert.a(var3 != var4);
         BasicBlock var5 = this.cfg.get(var3);
         IDInstruction var6 = (IDInstruction)var5.getLast();
         BasicBlock var7 = this.cfg.get(var4);
         IDInstruction var8 = (IDInstruction)var7.getLast();
         if (var8.isJcond()) {
            int var9 = var4;

            for (BasicBlock var11 : var7.getInputs()) {
               int var12 = var2.q(var11.getBase());
               if (var12 > var9) {
                  var9 = var12;
               }
            }

            if (var9 > var4) {
               BasicBlock var19 = this.cfg.get(var9);
               IDInstruction var22 = (IDInstruction)var19.getLast();
               if (var22.isJump()) {
                  Assert.a(var22.getBranchTarget() == var7.getBase());
                  DUtil.modifyInstructionSize(this.ctx, var22, 2);
                  var22.adjustSize(-1);
                  int var28 = (int)var5.getBase();
                  IDInstruction var32 = this.ctx.createJump(var28).withOffset(var22.getOffsetEnd());
                  var32.setData("KEEP_INSTRUCTION", true);
                  BasicBlock var36 = new BasicBlock();
                  var36.add(var32);
                  this.cfg.addBlock(var9 + 1, var36);
                  this.cfg.reconnectEdge(var7, var5, var36);
                  ((IDInstruction)var7.getLast()).setBranchTarget((int)var36.getBase());
                  this.cfg.addEdge(var36, var5);
                  Object[] var42 = new Object[0];
                  return true;
               }
            }
         }

         if (var8.isJump()) {
            int var17 = var6.getBranchTarget();
            if (var17 > var7.getEndAddress()) {
               BasicBlock var20 = this.cfg.getBlockAt((long)var17);
               BasicBlock var23 = this.cfg.get(var4 + 1);
               if (var23.irrinsize() == 0) {
                  boolean var25 = true;

                  for (BasicBlock var14 : var23.getInputs()) {
                     int var15 = var2.q(var14.getBase());
                     if (var15 < var3 || var15 > var4) {
                        var25 = false;
                        break;
                     }
                  }

                  if (!var25) {
                     DUtil.modifyInstructionSize(this.ctx, var8, 2);
                     var8.adjustSize(-1);
                     int var29 = (int)((IDInstruction)var20.get(0)).getOffset();
                     IDInstruction var33 = this.ctx.createJump(var29).withOffset(var8.getOffsetEnd());
                     var33.setData("KEEP_INSTRUCTION", true);
                     BasicBlock var37 = new BasicBlock();
                     var37.add(var33);
                     this.cfg.addBlock(var4 + 1, var37);
                     var6.setBranchTarget((int)var33.getOffset());
                     this.cfg.reconnectEdge(var5, var20, var37);
                     this.cfg.addEdge(var37, var20);
                     Object[] var10000 = new Object[0];
                     return true;
                  }
               } else {
                  boolean var26 = true;

                  for (BasicBlock var34 : var23.getIrregularInputs()) {
                     int var38 = var2.q(var34.getBase());
                     if (var38 < var3 || var38 > var4) {
                        var26 = false;
                        break;
                     }
                  }

                  if (var26) {
                     BasicBlock var31 = this.cfg.getBlockEndingAt((long)var17);
                     IDInstruction var35 = (IDInstruction)var31.getLast();
                     if (var35.isOpcode(DOpcodeType.IR_RETURN, DOpcodeType.IR_THROW)) {
                        DUtil.modifyInstructionSize(this.ctx, var35, 2);
                        var35.adjustSize(-1);
                        IDInstruction var39 = this.ctx.createJump((int)var5.getBase()).withOffset(var35.getOffsetEnd());
                        var39.setData("KEEP_INSTRUCTION", true);
                        BasicBlock var16 = new BasicBlock();
                        var16.add(var39);
                        this.cfg.addBlock(this.cfg.indexOf(var31) + 1, var16);
                        var8.setBranchTarget((int)var39.getOffset());
                        var8.setData("KEEP_INSTRUCTION", true);
                        this.cfg.reconnectEdge(var7, var5, var16);
                        this.cfg.addEdge(var16, var5);
                        Object[] var41 = new Object[0];
                        return true;
                     }
                  }
               }
            }
         }

         if (var8.isJump() && var6.isJcond()) {
            int var18 = var6.getBranchTarget();
            if (var18 < var5.getBase()) {
               BasicBlock var21 = this.cfg.getBlockAt((long)var18);
               DUtil.modifyInstructionSize(this.ctx, var8, 2);
               var8.adjustSize(-1);
               IDInstruction var24 = this.ctx.createJump((int)var21.getBase()).withOffset(var8.getOffsetEnd());
               var24.setData("KEEP_INSTRUCTION", true);
               BasicBlock var27 = new BasicBlock();
               var27.add(var24);
               this.cfg.addBlock(var4 + 1, var27);
               this.cfg.reconnectEdge(var5, var21, var27);
               var6.setBranchTarget((int)var27.getBase());
               this.cfg.addEdge(var27, var21);
               Object[] var40 = new Object[0];
               return true;
            }
         }

         return false;
      }
   }

   private boolean RF(btc var1, btb var2) {
      int var3 = var1.q();
      int var4 = var1.RF();
      if (var3 != var4 && var4 != this.cfg.size() - 1) {
         BasicBlock var5 = this.cfg.get(var3);
         BasicBlock var6 = this.cfg.get(var4);
         IDInstruction var7 = (IDInstruction)var6.getLast();
         if (var7.isJcond() && var7.getBranchTarget() == var5.getBase()) {
            int var8 = var4 + 1;
            BasicBlock var9 = this.cfg.get(var8);
            BasicBlock var10 = null;

            for (int var11 = var3; var11 < var4; var11++) {
               BasicBlock var12 = this.cfg.get(var11);

               for (BasicBlock var14 : var12.getOutputs()) {
                  if (var14.getBase() > var9.getBase()) {
                     if (var10 == null) {
                        var10 = var14;
                     } else if (var10 != var14) {
                        return false;
                     }
                  }
               }
            }

            if (var10 == null) {
               return false;
            } else {
               int var21 = var2.q(var10.getBase());
               int var22 = var21 - 1;
               BasicBlock var23 = this.cfg.get(var22);
               IDInstruction var24 = (IDInstruction)var23.getLast();
               if (var24.isJcondOrSwitch()) {
                  return false;
               } else {
                  for (int var15 = var3; var15 <= var22; var15++) {
                     BasicBlock var16 = this.cfg.get(var15);

                     for (BasicBlock var18 : var16.getOutputs()) {
                        if (var18.getBase() > var10.getBase()) {
                           return false;
                        }
                     }
                  }

                  for (int var25 = var3 + 1; var25 <= var22; var25++) {
                     BasicBlock var27 = this.cfg.get(var25);

                     for (BasicBlock var31 : var27.getInputs()) {
                        if (var31.getBase() < var5.getBase() || var31.getBase() >= var10.getBase()) {
                           return false;
                        }
                     }
                  }

                  DUtil.modifyInstructionSize(this.ctx, var24, 2);
                  var24.adjustSize(-1);
                  int var26 = (int)var5.getBase();
                  IDImm var28 = this.ctx.getGlobalContext().createBoolean(true);
                  IDInstruction var30 = this.ctx.createJcond(var26, var28).withOffset(var24.getOffsetEnd());
                  var30.setData("KEEP_INSTRUCTION", true);
                  BasicBlock var32 = new BasicBlock();
                  var32.add(var30);
                  this.cfg.addBlock(var21, var32);
                  this.cfg.reconnectEdge(var6, var5, var32);
                  ((IDInstruction)var6.getLast()).setBranchTarget((int)var32.getBase());
                  this.cfg.addEdge(var32, var10);
                  this.cfg.addEdge(var32, var5);
                  if (!var24.isReturnOrThrow() && !var24.isJump()) {
                     Assert.a(var23.outsize() == 1);
                     DUtil.modifyInstructionSize(this.ctx, var24, 2);
                     var24.adjustSize(-1);
                     int var19 = (int)var10.getBase();
                     IDInstruction var20 = this.ctx.createJump(var19).withOffset(var24.getOffsetEnd());
                     var20.setData("KEEP_INSTRUCTION", true);
                     var23.add(var20);
                     this.cfg.deleteOutEdges(var23);
                     this.cfg.addEdge(var23, var10);
                  }

                  Object[] var10000 = new Object[]{var1};
                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean xK(btc var1, btb var2) {
      int var3 = var1.q();
      int var4 = var1.RF();
      if (var3 != var4 && var4 != this.cfg.size() - 1) {
         BasicBlock var5 = this.cfg.get(var3);
         BasicBlock var6 = this.cfg.get(var4);
         IDInstruction var7 = (IDInstruction)var6.getLast();
         if (var7.isJump() && var7.getBranchTarget() == var5.getBase()) {
            int var8 = var4 + 1;
            BasicBlock var9 = this.cfg.get(var8);
            BasicBlock var10 = null;

            for (int var11 = var3; var11 < var4; var11++) {
               BasicBlock var12 = this.cfg.get(var11);

               for (BasicBlock var14 : var12.getOutputs()) {
                  if (var14.getBase() > var9.getBase()) {
                     if (var10 == null) {
                        var10 = var14;
                     } else if (var10 != var14) {
                        return false;
                     }
                  }
               }
            }

            if (var10 == null) {
               return false;
            } else {
               int var19 = var2.q(var10.getBase());
               int var20 = var19 - 1;
               BasicBlock var21 = this.cfg.get(var20);
               IDInstruction var22 = (IDInstruction)var21.getLast();
               if (var22.getBreakingFlow().isBroken()) {
                  return false;
               } else {
                  for (int var15 = var3; var15 <= var20; var15++) {
                     BasicBlock var16 = this.cfg.get(var15);

                     for (BasicBlock var18 : var16.getOutputs()) {
                        if (var18.getBase() > var10.getBase()) {
                           return false;
                        }
                     }
                  }

                  for (int var23 = var3 + 1; var23 <= var20; var23++) {
                     BasicBlock var25 = this.cfg.get(var23);

                     for (BasicBlock var29 : var25.getInputs()) {
                        if (var29.getBase() < var5.getBase() || var29.getBase() >= var10.getBase()) {
                           return false;
                        }
                     }
                  }

                  DUtil.modifyInstructionSize(this.ctx, var22, 2);
                  var22.adjustSize(-1);
                  int var24 = (int)var5.getBase();
                  IDImm var26 = this.ctx.getGlobalContext().createBoolean(false);
                  IDInstruction var28 = this.ctx.createJcond(var24, var26).withOffset(var22.getOffsetEnd());
                  var28.setData("KEEP_INSTRUCTION", true);
                  var21.add(var28);
                  this.cfg.addEdge(var21, var5);
                  Object[] var10000 = new Object[]{var1};
                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean Dw(btc var1, btb var2) {
      int var3 = var1.q();
      BasicBlock var4 = this.cfg.get(var3);
      IDInstruction var5 = (IDInstruction)var4.getLast();
      int var6 = var1.RF();
      BasicBlock var7 = this.cfg.get(var6);
      IDInstruction var8 = (IDInstruction)var7.getLast();
      if (!var8.isJump() || var8.getBranchTarget() != var4.getBase()) {
         return false;
      } else if (!var5.isJumpOrJcond()) {
         return false;
      } else {
         int var9 = var5.getBranchTarget();
         if (var9 <= var7.getEndAddress()) {
            return false;
         } else {
            BasicBlock var10 = this.cfg.getBlockAt((long)var9);
            BasicBlock var11 = this.cfg.get(var6 + 1);
            if (var11.irrinsize() == 0) {
               return false;
            } else {
               for (BasicBlock var13 : var11.getIrregularInputs()) {
                  int var14 = var2.q(var13.getBase());
                  if (var14 < var3 || var14 > var6) {
                     return false;
                  }
               }

               BasicBlock var17 = this.cfg.getBlockEndingAt((long)var9);
               IDInstruction var18 = (IDInstruction)var17.getLast();
               if (!var18.isReturnOrThrow()) {
                  return false;
               } else {
                  DUtil.modifyInstructionSize(this.ctx, var18, 2);
                  var18.adjustSize(-1);
                  IDImm var19 = this.ctx.getGlobalContext().createBoolean(true);
                  IDInstruction var15 = this.ctx.createJcond((int)var4.getBase(), var19).withOffset(var18.getOffsetEnd());
                  var15.setData("KEEP_INSTRUCTION", true);
                  BasicBlock var16 = new BasicBlock();
                  var16.add(var15);
                  this.cfg.addBlock(this.cfg.indexOf(var17) + 1, var16);
                  var8.setBranchTarget((int)var15.getOffset());
                  var8.setData("KEEP_INSTRUCTION", true);
                  this.cfg.reconnectEdge(var7, var4, var16);
                  this.cfg.addEdge(var16, var10);
                  this.cfg.addEdge(var16, var4);
                  Object[] var10000 = new Object[]{var1};
                  return true;
               }
            }
         }
      }
   }

   private boolean Uv(btc var1, btb var2) {
      int var3 = var1.q();
      int var4 = var1.RF();
      if (var3 != var4 && var4 != this.cfg.size() - 1) {
         BasicBlock var5 = this.cfg.get(var3);
         IDInstruction var6 = (IDInstruction)var5.getLast();
         if (!var6.isJcond()) {
            return false;
         } else {
            BasicBlock var7 = this.cfg.get(var4);
            IDInstruction var8 = (IDInstruction)var7.getLast();
            if (!var8.isJumpOrJcondTo((int)var5.getBase())) {
               return false;
            } else {
               BasicBlock var9 = this.cfg.getBlockAt((long)var6.getBranchTarget());
               BasicBlock var10 = var9;

               while (((IDInstruction)var10.get(0)).isJump()) {
                  var10 = var10.getOutputBlock(0);
                  if (var10 == var9) {
                     return false;
                  }
               }

               var9 = var10;
               IDInstruction var11 = (IDInstruction)var10.get(0);
               if (!var11.isAssignToVar() || !(var11.getAssignSource() instanceof IDImm)) {
                  return false;
               } else if (var10.outsize() != 1) {
                  return false;
               } else {
                  BasicBlock var12 = var10.getOutputBlock(0);
                  if (!CFGUtil.canReach(var5, var12, false, Sets.newHashSet(var10))) {
                     return false;
                  } else {
                     IDVar var13 = var11.getAssignDestination().asVar();
                     if (var3 == 0) {
                        if (var10.insize() != 1) {
                           return false;
                        } else {
                           this.analyzeChains();
                           if (this.dfa.isAlive(var5, 0, var13.getId())) {
                              return false;
                           } else if (!this.dfa.getReachChains(var10, 0, var13.getId(), 1).isEmpty()) {
                              return false;
                           } else {
                              IDInstruction var25 = (IDInstruction)var5.get(0);
                              DUtil.modifyInstructionSize(this.ctx, var25, 2);
                              var25.adjustSize(-1);
                              long var28 = var25.getOffset();
                              var25.setOffset(var28 + 1L);
                              IDInstruction var32 = var11.duplicateWithOffsetAndSize(var28, 1);
                              var11.transformToNop();
                              var5.add(0, var32);
                              BasicBlock var33 = DUtil.splitBlock(this.ctx, var5, 1);

                              for (BasicBlock var36 : var5.getInputBlocks()) {
                                 this.cfg.reconnectEdge(var36, var5, var33);
                                 ((IDInstruction)var36.getLast()).updateTargets(Maps.toMap(0, 1));
                              }

                              return true;
                           }
                        }
                     } else {
                        BasicBlock var14;
                        if (var10.insize() == 1) {
                           BasicBlock var15 = this.cfg.getBlock(var3 - 1);
                           if (var15.outsize() != 1 || var15.getOutputBlock(0) != var5) {
                              return false;
                           }

                           var14 = var15;
                        } else {
                           int var26 = -1;
                           Map var16 = bto.q(this.cfg, true);

                           for (BasicBlock var19 : (Set)var16.get(var10)) {
                              if (var19 != var9 && var19 != var5) {
                                 int var20 = this.cfg.indexOf(var19);
                                 if (var20 < var3 && (var26 == -1 || var20 > var26)) {
                                    var26 = var20;
                                 }
                              }
                           }

                           if (var26 == -1) {
                              return false;
                           }

                           var14 = this.cfg.get(var26);
                        }

                        if (var14 == null) {
                           return false;
                        } else {
                           this.analyzeChains();
                           if (this.dfa.isAlive(var14, 0, var13.getId())) {
                              return false;
                           } else {
                              IDInstruction var27 = (IDInstruction)var14.get(0);
                              bub.eo var29 = ((bub)var27).q((var1x, var2x, var3x) -> var2x.add(var13.getId()));
                              this.cfg.invalidateDataFlowAnalysis(var27.getOffset());

                              try {
                                 this.analyzeChains();
                                 if (!this.dfa.checkSingleSource(var11.getOffset(), var13.getId(), var27.getOffset())) {
                                    return false;
                                 }
                              } finally {
                                 ((bub)var27).q(var29);
                                 this.cfg.invalidateDataFlowAnalysis(var27.getOffset());
                              }

                              DUtil.modifyInstructionSize(this.ctx, var27, 2);
                              var27.adjustSize(-1);
                              long var30 = var27.getOffset();
                              var27.setOffset(var30 + 1L);
                              IDInstruction var34 = var11.duplicateWithOffsetAndSize(var30, 1);
                              var11.transformToNop();
                              var14.add(0, var34);
                              return true;
                           }
                        }
                     }
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   private boolean oW(btc var1, btb var2) {
      int var3 = var1.q();
      int var4 = var1.RF();
      if (var3 == var4) {
         return false;
      } else {
         BasicBlock var5 = this.cfg.get(var3);
         BasicBlock var6 = this.cfg.get(var4);
         IDInstruction var7 = (IDInstruction)var6.getLast();
         return var7.isJump() && var7.getBranchTarget() == var5.getBase() ? this.q(var6, var5) : false;
      }
   }

   private boolean q(btb var1) {
      int var2 = 0;
      var1.oW();

      for (int var4 : var1.Uv()) {
         BasicBlock var5 = this.cfg.get(var4);
         BasicBlock var6 = btb.q(var5);
         if (var6 != null && var6 != var5) {
            IDInstruction var7 = (IDInstruction)var6.getLast();
            if (var7.isJump() && var7.getBranchTarget() == var5.getBase() && this.q(var6, var5)) {
               var2++;
            }
         }
      }

      for (int var9 = 0; var9 < this.cfg.size(); var9++) {
         BasicBlock var10 = this.cfg.get(var9);
         IDInstruction var11 = (IDInstruction)var10.getLast();
         if (var11.isSwitch()) {
            boolean var12 = false;

            for (BasicBlock var8 : var10.getOutputs()) {
               if (var8 == var10) {
                  var12 = true;
                  break;
               }
            }

            if (var12) {
               BasicBlock var14 = new BasicBlock();
               IDInstruction var15 = this.ctx.createJump((int)var10.getBase()).withOffset(this.cfg.getEffectiveSize());
               var15.setData("KEEP_INSTRUCTION", true);
               var14.add(var15);
               this.cfg.addBlock(var14);
               this.cfg.addEdge(var14, var10);
               this.cfg.reconnectEdges(var10, var10, var14);
               var11.updateTargets(Maps.toMap((int)var10.getBase(), (int)var14.getBase()));
               var2++;
            }
         }
      }

      return var2 > 0;
   }

   boolean q(BasicBlock var1) {
      IDInstruction var2 = (IDInstruction)var1.getLast();
      if (!var2.isJump()) {
         return false;
      } else {
         BasicBlock var3 = var1.getOutputBlock(0);
         return this.q(var1, var3);
      }
   }

   private boolean q(BasicBlock var1, BasicBlock var2) {
      BasicBlock var3;
      IDInstruction var4;
      if (var1.insize() == 1
         && var1.irrinsize() == 0
         && var1.irroutsize() == 0
         && (var3 = var1.getInputBlock(0)).getEndAddress() < var1.getBase()
         && var2.getBase() < var3.getBase()
         && (var4 = (IDInstruction)var3.getLast()).isJcond()
         && var4.getBranchTarget() == var1.getBase()) {
         DUtil.modifyInstructionSize(this.ctx, var4, 1 + var1.size());
         var4.setSize(var4.getSize() - var1.size());
         long var5 = var4.getOffsetEnd();
         BasicBlock var7 = bto.q(var1, var5, 1);
         this.cfg.deleteOutEdges(var3);
         this.cfg.deleteOutEdges(var1);
         int var8 = this.cfg.indexOf(var3);
         BasicBlock var9 = this.cfg.getBlock(var8 + 1);
         this.cfg.addBlock(var8 + 1, var7);
         this.cfg.addEdge(var7, var2);
         this.cfg.addEdge(var3, var7);
         this.cfg.addEdge(var3, var9);
         var4.reverseJcondCondition();
         var4.setBranchTarget((int)var9.getBase());
         return true;
      } else {
         return false;
      }
   }

   private boolean gO(btc var1, btb var2) {
      int var3 = var1.q();
      int var4 = var1.RF();
      if (var3 != 0 && var3 != var4) {
         BasicBlock var5 = this.cfg.get(var3);
         if (var5.size() != 1) {
            return false;
         } else {
            IDInstruction var6 = (IDInstruction)var5.getLast();
            if (!var6.isJcond()) {
               return false;
            } else {
               BasicBlock var7 = this.cfg.get(var3 - 1);
               if (var7.outsize() == 1 && var7.getOutputBlock(0) == var5 && !((IDInstruction)var7.getLast()).getBreakingFlow().isBroken()) {
                  IDExpression var8 = var6.getJcondCondition();
                  if (var8 instanceof IDOperation var9) {
                     if (var9.getOperatorType().isAnyOf(JavaOperatorType.LOG_NOT, JavaOperatorType.LOG_IDENT)) {
                        var8 = var9.getOperand2();
                     } else {
                        var8 = null;
                     }
                  }

                  if (var8 != null
                     && var8 instanceof IDCallInfo var15
                     && var15.hasThis()
                     && var15.getCountOfArguments() == 1
                     && "hasNext".equals(var15.getMethodName())
                     && var15.getArgument(0) instanceof IDVar) {
                     IDVar var10 = var15.getArgument(0).asVar();
                     HashSet var11 = new HashSet();
                     int var12 = -1;

                     for (int var13 = var7.size() - 1; var13 >= 0; var13--) {
                        IDInstruction var14 = (IDInstruction)var7.get(var13);
                        if (var14.isAssignToVar(var10.getId())) {
                           var12 = var13;
                           break;
                        }

                        if (!var14.isAssignToVar() || var14.countUsedVariable(var10) != 0 || var14.getAssignSource().hasSideEffects(this.ctx, true)) {
                           break;
                        }

                        var11.add(var14.getAssignDestination().asVar().getId());
                     }

                     if (var12 == -1) {
                        return false;
                     } else if (var12 == var7.size() - 1) {
                        return false;
                     } else {
                        HashSet var16 = new HashSet();
                        ((IDInstruction)var7.get(var12)).getAssignSource().collectVarIds(var16);
                        if (CollectionUtil.hasIntersection(var16, var11)) {
                           return false;
                        } else {
                           bto.q(var7, var12, var7.size() - 1);
                           return true;
                        }
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }
}
