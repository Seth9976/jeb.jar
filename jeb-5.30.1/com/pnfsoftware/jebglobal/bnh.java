package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnh extends bmk implements IJavaAnnotationElement {
   @SerId(1)
   private IJavaConstant Dw;
   @SerId(2)
   private IJavaExpression Uv;

   public bnh(IJavaConstant var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.Dw = var1;
         this.Uv = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaConstant getName() {
      return this.Dw;
   }

   @Override
   public void setName(IJavaConstant var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   @Override
   public IJavaExpression getValue() {
      return this.Uv;
   }

   @Override
   public void setValue(IJavaExpression var1) {
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
         if (!(var2 instanceof IJavaConstant)) {
            return false;
         } else {
            this.Dw = (IJavaConstant)var2;
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
      var1.appendAndRecord(this.Dw.getString(), ItemClassIdentifiers.IDENTIFIER);
      var1.append(" = ");
      this.Uv.generate(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.AnnotationElement;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      return 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
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
         bnh var2 = (bnh)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         if (this.Uv == null) {
            if (var2.Uv != null) {
               return false;
            }
         } else if (!this.Uv.equals(var2.Uv)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.Dw.toString() + "=" + this.Uv.toString();
   }

   public bnh xK() {
      bnh var1 = new bnh(this.Dw.duplicate(), this.Uv.duplicate());
      this.q(var1);
      return var1;
   }
}
