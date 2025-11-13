package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EExpressionMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.INode;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Map;

public class aru extends AbstractEOptimizer {
   private static final StructuredLogger oW = aeg.q(aru.class);
   static final INode q = Util.N(O.ADD, Util.N(O.SLICE_HALF1, Util.L(0)), Util.N(O.SLICE_HALF1, Util.L(1)));
   static final INode RF = Util.N(
      O.ADD,
      Util.N(O.SLICE_HALF2, Util.L(0)),
      Util.N(O.SLICE_HALF2, Util.L(1)),
      Util.N(O.COMPOSE_2, Util.N(O.CARRY, Util.N(O.SLICE_HALF1, Util.L(0)), Util.N(O.SLICE_HALF1, Util.L(1))), Util.LC(0L))
   );
   private static final INode gO = Util.N(
      O.COND,
      Util.N(O.LT_U, Util.N(O.ADD, Util.N(O.SLICE_HALF1, Util.L(0)), Util.N(O.SLICE_HALF1, Util.L(1))), Util.N(O.SLICE_HALF1, Util.L(0))),
      Util.LC(1L),
      Util.LC(0L)
   );
   static final INode xK = Util.N(O.ADD, gO, Util.N(O.SLICE_HALF2, Util.L(0)), Util.N(O.SLICE_HALF2, Util.L(1)));
   static final INode Dw = Util.N(O.SUB, Util.N(O.SLICE_HALF1, Util.L(0)), Util.N(O.SLICE_HALF1, Util.L(1)));
   static final INode Uv = Util.N(
      O.SUB,
      Util.N(O.SLICE_HALF2, Util.L(0)),
      Util.N(
         O.ADD,
         Util.N(O.SLICE_HALF2, Util.L(1)),
         Util.N(O.COMPOSE_2, Util.N(O.LT_U, Util.N(O.SLICE_HALF1, Util.L(0)), Util.N(O.SLICE_HALF1, Util.L(1))), Util.LC(0L))
      )
   );

