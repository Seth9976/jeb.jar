package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConditionalExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bmt extends bmk implements IJavaConditionalExpression {
   @SerId(1)
   private IJavaExpression Dw;
   @SerId(2)
   private IJavaExpression Uv;
   @SerId(3)
   private IJavaExpression oW;

   public bmt(IJavaExpression var1, IJavaExpression var2, IJavaExpression var3) {
      if (var1 != null && var2 != null && var3 != null) {
         this.Dw = var1;
         this.Uv = var2;
         this.oW = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaExpression getPredicate() {
      return this.Dw;
   }

   @Override
   public void setPredicate(IJavaExpression var1) {
      this.Dw = var1;
   }

   @Override
   public IJavaExpression getExpressionTrue() {
      return this.Uv;
   }

   @Override
   public void setExpressionTrue(IJavaExpression var1) {
      this.Uv = var1;
   }

   @Override
   public IJavaExpression getExpressionFalse() {
      return this.oW;
   }

   @Override
   public void setExpressionFalse(IJavaExpression var1) {
      this.oW = var1;
   }

   @Override
   public List getSubElements() {
      return blo.q(this.Dw, this.Uv, this.oW);
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
      } else if (this.Uv == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.Uv = (IJavaExpression)var2;
            return true;
         }
      } else if (this.oW == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.oW = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      IJavaElement var2 = var1.astParent();
      this.q(var1);
      boolean var3 = (!(var2 instanceof IJavaReturn) || ((IJavaReturn)var2).getExpression() != this)
         && (!(var2 instanceof IJavaThrow) || ((IJavaThrow)var2).getExpression() != this)
         && (!(var2 instanceof IJavaAssignment) || !((IJavaAssignment)var2).isSimpleAssignment() || ((IJavaAssignment)var2).getRight() != this);
      if (var3) {
         var1.paren();
      }

      this.Dw.generate(var1);
      var1.append(" ? ");
      this.Uv.generate(var1);
      var1.append(" : ");
      this.oW.generate(var1);
      if (var3) {
         var1.parenClose();
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.ConditionalExpression;
   }

   @Override
   public String toString() {
      return this.Dw.toString() + " ? " + this.Uv.toString() + " : " + this.oW.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + this.Uv.hashCode();
      return 31 * var1 + this.oW.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bmt var2 = (bmt)var1;
         return this.Dw.equals(var2.Dw) && this.Uv.equals(var2.Uv) && this.oW.equals(var2.oW);
      } else {
         return false;
      }
   }

   public bmt xK() {
      bmt var1 = new bmt(this.Dw.duplicate(), this.Uv.duplicate(), this.oW.duplicate());
      this.q(var1);
      return var1;
   }
}
