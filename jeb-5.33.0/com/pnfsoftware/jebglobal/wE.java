package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class wE extends Dd {
   @SerId(1)
   private ICExpression pC;

   public wE(ICExpression var1, ICExpression var2) {
      super(var1);
      this.pC = var2;
   }

   @Override
   public void generate(COutputSink var1) {
      if (this.getElementType() == CElementType.Constant) {
         super.generate(var1);
      } else {
         var1.append("{");
         this.pC.generate(var1);
         var1.append("}");
      }
   }

   public wE pC() {
      return new wE(this.kS().duplicate(), this.pC.duplicate());
   }

   @Override
   public List getSubElements() {
      List var1 = super.getSubElements();
      return afm.pC(var1, this.pC);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      boolean var3 = super.replaceSubElement(var1, var2);
      if (var3) {
         return true;
      } else if (this.pC == var1) {
         this.pC = (ICExpression)var2;
         return true;
      } else {
         return false;
      }
   }
}
