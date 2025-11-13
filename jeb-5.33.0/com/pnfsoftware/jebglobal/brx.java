package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class brx {
   private static final ILogger pC = GlobalLog.getLogger(brx.class);
   private IDMethodContext A;

   public brx(IDMethodContext var1) {
      this.A = var1;
   }

   public void pC() {
      bzh var1 = new bzh();
      int var2 = var1.perform(this.A);
      if (var2 > 0) {
         IDMasterOptimizer var3 = this.A.getGlobalContext().createMasterOptimizer(this.A);
         var3.setSafeMode(true);
         var3.perform();
      }

      bpl.pC(this.A);
   }
}
