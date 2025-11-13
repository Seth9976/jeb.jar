package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class and implements IEVisitor {
   and(anc var1, IEStatement var2, IEVar var3, boolean var4, AtomicInteger var5) {
      this.UT = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var2 instanceof IEMem && var1 == var2.asMem().getReference()) {
         boolean var4 = this.pC.isAssign() && this.pC.asAssign().getLeftOperand() == var2;
         if (this.UT.pC(var1, var2, this.A, this.kS, var4) != null) {
            this.wS.incrementAndGet();
         }
      }
   }
}
