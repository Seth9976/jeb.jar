package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class aka implements IEVisitor {
   aka(ajz var1) {
      this.pC = var1;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar) {
         if (((IEVar)var1).isGlobalReference()) {
            var3.interrupt(true, 1);
         } else if (((IEVar)var1).isStackReference()) {
            var3.interrupt(true, 2);
         }
      }
   }
}
