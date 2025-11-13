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
public class bnv extends bmk implements IJavaPredicate {
   @SerId(1)
   IJavaExpression Dw;

   public bnv(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   @Override
   public IJavaExpression getExpression() {
      return this.Dw;
   }

   @Override
   public void setExpression(IJavaExpression var1) {
      this.Dw = var1;
   }

   @Override
   public List getSubElements() {
      return blo.q(this.Dw);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.Dw == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.Dw = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      this.Dw.generate(var1);
      this.RF(var1);
   }

   @Override
   public boolean isLitteralTrue() {
      return this.Dw instanceof IJavaConstant && ((IJavaConstant)this.Dw).isTrue();
   }

   @Override
   public boolean isLitteralFalse() {
      return this.Dw instanceof IJavaConstant && ((IJavaConstant)this.Dw).isFalse();
   }

   public bnv xK() {
      bnv var1 = new bnv(this.Dw.duplicate());
      super.q(var1);
      return var1;
   }

   @Override
   public void reverse(IJavaOperatorFactory var1) {
      if (!this.RF(var1)) {
         this.Dw = new bnu(null, var1.get(JavaOperatorType.LOG_NOT), this.Dw);
      }
   }

   private boolean RF(IJavaOperatorFactory var1) {
      if (this.Dw instanceof bnu var2) {
         if (var2.getOperatorType() == JavaOperatorType.LOG_NOT) {
            this.Dw = var2.getRight();
            return true;
         } else {
            return var2.reverse(var1);
         }
      } else {
         return false;
      }
   }

   public bnv q(IJavaOperatorFactory var1) {
      if (this.Dw instanceof bnu) {
         bnu var2 = (bnu)this.Dw.duplicate();
         bnu var3 = new bnu(var2.getLeft(), var2.getOperator(), var2.getRight());
         if (var3.reverse(var1)) {
            return new bnv(var3);
         }
      }

      return new bnv(new bnu(null, var1.get(JavaOperatorType.LOG_NOT), this.Dw.duplicate()));
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
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
         bnv var2 = (bnv)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
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
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.Dw.toString());
   }
}
