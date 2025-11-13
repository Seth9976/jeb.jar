package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class aqw implements IEVisitor {
   aqw(aqv var1) {
      this.q = var1;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem && this.q.xK(var1) && var2.replaceSubExpression(var1, this.q.q(var1.getBitsize()))) {
         this.q.RF++;
      }
   }
}
