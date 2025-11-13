package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class bzr extends AbstractDOptimizer {
   public bzr() {
      super(DOptimizerType.NORMAL);
   }

   @Override
   public int perform() {
      int var1 = 0;
      ArrayList var2 = new ArrayList();
      var1 += this.pC(var2);

      for (IDInstruction var4 : var2) {
         if (this.pC(var4)) {
            var1++;
         }
      }

      return var1;
   }

   int pC(Collection var1) {
      IDTryData var2 = this.ctx.getExceptionData();
      if (var2 != null && !var2.isEmpty()) {
         int var3 = 0;

         for (int var4 = 0; var4 < this.cfg.size() - 2; var4++) {
            BasicBlock var5 = this.cfg.get(var4);
            if (var5.size() == 3
               && var5.insize() == 0
               && var5.outsize() == 1
               && var5.irrinsize() != 0
               && var5.irroutsize() == 1
               && DUtil.checkBlock(var5, DOpcodeType.IR_STORE_EXCEPTION, DOpcodeType.IR_INVOKE, DOpcodeType.IR_JUMP)) {
               BasicBlock var6 = var5.getIrregularOutputBlock(0);
               if (var6.size() == 2
                  && var6.insize() == 0
                  && var6.outsize() == 1
                  && var6.irrinsize() == 1
                  && var6.getIrregularInputBlock(0) == var5
                  && DUtil.checkBlock(var6, DOpcodeType.IR_STORE_EXCEPTION, DOpcodeType.IR_INVOKE)) {
                  BasicBlock var7 = var6.getOutputBlock(0);
                  if (var7.size() == 1
                     && var7.insize() == 2
                     && var7 == var5.getOutputBlock(0)
                     && var7.outsize() == 0
                     && var7.irrinsize() == 0
                     && DUtil.checkBlock(var7, DOpcodeType.IR_THROW)) {
                     IDVar var8 = ((IDInstruction)var5.get(0)).getStoredExceptionVariable();
                     if (((IDInstruction)var5.get(1)).getInvokeData() instanceof IDCallInfo var10
                        && (var10.getInvokeType() == DInvokeType.VIRTUAL || var10.getInvokeType() == DInvokeType.INTERFACE)
                        && var10.getMethodSignature().endsWith("->close()V")
                        && var10.getArgument(0) instanceof IDVar var11) {
                        IDVar var13 = ((IDInstruction)var6.get(0)).getStoredExceptionVariable();
                        if (((IDInstruction)var6.get(1)).getInvokeData() instanceof IDCallInfo var26
                           && (var26.getInvokeType() == DInvokeType.VIRTUAL || var26.getInvokeType() == DInvokeType.INTERFACE)
                           && var26.getMethodSignature().equals("Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V")) {
                           IDExpression var14 = var26.getArgument(0);
                           IDExpression var15 = var26.getArgument(1);
                           if (var14 == var8 && var15 == var13) {
                              Object[] var10000 = new Object[]{var11};
                              IDExpression var16 = ((IDInstruction)var7.get(0)).getThrowExpression();
                              if (var16 == var8) {
                                 this.cfg.deleteIrregularOutEdges(var6);
                                 var2.unprotectBlock((int)var6.getBase());
                                 IDexMethod var17 = this.dex
                                    .addMethod("Ljeb/synthetic/TWR;", "safeClose$NT", "(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V");
                                 IDCallInfo var27 = this.ctx.createCallInfo(DInvokeType.STATIC, var17.getIndex(), Arrays.asList(var11, var8));
                                 IDInstruction var18 = this.ctx.createInvoke(var27);
                                 var18.copyBaseFields((IDInstruction)var5.get(1));
                                 var5.set(1, var18);
                                 if (var1 != null) {
                                    var1.add(var18);
                                 }

                                 var2.unprotectBlock((int)var5.getBase());
                                 this.cfg.deleteIrregularOutEdges(var5);
                                 var3++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         for (int var19 = 0; var19 < this.cfg.size(); var19++) {
            BasicBlock var20 = this.cfg.get(var19);
            if (var20.insize() == 0 && var20.irrinsize() != 0 && ((IDInstruction)var20.get(0)).isStoreException()) {
               bzr.Av var21 = new bzr.Av(var20, false, false);
               if (var21.pC()) {
                  IDInstruction var22 = (IDInstruction)var20.get(0);
                  DUtil.modifyInstructionSize(this.ctx, var22, 3);
                  if (var20.size() > 1) {
                     DUtil.splitBlock(this.ctx, var20, 1);
                  }

                  this.cfg.deleteOutEdges(var20);
                  this.cfg.deleteIrregularOutEdges(var20);
                  var2.unprotectBlock((int)var20.getBase());
                  IDexMethod var23 = this.dex.addMethod("Ljeb/synthetic/TWR;", "safeClose$NT", "(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V");
                  IDCallInfo var25 = this.ctx.createCallInfo(DInvokeType.STATIC, var23.getIndex(), Arrays.asList(var21.pC, var21.A));
                  IDInstruction var28 = this.ctx.createInvoke(var25);
                  var22.adjustSize(-2);
                  var28.setOffset(var22.getOffsetEnd());
                  var20.add(1, var28);
                  if (var1 != null) {
                     var1.add(var28);
                  }

                  IDInstruction var29 = this.ctx.createThrow(var21.A);
                  var29.setOffset(var28.getOffsetEnd());
                  var20.add(2, var29);
                  BasicBlock var30 = this.cfg.getBlockFor(var21.wS);
                  if (var2.copyProtectedBlock((int)var30.getBase(), (int)var20.getBase())) {
                     for (BasicBlock var32 : var30.getIrregularOutputs()) {
                        this.cfg.addIrregularEdge(var20, var32, -1);
                     }
                  }

                  var3++;
               }
            }
         }

         if (var3 > 0) {
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var3;
      } else {
         return 0;
      }
   }

   boolean pC(IDInstruction var1) {
      if (this.cfg.getInstructionAt(var1.getOffset()) != var1) {
         return false;
      } else {
         IDVar var2;
         try {
            if (!var1.isInvoke()) {
               return false;
            }

            IDExpression var3 = var1.getInvokeData().asCallInfo().getArgument(0);
            if (!var3.isVar()) {
               return false;
            }

            var2 = var3.asVar();
         } catch (Exception var9) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
               new RuntimeException("pre-process-TWR unexpected safe-close format", var9), this.ctx.getMethodSignature()
            );
            return false;
         }

         this.analyzeChains();
         int var10 = var2.getId();
         Long var4 = this.dfa.checkSingleDef(var1.getOffset(), var10);
         if (var4 != null && var4 >= 0L) {
            Couple var5 = this.cfg.getInstructionLocation(var4);
            if (var5 == null) {
               return false;
            } else {
               BasicBlock var6 = (BasicBlock)var5.getFirst();
               int var7 = (Integer)var5.getSecond();
               if (var7 == var6.size() - 1) {
                  return false;
               } else if (DUtil.canThrow(var6, var7 + 1)) {
                  return false;
               } else {
                  BasicBlock var8 = DUtil.splitBlock(this.ctx, var6, var7 + 1);
                  ((IDInstruction)var6.get(var7)).setData("KEEP_LAST", true);
                  if (this.ctx.getExceptionData().unprotectBlock((int)var8.getBase())) {
                     this.cfg.deleteIrregularOutEdges(var8);
                  }

                  this.cleanGraph();
                  this.cfg.invalidateDataFlowAnalysis();
                  return true;
               }
            }
         } else {
            return false;
         }
      }
   }

   class Av {
      private BasicBlock E;
      private boolean sY = true;
      private boolean ys = true;
      private int ld;
      IDVar pC;
      IDVar A;
      IDVar kS;
      private BasicBlock gp;
      private Long oT;
      IDInstruction wS;

      Av(BasicBlock var2, boolean var3, boolean var4) {
         if (var2 == null) {
            throw new IllegalArgumentException();
         } else {
            this.E = var2;
            this.sY = var3;
            this.ys = var4;
         }
      }

      boolean pC() {
         BasicBlock var1 = this.E;
         int var2 = 0;

         while (var1 != null) {
            IDInstruction var3 = (IDInstruction)var1.get(var2);
            if (++var2 >= var1.size()) {
               var1 = bzr.this.cfg.getBlockAt(var1.getEndAddress());
               var2 = 0;
            }

            if (!var3.isNop()) {
               if (var3.isJump()) {
                  var1 = bzr.this.cfg.getBlockAt((long)var3.getBranchTarget());
                  var2 = 0;
               } else if (this.ld == 0) {
                  if (!var3.isStoreException()) {
                     return false;
                  }

                  this.A = var3.getStoredExceptionVariable();
                  this.ld = 1;
               } else if (this.ld == 1) {
                  if (var3.isInvoke()) {
                     if (var3.getInvokeData() instanceof IDCallInfo var12
                        && (var12.getInvokeType() == DInvokeType.VIRTUAL || var12.getInvokeType() == DInvokeType.INTERFACE)
                        && var12.getMethodSignature().endsWith("->close()V")
                        && var12.getArgument(0) instanceof IDVar var14) {
                        if (this.pC == null) {
                           this.pC = var14;
                        } else if (this.pC != var14) {
                           return false;
                        }

                        BasicBlock var18 = bzr.this.cfg.getBlockFor(var3);
                        if (var18.irroutsize() == 1
                           && (DUtil.isProtectedByCatchAll(bzr.this.ctx, var18) || !this.ys && bpl.pC(bzr.this.ctx, var18, "Ljava/lang/Exception;"))) {
                           this.gp = var18.getIrregularOutputBlock(0);
                           this.ld = 2;
                           continue;
                        }

                        if (var18.irroutsize() == 0 && !this.sY) {
                           this.ld = 2;
                           continue;
                        }
                     }
                  } else if (var3.isJcond()
                     && var3.getJcondCondition() instanceof IDOperation var11
                     && var11.getOperatorType().isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)
                     && var11.getOperand2() instanceof IDImm var13
                     && var13.isZero()
                     && var11.getOperand1() instanceof IDVar) {
                     if (this.pC != null) {
                        return false;
                     }

                     this.pC = var11.getOperand1().asVar();
                     long var16;
                     if (var11.getOperatorType() == JavaOperatorType.EQ) {
                        var16 = var3.getOffsetEnd();
                        this.oT = (long)var3.getBranchTarget();
                     } else {
                        var16 = var3.getBranchTarget();
                        this.oT = var3.getOffsetEnd();
                     }

                     var1 = bzr.this.cfg.getBlockAt(var16);
                     Assert.a(this.ld == 1);
                     continue;
                  }

                  return false;
               } else if (this.ld == 2) {
                  if (!var3.isThrow() || var3.getThrowExpression() != this.A) {
                     return false;
                  }

                  this.wS = var3;
                  if (this.gp == null && !this.sY) {
                     return true;
                  }

                  var1 = this.gp;
                  var2 = 0;
                  this.ld = 3;
               } else if (this.ld == 3) {
                  if (!var3.isStoreException()) {
                     if (var3.isThrow() && var3.getThrowExpression() == this.A) {
                        this.wS = var3;
                        return true;
                     }

                     return false;
                  }

                  this.kS = var3.getStoredExceptionVariable();
                  this.ld = 4;
               } else if (this.ld != 4) {
                  if (this.ld != 5) {
                     if (this.ld == 6) {
                        if (var3 == this.wS) {
                           return true;
                        }

                        return false;
                     }

                     return false;
                  }

                  if (!var3.isThrow() || var3.getThrowExpression() != this.A) {
                     return false;
                  }

                  this.wS = var3;
                  if (this.oT == null) {
                     return true;
                  }

                  var1 = bzr.this.cfg.getBlockAt(this.oT);
                  this.ld = 6;
               } else {
                  if (var3.isInvoke()
                     && var3.getInvokeData() instanceof IDCallInfo var5
                     && (var5.getInvokeType() == DInvokeType.VIRTUAL || var5.getInvokeType() == DInvokeType.INTERFACE)
                     && var5.getMethodSignature().equals("Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V")) {
                     IDExpression var6 = var5.getArgument(0);
                     IDExpression var7 = var5.getArgument(1);
                     if (var6 == this.A && var7 == this.kS) {
                        this.ld = 5;
                        continue;
                     }
                  }

                  return false;
               }
            }
         }

         return false;
      }
   }
}
