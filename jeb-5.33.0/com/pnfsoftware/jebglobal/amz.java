package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.util.base.Couple;

class amz implements IEVisitor {
   amz(amw var1, IEVar var2, int var3, amw.K var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      boolean var4 = false;
      if (var1 instanceof IESlice var5) {
         if (var5.getWholeExpression() == this.pC) {
            if (var5.getBitStart() >= 0 && var5.getBitEnd() <= this.A) {
               this.kS.UT.add(new Couple(var2, var5));
               var3.skipChildren();
            } else {
               var4 = true;
            }
         }
      } else if (var1 == this.pC) {
         var4 = true;
      }

      if (var4) {
         var3.interrupt(false);
      }
   }
}