   public aru() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg.getBlocks()) {
         boolean var4 = false;
         IEVar var5 = null;
         int var6 = 0;

         for (int var7 = 0; var7 < var3.size(); var7++) {
            IEStatement var8 = (IEStatement)var3.get(var7);
            if (var8 instanceof IEAssign && ((IEAssign)var8).getLeftOperand() instanceof IESlice) {
               IESlice var9 = (IESlice)((IEAssign)var8).getLeftOperand();
               if (var4) {
                  if (this.q(var9, var5)) {
                     var1 += this.q(var1, var3, var5, var6, var7);
                     var4 = false;
                     continue;
                  }

                  boolean var10 = true;

                  for (int var11 = 0; var11 < var3.size(); var11++) {
                     IEStatement var12 = (IEStatement)var3.get(var11);
                     if (var12 instanceof IEAssign && ((IEAssign)var12).getLeftOperand() instanceof IESlice) {
                        IESlice var13 = (IESlice)((IEAssign)var12).getLeftOperand();
                        if (this.q(var13, var5)) {
                           var10 = false;
                           if (var11 < var6) {
                              int var14 = var11;

                              while (var11 + 1 != var6 && var3.get(var11 + 1) instanceof IENop) {
                                 var11++;
                              }

                              if (var11 + 1 == var6) {
                                 var1 += this.q(var1, var3, var5, var6, var14);
                              }
                           }
                           break;
                        }
                     }
                  }

                  if (var10) {
                     IEAssign var15 = (IEAssign)var3.get(var6);
                     IEAssign var16 = var15.duplicateWithNewOperands(
                        var5, this.ectx.createCompose(var15.getRightOperand(), var5.slice(var5.getBitsize() / 2, var5.getBitsize()))
                     );
                     var3.set(var6, var16);
                     var1++;
                  }

                  var4 = false;
               }

               if (var9.getWholeExpression() instanceof IEVar) {
                  var5 = (IEVar)var9.getWholeExpression();
                  if (var9.getBitStart() == 0 && var9.getBitEnd() == var5.getBitsize() / 2) {
                     var4 = true;
                     var6 = var7;
                     continue;
                  }
               }
            }

            if (!(var8 instanceof IENop)) {
               var4 = false;
            }
         }
      }

      return this.postPerform(var1);
   }

   private int q(int var1, BasicBlock var2, IEVar var3, int var4, int var5) {
      Object[] var10000 = new Object[]{var2.get(var4), var2.get(var5)};
      if (this.q(var2, var4, var5, var3)) {
         return 1;
      } else {
         return this.RF(var2, var4, var5, var3) ? 1 : 0;
      }
   }

   private boolean q(IESlice var1, IEVar var2) {
      if (!(var1.getWholeExpression() instanceof IEVar)) {
         return false;
      } else {
         IEVar var3 = (IEVar)var1.getWholeExpression();
         return var3 == var2 && var1.getBitStart() == var2.getBitsize() / 2 && var1.getBitEnd() == var2.getBitsize();
      }
   }

   private boolean q(BasicBlock var1, int var2, int var3, IEVar var4) {
      boolean var5 = true;
      IEAssign var6 = (IEAssign)var1.get(var2);
      EExpressionMatcher var7 = new EExpressionMatcher(q);
      if (!var7.isMatch(var6.getRightOperand())) {
         var7 = new EExpressionMatcher(Dw);
         if (!var7.isMatch(var6.getRightOperand())) {
            return false;
         }

         var5 = false;
      }

      IEAssign var8 = (IEAssign)var1.get(var3);
      EExpressionMatcher var9 = new EExpressionMatcher(var5 ? RF : Uv);
      if (!var9.isMatch(var8.getRightOperand())) {
         return false;
      } else {
         Map var10 = var7.getMatchMap();
         IEGeneric var11 = (IEGeneric)var10.get(0);
         IEGeneric var12 = (IEGeneric)var10.get(1);
         var10 = var9.getMatchMap();
         IEGeneric var13 = (IEGeneric)var10.get(0);
         IEGeneric var14 = (IEGeneric)var10.get(1);
         if (var11 == var13 && var12 == var14) {
            IEAssign var15 = var6.duplicateWithNewOperands(var4, this.ectx.createOperation(var5 ? OperationType.ADD : OperationType.SUB, var11, var12));
            var15.copyProperties(var8);
            var15.setSize(var6.getSize());
            var1.set(var2, var15);
            var1.set(var3, this.ectx.createNop(var8));
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean RF(BasicBlock var1, int var2, int var3, IEVar var4) {
      IEAssign var5 = (IEAssign)var1.get(var2);
      IEGeneric var6 = var5.getSrcOperand();
      IEAssign var7 = (IEAssign)var1.get(var3);
      IEGeneric var8 = var7.getSrcOperand();
      Object var9 = null;
      if (var6 instanceof IEImm && var8 instanceof IEImm) {
         var9 = this.ectx.createCompose(var6, var8);
      } else if (var6 instanceof IESlice && var8 instanceof IESlice) {
         IESlice var10 = (IESlice)var6;
         IESlice var11 = (IESlice)var8;
         IEGeneric var12 = var10.getWholeExpression();
         IEGeneric var13 = var11.getWholeExpression();
         if (var12 instanceof IEVar
            && var12 == var13
            && var10.getBitStart() == 0
            && var10.getBitEnd() == var4.getBitsize() / 2
            && var11.getBitStart() == var4.getBitsize() / 2
            && var11.getBitEnd() == var4.getBitsize()) {
            var9 = var12;
         }
      }

      if (var9 == null && this.getMasterOptimizerSafe().getMode().isAggressive()) {
         IEAssign var14 = var2 < var3 ? var5 : var7;
         IEAssign var16 = var2 < var3 ? var7 : var5;
         IdRanges var17 = var16.getRightOperand().getUsed();
         if (var17.containsVarPart(var4)) {
            IECompose var18;
            if (var2 < var3) {
               var18 = this.ectx.createCompose(var14.getRightOperand(), var4.slice(var4.getBitsize() / 2, var4.getBitsize()));
            } else {
               var18 = this.ectx.createCompose(var4.slice(0, var4.getBitsize() / 2), var14.getRightOperand());
            }

            var16.getRightOperand().replaceVar(var4, var18);
         }

         var9 = this.ectx.createCompose(var5.getRightOperand(), var7.getRightOperand());
      }

      if (var9 == null) {
         return false;
      } else {
         IEAssign var15 = var5.duplicateWithNewOperands(var4, (IEGeneric)var9);
         var15.copyProperties(var7);
         var15.setSize(var5.getSize());
         var1.set(var2, var15);
         var1.set(var3, this.ectx.createNop(var7));
         return true;
      }
   }
}
