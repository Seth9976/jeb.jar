package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.math.BigInteger;

public class asc extends AbstractEExpressionOptimizer {
   private boolean q;

   public asc() {
      this(false);
   }

   public asc(boolean var1) {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.q = var1;
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IEOperation)) {
         return null;
      } else {
         OperationType var2 = ((IEOperation)var1).getOperationType();
         IEGeneric var3 = ((IEOperation)var1).getOperand1();
         Object var4 = ((IEOperation)var1).getOperand2();
         if (var2 == OperationType.XOR) {
            if (amw.q(var3, (IEGeneric)var4) && !EUtil.containsUndeterminedInvocations(var3)) {
               IEImm var36 = this.ectx.createImm(0L, var3.getBitsize());
               return AbstractEExpressionOptimizer.EOR.create(var36, EUtil.countVariableUse(var3) > 0);
            }

            if (var4 instanceof IEImm && ((IEGeneric)var4).asImm().isOnes()) {
               return AbstractEExpressionOptimizer.EOR.create(EUtil.notB(var3));
            }

            if (EUtil.isOperation(var3, OperationType.XOR) && EUtil.isOperation((IEGeneric)var4, OperationType.XOR)) {
               IEGeneric var5 = var3.asOperation().getOperand2();
               IEGeneric var6 = ((IEGeneric)var4).asOperation().getOperand2();
               if (var5.equalsEx(var6, false)) {
                  IEGeneric var78 = var3.asOperation().getOperand1();
                  IEGeneric var89 = ((IEGeneric)var4).asOperation().getOperand1();
                  return AbstractEExpressionOptimizer.EOR.create(EUtil.xorB(var78, var89), EUtil.countVariableUse(var5) > 0);
               }
            }
         }

         if (var2.isAnyOf(OperationType.AND, OperationType.MUL)) {
            if (EUtil.isImmZero((IEGeneric)var4)) {
               if (!EUtil.containsUndeterminedInvocations(var3)) {
                  return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var4, EUtil.countVariableUse(var3) > 0);
               }
            } else if (EUtil.isImmZero(var3) && !EUtil.containsUndeterminedInvocations((IEGeneric)var4)) {
               return AbstractEExpressionOptimizer.EOR.create(var3, EUtil.countVariableUse((IEGeneric)var4) > 0);
            }
         }

         if (var2.isAnyOf(OperationType.SHL, OperationType.SHR) && EUtil.isImmZero(var3) && !EUtil.containsUndeterminedInvocations((IEGeneric)var4)) {
            return AbstractEExpressionOptimizer.EOR.create(var3, EUtil.countVariableUse((IEGeneric)var4) > 0);
         } else if (var2.isAnyOf(OperationType.MUL, OperationType.DIV_S, OperationType.DIV_U) && EUtil.isImmValue((IEGeneric)var4, 1L)) {
            return AbstractEExpressionOptimizer.EOR.create(var3);
         } else if (var2 == OperationType.AND && EUtil.isImmValue((IEGeneric)var4, -1L)) {
            return AbstractEExpressionOptimizer.EOR.create(var3);
         } else {
            if (var2 == OperationType.OR) {
               if (EUtil.isImmValue((IEGeneric)var4, -1L)) {
                  if (!EUtil.containsUndeterminedInvocations(var3)) {
                     return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var4, EUtil.countVariableUse(var3) > 0);
                  }
               } else if (EUtil.isImmValue(var3, -1L) && !EUtil.containsUndeterminedInvocations((IEGeneric)var4)) {
                  return AbstractEExpressionOptimizer.EOR.create(var3, EUtil.countVariableUse((IEGeneric)var4) > 0);
               }
            }

            if (var2.isAnyOf(OperationType.OR, OperationType.ADD, OperationType.XOR)) {
               if (EUtil.isImmZero((IEGeneric)var4)) {
                  return AbstractEExpressionOptimizer.EOR.create(var3);
               }

               if (EUtil.isImmZero(var3)) {
                  return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var4);
               }
            }

            if (var2 == OperationType.SUB) {
               if (EUtil.isImmZero((IEGeneric)var4)) {
                  return AbstractEExpressionOptimizer.EOR.create(var3);
               }

               if (amw.q(var3, (IEGeneric)var4) && EUtil.hasNoSideEffect(var3)) {
                  return AbstractEExpressionOptimizer.EOR.create(EUtil.zero(var3.getBitsize()));
               }

               if (var3 instanceof IEImm && var3.asImm().isZero() && EUtil.isOperation((IEGeneric)var4, OperationType.SUB)) {
                  IEOperation var35 = ((IEGeneric)var4).asOperation();
                  IEGeneric var58 = var35.getOperand1();
                  IEGeneric var77 = var35.getOperand2();
                  return AbstractEExpressionOptimizer.EOR.create(EUtil.sub(var77, var58));
               }

               if (var4 instanceof IEOperation) {
                  IEOperation var16 = ((IEGeneric)var4).asOperation();
                  OperationType var37 = var16.getOperationType();
                  if ((var37 == OperationType.ADD || var37 == OperationType.SUB)
                     && var16.getOperand1() instanceof IEImm
                     && var16.getOperand1().asImm().isZero()) {
                     IEGeneric var88 = var16.getOperand2();
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.op(var37 == OperationType.ADD ? OperationType.SUB : OperationType.ADD, var3, var88));
                  }
               }

               if (EUtil.isOperation((IEGeneric)var4, OperationType.ADD)) {
                  IEGeneric var17 = ((IEOperation)var4).getOperand2();
                  if (amw.q(var3, var17)) {
                     IEGeneric var57 = ((IEOperation)var4).getOperand1();
                     return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(OperationType.SUB, EUtil.zero(var57.getBitsize()), var57), true);
                  }
               }

               if (EUtil.isOperation(var3, OperationType.SUB) && EUtil.isOperation((IEGeneric)var4, OperationType.SUB)) {
                  IEGeneric var18 = ((IEOperation)var3).getOperand2();
                  IEGeneric var38 = ((IEOperation)var4).getOperand2();
                  if (var18.equalsEx(var38, false) && EUtil.hasNoSideEffect(var18)) {
                     IEGeneric var76 = ((IEOperation)var3).getOperand1();
                     IEGeneric var87 = ((IEOperation)var4).getOperand1();
                     return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(OperationType.SUB, var76, var87), true);
                  }

                  IEGeneric var7 = ((IEOperation)var3).getOperand1();
                  IEGeneric var8 = ((IEOperation)var4).getOperand1();
                  if (var7.equalsEx(var8, false) && EUtil.hasNoSideEffect(var7) && (EUtil.hasNoSideEffect(var38) || EUtil.hasNoSideEffect(var18))) {
                     return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(OperationType.SUB, var38, var18), true);
                  }
               }
            }

            if (var2.isAnyOf(OperationType.AND, OperationType.OR) && amw.q(var3, (IEGeneric)var4) && !EUtil.containsUndeterminedInvocations(var3)) {
               return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var4, EUtil.countVariableUse(var3) > 0);
            } else {
               if (var2 == OperationType.LOG_OR) {
                  if (EUtil.isImmZero((IEGeneric)var4)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL(var3));
                  }

                  if (EUtil.isImmZero(var3)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL((IEGeneric)var4));
                  }

                  if (EUtil.isImmNonZero((IEGeneric)var4) && !EUtil.containsUndeterminedInvocations(var3)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL((IEGeneric)var4), EUtil.countVariableUse(var3) > 0);
                  }

                  if (EUtil.isImmNonZero(var3)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL(var3), EUtil.countVariableUse((IEGeneric)var4) > 0);
                  }
               }

               if (var2 == OperationType.LOG_AND) {
                  if (EUtil.isImmZero((IEGeneric)var4) && !EUtil.containsUndeterminedInvocations(var3)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL((IEGeneric)var4), EUtil.countVariableUse(var3) > 0);
                  }

                  if (EUtil.isImmZero(var3)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL(var3), EUtil.countVariableUse((IEGeneric)var4) > 0);
                  }

                  if (EUtil.isImmNonZero((IEGeneric)var4)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL(var3));
                  }

                  if (EUtil.isImmNonZero(var3)) {
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.identL((IEGeneric)var4));
                  }
               }

               if (var2.isAnyOf(OperationType.SHL, OperationType.SHR, OperationType.SAR) && EUtil.isImmZero((IEGeneric)var4)) {
                  return AbstractEExpressionOptimizer.EOR.create(var3);
               } else {
                  if (var2 == OperationType.AND && var3 instanceof IEOperation && var4 instanceof IEImm) {
                     IEOperation var19 = var3.asOperation();
                     OperationType var39 = var19.getOperationType();
                     if (var39 == OperationType.SHR && var19.getOperand2() instanceof IEImm) {
                        IEGeneric var59 = var19.getOperand1();
                        IEImm var79 = var19.getOperand2().asImm();
                        if (var79.canReadAsLong()) {
                           int var9 = var59.getBitsize();
                           int var10 = (int)var79.getValueAsLong();
                           if (var10 >= 1 && var10 < var9) {
                              IEImm var11 = ((IEGeneric)var4).asImm();
                              IEImm var12 = EUtil.one(var9);
                              IEImm var13 = var12._shl(var9 - var10)._sub(var12);
                              if (var13._and(var11._not()).isZero()) {
                                 return AbstractEExpressionOptimizer.EOR.create(var3);
                              }

                              IEImm var14 = var11._and(var13);
                              if (!var14.equalsEx(var11, false)) {
                                 return AbstractEExpressionOptimizer.EOR.create(EUtil.andB(var3, var14));
                              }
                           }
                        }
                     }
                  }

                  if (var2 == OperationType.NOT && EUtil.isOperation(var3, OperationType.NOT)) {
                     IEGeneric var34 = ((IEOperation)var3).getOperand1();
                     return AbstractEExpressionOptimizer.EOR.create(var34);
                  } else {
                     if (var2 == OperationType.NOT) {
                        if (EUtil.isOperation(var3, OperationType.OR)) {
                           IEOperation var20 = var3.asOperation();
                           if (EUtil.isOperation(var20.getOperand1(), OperationType.NOT) && var20.getOperand2() instanceof IEImm) {
                              IEGeneric var56 = var20.getOperand1().asOperation().getOperand1();
                              IEImm var75 = var20.getOperand2().asImm()._not();
                              return AbstractEExpressionOptimizer.EOR.create(EUtil.andB(var56, var75));
                           }
                        }

                        if (EUtil.isOperation(var3, OperationType.AND)) {
                           IEOperation var21 = var3.asOperation();
                           if (EUtil.isOperation(var21.getOperand1(), OperationType.NOT) && var21.getOperand2() instanceof IEImm) {
                              IEGeneric var55 = var21.getOperand1().asOperation().getOperand1();
                              IEImm var74 = var21.getOperand2().asImm()._not();
                              return AbstractEExpressionOptimizer.EOR.create(EUtil.orB(var55, var74));
                           }
                        }
                     }

                     if (var2 == OperationType.AND && EUtil.isOperation(var3, OperationType.NOT) && EUtil.isOperation((IEGeneric)var4, OperationType.NOT)) {
                        IEGeneric var33 = var3.asOperation().getOperand1();
                        IEGeneric var54 = ((IEGeneric)var4).asOperation().getOperand1();
                        IEGeneric var73 = EUtil.notB(EUtil.orB(var33, var54));
                        return AbstractEExpressionOptimizer.EOR.create(var73);
                     } else if (var2 == OperationType.OR && EUtil.isOperation(var3, OperationType.NOT) && EUtil.isOperation((IEGeneric)var4, OperationType.NOT)
                        )
                      {
                        IEGeneric var32 = var3.asOperation().getOperand1();
                        IEGeneric var53 = ((IEGeneric)var4).asOperation().getOperand1();
                        IEGeneric var72 = EUtil.notB(EUtil.andB(var32, var53));
                        return AbstractEExpressionOptimizer.EOR.create(var72);
                     } else {
                        if (var2 == OperationType.XOR) {
                           boolean var22 = EUtil.isOperation(var3, OperationType.NOT);
                           boolean var40 = EUtil.isOperation((IEGeneric)var4, OperationType.NOT);
                           if (var22 && var40) {
                              IEGeneric var71 = var3.asOperation().getOperand1();
                              IEGeneric var86 = ((IEGeneric)var4).asOperation().getOperand1();
                              return AbstractEExpressionOptimizer.EOR.create(EUtil.xorB(var71, var86));
                           }

                           if (var22 && !var40) {
                              IEGeneric var70 = var3.asOperation().getOperand1();
                              return AbstractEExpressionOptimizer.EOR.create(EUtil.notB(EUtil.xorB(var70, (IEGeneric)var4)));
                           }

                           if (!var22 && var40) {
                              IEGeneric var85 = ((IEGeneric)var4).asOperation().getOperand1();
                              return AbstractEExpressionOptimizer.EOR.create(EUtil.notB(EUtil.xorB(var3, var85)));
                           }
                        }

                        if (var2 == OperationType.ADD) {
                           if (var4 instanceof IEImm && EUtil.isOperation(var3, OperationType.NOT)) {
                              if (EUtil.isOne((IEGeneric)var4)) {
                                 IEGeneric var31 = ((IEOperation)var3).getOperand1();
                                 IEOperation var52 = EUtil.sub(EUtil.zero(var31.getBitsize()), var31);
                                 return AbstractEExpressionOptimizer.EOR.create(var52);
                              }

                              IEGeneric var30 = ((IEOperation)var3).getOperand1();
                              IEImm var51 = ((IEGeneric)var4).asImm();
                              IEImm var69 = EUtil.one(var51.getBitsize());
                              IEOperation var84 = EUtil.sub(var51._sub(var69), var30);
                              return AbstractEExpressionOptimizer.EOR.create(var84);
                           }

                           if (EUtil.isOperation(var3, OperationType.SUB)) {
                              IEGeneric var23 = ((IEOperation)var3).getOperand1();
                              if (EUtil.isZero(var23)) {
                                 IEGeneric var41 = ((IEOperation)var3).getOperand2();
                                 if (EUtil.hasNoSideEffect(var41)) {
                                    IEOperation var68 = EUtil.sub((IEGeneric)var4, var41);
                                    return AbstractEExpressionOptimizer.EOR.create(var68);
                                 }
                              }
                           }

                           if (EUtil.isOperation(var3, OperationType.SHR)) {
                              IEGeneric var24 = ((IEOperation)var3).getOperand1();
                              IEGeneric var42 = ((IEOperation)var3).getOperand2();
                              if (EUtil.hasNoSideEffect(var24) && EUtil.hasNoSideEffect((IEGeneric)var4) && EUtil.isImmValue(var42, var24.getBitsize() - 1)) {
                                 IEOperation var67 = EUtil.sub((IEGeneric)var4, EUtil.sar(var24, var42));
                                 return AbstractEExpressionOptimizer.EOR.create(var67);
                              }
                           }
                        }

                        if (var2 == OperationType.SUB) {
                           int var25 = var3.getBitsize();
                           if (var25 > 1 && var3.isImm() && var4 instanceof IECompose && ((IECompose)var4).getPartsCount() == 2) {
                              IEGeneric var43 = ((IECompose)var4).getPart(0);
                              IEGeneric var60 = ((IECompose)var4).getPart(1);
                              if (var43.getBitsize() == 1 && EUtil.isZero(var60)) {
                                 IECond var83 = this.ectx.createCond(var43, var3.asImm()._sub(EUtil.one(var25)), var3);
                                 return AbstractEExpressionOptimizer.EOR.create(var83);
                              }
                           }
                        }

                        if ((var2 == OperationType.ADD || var2 == OperationType.SUB) && var4 instanceof IEImm && ((IEImm)var4)._signum() < 0) {
                           var4 = ((IEImm)var4)._neg();
                           if (((IEImm)var4)._signum() > 0) {
                              var2 = var2 == OperationType.ADD ? OperationType.SUB : OperationType.ADD;
                              IEOperation var29 = this.ectx.createOperation(var2, var3, (IEGeneric)var4);
                              return AbstractEExpressionOptimizer.EOR.create(var29);
                           }
                        }

                        if (var2.getOperandCount() == 2 && !var2.isLogical() && var3.isCond() && EUtil.hasNoSideEffect((IEGeneric)var4)) {
                           IECond var26 = var3.asCond();
                           IEGeneric var44 = var26.getExpressionTrue();
                           IEGeneric var61 = var26.getExpressionFalse();
                           if ((((IEGeneric)var4).isImm() || ((IEGeneric)var4).isVar()) && var44.isImm() && var61.isImm()
                              || (EUtil.isImmZero(var44) || EUtil.isImmZero(var61)) && var2.isAnyOf(OperationType.AND, OperationType.SHL, OperationType.SHR)) {
                              IEOperation var50 = EUtil.op(var2, var44, (IEGeneric)var4);
                              IEOperation var66 = EUtil.op(var2, var61, ((IEGeneric)var4).duplicate());
                              return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var26.getCondition(), var50, var66));
                           }
                        }

                        if ((var2 == OperationType.LOG_EQ || var2 == OperationType.LOG_NEQ) && var3 instanceof IEOperation && var4 instanceof IEImm) {
                           IEOperation var27 = var3.asOperation();
                           OperationType var45 = var27.getOperationType();
                           if (var45.isAnyOf(OperationType.ADD, OperationType.SUB)) {
                              if (var27.getOperand2() instanceof IEImm) {
                                 IEImm var65 = var27.getOperand2().asImm();
                                 IEImm var82 = ((IEGeneric)var4).asImm();

                                 return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var2, var27.getOperand1(), switch (var45) {
                                    case ADD -> var82._sub(var65);
                                    case SUB -> var82._add(var65);
                                    default -> throw new RuntimeException();
                                 }));
                              }

                              if (var27.getOperand1() instanceof IEImm) {
                                 IEImm var64 = var27.getOperand1().asImm();
                                 IEImm var81 = ((IEGeneric)var4).asImm();

                                 return AbstractEExpressionOptimizer.EOR.create(this.ectx.createOperation(var2, var27.getOperand2(), switch (var45) {
                                    case ADD -> var81._sub(var64);
                                    case SUB -> var64._sub(var81);
                                    default -> throw new RuntimeException();
                                 }));
                              }
                           }
                        }

                        if (var2 == OperationType.SHR || var2 == OperationType.SHL || var2 == OperationType.SAR) {
                           int var28 = var3.getBitsize();
                           if (((IEGeneric)var4).isOperation(OperationType.AND)) {
                              IEOperation var46 = ((IEGeneric)var4).asOperation();
                              if (var46.getOperand2() instanceof IEImm && ((IEGeneric)var4).getBitsize() <= var28) {
                                 IEImm var62 = var46.getOperand2().asImm().signExtend(var28);
                                 IEImm var80 = EUtil.imm(var28 - 1, var28);
                                 if (var80._and(var62._not()).isZero() && var1.replaceSubExpression((IEGeneric)var4, var46.getOperand1())) {
                                    return AbstractEExpressionOptimizer.EOR.create(var1);
                                 }
                              }
                           }

                           if (((IEGeneric)var4).isOperation(OperationType.REM_U)) {
                              IEOperation var47 = ((IEGeneric)var4).asOperation();
                              if (var47.getOperand2() instanceof IEImm && ((IEGeneric)var4).getBitsize() <= var28) {
                                 IEImm var63 = var47.getOperand2().asImm();
                                 if (var63.getValue().equals(BigInteger.valueOf(var28)) && var1.replaceSubExpression((IEGeneric)var4, var47.getOperand1())) {
                                    return AbstractEExpressionOptimizer.EOR.create(var1);
                                 }
                              }
                           }

                           if (var4 instanceof IECompose) {
                              IEGeneric var48 = ((IEGeneric)var4).asCompose().getLowPart();
                              if (1L << var48.getBitsize() >= var28 && var1.replaceSubExpression((IEGeneric)var4, var48)) {
                                 return AbstractEExpressionOptimizer.EOR.create(var1);
                              }
                           }

                           if (var4 instanceof IESlice
                              && ((IESlice)var4).getBitStart() == 0
                              && 1L << ((IEGeneric)var4).getBitsize() >= var28
                              && (this.q || this.getMasterOptimizerSafe().getMode().isAggressive())) {
                              IEGeneric var49 = ((IEGeneric)var4).asSlice().getWholeExpression();
                              if (var1.replaceSubExpression((IEGeneric)var4, var49)) {
                                 return AbstractEExpressionOptimizer.EOR.create(var1);
                              }
                           }
                        }

                        if (var4 instanceof IEImm) {
                           if (var2 == OperationType.GE_U) {
                              if (((IEGeneric)var4).asImm().isZero()) {
                                 return AbstractEExpressionOptimizer.EOR.create(EUtil.one(1), EUtil.countVariableUse(var3) > 0);
                              }
                           } else if (var2 == OperationType.LT_U) {
                              if (((IEGeneric)var4).asImm().isZero()) {
                                 return AbstractEExpressionOptimizer.EOR.create(EUtil.zero(1), EUtil.countVariableUse(var3) > 0);
                              }
                           } else if (var2 == OperationType.GT_U) {
                              if (((IEGeneric)var4).asImm().isOnes()) {
                                 return AbstractEExpressionOptimizer.EOR.create(EUtil.one(1), EUtil.countVariableUse(var3) > 0);
                              }
                           } else if (var2 == OperationType.LE_U && ((IEGeneric)var4).asImm().isOnes()) {
                              return AbstractEExpressionOptimizer.EOR.create(EUtil.one(1), EUtil.countVariableUse(var3) > 0);
                           }
                        }

                        return null;
                     }
                  }
               }
            }
         }
      }
   }
}
