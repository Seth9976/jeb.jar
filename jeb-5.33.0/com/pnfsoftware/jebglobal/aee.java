package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class aee extends ado implements ICContinue {
   @SerId(1)
   ICLabel A;

   aee(ICLabel var1) {
      this.A = var1;
   }

   public aee A() {
      aee var1 = new aee(this.A != null ? this.A.duplicate() : null);
      super.pC(var1);
      return var1;
   }

   @Override
   public ICLabel getLabel() {
      return this.A;
   }

   @Override
   public void setLabel(ICLabel var1) {
      this.A = var1;
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.A == var1) {
         if (var2 != null && !(var2 instanceof ICLabel)) {
            return false;
         } else {
            this.A = (ICLabel)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(CKeyword.CONTINUE);
      if (this.A != null) {
         var1.space();
         this.A.generate(var1, false);
      }

      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Continue;
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
         aee var2 = (aee)var1;
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
      return "continue" + (this.A == null ? "" : " " + this.A.getName());
   }
}
