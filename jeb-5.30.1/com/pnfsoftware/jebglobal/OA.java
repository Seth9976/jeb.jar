package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class OA implements IEVisitor {
   OA(tx var1) {
      this.q = var1;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.isMem() && !var1.getUsed().getVarIds().isEmpty() && !this.q.oW.contains(var1)) {
         this.q.oW.add(var1.asMem());
      }
   }
}
