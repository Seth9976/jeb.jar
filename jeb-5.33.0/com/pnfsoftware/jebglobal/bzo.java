package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;

class bzo extends bru {
   bzo(bzn.K var1, CFG var2, long var3, long var5) {
      super(var2, var3, var5);
      this.pC = var1;
   }

   @Override
   protected bru.Av pC(BasicBlock var1) {
      if (bpl.A(var1, false)) {
         return bru.Av.pC();
      } else if (var1 == this.pC.E) {
         return bru.Av.A();
      } else {
         if (!var1.getIrregularOutputs().contains(this.pC.E) && var1.size() >= this.pC.A) {
            bry.Av var2 = this.pC.kS(var1);
            if (var2 != null) {
               bru.Av var3 = bru.Av.A();
               var3.A = var2.sY;
               return var3;
            }
         }

         return this.pC.kS(var1) != null ? bru.Av.A() : bru.Av.pC();
      }
   }
}
