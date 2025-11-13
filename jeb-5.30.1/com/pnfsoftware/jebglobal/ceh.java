package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;

class ceh extends bwe {
   ceh(ceg.nI var1, CFG var2, long var3, long var5) {
      super(var2, var3, var5);
      this.q = var1;
   }

   @Override
   protected bwe.eo q(BasicBlock var1) {
      if (bto.RF(var1, false)) {
         return bwe.eo.q();
      } else if (var1 == this.q.oW) {
         return bwe.eo.RF();
      } else {
         if (!var1.getIrregularOutputs().contains(this.q.oW) && var1.size() >= this.q.RF) {
            bwj.eo var2 = this.q.xK(var1);
            if (var2 != null) {
               bwe.eo var3 = bwe.eo.RF();
               var3.RF = var2.gO;
               return var3;
            }
         }

         return this.q.xK(var1) != null ? bwe.eo.RF() : bwe.eo.q();
      }
   }
}
