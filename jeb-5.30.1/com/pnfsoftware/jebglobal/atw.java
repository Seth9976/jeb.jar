package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class atw extends AbstractEExpressionOptimizer {
   public atw() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEOperation var2) {
         if (var2.getOperationType() == OperationType.AND) {
            IEGeneric var3 = var2.getOperand1();
            IEGeneric var4 = var2.getOperand2();
            AbstractEExpressionOptimizer.EOR var5 = this.q(var3, var4);
            if (var5 == null) {
               var5 = this.q(var4, var3);
            }

            if (var5 != null) {
               return var5;
            }
         }

         if (var2.getOperationType() == OperationType.SHR) {
            IEGeneric var14 = var2.getOperand1();
            IEGeneric var19 = var2.getOperand2();
            if (var19 instanceof IEImm && var19.getBitsize() <= 64) {
               int var24 = (int)((IEImm)var19).getValueAsLong();
               if (var24 > 0) {
                  if (var14 instanceof IECompose var6) {
                     if (!EUtil.isZeroExtend(var6) && !EUtil.looksLikeSignExtension(var6)) {
                        return AbstractEExpressionOptimizer.EOR.create(var14.duplicate().rightShift(var24));
                     }
                  } else if (EUtil.getOperation(var14, OperationType.MUL) != null) {
                     int var31 = this.q(((IEOperation)var14).getOperand2());
                     if (var31 >= 0) {
                        return AbstractEExpressionOptimizer.EOR.create(((IEOperation)var14).getOperand1().leftShift(var31).rightShift(var24));
                     }
                  }
               }
            }
         }

         if (var2.getOperationType() == OperationType.SHL) {
            IEGeneric var15 = var2.getOperand1();
            IEGeneric var20 = var2.getOperand2();
            if (var20 instanceof IEImm && var20.getBitsize() <= 64) {
               int var25 = (int)((IEImm)var20).getValueAsLong();
               if (var25 > 0 && var25 < 64) {
                  return AbstractEExpressionOptimizer.EOR.create(EUtil.mul(var15, this.ectx.createImm(1L << var25, var15.getBitsize())));
               }
            }
         }
      }

      if (var1 instanceof IESlice) {
         IEGeneric var11 = ((IESlice)var1).getWholeExpression();
         IERange var16 = ((IESlice)var1).getRange();
         int var21 = var11.getBitsize();
         if (var16.getBegin() == var21 - 1) {
            var16.getEnd();
         }

         if (EUtil.isOperation(var11, OperationType.MUL)) {
            int var26 = this.q(((IEOperation)var11).getOperand2());
            if (var26 >= 0) {
               return AbstractEExpressionOptimizer.EOR.create(((IEOperation)var11).getOperand1().leftShift(var26).slice(((IESlice)var1).getRange()));
            }
         }

         if (EUtil.isOperation(var11, OperationType.AND) && ((IESlice)var1).getBitStart() == 0) {
            IEGeneric var27 = ((IEOperation)var11).getOperand2();
            if (var27 instanceof IEImm) {
               BigInteger var32 = BigInteger.ONE.shiftLeft(var1.getBitsize()).subtract(BigInteger.ONE);
               if (var32.and(((IEImm)var27).getValue()).equals(var32)) {
                  return AbstractEExpressionOptimizer.EOR.create(((IEOperation)var11).getOperand1().part(var1.getBitsize()));
               }
            }
         }

         if (EUtil.getOperation(var11, OperationType.SHR, OperationType.SAR) != null) {
            IEGeneric var28 = ((IEOperation)var11).getOperand1();
            IEGeneric var33 = ((IEOperation)var11).getOperand2();
            if (var33 instanceof IEImm) {
               long var7 = ((IESlice)var1).getBitStart() + ((IEImm)var33).getValueAsLong();
               long var9 = ((IESlice)var1).getBitEnd() + ((IEImm)var33).getValueAsLong();
               if (var9 <= var28.getBitsize()) {
                  return AbstractEExpressionOptimizer.EOR.create(var28.slice((int)var7, (int)var9));
               }
            }
         }

         if (EUtil.getOperation(var11, OperationType.SHL) != null) {
            IEGeneric var29 = ((IEOperation)var11).getOperand1();
            IEGeneric var34 = ((IEOperation)var11).getOperand2();
            if (var34 instanceof IEImm) {
               long var36 = ((IESlice)var1).getBitStart() - ((IEImm)var34).getValueAsLong();
               long var38 = ((IESlice)var1).getBitEnd() - ((IEImm)var34).getValueAsLong();
               if (var36 >= 0L) {
                  return AbstractEExpressionOptimizer.EOR.create(var29.slice((int)var36, (int)var38));
               }
            }
         }
      }

      if (EUtil.isZeroExtend(var1)) {
         IEGeneric var12 = ((IECompose)var1).getLowPart();
         if (var12 instanceof IESlice && ((IESlice)var12).getBitStart() == 0 && EUtil.isOperation(((IESlice)var12).getWholeExpression(), OperationType.AND)) {
            IEOperation var17 = (IEOperation)((IESlice)var12).getWholeExpression();
            if (var17.getOperand1().getBitsize() == var1.getBitsize() && var17.getOperand2() instanceof IEImm && var12.getBitsize() < 64) {
               long var23 = ((IEImm)var17.getOperand2()).getValueAsLong() & (1L << var12.getBitsize()) - 1L;
               return AbstractEExpressionOptimizer.EOR.create(EUtil.andB(var17.getOperand1(), this.ectx.createImm(var23, var1.getBitsize())));
            }
         }
      }

      if (var1 instanceof IECond var13 && EUtil.isZero(var13.getExpressionFalse())) {
         List var18 = this.q(var13);
         ArrayList var22 = new ArrayList();

         for (IEGeneric var35 : var18) {
            if (EUtil.isOperation(var35, OperationType.LT_U) && EUtil.isImmNonZero(((IEOperation)var35).getOperand2())) {
               IEGeneric var37 = ((IEOperation)var35).getOperand1();
               long var8 = ((IEImm)((IEOperation)var35).getOperand2()).getValueAsLong();
               if (var8 == 8L || var8 == 16L || var8 == 32L || var8 == 64L) {
                  Couple var10 = var13.getExpressionTrue().find(var37, 0, 0, null);
                  if (EUtil.isOperation((IEGeneric)var10.getFirst(), OperationType.SHL, OperationType.SHR, OperationType.SAR)
                     && ((IEOperation)var10.getFirst()).getBitsize() == var8) {
                     var22.add(var35);
                  }
               }
            }
         }

         if (!var22.isEmpty()) {
            var18.removeAll(var22);
            if (var18.isEmpty()) {
               return AbstractEExpressionOptimizer.EOR.create(var13.getExpressionTrue());
            }

            return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(this.q(var18), var13.getExpressionTrue(), var13.getExpressionFalse()));
         }
      }

      return null;
   }

   private int q(IEGeneric var1) {
      if (var1 instanceof IEImm) {
         int var2 = -1;

         for (int var3 = 0; var3 < var1.getBitsize(); var3++) {
            if (((IEImm)var1)._testbit(var3)) {
               if (var2 != -1) {
                  return -1;
               }

               var2 = var3;
            }
         }

         return var2;
      } else {
         return -1;
      }
   }

   private AbstractEExpressionOptimizer.EOR q(IEGeneric var1, IEGeneric var2) {
      if (var1 instanceof IEImm) {
         if (EUtil.isImmZero(var1)) {
            return AbstractEExpressionOptimizer.EOR.create(this.ectx.createImm(0L, var1.getBitsize()), true);
         }

         Integer var3 = this.q((IEImm)var1);
         if (var3 != null && var3 % 8 == 0) {
            return AbstractEExpressionOptimizer.EOR.create(var2.part(var3).zeroExtend(var2.getBitsize()));
         }
      }

      return null;
   }

   private Integer q(IEImm var1) {
      if (var1.getBitsize() < 64) {
         long var2 = var1.getValueAsUnsignedLong();

         int var4;
         for (var4 = 0; (var2 & 1L) == 1L; var4++) {
            var2 >>>= 1;
         }

         if (var2 == 0L) {
            return var4;
         }
      }

      return null;
   }

   private List q(IECond var1) {
      IEGeneric var2 = var1.getCondition();
      ArrayList var3 = new ArrayList();
      this.q(var3, var2);
      return var3;
   }

   private void q(List var1, IEGeneric var2) {
      if (EUtil.isOperation(var2, OperationType.LOG_AND)) {
         IEOperation var3 = (IEOperation)var2;
         this.q(var1, var3.getOperand1());
         this.q(var1, var3.getOperand2());
      } else {
         var1.add(var2);
      }
   }

   private IEGeneric q(List var1) {
      Object var2 = (IEGeneric)var1.get(0);

      for (int var3 = 1; var3 < var1.size(); var3++) {
         var2 = this.ectx.createOperation(OperationType.LOG_AND, (IEGeneric)var2, (IEGeneric)var1.get(var3));
      }

      return (IEGeneric)var2;
   }
}
