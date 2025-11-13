package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class apn implements IEVisitor {
   apn(apm var1, IEStatement var2, IEVar var3, boolean var4, AtomicInteger var5) {
      this.Uv = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var2 instanceof IEMem && var1 == var2.asMem().getReference()) {
         boolean var4 = this.q.isAssign() && this.q.asAssign().getLeftOperand() == var2;
         if (this.Uv.q(var1, var2, this.RF, this.xK, var4) != null) {
            this.Dw.incrementAndGet();
         }
      }
   }
}
