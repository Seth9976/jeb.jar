package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.SubstitutionDefinition;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.math.BigInteger;

public class ara extends AbstractEExpressionOptimizer {
   private static final StructuredLogger RF = aeg.q(ara.class);
   public static final SubstitutionDefinition[] q = new SubstitutionDefinition[]{
      Util.SD(
         Util.N(
            O.EQ,
            Util.N(O.SLICE_LASTBIT, Util.N(O.ADD, Util.L(0), Util.L(1))),
            Util.N(
               O.SLICE_LASTBIT,
               Util.N(O.AND, Util.N(O.XOR, Util.N(O.ADD, Util.L(0), Util.L(1)), Util.L(0)), Util.N(O.NOT, Util.N(O.XOR, Util.L(0), Util.L(1))))
            )
         ),
         Util.N(O.GE_S, Util.N(O.ADD, Util.L(0), Util.L(1)), Util.LC(0L))
      ),
      Util.SD(Util.N(O.EQ, Util.N(O.EQ, Util.L(0), Util.L(1)), Util.LC(0L)), Util.N(O.NE, Util.L(0), Util.L(1))),
      Util.SD(Util.N(O.NE, Util.N(O.EQ, Util.L(0), Util.L(1)), Util.LC(0L)), Util.N(O.EQ, Util.L(0), Util.L(1))),
      Util.SD(
         Util.N(O.SLICE_LASTBIT, Util.N(O.AND, Util.N(O.ADD, Util.L(0), Util.LC(1L)), Util.N(O.XOR, Util.N(O.ADD, Util.L(0), Util.LC(1L)), Util.L(0)))),
         Util.LC(0L, 1)
      )
   };

