package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICArrayElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class adp extends adn implements ICArrayElement {
   @SerId(1)
   ICExpression pC;
   @SerId(2)
   ICExpression A;

   adp(ICExpression var1, ICExpression var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public CElementType getElementType() {
      return CElementType.ArrayElement;
   }

   @Override
   public ICExpression getArray() {
      return this.pC;
   }

   @Override
   public void setArray(ICExpression var1) {
      this.pC = var1;
   }

   @Override
   public ICExpression getElementIndex() {
      return this.A;
   }

   @Override
   public void setEltIndex(ICExpression var1) {
      this.A = var1;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.pC, this.A);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.pC == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.pC = (ICExpression)var2;
            return true;
         }
      } else if (this.A == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.A = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      this.pC.generate(var1);
      var1.bracket();
      this.A.generate(var1);
      var1.bracketClose();
      this.A(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         adp var2 = (adp)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
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

   public adp A() {
      adp var1 = new adp(this.pC.duplicate(), this.A.duplicate());
      super.pC(var1);
      return var1;
   }

   @Override
   public String toString() {
      return this.pC + "[" + this.A + "]";
   }
}
