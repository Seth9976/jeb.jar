package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEOptFilterCanDiscard;

class bO implements IEOptFilterCanDiscard {
   bO(Ws var1, IEMasterOptimizer var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public boolean check(CFG var1, long var2, int var4) {
      return this.pC.getMode().isAggressive() ? var4 < 0 : false;
   }
}
