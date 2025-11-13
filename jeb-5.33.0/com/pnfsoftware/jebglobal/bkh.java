package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeReference;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bkh extends bin implements IJavaTypeReference {
   @SerId(1)
   private IJavaType A;

   public bkh(IJavaType var1) {
      this.setType(var1);
   }

   @Override
   public IJavaType getType() {
      return this.A;
   }

   @Override
   public void setType(IJavaType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      bhn.pC(var1, this.A);
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.TypeReference;
   }

   @Override
   public String toString() {
      return this.A.toString();
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         bkh var2 = (bkh)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return true;
      }
   }

   public bkh A() {
      bkh var1 = new bkh(this.A);
      this.pC(var1);
      return var1;
   }
}
