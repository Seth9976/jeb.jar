package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;

public class bwf {
   private IDMethodContext q;

   public bwf(IDMethodContext var1) {
      this.q = var1;
   }

   public int q() {
      IDTryData var1 = this.q.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;
         CFG var3 = this.q.getCfg();

         for (BasicBlock var5 : var3) {
            if (var1.unprotectBlock((int)var5.getAddress())) {
               var3.deleteIrregularOutEdges(var5);
               var2++;
            }
         }

         if (var2 > 0) {
            var3.invalidateDataFlowAnalysis();
            DUtil.cleanGraph(this.q);
         }

         return var2;
      }
   }
}
