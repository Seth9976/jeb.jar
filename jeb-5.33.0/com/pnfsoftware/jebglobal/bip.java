package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaArrayElt;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bip extends bin implements IJavaArrayElt {
   @SerId(1)
   private IJavaExpression A;
   @SerId(2)
   private IJavaExpression kS;

   public bip(IJavaExpression var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.A = var1;
         this.kS = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaExpression getArray() {
      return this.A;
   }

   @Override
   public IJavaExpression getIndex() {
      return this.kS;
   }

   @Override
   public void setArray(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public void setIndex(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.A, this.kS);
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
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      this.A.generate(var1);
      var1.bracket();
      this.kS.generate(var1);
      var1.bracketClose();
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.ArrayElement;
   }

   @Override
   public String toString() {
      return this.A.toString() + "[" + this.kS.toString() + "]";
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.A.hashCode();
      return 31 * var1 + this.kS.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bip var2 = (bip)var1;
         return this.A.equals(var2.A) && this.kS.equals(var2.kS);
      } else {
         return false;
      }
   }

   public bip A() {
      bip var1 = new bip(this.A.duplicate(), this.kS.duplicate());
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
