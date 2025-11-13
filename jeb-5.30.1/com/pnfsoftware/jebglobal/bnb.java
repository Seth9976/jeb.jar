package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnb extends bml implements IJavaGoto {
   @SerId(1)
   private IJavaLabel oW;

   public bnb(IJavaLabel var1) {
      this.setLabel(var1);
   }

   @Override
   public IJavaLabel getLabel() {
      return this.oW;
   }

   @Override
   public void setLabel(IJavaLabel var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q(this.oW);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.oW == var1) {
         if (!(var2 instanceof IJavaLabel)) {
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
      var1.appendKeyword(JavaKeyword.GOTO);
      var1.append(" ");
      this.oW.generate(var1, false);
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Goto;
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
         bnb var2 = (bnb)var1;
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
      return "goto " + this.getLabel().getName();
   }

   public bnb Dw() {
      bnb var1 = new bnb(this.oW.duplicate());
      this.q(var1);
      return var1;
   }
}
