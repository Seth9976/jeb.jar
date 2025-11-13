package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConditionalExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNewArray;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public class bmf extends AbstractJOptimizer {
   private static final String[][] pC = new String[][]{
      {"Ljava/lang/Byte;", "byteValue", "B"},
      {"Ljava/lang/Short;", "shortValue", "S"},
      {"Ljava/lang/Integer;", "intValue", "I"},
      {"Ljava/lang/Long;", "longValue", "J"},
      {"Ljava/lang/Float;", "floatValue", "F"},
      {"Ljava/lang/Double;", "doubleValue", "D"}
   };

   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         Set var1 = Collections.newSetFromMap(new IdentityHashMap());
         return this.pC(this.m, null, var1);
      }
   }

   private int pC(IJavaElement var1, IJavaElement var2, Set var3) {
      if (!var3.add(var1)) {
         return 0;
      } else {
         IJavaElement var4 = null;
         if (var1 instanceof IJavaOperation var5) {
            var4 = this.pC(var5, var2);
         } else if (var1 instanceof IJavaConditionalExpression var6) {
            var4 = this.pC(var6, var2);
         } else if (var1 instanceof IJavaCall var7) {
            var4 = this.pC(var7, var2);
         } else if (var1 instanceof IJavaReturn var8) {
            var4 = this.pC(var8, var2);
         }

         int var9 = 0;
         if (var4 != null) {
            var1 = var4;
            var9++;
         }

         for (IJavaElement var11 : var1.getSubElements()) {
            var9 += this.pC(var11, var1, var3);
         }

         return var9;
      }
   }

   private IJavaElement pC(IJavaOperation var1, IJavaElement var2) {
      IJavaOperator var3 = var1.getOperator();
      IJavaExpression var4 = var1.getLeft();
      IJavaExpression var5 = var1.getRight();
      if (var3.getType() == JavaOperatorType.LOG_NOT) {
         IJavaExpression var6 = JUtil.resolveLogicalNot(var1, this.jctx);
         if (var6 != null) {
            if (var6 == var1) {
               return var1;
            }

            if (var2.replaceSubElement(var1, var6)) {
               return var6;
            }
         }
      }

      if (var5 instanceof IJavaConstant var11) {
         if (var11.isNegative()) {
            if (var3.isAnyOf(JavaOperatorType.ADD, JavaOperatorType.SUB)) {
               IJavaConstant var7 = JUtil.getNegatedConstant(var11, this.cf);
               if (var7 != null && var7 != var11 && var1.replaceSubElement(var11, var7)) {
                  var1.setOperator(
                     var3.is(JavaOperatorType.ADD) ? this.of.getStandardOperator(JavaOperatorType.SUB) : this.of.getStandardOperator(JavaOperatorType.ADD)
                  );
                  return var1;
               }
            }
         } else if (var3.is(JavaOperatorType.LOG_NOT) && (var11.isFalse() || var11.isTrue())) {
            var1.setRight(var11.isFalse() ? this.cf.createBoolean(true) : this.cf.createBoolean(false));
            var1.setOperator(this.of.getStandardOperator(JavaOperatorType.LOG_IDENT));
            return var1;
         }
      }

      if (var3.is(JavaOperatorType.CONCAT) && JUtil.isStringConstant(var4) && JUtil.isStringConstant(var5)) {
         String var12 = ((IJavaConstant)var4).getOrigin();
         String var15 = ((IJavaConstant)var4).getOrigin();
         if (var12 == null && var15 == null) {
            String var8 = JUtil.getStringConstant(var4);
            String var9 = JUtil.getStringConstant(var5);
            IJavaConstant var10 = this.cf.createString(var8 + var9);
            if (var2.replaceSubElement(var1, var10)) {
               return var10;
            }
         }
      }

      if (var3.isCast()) {
         IJavaType var13 = var3.getCastType();
         if (var13.isArray() && var5 instanceof IJavaNewArray && var2.replaceSubElement(var1, var5)) {
            return var5;
         }

         if (var5 instanceof IJavaOperation var16) {
            if (var16.getOperator().isCast()) {
               IJavaType var20 = var16.getOperator().getCastType();
               if (var20 == var13) {
                  var1.setRight(var16.getRight());
                  return var1;
               }
            }
         } else if (var5 instanceof IJavaCall var18) {
            IJavaType var21 = this.tf.createType(JvmMethodSig.parse(var18.getMethodSignature()).rettype);
            if (var21 == var13 && var2.replaceSubElement(var1, var18)) {
               return var18;
            }
         } else if (var5 instanceof IJavaConditionalExpression) {
            ;
         }
      }

      IJavaElement var14 = this.pC(var4);
      IJavaElement var17 = this.pC(var5);
      if (var14 == null && var17 == null) {
         return null;
      } else {
         int var19 = 0;
         if (var14 != null && var1.replaceSubElement(var4, var14)) {
            var19++;
         }

         if (var17 != null && var1.replaceSubElement(var5, var17)) {
            var19++;
         }

         return var19 == 0 ? null : var1;
      }
   }

   private IJavaElement pC(IJavaElement var1) {
      if (var1 instanceof IJavaCall var2) {
         String var3 = var2.getMethodClass();

         for (String[] var7 : pC) {
            if (var3.equals(var7[0]) && var2.getMethodName().equals(var7[1])) {
               return this.jctx.createCastOperation(this.tf.createType(var7[2]), var2.getArgument(0));
            }
         }
      }

      return null;
   }

   private IJavaExpression A(IJavaElement var1) {
      if (var1 instanceof IJavaCall var2) {
         String var3 = var2.getMethodClass();

         for (String[] var7 : pC) {
            if (var3.equals(var7[0]) && var2.getMethodName().equals("valueOf")) {
               String var8 = var3 + "->valueOf(" + var7[2] + ")" + var3;
               String var9 = var2.getMethodSignature();
               if (var9.equals(var8)) {
                  IJavaExpression var10 = var2.getArgument(0);
                  IJavaType var11 = null;
                  if (var10 instanceof IJavaIdentifier var12) {
                     var11 = var12.getType();
                  } else if (var10 instanceof IJavaConstant var13) {
                     var11 = var13.getType();
                  } else if (var10 instanceof IJavaStaticField var14 && var14.getField() != null) {
                     var11 = var14.getField().getType();
                  } else if (var10 instanceof IJavaInstanceField var15 && var15.getField() != null) {
                     var11 = var15.getField().getType();
                  }

                  if (var11 != null && var11.getSignature().equals(var7[2])) {
                     return var10;
                  }

                  return this.jctx.createCastOperation(this.tf.createType(var7[2]), var10);
               }
            }
         }
      }

      if (var1 instanceof IJavaStaticField var16) {
         String var17 = var16.getFieldSignature();
         if (var17 != null) {
            if (var17.equals("Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;")) {
               return this.cf.createBoolean(false);
            }

            if (var17.equals("Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;")) {
               return this.cf.createBoolean(true);
            }
         }
      }

      return null;
   }

   private IJavaElement pC(IJavaConditionalExpression var1, IJavaElement var2) {
      IJavaExpression var3 = var1.getPredicate();
      IJavaExpression var4 = var1.getExpressionTrue();
      IJavaExpression var5 = var1.getExpressionFalse();
      IJavaConstant var6 = this.cf.createBoolean(true);
      IJavaConstant var7 = this.cf.createBoolean(false);
      if (var4 == var6 && var5 == var7 && var2.replaceSubElement(var1, var3)) {
         return var3;
      } else {
         if (var4 == var7 && var5 == var6 && var3 instanceof IJavaPredicate) {
            ((IJavaPredicate)var3).reverse(this.of);
            if (var2.replaceSubElement(var1, var3)) {
               return var3;
            }
         }

         return null;
      }
   }

   private IJavaElement pC(IJavaCall var1, IJavaElement var2) {
      boolean var3 = true;
      if (var2 instanceof IJavaBlock) {
         var3 = false;
      } else if (var2 instanceof IJavaCall) {
         String var4 = ((IJavaCall)var2).getMethodSignature();
         if (var4.contains("List;->remove(")) {
            var3 = false;
         }
      }

      if (!var3) {
         return null;
      } else {
         Object var5 = this.pC(var1);
         if (var5 == null) {
            var5 = this.A(var1);
         }

         return (IJavaElement)(var5 != null && var2.replaceSubElement(var1, (IJavaElement)var5) ? var5 : null);
      }
   }

   private IJavaElement pC(IJavaReturn var1, IJavaElement var2) {
      IJavaExpression var3 = var1.getExpression();
      if (var3 == null) {
         return null;
      } else if (var3 instanceof IJavaConditionalExpression var9) {
         int var5 = 0;
         IJavaExpression var6 = var9.getExpressionTrue();
         IJavaExpression var7 = this.A(var6);
         if (var7 != null) {
            var9.setExpressionTrue(var7);
            var5++;
         }

         IJavaExpression var8 = var9.getExpressionFalse();
         var7 = this.A(var8);
         if (var7 != null) {
            var9.setExpressionFalse(var7);
            var5++;
         }

         return var5 > 0 ? var1 : null;
      } else {
         IJavaExpression var4 = this.A(var3);
         if (var4 != null) {
            var1.replaceSubElement(var3, var4);
            return var1;
         } else {
            return null;
         }
      }
   }
}
