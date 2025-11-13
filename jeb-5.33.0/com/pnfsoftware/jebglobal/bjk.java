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
public class bjk extends bin implements IJavaAnnotationElement {
   @SerId(1)
   private IJavaConstant A;
   @SerId(2)
   private IJavaExpression kS;

   public bjk(IJavaConstant var1, IJavaExpression var2) {
      if (var1 != null && var2 != null) {
         this.A = var1;
         this.kS = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaConstant getName() {
      return this.A;
   }

   @Override
   public void setName(IJavaConstant var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public IJavaExpression getValue() {
      return this.kS;
   }

   @Override
   public void setValue(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.A, this.kS);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof IJavaConstant)) {
            return false;
         } else {
            this.A = (IJavaConstant)var2;
            return true;
         }
      } else if (this.kS == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.kS = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      var1.appendAndRecord(this.A.getString(), ItemClassIdentifiers.IDENTIFIER);
      var1.append(" = ");
      this.kS.generate(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.AnnotationElement;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         bjk var2 = (bjk)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.A.toString() + "=" + this.kS.toString();
   }

   public bjk A() {
      bjk var1 = new bjk(this.A.duplicate(), this.kS.duplicate());
      this.pC(var1);
      return var1;
   }
}
