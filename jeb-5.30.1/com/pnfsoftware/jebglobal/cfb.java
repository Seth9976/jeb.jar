package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.ArrayList;
import java.util.TreeMap;

public class cfb extends AbstractDOptimizer {
   private static final int q = 2;
   private int RF = 0;

   public cfb() {
      this.addTag("reorderer");
   }

   @Override
   public int perform() {
      if (this.RF >= 1) {
         return 0;
      } else {
         this.RF++;
         if (bto.q(this.ctx)) {
            this.assignLocalFields(this.ctx);
         }

         return this.q();
      }
   }

   int q() {
      int var1 = 0;
      var1 += this.RF();
      return var1 + this.xK();
   }

   private int RF() {
      int var1 = 0;
      int var2 = 0;
      bsr var3 = new bsr(this.ctx, true);
      int var4 = 100000;

      while (var2++ <= var4) {
         bss var5 = var3.Uv();
         if (var5 == null) {
            break;
         }

         int var6 = var5.q();
         int var7 = var5.xK();
         if (var6 < var7 && var7 - 1 >= 0) {
            BasicBlock var8 = this.cfg.get(var6);
            BasicBlock var9 = this.cfg.get(var7);
            if (var5.Dw() == bst.RF) {
               BasicBlock var10 = this.cfg.get(var7 - 1);
               IDInstruction var11 = (IDInstruction)var10.getLast();
               if (var11.isJump()) {
                  int var12 = var11.getBranchTarget();
                  if (var12 <= var8.getBase() || var12 > var9.getBase()) {
                     int var13 = 0;
                     ArrayList var14 = new ArrayList();
                     var14.add(var10);

                     for (BasicBlock var16 : var8.getOutputs()) {
                        if (var13 >= 1) {
                           int var17 = var3.q(var16.getBase());
                           if (var17 >= 1) {
                              BasicBlock var18 = this.cfg.getBlock(var17 - 1);
                              if (((IDInstruction)var18.getLast()).isJumpTo(var12) && !var14.contains(var18)) {
                                 var14.add(var18);
                              }
                           }
                        }

                        var13++;
                     }

                     if (var14.size() >= 2) {
                        BasicBlock var22 = this.cfg.getBlockAt((long)var12);
                        DUtil.modifyInstructionSize(this.ctx, var11, 2);
                        var11.adjustSize(-1);
                        IDInstruction var23 = this.ctx.createJump((int)var22.getBase()).withOffset(var11.getOffsetEnd());
                        var23.setData("KEEP_INSTRUCTION", true);
                        BasicBlock var24 = new BasicBlock();
                        var24.add(var23);
                        this.cfg.addBlock(var5.xK(), var24);
                        this.cfg.addEdge(var24, var22);

                        for (BasicBlock var19 : var14) {
                           IDInstruction var20 = (IDInstruction)var19.getLast();
                           var20.setData("KEEP_INSTRUCTION", true);
                           var20.setBranchTarget((int)var24.getBase());
                           this.cfg.reconnectEdge(var19, var22, var24);
                        }

                        Object[] var26 = new Object[]{var5};
                        var3.q();
                        var1++;
                     }
                  }
               }
            }
         }
      }

      if (var2 > var4) {
         RuntimeException var21 = new RuntimeException("RefC optimizer does not seem to terminate");
         com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var21, this.ctx.getMethodSignature());
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private int xK() {
      int var1 = 0;
      int var2 = 0;
      bsr var3 = new bsr(this.ctx, true);
      int var4 = 100000;

      label210:
      while (var2++ <= var4) {
         bss var5 = var3.Uv();
         if (var5 == null) {
            break;
         }

         int var6 = var5.q();
         int var7 = var5.xK();
         if (var6 < var7 && var7 >= 0 && !var5.Dw) {
            BasicBlock var8 = this.cfg.get(var6);
            BasicBlock var9 = this.cfg.get(var7);
            TreeMap var10 = new TreeMap();

            for (BasicBlock var12 : var8.getOutputs()) {
               var10.put(var12.getBase(), var3.q(var12.getBase()));
            }

            if (var10.size() >= 2) {
               long var33 = var9.getBase();
               int var13 = 0;
               ArrayList var14 = new ArrayList(var10.keySet());

               label202:
               for (int var15 = 0; var15 < var14.size(); var15++) {
                  long var16 = (Long)var14.get(var15);
                  long var18 = var15 + 1 < var14.size() ? (Long)var14.get(var15 + 1) : var33;
                  if (var18 > var33) {
                     var18 = var33;
                  }

                  int var20 = var3.q(var16);
                  int var21 = var3.q(var18);
                  BasicBlock var22 = null;

                  for (int var23 = var20; var23 < var21; var23++) {
                     if (var18 != var33 || var20 == var23 || CFGUtil.canReach(this.cfg.getBlock(var20), this.cfg.getBlock(var23), false, Sets.newHashSet(var9))
                        )
                      {
                        boolean var24 = false;
                        boolean var25 = false;
                        BasicBlock var26 = this.cfg.get(var23);

                        for (BasicBlock var28 : var26.getInputs()) {
                           if (var28 != var8 && (var28.getBase() < var16 || var28.getBase() >= var18)) {
                              var25 = true;
                           } else {
                              var24 = true;
                           }
                        }

                        if (var24 && var25) {
                           var22 = var26;
                           break;
                        }
                     }
                  }

                  if (var22 != null && var22.irrinsize() <= 0 && var22.outsize() == 1) {
                     boolean var34 = ((IDInstruction)var22.getLast()).isJump();
                     if ((!((IDInstruction)var22.getLast()).getBreakingFlow().isBroken() || var34) && var22.size() - (var34 ? 1 : 0) <= 2) {
                        BasicBlock var35 = var22.getOutputBlock(0);
                        BasicBlock var36 = this.cfg.getBlockAt(var16);
                        BasicBlock var38 = this.cfg.getBlockAt(var18);

                        for (BasicBlock var42 : var22.getInputBlocks()) {
                           if (var42 != var8 && (var42.getBase() < var36.getAddress() || var42.getBase() >= var38.getBase())) {
                              IDInstruction var29 = (IDInstruction)var42.getLast();
                              if (var29.isJcondOrSwitch() && var29.getOffsetEnd() == var22.getBase()) {
                                 continue label202;
                              }
                           }
                        }

                        BasicBlock var41 = bto.q(var22, this.cfg.getEndAddress());
                        if (!var34) {
                           IDInstruction var43 = this.ctx.createJump((int)var35.getBase());
                           var43.setOffset(var41.getEndAddress());
                           var41.add(var43);
                        } else {
                           ((IDInstruction)var41.getLast()).setBranchTarget((int)var35.getBase());
                        }

                        this.cfg.addBlock(var41);
                        this.cfg.addEdge(var41, var35);
                        bto.q(var22, var41);
                        var36 = this.cfg.getBlockAt(var16);
                        var38 = this.cfg.getBlockAt(var18);

                        for (BasicBlock var45 : var22.getInputBlocks()) {
                           if (var45 != var8 && (var45.getBase() < var36.getAddress() || var45.getBase() >= var38.getBase())) {
                              IDInstruction var30 = (IDInstruction)var45.getLast();
                              if (var30.isJump() || var30.isJcond()) {
                                 var30.setBranchTarget((int)var41.getAddress());
                              } else if (var30.isSwitch()) {
                                 var30.getSwitchData().updateCases((int)var22.getAddress(), (int)var41.getAddress());
                              } else {
                                 DUtil.modifyInstructionSize(this.ctx, var30, 2);
                                 var30.adjustSize(-1);
                                 IDInstruction var31 = this.ctx.createJump((int)var41.getAddress());
                                 var31.setOffset(var30.getOffsetEnd());
                                 var45.add(var31);
                              }

                              this.cfg.reconnectEdges(var45, var22, var41);
                              var13++;
                           }
                        }

                        Assert.a(var13 > 0);
                        if (var13 != 0) {
                           Object[] var46 = new Object[]{var5, var2, var4};
                           var3.q();
                           if (this.cfg.size() <= 100 && bto.q(this.ctx)) {
                              this.assignLocalFields(this.ctx);
                           }

                           var1++;
                        }
                        continue label210;
                     }
                  }
               }
               break;
            }
         }
      }

      if (var2 > var4) {
         RuntimeException var32 = new RuntimeException("RefC optimizer does not seem to terminate");
         com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var32, this.ctx.getMethodSignature());
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
