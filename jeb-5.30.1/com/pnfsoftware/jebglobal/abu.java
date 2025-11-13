package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.concurrent.ActiveTask;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;
import java.util.SortedMap;
import java.util.Map.Entry;

@Ser
public class abu extends ActiveTask {
   private static final ILogger RF = GlobalLog.getLogger(abu.class);
   @SerId(1)
   abg q;

   public abu(abg var1) {
      super("jeb-analyzer");
      this.q = var1;
   }

   @Override
   public void runi() {
      GlobalLog.status(S.L("Analysis started..."));
      long var1 = System.currentTimeMillis();

      try (ACLock var3 = this.q.q().a()) {
         this.q.getCodeAnalyzer().analyze();
         this.q.oW();
      } finally {
         String var7 = TimeFormatter.formatTimestampDelta(System.currentTimeMillis() - var1);
         UnitUtil.logInfo(this.q, null, true, RF, S.L("Analysis completed (%s)"), var7);
         GlobalLog.status(S.L("Analysis completed (%s)"), var7);
         if (this.q.getHighLevelEntryPointAddress() != -1L) {
            UnitUtil.logInfo(
               this.q,
               this.q.getSymbolicStringAddress(this.q.getHighLevelEntryPointAddress()),
               true,
               RF,
               S.L("High-level entry-point found at %xh"),
               this.q.getHighLevelEntryPointAddress()
            );
         }

         this.q.RF.xK().RF();
      }
   }

   @Override
   protected void onException(Exception var1) {
      super.onException(var1);
      JebCoreService.notifySilentExceptionToClient(var1);
   }

   private MemoryRanges q() {
      MemoryRanges var1 = new MemoryRanges();
      SortedMap var2 = this.q.getCodeModel().getView();
      boolean var3 = false;
      long var4 = 0L;

      for (Entry var7 : var2.entrySet()) {
         long var8 = (Long)var7.getKey();
         INativeContinuousItem var10 = (INativeContinuousItem)var7.getValue();
         if (var10 instanceof INativeInstructionItem) {
            List var11 = this.q.getCodeModel().getContainedRoutineAddresses(var8);
            if (var11 != null && var11.size() != 0) {
               if (var3) {
                  var3 = false;
                  var1.add(var4, var8);
               }
            } else if (!var3) {
               var4 = var8;
               var3 = true;
            }
         }
      }

      return var1;
   }
}
