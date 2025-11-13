package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.concurrent.ActiveTask;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class aac extends ActiveTask {
   private static final ILogger A = GlobalLog.getLogger(aac.class);
   @SerId(1)
   C pC;

   public aac(C var1) {
      super("jeb-analyzer");
      this.pC = var1;
   }

   @Override
   public void runi() {
      GlobalLog.status(S.L("Analysis started..."));
      long var1 = System.currentTimeMillis();

      try (ACLock var3 = this.pC.pC().a()) {
         this.pC.getCodeAnalyzer().analyze();
         this.pC.E();
      } finally {
         String var7 = TimeFormatter.formatTimestampDelta(System.currentTimeMillis() - var1);
         UnitUtil.logInfo(this.pC, null, true, A, S.L("Analysis completed (%s)"), var7);
         GlobalLog.status(S.L("Analysis completed (%s)"), var7);
         if (this.pC.getHighLevelEntryPointAddress() != -1L) {
            UnitUtil.logInfo(
               this.pC,
               this.pC.getSymbolicStringAddress(this.pC.getHighLevelEntryPointAddress()),
               true,
               A,
               S.L("High-level entry-point found at %xh"),
               this.pC.getHighLevelEntryPointAddress()
            );
         }

         this.pC.pC.kS().pC();
      }
   }

   @Override
   protected void onException(Exception var1) {
      super.onException(var1);
      JebCoreService.notifySilentExceptionToClient(var1);
   }
}
