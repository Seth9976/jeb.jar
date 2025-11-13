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
public class boe extends bmk implements IJavaTypeReference {
   @SerId(1)
   private IJavaType Dw;

   public boe(IJavaType var1) {
      this.setType(var1);
   }

   @Override
   public IJavaType getType() {
      return this.Dw;
   }

   @Override
   public void setType(IJavaType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      blk.q(var1, this.Dw);
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.TypeReference;
   }

   @Override
   public String toString() {
      return this.Dw.toString();
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
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
         boe var2 = (boe)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         return true;
      }
   }

   public boe xK() {
      boe var1 = new boe(this.Dw);
      this.q(var1);
      return var1;
   }
}
