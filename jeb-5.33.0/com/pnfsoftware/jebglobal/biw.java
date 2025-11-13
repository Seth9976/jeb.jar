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
public class biw extends bin implements IJavaConditionalExpression {
   @SerId(1)
   private IJavaExpression A;
   @SerId(2)
   private IJavaExpression kS;
   @SerId(3)
   private IJavaExpression wS;

   public biw(IJavaExpression var1, IJavaExpression var2, IJavaExpression var3) {
      if (var1 != null && var2 != null && var3 != null) {
         this.A = var1;
         this.kS = var2;
         this.wS = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaExpression getPredicate() {
      return this.A;
   }

   @Override
   public void setPredicate(IJavaExpression var1) {
      this.A = var1;
   }

   @Override
   public IJavaExpression getExpressionTrue() {
      return this.kS;
   }

   @Override
   public void setExpressionTrue(IJavaExpression var1) {
      this.kS = var1;
   }

   @Override
   public IJavaExpression getExpressionFalse() {
      return this.wS;
   }

   @Override
   public void setExpressionFalse(IJavaExpression var1) {
      this.wS = var1;
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.A, this.kS, this.wS);
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
      } else if (this.kS == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.kS = (IJavaExpression)var2;
            return true;
         }
      } else if (this.wS == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.wS = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      IJavaElement var2 = var1.astParent();
      this.pC(var1);
      boolean var3 = (!(var2 instanceof IJavaReturn) || ((IJavaReturn)var2).getExpression() != this)
         && (!(var2 instanceof IJavaThrow) || ((IJavaThrow)var2).getExpression() != this)
         && (!(var2 instanceof IJavaAssignment) || !((IJavaAssignment)var2).isSimpleAssignment() || ((IJavaAssignment)var2).getRight() != this);
      if (var3) {
         var1.paren();
      }

      this.A.generate(var1);
      var1.append(" ? ");
      this.kS.generate(var1);
      var1.append(" : ");
      this.wS.generate(var1);
      if (var3) {
         var1.parenClose();
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.ConditionalExpression;
   }

   @Override
   public String toString() {
      return this.A.toString() + " ? " + this.kS.toString() + " : " + this.wS.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + this.kS.hashCode();
      return 31 * var1 + this.wS.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         biw var2 = (biw)var1;
         return this.A.equals(var2.A) && this.kS.equals(var2.kS) && this.wS.equals(var2.wS);
      } else {
         return false;
      }
   }

   public biw A() {
      biw var1 = new biw(this.A.duplicate(), this.kS.duplicate(), this.wS.duplicate());
      this.pC(var1);
      return var1;
   }
}
