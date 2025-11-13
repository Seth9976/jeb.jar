package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger32;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger64;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCElementOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.List;

public class aih extends AbstractCElementOptimizer {
   private static final StructuredLogger q = aeg.q(aih.class);

   public aih() {
      super(true);
   }

   @Override
   protected ICElement optimizeElement(ICElement var1, ICElement var2) {
      if (!(var1 instanceof ICOperation var3)) {
         return null;
      } else {
         ICExpression var4 = this.q(var3, var2);
         if (var4 != null) {
            return var4;
         } else {
            if (var3.getSecondOperand() instanceof ICConstantInteger32) {
               ICConstantInteger32 var5 = (ICConstantInteger32)var3.getSecondOperand();
               if (var5.isNegative()) {
                  COperatorType var6 = var3.getOperatorType();
                  if (var6 == COperatorType.ADD || var6 == COperatorType.SUB) {
                     ICConstantInteger32 var7 = this.cf.createInt32(-(Integer)var5.getValue());
                     if (var7 != null && var7 != var5 && var3.replaceSubElement(var5, var7)) {
                        var3.setOperator(var6 == COperatorType.ADD ? this.of.get(COperatorType.SUB) : this.of.get(COperatorType.ADD));
                        return var3;
                     }
                  }
               }
            } else if (var3.getSecondOperand() instanceof ICConstantInteger64) {
               ICConstantInteger64 var12 = (ICConstantInteger64)var3.getSecondOperand();
               if (var12.isNegative()) {
                  COperatorType var15 = var3.getOperatorType();
                  if (var15 == COperatorType.ADD || var15 == COperatorType.SUB) {
                     ICConstantInteger64 var17 = this.cf.createInt64(-(Long)var12.getValue());
                     if (var17 != null && var17 != var12 && var3.replaceSubElement(var12, var17)) {
                        var3.setOperator(var15 == COperatorType.ADD ? this.of.get(COperatorType.SUB) : this.of.get(COperatorType.ADD));
                        return var3;
                     }
                  }
               }
            }

            ICOperation var9 = this.q(var3);
            if (var9 != null) {
               return var9;
            } else {
               var4 = this.RF(var3);
               if (var4 != null) {
                  Object[] var10000 = new Object[]{var3, var4};
                  if (var2.replaceSubElement(var3, var4)) {
                     return var4;
                  }
               }

               var4 = this.xK(var3, var2);
               if (var4 != null) {
                  if (var4 instanceof ICOperation) {
                     ICExpression var14 = this.q((ICOperation)var4, var2);
                     if (var14 != null) {
                        return var14;
                     }
                  }

                  return var4;
               } else {
                  if (var3.getOperator().isCast()) {
                     ICExpression var13 = var3.getFirstOperand();
                     if (var13 instanceof ICOperation var16 && var16.getOperator().isCast()) {
                        ICType var18 = var3.getOperator().getCastType();
                        ICType var8 = var16.getOperator().getCastType();
                        if ((var18 == var8 || var18.equals(var8) || var18.isReference() && var8.isReference())
                           && var3.replaceSubElement(var13, var16.getFirstOperand())) {
                           return var3;
                        }
                     }
                  }

                  return null;
               }
            }
         }
      }
   }

   private ICExpression q(ICOperation var1, ICElement var2) {
      ICExpression var3 = this.RF(var1, var2);
      if (var3 != null) {
         return var3;
      } else {
         var3 = CUtil.resolveNotOperation(this.m, var1, var2);
         return var3 != null ? var3 : null;
      }
   }

