package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;

public class byk extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(byk.class);
   private cdg A;
   private cdk kS;

   @Override
   public int perform() {
      this.A = (cdg)this.of;
      this.kS = (cdk)this.tf;
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);

            int var6;
            do {
               var6 = 0;

               for (IDExpression var8 : var5.getSubExpressions()) {
                  var6 += this.pC(var5, var8);
               }

               var1 += var6;
            } while (var6 > 0);
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean pC(IDExpression var1) {
      if (var1.getType().isObject()) {
         return false;
      } else {
         return !bpl.pC(var1)
            ? false
            : !(
               var1 instanceof IDOperation var2
                  && var2.getOperatorType().isAnyOf(JavaOperatorType.DIV, JavaOperatorType.REM)
                  && var2.getOperand2() instanceof IDImm var3
                  && var3.isZero()
            );
      }
   }

   private int pC(IDExpression var1, IDExpression var2) {
      Object var3 = null;

      try {
         if (var2 instanceof IDOperation var4) {
            if (var3 == null && this.pC(var2)) {
               try {
                  IDImm var18 = var2.evaluate(this.ctx);
                  if (var18 != null && !var18.isRef() && !var18.equalsEx(var2, false)) {
                     var3 = var18;
                  }
               } catch (Exception var14) {
                  var3 = null;
               }
            }

            if (var3 == null
               && var4.getType().isBoolean()
               && var4.getOperator().isLogical()
               && var4.getLeft() instanceof IDImm var19
               && var4.getRight() instanceof IDImm var6) {
               long var35 = this.pC(var19.getRawValue(), var19.getType());
               long var9 = this.pC(var6.getRawValue(), var6.getType());
               boolean var11 = false;
               boolean var12 = true;
               if (var4.getOperator() == this.A.Ek) {
                  var11 = var35 > var9;
               } else if (var4.getOperator() == this.A.z) {
                  var11 = var35 >= var9;
               } else if (var4.getOperator() == this.A.rl) {
                  var11 = var35 < var9;
               } else if (var4.getOperator() == this.A.hK) {
                  var11 = var35 <= var9;
               } else if (var4.getOperator() == this.A.UO) {
                  var11 = var35 == var9;
               } else if (var4.getOperator() != this.A.Ab) {
                  var11 = var35 != var9;
               } else {
                  var12 = false;
               }

               if (var12) {
                  var3 = this.g.createBoolean(var11);
               }
            }

            if (var3 == null && var4.isConditional()) {
               IDExpression var20 = var4.getCondPredicate();
               if (bpl.kS(var20)) {
                  var3 = var4.getCondTrueExpression();
               } else if (bpl.wS(var20)) {
                  var3 = var4.getCondFalseExpression();
               }
            }

            if (var3 == null) {
               var4.getType().isFloatingPointType();
            }

            if (var3 == null && var4.getType().isSpecificInteger()) {
               if (var3 == null && var4.getLeft() instanceof IDImm && var4.getRight() instanceof IDImm && var4.getOperator().isArithmetic()) {
                  IDImm var21 = (IDImm)var4.getLeft();
                  IDImm var28 = (IDImm)var4.getRight();
                  long var36 = this.pC(var21.getRawValue(), var21.getType());
                  long var47 = this.pC(var28.getRawValue(), var28.getType());
                  long var49 = 0L;
                  boolean var13 = true;
                  if (var4.getOperator() == this.A.kS) {
                     var49 = var36 + var47;
                  } else if (var4.getOperator() == this.A.wS) {
                     var49 = var36 - var47;
                  } else if (var4.getOperator() == this.A.UT) {
                     var49 = var36 * var47;
                  } else if (var4.getOperator() == this.A.E && var47 != 0L) {
                     var49 = var36 / var47;
                  } else if (var4.getOperator() == this.A.sY && var47 != 0L) {
                     var49 = var36 % var47;
                  } else if (var4.getOperator() == this.A.ys) {
                     var49 = var36 & var47;
                  } else if (var4.getOperator() == this.A.ld) {
                     var49 = var36 | var47;
                  } else if (var4.getOperator() == this.A.gp) {
                     var49 = var36 ^ var47;
                  } else if (var4.getOperator() == this.A.oT) {
                     var49 = var36 << (int)var47;
                  } else if (var4.getOperator() == this.A.fI) {
                     var36 = this.A(var21.getRawValue(), var21.getType());
                     var49 = var36 >> (int)var47;
                  } else if (var4.getOperator() == this.A.WR) {
                     var49 = var36 >>> (int)var47;
                  } else {
                     var13 = false;
                  }

                  if (var13) {
                     var3 = this.g.createConstant(this.pC(var49, var4.getType()), var4.getType());
                  }
               }

               if (var3 == null) {
                  IDImm var22 = null;
                  if (var4.getLeft() instanceof IDImm) {
                     var22 = (IDImm)var4.getLeft();
                  }

                  IDImm var29 = null;
                  if (var4.getRight() instanceof IDImm) {
                     var29 = (IDImm)var4.getRight();
                  }

                  if (var22 != null || var29 != null) {
                     long var38 = -1L;
                     if (var22 != null) {
                        var38 = this.pC(var22.getRawValue(), var22.getType());
                     }

                     long var48 = -1L;
                     if (var29 != null) {
                        var48 = this.pC(var29.getRawValue(), var29.getType());
                     }

                     IJavaOperator var50 = var4.getOperator();
                     if (var50 == this.A.kS) {
                        if (var38 == 0L) {
                           var3 = var4.getRight();
                        } else if (var48 == 0L) {
                           var3 = var4.getLeft();
                        }
                     } else if (var50 == this.A.wS) {
                        if (var38 == 0L) {
                           var3 = this.g.createOperation(var4.getType(), null, this.A.NS, var4.getRight());
                        } else if (var48 == 0L) {
                           var3 = var4.getLeft();
                        }
                     } else if (var50 == this.A.UT) {
                        if (var38 == 1L) {
                           var3 = var4.getRight();
                        } else if (var48 == 1L) {
                           var3 = var4.getLeft();
                        } else if (var38 == 0L) {
                           var3 = var4.getLeft();
                        } else if (var48 == 0L) {
                           var3 = var4.getRight();
                        }
                     } else if (var50 == this.A.E) {
                        if (var48 == 1L) {
                           var3 = var4.getLeft();
                        }
                     } else if (var50 == this.A.sY && var48 == 1L) {
                        var3 = this.g.createConstant(0L, var4.getType());
                     }
                  }
               }

               if (var3 == null) {
                  IDExpression var23 = var4.getRight();
                  IJavaOperator var30 = var4.getOperator();
                  if (var30 == this.A.NS && var23 instanceof IDOperation && ((IDOperation)var23).getOperator() == this.A.NS) {
                     IDExpression var39 = ((IDOperation)var23).getRight();
                     var3 = var39;
                  }
               }

               if (var3 == null) {
                  IDExpression var24 = var4.getLeft();
                  IDExpression var31 = var4.getRight();
                  IJavaOperator var40 = var4.getOperator();
                  if (var40 != this.A.wS && var40 != this.A.gp) {
                     if (var40 == this.A.kS) {
                        if (var31 instanceof IDOperation && ((IDOperation)var31).getOperator() == this.A.NS) {
                           IDExpression var45 = ((IDOperation)var31).getRight();
                           var3 = this.g.createOperation(var4.getType(), var24, this.A.wS, var45);
                        }
                     } else if (var40 == this.A.ys) {
                        if (var24.equalsEx(var31, false)) {
                           var3 = var24;
                        } else if (var24 instanceof IDOperation && ((IDOperation)var24).getOperator() == this.A.vP) {
                           IDExpression var44 = ((IDOperation)var24).getRight();
                           if (var44.equalsEx(var31, false)) {
                              var3 = this.g.createConstant(0L, var4.getType());
                           }
                        } else if (var31 instanceof IDOperation && ((IDOperation)var31).getOperator() == this.A.vP) {
                           IDExpression var43 = ((IDOperation)var31).getRight();
                           if (var43.equalsEx(var24, false)) {
                              var3 = this.g.createConstant(0L, var4.getType());
                           }
                        }
                     } else if (var40 == this.A.ld) {
                        if (var24.equalsEx(var31, false)) {
                           var3 = var24;
                        } else if (var24 instanceof IDOperation && ((IDOperation)var24).getOperator() == this.A.vP) {
                           IDExpression var42 = ((IDOperation)var24).getRight();
                           if (var42.equalsEx(var31, false)) {
                              var3 = this.g.createConstant(-1L, var4.getType());
                           }
                        } else if (var31 instanceof IDOperation && ((IDOperation)var31).getOperator() == this.A.vP) {
                           IDExpression var8 = ((IDOperation)var31).getRight();
                           if (var8.equalsEx(var24, false)) {
                              var3 = this.g.createConstant(-1L, var4.getType());
                           }
                        }
                     }
                  } else if (var24.equalsEx(var31, false)) {
                     var3 = this.g.createConstant(0L, var24.getType());
                  }
               }

               if (var3 == null && var4.getOperator() == this.A.kS) {
                  IDImm var25 = null;
                  IDOperation var32 = null;
                  if (var4.getLeft() instanceof IDImm && var4.getRight() instanceof IDOperation) {
                     var25 = (IDImm)var4.getLeft();
                     var32 = (IDOperation)var4.getRight();
                  } else if (var4.getLeft() instanceof IDOperation && var4.getRight() instanceof IDImm) {
                     var32 = (IDOperation)var4.getLeft();
                     var25 = (IDImm)var4.getRight();
                  }

                  if (var25 != null && var32 != null && var32.getOperator() == this.A.kS) {
                     IDImm var41 = null;
                     if (var32.getRight() instanceof IDImm) {
                        var41 = (IDImm)var32.getRight();
                     } else if (var32.getLeft() instanceof IDImm) {
                        var41 = (IDImm)var32.getLeft();
                     }

                     if (var41 != null) {
                        long var46 = this.pC(var25.getRawValue() + var41.getRawValue(), var4.getType());
                        var32.replaceSubExpression(var41, this.g.createConstant(var46, var4.getType()));
                        var3 = var32;
                     }
                  }
               }

               if (var3 == null) {
                  var3 = this.pC(var4);
               }
            }
         }
      } catch (Exception var15) {
         HashMap var5 = Maps.toMap("ir-expression", var2.toString());
         com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("ir-folder: generic error", var15), this.ctx.getMethodSignature(), -1, var5);
         var3 = null;
      }

      if (var3 != null) {
         boolean var16 = var1.replaceSubExpression(var2, (IDExpression)var3);
         if (var16) {
            return 1;
         }

         HashMap var26 = Maps.toMap("ir-expression", var2.toString());
         com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
            new RuntimeException("ir-folder: replaceSubExpression failed"), this.ctx.getMethodSignature(), -1, var26
         );
      }

      int var17 = 0;

      for (IDExpression var33 : var2.getSubExpressions()) {
         var17 += this.pC(var2, var33);
      }

      return var17;
   }

   private IDExpression pC(IDOperation var1) {
      IJavaOperator var2 = var1.getOperator();
      if (var2 != this.A.kS && var2 != this.A.wS) {
         return null;
      } else {
         boolean var5 = false;
         IDImm var3;
         IDOperation var4;
         if (var1.getLeft() instanceof IDOperation && var1.getRight() instanceof IDImm) {
            var4 = (IDOperation)var1.getLeft();
            var3 = (IDImm)var1.getRight();
         } else {
            if (!(var1.getLeft() instanceof IDImm) || !(var1.getRight() instanceof IDOperation)) {
               return null;
            }

            var3 = (IDImm)var1.getLeft();
            var4 = (IDOperation)var1.getRight();
            var5 = true;
         }

         IJavaOperator var6 = var4.getOperator();
         if (var6 != this.A.kS && var6 != this.A.wS) {
            return null;
         } else {
            boolean var9 = false;
            IDExpression var7;
            IDImm var8;
            if (var4.getRight() instanceof IDImm) {
               var7 = var4.getLeft();
               var8 = (IDImm)var4.getRight();
            } else {
               if (!(var4.getLeft() instanceof IDImm)) {
                  return null;
               }

               var7 = var4.getRight();
               var8 = (IDImm)var4.getLeft();
               var9 = true;
            }

            long var10 = var8.getRawValue();
            long var12 = var3.getRawValue();
            boolean var16 = false;
            long var14;
            if (var2 == this.A.kS && var6 == this.A.kS) {
               var14 = var10 + var12;
            } else if (var2 == this.A.kS && var6 == this.A.wS) {
               if (!var9) {
                  var14 = -var10 + var12;
               } else {
                  var14 = var10 + var12;
                  var16 = true;
               }
            } else if (var2 == this.A.wS && var6 == this.A.kS) {
               if (!var5) {
                  var14 = var10 - var12;
               } else {
                  var14 = var12 - var10;
                  var16 = true;
               }
            } else if (!var5) {
               if (!var9) {
                  var14 = -var10 - var12;
               } else {
                  var14 = var10 - var12;
                  var16 = true;
               }
            } else if (!var9) {
               var14 = var12 + var10;
               var16 = true;
            } else {
               var14 = var12 - var10;
            }

            long var17 = this.pC(var14, var1.getType());
            IDImm var19 = this.g.createConstant(var17, var1.getType());
            Object var20;
            Object var21;
            cde var22;
            if (!var16) {
               var20 = var7;
               var22 = this.A.kS;
               var21 = var19;
            } else {
               var20 = var19;
               var22 = this.A.wS;
               var21 = var7;
            }

            return this.g.createOperation(var1.getType(), (IDExpression)var20, var22, (IDExpression)var21);
         }
      }
   }

   private long pC(long var1, IJavaType var3) {
      if (var3 == this.kS.A) {
         var1 &= 1L;
      } else if (var3 == this.kS.kS) {
         var1 &= 255L;
      } else if (var3 == this.kS.wS || var3 == this.kS.UT) {
         var1 &= 65535L;
      } else if (var3 != this.kS.E && var3 != this.kS.ys) {
         if (var3 != this.kS.sY && var3 != this.kS.ld) {
            throw new IllegalArgumentException("Loose type: " + var3);
         }
      } else {
         var1 &= 4294967295L;
      }

      return var1;
   }

   private long A(long var1, IJavaType var3) {
      if (var3 == this.kS.A) {
         var1 &= 1L;
      } else if (var3 == this.kS.kS) {
         var1 &= 255L;
         if ((var1 & 128L) != 0L) {
            var1 |= -256L;
         }
      } else if (var3 == this.kS.wS) {
         var1 &= 65535L;
      } else if (var3 == this.kS.UT) {
         var1 &= 65535L;
         if ((var1 & 32768L) != 0L) {
            var1 |= -65536L;
         }
      } else if (var3 == this.kS.E) {
         var1 &= 4294967295L;
         if ((var1 & 2147483648L) != 0L) {
            var1 |= -4294967296L;
         }
      }

      return var1;
   }
}
