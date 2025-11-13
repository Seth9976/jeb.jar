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
public class bjj extends bin implements IJavaAnnotation {
   @SerId(1)
   private IJavaType A;
   @SerId(2)
   private List kS;

   public bjj(IJavaType var1, List var2) {
      if (var1 != null && var2 != null) {
         this.A = var1;
         this.kS = var2;
      } else {
         throw new IllegalArgumentException();
      }
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
   public List getElements() {
      return this.kS;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.generate(var1, '\u0000');
   }

   @Override
   public void generate(JavaOutputSink var1, char var2) {
      this.pC(var1);
      var1.append("@");
      bhn.pC(var1, this.A);
      if (!this.kS.isEmpty()) {
         var1.paren();
         if (this.kS.size() == 1 && ((IJavaAnnotationElement)this.kS.get(0)).getName().getString().equals("value")) {
            ((IJavaAnnotationElement)this.kS.get(0)).getValue().generate(var1);
         } else {
            int var3 = 0;

            for (IJavaAnnotationElement var5 : this.kS) {
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

      this.A(var1);
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.kS);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.kS.size(); var3++) {
         IJavaAnnotationElement var4 = (IJavaAnnotationElement)this.kS.get(var3);
         if (var4 == var1) {
            if (!(var2 instanceof bjk)) {
               return false;
            }

            this.kS.set(var3, (bjk)var2);
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
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         bjj var2 = (bjj)var1;
         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

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

   @Override
   public String toString() {
      return "@" + this.A.getName() + "(" + this.kS + ")";
   }

   public bjj A() {
      ArrayList var1 = new ArrayList(this.kS.size());
      this.kS.forEach(var1x -> var1.add(var1x.duplicate()));
      bjj var2 = new bjj(this.A, var1);
      this.pC(var2);
      return var2;
   }
}
