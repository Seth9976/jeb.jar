package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class yK extends xx {
   @SerId(1)
   private ICExpression q;

   public yK(ICExpression var1, ICExpression var2) {
      super(var1);
      this.q = var2;
   }

   @Override
   public void generate(COutputSink var1) {
      if (this.getElementType() == CElementType.Constant) {
         super.generate(var1);
      } else {
         var1.append("{");
         this.q.generate(var1);
         var1.append("}");
      }
   }

   public yK q() {
      return new yK(this.xK().duplicate(), this.q.duplicate());
   }

   @Override
   public List getSubElements() {
      List var1 = super.getSubElements();
      return ahf.q(var1, this.q);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      boolean var3 = super.replaceSubElement(var1, var2);
      if (var3) {
         return true;
      } else if (this.q == var1) {
         this.q = (ICExpression)var2;
         return true;
      } else {
         return false;
      }
   }
}
