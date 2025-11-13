package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class apf implements IEVisitor {
   apf(apd var1, IEVar var2, int var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isSlice() && var1.asSlice().getWholeExpression() == this.q && var1.asSlice().getBitEnd() <= this.RF) {
         var3.skipChildren();
      }

      if (var1 == this.q) {
         var3.interrupt(false);
      }
   }
}
