package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class bxm extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(bxm.class);

   public bxm() {
      this.setPriority(-100.0);
   }

   @Override
   public int perform() {
      if (((bpx)this.ctx).getParentContext() != null) {
         return 0;
      } else {
         int var1 = 0;

         for (int var2 = 1; var2 < this.cfg.size() - 1; var2++) {
            BasicBlock var3 = this.cfg.get(var2);
            if (var3.allinsize() == 1 && DUtil.checkBlock(var3, DOpcodeType.IR_ASSIGN)) {
               IDInstruction var4 = (IDInstruction)var3.get(0);
               BasicBlock var5 = this.cfg.get(var2 - 1);
               IDInstruction var6;
               IDInstruction var7;
               if (var5.size() >= 2
                  && (var6 = (IDInstruction)var5.getLast()).getOpcode() == DOpcodeType.IR_JCOND
                  && (var7 = (IDInstruction)var5.get(var5.size() - 2)).getOpcode() == DOpcodeType.IR_ASSIGN
                  && var7.getAssignDestination() instanceof IDVar
                  && !var7.hasUseSideEffects(true)) {
                  BasicBlock var8 = this.cfg.get(var2 + 1);
                  if (var8.allinsize() == 2 && (int)var8.getAddress() == var6.getBranchTarget()) {
                     IDExpression var9 = var4.getAssignDestination();
                     if (var9.equals(var7.getAssignDestination())) {
                        IDVar var10 = (IDVar)var9;
                        Object[] var10000 = new Object[]{var10};
                        IDTryData var11 = this.ctx.getExceptionData();
                        if (var11.compareHandlers((int)var5.getAddress(), (int)var3.getAddress())) {
                           IDExpression var12 = var4.getAssignSource();
                           if (!(var12 instanceof IDOperation) || !((IDOperation)var12).isConditional()) {
                              IDExpression var13 = var7.getAssignSource();
                              if (!(var13 instanceof IDOperation) || !((IDOperation)var13).isConditional()) {
                                 IDExpression var14 = var6.getJcondCondition();
                                 if (var12.getType().equals(var13.getType())) {
                                    IDOperation var15 = this.g.createConditional(var12.getType(), var14.duplicate(), var13.duplicate(), var12.duplicate());
                                    IDInstruction var16 = this.ctx.createAssign(var10.duplicate(), var15);
                                    this.cfg.deleteOutEdges(var5);
                                    this.cfg.addEdge(var5, var8);
                                    var16.copyBaseFields(var6);
                                    var5.set(var5.size() - 1, var16);
                                    this.cleanGraph();
                                    var1++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         for (int var18 = 1; var18 < this.cfg.size() - 2; var18++) {
            BasicBlock var21 = this.cfg.get(var18);
            if (var21.allinsize() == 1 && DUtil.checkBlock(var21, DOpcodeType.IR_ASSIGN, DOpcodeType.IR_JUMP)) {
               BasicBlock var24 = this.cfg.get(var18 - 1);
               if (((IDInstruction)var24.getLast()).isJcond()) {
                  BasicBlock var27 = this.cfg.get(var18 + 1);
                  if (var27.allinsize() == 1
                     && (int)var27.getAddress() == ((IDInstruction)var24.getLast()).getBranchTarget()
                     && DUtil.checkBlock(var27, DOpcodeType.IR_ASSIGN)
                     && ((IDInstruction)var21.getLast()).getBranchTarget() == (int)var27.getEndAddress()) {
                     IDExpression var30 = (IDExpression)((IDInstruction)var21.get(0)).getOperand1();
                     if (var30 instanceof IDVar && ((IDInstruction)var27.get(0)).getOperand1().equals(var30)) {
                        IDVar var33 = (IDVar)var30;
                        Object[] var60 = new Object[]{var33};
                        IDTryData var36 = this.ctx.getExceptionData();
                        if (var36.compareHandlers((int)var24.getAddress(), (int)var21.getAddress())
                           && var36.compareHandlers((int)var27.getAddress(), (int)var21.getAddress())) {
                           IDExpression var39 = (IDExpression)((IDInstruction)var21.get(0)).getOperand2();
                           if (!(var39 instanceof IDOperation) || !((IDOperation)var39).isConditional()) {
                              IDExpression var42 = (IDExpression)((IDInstruction)var27.get(0)).getOperand2();
                              if (!(var42 instanceof IDOperation) || !((IDOperation)var42).isConditional()) {
                                 IDExpression var45 = ((IDInstruction)var24.getLast()).getJcondCondition();
                                 if (var39.getType().equals(var42.getType())) {
                                    IDOperation var48 = this.g.createConditional(var39.getType(), var45.duplicate(), var42.duplicate(), var39.duplicate());
                                    IDInstruction var51 = this.ctx.createAssign(var33.duplicate(), var48);
                                    BasicBlock var54 = this.cfg.get(var18 + 2);
                                    this.cfg.deleteEdge(var24, var21);
                                    this.cfg.deleteEdge(var24, var27);
                                    this.cfg.addEdge(var24, var54);
                                    var51.setOffset(((IDInstruction)var24.getLast()).getOffset());
                                    var51.setSize(((IDInstruction)var24.getLast()).getSize());
                                    var24.set(var24.size() - 1, var51);
                                    this.cleanGraph();
                                    var1++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         for (int var19 = 0; var19 < this.cfg.size() - 1; var19++) {
            BasicBlock var22 = this.cfg.get(var19);
            IDInstruction var25 = (IDInstruction)var22.getLast();
            if (var25.getOpcode() == DOpcodeType.IR_JCOND) {
               BasicBlock var28 = this.cfg.get(var19 + 1);
               BasicBlock var31 = this.cfg.getBlockAt((long)var25.getBranchTarget());
               if (var28.size() == 1 && var31.size() == 1) {
                  IDInstruction var34 = (IDInstruction)var28.get(0);
                  DOpcodeType var37 = var34.getOpcode();
                  if (var37 == DOpcodeType.IR_RETURN || var37 == DOpcodeType.IR_THROW) {
                     IDInstruction var40 = (IDInstruction)var31.get(0);
                     if (var40.getOpcode() == var37 && var34.getOperand2() != null && var40.getOperand2() != null) {
                        IDTryData var43 = this.ctx.getExceptionData();
                        if (var43.compareHandlers((int)var31.getAddress(), (int)var22.getAddress())
                           && var43.compareHandlers((int)var28.getAddress(), (int)var22.getAddress())) {
                           IDExpression var46 = (IDExpression)var34.getOperand2();
                           if (!(var46 instanceof IDOperation) || !((IDOperation)var46).isConditional()) {
                              IDExpression var49 = (IDExpression)var40.getOperand2();
                              if (!(var49 instanceof IDOperation) || !((IDOperation)var49).isConditional()) {
                                 IDExpression var52 = var25.getJcondCondition();
                                 if (var46.getType().equals(var49.getType())) {
                                    IDOperation var55 = this.g.createConditional(var46.getType(), var52.duplicate(), var49.duplicate(), var46.duplicate());
                                    IDInstruction var57 = var37 == DOpcodeType.IR_RETURN ? this.ctx.createReturn(var55) : this.ctx.createThrow(var55);
                                    this.cfg.deleteEdge(var22, var28);
                                    this.cfg.deleteEdge(var22, var31);
                                    var57.setOffset(var25.getOffset());
                                    var57.setSize(var25.getSize());
                                    var22.set(var22.size() - 1, var57);
                                    this.cleanGraph();
                                    var1++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         int var20 = 0;

         for (int var23 = 0; var23 < this.cfg.size() - 5; var23++) {
            BasicBlock var26 = this.cfg.get(var23);
            IDInstruction var29 = (IDInstruction)var26.getLast();
            if (var29.isJcond()) {
               int var32 = var29.getBranchTarget();
               BasicBlock var35 = this.cfg.get(var23 + 1);
               if (var35.size() == 1 && var35.insize() == 1 && ((IDInstruction)var35.get(0)).isJcond()) {
                  int var38 = ((IDInstruction)var35.get(0)).getBranchTarget();
                  BasicBlock var41 = this.cfg.get(var23 + 2);
                  if (var41.size() == 1 && var41.insize() == 1 && ((IDInstruction)var41.get(0)).isJump()) {
                     int var44 = ((IDInstruction)var41.get(0)).getBranchTarget();
                     BasicBlock var47 = this.cfg.get(var23 + 3);
                     if (var47.size() == 1 && var47.insize() == 1 && var47.getBase() == var32 && ((IDInstruction)var47.get(0)).isJcondTo(var44)) {
                        BasicBlock var50 = this.cfg.get(var23 + 4);
                        if (var50.getBase() == var38) {
                           IDExpression var53 = var29.getJcondCondition();
                           IDExpression var56 = ((IDInstruction)var35.get(0)).getJcondCondition();
                           IDExpression var58 = ((IDInstruction)var47.get(0)).getJcondCondition();
                           if (!var56.canThrow(this.ctx) && !var58.canThrow(this.ctx) && var56 instanceof IDOperation var59 && var59.canReverse()) {
                              var59.reverse();
                              IDOperation var17 = this.g.createConditional(this.tf.getBoolean(), var53.duplicate(), var58.duplicate(), var56.duplicate());
                              var29.setJcondCondition(var17);
                              var29.setBranchTarget(var44);
                              this.cfg.reconnectEdge(var26, var47, this.cfg.getBlockAt((long)var44));
                              ((IDInstruction)var35.get(0)).transformToJump(var38);
                              this.cfg.deleteOutEdges(var35);
                              this.cfg.addEdge(var35, this.cfg.getBlockAt((long)var38));
                              var20++;
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var20 > 0) {
            this.cleanGraph();
            var1 += var20;
         }

         if (var1 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }
}
