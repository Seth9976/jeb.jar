package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class amy implements IEVisitor {
   amy(amw var1, IEVar var2, int var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IESlice var4 && var4.getWholeExpression() == this.pC && var4.getBitEnd() <= this.A) {
         var3.skipChildren();
      }

      if (var1 == this.pC) {
         var3.interrupt(false);
      }
   }
}