   private ICExpression RF(ICOperation var1, ICElement var2) {
      if (CUtil.isIntegerConstant(var1.getFirstOperand())
         && (var1.getCountOfOperands() == 2 && CUtil.isIntegerConstant(var1.getSecondOperand()) || var1.getCountOfOperands() == 1)) {
         if (aew.q(var1) || CUtil.isOperation(var1, COperatorType.LOG_AND, COperatorType.LOG_IDENT, COperatorType.LOG_NOT, COperatorType.LOG_OR)) {
            Long var6 = var1.evaluate(null, null);
            if (var6 != null) {
               ICConstantInteger32 var7 = this.cf.createInt32(var6.intValue());
               if (var2.replaceSubElement(var1, var7)) {
                  return var7;
               }
            }
         } else if (CUtil.isOperation(
            var1,
            COperatorType.ADD,
            COperatorType.AND,
            COperatorType.DIV,
            COperatorType.MUL,
            COperatorType.NEG,
            COperatorType.NOT,
            COperatorType.OR,
            COperatorType.REM,
            COperatorType.SHL,
            COperatorType.SHR,
            COperatorType.SUB,
            COperatorType.USHR,
            COperatorType.XOR
         )) {
            Long var3 = null;

            try {
               var3 = var1.evaluate(null, null);
            } catch (RuntimeException var5) {
            }

            if (var3 != null) {
               Object var4 = null;
               if (!(var1.getFirstOperand() instanceof ICConstantInteger32)
                  || var1.getCountOfOperands() != 1 && !(var1.getSecondOperand() instanceof ICConstantInteger32)) {
                  if ((var1.getFirstOperand() instanceof ICConstantInteger32 || var1.getFirstOperand() instanceof ICConstantInteger64)
                     && (
                        var1.getCountOfOperands() == 1
                           || var1.getSecondOperand() instanceof ICConstantInteger32
                           || var1.getSecondOperand() instanceof ICConstantInteger64
                     )) {
                     var4 = this.cf.createInt64(var3);
                  }
               } else {
                  var4 = this.cf.createInt32(var3.intValue());
               }

               if (var4 != null && var2.replaceSubElement(var1, (ICElement)var4)) {
                  return (ICExpression)var4;
               }
            }
         }
      }

      return null;
   }

   private ICExpression q(ICExpression var1, ICElement var2) {
      return var1 instanceof ICOperation ? CUtil.resolveNotOperation(this.m, (ICOperation)var1, var2) : null;
   }

   private ICOperation q(ICOperation var1) {
      if (!CUtil.isOperation(var1, COperatorType.COND)) {
         return null;
      } else {
         ICExpression var3 = var1.getFirstOperand();
         ICExpression var4 = var1.getSecondOperand();
         ICExpression var5 = var1.getThirdOperand();
         if (aew.q(var4, var3)) {
            if (var1.replaceSubElement(var4, this.cf.createInt32(1))) {
               return var1;
            }
         } else if (var4 instanceof ICOperation var6) {
            ICOperation var7 = this.ef.createOperation(var6.getOperator(), var6.getFirstOperand(), var6.getSecondOperand(), var6.getThirdOperand());
            if (var7.reverse(this.m.getGlobalContext().getOperatorFactory()) && aew.q(var7, var3) && var1.replaceSubElement(var4, this.cf.createInt32(0))) {
               return var1;
            }
         }

         if (var3 instanceof ICOperation var10) {
            ICOperation var11 = this.ef.createOperation(var10.getOperator(), var10.getFirstOperand(), var10.getSecondOperand(), var10.getThirdOperand());
            if (var11.reverse(this.m.getGlobalContext().getOperatorFactory())) {
               if (aew.q(var5, var11)) {
                  if (var1.replaceSubElement(var5, this.cf.createInt32(1))) {
                     return var1;
                  }
               } else if (var5 instanceof ICOperation var8) {
                  ICOperation var9 = this.ef.createOperation(var8.getOperator(), var8.getFirstOperand(), var8.getSecondOperand(), var8.getThirdOperand());
                  if (var9.reverse(this.m.getGlobalContext().getOperatorFactory())
                     && aew.q(var9, var11)
                     && var1.replaceSubElement(var5, this.cf.createInt32(0))) {
                     return var1;
                  }
               }
            }
         }

         return null;
      }
   }

