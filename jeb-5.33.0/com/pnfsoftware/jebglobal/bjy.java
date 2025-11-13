package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.lang.invoke.StringConcatFactory;
import java.util.List;

@Ser
public class bjy extends bin implements IJavaPredicate {
   @SerId(1)
   IJavaExpression A;

   public bjy(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public IJavaExpression getExpression() {
      return this.A;
   }

   @Override
   public void setExpression(IJavaExpression var1) {
      this.A = var1;
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.A = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      this.A.generate(var1);
      this.A(var1);
   }

   @Override
   public boolean isLitteralTrue() {
      return this.A instanceof IJavaConstant && ((IJavaConstant)this.A).isTrue();
   }

   @Override
   public boolean isLitteralFalse() {
      return this.A instanceof IJavaConstant && ((IJavaConstant)this.A).isFalse();
   }

   public bjy A() {
      bjy var1 = new bjy(this.A.duplicate());
      super.pC(var1);
      return var1;
   }

   @Override
   public void reverse(IJavaOperatorFactory var1) {
      if (!this.A(var1)) {
         this.A = new bjx(null, var1.get(JavaOperatorType.LOG_NOT), this.A);
      }
   }

   private boolean A(IJavaOperatorFactory var1) {
      if (this.A instanceof bjx var2) {
         if (var2.getOperatorType() == JavaOperatorType.LOG_NOT) {
            this.A = var2.getRight();
            return true;
         } else {
            return var2.reverse(var1);
         }
      } else {
         return false;
      }
   }

   public bjy pC(IJavaOperatorFactory var1) {
      if (this.A instanceof bjx) {
         bjx var2 = (bjx)this.A.duplicate();
         bjx var3 = new bjx(var2.getLeft(), var2.getOperator(), var2.getRight());
         if (var3.reverse(var1)) {
            return new bjy(var3);
         }
      }

      return new bjy(new bjx(null, var1.get(JavaOperatorType.LOG_NOT), this.A.duplicate()));
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bjy var2 = (bjy)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Predicate;
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.A.toString());
   }
}
