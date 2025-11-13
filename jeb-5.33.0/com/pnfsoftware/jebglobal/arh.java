package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpWithOptionalCondition;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.math.BigInteger;

public class arh extends AbstractEExpressionOptimizer {
   public arh() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.skipStatementProcessing = false;
      this.skipLeftSideOfAssignment = true;
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IECond) {
         IEGeneric var2 = ((IECond)var1).getCondition();
         IEGeneric var3 = ((IECond)var1).getExpressionTrue();
         IEGeneric var4 = ((IECond)var1).getExpressionFalse();
         if (var2 instanceof IEOperation var5) {
            if (var5.getOperationType() == OperationType.SUB) {
               IEGeneric var54 = var5.getOperand1();
               IEGeneric var64 = var5.getOperand2();
               IEOperation var71 = this.ectx.createOperation(OperationType.LOG_EQ, var54, var64);
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var71, var4, var3));
            }

            if (var5.getBitsize() == 1 && EUtil.isImmSize(var3, 1) && EUtil.isImmSize(var4, 1)) {
               BigInteger var6 = ((IEImm)var3).getValue().abs();
               BigInteger var7 = ((IEImm)var4).getValue().abs();
               if (var6.compareTo(BigInteger.ONE) == 0 && var7.compareTo(BigInteger.ZERO) == 0) {
                  return AbstractEExpressionOptimizer.EOR.create(var2);
               }

               if (var6.compareTo(BigInteger.ZERO) == 0 && var7.compareTo(BigInteger.ONE) == 0) {
                  OperationType var8 = ((IEOperation)var2).getOperationType();
                  var8 = EUtil.getReverseOperation(var8);
                  if (var8 != null) {
                     IEOperation var9 = this.ectx.createOperation(var8, ((IEOperation)var2).getOperand1(), ((IEOperation)var2).getOperand2());
                     if (var9.getBitsize() == 1) {
                        return AbstractEExpressionOptimizer.EOR.create(var9);
                     }
                  }
               }
            }

            if (var5.getOperationType() == OperationType.LOG_NEQ && var5.getOperand2().getBitsize() == 1 && EUtil.isZero(var5.getOperand2())) {
               ((IECond)var1).setCondition(var5.getOperand1());
               return AbstractEExpressionOptimizer.EOR.create(var1);
            }

            if (var5.getOperationType() == OperationType.LOG_EQ && var5.getOperand2().getBitsize() == 1 && EUtil.isMinusOne(var5.getOperand2())) {
               ((IECond)var1).setCondition(var5.getOperand1());
               return AbstractEExpressionOptimizer.EOR.create(var1);
            }
         }

         if (var2 instanceof IECompose) {
            IEGeneric var30 = null;
            boolean var44 = false;

            for (IEGeneric var66 : var2.asCompose()) {
               if (!EUtil.isZero(var66)) {
                  if (var30 != null) {
                     var44 = true;
                     break;
                  }

                  var30 = var66;
               }
            }

            if (!var44 && var30 != null) {
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var30, var3, var4));
            }
         }

         if (var3 instanceof IECond) {
            IEGeneric var31 = ((IECond)var3).getCondition();
            IEGeneric var45 = ((IECond)var3).getExpressionTrue();
            IEGeneric var56 = ((IECond)var3).getExpressionFalse();
            if (var4.equals(var56)) {
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(EUtil.andL(var2, var31), var45, var4));
            }
         }
      }

      if (var1 instanceof IEOperation var12) {
         OperationType var16 = var12.getOperationType();
         if (var16 == OperationType.LOG_NOT && var12.getOperand1() instanceof IEOperation) {
            IEOperation var20 = (IEOperation)var12.getOperand1();
            if (var20.getBitsize() == var12.getBitsize()) {
               OperationType var32 = var20.getOperationType();
               var32 = EUtil.getReverseOperation(var32);
               if (var32 != null) {
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var32, var20.getOperand1(), var20.getOperand2()));
               }
            }
         }

         if ((var16 == OperationType.LOG_EQ || var16 == OperationType.LOG_NEQ) && var12.getOperand1() instanceof IECond && var12.getOperand2() instanceof IEImm
            )
          {
            IECond var21 = (IECond)var12.getOperand1();
            if (var21.getExpressionTrue() instanceof IEImm && var21.getExpressionFalse() instanceof IEImm) {
               IEImm var34 = (IEImm)var21.getExpressionTrue();
               IEImm var46 = (IEImm)var21.getExpressionFalse();
               IEImm var57 = (IEImm)var12.getOperand2();
               if (!akt.pC(var34, var46)) {
                  IEGeneric var67 = var21.getCondition();
                  if (akt.pC(var34, var57)) {
                     return var16 == OperationType.LOG_EQ
                        ? AbstractEExpressionOptimizer.EOR.create(EUtil.checkTrue(var67))
                        : AbstractEExpressionOptimizer.EOR.create(EUtil.checkFalse(var67));
                  }

                  if (akt.pC(var46, var57)) {
                     return var16 == OperationType.LOG_EQ
                        ? AbstractEExpressionOptimizer.EOR.create(EUtil.checkFalse(var67))
                        : AbstractEExpressionOptimizer.EOR.create(EUtil.checkTrue(var67));
                  }
               }
            }
         }

         if (var16 == OperationType.NOT && var12.getOperand1() instanceof IECond) {
            IECond var22 = (IECond)var12.getOperand1();
            if (var22.getExpressionTrue() instanceof IEImm || var22.getExpressionFalse() instanceof IEImm) {
               IEGeneric var43 = EUtil.notB(var22.getExpressionTrue());
               IEGeneric var53 = EUtil.notB(var22.getExpressionFalse());
               var22.setExpressionTrue(var43);
               var22.setExpressionFalse(var53);
               return AbstractEExpressionOptimizer.EOR.create(var22, false);
            }
         }

         if (EUtil.isComparableSignedOperation(var12) && var12.getOperand1() instanceof IECompose && var12.getOperand2() instanceof IEImm) {
            IECompose var23 = (IECompose)var12.getOperand1();
            IEGeneric var35 = EUtil.getSignExtensionBase(var23);
            if (var35 != null) {
               IEImm var47 = (IEImm)var12.getOperand2();
               IEImm var58 = var47.truncate(var35.getBitsize());
               if (var47._cmp(var58.truncate(var47.getBitsize())) == 0) {
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var12.getOperationType(), var35, var58));
               }
            }
         }

         if (EUtil.isComparableIntegerOperation(var12) && var12.getOperand1() instanceof IECompose && var12.getOperand2() instanceof IEImm) {
            IECompose var24 = (IECompose)var12.getOperand1();
            if (var24.getPartsCount() == 2 && var24.getPart(1) instanceof IEImm) {
               if (var24.getPart(1).equalsEx(var12.getOperand2().slice(var24.getPart(0).getBitsize(), var12.getOperand2().getBitsize()), false)) {
                  return AbstractEExpressionOptimizer.EOR.create(
                     this.ectx.createOperation(var12.getOperationType(), var24.getPart(0), var12.getOperand2().part(var24.getPart(0).getBitsize()))
                  );
               }

               if (var12.getOperationType() == OperationType.LOG_EQ) {
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createImm(0L, var12.getBitsize()), true);
               }

               if (var12.getOperationType() == OperationType.LOG_NEQ) {
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createImm(1L, var12.getBitsize()), true);
               }
            }
         }

         if (var12.getOperand1().getType() != null && var12.getOperand2() instanceof IEImm) {
            IEGeneric var25 = var12.getOperand1();
            IWildcardType var36 = var25.getType();
            Boolean var48 = null;
            if (var36.isSigned()) {
               var48 = true;
            } else if (var36.isUnsigned()) {
               var48 = false;
            }

            if (var48 != null) {
               IEOperation var59 = null;
               IEImm var68 = (IEImm)var12.getOperand2();
               IEImm var72 = EUtil.imm(BigInteger.ONE.shiftLeft(var25.getBitsize() - 1), var25.getBitsize());
               if (var48 && var16 == OperationType.LT_U && var68.equalsEx(var72, false)) {
                  var59 = this.ectx.createOperation(OperationType.GE_S, var25, EUtil.zero(var25.getBitsize()));
               } else if (var48 && var16 == OperationType.GE_U && var68.equalsEx(var72, false)) {
                  var59 = this.ectx.createOperation(OperationType.LT_S, var25, EUtil.zero(var25.getBitsize()));
               } else if (!var48 && var16 == OperationType.LT_S && var68._signum() == 0) {
                  var59 = this.ectx.createOperation(OperationType.GE_U, var25, var72);
               } else if (!var48 && var16 == OperationType.GE_S && var68._signum() == 0) {
                  var59 = this.ectx.createOperation(OperationType.LT_U, var25, var72);
               }

               if (var59 != null) {
                  return AbstractEExpressionOptimizer.EOR.create(var59);
               }
            }
         }

         if (var12.getCountOfOperands() == 2 && var12.getOperand1() instanceof IECond && var12.getOperand2() instanceof IEImm) {
            IECond var26 = (IECond)var12.getOperand1();
            if (var26.getExpressionTrue() instanceof IEImm && var26.getExpressionFalse() instanceof IEImm) {
               IEImm var42 = var26.getExpressionTrue().asImm();
               IEImm var52 = var26.getExpressionFalse().asImm();
               IEImm var63 = var12.getOperand2().asImm();
               IECond var70 = this.ectx
                  .createCond(var26.getCondition(), this.ectx.createOperation(var16, var42, var63), this.ectx.createOperation(var16, var52, var63.duplicate()));
               return AbstractEExpressionOptimizer.EOR.create(var70);
            }
         }
      }

      if (var1 instanceof IEMem && ((IEMem)var1).getReference() instanceof IECond) {
         IEMem var15 = (IEMem)var1;
         IECond var19 = (IECond)var15.getReference();
         IEMem var29 = this.ectx.createMem(var15.getSegment(), var19.getExpressionTrue(), var15.getBitsize());
         var29.setType(var15.getType());
         IEMem var41 = this.ectx.createMem(var15.getSegment(), var19.getExpressionFalse(), var15.getBitsize());
         var41.setType(var15.getType());
         return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var19.getCondition(), var29, var41));
      } else {
         if (var1 instanceof IESlice && ((IESlice)var1).getWholeExpression() instanceof IECond var17) {
            IERange var27 = ((IESlice)var1).getRange();
            if (var17.getExpressionTrue() instanceof IEImm || var17.getExpressionFalse() instanceof IEImm) {
               IEGeneric var40 = var17.getExpressionTrue();
               IEGeneric var51 = var17.getExpressionFalse();
               IECond var62 = this.ectx.createCond(var17.getCondition(), var40.slice(var27), var51.slice(var27));
               return AbstractEExpressionOptimizer.EOR.create(var62);
            }

            if (this.pC(var17.getExpressionTrue(), var27) && this.pC(var17.getExpressionFalse(), var27)) {
               IEGeneric var39 = var17.getExpressionTrue();
               IEGeneric var50 = var17.getExpressionFalse();
               IECond var61 = this.ectx.createCond(var17.getCondition(), var39.slice(var27), var50.slice(var27));
               return AbstractEExpressionOptimizer.EOR.create(var61, true);
            }
         }

         if (var1 instanceof IEJumpWithOptionalCondition var14) {
            IEGeneric var18 = var14.getCondition();
            boolean var28 = false;
            if (var18 instanceof IEOperation) {
               IEOperation var37 = var18.asOperation();
               if (var37.getOperationType() == OperationType.LOG_NOT) {
                  var28 = true;
                  var18 = var37.getOperand1();
               }
            }

            if (var18 instanceof IEOperation) {
               IEOperation var38 = var18.asOperation();
               OperationType var49 = var38.getOperationType();
               if (var49.isAnyOf(OperationType.ADD, OperationType.SUB) && var38.getOperand2() instanceof IEImm) {
                  IEGeneric var60 = var38.getOperand1();
                  IEGeneric var69 = var38.getOperand2();
                  OperationType var73 = var28 ? OperationType.LOG_EQ : OperationType.LOG_NEQ;
                  Object var10 = var49 == OperationType.ADD ? var69.asImm()._neg() : var69;
                  IEOperation var11 = this.ectx.createOperation(var73, var60, (IEGeneric)var10);
                  var14.setCondition(var11);
                  return AbstractEExpressionOptimizer.EOR.create(var14, false);
               }
            }
         }

         return null;
      }
   }

   private boolean pC(IEGeneric var1, IERange var2) {
      return var1 instanceof IEImm ? true : var2.getBegin() == 0 && var1 instanceof IECompose;
   }
}
