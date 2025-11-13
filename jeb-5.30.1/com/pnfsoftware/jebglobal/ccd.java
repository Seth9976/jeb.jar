package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class ccd extends AbstractDOptimizer {
   private static final ILogger q = GlobalLog.getLogger(ccd.class);

   @Override
   public int perform() {
      int var1 = 0;

      int var20;
      do {
         this.checkInterrupted();
         var20 = 0;

         for (BasicBlock var4 : this.cfg) {
            boolean var5 = false;
            IDInstruction var6;
            if (var4.size() == 1 && var4.insize() == 1 && var4.outsize() == 2 && var4.irrinsize() == 0 && (var6 = (IDInstruction)var4.get(0)).isJcond()) {
               BasicBlock var8 = var4.getInputBlock(0);
               BasicBlock var9 = var4.getOutputBlock(0);
               IDInstruction var10 = (IDInstruction)var8.getLast();
               if (var10.isJcond() && var8.outsize() == 2) {
                  IDExpression var12 = var10.getJcondCondition();
                  IDExpression var13 = var6.getJcondCondition();
                  BasicBlock var14 = var4.getOutputBlock(1);
                  BasicBlock var15 = var8.getOutputBlock(0);
                  BasicBlock var16 = var8.getOutputBlock(1);
                  if (var15 == var4) {
                     if (var16 != var9 && var16 == var14 && var9.irrinsize() == 0) {
                        if (this.q(var8, var4)) {
                           this.cfg.reconnectEdge(var8, var4, var9);
                           this.cfg.deleteOutEdges(var4);
                           var5 = DUtil.removeUnreachableTrampoline(this.cfg, var4);
                           IDOperation var24 = this.g.createPredicate(JavaOperatorType.LOG_OR, var12, var13);
                           var10.setJcondCondition(var24);
                           var10.setBranchTarget(var6.getBranchTarget());
                           Object[] var30 = new Object[0];
                           var20++;
                        }
                     } else if (var16 != var9 || var14 == var9 || var9.irrinsize() != 0) {
                        if (var9.size() == 1 && ((IDInstruction)var9.get(0)).isJump()) {
                           BasicBlock var23 = var9.getOutputBlock(0);
                           if (var23 == var16 && var23 != var9 && var9.getEndAddress() != var14.getFirstAddress()) {
                              int var25 = bto.q(this.ctx, var8, var4, false);
                              if (var25 >= 0) {
                                 if (var25 == 3) {
                                    bto.q(var4, var8);
                                 }

                                 this.cfg.reconnectEdge(var8, var4, var9);
                                 this.cfg.reconnectEdge(var8, var16, var14);
                                 this.cfg.deleteEdge(var4, var14);
                                 this.cfg.deleteEdge(var4, var9);
                                 var5 = DUtil.removeUnreachableTrampoline(this.cfg, var4);
                                 IDOperation var26 = this.g
                                    .createPredicate(JavaOperatorType.LOG_AND, this.g.createPredicate(JavaOperatorType.LOG_NOT, null, var12), var13);
                                 var10.setJcondCondition(var26);
                                 var10.setBranchTarget(var6.getBranchTarget());
                                 Object[] var29 = new Object[0];
                                 var20++;
                              }
                           }
                        } else if (var9 != var16 && var16 != var14) {
                           IDInstruction var22 = (IDInstruction)var9.get(0);
                           IDInstruction var18 = (IDInstruction)var16.get(0);
                           IDInstruction var19 = (IDInstruction)var14.get(0);
                           if (var9.size() == 1
                              && var9.outsize() == 0
                              && var16.size() == 1
                              && var16.outsize() == 0
                              && ((bub)var22).q(var18)
                              && this.q(var9, var16)) {
                              var10.setBranchTarget((int)var9.getAddress());
                              this.cfg.reconnectEdge(var8, var16, var9);
                              var5 = DUtil.removeUnreachableBlocks(this.ctx) > 0;
                              DUtil.simplifyJCondsAndSwitches(this.ctx);
                              Object[] var28 = new Object[0];
                              var20++;
                           } else if (var16.size() == 1
                              && var16.outsize() == 0
                              && var14.size() == 1
                              && var14.outsize() == 0
                              && ((bub)var18).q(var19)
                              && this.q(var16, var14)) {
                              var10.setBranchTarget((int)var14.getAddress());
                              this.cfg.reconnectEdge(var8, var16, var14);
                              var5 = DUtil.removeUnreachableBlocks(this.ctx) > 0;
                              DUtil.simplifyJCondsAndSwitches(this.ctx);
                              Object[] var27 = new Object[0];
                              var20++;
                           }
                        }
                     } else if (this.q(var8, var4)) {
                        this.cfg.reconnectEdge(var8, var9, var14);
                        this.cfg.reconnectEdge(var8, var4, var9);
                        this.cfg.deleteOutEdges(var4);
                        var5 = DUtil.removeUnreachableTrampoline(this.cfg, var4);
                        IDOperation var17 = this.g
                           .createPredicate(JavaOperatorType.LOG_AND, this.g.createPredicate(JavaOperatorType.LOG_NOT, null, var12), var13);
                        var10.setJcondCondition(var17);
                        var10.setBranchTarget(var6.getBranchTarget());
                        Object[] var10000 = new Object[0];
                        var20++;
                     }
                  }
               }

               if (var5) {
                  break;
               }
            }
         }

         int var21 = this.cfg.simplify();
         var20 += var21;
         var1 += var20;
      } while (var20 > 0);

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean q(BasicBlock var1, BasicBlock var2) {
      return bto.q(this.ctx, var1, var2, true) == 0;
   }
}
