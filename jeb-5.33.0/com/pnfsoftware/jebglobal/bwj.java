package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public abstract class bwj extends AbstractDOptimizer {
   protected abstract Collection pC();

   public bwj() {
      this.setPriority(-10.0);
   }

   @Override
   public int perform() {
      int var1 = 0;
      Map var2 = null;
      boolean var3 = false;

      for (bso.Av var5 : this.pC()) {
         bsq var6 = new bsq(var5, this.cfg, this.ctx);
         if (var2 == null) {
            var2 = pC(this.cfg);
         }

         var6.pC(var2);
         bsq.Av var7 = null;

         while (true) {
            Watchdog.verify(this.ctx.getWatchdog());
            var7 = var6.pC(var7);
            if (var7 == null) {
               break;
            }

            if (var6.pC(var7, false)) {
               if (!var3 && var6.pC().wS() != null && (var6.pC().wS().pC() & 256) != 0) {
                  var3 = true;
               }

               var2 = null;
               var1++;
            }
         }
      }

      if (var3) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private static Map pC(CFG var0) {
      IdentityHashMap var1 = new IdentityHashMap();

      for (IDInstruction var3 : var0.instructions()) {
         pC(var3, var1);
      }

      return var1;
   }

   private static int pC(IDExpression var0, Map var1) {
      ArrayList var3 = new ArrayList();
      var0.collectSubExpressions(var3);
      int var2;
      if (var3.isEmpty()) {
         var2 = 0;
      } else {
         int var4 = 0;

         for (IDExpression var6 : var3) {
            int var7 = pC(var6, var1);
            if (var7 > var4) {
               var4 = var7;
            }
         }

         var2 = 1 + var4;
      }

      var1.put(var0, var2);
      return var2;
   }
}
