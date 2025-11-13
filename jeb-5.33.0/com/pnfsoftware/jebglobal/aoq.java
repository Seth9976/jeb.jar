package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.HashSet;
import java.util.Set;

public class aoq extends AbstractEExpressionOptimizer {
   public aoq() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.skipLeftSideOfAssignment = true;
   }

   @Override
   public AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEOperation) {
         IEOperation var3 = var1.asOperation();
         OperationType var4 = var3.getOperationType();
         if (var3.getOperand2() instanceof IEImm) {
            if (var4 == OperationType.SHR) {
               IEImm var5 = var3.getOperand2().asImm();
               if (var5.canReadAsLong() && var5.getValueAsLong() == var3.getOperand1().getBitsize() - 1) {
                  IEGeneric var6 = new aoq.Av(this.ectx).pC(var3.getOperand1());
                  if (var6 != null) {
                     IEGeneric var2 = EUtil.compose(this.ectx, var6, EUtil.zero(var3.getBitsize() - 1));
                     return AbstractEExpressionOptimizer.EOR.create(var2);
                  }
               }
            } else if (var4 == OperationType.AND) {
               IEImm var15 = var3.getOperand2().asImm();
               Integer var17 = var15._log2();
               if (var17 != null && var17 == var3.getOperand1().getBitsize() - 1) {
                  IEGeneric var7 = new aoq.Av(this.ectx).pC(var3.getOperand1());
                  if (var7 != null) {
                     IEGeneric var12 = EUtil.compose(this.ectx, EUtil.zero(var3.getBitsize() - 1), var7);
                     return AbstractEExpressionOptimizer.EOR.create(var12);
                  }
               }
            }
         } else if (var4.isAnyOf(OperationType.LOG_EQ, OperationType.LOG_NEQ)) {
            IEGeneric var16 = var3.getOperand1();
            IEGeneric var18 = var3.getOperand2();
            if (var16 instanceof IESlice && var18 instanceof IESlice) {
               IESlice var19 = var16.asSlice();
               IESlice var8 = var18.asSlice();
               int var9 = var19.getWholeExpression().getBitsize();
               if (var9 == var8.getWholeExpression().getBitsize()
                  && var19.getBitsize() == 1
                  && var19.getBitStart() == var9 - 1
                  && var8.getBitsize() == 1
                  && var8.getBitStart() == var9 - 1) {
                  Object var10 = EUtil.xorB(var19.getWholeExpression(), var8.getWholeExpression());
                  if (var4 == OperationType.LOG_EQ) {
                     var10 = EUtil.notB((IEGeneric)var10);
                  }

                  IEGeneric var11 = new aoq.Av(this.ectx).pC((IEGeneric)var10);
                  if (var11 != null) {
                     return AbstractEExpressionOptimizer.EOR.create(var11);
                  }
               }
            }
         }
      } else if (var1 instanceof IESlice) {
         IESlice var13 = var1.asSlice();
         if (var13.getBitsize() == 1 && var13.getBitStart() == var13.getWholeExpression().getBitsize() - 1) {
            IEGeneric var14 = new aoq.Av(this.ectx).pC(var13.getWholeExpression());
            if (var14 != null) {
               return AbstractEExpressionOptimizer.EOR.create(var14);
            }
         }
      }

      return null;
   }

   static class Av {
      IERoutineContext pC;
      IEGeneric A;
      IEGeneric kS;
      Set wS;

      Av(IERoutineContext var1) {
         this.pC = var1;
      }

      // $VF: Unable to simplify switch on enum
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      IEGeneric pC(IEGeneric var1) {
         if (!this.pC(var1, null)) {
            return null;
         } else if (this.wS == null || this.A == null || this.kS == null) {
            return null;
         } else if (this.A instanceof IEImm && this.kS instanceof IEImm) {
            return null;
         } else {
            int var2 = var1.getBitsize();
            Boolean var3 = null;
            Boolean var4 = null;
            if (this.A instanceof IEImm) {
               if (this.wS.size() != 1 || !((IEGeneric)this.wS.iterator().next()).equalsEx(this.kS, false)) {
                  return null;
               }

               var3 = !this.A.asImm()._shr(var2 - 1).isZero();
            } else if (this.kS instanceof IEImm) {
               if (this.wS.size() != 1 || !((IEGeneric)this.wS.iterator().next()).equalsEx(this.A, false)) {
                  return null;
               }

               var4 = !this.kS.asImm()._shr(var2 - 1).isZero();
            } else if (this.wS.size() != 2 || !this.wS.equals(Sets.newHashSet(this.A, this.kS))) {
               return null;
            }

            IEVar var5 = this.pC(var2, 0);
            IEVar var6 = this.pC(var2, 1);
            IEVar var7 = this.pC(var2, 2);
            IEGeneric var8 = var1.duplicate();
            if (!var8.visitDepthPre(new aos(this, var2, var5, var6, var7))) {
               return null;
            } else {
               EState var9 = this.pC.getGlobalContext().buildEmptyState();
               int[] var10 = new int[8];
               int var11 = 0;
               int[][] var12 = new int[][]{{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1}, {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};

               for (int[] var16 : var12) {
                  var9.setValue(var5, var16[0]);
                  var9.setValue(var6, var16[1]);
                  var9.setValue(var7, var16[2]);

                  try {
                     var10[var11] = (int)var8.evaluate(var9).getValueAsLong() & 1;
                     var11++;
                  } catch (EvaluationException var17) {
                     return null;
                  }
               }

               aoq.Av.Av var18 = this.pC(var10, var3, var4);
               if (var18 == null) {
                  return null;
               } else {
                  switch (aor.pC[var18.ordinal()]) {
                     case 1:
                        return EUtil.geU(this.A, this.kS);
                     case 2:
                        return EUtil.geS(this.A, this.kS);
                     case 3:
                        return EUtil.ltU(this.A, this.kS);
                     case 4:
                        return EUtil.ltS(this.A, this.kS);
                     default:
                        return null;
                  }
               }
            }
         }
      }

      private IEVar pC(int var1, int var2) {
         String var3 = "vreg" + var1 + "opt" + var2;
         IEVar var4 = this.pC.getGlobalContext().getVariableByName(var3);
         if (var4 == null) {
            var4 = this.pC.getGlobalContext().createVirtualRegister(var3, var1);
         }

         return var4;
      }

      boolean pC(IEGeneric var1, IEGeneric var2) {
         if (!(var1 instanceof IEImm)) {
            if (!(var1 instanceof IEVar) && !(var1 instanceof IEMem)) {
               if (!(var1 instanceof IEOperation)) {
                  return false;
               }

               IEOperation var3 = var1.asOperation();
               OperationType var4 = var3.getOperationType();
               if (var4 == OperationType.NOT) {
                  if (!this.pC(var3.getOperand1(), var3)) {
                     return false;
                  }
               } else if (var4.isAnyOf(OperationType.AND, OperationType.OR, OperationType.XOR)) {
                  if (!this.pC(var3.getOperand1(), var3)) {
                     return false;
                  }

                  if (!this.pC(var3.getOperand2(), var3)) {
                     return false;
                  }
               } else {
                  if (!var4.isAnyOf(OperationType.ADD, OperationType.SUB)) {
                     return false;
                  }

                  IEGeneric var5 = var3.getOperand1();
                  if (!this.A(var5)) {
                     return false;
                  }

                  Object var6 = var3.getOperand2();
                  if (var4 == OperationType.ADD) {
                     if (!(var6 instanceof IEImm)) {
                        return false;
                     }

                     var6 = ((IEGeneric)var6).asImm()._neg();
                  }

                  if (!this.kS((IEGeneric)var6)) {
                     return false;
                  }
               }
            } else {
               if (this.wS == null) {
                  this.wS = new HashSet();
               }

               if (this.wS.add(var1) && this.wS.size() > 2) {
                  return false;
               }
            }
         }

         return true;
      }

      boolean A(IEGeneric var1) {
         if (this.A == null) {
            this.A = var1;
            return true;
         } else {
            return this.A.equalsEx(var1, false);
         }
      }

      boolean kS(IEGeneric var1) {
         if (this.kS == null) {
            this.kS = var1;
            return true;
         } else {
            return this.kS.equalsEx(var1, false);
         }
      }

      aoq.Av.Av pC(int[] var1, Boolean var2, Boolean var3) {
         if (var2 == null && var3 == null) {
            switch (var1[0] << 1 | var1[2]) {
               case 0:
                  if (this.pC(var1, 0, 1, 0, 0, 1, 1, 0, 1)) {
                     return aoq.Av.Av.wS;
                  }
                  break;
               case 1:
                  if (this.pC(var1, 0, 1, 1, 1, 0, 0, 0, 1)) {
                     return aoq.Av.Av.kS;
                  }
                  break;
               case 2:
                  if (this.pC(var1, 1, 0, 0, 0, 1, 1, 1, 0)) {
                     return aoq.Av.Av.pC;
                  }
                  break;
               case 3:
                  if (this.pC(var1, 1, 0, 1, 1, 0, 0, 1, 0)) {
                     return aoq.Av.Av.A;
                  }
            }
         } else if (var2 == null && var3 != null) {
            if (!var3) {
               switch (var1[0] << 1 | var1[4]) {
                  case 0:
                     if (this.pC(var1, 0, 1, -1, -1, 0, 0, -1, -1)) {
                        return aoq.Av.Av.kS;
                     }
                     break;
                  case 1:
                     if (this.pC(var1, 0, 1, -1, -1, 1, 1, -1, -1)) {
                        return aoq.Av.Av.wS;
                     }
                     break;
                  case 2:
                     if (this.pC(var1, 1, 0, -1, -1, 0, 0, -1, -1)) {
                        return aoq.Av.Av.A;
                     }
                     break;
                  case 3:
                     if (this.pC(var1, 1, 0, -1, -1, 1, 1, -1, -1)) {
                        return aoq.Av.Av.pC;
                     }
               }
            } else {
               switch (var1[2] << 1 | var1[6]) {
                  case 0:
                     if (this.pC(var1, -1, -1, 0, 0, -1, -1, 0, 1)) {
                        return aoq.Av.Av.wS;
                     }
                     break;
                  case 1:
                     if (this.pC(var1, -1, -1, 0, 0, -1, -1, 1, 0)) {
                        return aoq.Av.Av.pC;
                     }
                     break;
                  case 2:
                     if (this.pC(var1, -1, -1, 1, 1, -1, -1, 0, 1)) {
                        return aoq.Av.Av.kS;
                     }
                     break;
                  case 3:
                     if (this.pC(var1, -1, -1, 1, 1, -1, -1, 1, 0)) {
                        return aoq.Av.Av.A;
                     }
               }
            }
         } else {
            if (var2 == null || var3 != null) {
               throw new RuntimeException();
            }

            if (!var2) {
               switch (var1[0] << 1 | var1[2]) {
                  case 0:
                     if (this.pC(var1, 0, 1, 0, 0, -1, -1, -1, -1)) {
                        return aoq.Av.Av.wS;
                     }
                     break;
                  case 1:
                     if (this.pC(var1, 0, 1, 1, 1, -1, -1, -1, -1)) {
                        return aoq.Av.Av.kS;
                     }
                     break;
                  case 2:
                     if (this.pC(var1, 1, 0, 0, 0, -1, -1, -1, -1)) {
                        return aoq.Av.Av.pC;
                     }
                     break;
                  case 3:
                     if (this.pC(var1, 1, 0, 1, 1, -1, -1, -1, -1)) {
                        return aoq.Av.Av.A;
                     }
               }
            } else {
               switch (var1[4] << 1 | var1[6]) {
                  case 0:
                     if (this.pC(var1, -1, -1, -1, -1, 0, 0, 0, 1)) {
                        return aoq.Av.Av.kS;
                     }
                     break;
                  case 1:
                     if (this.pC(var1, -1, -1, -1, -1, 0, 0, 1, 0)) {
                        return aoq.Av.Av.A;
                     }
                     break;
                  case 2:
                     if (this.pC(var1, -1, -1, -1, -1, 1, 1, 0, 1)) {
                        return aoq.Av.Av.wS;
                     }
                     break;
                  case 3:
                     if (this.pC(var1, -1, -1, -1, -1, 1, 1, 1, 0)) {
                        return aoq.Av.Av.pC;
                     }
               }
            }
         }

         return null;
      }

      boolean pC(int[] var1, int... var2) {
         for (int var3 = 0; var3 < 8; var3++) {
            if (var2[var3] >= 0 && var1[var3] != var2[var3]) {
               return false;
            }
         }

         return true;
      }

      static enum Av {
         pC,
         A,
         kS,
         wS;
      }
   }
}
