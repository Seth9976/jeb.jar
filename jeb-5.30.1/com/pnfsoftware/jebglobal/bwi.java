package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class bwi {
   private static final ILogger q = GlobalLog.getLogger(bwi.class);
   private IDMethodContext RF;

   public bwi(IDMethodContext var1) {
      this.RF = var1;
   }

   public void q() {
      cdx var1 = new cdx();
      int var2 = var1.perform(this.RF);
      if (var2 > 0) {
         IDMasterOptimizer var3 = this.RF.getGlobalContext().createMasterOptimizer(this.RF);
         var3.setSafeMode(true);
         var3.perform();
      }

      bto.q(this.RF);
   }
}
