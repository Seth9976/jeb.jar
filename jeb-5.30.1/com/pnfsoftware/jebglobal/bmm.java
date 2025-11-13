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
public class bmm extends bmk implements IJavaArrayElt {
   @SerId(1)
   private IJavaExpression Dw;
   @SerId(2)
   private IJavaExpression Uv;

   public bmm(IJavaExpression var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.Dw = var1;
         this.Uv = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaExpression getArray() {
      return this.Dw;
   }

   @Override
   public IJavaExpression getIndex() {
      return this.Uv;
   }

   @Override
   public void setArray(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   @Override
   public void setIndex(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Uv = var1;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q(this.Dw, this.Uv);
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
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      this.Dw.generate(var1);
      var1.bracket();
      this.Uv.generate(var1);
      var1.bracketClose();
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.ArrayElement;
   }

   @Override
   public String toString() {
      return this.Dw.toString() + "[" + this.Uv.toString() + "]";
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.Dw.hashCode();
      return 31 * var1 + this.Uv.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bmm var2 = (bmm)var1;
         return this.Dw.equals(var2.Dw) && this.Uv.equals(var2.Uv);
      } else {
         return false;
      }
   }

   public bmm xK() {
      bmm var1 = new bmm(this.Dw.duplicate(), this.Uv.duplicate());
      this.q(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
