package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bkf extends bio implements IJavaThrow {
   @SerId(1)
   private IJavaExpression wS;

   public bkf(IJavaExpression var1) {
      this.setExpression(var1);
   }

   @Override
   public IJavaExpression getExpression() {
      return this.wS;
   }

   @Override
   public void setExpression(IJavaExpression var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.wS);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.wS == var1) {
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
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.THROW);
      var1.space();
      this.wS.generate(var1);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Throw;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
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
         bkf var2 = (bkf)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "throw " + this.wS;
   }

   public bkf A() {
      bkf var1 = new bkf(this.wS.duplicate());
      this.pC(var1);
      return var1;
   }
}
