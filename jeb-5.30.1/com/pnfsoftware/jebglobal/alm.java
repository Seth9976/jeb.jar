package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class alm implements IEVisitor {
   alm(all var1) {
      this.q = var1;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      var1.verify();
   }
}
