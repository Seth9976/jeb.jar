package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class atv extends AbstractEExpressionOptimizer {
   public atv() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      return this.RF(var1);
   }

   private AbstractEExpressionOptimizer.EOR q(IEGeneric var1) {
      OperationType var2 = EUtil.getOperation(var1, OperationType.ADD, OperationType.SUB);
      if (var2 != null) {
         IEGeneric var3 = ((IEOperation)var1).getOperand1();
         IEGeneric var4 = ((IEOperation)var1).getOperand2();
         OperationType var5 = EUtil.getOperation(var3, OperationType.ADD, OperationType.SUB);
         if (var5 != null && ((IEOperation)var3).getOperand2() instanceof IEImm) {
            IEGeneric var6 = ((IEOperation)var3).getOperand1();
            IEImm var7 = (IEImm)((IEOperation)var3).getOperand2();
            IEGeneric var9 = null;
            IEImm var10 = null;
            OperationType var8;
            if (var4 instanceof IEImm) {
               var8 = OperationType.ADD;
               var10 = (IEImm)var4;
            } else {
               var8 = EUtil.getOperation(var4, OperationType.ADD, OperationType.SUB);
               if (var8 != null && ((IEOperation)var4).getOperand2() instanceof IEImm) {
                  var9 = ((IEOperation)var4).getOperand1();
                  var10 = (IEImm)((IEOperation)var4).getOperand2();
               }
            }

            if (var10 != null) {
               int var11 = EUtil.sameBitsizeAllowNulls(var6, var7, var9, var10);
               if (var11 == 0) {
                  return null;
               }

               IEImm var12 = var7;
               if (var5 == OperationType.SUB) {
                  var12 = var7._neg();
               }

               IEImm var13 = var10;
               if (var2 != var8) {
                  var13 = var10._neg();
               }

               IEImm var14 = var12._add(var13);
               Object var15;
               if (var14._signum() == 0) {
                  var15 = var6;
               } else if (var14._signum() > 0) {
                  var15 = EUtil.add(var6, var14);
               } else {
                  var15 = EUtil.sub(var6, var14._neg());
               }

               if (var9 != null) {
                  var15 = this.ectx.createOperation(var2, (IEGeneric)var15, var9);
               }

               return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var15);
            }
         }
      }

      return null;
   }

   private AbstractEExpressionOptimizer.EOR RF(IEGeneric var1) {
      OperationType var2 = EUtil.getOperation(var1, OperationType.ADD, OperationType.SUB);
      if (var2 == null) {
         return null;
      } else {
         IEGeneric var3 = ((IEOperation)var1).getOperand1();
         IEGeneric var4 = ((IEOperation)var1).getOperand2();
         OperationType var5 = EUtil.getOperation(var3, OperationType.ADD, OperationType.SUB);
         if (var5 == null) {
            return null;
         } else {
            IEGeneric var6 = ((IEOperation)var3).getOperand1();
            IEGeneric var7 = ((IEOperation)var3).getOperand2();
            if (!(var6 instanceof IEImm ^ var7 instanceof IEImm)) {
               return null;
            } else {
               OperationType var8;
               IEGeneric var9;
               IEGeneric var10;
               if (var4 instanceof IEImm) {
                  var8 = OperationType.ADD;
                  var9 = var4;
                  var10 = null;
               } else {
                  var8 = EUtil.getOperation(var4, OperationType.ADD, OperationType.SUB);
                  if (var8 == null) {
                     return null;
                  }

                  var9 = ((IEOperation)var4).getOperand1();
                  var10 = ((IEOperation)var4).getOperand2();
                  if (!(var9 instanceof IEImm ^ var10 instanceof IEImm)) {
                     return null;
                  }
               }

               int[] var11 = new int[4];
               if (var6 instanceof IEImm var12) {
                  var11[0] = 0;
                  var11[1] = var5 == OperationType.SUB ? -1 : 1;
               } else {
                  var12 = (IEImm)var7;
                  if (var5 == OperationType.SUB) {
                     var12 = var12._neg();
                  }

                  var11[0] = 1;
                  var11[1] = 0;
               }

               if (var9 instanceof IEImm var13) {
                  if (var2 == OperationType.SUB) {
                     var13 = var13._neg();
                  }

                  var11[2] = 0;
                  if (var10 != null) {
                     var11[3] = var2 == OperationType.SUB ^ var8 == OperationType.SUB ? -1 : 1;
                  }
               } else {
                  var13 = (IEImm)var10;
                  if (var2 == OperationType.SUB ^ var8 == OperationType.SUB) {
                     var13 = var13._neg();
                  }

                  var11[2] = var2 == OperationType.SUB ? -1 : 1;
                  var11[3] = 0;
               }

               IEImm var14 = var12._add(var13);
               IEOperation var15;
               if (var11[0] != 0) {
                  var15 = EUtil.op(this.q(var11[0]), var14, var6);
               } else {
                  if (var11[1] == 0) {
                     throw new RuntimeException();
                  }

                  var15 = EUtil.op(this.q(var11[1]), var14, var7);
               }

               if (var10 != null) {
                  if (var11[2] != 0) {
                     var15 = EUtil.op(this.q(var11[2]), var15, var9);
                  } else {
                     if (var11[3] == 0) {
                        throw new RuntimeException();
                     }

                     var15 = EUtil.op(this.q(var11[3]), var15, var10);
                  }
               }

               return AbstractEExpressionOptimizer.EOR.create(var15);
            }
         }
      }
   }

   private OperationType q(int var1) {
      if (var1 == 1) {
         return OperationType.ADD;
      } else {
         return var1 == -1 ? OperationType.SUB : null;
      }
   }
}
