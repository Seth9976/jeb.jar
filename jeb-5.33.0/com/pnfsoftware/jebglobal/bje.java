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
public class bje extends bio implements IJavaGoto {
   @SerId(1)
   private IJavaLabel wS;

   public bje(IJavaLabel var1) {
      this.setLabel(var1);
   }

   @Override
   public IJavaLabel getLabel() {
      return this.wS;
   }

   @Override
   public void setLabel(IJavaLabel var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
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
         if (!(var2 instanceof IJavaLabel)) {
            return false;
         } else {
            this.wS = (IJavaLabel)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(JavaKeyword.GOTO);
      var1.append(" ");
      this.wS.generate(var1, false);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Goto;
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
         bje var2 = (bje)var1;
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
      return "goto " + this.getLabel().getName();
   }

   public bje A() {
      bje var1 = new bje(this.wS.duplicate());
      this.pC(var1);
      return var1;
   }
}
