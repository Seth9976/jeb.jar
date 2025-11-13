package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexString;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalCodeThrownException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.Set;

public class bqa extends bph implements IDOperation {
   private static final ILogger UT = GlobalLog.getLogger(bqa.class);
   protected IJavaOperator A;
   protected IDExpression kS;
   protected IDExpression wS;
   private IDExpression E;
   private boolean sY;

   static boolean pC(IDExpression var0, IJavaOperator var1) {
      return var0 != null && var0.getType().isLong() && var1.isAnyOf(JavaOperatorType.SHL, JavaOperatorType.SHR, JavaOperatorType.USHR);
   }

   bqa(IJavaType var1, IDExpression var2, IJavaOperator var3, IDExpression var4) {
      super(var1);
      if (var3 == null) {
         throw new IllegalArgumentException("Missing operator");
      } else if (var4 == null) {
         throw new IllegalArgumentException("Missing operand");
      } else if (var2 == null && var3.isBinary()) {
         throw new IllegalArgumentException("Binary operation requires two operands, not one");
      } else if (var2 != null && var3.isUnary()) {
         throw new RuntimeException("Unary operation requires one operand, not two");
      } else {
         this.kS = var2;
         this.A = var3;
         this.wS = var4;
         this.sY = pC(this.kS, var3);
      }
   }

   bqa(IJavaType var1, IJavaOperator var2, IDExpression var3, IDExpression var4, IDExpression var5) {
      super(var1);
      if (var2 != null && var3 != null && var4 != null && var5 != null) {
         this.A = var2;
         this.E = var3;
         this.kS = var4;
         this.wS = var5;
      } else {
         throw new IllegalArgumentException("Illegal conditional operation, some elements are missing");
      }
   }

