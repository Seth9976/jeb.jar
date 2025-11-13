package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class arl implements IEVisitor {
   arl(ark var1, IEVar var2, int var3, IEGeneric var4, AtomicInteger var5) {
      this.Uv = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IESlice var4 && var4.getWholeExpression() == this.q && var4.getBitStart() == 0 && var4.getBitEnd() == this.RF) {
         IEGeneric var5 = this.xK.duplicate();
         if (!var2.replaceSubExpression(var1, var5)) {
            var3.interrupt(false);
            return;
         }

         var3.setReplacedNode(var5);
         var3.skipChildren();
         this.Dw.incrementAndGet();
      }
   }
}
