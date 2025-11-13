package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class Ql implements IEVisitor {
   Ql(Ei var1) {
      this.pC = var1;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isMem() && !var1.getUsed().getVarIds().isEmpty() && !this.pC.E.contains(var1)) {
         this.pC.E.add(var1.asMem());
      }
   }
}
