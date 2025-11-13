package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.concurrent.atomic.AtomicInteger;

class aoz implements IEVisitor {
   aoz(aoy var1, IEVar var2, int var3, IEGeneric var4, AtomicInteger var5) {
      this.UT = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IESlice var4 && var4.getWholeExpression() == this.pC && var4.getBitStart() == 0 && var4.getBitEnd() == this.A) {
         IEGeneric var5 = this.kS.duplicate();
         if (!var2.replaceSubExpression(var1, var5)) {
            var3.interrupt(false);
            return;
         }

         var3.setReplacedNode(var5);
         var3.skipChildren();
         this.wS.incrementAndGet();
      }
   }
}
