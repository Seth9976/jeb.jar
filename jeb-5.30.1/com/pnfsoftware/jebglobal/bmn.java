package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bmn extends bml implements IJavaAssignment {
   @SerId(1)
   private IJavaLeftExpression oW;
   @SerId(2)
   private IJavaExpression gO;
   @SerId(3)
   private IJavaOperator nf;
   @SerId(4)
   private boolean gP;
   @SerId(5)
   private boolean za;

   private bmn(IJavaLeftExpression var1, IJavaExpression var2, IJavaOperator var3, boolean var4, boolean var5) {
      this.oW = var1;
      this.gO = var2;
      this.nf = var3;
      this.gP = var4;
      this.za = var5;
   }

   public bmn(IJavaLeftExpression var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.oW = var1;
         this.gO = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public boolean isSimpleAssignment() {
      return this.gO != null && this.nf == null;
   }

   @Override
   public boolean isCombinedOperatorAssignment() {
      return this.gO != null && this.nf != null;
   }

   @Override
   public IJavaOperator getCombinedOperator() {
      return !this.isCombinedOperatorAssignment() ? null : this.nf;
   }

   @Override
   public boolean isUnaryOperatorAssignment() {
      return this.gO == null;
   }

   @Override
   public void getUnaryOperator(boolean[] var1) {
      if (!this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.length >= 2) {
         var1[0] = this.gP;
         var1[1] = this.za;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaLeftExpression getLeft() {
      return this.oW;
   }

   @Override
   public void setLeft(IJavaLeftExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
      }
   }

   @Override
   public IJavaExpression getRight() {
      return this.gO;
   }

   @Override
   public void setRight(IJavaExpression var1) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1;
      }
   }

   @Override
   public void setCombinedOperatorAssignment(IJavaOperator var1, IJavaExpression var2) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isValidForCombinedAssignment()) {
         this.nf = var1;
         if (var2 != null) {
            this.gO = var2;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void setCombinedOperator(IJavaOperator var1) {
      this.setCombinedOperatorAssignment(var1, null);
   }

   @Override
   public void setUnaryOperator(boolean var1, boolean var2) {
      this.gO = null;
      this.nf = null;
      this.gP = var1;
      this.za = var2;
   }

   @Override
   public List getSubElements() {
      return blo.q(this.oW, this.gO);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (!(var2 instanceof IJavaLeftExpression)) {
            return false;
         } else {
            this.oW = (IJavaLeftExpression)var2;
            return true;
         }
      } else if (this.gO == var1) {
         if (var2 != null && !(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.gO = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      if (this.gO == null) {
         if (this.za) {
            var1.append(this.gP ? "++" : "--");
         }

         this.oW.generate(var1);
         if (!this.za) {
            var1.append(this.gP ? "++" : "--");
         }
      } else {
         this.oW.generate(var1);
         if (this.nf != null) {
            var1.append(Strings.ff(" %s= ", this.nf.toString()));
         } else {
            var1.append(" = ");
         }

         this.gO.generate(var1);
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Assignment;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
      var1 = 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
      var1 = 31 * var1 + (this.gP ? 1231 : 1237);
      return 31 * var1 + (this.za ? 1231 : 1237);
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
         bmn var2 = (bmn)var1;
         if (this.nf == null) {
            if (var2.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var2.nf)) {
            return false;
         }

         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         if (this.gO == null) {
            if (var2.gO != null) {
               return false;
            }
         } else if (!this.gO.equals(var2.gO)) {
            return false;
         }

         return this.gP != var2.gP ? false : this.za == var2.za;
      }
   }

   @Override
   public String toString() {
      String var2;
      if (this.gO != null) {
         var2 = this.oW + " ";
         if (this.nf != null) {
            var2 = var2 + this.nf.toString();
         }

         var2 = var2 + "= " + this.gO;
      } else {
         Object var3 = this.gP ? "++" : "--";
         if (this.za) {
            var2 = var3 + this.oW;
         } else {
            var2 = this.oW + var3;
         }
      }

      return var2;
   }

   public bmn Dw() {
      bmn var1 = new bmn(this.oW.duplicate(), this.gO == null ? null : this.gO.duplicate(), this.nf, this.gP, this.za);
      this.q(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return this.nf != null && this.nf.canCauseException();
   }
}
