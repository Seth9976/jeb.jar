package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class cei extends AbstractDOptimizer {
   public static final String q = "Ljeb/synthetic/TWR;->safeClose$NT(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V";
   public static final String RF = "Ljeb/synthetic/TWR;->autoClose(Ljava/lang/AutoCloseable;)V";
   public static final String xK = "Ljeb/synthetic/TWR;->declareResource(Ljava/lang/AutoCloseable;)V";
   public static final String Dw = "Ljeb/synthetic/TWR;->useResource$NT(Ljava/lang/AutoCloseable;)V";
   public static final String Uv = "Ljeb/synthetic/TWR;->moot$NT()V";
   public static final String oW = "Ljeb/synthetic/TWR;->getResource$NT(Ljava/lang/Object;)Ljava/lang/AutoCloseable;";
   int gO;

   public cei(int var1) {
      super(DOptimizerType.UNSAFE);
   }

   public cei() {
      this(1);
   }

   public void q(int var1) {
      this.gO = var1;
   }

   public int q() {
      return this.gO;
   }

   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1 != null && !var1.isEmpty()) {
         int var2 = 0;

         for (int var3 = 0; var3 < this.cfg.size(); var3++) {
            BasicBlock var4 = this.cfg.get(var3);
            if (cei.eo.q(var4) && this.q(var4)) {
               var2++;
            }
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
            this.cleanGraph();
         }

         return var2;
      } else {
         return 0;
      }
   }

   boolean q(BasicBlock var1) {
      cei.eo var2 = new cei.eo(var1);
      if (!var2.q()) {
         return false;
      } else {
         BasicBlock var3 = var2.q;
         this.analyzeChains();
         int var4 = var2.xK.getId();
         Long var5 = this.dfa.checkSingleDef(var2.Dw, var4);
         if (var5 == null) {
            return false;
         } else {
            BasicBlock var6 = null;
            long var7 = var5;
            if (var7 != -1L) {
               var6 = this.cfg.getBlockContaining(var7);
               if (var6 == null) {
                  return false;
               }

               if (var6.outsize() != 1) {
                  return false;
               }
            }

            ArrayList var9 = new ArrayList();
            HashMap var10 = new HashMap();
            long[] var11 = new long[1];
            Collection var12;
            if (var7 == -1L) {
               var12 = this.dfa.getInputMap(var4);
            } else {
               var12 = this.dfa.getDefUses(var7, var4);
            }

            if (var12.isEmpty()) {
               return false;
            } else {
               for (long var14 : var12) {
                  if (var14 != var2.Dw) {
                     IDInstruction var16 = (IDInstruction)this.cfg.getInstruction(var14);
                     if (var16 != null) {
                        if (this.q(var16, var2.xK)) {
                           BasicBlock var17 = this.cfg.getBlockContaining(var16.getOffset());
                           if (!CFGUtil.canReach(var17, var3, true, var6 == null ? null : Sets.newHashSet(var6))) {
                              var9.add(var16);
                           }
                        } else if (this.q(var16, var2.xK, var11)) {
                           BasicBlock var38 = this.cfg.getBlockFor(var16);
                           BasicBlock var18 = this.cfg.getBlockAt(var11[0]);
                           var10.put(var38, var18);
                        }
                     }
                  }
               }

               if (var9.isEmpty()) {
                  return false;
               } else {
                  for (IDInstruction var33 : var9) {
                     BasicBlock var15 = this.cfg.getBlockContaining(var33.getOffset());
                     int var36 = var15.getIndexOfInstruction(var33.getOffset());
                     Assert.a(var36 != -1);
                     if (var36 != 0) {
                        return false;
                     }

                     if (var15.irroutsize() != 0 && var15.size() != 1) {
                        for (int var39 = 1; var39 < var15.size(); var39++) {
                           if (((IDInstruction)var15.get(var39)).canThrow()) {
                              return false;
                           }
                        }
                     }
                  }

                  int var32 = -1;
                  if (var6 != null) {
                     var32 = var6.getIndexOfInstruction(var7);
                     Assert.a(var32 != -1);
                     if (var32 + 1 != var6.size()) {
                        for (int var34 = var32 + 1; var34 < var6.size(); var34++) {
                           if (((IDInstruction)var6.get(var34)).canThrow()) {
                              return false;
                           }
                        }
                     }

                     if (var6.getIrregularOutputs().contains(var3)) {
                        return false;
                     }
                  }

                  long var35;
                  BasicBlock var37;
                  if (var6 == null) {
                     var35 = (Long)var12.iterator().next();
                     var37 = this.cfg.getBlockContaining(var35);
                  } else {
                     var35 = -1L;
                     var37 = var6.getOutputBlock(0);
                  }

                  HashSet var40 = new HashSet();
                  var40.add(var2.q.getBase());
                  var9.forEach(var2x -> var40.add(this.cfg.getBlockFor(var2x).getBase()));
                  cej var41 = new cej(this, this.cfg, var37.getBase(), var40, var10);
                  var41.q(true);
                  if (!var41.xK()) {
                     return false;
                  } else {
                     ArrayList var19 = new ArrayList();

                     for (long var21 : var41.q()) {
                        BasicBlock var23 = this.cfg.getBlockAt(var21);
                        if (var6 != null && var23 == var6) {
                           return false;
                        }

                        if (!var23.getIrregularOutputs().contains(var2.q)) {
                           if (var23.canThrow()) {
                              return false;
                           }

                           var19.add(var23);
                        }
                     }

                     if (!this.q(var3, var6)) {
                        return false;
                     } else {
                        ArrayList var42 = new ArrayList();
                        var41.q().forEach(var2x -> var42.add(this.cfg.getBlockAt(var2x)));
                        if (var6 == null) {
                           Couple var43 = this.cfg.getInstructionLocation(var35);
                           BasicBlock var22 = (BasicBlock)var43.getFirst();
                           byte var51 = 0;
                           IDInstruction var24 = (IDInstruction)var22.get(var51);
                           long var25 = var24.getOffset();
                           DUtil.modifyInstructionSize(this.ctx, var24, 2);
                           var24.adjustSize(-1);
                           var24.setOffset(var25 + 1L);
                           IDexMethod var27 = this.dex.addMethod("Ljeb/synthetic/TWR;", "getResource$NT", "(Ljava/lang/Object;)Ljava/lang/AutoCloseable;");
                           IDCallInfo var28 = this.ctx.createCallInfo(DInvokeType.STATIC, var27.getIndex(), Arrays.asList(var2.xK));
                           IDInstruction var29 = this.ctx.createAssign(var2.xK, var28).withOffset(var25);
                           var22.add(var51, var29);
                           BasicBlock var30 = DUtil.splitBlock(this.ctx, var22, 1);
                           var42.add(var30);
                           var6 = var22;
                           var32 = var51;
                        }

                        for (BasicBlock var48 : var42) {
                           if (var48 != var6) {
                              this.q(var48, var2.xK);
                           }
                        }

                        for (IDInstruction var49 : var9) {
                           var49.transformToNop();
                        }

                        if (var32 + 1 != var6.size()) {
                           BasicBlock var46 = DUtil.splitBlock(this.ctx, var6, var32 + 1);
                           this.cfg.deleteIrregularOutEdges(var46);
                           this.ctx.getExceptionData().unprotectBlock((int)var46.getBase());
                           this.q(var46, var2.xK);
                        }

                        IDInstruction var47 = (IDInstruction)var6.getLast();
                        DUtil.modifyInstructionSize(this.ctx, var47, 2);
                        var47.adjustSize(-1);
                        IDexMethod var50 = this.dex.addMethod("Ljeb/synthetic/TWR;", "declareResource", "(Ljava/lang/AutoCloseable;)V");
                        IDCallInfo var52 = this.ctx.createCallInfo(DInvokeType.STATIC, var50.getIndex(), Arrays.asList(var2.xK));
                        IDInstruction var53 = this.ctx.createInvoke(var52);
                        var53.setOffset(var47.getOffsetEnd());
                        var53.setPhysicalMethodIndex(var47.getPhysicalMethodIndex());
                        var53.setPhysicalOffset(var47.getPhysicalOffset());
                        var6.add(var53);
                        return true;
                     }
                  }
               }
            }
         }
      }
   }

   boolean q(IDInstruction var1, IDVar var2) {
      if (var1.isInvoke() && var1.getInvokeData() instanceof IDCallInfo) {
         IDCallInfo var3 = (IDCallInfo)var1.getInvokeData();
         if ((var3.getInvokeType() == DInvokeType.VIRTUAL || var3.getInvokeType() == DInvokeType.INTERFACE) && var3.getMethodSignature().endsWith("->close()V")
            )
          {
            return var3.getArgument(0) == var2;
         }
      }

      return false;
   }

   boolean q(IDInstruction var1, IDVar var2, long[] var3) {
      if (var1.isJcond()
         && var1.getJcondCondition() instanceof IDOperation var5
         && var5.getOperatorType().isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)
         && var5.getOperand1() == var2
         && var5.getOperand2() instanceof IDImm var6
         && var6.isZero()) {
         if (var3 != null) {
            var3[0] = var5.getOperatorType() == JavaOperatorType.EQ ? var1.getOffsetEnd() : var1.getBranchTarget();
         }

         return true;
      } else {
         return false;
      }
   }

   boolean q(BasicBlock var1, BasicBlock var2) {
      IDTryData var3 = this.ctx.getExceptionData();
      List var4 = var3.getBlockHandlers((int)var1.getBase());
      List var5 = var1.getIrregularOutputBlocks();
      if (var4.size() != var5.size()) {
         return false;
      } else {
         int var6 = var4.size();
         if (var6 == 0) {
            if (var2 != null) {
               if (var2.irroutsize() != 0) {
                  return false;
               }

               if (var3.protectBlock((int)var2.getBase(), -1, (int)var1.getBase(), -1)) {
                  this.cfg.addIrregularEdge(var2, var1, -1);
               }
            }

            IDexMethod var13 = this.dex.addMethod("Ljeb/synthetic/TWR;", "moot$NT", "()V");
            IDCallInfo var16 = this.ctx.createCallInfo(DInvokeType.STATIC, var13.getIndex(), Collections.emptyList());
            IDInstruction var19 = this.ctx.createInvoke(var16);
            var19.copyBaseFields((IDInstruction)var1.get(1));
            var1.set(1, var19);
            return true;
         } else {
            for (BasicBlock var8 : var1.getIrregularInputs()) {
               List var9 = var8.getIrregularOutputs();
               if (var9.indexOf(var1) != var9.size() - 1) {
                  return false;
               }
            }

            ArrayList var12 = new ArrayList(var1.getIrregularInputs());

            for (BasicBlock var17 : var12) {
               if (!this.cfg.deleteIrregularEdge(var17, var1)) {
                  throw new RuntimeException("deleteIrregularEdge failed");
               }

               if (!var3.unprotectBlock((int)var17.getBase(), (int)var1.getBase())) {
                  throw new RuntimeException("unprotectBlock failed");
               }
            }

            this.cfg.deleteIrregularOutEdges(var1);

            for (BasicBlock var18 : var12) {
               for (int var10 = 0; var10 < var6; var10++) {
                  IDExceptionHandler var11 = (IDExceptionHandler)var4.get(var10);
                  if (var3.protectBlock((int)var18.getBase(), var11.getTypeIndex(), var11.getAddress(), -1)) {
                     this.cfg.addIrregularEdge(var18, (BasicBlock)var5.get(var10), -1);
                  }
               }
            }

            return true;
         }
      }
   }

   void q(BasicBlock var1, IDVar var2) {
      if (var1.irroutsize() != 0) {
         byte var3 = 0;
         IDInstruction var4 = (IDInstruction)var1.get(0);
         if (var4.isStoreException()) {
            if (var1.size() == 1) {
               return;
            }

            var3 = 1;
            var4 = (IDInstruction)var1.get(var3);
         }

         DUtil.modifyInstructionSize(this.ctx, var4, 2);
         long var5 = var4.getOffset();
         var4.adjustSize(-1);
         var4.setOffset(var5 + 1L);
         IDexMethod var7 = this.dex.addMethod("Ljeb/synthetic/TWR;", "useResource$NT", "(Ljava/lang/AutoCloseable;)V");
         IDCallInfo var8 = this.ctx.createCallInfo(DInvokeType.STATIC, var7.getIndex(), Arrays.asList(var2));
         IDInstruction var9 = this.ctx.createInvoke(var8);
         var9.setOffset(var5);
         var9.setPhysicalMethodIndex(var4.getPhysicalMethodIndex());
         var9.setPhysicalOffset(var4.getPhysicalOffset());
         var1.add(var3, var9);
      }
   }

   static class eo {
      BasicBlock q;
      IDVar RF;
      IDVar xK;
      long Dw;

      static boolean q(BasicBlock var0) {
         return var0.size() == 3
            && var0.insize() == 0
            && var0.outsize() == 0
            && var0.irrinsize() > 0
            && DUtil.checkBlock(var0, DOpcodeType.IR_STORE_EXCEPTION, DOpcodeType.IR_INVOKE, DOpcodeType.IR_THROW);
      }

      eo(BasicBlock var1) {
         this.q = var1;
      }

      boolean q() {
         BasicBlock var1 = this.q;
         IDVar var2 = ((IDInstruction)var1.get(0)).getStoredExceptionVariable();
         if (((IDInstruction)var1.get(2)).getThrowExpression() == var2
            && ((IDInstruction)var1.get(1)).getInvokeData() instanceof IDCallInfo var4
            && var4.getInvokeType() == DInvokeType.STATIC
            && var4.getMethodSignature().equals("Ljeb/synthetic/TWR;->safeClose$NT(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V")
            && var4.getArgument(1) == var2
            && var4.getArgument(0) instanceof IDVar) {
            this.Dw = ((IDInstruction)var1.get(1)).getOffset();
            this.xK = (IDVar)var4.getArgument(0);
            this.RF = var2;
            return true;
         } else {
            return false;
         }
      }
   }
}
