package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class ajj implements IEVisitor {
   ajj(aji var1) {
      this.pC = var1;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      var1.verify();
   }
}
