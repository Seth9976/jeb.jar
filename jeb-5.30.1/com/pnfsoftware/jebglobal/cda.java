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

public class cda extends AbstractDOptimizer {
   private static final ILogger q = GlobalLog.getLogger(cda.class);
   private cio RF;
   private cis xK;

   @Override
   public int perform() {
      this.RF = (cio)this.of;
      this.xK = (cis)this.tf;
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);

            int var6;
            do {
               var6 = 0;

               for (IDExpression var8 : var5.getSubExpressions()) {
                  var6 += this.q(var5, var8);
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

   private boolean q(IDExpression var1) {
      if (var1.getType().isObject()) {
         return false;
      } else {
         return !bto.q(var1)
            ? false
            : !(
               var1 instanceof IDOperation var2
                  && var2.getOperatorType().isAnyOf(JavaOperatorType.DIV, JavaOperatorType.REM)
                  && var2.getOperand2() instanceof IDImm var3
                  && var3.isZero()
            );
      }
   }

   private int q(IDExpression var1, IDExpression var2) {
      Object var3 = null;

      try {
         if (var2 instanceof IDOperation var4) {
            if (var3 == null && this.q(var2)) {
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
               long var35 = this.q(var19.getRawValue(), var19.getType());
               long var9 = this.q(var6.getRawValue(), var6.getType());
               boolean var11 = false;
               boolean var12 = true;
               if (var4.getOperator() == this.RF.Ef) {
                  var11 = var35 > var9;
               } else if (var4.getOperator() == this.RF.Gf) {
                  var11 = var35 >= var9;
               } else if (var4.getOperator() == this.RF.KT) {
                  var11 = var35 < var9;
               } else if (var4.getOperator() == this.RF.cC) {
                  var11 = var35 <= var9;
               } else if (var4.getOperator() == this.RF.oQ) {
                  var11 = var35 == var9;
               } else if (var4.getOperator() != this.RF.xW) {
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
               if (bto.xK(var20)) {
                  var3 = var4.getCondTrueExpression();
               } else if (bto.Dw(var20)) {
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
                  long var36 = this.q(var21.getRawValue(), var21.getType());
                  long var47 = this.q(var28.getRawValue(), var28.getType());
                  long var49 = 0L;
                  boolean var13 = true;
                  if (var4.getOperator() == this.RF.xK) {
                     var49 = var36 + var47;
                  } else if (var4.getOperator() == this.RF.Dw) {
                     var49 = var36 - var47;
                  } else if (var4.getOperator() == this.RF.Uv) {
                     var49 = var36 * var47;
                  } else if (var4.getOperator() == this.RF.oW && var47 != 0L) {
                     var49 = var36 / var47;
                  } else if (var4.getOperator() == this.RF.gO && var47 != 0L) {
                     var49 = var36 % var47;
                  } else if (var4.getOperator() == this.RF.nf) {
                     var49 = var36 & var47;
                  } else if (var4.getOperator() == this.RF.gP) {
                     var49 = var36 | var47;
                  } else if (var4.getOperator() == this.RF.za) {
                     var49 = var36 ^ var47;
                  } else if (var4.getOperator() == this.RF.lm) {
                     var49 = var36 << (int)var47;
                  } else if (var4.getOperator() == this.RF.zz) {
                     var36 = this.RF(var21.getRawValue(), var21.getType());
                     var49 = var36 >> (int)var47;
                  } else if (var4.getOperator() == this.RF.JY) {
                     var49 = var36 >>> (int)var47;
                  } else {
                     var13 = false;
                  }

                  if (var13) {
                     var3 = this.g.createConstant(this.q(var49, var4.getType()), var4.getType());
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
                        var38 = this.q(var22.getRawValue(), var22.getType());
                     }

                     long var48 = -1L;
                     if (var29 != null) {
                        var48 = this.q(var29.getRawValue(), var29.getType());
                     }

                     IJavaOperator var50 = var4.getOperator();
                     if (var50 == this.RF.xK) {
                        if (var38 == 0L) {
                           var3 = var4.getRight();
                        } else if (var48 == 0L) {
                           var3 = var4.getLeft();
                        }
                     } else if (var50 == this.RF.Dw) {
                        if (var38 == 0L) {
                           var3 = this.g.createOperation(var4.getType(), null, this.RF.HF, var4.getRight());
                        } else if (var48 == 0L) {
                           var3 = var4.getLeft();
                        }
                     } else if (var50 == this.RF.Uv) {
                        if (var38 == 1L) {
                           var3 = var4.getRight();
                        } else if (var48 == 1L) {
                           var3 = var4.getLeft();
                        } else if (var38 == 0L) {
                           var3 = var4.getLeft();
                        } else if (var48 == 0L) {
                           var3 = var4.getRight();
                        }
                     } else if (var50 == this.RF.oW) {
                        if (var48 == 1L) {
                           var3 = var4.getLeft();
                        }
                     } else if (var50 == this.RF.gO && var48 == 1L) {
                        var3 = this.g.createConstant(0L, var4.getType());
                     }
                  }
               }

               if (var3 == null) {
                  IDExpression var23 = var4.getRight();
                  IJavaOperator var30 = var4.getOperator();
                  if (var30 == this.RF.HF && var23 instanceof IDOperation && ((IDOperation)var23).getOperator() == this.RF.HF) {
                     IDExpression var39 = ((IDOperation)var23).getRight();
                     var3 = var39;
                  }
               }

               if (var3 == null) {
                  IDExpression var24 = var4.getLeft();
                  IDExpression var31 = var4.getRight();
                  IJavaOperator var40 = var4.getOperator();
                  if (var40 != this.RF.Dw && var40 != this.RF.za) {
                     if (var40 == this.RF.xK) {
                        if (var31 instanceof IDOperation && ((IDOperation)var31).getOperator() == this.RF.HF) {
                           IDExpression var45 = ((IDOperation)var31).getRight();
                           var3 = this.g.createOperation(var4.getType(), var24, this.RF.Dw, var45);
                        }
                     } else if (var40 == this.RF.nf) {
                        if (var24.equalsEx(var31, false)) {
                           var3 = var24;
                        } else if (var24 instanceof IDOperation && ((IDOperation)var24).getOperator() == this.RF.LK) {
                           IDExpression var44 = ((IDOperation)var24).getRight();
                           if (var44.equalsEx(var31, false)) {
                              var3 = this.g.createConstant(0L, var4.getType());
                           }
                        } else if (var31 instanceof IDOperation && ((IDOperation)var31).getOperator() == this.RF.LK) {
                           IDExpression var43 = ((IDOperation)var31).getRight();
                           if (var43.equalsEx(var24, false)) {
                              var3 = this.g.createConstant(0L, var4.getType());
                           }
                        }
                     } else if (var40 == this.RF.gP) {
                        if (var24.equalsEx(var31, false)) {
                           var3 = var24;
                        } else if (var24 instanceof IDOperation && ((IDOperation)var24).getOperator() == this.RF.LK) {
                           IDExpression var42 = ((IDOperation)var24).getRight();
                           if (var42.equalsEx(var31, false)) {
                              var3 = this.g.createConstant(-1L, var4.getType());
                           }
                        } else if (var31 instanceof IDOperation && ((IDOperation)var31).getOperator() == this.RF.LK) {
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

               if (var3 == null && var4.getOperator() == this.RF.xK) {
                  IDImm var25 = null;
                  IDOperation var32 = null;
                  if (var4.getLeft() instanceof IDImm && var4.getRight() instanceof IDOperation) {
                     var25 = (IDImm)var4.getLeft();
                     var32 = (IDOperation)var4.getRight();
                  } else if (var4.getLeft() instanceof IDOperation && var4.getRight() instanceof IDImm) {
                     var32 = (IDOperation)var4.getLeft();
                     var25 = (IDImm)var4.getRight();
                  }

                  if (var25 != null && var32 != null && var32.getOperator() == this.RF.xK) {
                     IDImm var41 = null;
                     if (var32.getRight() instanceof IDImm) {
                        var41 = (IDImm)var32.getRight();
                     } else if (var32.getLeft() instanceof IDImm) {
                        var41 = (IDImm)var32.getLeft();
                     }

                     if (var41 != null) {
                        long var46 = this.q(var25.getRawValue() + var41.getRawValue(), var4.getType());
                        var32.replaceSubExpression(var41, this.g.createConstant(var46, var4.getType()));
                        var3 = var32;
                     }
                  }
               }

               if (var3 == null) {
                  var3 = this.q(var4);
               }
            }
         }
      } catch (Exception var15) {
         HashMap var5 = Maps.toMap("ir-expression", var2.toString());
         com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("ir-folder: generic error", var15), this.ctx.getMethodSignature(), -1, var5);
         var3 = null;
      }

      if (var3 != null) {
         boolean var16 = var1.replaceSubExpression(var2, (IDExpression)var3);
         if (var16) {
            return 1;
         }

         HashMap var26 = Maps.toMap("ir-expression", var2.toString());
         com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("ir-folder: replaceSubExpression failed"), this.ctx.getMethodSignature(), -1, var26);
      }

      int var17 = 0;

      for (IDExpression var33 : var2.getSubExpressions()) {
         var17 += this.q(var2, var33);
      }

      return var17;
   }

   private IDExpression q(IDOperation var1) {
      IJavaOperator var2 = var1.getOperator();
      if (var2 != this.RF.xK && var2 != this.RF.Dw) {
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
         if (var6 != this.RF.xK && var6 != this.RF.Dw) {
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
            if (var2 == this.RF.xK && var6 == this.RF.xK) {
               var14 = var10 + var12;
            } else if (var2 == this.RF.xK && var6 == this.RF.Dw) {
               if (!var9) {
                  var14 = -var10 + var12;
               } else {
                  var14 = var10 + var12;
                  var16 = true;
               }
            } else if (var2 == this.RF.Dw && var6 == this.RF.xK) {
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

            long var17 = this.q(var14, var1.getType());
            IDImm var19 = this.g.createConstant(var17, var1.getType());
            Object var20;
            Object var21;
            cim var22;
            if (!var16) {
               var20 = var7;
               var22 = this.RF.xK;
               var21 = var19;
            } else {
               var20 = var19;
               var22 = this.RF.Dw;
               var21 = var7;
            }

            return this.g.createOperation(var1.getType(), (IDExpression)var20, var22, (IDExpression)var21);
         }
      }
   }

   private long q(long var1, IJavaType var3) {
      if (var3 == this.xK.RF) {
         var1 &= 1L;
      } else if (var3 == this.xK.xK) {
         var1 &= 255L;
      } else if (var3 == this.xK.Dw || var3 == this.xK.Uv) {
         var1 &= 65535L;
      } else if (var3 != this.xK.oW && var3 != this.xK.nf) {
         if (var3 != this.xK.gO && var3 != this.xK.gP) {
            throw new IllegalArgumentException("Loose type: " + var3);
         }
      } else {
         var1 &= 4294967295L;
      }

      return var1;
   }

   private long RF(long var1, IJavaType var3) {
      if (var3 == this.xK.RF) {
         var1 &= 1L;
      } else if (var3 == this.xK.xK) {
         var1 &= 255L;
         if ((var1 & 128L) != 0L) {
            var1 |= -256L;
         }
      } else if (var3 == this.xK.Dw) {
         var1 &= 65535L;
      } else if (var3 == this.xK.Uv) {
         var1 &= 65535L;
         if ((var1 & 32768L) != 0L) {
            var1 |= -65536L;
         }
      } else if (var3 == this.xK.oW) {
         var1 &= 4294967295L;
         if ((var1 & 2147483648L) != 0L) {
            var1 |= -4294967296L;
         }
      }

      return var1;
   }
}
