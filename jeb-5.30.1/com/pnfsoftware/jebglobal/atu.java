package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class atu implements IEVisitor {
   atu(att var1, IEGeneric var2, IEVar var3, AtomicInteger var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.equalsEx(this.q, false) && var2.replaceSubExpression(var1, this.RF)) {
         var3.setReplacedNode(this.RF);
         this.xK.incrementAndGet();
      }
   }
}
