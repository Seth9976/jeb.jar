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
public class afx extends afh implements ICContinue {
   @SerId(1)
   ICLabel RF;

   afx(ICLabel var1) {
      this.RF = var1;
   }

   public afx RF() {
      afx var1 = new afx(this.RF != null ? this.RF.duplicate() : null);
      super.q(var1);
      return var1;
   }

   @Override
   public ICLabel getLabel() {
      return this.RF;
   }

   @Override
   public void setLabel(ICLabel var1) {
      this.RF = var1;
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.RF);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.RF == var1) {
         if (var2 != null && !(var2 instanceof ICLabel)) {
            return false;
         } else {
            this.RF = (ICLabel)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      var1.appendKeyword(CKeyword.CONTINUE);
      if (this.RF != null) {
         var1.space();
         this.RF.generate(var1, false);
      }

      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Continue;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
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
         afx var2 = (afx)var1;
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

   @Override
   public String toString() {
      return "continue" + (this.RF == null ? "" : " " + this.RF.getName());
   }
}