   protected bqa(IJavaType var1, IJavaOperator var2, IDExpression var3, IDExpression var4, IDExpression var5, boolean var6) {
      super(var1);
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.E = var5;
      this.sY = var6;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.sY ? 1231 : 1237);
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bqa var3 = (bqa)var1;
         if (this.sY != var3.sY) {
            return false;
         } else {
            if (this.A == null) {
               if (var3.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var3.A)) {
               return false;
            }

            if (this.kS == null) {
               if (var3.kS != null) {
                  return false;
               }
            } else if (!this.kS.equalsEx(var3.kS, var2)) {
               return false;
            }

            if (this.wS == null) {
               if (var3.wS != null) {
                  return false;
               }
            } else if (!this.wS.equalsEx(var3.wS, var2)) {
               return false;
            }

            if (this.E == null) {
               if (var3.E != null) {
                  return false;
               }
            } else if (!this.E.equalsEx(var3.E, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bqa var3 = new bqa(this.pC, this.A, pC(this.kS, var1), pC(this.wS, var1), this.E == null ? null : this.E.copy(var1), this.sY);
      super.A(var3);
      return var3;
   }

   @Override
   public IDOperation duplicate() {
      return (IDOperation)this.copy(null);
   }

   @Override
   public void setOperator(IJavaOperator var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public void setOperator(JavaOperatorType var1, IJavaOperatorFactory var2) {
      this.setOperator(var2.getStandardOperator(var1));
   }

   @Override
   public IJavaOperator getOperator() {
      return this.A;
   }

   @Override
   public JavaOperatorType getOperatorType() {
      return this.A.getOperatorType();
   }

   @Override
   public void setLeft(IDExpression var1) {
      if (this.kS == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
      }
   }

   @Override
   public IDExpression getLeft() {
      return this.kS;
   }

   @Override
   public void setRight(IDExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public IDExpression getRight() {
      return this.wS;
   }

   public IDExpression[] kS() {
      if (this.E != null) {
         return new IDExpression[]{this.E, this.kS, this.wS};
      } else {
         return this.kS != null ? new IDExpression[]{this.kS, this.wS} : new IDExpression[]{this.wS};
      }
   }

   @Override
   public boolean isUnary() {
      return this.kS == null;
   }

   @Override
   public boolean isConditional() {
      return this.E != null;
   }

   @Override
   public IDExpression getCondPredicate() {
      return this.E;
   }

   @Override
   public void setCondPredicate(IDExpression var1) {
      this.E = var1;
   }

   @Override
   public IDExpression getCondTrueExpression() {
      return this.kS;
   }

   @Override
   public IDExpression getCondFalseExpression() {
      return this.wS;
   }

   @Override
   public boolean isCast() {
      return this.isCast(null);
   }

   @Override
   public boolean isCast(IJavaType var1) {
      return this.A.isCast() && (var1 == null || var1.equals(this.A.getCastType()));
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public boolean canReverse() {
      switch (this.A.getOperatorType()) {
         case EQ:
         case NE:
         case LT:
         case GT:
         case LE:
         case GE:
            IJavaType var1 = this.kS.getType();
            IJavaType var2 = this.wS.getType();
            return var1.isDefined() && var2.isDefined() ? !var1.isFloatingPointType() && !var2.isFloatingPointType() : false;
         case LOG_OR:
         case LOG_AND:
            return this.kS instanceof bqa var4 && this.wS instanceof bqa var3 && var4.canReverse() && var3.canReverse();
         default:
            return false;
      }
   }

   @Override
   public void reverse() {
      cdg var1 = (cdg)this.getOperator().getFactory();
      if (this.A == var1.UO) {
         this.A = var1.Ab;
      } else if (this.A == var1.Ab) {
         this.A = var1.UO;
      } else if (this.A == var1.rl) {
         this.A = var1.z;
      } else if (this.A == var1.z) {
         this.A = var1.rl;
      } else if (this.A == var1.Ek) {
         this.A = var1.hK;
      } else if (this.A == var1.hK) {
         this.A = var1.Ek;
      } else if (this.A == var1.Sc) {
         ((bqa)this.kS).reverse();
         this.A = var1.ah;
         ((bqa)this.wS).reverse();
      } else {
         if (this.A != var1.ah) {
            throw new RuntimeException("Cannot perform in-place reversal");
         }

         ((bqa)this.kS).reverse();
         this.A = var1.Sc;
         ((bqa)this.wS).reverse();
      }
   }

   @Override
   public void collectVarIds(Set var1) {
      if (this.kS != null) {
         this.kS.collectVarIds(var1);
      }

      if (this.wS != null) {
         this.wS.collectVarIds(var1);
      }

      if (this.E != null) {
         this.E.collectVarIds(var1);
      }
   }

   @Override
   public void updateConversionOperators(DTypeInfo var1, IDGlobalContext var2) {
      cdg var3 = (cdg)var2.getOperatorFactory();
      cdk var4 = (cdk)var2.getTypeFactory();
      JavaOperatorType var5 = this.A.getOperatorType();
      switch (var5) {
         case CAST_CONVERSION:
            IJavaType var6 = this.getType();
            if (!var6.isFloat() && !var6.isDouble()) {
               if (!var6.isInt() && !var6.isLong()) {
                  throw new RuntimeException();
               }

               if (this.wS.getType().isSingleSlot()) {
                  this.wS.setType(var4.ys, var1);
               } else {
                  if (!this.wS.getType().isDoubleSlot()) {
                     throw new RuntimeException();
                  }

                  this.wS.setType(var4.ld, var1);
               }

               var5 = var6.isInt() ? JavaOperatorType.CAST_TO_INT : JavaOperatorType.CAST_TO_LONG;
            } else {
               if (this.wS.getType().isSingleSlot()) {
                  this.wS.setType(var4.E, var1);
               } else {
                  if (!this.wS.getType().isDoubleSlot()) {
                     throw new RuntimeException();
                  }

                  this.wS.setType(var4.sY, var1);
               }

               var5 = var6.isFloat() ? JavaOperatorType.CAST_TO_FLOAT : JavaOperatorType.CAST_TO_DOUBLE;
            }

            this.A = var3.getStandardOperator(var5);
            break;
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_LONG:
            this.wS.setType(var4.E, var1);
            break;
         case CAST_TO_INT:
            this.wS.setType(var4.sY, var1);
            break;
         case CAST_TO_FLOAT:
            this.wS.setType(var4.ld, var1);
            break;
         case CAST_TO_DOUBLE:
            this.wS.setType(var4.ys, var1);
      }
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      if (this.E != null) {
         this.E.updateTypes(var1);
      }

      if (this.kS != null) {
         this.kS.updateTypes(var1);
      }

      this.wS.updateTypes(var1);
      Assert.a(this.pC != null);
      cdk var2 = (cdk)this.pC.getFactory();
      JavaOperatorType var3 = this.A.getOperatorType();
      Object var4 = this.kS == null ? var2.pC : this.kS.getType();
      Object var5 = this.wS == null ? var2.pC : this.wS.getType();
      switch (var3) {
         case EQ:
         case NE:
         case LT:
         case GT:
         case LE:
         case GE:
         case LOG_OR:
         case LOG_AND:
         case LOG_IDENT:
         case LOG_NOT:
         case INSTANCEOF:
            this.setType(var2.A, var1);
         case CAST_CONVERSION:
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_LONG:
         case CAST_TO_INT:
         case CAST_TO_FLOAT:
         case CAST_TO_DOUBLE:
         default:
            switch (var3) {
               case LOG_OR:
               case LOG_AND:
               case LOG_IDENT:
               case LOG_NOT:
                  if (this.kS != null) {
                     this.kS.setType(var2.A, var1);
                  }

                  this.wS.setType(var2.A, var1);
               default:
                  switch (var3) {
                     case EQ:
                     case NE:
                     case LT:
                     case GT:
                     case LE:
                     case GE:
                     case ADD:
                     case SUB:
                     case MUL:
                     case DIV:
                     case REM:
                     case AND:
                     case OR:
                     case XOR:
                     case SHL:
                     case SHR:
                     case USHR:
                        Object var6 = ((IJavaType)var4).isSmallInt() ? var2.fI : var4;
                        Object var7 = ((IJavaType)var5).isSmallInt() ? var2.fI : var5;
                        if (((IJavaType)var6).canSetType((IJavaType)var7, false)) {
                           this.kS.setType((IJavaType)var7, var1);
                           var4 = this.kS.getType();
                        } else if (((IJavaType)var7).canSetType((IJavaType)var6, false)) {
                           this.wS.setType((IJavaType)var6, var1);
                           var5 = this.wS.getType();
                        }
                     case LOG_OR:
                     case LOG_AND:
                     case CAST_CONVERSION:
                     case CAST_TO_BYTE:
                     case CAST_TO_CHAR:
                     case CAST_TO_SHORT:
                     case CAST_TO_LONG:
                     case CAST_TO_INT:
                     case CAST_TO_FLOAT:
                     case CAST_TO_DOUBLE:
                     case LOG_IDENT:
                     case LOG_NOT:
                     case INSTANCEOF:
                     default:
                        switch (var3) {
                           case LT:
                           case GT:
                           case LE:
                           case GE:
                           case ADD:
                           case SUB:
                           case MUL:
                           case DIV:
                           case REM:
                           case SHL:
                           case SHR:
                           case USHR:
                           case NEG:
                           case NOT:
                              if (((IJavaType)var4).isBoolean()) {
                                 this.kS.setType(var2.fI, var1, true);
                              }

                              if (((IJavaType)var5).isBoolean()) {
                                 this.wS.setType(var2.fI, var1, true);
                              }
                           case LOG_OR:
                           case LOG_AND:
                           case CAST_CONVERSION:
                           case CAST_TO_BYTE:
                           case CAST_TO_CHAR:
                           case CAST_TO_SHORT:
                           case CAST_TO_LONG:
                           case CAST_TO_INT:
                           case CAST_TO_FLOAT:
                           case CAST_TO_DOUBLE:
                           case LOG_IDENT:
                           case LOG_NOT:
                           case INSTANCEOF:
                           case AND:
                           case OR:
                           case XOR:
                           default:
                              if (((IJavaType)var5).isSmallInt()) {
                                 var5 = var2.E;
                              }

                              if (((IJavaType)var4).isSmallInt()) {
                                 var4 = var2.E;
                              }

                              if (this.A.isArithmetic()) {
                                 if (var4 == var2.pC) {
                                    this.setType((IJavaType)var5, var1);
                                 } else if (this.sY) {
                                    this.setType(var2.sY, var1);
                                 } else if (var4 != var5) {
                                    var1.recordConflict("Operand types mismatch!", this, (IJavaType)var4, (IJavaType)var5);
                                 } else {
                                    this.setType((IJavaType)var5, var1);
                                 }
                              } else if (this.A.isLogical()) {
                                 this.setType(var2.A, var1);
                              } else if (this.A.isCast()) {
                                 Object var8;
                                 if (this.A.is(JavaOperatorType.CAST_TO_BYTE)) {
                                    var8 = var2.kS;
                                 } else if (this.A.is(JavaOperatorType.CAST_TO_CHAR)) {
                                    var8 = var2.wS;
                                 } else if (this.A.is(JavaOperatorType.CAST_TO_SHORT)) {
                                    var8 = var2.UT;
                                 } else if (this.A.is(JavaOperatorType.CAST_TO_INT)) {
                                    var8 = var2.E;
                                 } else if (this.A.is(JavaOperatorType.CAST_TO_LONG)) {
                                    var8 = var2.sY;
                                 } else if (this.A.is(JavaOperatorType.CAST_TO_FLOAT)) {
                                    var8 = var2.ys;
                                 } else if (this.A.is(JavaOperatorType.CAST_TO_DOUBLE)) {
                                    var8 = var2.ld;
                                 } else if (this.A.is(JavaOperatorType.CAST_TO_BOOLEAN)) {
                                    var8 = var2.A;
                                 } else {
                                    var8 = this.A.getCastType();
                                 }

                                 this.setType((IJavaType)var8, var1);
                              } else if (this.A.is(JavaOperatorType.CONCAT)) {
                                 this.setType(var2.getJavaLangString(), var1);
                              } else if (!this.A.is(JavaOperatorType.COND_EXP)) {
                                 throw new RuntimeException("Cannot infer expression type");
                              }
                        }
                  }
            }
      }
   }

   @Override
   public boolean pC() {
      if (this.kS != null && ((bph)this.kS).pC()) {
         return true;
      } else {
         return this.wS != null && ((bph)this.wS).pC() ? true : this.E != null && ((bph)this.E).pC();
      }
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      if (this.A.isCast() && !this.A.isCastToPrimitive()) {
         return true;
      } else if (this.A.getOperatorType() != JavaOperatorType.DIV && this.A.getOperatorType() != JavaOperatorType.REM
         || this.wS instanceof bps var2 && !var2.isZero()) {
         if (this.kS != null && this.kS.canThrow(var1)) {
            return true;
         } else {
            return this.wS != null && this.wS.canThrow(var1) ? true : this.E != null && this.E.canThrow(var1);
         }
      } else {
         return true;
      }
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      if (var2 && this.canThrow(var1)) {
         return true;
      } else if (this.kS != null && this.kS.hasSideEffects(var1, false)) {
         return true;
      } else {
         return this.wS != null && this.wS.hasSideEffects(var1, false) ? true : this.E != null && this.E.hasSideEffects(var1, false);
      }
   }

   @Override
   public int countVariable(IDVar var1) {
      int var2 = 0;
      if (this.kS != null) {
         var2 += ((bph)this.kS).countVariable(var1);
      }

      if (this.wS != null) {
         var2 += ((bph)this.wS).countVariable(var1);
      }

      if (this.E != null) {
         var2 += ((bph)this.E).countVariable(var1);
      }

      return var2;
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      int var3 = 0;
      if (this.kS == var1) {
         this.kS = var2 == null ? null : var2.duplicate();
         var3++;
      } else if (this.kS != null) {
         var3 += this.kS.replaceVariable(var1, var2);
      }

      if (this.wS == var1) {
         this.wS = var2.duplicate();
         var3++;
      } else if (this.wS != null) {
         var3 += this.wS.replaceVariable(var1, var2);
      }

      if (this.E == var1) {
         this.E = var2.duplicate();
         var3++;
      } else if (this.E != null) {
         var3 += this.E.replaceVariable(var1, var2);
      }

      return var3;
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      super.pC(var1, var2);
      if (this.kS != null) {
         ((bph)this.kS).pC(var1, var2);
      }

      if (this.wS != null) {
         ((bph)this.wS).pC(var1, var2);
      }

      if (this.E != null) {
         ((bph)this.E).pC(var1, var2);
      }
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      if (this.E != null) {
         var1.add(this.E);
      }

      if (this.kS != null) {
         var1.add(this.kS);
      }

      if (this.wS != null) {
         var1.add(this.wS);
      }
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      if (var1 != null && var2 != null) {
         int var3 = 0;
         if (this.kS == var1) {
            this.kS = pC(var2, var3);
            var3++;
         }

         if (this.wS == var1) {
            this.wS = pC(var2, var3);
            var3++;
         }

         if (this.E == var1) {
            this.E = pC(var2, var3);
            var3++;
         }

         return var3 > 0;
      } else {
         return false;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      try {
         IDImm var2 = this.kS == null ? null : this.kS.evaluate(var1);
         IDImm var3 = this.wS.evaluate(var1);
         if (this.isConditional()) {
            IDImm var13 = this.E.evaluate(var1);
            return var13.isZero() ? var3 : var2;
         } else if (this.A.is(JavaOperatorType.CONCAT)) {
            String var12 = null;
            if (var2.isRef() && var1.getObject(var2.getObjectReferenceId()) instanceof String) {
               var12 = (String)var1.getObject(var2.getObjectReferenceId());
            }

            String var14 = null;
            if (var3.isRef() && var1.getObject(var3.getObjectReferenceId()) instanceof String) {
               var14 = (String)var1.getObject(var3.getObjectReferenceId());
            }

            if (var12 != null && var14 != null) {
               String var6 = var12 + var14;
               IDexString var7 = var1.getDex().addString(var6);
               IDGlobalContext var8 = var1.getGlobalContext();
               IDImm var9 = var8.createString(var8.createIndex(var7.getIndex()));
               var9.setOrigin("Result of concatenation");
               return var9;
            } else {
               throw new DexDecEvaluationException("String CONCAT (+) operator requires at least one of its operands to be a String!");
            }
         } else if ((this.A.isArithmetic() || this.A.isLogical()) && this.getType().isFloatingPointType()) {
            return this.pC(var1, var2, var3);
         } else {
            if (this.A.is(JavaOperatorType.CAST_CONVERSION)) {
               IJavaType var4 = this.getType();
               if (var4.isSmallInt() || var4.isLong()) {
                  return this.pC(var1, var2, var3);
               }

               if (var4.isFloatingPointType()) {
                  return this.A(var1, var2, var3);
               }
            }

            IJavaType var11 = this.kS == null ? null : var2.getType();
            IJavaType var5 = var3.getType();
            return (var11 == null || !var11.isFloatingPointType()) && !var5.isFloatingPointType() ? this.A(var1, var2, var3) : this.pC(var1, var2, var3);
         }
      } catch (Exception var10) {
         if (var10 instanceof DexDecEvaluationException) {
            throw var10;
         } else {
            throw new DexDecEvaluationException(var10);
         }
      }
   }

   private IDImm pC(IDState var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      IDGlobalContext var4 = var1.getGlobalContext();
      double var5 = var2 == null ? 0.0 : var2.toDouble();
      double var7 = var3 == null ? 0.0 : var3.toDouble();
      IJavaType var9 = this.getType();
      if (this.A.isCast()) {
         switch (this.A.getOperatorType()) {
            case CAST_CONVERSION:
               if (var9.isLong()) {
                  long var20 = (long)var7;
                  return var4.createLong(var20);
               } else {
                  if (var9.isInt()) {
                     int var19 = (int)var7;
                     return var4.createInt(var19);
                  }

                  throw new DexDecEvaluationException(this);
               }
            case CAST_TO_BYTE:
               long var18 = (byte)var7;
               return var4.createConstant(var18, var9);
            case CAST_TO_CHAR:
               long var17 = (char)var7;
               return var4.createConstant(var17, var9);
            case CAST_TO_SHORT:
               long var16 = (short)var7;
               return var4.createConstant(var16, var9);
            case CAST_TO_LONG:
               long var15 = (long)var7;
               return var4.createConstant(var15, var9);
            case CAST_TO_INT:
               long var14 = (int)var7;
               return var4.createConstant(var14, var9);
            case CAST_TO_FLOAT:
               Assert.a(var9.isFloat());
               float var13 = (float)var7;
               return var4.createFloat(var13);
            case CAST_TO_DOUBLE:
               Assert.a(var9.isDouble());
               return var4.createDouble(var7);
            default:
               throw new DexDecEvaluationException(this);
         }
      } else {
         if (this.A.isArithmetic()) {
            double var10 = switch (this.A.getOperatorType()) {
               case ADD -> var5 + var7;
               case SUB -> var5 - var7;
               case MUL -> var5 * var7;
               case DIV -> var5 / var7;
               case REM -> var5 % var7;
               default -> throw new DexDecEvaluationException(this);
               case NEG -> -var7;
            };
            if (var9.isFloat()) {
               return var4.createFloat((float)var10);
            }

            if (var9.isDouble()) {
               return var4.createDouble(var10);
            }
         }

         if (this.A.isLogical()) {
            return var4.createConstant(switch (this.A.getOperatorType()) {
               case EQ -> var5 == var7;
               case NE -> var5 != var7;
               case LT -> var5 < var7;
               case GT -> var5 > var7;
               case LE -> var5 <= var7;
               case GE -> var5 >= var7;
               default -> throw new DexDecEvaluationException(this);
            } ? 1L : 0L, var9);
         } else {
            throw new DexDecEvaluationException(this);
         }
      }
   }

   private IDImm A(IDState var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      IDGlobalContext var4 = var1.getGlobalContext();
      long var5 = var2 == null ? 0L : var2.toLong(true);
      long var7 = var3 == null ? 0L : var3.toLong(true);
      IJavaType var9 = this.getType();
      if (this.A.isCast()) {
         IJavaType var19 = var3.getType();
         switch (this.A.getOperatorType()) {
            case CAST_CONVERSION:
               if (var9.isDouble()) {
                  double var26 = var7;
                  return var4.createDouble(var26);
               } else {
                  if (var9.isFloat()) {
                     float var25 = (float)var7;
                     return var4.createFloat(var25);
                  }

                  throw new DexDecEvaluationException(this);
               }
            case CAST_TO_BYTE:
               long var24 = (byte)var7;
               return var4.createConstant(var24, var9);
            case CAST_TO_CHAR:
               long var23 = (char)var7;
               return var4.createConstant(var23, var9);
            case CAST_TO_SHORT:
               long var22 = (short)var7;
               return var4.createConstant(var22, var9);
            case CAST_TO_LONG:
               return var4.createConstant(var7, var9);
            case CAST_TO_INT:
               long var21 = (int)var7;
               return var4.createConstant(var21, var9);
            case CAST_TO_FLOAT:
               float var20 = (int)var7;
               return var4.createFloat(var20);
            case CAST_TO_DOUBLE:
               double var11 = var7;
               return var4.createDouble(var11);
            case LOG_IDENT:
            case LOG_NOT:
            case INSTANCEOF:
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case REM:
            case AND:
            case OR:
            case XOR:
            case SHL:
            case SHR:
            case USHR:
            case NEG:
            case NOT:
            default:
               throw new DexDecEvaluationException(this);
            case CAST_TO_OBJECT:
               if (var19.isObject() || var19.isSingleSlotWildcard() && var3.isZero()) {
                  return var3;
               } else {
                  throw new DexDecEvaluationException("Cannot cast non-object to object");
               }
         }
      } else if (this.A.isArithmetic()) {
         IJavaType var29 = var3.getType();
         if (var29.isSmallInt()) {
            if (var2 == null || var2.getType().isSmallInt()) {
               var9 = var4.getTypeFactory().getInt();
            } else if (var2.getType().isLong()) {
               var9 = var4.getTypeFactory().getLong();
            }
         } else if (var29.isLong()) {
            var9 = var4.getTypeFactory().getLong();
         }

         return var4.createConstant(switch (this.A.getOperatorType()) {
            case ADD -> var5 + var7;
            case SUB -> var5 - var7;
            case MUL -> var5 * var7;
            case DIV -> {
               try {
                  yield var5 / var7;
               } catch (Exception var15) {
                  throw new DexDecEvalCodeThrownException(var1.registerObject(new ArithmeticException()));
               }
            }
            case REM -> {
               try {
                  yield var5 % var7;
               } catch (Exception var14) {
                  throw new DexDecEvalCodeThrownException(var1.registerObject(new ArithmeticException()));
               }
            }
            case AND -> var5 & var7;
            case OR -> var5 | var7;
            case XOR -> var5 ^ var7;
            case SHL -> {
               long var28 = this.getType().isSingleSlot() ? 31L : 63L;
               yield var5 << (int)(var7 & var28);
            }
            case SHR -> {
               long var27 = this.getType().isSingleSlot() ? 31L : 63L;
               yield var5 >> (int)(var7 & var27);
            }
            case USHR -> {
               if (var2.getType().isSmallInt()) {
                  var5 = var2.toLong() & 4294967295L;
                  var7 = var3.toLong() & 4294967295L;
               } else {
                  var5 = var2.toUnsignedLong();
                  var7 = var3.toUnsignedLong();
               }

               long var12 = this.getType().isSingleSlot() ? 31L : 63L;
               yield var5 >>> (int)(var7 & var12);
            }
            case NEG -> -var7;
            case NOT -> ~var7;
            default -> throw new DexDecEvaluationException(this);
         }, var9);
      } else if (!this.A.isLogical()) {
         throw new DexDecEvaluationException(this);
      } else {
         return var4.createConstant(switch (this.A.getOperatorType()) {
            case EQ -> var5 == var7;
            case NE -> var5 != var7;
            case LT -> var5 < var7;
            case GT -> var5 > var7;
            case LE -> var5 <= var7;
            case GE -> var5 >= var7;
            case LOG_OR -> var5 != 0L || var7 != 0L;
            case LOG_AND -> var5 != 0L && var7 != 0L;
            default -> throw new DexDecEvaluationException(this);
            case LOG_IDENT -> var7 != 0L;
            case LOG_NOT -> var7 == 0L;
            case INSTANCEOF -> var1.isInstanceOf(var2, var3);
         } ? 1L : 0L, var9);
      }
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      if (this.E != null) {
         return this.pC(
            var2.getGlobalContext()
               .createConditionalExpression(
                  (IJavaExpression)this.E.generateAST(var1, var2),
                  (IJavaExpression)this.kS.generateAST(var1, var2),
                  (IJavaExpression)this.wS.generateAST(var1, var2)
               )
         );
      } else {
         IJavaExpression var3 = null;
         if (this.kS != null) {
            var3 = (IJavaExpression)this.kS.generateAST(var1, var2);
         }

         IJavaExpression var4 = (IJavaExpression)this.wS.generateAST(var1, var2);
         return this.pC(var2.getGlobalContext().createOperation(var3, this.A, var4));
      }
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.paren();
      if (this.E != null) {
         this.E.format(var1);
         var1.append(" ? ");
         this.kS.format(var1);
         var1.append(" : ");
         this.wS.format(var1);
      } else if (this.kS != null) {
         this.kS.format(var1);
         var1.append(" ");
         this.A.format(var1);
         var1.append(" ");
         this.wS.format(var1);
      } else {
         this.A.format(var1);
         this.wS.format(var1);
      }

      var1.parenClose();
      var1.appendFormattedTypeIf(this.pC);
   }
}
