package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEOptFilterCanDiscard;

class HA implements IEOptFilterCanDiscard {
   HA(eM var1) {
      this.q = var1;
   }

   @Override
   public boolean check(CFG var1, long var2, int var4) {
      return var4 < 0;
   }
}