   public ara() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      IEGeneric var2 = this.doSubstitution(var1, q);
      if (var2 != null) {
         return AbstractEExpressionOptimizer.EOR.create(var2);
      } else if (!(var1 instanceof IEOperation)) {
         return null;
      } else {
         OperationType var3 = ((IEOperation)var1).getOperationType();
         IEGeneric var4 = ((IEOperation)var1).getOperand1();
         IEGeneric var5 = ((IEOperation)var1).getOperand2();
         if (var3 == OperationType.NOT && var4 instanceof IEOperation) {
            OperationType var6 = ((IEOperation)var4).getOperationType();
            var6 = EUtil.getReverseOperation(var6);
            if (var6 != null) {
               IEGeneric var27 = ((IEOperation)var4).getOperand1();
               IEGeneric var33 = ((IEOperation)var4).getOperand2();
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var6, var27, var33));
            }
         }

         if (var3 == OperationType.LOG_NOT && var4 instanceof IEOperation) {
            OperationType var12 = ((IEOperation)var4).getOperationType();
            if (var12 == OperationType.LOG_NOT) {
               IEGeneric var26 = ((IEOperation)var4).getOperand1();
               IEOperation var32 = this.ectx.createOperation(OperationType.LOG_NEQ, var26, EUtil.zero(var26.getBitsize()));
               return AbstractEExpressionOptimizer.EOR.create(var32);
            }
         }

         if (var3 == OperationType.LOG_EQ || var3 == OperationType.LOG_NEQ) {
            boolean var13 = var3 == OperationType.LOG_EQ;
            Boolean var7 = null;
            if (EUtil.isBitZero(var5)) {
               var7 = true;
            } else if (EUtil.isBitOne(var5)) {
               var7 = false;
            }

            if (var7 != null) {
               if (EUtil.isComparableOperation(var4)) {
                  if (var13 ^ var7) {
                     return AbstractEExpressionOptimizer.EOR.create(var4);
                  }

                  IEOperation var31 = (IEOperation)var4;
                  OperationType var35 = EUtil.getReverseOperation(var31.getOperationType());
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var35, var31.getOperand1(), var31.getOperand2()));
               }

               if (var4.getBitsize() == 1) {
                  if (var13 ^ var7) {
                     return AbstractEExpressionOptimizer.EOR.create(var4);
                  }

                  return AbstractEExpressionOptimizer.EOR.create(EUtil.notL(var4));
               }
            }
         }

         if (var3 == OperationType.LOG_AND && var4 instanceof IEOperation && var5 instanceof IEOperation) {
            IEOperation var14 = null;
            IEOperation var20 = null;
            if (RF(((IEOperation)var4).getOperationType())) {
               var14 = (IEOperation)var4;
               var20 = (IEOperation)var5;
            } else if (RF(((IEOperation)var5).getOperationType())) {
               var14 = (IEOperation)var5;
               var20 = (IEOperation)var4;
            }

            if (var14 != null && this.q(var14, var20)) {
               OperationType var8 = Dw(var20.getOperationType());
               if (var8 != null) {
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var8, var20.getOperand1(), var20.getOperand2()));
               }
            }
         }

         if (var3 == OperationType.LOG_OR && var4 instanceof IEOperation && var5 instanceof IEOperation) {
            IEOperation var15 = null;
            IEOperation var21 = null;
            if (q(((IEOperation)var4).getOperationType())) {
               var15 = (IEOperation)var4;
               var21 = (IEOperation)var5;
            } else if (q(((IEOperation)var5).getOperationType())) {
               var15 = (IEOperation)var5;
               var21 = (IEOperation)var4;
            }

            if (var15 != null && this.q(var15, var21)) {
               OperationType var28 = oW(var21.getOperationType());
               if (var28 != null) {
                  return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var28, var21.getOperand1(), var21.getOperand2()));
               }
            }
         }

         if ((var3 == OperationType.LOG_EQ || var3 == OperationType.LOG_NEQ)
            && EUtil.isImmZero(var5)
            && EUtil.isOperation(var4, OperationType.XOR, OperationType.SUB)) {
            IEGeneric var19 = ((IEOperation)var4).getOperand1();
            IEGeneric var25 = ((IEOperation)var4).getOperand2();
            return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var3, var19, var25));
         } else {
            if ((var3 == OperationType.LOG_EQ || var3 == OperationType.LOG_NEQ) && EUtil.isImmZero(var5) && EUtil.isOperation(var4, OperationType.AND)) {
               IEGeneric var16 = ((IEOperation)var4).getOperand1();
               IEGeneric var22 = ((IEOperation)var4).getOperand2();
               int var29 = var16.getBitsize() - 1;
               int var9 = var16.getBitsize() % 8;
               byte[] var10 = new byte[var16.getBitsize() / 8 + (var9 == 0 ? 0 : 1)];
               var10[0] = (byte)(1 << var29 % 8);
               if (EUtil.isImmValue(var22, new BigInteger(-1, var10))) {
                  return AbstractEExpressionOptimizer.EOR.create(
                     this.ectx.createOperation(var3 == OperationType.LOG_EQ ? OperationType.GE_S : OperationType.LT_S, var16, var5)
                  );
               }
            }

            if (var3 == OperationType.SAR && EUtil.isImmValue(var5, var4.getBitsize())) {
               IEImm var18 = this.ectx.createImm(0L, var4.getBitsize());
               IEImm var24 = this.ectx.createImm(-1L, var4.getBitsize());
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(this.ectx.createOperation(OperationType.GE_S, var4, var18), var18, var24));
            } else {
               if (var3 == OperationType.CARRY && this.currentStatement != null && !(this.currentStatement instanceof IEAssign)) {
                  IEImm var17 = null;
                  IEGeneric var23 = null;
                  if (var4 instanceof IEImm) {
                     var17 = (IEImm)var4;
                     var23 = var5;
                  } else if (var5 instanceof IEImm) {
                     var17 = (IEImm)var5;
                     var23 = var4;
                  }

                  if (var17 != null) {
                     int var30 = var17.getBitsize();
                     IEImm var34 = alu.q(0L, var30)._sub(var17);
                     IEOperation var36 = this.ectx.createOperation(OperationType.GE_U, var23, var34);
                     return AbstractEExpressionOptimizer.EOR.create(var36);
                  }
               }

               return null;
            }
         }
      }
   }

   static boolean q(OperationType var0) {
      return var0 == OperationType.LOG_EQ || var0 == OperationType.FEQ;
   }

   static boolean RF(OperationType var0) {
      return var0 == OperationType.LOG_NEQ || var0 == OperationType.FNE;
   }

   static OperationType xK(OperationType var0) {
      switch (var0) {
         case GE_S:
            return OperationType.GT_S;
         case GE_U:
            return OperationType.GT_U;
         case LE_S:
            return OperationType.LT_S;
         case LE_U:
            return OperationType.LT_U;
         default:
            return null;
      }
   }

   static OperationType Dw(OperationType var0) {
      switch (var0) {
         case GE_S:
            return OperationType.GT_S;
         case GE_U:
            return OperationType.GT_U;
         case LE_S:
            return OperationType.LT_S;
         case LE_U:
            return OperationType.LT_U;
         case FGE:
            return OperationType.FGT;
         case FLE:
            return OperationType.FLT;
         case GT_S:
            return OperationType.GT_S;
         case GT_U:
            return OperationType.GT_U;
         case LT_S:
            return OperationType.LT_S;
         case LT_U:
            return OperationType.LT_U;
         case FGT:
            return OperationType.FGT;
         case FLT:
            return OperationType.FLT;
         default:
            return null;
      }
   }

   static OperationType Uv(OperationType var0) {
      switch (var0) {
         case GT_S:
            return OperationType.GE_S;
         case GT_U:
            return OperationType.GE_U;
         case LT_S:
            return OperationType.LE_S;
         case LT_U:
            return OperationType.LE_U;
         default:
            return null;
      }
   }

   static OperationType oW(OperationType var0) {
      switch (var0) {
         case GE_S:
            return OperationType.GE_S;
         case GE_U:
            return OperationType.GE_U;
         case LE_S:
            return OperationType.LE_S;
         case LE_U:
            return OperationType.LE_U;
         case FGE:
            return OperationType.FGE;
         case FLE:
            return OperationType.FLE;
         case GT_S:
            return OperationType.GE_S;
         case GT_U:
            return OperationType.GE_U;
         case LT_S:
            return OperationType.LE_S;
         case LT_U:
            return OperationType.LE_U;
         case FGT:
            return OperationType.FGE;
         case FLT:
            return OperationType.FLE;
         default:
            return null;
      }
   }

   private boolean q(IEOperation var1, IEOperation var2) {
      if (var1.getOperand2() != null) {
         if (amw.q(var1.getOperand1(), var2.getOperand1())) {
            return amw.q(var1.getOperand2(), var2.getOperand2());
         } else {
            return amw.q(var1.getOperand1(), var2.getOperand2()) ? amw.q(var1.getOperand2(), var2.getOperand1()) : false;
         }
      } else {
         return var2.getOperand2() == null && amw.q(var1.getOperand1(), var2.getOperand2());
      }
   }
}
