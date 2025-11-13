package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBreak;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bis extends bio implements IJavaBreak {
   @SerId(1)
   private IJavaLabel wS;

   public bis(IJavaLabel var1) {
      this.wS = var1;
   }

   @Override
   public IJavaLabel getLabel() {
      return this.wS;
   }

   @Override
   public void setLabel(IJavaLabel var1) {
      this.wS = var1;
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.wS);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.wS == var1) {
         if (var2 != null && !(var2 instanceof IJavaLabel)) {
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
      var1.appendKeyword(JavaKeyword.BREAK);
      if (this.wS != null) {
         var1.space();
         this.wS.generate(var1, false);
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Break;
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
         bis var2 = (bis)var1;
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
      return "break" + (this.wS == null ? "" : " " + this.wS.getName());
   }

   public bis A() {
      bis var1 = new bis(this.wS == null ? null : this.wS.duplicate());
      this.pC(var1);
      return var1;
   }
}
