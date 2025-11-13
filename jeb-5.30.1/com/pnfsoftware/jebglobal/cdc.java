package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class cdc extends AbstractDOptimizer {
   @Override
   public int perform() {
      cdc.eo var1 = new cdc.eo();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstruction(var1, true);
      }

      return var1.q;
   }

   private static boolean q(IDExpression var0) {
      return bto.Dw(var0);
   }

   private static boolean RF(IDExpression var0) {
      return bto.xK(var0);
   }

   private IDExpression xK(IDExpression var1) {
      var1.setType(this.tf.getBoolean());
      return var1;
   }

   private IDExpression Dw(IDExpression var1) {
      return (IDExpression)(var1.isOperation(JavaOperatorType.NOT)
         ? var1.asOperation().getOperand2()
         : this.g.createOperation(var1.getType(), JavaOperatorType.NOT, null, var1));
   }

   class eo implements IDVisitor {
      int q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDOperation var4) {
            bvx var5 = this.q(var4, var2);
            if (var5 != null) {
               IDExpression var6 = var5.q();
               if (var2.replaceSubExpression(var1, var6)) {
                  var6.setType(var1.getType());
                  var3.setReplacedNode(var6);
                  if (var5.RF()) {
                     cdc.this.cfg.invalidateDataFlowAnalysis();
                  }

                  this.q++;
               }
            }
         }
      }

      public bvx q(IDOperation var1, IDExpression var2) {
         JavaOperatorType var3 = var1.getOperatorType();
         IDExpression var4 = var1.getOperand1();
         IDExpression var5 = var1.getOperand2();
         IJavaType var6 = var1.getType();
         if (var3 == JavaOperatorType.XOR) {
            if (var4.equalsEx(var5, false) && !var4.hasSideEffects(cdc.this.ctx, true)) {
               IDImm var29 = cdc.this.ctx.createImm(0L, var6);
               return bvx.q(var29, DUtil.hasVariables(var4));
            }

            if (DUtil.isImmValue(var5, -1L)) {
               return bvx.q(cdc.this.g.createOperation(var6, JavaOperatorType.NOT, null, var4));
            }
         }

         if (var3.isAnyOf(JavaOperatorType.AND, JavaOperatorType.MUL)) {
            if (DUtil.isImmZero(var5)) {
               if (!var4.hasSideEffects(cdc.this.ctx, true)) {
                  return bvx.q(var5, DUtil.hasVariables(var4));
               }
            } else if (DUtil.isImmZero(var4) && !var5.hasSideEffects(cdc.this.ctx, true)) {
               return bvx.q(var4, DUtil.hasVariables(var5));
            }
         }

         if (var3 == JavaOperatorType.MUL) {
            if (DUtil.isImmValue(var5, 1L)) {
               return bvx.q(var4);
            }

            if (DUtil.isImmValue(var4, 1L)) {
               return bvx.q(var5);
            }

            if (DUtil.isImmValue(var5, -1L)) {
               IDOperation var28 = cdc.this.ctx.createOperation(null, JavaOperatorType.NEG, null, var4);
               return bvx.q(var28);
            }

            if (DUtil.isImmValue(var4, -1L)) {
               IDOperation var27 = cdc.this.ctx.createOperation(null, JavaOperatorType.NEG, null, var5);
               return bvx.q(var27);
            }
         }

         if ((var3 == JavaOperatorType.ADD || var3 == JavaOperatorType.SUB)
            && var5.getType().isSmallInt()
            && var5 instanceof IDImm var7
            && var7.getRawValue() < 0L) {
            IDImm var8 = var7._neg();
            if (!var8.equalsEx(var7, false)) {
               var1.setOperator(var3 == JavaOperatorType.ADD ? JavaOperatorType.SUB : JavaOperatorType.ADD, cdc.this.of);
               var1.setOperand2(var8.duplicateWithDifferentType(var7.getType()));
               return bvx.q(var1);
            }
         }

         if (var3 == JavaOperatorType.DIV) {
            if (DUtil.isImmValue(var5, 1L)) {
               return bvx.q(var4);
            }

            if (DUtil.isImmZero(var4) && !DUtil.isImmZero(var5) && !var5.hasSideEffects(cdc.this.ctx, true)) {
               return bvx.q(var4, DUtil.hasVariables(var5));
            }
         }

         if (var3 == JavaOperatorType.NEG && var5 instanceof IDOperation var14 && var14.getOperatorType() == JavaOperatorType.NOT) {
            IDExpression var39 = var14.getOperand2();
            IDOperation var43 = cdc.this.g.createOperation(null, JavaOperatorType.ADD, var39, cdc.this.g.createImm(1L, var39.getType()));
            return bvx.q(var43);
         } else {
            if (var3 == JavaOperatorType.AND) {
               if (DUtil.isImmValue(var5, -1L)) {
                  return bvx.q(var4);
               }

               if (DUtil.isImmValue(var4, -1L)) {
                  return bvx.q(var5);
               }
            }

            if (var3 == JavaOperatorType.OR) {
               if (DUtil.isImmValue(var5, -1L)) {
                  if (!var4.hasSideEffects(cdc.this.ctx, true)) {
                     return bvx.q(var5, DUtil.hasVariables(var4));
                  }
               } else if (DUtil.isImmValue(var4, -1L) && !var5.hasSideEffects(cdc.this.ctx, true)) {
                  return bvx.q(var4, DUtil.hasVariables(var5));
               }
            }

            if (var3.isAnyOf(JavaOperatorType.OR, JavaOperatorType.ADD, JavaOperatorType.XOR)) {
               if (DUtil.isImmZero(var5)) {
                  return bvx.q(var4);
               }

               if (DUtil.isImmZero(var4)) {
                  return bvx.q(var5);
               }
            }

            if (var3 == JavaOperatorType.SUB) {
               if (DUtil.isImmZero(var5)) {
                  return bvx.q(var4);
               }

               if (var4.equalsEx(var5, false) && !var4.hasSideEffects(cdc.this.ctx, true)) {
                  return bvx.q(cdc.this.ctx.createImm(0L, var6));
               }
            }

            if (var3.isAnyOf(JavaOperatorType.AND, JavaOperatorType.OR) && var4.equalsEx(var5, false) && !var4.hasSideEffects(cdc.this.ctx, true)) {
               return bvx.q(var4);
            } else {
               if (var3 == JavaOperatorType.NOT && var5 instanceof IDOperation var15) {
                  JavaOperatorType var30 = var15.getOperatorType();
                  if (var30 == JavaOperatorType.NOT) {
                     IDExpression var42 = var15.getOperand2();
                     return bvx.q(var42);
                  }

                  if (var30.isAnyOf(JavaOperatorType.AND, JavaOperatorType.OR, JavaOperatorType.XOR)) {
                     IDExpression var9 = var15.getOperand1();
                     IDExpression var10 = var15.getOperand2();
                     boolean var11 = var9.isOperation(JavaOperatorType.NOT);
                     boolean var12 = var10.isOperation(JavaOperatorType.NOT);
                     if (var11 || var12) {
                        Object var13;
                        if (var30.isAnyOf(JavaOperatorType.AND, JavaOperatorType.OR)) {
                           var13 = cdc.this.g
                              .createOperation(
                                 var6, var30 == JavaOperatorType.AND ? JavaOperatorType.OR : JavaOperatorType.AND, cdc.this.Dw(var9), cdc.this.Dw(var10)
                              );
                        } else if (var11 && !var12) {
                           var13 = cdc.this.g.createOperation(var6, JavaOperatorType.XOR, cdc.this.Dw(var9), var10);
                        } else if (!var11 && var12) {
                           var13 = cdc.this.g.createOperation(var6, JavaOperatorType.XOR, var9, cdc.this.Dw(var10));
                        } else {
                           var13 = cdc.this.Dw(cdc.this.g.createOperation(var6, JavaOperatorType.XOR, cdc.this.Dw(var9), cdc.this.Dw(var10)));
                        }

                        return bvx.q((IDExpression)var13);
                     }
                  }
               }

               if (var3.isAnyOf(JavaOperatorType.AND, JavaOperatorType.OR) && var4.isOperation(JavaOperatorType.NOT) && var5.isOperation(JavaOperatorType.NOT)) {
                  IDExpression var26 = cdc.this.Dw(
                     cdc.this.g
                        .createOperation(var6, var3 == JavaOperatorType.AND ? JavaOperatorType.OR : JavaOperatorType.AND, cdc.this.Dw(var4), cdc.this.Dw(var5))
                  );
                  return bvx.q(var26);
               } else {
                  if (var3 == JavaOperatorType.XOR) {
                     boolean var16 = var4.isOperation(JavaOperatorType.NOT);
                     boolean var31 = var5.isOperation(JavaOperatorType.NOT);
                     if (var16 ^ var31) {
                        IDExpression var41 = var16 ? cdc.this.Dw(var4) : var4;
                        IDExpression var45 = var31 ? cdc.this.Dw(var5) : var5;
                        IDExpression var46 = cdc.this.Dw(cdc.this.g.createOperation(var6, JavaOperatorType.XOR, var41, var45));
                        return bvx.q(var46);
                     }
                  }

                  if (var3 == JavaOperatorType.ADD) {
                     if (var5 instanceof IDImm && var4.isOperation(JavaOperatorType.NOT) && DUtil.isImmValue(var5, 1L)) {
                        IDExpression var25 = ((IDOperation)var4).getOperand2();
                        IDOperation var38 = cdc.this.g.createOperation(var6, JavaOperatorType.SUB, cdc.this.g.createImm(0L, var5.getType()), var25);
                        return bvx.q(var38);
                     }

                     if (var4 instanceof IDOperation var17 && var17.getOperatorType() == JavaOperatorType.NEG) {
                        IDExpression var32 = var17.getOperand2();
                        if (!var32.hasSideEffects(cdc.this.ctx, true) || !var5.hasSideEffects(cdc.this.ctx, true)) {
                           var1.setOperand1(var5);
                           var1.setOperand2(var32);
                           var1.setOperator(JavaOperatorType.SUB, cdc.this.of);
                           return bvx.q(var1);
                        }
                     }

                     if (var4 instanceof IDImm && !(var5 instanceof IDOperation) && !(var5 instanceof IDImm)) {
                        var1.setOperand1(var5);
                        var1.setOperand2(var4);
                        return bvx.q(var1);
                     }
                  }

                  if (var3 == JavaOperatorType.SUB && var4 instanceof IDImm var18 && var5.isOperation(JavaOperatorType.NOT)) {
                     IDImm var37 = cdc.this.g.createImm(var18.getRawValue() + 1L, var18.getType());
                     IDExpression var40 = ((IDOperation)var5).getOperand2();
                     IDOperation var44 = cdc.this.g.createOperation(var6, JavaOperatorType.ADD, var40, var37);
                     return bvx.q(var44);
                  } else if (var3.isAnyOf(JavaOperatorType.ADD, JavaOperatorType.MUL, JavaOperatorType.XOR, JavaOperatorType.AND, JavaOperatorType.OR)
                     && var4 instanceof IDOperation var19
                     && var5 instanceof IDImm
                     && var19.getOperatorType() == var3
                     && var19.getOperand2() instanceof IDImm) {
                     IDOperation var36 = cdc.this.g
                        .createOperation(var6, var3, var19.getOperand1(), cdc.this.g.createOperation(var6, var3, var19.getOperand2(), var5));
                     return bvx.q(var36);
                  } else if ((var3 == JavaOperatorType.EQ || var3 == JavaOperatorType.NE)
                     && var4.equalsEx(var5, false)
                     && !var4.hasSideEffects(cdc.this.ctx, true)) {
                     return bvx.q(cdc.this.ctx.createBoolean(var3 == JavaOperatorType.EQ), DUtil.hasVariables(var4));
                  } else if (var3.isAnyOf(JavaOperatorType.LOG_AND, JavaOperatorType.LOG_OR)
                     && var4.equalsEx(var5, false)
                     && !var4.hasSideEffects(cdc.this.ctx, true)) {
                     return bvx.q(var4);
                  } else {
                     if (var3 == JavaOperatorType.LOG_OR) {
                        if (cdc.q(var5)) {
                           return bvx.q(cdc.this.xK(var4));
                        }

                        if (cdc.q(var4)) {
                           return bvx.q(cdc.this.xK(var5));
                        }

                        if (cdc.RF(var5) && !var4.hasSideEffects(cdc.this.ctx, true)) {
                           return bvx.q(cdc.this.xK(var5), DUtil.hasVariables(var4));
                        }

                        if (cdc.RF(var4)) {
                           return bvx.q(cdc.this.xK(var4), DUtil.hasVariables(var5));
                        }
                     }

                     if (var3 == JavaOperatorType.LOG_AND) {
                        if (cdc.q(var5) && !var4.hasSideEffects(cdc.this.ctx, true)) {
                           return bvx.q(cdc.this.xK(var5), DUtil.hasVariables(var4));
                        }

                        if (cdc.q(var4)) {
                           return bvx.q(cdc.this.xK(var4), DUtil.hasVariables(var5));
                        }

                        if (cdc.RF(var5)) {
                           return bvx.q(cdc.this.xK(var4));
                        }

                        if (cdc.RF(var4)) {
                           return bvx.q(cdc.this.xK(var5));
                        }
                     }

                     if (var3 == JavaOperatorType.LOG_NOT && var5 instanceof IDOperation var20 && var20.getOperatorType() == JavaOperatorType.LOG_NOT) {
                        IDExpression var35 = var20.getOperand2();
                        return bvx.q(var35);
                     } else if (var3.isAnyOf(JavaOperatorType.SHL, JavaOperatorType.SHR, JavaOperatorType.USHR) && DUtil.isImmZero(var5)) {
                        return bvx.q(var4);
                     } else {
                        if (var3.isAnyOf(JavaOperatorType.ADD, JavaOperatorType.SUB)) {
                           if (var5 instanceof IDImm var21) {
                              if (var6.isInt()) {
                                 if (var5.getType().isInt() && (int)var21.getRawValue() == Integer.MIN_VALUE) {
                                    var1.setOperator(JavaOperatorType.XOR, cdc.this.of);
                                    return bvx.q(var1);
                                 }
                              } else if (var6.isLong() && var5.getType().isLong() && var21.getRawValue() == Long.MIN_VALUE) {
                                 var1.setOperator(JavaOperatorType.XOR, cdc.this.of);
                                 return bvx.q(var1);
                              }
                           }

                           if (var4 instanceof IDImm var22) {
                              if (var6.isInt()) {
                                 if (var4.getType().isInt() && (int)var22.getRawValue() == Integer.MIN_VALUE) {
                                    IDOperation var33 = cdc.this.ctx.createOperation(null, JavaOperatorType.XOR, var5, var4);
                                    return bvx.q(var33);
                                 }
                              } else if (var6.isLong() && var4.getType().isLong() && var22.getRawValue() == Long.MIN_VALUE) {
                                 IDOperation var34 = cdc.this.ctx.createOperation(null, JavaOperatorType.XOR, var5, var4);
                                 return bvx.q(var34);
                              }
                           }
                        }

                        if (var3.isAnyOf(JavaOperatorType.CAST_TO_OBJECT) && var5 instanceof IDImm var23 && var23.maybeNullRef()) {
                           return bvx.q(var23.duplicateWithDifferentType(var6));
                        } else if (var3 == JavaOperatorType.XOR && var4.getType().isBoolean() && var5.isConstantImm(1L)) {
                           IDOperation var24 = cdc.this.g.createOperation(null, JavaOperatorType.LOG_NOT, null, var4);
                           return bvx.q(var24);
                        } else {
                           return null;
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
