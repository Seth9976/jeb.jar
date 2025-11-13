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

public class bug extends btk implements IDOperation {
   private static final ILogger Uv = GlobalLog.getLogger(bug.class);
   protected IJavaOperator RF;
   protected IDExpression xK;
   protected IDExpression Dw;
   private IDExpression oW;
   private boolean gO;

   static boolean q(IDExpression var0, IJavaOperator var1) {
      return var0 != null && var0.getType().isLong() && var1.isAnyOf(JavaOperatorType.SHL, JavaOperatorType.SHR, JavaOperatorType.USHR);
   }

   bug(IJavaType var1, IDExpression var2, IJavaOperator var3, IDExpression var4) {
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
         this.xK = var2;
         this.RF = var3;
         this.Dw = var4;
         this.gO = q(this.xK, var3);
      }
   }

   bug(IJavaType var1, IJavaOperator var2, IDExpression var3, IDExpression var4, IDExpression var5) {
      super(var1);
      if (var2 != null && var3 != null && var4 != null && var5 != null) {
         this.RF = var2;
         this.oW = var3;
         this.xK = var4;
         this.Dw = var5;
      } else {
         throw new IllegalArgumentException("Illegal conditional operation, some elements are missing");
      }
   }

   protected bug(IJavaType var1, IJavaOperator var2, IDExpression var3, IDExpression var4, IDExpression var5, boolean var6) {
      super(var1);
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.oW = var5;
      this.gO = var6;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.gO ? 1231 : 1237);
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
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
         bug var3 = (bug)var1;
         if (this.gO != var3.gO) {
            return false;
         } else {
            if (this.RF == null) {
               if (var3.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var3.RF)) {
               return false;
            }

            if (this.xK == null) {
               if (var3.xK != null) {
                  return false;
               }
            } else if (!this.xK.equalsEx(var3.xK, var2)) {
               return false;
            }

            if (this.Dw == null) {
               if (var3.Dw != null) {
                  return false;
               }
            } else if (!this.Dw.equalsEx(var3.Dw, var2)) {
               return false;
            }

            if (this.oW == null) {
               if (var3.oW != null) {
                  return false;
               }
            } else if (!this.oW.equalsEx(var3.oW, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   protected void q(bug var1, DCopyOptions var2) {
      if (this.getClass() == bug.class) {
         var1.RF = this.RF;
         var1.xK = q(this.xK, var2);
         var1.Dw = q(this.Dw, var2);
      }

      var1.oW = this.oW == null ? null : this.oW.copy(var2);
      var1.gO = this.gO;
      super.RF(var1);
   }

   @Override
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bug var3 = new bug(this.q, this.RF, q(this.xK, var1), q(this.Dw, var1), this.oW == null ? null : this.oW.copy(var1), this.gO);
      super.RF(var3);
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
         this.RF = var1;
      }
   }

   @Override
   public void setOperator(JavaOperatorType var1, IJavaOperatorFactory var2) {
      this.setOperator(var2.getStandardOperator(var1));
   }

   @Override
   public IJavaOperator getOperator() {
      return this.RF;
   }

   @Override
   public JavaOperatorType getOperatorType() {
      return this.RF.getOperatorType();
   }

   @Override
   public void setLeft(IDExpression var1) {
      if (this.xK == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK = var1;
      }
   }

   @Override
   public IDExpression getLeft() {
      return this.xK;
   }

   @Override
   public void setRight(IDExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   @Override
   public IDExpression getRight() {
      return this.Dw;
   }

   public IDExpression[] oW() {
      if (this.oW != null) {
         return new IDExpression[]{this.oW, this.xK, this.Dw};
      } else {
         return this.xK != null ? new IDExpression[]{this.xK, this.Dw} : new IDExpression[]{this.Dw};
      }
   }

   @Override
   public boolean isUnary() {
      return this.xK == null;
   }

   @Override
   public boolean isConditional() {
      return this.oW != null;
   }

   @Override
   public IDExpression getCondPredicate() {
      return this.oW;
   }

   @Override
   public void setCondPredicate(IDExpression var1) {
      this.oW = var1;
   }

   @Override
   public IDExpression getCondTrueExpression() {
      return this.xK;
   }

   @Override
   public IDExpression getCondFalseExpression() {
      return this.Dw;
   }

   @Override
   public boolean isCast() {
      return this.isCast(null);
   }

   @Override
   public boolean isCast(IJavaType var1) {
      return this.RF.isCast() && (var1 == null || var1.equals(this.RF.getCastType()));
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public boolean canReverse() {
      switch (this.RF.getOperatorType()) {
         case EQ:
         case NE:
         case LT:
         case GT:
         case LE:
         case GE:
            IJavaType var1 = this.xK.getType();
            IJavaType var2 = this.Dw.getType();
            return var1.isDefined() && var2.isDefined() ? !var1.isFloatingPointType() && !var2.isFloatingPointType() : false;
         case LOG_OR:
         case LOG_AND:
            return this.xK instanceof bug var4 && this.Dw instanceof bug var3 && var4.canReverse() && var3.canReverse();
         default:
            return false;
      }
   }

   @Override
   public void reverse() {
      cio var1 = (cio)this.getOperator().getFactory();
      if (this.RF == var1.oQ) {
         this.RF = var1.xW;
      } else if (this.RF == var1.xW) {
         this.RF = var1.oQ;
      } else if (this.RF == var1.KT) {
         this.RF = var1.Gf;
      } else if (this.RF == var1.Gf) {
         this.RF = var1.KT;
      } else if (this.RF == var1.Ef) {
         this.RF = var1.cC;
      } else if (this.RF == var1.cC) {
         this.RF = var1.Ef;
      } else if (this.RF == var1.Hk) {
         ((bug)this.xK).reverse();
         this.RF = var1.Me;
         ((bug)this.Dw).reverse();
      } else {
         if (this.RF != var1.Me) {
            throw new RuntimeException("Cannot perform in-place reversal");
         }

         ((bug)this.xK).reverse();
         this.RF = var1.Hk;
         ((bug)this.Dw).reverse();
      }
   }

   @Override
   public void collectVarIds(Set var1) {
      if (this.xK != null) {
         this.xK.collectVarIds(var1);
      }

      if (this.Dw != null) {
         this.Dw.collectVarIds(var1);
      }

      if (this.oW != null) {
         this.oW.collectVarIds(var1);
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void updateConversionOperators(DTypeInfo var1, IDGlobalContext var2) {
      cio var3 = (cio)var2.getOperatorFactory();
      cis var4 = (cis)var2.getTypeFactory();
      JavaOperatorType var5 = this.RF.getOperatorType();
      switch (var5) {
         case CAST_CONVERSION:
            IJavaType var6 = this.getType();
            if (!var6.isFloat() && !var6.isDouble()) {
               if (!var6.isInt() && !var6.isLong()) {
                  throw new RuntimeException();
               }

               if (this.Dw.getType().isSingleSlot()) {
                  this.Dw.setType(var4.nf, var1);
               } else {
                  if (!this.Dw.getType().isDoubleSlot()) {
                     throw new RuntimeException();
                  }

                  this.Dw.setType(var4.gP, var1);
               }

               var5 = var6.isInt() ? JavaOperatorType.CAST_TO_INT : JavaOperatorType.CAST_TO_LONG;
            } else {
               if (this.Dw.getType().isSingleSlot()) {
                  this.Dw.setType(var4.oW, var1);
               } else {
                  if (!this.Dw.getType().isDoubleSlot()) {
                     throw new RuntimeException();
                  }

                  this.Dw.setType(var4.gO, var1);
               }

               var5 = var6.isFloat() ? JavaOperatorType.CAST_TO_FLOAT : JavaOperatorType.CAST_TO_DOUBLE;
            }

            this.RF = var3.getStandardOperator(var5);
            break;
         case CAST_TO_BYTE:
         case CAST_TO_CHAR:
         case CAST_TO_SHORT:
         case CAST_TO_LONG:
            this.Dw.setType(var4.oW, var1);
            break;
         case CAST_TO_INT:
            this.Dw.setType(var4.gO, var1);
            break;
         case CAST_TO_FLOAT:
            this.Dw.setType(var4.gP, var1);
            break;
         case CAST_TO_DOUBLE:
            this.Dw.setType(var4.nf, var1);
      }
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      if (this.oW != null) {
         this.oW.updateTypes(var1);
      }

      if (this.xK != null) {
         this.xK.updateTypes(var1);
      }

      this.Dw.updateTypes(var1);
      Assert.a(this.q != null);
      cis var2 = (cis)this.q.getFactory();
      JavaOperatorType var3 = this.RF.getOperatorType();
      Object var4 = this.xK == null ? var2.q : this.xK.getType();
      Object var5 = this.Dw == null ? var2.q : this.Dw.getType();
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
            this.setType(var2.RF, var1);
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
                  if (this.xK != null) {
                     this.xK.setType(var2.RF, var1);
                  }

                  this.Dw.setType(var2.RF, var1);
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
                        Object var6 = ((IJavaType)var4).isSmallInt() ? var2.zz : var4;
                        Object var7 = ((IJavaType)var5).isSmallInt() ? var2.zz : var5;
                        if (((IJavaType)var6).canSetType((IJavaType)var7, false)) {
                           this.xK.setType((IJavaType)var7, var1);
                           var4 = this.xK.getType();
                        } else if (((IJavaType)var7).canSetType((IJavaType)var6, false)) {
                           this.Dw.setType((IJavaType)var6, var1);
                           var5 = this.Dw.getType();
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
                                 this.xK.setType(var2.zz, var1, true);
                              }

                              if (((IJavaType)var5).isBoolean()) {
                                 this.Dw.setType(var2.zz, var1, true);
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
                                 var5 = var2.oW;
                              }

                              if (((IJavaType)var4).isSmallInt()) {
                                 var4 = var2.oW;
                              }

                              if (this.RF.isArithmetic()) {
                                 if (var4 == var2.q) {
                                    this.setType((IJavaType)var5, var1);
                                 } else if (this.gO) {
                                    this.setType(var2.gO, var1);
                                 } else if (var4 != var5) {
                                    var1.recordConflict("Operand types mismatch!", this, (IJavaType)var4, (IJavaType)var5);
                                 } else {
                                    this.setType((IJavaType)var5, var1);
                                 }
                              } else if (this.RF.isLogical()) {
                                 this.setType(var2.RF, var1);
                              } else if (this.RF.isCast()) {
                                 Object var8;
                                 if (this.RF.is(JavaOperatorType.CAST_TO_BYTE)) {
                                    var8 = var2.xK;
                                 } else if (this.RF.is(JavaOperatorType.CAST_TO_CHAR)) {
                                    var8 = var2.Dw;
                                 } else if (this.RF.is(JavaOperatorType.CAST_TO_SHORT)) {
                                    var8 = var2.Uv;
                                 } else if (this.RF.is(JavaOperatorType.CAST_TO_INT)) {
                                    var8 = var2.oW;
                                 } else if (this.RF.is(JavaOperatorType.CAST_TO_LONG)) {
                                    var8 = var2.gO;
                                 } else if (this.RF.is(JavaOperatorType.CAST_TO_FLOAT)) {
                                    var8 = var2.nf;
                                 } else if (this.RF.is(JavaOperatorType.CAST_TO_DOUBLE)) {
                                    var8 = var2.gP;
                                 } else if (this.RF.is(JavaOperatorType.CAST_TO_BOOLEAN)) {
                                    var8 = var2.RF;
                                 } else {
                                    var8 = this.RF.getCastType();
                                 }

                                 this.setType((IJavaType)var8, var1);
                              } else if (this.RF.is(JavaOperatorType.CONCAT)) {
                                 this.setType(var2.getJavaLangString(), var1);
                              } else if (!this.RF.is(JavaOperatorType.COND_EXP)) {
                                 throw new RuntimeException("Cannot infer expression type");
                              }
                        }
                  }
            }
      }
   }

   @Override
   public boolean q() {
      return this.q.isDetermined()
         && (this.xK == null || ((btk)this.xK).q())
         && (this.Dw == null || ((btk)this.Dw).q())
         && (this.oW == null || ((btk)this.oW).q());
   }

   @Override
   public boolean RF() {
      if (this.xK != null && ((btk)this.xK).RF()) {
         return true;
      } else {
         return this.Dw != null && ((btk)this.Dw).RF() ? true : this.oW != null && ((btk)this.oW).RF();
      }
   }

   @Override
   public boolean xK() {
      if (this.xK != null && ((btk)this.xK).xK()) {
         return true;
      } else {
         return this.Dw != null && ((btk)this.Dw).xK() ? true : this.oW != null && ((btk)this.oW).xK();
      }
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      if (this.RF.isCast() && !this.RF.isCastToPrimitive()) {
         return true;
      } else if (this.RF.getOperatorType() != JavaOperatorType.DIV && this.RF.getOperatorType() != JavaOperatorType.REM
         || this.Dw instanceof bty var2 && !var2.isZero()) {
         if (this.xK != null && this.xK.canThrow(var1)) {
            return true;
         } else {
            return this.Dw != null && this.Dw.canThrow(var1) ? true : this.oW != null && this.oW.canThrow(var1);
         }
      } else {
         return true;
      }
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      if (var2 && this.canThrow(var1)) {
         return true;
      } else if (this.xK != null && this.xK.hasSideEffects(var1, false)) {
         return true;
      } else {
         return this.Dw != null && this.Dw.hasSideEffects(var1, false) ? true : this.oW != null && this.oW.hasSideEffects(var1, false);
      }
   }

   @Override
   public int countVariable(IDVar var1) {
      int var2 = 0;
      if (this.xK != null) {
         var2 += ((btk)this.xK).countVariable(var1);
      }

      if (this.Dw != null) {
         var2 += ((btk)this.Dw).countVariable(var1);
      }

      if (this.oW != null) {
         var2 += ((btk)this.oW).countVariable(var1);
      }

      return var2;
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      int var3 = 0;
      if (this.xK == var1) {
         this.xK = var2 == null ? null : var2.duplicate();
         var3++;
      } else if (this.xK != null) {
         var3 += this.xK.replaceVariable(var1, var2);
      }

      if (this.Dw == var1) {
         this.Dw = var2.duplicate();
         var3++;
      } else if (this.Dw != null) {
         var3 += this.Dw.replaceVariable(var1, var2);
      }

      if (this.oW == var1) {
         this.oW = var2.duplicate();
         var3++;
      } else if (this.oW != null) {
         var3 += this.oW.replaceVariable(var1, var2);
      }

      return var3;
   }

   @Override
   public void q(IJavaType var1, IJavaType var2) {
      super.q(var1, var2);
      if (this.xK != null) {
         ((btk)this.xK).q(var1, var2);
      }

      if (this.Dw != null) {
         ((btk)this.Dw).q(var1, var2);
      }

      if (this.oW != null) {
         ((btk)this.oW).q(var1, var2);
      }
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      if (this.oW != null) {
         var1.add(this.oW);
      }

      if (this.xK != null) {
         var1.add(this.xK);
      }

      if (this.Dw != null) {
         var1.add(this.Dw);
      }
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      if (var1 != null && var2 != null) {
         int var3 = 0;
         if (this.xK == var1) {
            this.xK = q(var2, var3);
            var3++;
         }

         if (this.Dw == var1) {
            this.Dw = q(var2, var3);
            var3++;
         }

         if (this.oW == var1) {
            this.oW = q(var2, var3);
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
         IDImm var2 = this.xK == null ? null : this.xK.evaluate(var1);
         IDImm var3 = this.Dw.evaluate(var1);
         if (this.isConditional()) {
            IDImm var13 = this.oW.evaluate(var1);
            return var13.isZero() ? var3 : var2;
         } else if (this.RF.is(JavaOperatorType.CONCAT)) {
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
         } else if ((this.RF.isArithmetic() || this.RF.isLogical()) && this.getType().isFloatingPointType()) {
            return this.q(var1, var2, var3);
         } else {
            if (this.RF.is(JavaOperatorType.CAST_CONVERSION)) {
               IJavaType var4 = this.getType();
               if (var4.isSmallInt() || var4.isLong()) {
                  return this.q(var1, var2, var3);
               }

               if (var4.isFloatingPointType()) {
                  return this.RF(var1, var2, var3);
               }
            }

            IJavaType var11 = this.xK == null ? null : var2.getType();
            IJavaType var5 = var3.getType();
            return (var11 == null || !var11.isFloatingPointType()) && !var5.isFloatingPointType() ? this.RF(var1, var2, var3) : this.q(var1, var2, var3);
         }
      } catch (Exception var10) {
         if (var10 instanceof DexDecEvaluationException) {
            throw var10;
         } else {
            throw new DexDecEvaluationException(var10);
         }
      }
   }

   private IDImm q(IDState var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      IDGlobalContext var4 = var1.getGlobalContext();
      double var5 = var2 == null ? 0.0 : var2.toDouble();
      double var7 = var3 == null ? 0.0 : var3.toDouble();
      IJavaType var9 = this.getType();
      if (this.RF.isCast()) {
         switch (this.RF.getOperatorType()) {
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
         if (this.RF.isArithmetic()) {
            double var10 = switch (this.RF.getOperatorType()) {
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

         if (this.RF.isLogical()) {
            return var4.createConstant(switch (this.RF.getOperatorType()) {
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

   private IDImm RF(IDState var1, IDImm var2, IDImm var3) throws DexDecEvaluationException {
      IDGlobalContext var4 = var1.getGlobalContext();
      long var5 = var2 == null ? 0L : var2.toLong(true);
      long var7 = var3 == null ? 0L : var3.toLong(true);
      IJavaType var9 = this.getType();
      if (this.RF.isCast()) {
         IJavaType var19 = var3.getType();
         switch (this.RF.getOperatorType()) {
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
      } else if (this.RF.isArithmetic()) {
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

         return var4.createConstant(switch (this.RF.getOperatorType()) {
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
      } else if (!this.RF.isLogical()) {
         throw new DexDecEvaluationException(this);
      } else {
         return var4.createConstant(switch (this.RF.getOperatorType()) {
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
      if (this.oW != null) {
         return this.q(
            var2.getGlobalContext()
               .createConditionalExpression(
                  (IJavaExpression)this.oW.generateAST(var1, var2),
                  (IJavaExpression)this.xK.generateAST(var1, var2),
                  (IJavaExpression)this.Dw.generateAST(var1, var2)
               )
         );
      } else {
         IJavaExpression var3 = null;
         if (this.xK != null) {
            var3 = (IJavaExpression)this.xK.generateAST(var1, var2);
         }

         IJavaExpression var4 = (IJavaExpression)this.Dw.generateAST(var1, var2);
         return this.q(var2.getGlobalContext().createOperation(var3, this.RF, var4));
      }
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.paren();
      if (this.oW != null) {
         this.oW.format(var1);
         var1.append(" ? ");
         this.xK.format(var1);
         var1.append(" : ");
         this.Dw.format(var1);
      } else if (this.xK != null) {
         this.xK.format(var1);
         var1.append(" ");
         this.RF.format(var1);
         var1.append(" ");
         this.Dw.format(var1);
      } else {
         this.RF.format(var1);
         this.Dw.format(var1);
      }

      var1.parenClose();
      var1.appendFormattedTypeIf(this.q);
   }
}
