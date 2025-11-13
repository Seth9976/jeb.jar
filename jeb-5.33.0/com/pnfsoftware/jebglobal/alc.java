package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class alc implements IEVisitor {
   int pC;

   alc(ala var1, Class var2, int var3, IEGeneric[] var4) {
      this.UT = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (this.A.isInstance(var1)) {
         if (this.pC >= this.kS) {
            this.wS[0] = var1;
            var3.interrupt(true);
         }

         this.pC++;
      }
   }
}