   private ICExpression RF(ICOperation var1) {
      ICExpression var2 = var1.getFirstOperand();
      ICExpression var3 = var1.getSecondOperand();
      ICExpression var4 = var1.getThirdOperand();
      if (var4 != null) {
         return null;
      } else {
         COperatorType var5 = CUtil.getOperation(var1, COperatorType.LOG_AND);
         if (var5 == null) {
            return null;
         } else {
            ArrayList var6 = new ArrayList();
            var6.add(var2);
            var6.add(var3);
            ArrayList var7 = new ArrayList();

            while (!var6.isEmpty()) {
               ICExpression var8 = (ICExpression)var6.remove(0);
               if (this.q(var8)) {
                  var7.add(var8);
               } else {
                  if (!CUtil.isOperation(var8, var5)) {
                     return null;
                  }

                  var2 = ((ICOperation)var8).getFirstOperand();
                  var3 = ((ICOperation)var8).getSecondOperand();
                  var6.add(0, var3);
                  var6.add(0, var2);
               }
            }

            int var15 = var7.size();

            boolean var9;
            label70:
            do {
               var9 = false;

               for (int var10 = 0; var10 < var7.size() - 1; var10++) {
                  for (int var11 = var10 + 1; var11 < var7.size(); var11++) {
                     ICExpression var12 = this.q(var7, var10, var11, var5);
                     if (var12 != null) {
                        var9 = true;
                        var7.set(var10, var12);
                        var7.remove(var11);
                        continue label70;
                     }
                  }
               }
            } while (var9 && var7.size() > 1);

            if (var7.size() == var15) {
               return null;
            } else if (var7.size() == 1) {
               return (ICExpression)var7.get(0);
            } else {
               ICOperation var16 = this.ef.createOperation(var1.getOperator(), (ICExpression)var7.remove(0), (ICExpression)var7.remove(0));

               while (!var7.isEmpty()) {
                  var16 = this.ef.createOperation(var1.getOperator(), var16, (ICExpression)var7.remove(0));
               }

               return var16;
            }
         }
      }
   }

   private ICExpression q(List var1, int var2, int var3, COperatorType var4) {
      ICExpression var5 = (ICExpression)var1.get(var2);
      ICExpression var6 = (ICExpression)var1.get(var3);
      ICIdentifier var7 = this.q(var5, var6);
      if (var7 == null) {
         return null;
      } else {
         ICOperation var8 = (ICOperation)var5;
         ICOperation var9 = (ICOperation)var6;
         if (this.q(var8.getOperatorType(), var9.getOperatorType())) {
            ICOperation var10 = var8;
            var8 = var9;
            var9 = var10;
         }

         COperatorType var18 = var8.getOperatorType();
         COperatorType var11 = var9.getOperatorType();
         ICExpression var12 = var8.getSecondOperand();
         ICExpression var13 = var9.getSecondOperand();
         long var14 = ((ICConstantInteger)var12).getValueAsLong();
         long var16 = ((ICConstantInteger)var13).getValueAsLong();
         if (var4 == COperatorType.LOG_AND) {
            switch (var18) {
               case LE:
                  switch (var11) {
                     case LE:
                        return var14 < var16 ? var8 : var9;
                     case LT:
                        if (var16 == var14) {
                           return var9;
                        }

                        return var14 < var16 ? var8 : var9;
                     case GE:
                     case GT:
                        return null;
                     case EQ:
                        if (var16 <= var14) {
                           return var9;
                        }

                        return this.cf.createInt32(0);
                     case NE:
                        if (var16 == var14) {
                           return CUtil.lt(this.m, var7, var12);
                        } else {
                           if (var16 < var14) {
                              return null;
                           }

                           return var8;
                        }
                     default:
                        return null;
                  }
               case LT:
                  switch (var11) {
                     case LT:
                        return var14 < var16 ? var8 : var9;
                     case GE:
                     case GT:
                        return null;
                     case EQ:
                        if (var16 == var14) {
                           return CUtil.le(this.m, var7, var12);
                        } else {
                           if (var16 < var14) {
                              return var9;
                           }

                           return this.cf.createInt32(0);
                        }
                     case NE:
                        if (var16 < var14) {
                           return null;
                        }

                        return var8;
                     default:
                        return null;
                  }
               case GE:
                  switch (var11) {
                     case GE:
                        return var14 > var16 ? var8 : var9;
                     case GT:
                        if (var16 == var14) {
                           return var9;
                        }

                        return var14 > var16 ? var8 : var9;
                     case EQ:
                        if (var16 >= var14) {
                           return var9;
                        }

                        return this.cf.createInt32(0);
                     case NE:
                        if (var16 == var14) {
                           return CUtil.gt(this.m, var7, var12);
                        } else {
                           if (var16 > var14) {
                              return null;
                           }

                           return var8;
                        }
                     default:
                        return null;
                  }
               case GT:
                  switch (var11) {
                     case GT:
                        return var14 > var16 ? var8 : var9;
                     case EQ:
                        if (var16 == var14) {
                           return CUtil.ge(this.m, var7, var12);
                        } else {
                           if (var16 > var14) {
                              return var9;
                           }

                           return this.cf.createInt32(0);
                        }
                     case NE:
                        if (var16 > var14) {
                           return null;
                        }

                        return var8;
                     default:
                        return null;
                  }
               case EQ:
                  switch (var11) {
                     case EQ:
                        if (var16 == var14) {
                           return var8;
                        }

                        return this.cf.createInt32(0);
                     case NE:
                        if (var16 == var14) {
                           return this.cf.createInt32(0);
                        }

                        return var8;
                     default:
                        return null;
                  }
               case NE:
                  switch (var11) {
                     case NE:
                        if (var16 == var14) {
                           return var8;
                        }

                        return null;
                     default:
                        return null;
                  }
               default:
                  return null;
            }
         } else {
            return null;
         }
      }
   }

