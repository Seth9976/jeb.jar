package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.concurrent.Callable;

class jU implements Callable {
   jU(um var1, String var2) {
      this.RF = var1;
      this.q = var2;
   }

   public Long q() throws Exception {
      if (this.RF.lm() != null) {
         Ht var1 = this.RF.lm().q(true);
         return RegisterUtil.getValueByNameAsLong(var1, this.q);
      } else {
         return null;
      }
   }
}
