package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class akn implements IEVisitor {
   akn(akm var1) {
      this.pC = var1;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var2 != null && var1 instanceof IEStatement) {
         var3.interrupt(false);
      }
   }
}
