package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class all implements IEVisitor {
   all(alk var1, AtomicInteger var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEMem var4 && this.A.A.getConverter().isSegmentEMemReferencingPrimaryMemory(var4) && this.A.pC(var4)) {
         this.pC.incrementAndGet();
      }
   }
}
