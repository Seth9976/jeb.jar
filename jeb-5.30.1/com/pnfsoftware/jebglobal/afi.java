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
public class afi extends afg implements ICArrayElement {
   @SerId(1)
   ICExpression q;
   @SerId(2)
   ICExpression RF;

   afi(ICExpression var1, ICExpression var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public CElementType getElementType() {
      return CElementType.ArrayElement;
   }

   @Override
   public ICExpression getArray() {
      return this.q;
   }

   @Override
   public void setArray(ICExpression var1) {
      this.q = var1;
   }

   @Override
   public ICExpression getElementIndex() {
      return this.RF;
   }

   @Override
   public void setEltIndex(ICExpression var1) {
      this.RF = var1;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.q, this.RF);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.q == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.q = (ICExpression)var2;
            return true;
         }
      } else if (this.RF == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.RF = (ICExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      this.q.generate(var1);
      var1.bracket();
      this.RF.generate(var1);
      var1.bracketClose();
      this.RF(var1);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         afi var2 = (afi)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         return true;
      }
   }

   public afi RF() {
      afi var1 = new afi(this.q.duplicate(), this.RF.duplicate());
      super.q(var1);
      return var1;
   }

   @Override
   public String toString() {
      return this.q + "[" + this.RF + "]";
   }
}
