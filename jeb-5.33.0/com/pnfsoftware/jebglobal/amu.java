package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.Set;

class amu implements IEVisitor {
   amu(amt var1, Set var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar && ((IEVar)var1).isRegister()) {
         int var4 = ((IEVar)var1).getId();
         if (this.A.pC(var4)) {
            this.pC.add(var4);
         }
      }
   }
}
