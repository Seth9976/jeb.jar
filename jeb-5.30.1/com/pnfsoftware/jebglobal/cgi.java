package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class cgi extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;
      IDTryData var2 = this.ctx.getExceptionData();
      if (var2.isEmpty()) {
         return 0;
      } else {
         int var3 = 0;

         for (int var4 = 0; var4 < this.cfg.size(); var4++) {
            BasicBlock var5 = this.cfg.get(var4);
            List var6 = var2.getBlockHandlers((int)var5.getBase());
            if (var6.size() > 1) {
               IDTypeInfoProvider var7 = this.ctx.getTypeInfoProvider();
               ArrayList var8 = new ArrayList();
               ArrayList var9 = new ArrayList();

               label368:
               for (IDExceptionHandler var11 : var6) {
                  String var12 = DUtil.getExceptionSignature(this.ctx, var11);
                  if (var12 != null) {
                     for (String var14 : var8) {
                        if (var7.isCompatible(var12, var14)) {
                           var9.add(var11);
                           continue label368;
                        }
                     }

                     var8.add(var12);
                  }
               }

               for (IDExceptionHandler var61 : var9) {
                  if (var2.unprotectBlock((int)var5.getBase(), var61.getAddress(), var61.getTypeIndex())) {
                     this.cfg.deleteIrregularEdge(var5, this.cfg.getBlockAt((long)var61.getAddress()));
                     var3++;
                  }
               }
            }
         }

         if (var3 > 0) {
            var1 += var3;
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         var3 = 0;
         HashMap var24 = new HashMap();

         label343:
         for (int var25 = 0; var25 < this.cfg.size(); var25++) {
            BasicBlock var30 = this.cfg.get(var25);
            if (var30.irrinsize() != 0) {
               for (BasicBlock var41 : var30.getIrregularInputBlocks()) {
                  Boolean var47 = (Boolean)var24.get(var41);
                  if (var47 == null) {
                     var47 = var41.canThrow();
                     var24.put(var41, var47);
                  }

                  if (var47) {
                     continue label343;
                  }
               }

               for (BasicBlock var42 : var30.getIrregularInputBlocks()) {
                  this.cfg.deleteIrregularEdges(var42, var30);
                  var2.unprotectBlock((int)var42.getAddress(), (int)var30.getAddress());
               }

               var3++;
            }
         }

         if (var3 > 0) {
            var1 += var3;
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         var3 = 0;

         for (BasicBlock var31 : this.cfg) {
            if (var31.irroutsize() == 1) {
               int var37 = (int)var31.getAddress();
               IDExceptionHandler var43 = (IDExceptionHandler)var2.getBlockHandlers(var37).get(0);
               if (var43.isCatchAll(this.ctx)) {
                  BasicBlock var48 = var31.getIrregularOutputBlock(0);
                  if (var48.size() == 1 && ((IDInstruction)var48.getLast()).isOpcode(DOpcodeType.IR_JUMP, DOpcodeType.IR_NOP)) {
                     BasicBlock var55 = var48.getOutputBlock(0);
                     IDInstruction var62 = (IDInstruction)var55.get(0);
                     if ((!var62.isJump() || var62.getBranchTarget() != (int)var48.getBase()) && this.cfg.reconnectIrregularEdge(var31, var48, var55) > 0) {
                        var43.setAddress((int)var55.getAddress());
                        var3++;
                     }
                  }
               }
            }
         }

         if (var3 > 0) {
            var1 += var3;
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         var3 = 0;

         for (BasicBlock var32 : this.cfg) {
            if (var32.irroutsize() > 0) {
               int var38 = (int)var32.getAddress();
               List var44 = var2.getBlockHandlers(var38);
               if (!var44.isEmpty()) {
                  IDExceptionHandler var49 = (IDExceptionHandler)var44.get(var44.size() - 1);
                  BasicBlock var56 = this.cfg.getBlockAt((long)var49.getAddress());
                  if (var56.size() == 2 && ((IDInstruction)var56.get(0)).isStoreException() && var56.irroutsize() == 0) {
                     IDExpression var63 = null;
                     IDInstruction var68 = (IDInstruction)var56.get(1);
                     if (var68.isThrow()) {
                        var63 = var68.getThrowExpression();
                     } else if (var68.isJump()) {
                        int var74 = var68.getBranchTarget();
                        BasicBlock var81 = this.cfg.getBlockAt((long)var74);
                        if (var81.irroutsize() == 0 && ((IDInstruction)var81.get(0)).isThrow()) {
                           var63 = ((IDInstruction)var81.get(0)).getThrowExpression();
                        }
                     }

                     IDVar var75 = ((IDInstruction)var56.get(0)).getStoredExceptionVariable();
                     if (var63 == var75) {
                        this.cfg.deleteIrregularEdge(var32, var56, -1);
                        var2.unprotectBlock(var38, var49.getAddress(), var49.getTypeIndex());
                        var3++;
                     }
                  }
               }
            }
         }

         if (var3 > 0) {
            var1 += var3;
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         var3 = 0;
         Iterator var28 = this.cfg.iterator();

         while (true) {
            BasicBlock var33;
            while (true) {
               if (!var28.hasNext()) {
                  if (var3 > 0) {
                     var1 += var3;
                     this.cleanGraph();
                     this.cfg.invalidateDataFlowAnalysis();
                  }

                  var3 = 0;

                  for (BasicBlock var34 : this.cfg) {
                     if (var34.irrinsize() > 0
                        && var34.insize() == 0
                        && var34.size() == 2
                        && ((IDInstruction)var34.get(0)).isStoreException()
                        && ((IDInstruction)var34.get(1)).isJcond()) {
                        List var40 = var2.getHandlers((int)var34.getBase());
                        if (var40.size() == 1) {
                           IDExceptionHandler var46 = (IDExceptionHandler)var40.get(0);
                           if (var46.isCatchAll(this.ctx)) {
                              BasicBlock var52 = var34.getOutputBlock(0);
                              if (var52.size() == 1 && ((IDInstruction)var52.get(0)).isThrow()) {
                                 IDVar var60 = ((IDInstruction)var34.get(0)).getStoredExceptionVariable();
                                 if (var60 == ((IDInstruction)var52.get(0)).getThrowExpression()
                                    && ((IDInstruction)var34.get(1)).getJcondCondition() instanceof IDCallInfo var73
                                    && var73.getMethodSignature().equals("Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z")
                                    && var73.getArgument(1) == var60
                                    && var73.getArgument(0) instanceof IDStaticField var87
                                    && var87.isTypeClass()) {
                                    String var89 = var87.getClassSignature();
                                    IDTypeInfoProvider var91 = this.ctx.getTypeInfoProvider();
                                    if (var91.isChildOf(var89, "Ljava/lang/Throwable;")) {
                                       var46.setTypeIndex(this.dex.getType(var89).getIndex());
                                       ((IDInstruction)var34.get(1)).setJcondCondition(this.g.createBoolean(true));
                                       var60.setType(this.tf.createType(var89), null, true);
                                       var3++;
                                    }
                                 }
                              } else {
                                 var52 = var34.getOutputBlock(1);
                                 if (var52.size() == 1 && ((IDInstruction)var52.get(0)).isThrow()) {
                                    IDVar var59 = ((IDInstruction)var34.get(0)).getStoredExceptionVariable();
                                    if (var59 == ((IDInstruction)var52.get(0)).getThrowExpression()
                                       && ((IDInstruction)var34.get(1)).getJcondCondition() instanceof IDOperation var72
                                       && var72.getOperatorType() == JavaOperatorType.LOG_NOT
                                       && var72.getOperand2() instanceof IDCallInfo var79
                                       && var79.getMethodSignature().equals("Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z")
                                       && var79.getArgument(1) == var59
                                       && var79.getArgument(0) instanceof IDStaticField var88
                                       && var88.isTypeClass()) {
                                       String var90 = var88.getClassSignature();
                                       IDTypeInfoProvider var92 = this.ctx.getTypeInfoProvider();
                                       if (var92.isChildOf(var90, "Ljava/lang/Throwable;")) {
                                          var46.setTypeIndex(this.dex.getType(var90).getIndex());
                                          ((IDInstruction)var34.get(1)).setJcondCondition(this.g.createBoolean(false));
                                          var59.setType(this.tf.createType(var90), null, true);
                                          var3++;
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (var3 > 0) {
                     var1 += var3;
                     this.cleanGraph();
                     this.cfg.invalidateDataFlowAnalysis();
                  }

                  return var1;
               }

               var33 = (BasicBlock)var28.next();
               IDInstruction var39;
               if (var33.irrinsize() > 0
                  && var33.irroutsize() > 0
                  && var33.outsize() == 0
                  && var33.size() == 2
                  && (var39 = (IDInstruction)var33.get(0)).isStoreException()
                  && ((IDInstruction)var33.get(1)).isThrow()
                  && ((IDInstruction)var33.get(1)).getThrowExpression() == var39.getStoredExceptionVariable()
                  && this.q(var33)) {
                  this.analyzeChains();
                  IDVar var45 = var39.getStoredExceptionVariable();
                  if (this.dfa.getDefUses(var39.getOffset(), var45.getId(), 2).size() < 2) {
                     break;
                  }

                  boolean var50 = false;
                  if (var33.irroutsize() == 1) {
                     BasicBlock var57 = var33.getIrregularOutputBlock(0);
                     if (((IDInstruction)var57.get(0)).isStoreException()) {
                        IDVar var64 = ((IDInstruction)var57.get(0)).getStoredExceptionVariable();
                        if (var64 != var45) {
                           var50 = true;
                           Collection var69 = this.dfa.getDefUses(var39.getOffset(), var45.getId());
                           LinkedHashSet var70 = new LinkedHashSet(var69);
                           var70.remove(((IDInstruction)var33.get(1)).getOffset());

                           for (long var82 : var70) {
                              if (!this.dfa.checkSingleDef(var82, var45.getId(), var33.getBase())
                                 || !this.dfa.checkSingleSource(var82, var64.getId(), var57.getBase())) {
                                 var50 = false;
                                 break;
                              }
                           }

                           if (var50) {
                              for (long var83 : var70) {
                                 ((IDInstruction)this.cfg.getInstruction(var83)).replaceUsedVariable(var45, var64);
                              }
                           }
                        }
                     }
                  }

                  if (var50) {
                     break;
                  }
               }
            }

            int var51 = (int)var33.getBase();
            List var58 = var2.getBlockHandlers(var51);
            var2.unprotectBlock(var51);
            this.cfg.deleteIrregularOutEdges(var33);

            for (BasicBlock var71 : var33.getIrregularInputBlocks()) {
               int var78 = (int)var71.getBase();
               var2.unprotectBlock(var78, var51);
               this.cfg.deleteIrregularEdges(var71, var33);
               List var84 = var2.getBlockHandlers(var78);

               label298:
               for (IDExceptionHandler var16 : var58) {
                  for (IDExceptionHandler var18 : var84) {
                     if (DUtil.canHandlerCatchException(this.ctx, var18, DUtil.getExceptionSignature(this.ctx, var16))) {
                        continue label298;
                     }
                  }

                  if (var2.protectBlock(var78, var16.getTypeIndex(), var16.getAddress(), -1)) {
                     this.cfg.addIrregularEdge(var71, this.cfg.getBlockAt((long)var16.getAddress()), -1);
                  }
               }
            }

            var3++;
         }
      }
   }

   private boolean q(BasicBlock var1) {
      Assert.a(var1.irrinsize() > 0);
      IDTryData var2 = this.ctx.getExceptionData();
      int var3 = (int)var1.getBase();

      for (BasicBlock var5 : var1.getIrregularInputs()) {
         List var6 = var2.getBlockHandlers((int)var5.getBase());
         if (!((IDExceptionHandler)var6.get(var6.size() - 1)).isCatchAll(this.ctx) || ((IDExceptionHandler)var6.get(var6.size() - 1)).getAddress() != var3) {
            return false;
         }

         for (IDExceptionHandler var8 : var6.subList(0, var6.size() - 1)) {
            if (var8.getAddress() == var3) {
               return false;
            }
         }
      }

      return true;
   }
}
