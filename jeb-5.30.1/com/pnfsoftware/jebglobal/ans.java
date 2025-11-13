package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.math.BigInteger;
import java.util.ArrayList;

public class ans extends AbstractEExpressionOptimizer {
   public ans() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.skipStatementProcessing = false;
      this.skipLeftSideOfAssignment = true;
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      Object var2 = null;
      if (var1 instanceof IEStatement) {
         if (var1 instanceof IEReturn) {
            IEGeneric var13 = ((IEReturn)var1).getValue();
            if (var13 != null && var13.getType() != null) {
               int var15 = var13.getType().getEffectiveBitsize();
               int var17 = var13.getBitsize();
               Object var19 = null;
               if (var13 instanceof IECompose var24
                  && var24.getPartsCount() == 2
                  && EUtil.hasNoSideEffect(var24.getHighPart())
                  && var24.getLowPart().getBitsize() == var15) {
                  var19 = var24.getLowPart();
                  ams.q((IEGeneric)var19, var13);
               }

               if (var13 instanceof IEImm
                  && ((IEImm)var13).getGroup() != IWildcardType.Group.FLOAT
                  && !var13.getType().isFloat()
                  && var15 != 0
                  && var15 < var17) {
                  var19 = var13.part(var15);
                  ams.q((IEGeneric)var19, var13);
               }

               if (var13 instanceof IECond && var15 != 0 && var15 < var17) {
                  IECond var25 = (IECond)var13;
                  IEGeneric var29 = var25.getExpressionTrue();
                  IEGeneric var30 = var25.getExpressionFalse();
                  var19 = this.ectx.createCond(var25.getCondition(), var29.part(var15), var30.part(var15));
                  ams.q((IEGeneric)var19, var13);
               }

               if (var19 != null) {
                  IEReturn var26 = this.ectx.createReturn((IEGeneric)var19);
                  var26.copyProperties((IEReturn)var1);
                  return AbstractEExpressionOptimizer.EOR.create(var26);
               }
            }
         }

         return null;
      } else {
         if (var1 instanceof IESlice) {
            var2 = this.q((IESlice)var1, false);
         } else if (var1 instanceof IECompose var3) {
            IEGeneric var4 = var3.getPart(0);
            IEGeneric var5 = var3.getPart(1);
            int var6 = var4.getBitsize() + var5.getBitsize();
            if (EUtil.isImmZero(var5)) {
               if (var4 instanceof IESlice) {
                  var4 = this.q((IESlice)var4, true);
                  var2 = EUtil.createResizeOperation(var4, var6, false);
               } else {
                  var2 = EUtil.zeroExt(var4, var6);
               }
            } else if (var5 instanceof IECond) {
               IEGeneric var7 = EUtil.getSignExtensionBase(var4, var5);
               if (var7 != null) {
                  var2 = EUtil.signExt(var7, var6);
               }
            }

            if (var2 != null) {
               int var20 = var3.getPartsCount();
               if (var20 >= 3) {
                  ArrayList var8 = new ArrayList(var3.getParts().subList(2, var20));
                  var8.add(0, var2);
                  var2 = this.ectx.createCompose(var8);
               }
            }

            if (var3.getPartsCount() == 3 && EUtil.isZero(var4)) {
               IEGeneric var21 = var3.getPart(2);
               if (EUtil.isZero(var21)) {
                  var2 = EUtil.zeroExt(var5, var1.getBitsize());
                  var2 = EUtil.shl((IEGeneric)var2, EUtil.imm(2L, var1.getBitsize()));
               } else {
                  IEGeneric var27 = EUtil.getSignExtensionBase(var5, var21);
                  if (var27 != null) {
                     var2 = EUtil.signExt(var5, var1.getBitsize());
                     var2 = EUtil.shl((IEGeneric)var2, EUtil.imm(2L, var1.getBitsize()));
                  }
               }
            }
         } else if (var1 instanceof IEOperation var12) {
            IEGeneric var14 = var12.getOperand1();
            OperationType var16 = var12.getOperationType();
            if (EUtil.getOperation(var12, OperationType.CAST, OperationType.CAST_S) != null) {
               boolean var18 = var16 == OperationType.CAST_S;
               if (EUtil.getOperation(var14, OperationType.CAST, OperationType.CAST_S) != null) {
                  IEOperation var22 = (IEOperation)var14;
                  IEGeneric var28 = var22.getOperand1();
                  boolean var9 = var22.getOperationType() == OperationType.CAST_S;
                  if (var1.getBitsize() <= var14.getBitsize()) {
                     if (var1.getBitsize() == var28.getBitsize()) {
                        var2 = var28;
                     } else {
                        var2 = EUtil.createResizeOperation(var28, var1.getBitsize(), var9);
                     }
                  } else if (var14.getBitsize() >= var28.getBitsize()) {
                     if (var18 == var9) {
                        var2 = EUtil.createResizeOperation(var28, var1.getBitsize(), var9);
                     } else if (var14.getBitsize() == var28.getBitsize()) {
                        var2 = EUtil.createResizeOperation(var28, var1.getBitsize(), var18);
                     } else if (var18 && !var9) {
                        var2 = EUtil.createResizeOperation(var28, var1.getBitsize(), var9);
                     }
                  }
               } else if (var14.getBitsize() == var12.getBitsize()) {
                  var2 = var14;
               } else if (var14 instanceof IECond var23) {
                  var2 = this.ectx
                     .createCond(
                        var23.getCondition(),
                        EUtil.createResizeOperation(var23.getExpressionTrue(), var12.getBitsize(), var18),
                        EUtil.createResizeOperation(var23.getExpressionFalse(), var12.getBitsize(), var18)
                     );
               } else if (var14 instanceof IEImm) {
                  var2 = var18 ? ((IEImm)var14).truncate(var12.getBitsize()) : ((IEImm)var14).expand(var12.getBitsize());
               }
            } else {
               var2 = this.q(var12);
            }
         }

         return var2 != null ? AbstractEExpressionOptimizer.EOR.create((IEGeneric)var2) : null;
      }
   }

   private IEGeneric q(IESlice var1, boolean var2) {
      IEGeneric var3 = var1.getWholeExpression();
      int var4 = var1.getBitStart();
      int var5 = var1.getBitsize();
      if (var5 == var3.getBitsize()) {
         return var3;
      } else {
         int var6 = var3.getBitsize();
         if (var4 == var6 - 1 && var5 == 1) {
            return EUtil.ltS(var3, EUtil.zero(var3.getBitsize()));
         } else {
            boolean var7 = var4 > 0;
            boolean var8 = true;
            Object var9 = var3;
            if (var7) {
               var9 = this.ectx.createOperation(OperationType.SHR, var3, EUtil.imm(var4, var3.getBitsize()));
               if (var2) {
                  var8 = var3.getBitsize() != var1.getBitEnd();
               }
            }

            if (var8) {
               var9 = EUtil.truncate((IEGeneric)var9, var1.getBitsize());
            }

            return (IEGeneric)var9;
         }
      }
   }

   private IEGeneric q(IEOperation var1) {
      if (var1.getOperationType() == OperationType.SAR || var1.getOperationType() == OperationType.SHR) {
         IEGeneric var2 = var1.getOperand1();
         IEGeneric var3 = var1.getOperand2();
         if (var3 instanceof IEImm && var2 instanceof IEOperation var4) {
            IEGeneric var5 = var4.getOperand1();
            if (this.q(var4, (IEImm)var3)) {
               int var6 = EUtil.evalAsSaturatedPositiveInt((IEImm)var3);
               IEOperation var7 = EUtil.createResizeOperation(var5, var1.getBitsize() - var6, false);
               return EUtil.createResizeOperation(var7, var1.getBitsize(), var1.getOperationType() == OperationType.SAR);
            }
         }
      }

      return null;
   }

   private boolean q(IEOperation var1, IEImm var2) {
      IEGeneric var3 = var1.getOperand2();
      if (var1.getOperationType() == OperationType.MUL) {
         if (var3 instanceof IEImm) {
            BigInteger var4 = ((IEImm)var3).getValue();
            int var5 = EUtil.evalAsSaturatedPositiveInt(var2);
            return var4.equals(BigInteger.ONE.shiftLeft(var5));
         }
      } else if (var1.getOperationType() == OperationType.SHL) {
         return var3 instanceof IEImm && var2._cmp((IEImm)var3) == 0;
      }

      return false;
   }
}