   private boolean q(COperatorType var1, COperatorType var2) {
      switch (var1) {
         case LE:
            return false;
         case LT:
            return var2 == COperatorType.LE;
         case GE:
            return var2 == COperatorType.LE || var2 == COperatorType.LT;
         case GT:
            return var2 == COperatorType.LE || var2 == COperatorType.LT || var2 == COperatorType.GE;
         case EQ:
            return var2 == COperatorType.LE || var2 == COperatorType.LT || var2 == COperatorType.GE || var2 == COperatorType.GT;
         case NE:
            return var2 == COperatorType.LE || var2 == COperatorType.LT || var2 == COperatorType.GE || var2 == COperatorType.GT || var2 == COperatorType.EQ;
         default:
            return false;
      }
   }

   private boolean q(ICExpression var1) {
      return !(var1 instanceof ICOperation var2)
         ? false
         : var2.getFirstOperand() instanceof ICIdentifier && var2.getSecondOperand() instanceof ICConstantInteger;
   }

   private ICIdentifier q(ICExpression var1, ICExpression var2) {
      if (var1 instanceof ICOperation && var2 instanceof ICOperation) {
         ICOperation var3 = (ICOperation)var1;
         ICOperation var4 = (ICOperation)var2;
         if (var3.getFirstOperand() instanceof ICIdentifier
            && var4.getFirstOperand() instanceof ICIdentifier
            && var3.getFirstOperand().equals(var4.getFirstOperand())
            && var3.getSecondOperand() instanceof ICConstantInteger
            && var4.getSecondOperand() instanceof ICConstantInteger) {
            return (ICIdentifier)var3.getFirstOperand();
         }
      }

      return null;
   }

