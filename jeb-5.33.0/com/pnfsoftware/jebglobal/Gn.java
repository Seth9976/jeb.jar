package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.concurrent.Callable;

class Gn implements Callable {
   Gn(ia var1, String var2) {
      this.A = var1;
      this.pC = var2;
   }

   public Long pC() throws Exception {
      if (this.A.gp() != null) {
         LD var1 = this.A.gp().pC(true);
         return RegisterUtil.getValueByNameAsLong(var1, this.pC);
      } else {
         return null;
      }
   }
}
