package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class bzn extends AbstractDOptimizer {
   int pC;

   public bzn(int var1) {
      super(DOptimizerType.UNSAFE);
   }

   public bzn() {
      this(1);
   }

   public void pC(int var1) {
      this.pC = var1;
   }

   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         HashSet var2 = new HashSet();

         for (BasicBlock var4 : this.cfg) {
            IDInstruction var5 = (IDInstruction)var4.getLast();
            if (var5.isThrow()) {
               IDExpression var6 = var5.getThrowExpression();
               if (var6 instanceof IDVar) {
                  var2.add(var6.asVar().getId());
               }
            }
         }

         if (var2.isEmpty()) {
            return 0;
         } else {
            int var11 = 0;

            for (int var12 = 0; var12 < this.cfg.size(); var12++) {
               BasicBlock var13 = this.cfg.get(var12);
               IDInstruction var14 = (IDInstruction)var13.get(0);
               if (var14.isStoreException() && var1.getHandledExceptionTypesAt((int)var13.getBase()).contains(-1)) {
                  IDVar var7 = var14.getStoredExceptionVariable();
                  bzn.Sv var8 = null;
                  if (((IDInstruction)var13.getLast()).isThrow() && ((IDInstruction)var13.getLast()).getThrowExpression() == var7) {
                     boolean var9 = false;

                     for (int var10 = 1; var10 < var13.size() - 1; var10++) {
                        if (!((IDInstruction)var13.get(var10)).isNop()) {
                           var9 = true;
                           break;
                        }
                     }

                     if (var9) {
                        var8 = new bzn.Sv(var13.getBase());
                     }
                  } else if (var2.contains(var7.getId())) {
                     var8 = this.pC(var13, var7);
                  }

                  if (var8 != null) {
                     bzn.K var15 = new bzn.K(var8, this.ctx);
                     if (var15.pC()) {
                        this.assignLocalFields(this.ctx);
                        var1 = this.ctx.getExceptionData();
                        this.cfg.invalidateDataFlowAnalysis();
                        var11++;
                     }
                  }
               }
            }

            return var11;
         }
      }
   }

   private bzn.Sv pC(BasicBlock var1, IDVar var2) {
      bru var3 = new bru(this.cfg, var1.getBase(), Collections.emptySet());
      if (!var3.kS()) {
         return null;
      } else {
         Set var4 = var3.pC();
         Long var5 = null;

         for (long var7 : var4) {
            BasicBlock var9 = this.cfg.getBlockAt(var7);
            IDInstruction var10 = (IDInstruction)var9.getLast();
            if (var10.isThrow() && var10.getThrowExpression() == var2) {
               this.analyzeChains();
               if (!this.dfa.checkSingleDef(var10.getOffset(), var2.getId(), var1.getBase())) {
                  return null;
               }

               if (var5 != null) {
                  return null;
               }

               var5 = var7;
            }

            var4.add(var7);
         }

         if (var5 == null) {
            return null;
         } else {
            boolean var13 = false;

            for (long var8 : var4) {
               for (IDInstruction var12 : this.cfg.getBlockAt(var8)) {
                  if (!var12.getOpcode().isAnyOf(DOpcodeType.IR_STORE_EXCEPTION, DOpcodeType.IR_THROW, DOpcodeType.IR_JUMP, DOpcodeType.IR_NOP)) {
                     var13 = true;
                     return !var13 ? null : new bzn.Sv(var1.getBase(), var4, var5);
                  }
               }
            }

            return !var13 ? null : new bzn.Sv(var1.getBase(), var4, var5);
         }
      }
   }

   static class Av implements bpv.Av {
      IDVar pC;

      Av(IDVar var1) {
         this.pC = var1;
      }

      @Override
      public void customize(IDInstruction var1, List var2, List var3) {
         CFG var4 = var1.getContext().getCfg();
         int var5 = -1;
         int var6 = -1;
         int var7 = 0;

         for (IDInstruction var9 : var4.instructions()) {
            if (var9.isInvoke() && var9.getInvokeData() instanceof IDCallInfo) {
               IDCallInfo var10 = (IDCallInfo)var9.getInvokeData();
               String var11 = var10.getMethodSignature();
               if (var11.equals("Ljeb/synthetic/FIN;->finallyCodeBegin$NT(I)V") && var10.getArgument(0) == this.pC) {
                  var5 = var7 + 1;
               } else if (var11.equals("Ljeb/synthetic/FIN;->finallyCodeEnd$NT(I)V") && var10.getArgument(0) == this.pC) {
                  var6 = var7;
               }
            }

            var7++;
         }

         if (var5 >= 0 && var6 > var5) {
            LinkedHashSet var15 = new LinkedHashSet();
            LinkedHashSet var16 = new LinkedHashSet();
            ArrayList var17 = new ArrayList();
            ArrayList var18 = new ArrayList();
            var7 = 0;

            for (IDInstruction var13 : var4.instructions()) {
               if (var7 >= var5) {
                  if (var7 >= var6) {
                     break;
                  }

                  var13.getDefUse(var17, var18, null);
                  var15.addAll(var17);
                  var16.addAll(var18);
               }

               var7++;
            }

            var2.addAll(var15);
            var3.addAll(var16);
         }
      }
   }

   private static class K {
      final bzn.Sv pC;
      final int A;
      IDMethodContext kS;
      CFG wS;
      IDTryData UT;
      BasicBlock E;
      BasicBlock sY;
      Set ys;
      private int ld = 2;
      private final Map gp = new HashMap();
      private final Map oT = new HashMap();

      K(bzn.Sv var1, IDMethodContext var2) {
         Assert.a(var1 != null);
         this.pC = var1;
         this.pC(var2);
         this.A = ((IDInstruction)this.E.getLast()).isThrow() ? this.E.size() - 2 : this.E.size() - 1;
      }

      private void pC(IDMethodContext var1) {
         Assert.a(var1 != null);
         this.kS = var1;
         this.wS = var1.getCfg();
         this.UT = var1.getExceptionData();
         this.E = this.wS.getBlockAt(this.pC.pC);
         this.sY = this.wS.getBlockAt(this.pC.kS);
         this.ys = new HashSet(this.pC.A.size());
         this.pC.A.forEach(var1x -> this.ys.add(this.wS.getBlockAt(var1x)));
      }

      boolean pC() {
         HashSet var1 = new HashSet();

         for (BasicBlock var3 : this.E.getIrregularInputBlocks()) {
            BasicBlock var4 = this.pC(var3);
            if (var4 != null && var4 != var3 && var4 != this.E && !var4.getIrregularOutputs().contains(this.E) && this.pC(var3, var4)) {
               return true;
            }

            ArrayDeque var5 = new ArrayDeque();
            var5.add(var3.getBase());

            while (!var5.isEmpty()) {
               long var6 = (Long)var5.remove();
               if (var1.add(var6)) {
                  BasicBlock var8 = this.wS.getBlockAt(var6);

                  for (BasicBlock var10 : var8.getAllInputs()) {
                     var4 = this.pC(var10);
                     if (var4 != null && var4 != var10 && var4 != this.E && !var4.getIrregularOutputs().contains(this.E) && this.pC(var10, var4)) {
                        return true;
                     }
                  }
               }
            }
         }

         return false;
      }

      private BasicBlock pC(BasicBlock var1) {
         Wrapper var2 = (Wrapper)this.gp.get(var1);
         if (var2 != null) {
            return (BasicBlock)var2.get();
         } else {
            BasicBlock var3 = null;
            if (this.ld == 0) {
               HashSet var4 = new HashSet();
               ArrayDeque var5 = new ArrayDeque();
               var5.add(var1.getBase());

               label156:
               while (!var5.isEmpty()) {
                  long var6 = (Long)var5.remove();
                  if (var4.add(var6)) {
                     BasicBlock var8 = this.wS.getBlockAt(var6);

                     for (BasicBlock var10 : var8.getOutputs()) {
                        if (this.A(var10)) {
                           var3 = var10;
                           break label156;
                        }

                        var5.add(var10.getBase());
                     }

                     for (BasicBlock var27 : var8.getIrregularOutputs()) {
                        if (var27 != this.E) {
                           if (this.A(var27)) {
                              var3 = var27;
                              break label156;
                           }

                           var5.add(var27.getBase());
                        }
                     }
                  }
               }
            } else if (this.ld == 1) {
               HashSet var11 = new HashSet();
               ArrayDeque var13 = new ArrayDeque();
               var13.add(var1.getBase());

               label129:
               while (!var13.isEmpty()) {
                  long var16 = (Long)var13.remove();
                  if (var11.add(var16)) {
                     BasicBlock var20 = this.wS.getBlockAt(var16);

                     for (BasicBlock var28 : var20.getOutputs()) {
                        if (this.A(var28)) {
                           var3 = var28;
                           break label129;
                        }

                        var13.add(var28.getBase());
                     }
                  }
               }

               if (var3 == null) {
                  var11.clear();
                  var13.clear();
                  var13.add(var1.getBase());

                  label112:
                  while (!var13.isEmpty()) {
                     long var17 = (Long)var13.remove();
                     if (var11.add(var17)) {
                        BasicBlock var21 = this.wS.getBlockAt(var17);

                        for (BasicBlock var29 : var21.getIrregularOutputs()) {
                           if (var29 != this.E) {
                              if (this.A(var29)) {
                                 var3 = var29;
                                 break label112;
                              }

                              var13.add(var29.getBase());
                           }
                        }
                     }
                  }
               }
            } else if (this.ld == 2) {
               HashSet var12 = new HashSet();
               ArrayDeque var14 = new ArrayDeque();
               var14.add(var1.getBase());
               ArrayDeque var18 = new ArrayDeque();

               while (!var14.isEmpty()) {
                  long var7 = (Long)var14.remove();
                  if (var12.add(var7)) {
                     BasicBlock var25 = this.wS.getBlockAt(var7);
                     if (var25 != this.E && var25 != var1 && this.A(var25)) {
                        var3 = var25;
                        break;
                     }

                     var14.addAll(var25.getOutputOffsets());
                     var18.addAll(var25.getIrregularOutputOffsets());
                  }
               }

               if (var3 == null) {
                  var14 = var18;

                  while (!var14.isEmpty()) {
                     long var19 = (Long)var14.remove();
                     if (var12.add(var19)) {
                        BasicBlock var26 = this.wS.getBlockAt(var19);
                        if (var26 != this.E && var26 != var1 && this.A(var26)) {
                           var3 = var26;
                           break;
                        }

                        var14.addAll(var26.getOutputOffsets());
                        var14.addAll(var26.getIrregularOutputOffsets());
                     }
                  }
               }
            }

            this.gp.put(var1, Wrapper.wrap(var3));
            return var3;
         }
      }

      private boolean A(BasicBlock var1) {
         return this.kS(var1) != null;
      }

      private bry.Av kS(BasicBlock var1) {
         Wrapper var2 = (Wrapper)this.oT.get(var1);
         if (var2 == null) {
            bry.Av var3 = this.wS(var1);
            var2 = Wrapper.wrap(var3);
            this.oT.put(var1, var2);
         }

         return (bry.Av)var2.get();
      }

      private boolean pC(BasicBlock var1, BasicBlock var2) {
         boolean var3 = true;

         for (IDInstruction var5 : var1) {
            if (var5.isInvoke()
               && var5.getInvokeData() instanceof IDCallInfo
               && var5.getInvokeData().asCallInfo().getMethodSignature().equals("Ljeb/synthetic/TWR;->declareResource(Ljava/lang/AutoCloseable;)V")) {
               var3 = false;
               break;
            }
         }

         if (var3 && !DUtil.isProtectedByCatchAll(this.kS, var1) && var1.canThrow()) {
            var3 = false;
         }

         HashSet var10 = new HashSet();
         if (this.pC.pC()) {
            for (BasicBlock var6 : this.wS) {
               if (var6 != var2 && var6 != this.E && var6.alloutsize() == 0 && this.pC(var6, var6.size() - 1 - this.A) != null) {
                  var10.add(var6.getBase());
               }
            }
         }

         bzo var12 = new bzo(this, this.wS, var1.getBase(), var2.getBase());
         var12.pC(var3);
         var12.A(true);
         var12.pC(Sets.newHashSet(this.E.getBase()));
         var12.A(var10);
         if (!var12.kS()) {
            return false;
         } else if (!var12.pC().contains(this.E.getBase())) {
            return false;
         } else {
            ArrayList var13 = new ArrayList();

            for (long var8 : var12.A()) {
               if (var8 != var2.getBase() && var8 != this.E.getBase()) {
                  var13.add(var8);
               }
            }

            return this.pC(var12.pC(), var1, var2, var13, var3);
         }
      }

      private boolean pC(Collection var1, BasicBlock var2, BasicBlock var3, List var4, boolean var5) {
         ArrayList var6 = new ArrayList();
         var6.add(this.E);

         for (long var8 : var1) {
            BasicBlock var10 = this.wS.getBlockAt(var8);
            if (var10 != this.E && var10 != var3 && !var4.contains(var10.getBase()) && var10.outsize() == 0) {
               if (var10.irroutsize() == 0) {
                  Assert.a(((IDInstruction)var10.getLast()).isReturnOrThrow());
                  return false;
               }

               for (BasicBlock var12 : var10.getIrregularOutputs()) {
                  if (!var1.contains(var12.getBase())) {
                     return false;
                  }
               }

               if (!this.UT(var10)) {
                  return false;
               }
            }
         }

         var3 = this.pC(var3, var4);
         boolean var17 = false;
         ArrayList var18 = new ArrayList();
         Iterator var9 = var1.iterator();

         while (true) {
            if (!var9.hasNext()) {
               for (BasicBlock var22 : this.E.getIrregularInputs()) {
                  if (!var1.contains(var22.getBase())) {
                     return false;
                  }
               }

               if (!var4.isEmpty() && !this.pC.pC()) {
                  return false;
               }

               IDMethodContext var20 = this.kS;
               this.pC(this.kS.copy());

               try {
                  if (this.pC(var1, var2.getBase(), var3.getBase(), var4, var17)) {
                     DUtil.cleanGraph(this.kS);
                     this.kS.verify();
                     var20.load(this.kS);
                     return true;
                  }
               } catch (Exception var15) {
                  AbstractDOptimizer.logger.catchingSilent(var15);
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("try-fin-rebuilder failed", var15), var20.getMethodSignature());
               }

               this.pC(var20);
               return false;
            }

            long var21 = (Long)var9.next();
            BasicBlock var23 = this.wS.getBlockAt(var21);
            if (var23 != this.E && var23 != var3 && !var4.contains(var21)) {
               if (!var23.canThrow()) {
                  var18.add(var23.getBase());
               } else if (!var23.getIrregularOutputs().contains(this.E)) {
                  if (var23 != var2 || var5 || !((IDInstruction)var2.getLast()).isJcond()) {
                     if (!DUtil.isProtectedByCatchAll(this.kS, var23)) {
                        break;
                     }

                     List var13 = this.UT.getBlockHandlers((int)var23.getBase());
                     IDExceptionHandler var14 = (IDExceptionHandler)var13.get(var13.size() - 1);
                     if (!var14.isCatchAll(this.kS) || !var1.contains((long)var14.getAddress())) {
                        break;
                     }
                  } else {
                     var17 = true;
                  }
               }
            }
         }

         return false;
      }

      private boolean pC(Collection var1, long var2, long var4, List var6, boolean var7) {
         BasicBlock var8 = this.wS.getBlockAt(var4);
         BasicBlock var9 = this.wS.getBlockAt(var2);
         ArrayList var10 = new ArrayList(var1.size());

         for (long var12 : var1) {
            var10.add(this.wS.getBlockAt(var12));
         }

         ArrayList var29 = new ArrayList(var6.size());

         for (long var13 : var6) {
            BasicBlock var15 = this.wS.getBlockAt(var13);
            bry.Av var16 = this.wS(var15);
            Assert.a(var16 != null);
            if (var16.A >= 1) {
               var15 = DUtil.splitBlock(this.kS, var15, var16.A);
               var10.add(var15);
            }

            var29.add(var15);
         }

         bry.Av var31 = this.wS(var8);
         Assert.a(var31 != null);
         if (var31.A >= 1) {
            var8 = DUtil.splitBlock(this.kS, var8, var31.A);
            var10.add(var8);
         }

         if (var7) {
            var10.remove(var9);
            var1.remove(var9.getBase());
            IDInstruction var34 = (IDInstruction)var9.getLast();
            if (var34.canThrow()) {
               DUtil.modifyInstructionSize(this.kS, var34, 2);
               long var14 = var34.getOffset();
               var34.adjustSize(-1);
               var34.setOffset(var14 + 1L);
               IDVar var39 = this.kS.createVirtualVar(this.kS.getTypeFactory().getBoolean());
               IDInstruction var17 = this.kS.createAssign(var39, var34.getJcondCondition()).withOffset(var14);
               var34.setJcondCondition(var39);
               var9.add(var9.size() - 1, var17);
            }

            BasicBlock var36 = DUtil.splitBlock(this.kS, var9, var9.size() - 1);
            if (this.UT.protectBlock((int)var36.getBase(), -1, (int)this.E.getBase(), -1)) {
               this.wS.addIrregularEdge(var36, this.E, -1);
            }

            var10.add(var36);
            var1.add(var36.getBase());
            var9 = var36;
         }

         IDVar var35 = this.kS.createVirtualVar(this.kS.getTypeFactory().getInt());
         IDexMethod var37 = this.kS.getDex().addMethod("Ljeb/synthetic/FIN;", "finallyExec$NT", "(I)V");
         bzn.Av var38 = new bzn.Av(var35);

         for (BasicBlock var43 : var29) {
            var31 = this.wS(var43);
            Assert.a(var31 != null);
            int var18 = var31.A;
            if (this.pC.pC()) {
               for (int var58 = 0; var58 < this.A; var58++) {
                  IDInstruction var20 = (IDInstruction)var43.get(var18);
                  var20.transformToNop();
                  if (var58 == 0) {
                     IDCallInfo var67 = this.kS.createCallInfo(DInvokeType.STATIC, var37.getIndex(), Arrays.asList(var35));
                     IDInstruction var70 = this.kS.createInvoke(var67);
                     var70.copyBaseFields(var20);
                     ((bpv)var70).pC(var38);
                     var43.set(var18, var70);
                  }

                  var18++;
               }
            } else {
               Assert.fail("TBI");
               IDInstruction var19 = (IDInstruction)var43.get(var18);
               DUtil.modifyInstructionSize(this.kS, var19, 2);
               DUtil.splitBlock(this.kS, var43, var18 + 1);
               this.wS.deleteOutEdges(var43);
               IDCallInfo var21 = this.kS.createCallInfo(DInvokeType.STATIC, var37.getIndex(), Arrays.asList(var35));
               IDInstruction var22 = this.kS.createInvoke(var21).withOffset(var19.getOffset()).withSize(var19.getSize() - 1);
               ((bpv)var22).pC(var38);
               var43.set(var18, var22);
               IDInstruction var23 = (IDInstruction)var31.kS.get(var31.wS);
               IDInstruction var24 = this.kS.createJump((int)var23.getOffset()).withOffset(var19.getOffset() + 1L).withSize(1);
               var43.add(var18 + 1, var24);
            }
         }

         for (BasicBlock var44 : this.E.getIrregularInputs()) {
            if (var10.contains(var44)) {
               IDInstruction var49 = (IDInstruction)var44.getLast();
               if (var49.isThrow() && !bpl.pC(var44)) {
                  DUtil.modifyInstructionSize(this.kS, var49, 2);
                  var49.adjustSize(-1);
                  long var59 = var49.getOffset();
                  var49.setOffset(var59 + 1L);
                  IDCallInfo var68 = this.kS.createCallInfo(DInvokeType.STATIC, var37.getIndex(), Arrays.asList(var35));
                  IDInstruction var71 = this.kS.createInvoke(var68).withOffset(var59).withSize(1);
                  var44.add(var44.size() - 1, var71);
               }
            }
         }

         var31 = this.wS(var8);
         Assert.a(var31 != null);
         brd var42 = new brd(var31.kS, var31.wS);
         if (this.pC.pC()) {
            Assert.a(this.A >= 1);
            int var45 = 1;

            for (int var50 = 0; var50 < this.A; var50++) {
               IDInstruction var60 = (IDInstruction)this.E.get(var45);
               var60.transformToNop();
               var45++;
            }
         } else {
            Assert.a(this.A >= 0);
            if (this.E.size() == 1) {
               IDInstruction var46 = (IDInstruction)this.E.get(0);
               Assert.a(var46.isStoreException());
               DUtil.modifyInstructionSize(this.kS, var46, 2);
               var46.adjustSize(-1);
               IDInstruction var51 = this.kS.createNop().withOffset(var46.getOffsetEnd());
               this.E.add(var51);
            }

            bpl.pC(this.sY, this.E);
            IDInstruction var47 = (IDInstruction)this.E.getLast();
            IDInstruction var52 = ((IDInstruction)this.sY.getLast()).duplicateWithOffsetAndSize(var47.getOffset(), var47.getSize());

            for (int var61 = 1; var61 < this.E.size(); var61++) {
               ((IDInstruction)this.E.get(var61)).transformToNop();
            }

            this.wS.deleteOutEdges(this.E);
            this.E.set(this.E.size() - 1, var52);
         }

         ArrayList var48 = new ArrayList();

         for (long var62 : CollectionUtil.intersect(var9.getInputOffsets(), var1)) {
            var48.add(this.wS.getBlockAt(var62));
         }

         IDInstruction var54 = (IDInstruction)var9.get(0);
         DUtil.modifyInstructionSize(this.kS, var54, 2);
         long var63 = var54.getOffset();
         var54.setOffset(var63 + 1L);
         var54.adjustSize(-1);
         IDexMethod var69 = this.kS.getDex().addMethod("Ljeb/synthetic/FIN;", "finallyOpen$NT", "()I");
         IDCallInfo var72 = this.kS.createCallInfo(DInvokeType.STATIC, var69.getIndex(), Arrays.asList());
         IDInstruction var76 = this.kS.createAssign(var35, var72);
         var76.setPhysicalMethodIndex(var54.getPhysicalMethodIndex());
         var76.setPhysicalOffset(var54.getPhysicalOffset());
         var76.setOffset(var63);
         var9.add(0, var76);
         if (!var48.isEmpty()) {
            BasicBlock var80 = DUtil.splitBlock(this.kS, var9, 1);
            if (var48.remove(var9)) {
               var48.add(var80);
            }

            for (BasicBlock var26 : var48) {
               if (var26.getEndAddress() == var9.getBase()) {
                  IDInstruction var27 = (IDInstruction)var26.getLast();
                  if (!var27.isJump()) {
                     return false;
                  }
               }

               this.wS.reconnectEdges(var26, var9, var80);
               ((IDInstruction)var26.getLast()).updateTargets(Maps.toMap((int)var63, (int)var54.getOffset()));
            }

            var10.add(var80);
         }

         var54 = (IDInstruction)var8.get(0);
         DUtil.modifyInstructionSize(this.kS, var54, 2);
         var63 = var54.getOffset();
         var54.setOffset(var63 + 1L);
         var54.adjustSize(-1);
         IDexMethod var81 = this.kS.getDex().addMethod("Ljeb/synthetic/FIN;", "finallyCodeBegin$NT", "(I)V");
         var72 = this.kS.createCallInfo(DInvokeType.STATIC, var81.getIndex(), Arrays.asList(var35));
         var76 = this.kS.createInvoke(var72);
         var76.setPhysicalMethodIndex(var54.getPhysicalMethodIndex());
         var76.setPhysicalOffset(var54.getPhysicalOffset());
         var76.setOffset(var63);
         var76.setData("KEEP_FIRST", true);
         var8.add(0, var76);
         boolean var82 = var42.pC() == var8;
         BasicBlock var83 = DUtil.splitBlock(this.kS, var8, 1);
         bpl.pC(var8, false);

         for (BasicBlock var28 : var8.getInputBlocks()) {
            if (!var10.contains(var28) && ((IDInstruction)var28.getLast()).getBreakingFlow().isBroken()) {
               ((IDInstruction)var28.getLast()).updateTargets(Maps.toMap((int)var8.getBase(), (int)var83.getBase()));
               this.wS.reconnectEdge(var28, var8, var83);
            }
         }

         if (var82) {
            var42.setFirst(var83);
         }

         boolean var85 = var42.A() < var42.pC().size();
         if (var85) {
            var54 = var42.kS();
            DUtil.modifyInstructionSize(this.kS, var54, 2);
            var54.adjustSize(-1);
            var63 = var54.getOffset();
            var54.setOffset(var63 + 1L);
            IDexMethod var86 = this.kS.getDex().addMethod("Ljeb/synthetic/FIN;", "finallyCodeEnd$NT", "(I)V");
            var72 = this.kS.createCallInfo(DInvokeType.STATIC, var86.getIndex(), Arrays.asList(var35));
            var76 = this.kS.createInvoke(var72);
            var76.setPhysicalMethodIndex(var54.getPhysicalMethodIndex());
            var76.setPhysicalOffset(var54.getPhysicalOffset());
            var76.setOffset(var63);
            var42.pC().add(var42.A(), var76);
         } else {
            Assert.a(var42.A() == var42.pC().size());
            var54 = (IDInstruction)var42.pC().getLast();
            Assert.a(!var54.getBreakingFlow().isBroken());
            DUtil.modifyInstructionSize(this.kS, var54, 2);
            var54.adjustSize(-1);
            var63 = var54.getOffsetEnd();
            IDexMethod var87 = this.kS.getDex().addMethod("Ljeb/synthetic/FIN;", "finallyCodeEnd$NT", "(I)V");
            var72 = this.kS.createCallInfo(DInvokeType.STATIC, var87.getIndex(), Arrays.asList(var35));
            var76 = this.kS.createInvoke(var72);
            var76.setPhysicalMethodIndex(var54.getPhysicalMethodIndex());
            var76.setPhysicalOffset(var54.getPhysicalOffset());
            var76.setOffset(var63);
            var42.pC().add(var76);
         }

         return true;
      }

      private bry.Av pC(BasicBlock var1, int var2) {
         bry var3 = new bry(this.kS, this.ys, this.E, 1, this.sY, this.sY.size() - 1, var1, var2);
         var3.pC(true);
         bry.Av var4 = var3.pC();
         if (var4 == null) {
            return null;
         } else {
            if (!var4.ys.isEmpty()) {
               IDFA var5 = this.wS.doDataFlowAnalysis();

               for (Entry var7 : var4.ys.entrySet()) {
                  if (!((Integer)var7.getKey()).equals(var7.getValue())) {
                     if (var5.isAlive(this.sY, this.sY.size() - 1, (Integer)var7.getKey())) {
                        return null;
                     }

                     if (var5.isAlive(this.sY, this.sY.size() - 1, (Integer)var7.getValue())) {
                        return null;
                     }

                     if (var5.isAlive(var4.kS, var4.wS, (Integer)var7.getKey())) {
                        return null;
                     }

                     if (var5.isAlive(var4.kS, var4.wS, (Integer)var7.getValue())) {
                        return null;
                     }
                  }
               }
            }

            return var4;
         }
      }

      private bry.Av wS(BasicBlock var1) {
         int var3 = ((IDInstruction)var1.get(0)).isStoreException() ? 1 : 0;
         bry.Av var2;
         if ((var2 = this.pC(var1, var3)) != null) {
            return var2;
         } else {
            boolean var4 = var1.getIrregularOutputs().contains(this.E);
            var3++;

            while (var3 < var1.size() - this.A && (var4 || !((IDInstruction)var1.get(var3 - 1)).canThrow())) {
               if ((var2 = this.pC(var1, var3)) != null) {
                  return var2;
               }

               var3++;
            }

            return null;
         }
      }

      private boolean UT(BasicBlock var1) {
         for (IDExceptionHandler var4 : this.kS.getExceptionData().getBlockHandlers((int)var1.getBase())) {
            if (var4.isCatchAll(this.kS)) {
               return true;
            }
         }

         return false;
      }

      private BasicBlock pC(BasicBlock var1, List var2) {
         if (!((IDInstruction)this.E.getLast()).isThrow()) {
            return var1;
         } else {
            BasicBlock var3 = null;

            for (long var5 : var2) {
               BasicBlock var7 = this.wS.getBlockAt(var5);
               if (!var7.getIrregularOutputs().contains(this.E)
                  && (var7.size() != this.A + 1 || !((IDInstruction)var7.getLast()).isReturnOrThrow())
                  && (var7.size() != this.A + 2 || !((IDInstruction)var7.get(0)).isStoreException() || !((IDInstruction)var7.getLast()).isThrow())) {
                  if (var7.size() == this.A) {
                     if (var3 == null || var7.size() > var3.size()) {
                        var3 = var7;
                     }
                  } else if (var7.size() >= this.A + 2 && (var3 == null || var7.size() > var3.size())) {
                     var3 = var7;
                  }
               }
            }

            if (var3 == null) {
               return var1;
            } else {
               var2.remove(var3.getBase());
               var2.add(var1.getBase());
               return var3;
            }
         }
      }
   }

   private static class Sv {
      final long pC;
      final Set A;
      final long kS;

      public Sv(long var1, Set var3, long var4) {
         this.pC = var1;
         this.A = var3;
         this.kS = var4;
      }

      public Sv(long var1) {
         this.pC = var1;
         this.A = Sets.newHashSet(var1);
         this.kS = var1;
      }

      boolean pC() {
         return this.pC == this.kS;
      }
   }
}
