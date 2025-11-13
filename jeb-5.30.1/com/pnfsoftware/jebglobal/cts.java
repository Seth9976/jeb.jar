package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class cts implements IEVisitor {
   cts(ctr var1, AtomicInteger var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem) {
         IEMem var4 = var1.asMem();
         if (var4.getSegment() instanceof IEVar) {
            int var5 = var4.getSegment().asVar().getId();
            if (this.RF.q.contains(var5)) {
               var4.setSegment(null);
               this.q.incrementAndGet();
            }
         }
      }
   }
}
