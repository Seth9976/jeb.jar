package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import java.util.HashMap;

public class bvw {
   IDMethodContext q;

   public bvw(IDMethodContext var1) {
      this.q = var1;
   }

   public int q() {
      return this.q(1);
   }

   public int q(int var1) {
      CFG var2 = this.q.getCfg();
      int var3 = 0;
      HashMap var4 = new HashMap();
      int var5 = 0;

      for (IDInstruction var7 : var2.instructions()) {
         if (var7.getSize() != var1) {
            var3++;
         }

         var4.put((int)var7.getOffset(), var5);
         var5 += var1;
      }

      if (var3 == 0) {
         return 0;
      } else {
         var5 = 0;

         for (BasicBlock var12 : var2) {
            for (IDInstruction var9 : var12) {
               var9.setSize(var1);
               var9.setOffset(var5);
               if (var9.getBreakingFlow().isBroken()) {
                  var9.updateTargets(var4);
               }

               var5 += var1;
            }
         }

         this.q.getExceptionData().updateTargets(var4);
         var2.invalidateDataFlowAnalysis();
         return var3;
      }
   }
}
