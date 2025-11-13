package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashSet;
import java.util.Set;

public class bya extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(bya.class);
   private int A;
   private Set kS = new HashSet();
   private static String wS = "axProtHelperClasses";

   public bya() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bpl.wS(this);
   }

   @Override
   public int perform() {
      Set var1 = pC(this.g, false);
      if (var1 != null && !var1.isEmpty()) {
         this.A = 0;

         for (IDInstruction var3 : this.cfg.instructions()) {
            var3.visitInstruction(new byb(this, var1), true);
         }

         if (this.A > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return this.A;
      } else {
         return 0;
      }
   }

   private IDImm pC(IDCallInfo var1) {
      IDState var2 = this.g.getEmulator();
      if (!var2.canRun()) {
         return null;
      } else {
         var2.pushContext("executionContext");
         Watchdog var3 = var2.setWatchdog(this.ctx.getWatchdog());
         boolean var4 = var2.setExceptionHandlingEnabled(false);

         Object var6;
         try {
            return var1.evaluate(var2);
         } catch (Exception var10) {
            if (var10 instanceof DexDecEvaluationException) {
               pC.catchingSilent(var10);
            }

            this.kS.add(var1.getMethodSignature());
            var6 = null;
         } finally {
            var2.deleteTopContext();
            var2.setExceptionHandlingEnabled(var4);
            var2.setWatchdog(var3);
         }

         return (IDImm)var6;
      }
   }

   public static Set pC(IDGlobalContext var0, boolean var1) {
      return var1 ? (Set)var0.getData(wS, var0x -> new ConcurrentHashSet()) : (Set)var0.getData(wS);
   }
}