   private ICExpression xK(ICOperation var1, ICElement var2) {
      Object var3 = null;
      Object var4 = null;
      boolean var5 = false;
      if (CUtil.isOperation(var1, COperatorType.EQ, COperatorType.NE) && CUtil.isIntegerValue(var1.getSecondOperand(), 0L)) {
         var3 = var1.getFirstOperand();
         var4 = var1;
         var5 = var1.getOperatorType() == COperatorType.EQ;
      } else if (var2 instanceof ICPredicate) {
         var3 = var1;
         var4 = (ICPredicate)var2;
      }

      if (CUtil.isOperation((ICExpression)var3, COperatorType.COND)) {
         ICOperation var6 = (ICOperation)var3;
         if (CUtil.isIntegerConstant(var6.getSecondOperand()) && CUtil.isIntegerConstant(var6.getThirdOperand())) {
            if (CUtil.isIntegerValue(var6.getSecondOperand(), 0L)) {
               if (CUtil.isIntegerValue(var6.getThirdOperand(), 0L)) {
                  if (CUtil.hasNoSideEffects(var6.getFirstOperand()) && ((ICExpression)var4).replaceSubElement(var6, this.cf.createInt32(0))) {
                     return (ICExpression)var4;
                  }
               } else {
                  ICExpression var7 = var5 ? var6.getFirstOperand() : CUtil.notL(this.m, var6.getFirstOperand());
                  if (var2.replaceSubElement(var1, var7)) {
                     return var7;
                  }
               }
            } else if (!CUtil.isIntegerValue(var6.getThirdOperand(), 0L)) {
               if (CUtil.hasNoSideEffects(var6.getFirstOperand()) && ((ICExpression)var4).replaceSubElement(var6, this.cf.createInt32(1))) {
                  return (ICExpression)var4;
               }
            } else {
               ICExpression var14 = var5 ? CUtil.notL(this.m, var6.getFirstOperand()) : var6.getFirstOperand();
               if (var2.replaceSubElement(var1, var14)) {
                  return var14;
               }
            }
         }

         boolean var8 = false;
         boolean var9;
         ICOperation var15;
         if (CUtil.isOperation(var6.getSecondOperand(), COperatorType.COND) && CUtil.isIntegerConstant(var6.getThirdOperand())) {
            var15 = (ICOperation)var6.getSecondOperand();
            var9 = CUtil.isIntegerValue(var6.getThirdOperand(), 0L);
            var8 = true;
         } else {
            if (!CUtil.isOperation(var6.getThirdOperand(), COperatorType.COND) || !CUtil.isIntegerConstant(var6.getSecondOperand())) {
               return null;
            }

            var15 = (ICOperation)var6.getThirdOperand();
            var9 = CUtil.isIntegerValue(var6.getSecondOperand(), 0L);
         }

         if (!CUtil.isIntegerConstant(var15.getSecondOperand()) || !CUtil.isIntegerConstant(var15.getThirdOperand())) {
            return null;
         }

         ICExpression var10 = var6.getFirstOperand();
         ICExpression var11 = var15.getFirstOperand();
         if (CUtil.isIntegerValue(var15.getSecondOperand(), 0L) != CUtil.isIntegerValue(var15.getThirdOperand(), 0L)) {
            boolean var12 = CUtil.isIntegerValue(var15.getSecondOperand(), 0L) && !CUtil.isIntegerValue(var15.getThirdOperand(), 0L);
            if (var9) {
               ICOperation var13 = CUtil.andL(
                  this.m, var8 ? var10 : CUtil.notL(this.m, var10.duplicate()), var12 ? CUtil.notL(this.m, var11.duplicate()) : var11
               );
               this.q(var13.getFirstOperand(), (ICElement)var13);
               this.q(var13.getSecondOperand(), (ICElement)var13);
               if (var5) {
                  var13 = (ICOperation)CUtil.notL(this.m, var13);
               }

               if (var2.replaceSubElement(var1, var13)) {
                  return var13;
               }
            } else {
               ICOperation var16 = CUtil.orL(
                  this.m, var8 ? CUtil.notL(this.m, var10.duplicate()) : var10, var12 ? CUtil.notL(this.m, var11.duplicate()) : var11
               );
               this.q(var16.getFirstOperand(), (ICElement)var16);
               this.q(var16.getSecondOperand(), (ICElement)var16);
               if (var5) {
                  var16 = (ICOperation)CUtil.notL(this.m, var16);
               }

               if (var2.replaceSubElement(var1, var16)) {
                  return var16;
               }
            }
         }
      }

      return null;
   }
}
