package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaContinue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bmw extends bml implements IJavaContinue {
   @SerId(1)
   private IJavaLabel oW;

   public bmw(IJavaLabel var1) {
      this.oW = var1;
   }

   @Override
   public IJavaLabel getLabel() {
      return this.oW;
   }

   @Override
   public void setLabel(IJavaLabel var1) {
      this.oW = var1;
   }

   @Override
   public List getSubElements() {
      return blo.q(this.oW);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (var2 != null && !(var2 instanceof IJavaLabel)) {
            return false;
         } else {
            this.oW = (IJavaLabel)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      var1.appendKeyword(JavaKeyword.CONTINUE);
      if (this.oW != null) {
         var1.space();
         this.oW.generate(var1, false);
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Continue;
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
         bmw var2 = (bmw)var1;
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
      return "continue" + (this.oW == null ? "" : " " + this.oW.getName());
   }

   public bmw Dw() {
      bmw var1 = new bmw(this.oW == null ? null : this.oW.duplicate());
      this.q(var1);
      return var1;
   }
}
