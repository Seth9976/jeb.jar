package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class ane implements IEVisitor {
   ane(anc var1, IEVar var2, boolean var3, AtomicInteger var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 == this.pC || var1 instanceof IEOperation && !EUtil.isLogicalOperation(var1)) {
         IEGeneric var4 = this.wS.pC(var1, var2, this.pC, this.A, null);
         if (var4 != null) {
            var3.setReplacedNode(var4);
            this.kS.incrementAndGet();
         }
      }
   }
}
