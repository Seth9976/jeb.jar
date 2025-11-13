package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnx extends bml implements IJavaReturn {
   @SerId(1)
   private IJavaExpression oW;

   public bnx(IJavaExpression var1) {
      this.oW = var1;
   }

   @Override
   public IJavaExpression getExpression() {
      return this.oW;
   }

   @Override
   public void setExpression(IJavaExpression var1) {
      this.oW = var1;
   }

   @Override
   public boolean returnsVoid() {
      return this.oW == null;
   }

   @Override
   public List getSubElements() {
      return blo.q(this.oW);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (var2 != null && !(var2 instanceof IJavaExpression)) {
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
      this.q(var1);
      var1.appendKeyword(JavaKeyword.RETURN);
      if (this.oW != null) {
         var1.append(" ");
         this.oW.generate(var1);
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Return;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
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
         bnx var2 = (bnx)var1;
         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "return" + (this.oW == null ? "" : " " + this.oW);
   }

   public bnx Dw() {
      bnx var1 = new bnx(this.oW == null ? null : this.oW.duplicate());
      this.q(var1);
      return var1;
   }
}
