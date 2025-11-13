package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEOptFilterCanDiscard;

class KD implements IEOptFilterCanDiscard {
   KD(zp var1, IEMasterOptimizer var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public boolean check(CFG var1, long var2, int var4) {
      return this.pC.getMode().isAggressive() ? var4 < 0 : false;
   }
}
