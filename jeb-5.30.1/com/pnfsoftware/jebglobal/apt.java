package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class apt implements IEVisitor {
   apt(aps var1, AtomicInteger var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEImm && !((IEImm)var1).isMutable()) {
         IEImm var4 = ((IEImm)var1).duplicateWithType(null);
         if (var2.replaceSubExpression(var1, var4)) {
            this.q.incrementAndGet();
         }
      }
   }
}
