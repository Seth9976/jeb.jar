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
public class biq extends bio implements IJavaAssignment {
   @SerId(1)
   private IJavaLeftExpression wS;
   @SerId(2)
   private IJavaExpression UT;
   @SerId(3)
   private IJavaOperator E;
   @SerId(4)
   private boolean sY;
   @SerId(5)
   private boolean ys;

   private biq(IJavaLeftExpression var1, IJavaExpression var2, IJavaOperator var3, boolean var4, boolean var5) {
      this.wS = var1;
      this.UT = var2;
      this.E = var3;
      this.sY = var4;
      this.ys = var5;
   }

   public biq(IJavaLeftExpression var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.wS = var1;
         this.UT = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public boolean isSimpleAssignment() {
      return this.UT != null && this.E == null;
   }

   @Override
   public boolean isCombinedOperatorAssignment() {
      return this.UT != null && this.E != null;
   }

   @Override
   public IJavaOperator getCombinedOperator() {
      return !this.isCombinedOperatorAssignment() ? null : this.E;
   }

   @Override
   public boolean isUnaryOperatorAssignment() {
      return this.UT == null;
   }

   @Override
   public void getUnaryOperator(boolean[] var1) {
      if (!this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.length >= 2) {
         var1[0] = this.sY;
         var1[1] = this.ys;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaLeftExpression getLeft() {
      return this.wS;
   }

   @Override
   public void setLeft(IJavaLeftExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public IJavaExpression getRight() {
      return this.UT;
   }

   @Override
   public void setRight(IJavaExpression var1) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   @Override
   public void setCombinedOperatorAssignment(IJavaOperator var1, IJavaExpression var2) {
      if (this.isUnaryOperatorAssignment()) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isValidForCombinedAssignment()) {
         this.E = var1;
         if (var2 != null) {
            this.UT = var2;
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
      this.UT = null;
      this.E = null;
      this.sY = var1;
      this.ys = var2;
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.wS, this.UT);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.wS == var1) {
         if (!(var2 instanceof IJavaLeftExpression)) {
            return false;
         } else {
            this.wS = (IJavaLeftExpression)var2;
            return true;
         }
      } else if (this.UT == var1) {
         if (var2 != null && !(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.UT = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      if (this.UT == null) {
         if (this.ys) {
            var1.append(this.sY ? "++" : "--");
         }

         this.wS.generate(var1);
         if (!this.ys) {
            var1.append(this.sY ? "++" : "--");
         }
      } else {
         this.wS.generate(var1);
         if (this.E != null) {
            var1.append(Strings.ff(" %s= ", this.E.toString()));
         } else {
            var1.append(" = ");
         }

         this.UT.generate(var1);
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Assignment;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      var1 = 31 * var1 + (this.sY ? 1231 : 1237);
      return 31 * var1 + (this.ys ? 1231 : 1237);
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
         biq var2 = (biq)var1;
         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
            return false;
         }

         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         return this.sY != var2.sY ? false : this.ys == var2.ys;
      }
   }

   @Override
   public String toString() {
      String var2;
      if (this.UT != null) {
         var2 = this.wS + " ";
         if (this.E != null) {
            var2 = var2 + this.E.toString();
         }

         var2 = var2 + "= " + this.UT;
      } else {
         Object var3 = this.sY ? "++" : "--";
         if (this.ys) {
            var2 = var3 + this.wS;
         } else {
            var2 = this.wS + var3;
         }
      }

      return var2;
   }

   public biq A() {
      biq var1 = new biq(this.wS.duplicate(), this.UT == null ? null : this.UT.duplicate(), this.E, this.sY, this.ys);
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return this.E != null && this.E.canCauseException();
   }
}
