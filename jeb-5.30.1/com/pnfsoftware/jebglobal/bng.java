package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaAnnotation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bng extends bmk implements IJavaAnnotation {
   @SerId(1)
   private IJavaType Dw;
   @SerId(2)
   private List Uv;

   public bng(IJavaType var1, List var2) {
      if (var1 != null && var2 != null) {
         this.Dw = var1;
         this.Uv = var2;
      } else {
         throw new IllegalArgumentException();
      }
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
   public List getElements() {
      return this.Uv;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.generate(var1, '\u0000');
   }

   @Override
   public void generate(JavaOutputSink var1, char var2) {
      this.q(var1);
      var1.append("@");
      blk.q(var1, this.Dw);
      if (!this.Uv.isEmpty()) {
         var1.paren();
         if (this.Uv.size() == 1 && ((IJavaAnnotationElement)this.Uv.get(0)).getName().getString().equals("value")) {
            ((IJavaAnnotationElement)this.Uv.get(0)).getValue().generate(var1);
         } else {
            int var3 = 0;

            for (IJavaAnnotationElement var5 : this.Uv) {
               if (var3++ > 0) {
                  var1.append(", ");
               }

               var5.generate(var1);
            }
         }

         var1.parenClose();
      }

      if (var2 == '\n') {
         var1.eol();
      } else if (var2 == ' ') {
         var1.space();
      } else if (var2 != 0) {
         throw new RuntimeException("Illegal character after annotation: " + var2);
      }

      this.RF(var1);
   }

   @Override
   public List getSubElements() {
      return blo.q(this.Uv);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.Uv.size(); var3++) {
         IJavaAnnotationElement var4 = (IJavaAnnotationElement)this.Uv.get(var3);
         if (var4 == var1) {
            if (!(var2 instanceof bnh)) {
               return false;
            }

            this.Uv.set(var3, (bnh)var2);
            return true;
         }
      }

      return false;
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Annotation;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
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
         bng var2 = (bng)var1;
         if (this.Uv == null) {
            if (var2.Uv != null) {
               return false;
            }
         } else if (!this.Uv.equals(var2.Uv)) {
            return false;
         }

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

   @Override
   public String toString() {
      return "@" + this.Dw.getName() + "(" + this.Uv + ")";
   }

   public bng xK() {
      ArrayList var1 = new ArrayList(this.Uv.size());
      this.Uv.forEach(var1x -> var1.add(var1x.duplicate()));
      bng var2 = new bng(this.Dw, var1);
      this.q(var2);
      return var2;
   }
}
