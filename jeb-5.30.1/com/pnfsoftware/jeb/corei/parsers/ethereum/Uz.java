package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEOptFilterCanDiscard;

class Uz implements IEOptFilterCanDiscard {
   Uz(Nz var1, IEMasterOptimizer var2) {
      this.RF = var1;
      this.q = var2;
   }

   @Override
   public boolean check(CFG var1, long var2, int var4) {
      return this.q.getMode().isAggressive() ? var4 < 0 : false;
   }
}
