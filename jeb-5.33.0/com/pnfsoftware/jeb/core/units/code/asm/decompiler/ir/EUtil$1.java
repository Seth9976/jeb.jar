package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.IFormattingContextFactory;
import com.pnfsoftware.jebglobal.ajn;

class EUtil$1 implements IFormattingContextFactory {
   EUtil$1(boolean var1) {
      this.val$displayTypes = var1;
   }

   public Object createFormattingContext(IEStatement var1) {
      return new ajn(true, true, this.val$displayTypes);
   }
}
