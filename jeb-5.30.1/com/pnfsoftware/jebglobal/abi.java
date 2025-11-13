package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IGlobalAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;

class abi implements Runnable {
   abi(abg var1) {
      this.q = var1;
   }

   @Override
   public void run() {
      if (this.q.Uv != null) {
         this.q.Uv.reload(this.q);
      }

      if (this.q.gP()) {
         adw var1 = (adw)DecompilerHelper.getDecompiler(this.q, true);
         if (var1 != null) {
            IGlobalAnalyzer var2 = var1.Dw();
            if (var2 != null) {
               var2.perform();
            }
         }
      }

      if (this.q.nf()) {
         cnt var6 = cnu.q(this.q);
         if (var6 != null) {
            GlobalLog.status(S.L("RTTI memory scanning started..."));
            if (var6.q(true, true, true)) {
               this.q.logInfo(true, var6.Dw());
               if (var6.Uv() != 0) {
                  JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("RTTI memory scanner error"));
               }

               if (this.q.getCodeObjectContainer() != null) {
                  cnr var9 = var6.q();
                  if (var9 != null && !var9.q()) {
                     IUnit var3 = this.q
                        .getUnitProcessor()
                        .process("run-time type information", new BytesInput(Strings.encodeUTF8(var6.RF())), this.q.getCodeObjectContainer());
                     if (var3 != null) {
                        this.q.getCodeObjectContainer().addChild(var3);
                     }
                  }
               }
            } else {
               this.q.logTrace(S.L("No RTTI information recovered"));
            }

            GlobalLog.status(S.L("RTTI memory scanning completed"));
         }
      }

      try (ACLock var7 = this.q.oQ.a()) {
         this.q.RF.Hk().q();
         this.q.PV();
         this.q.oQ();
      }

      if (this.q.lm instanceof AbstractProcessor) {
         ((AbstractProcessor)this.q.lm).clearInstructionCache();
      }

      GlobalLog.status(S.L("Initial analysis completed"));
      int var8 = this.q.JY.za();
      String var10;
      if (var8 <= 1) {
         var10 = S.L("Initial analysis created %d routine");
      } else {
         var10 = S.L("Initial analysis created %d routines");
      }

      this.q.logInfo(true, var10, var8);
      this.q.notifyListeners(new JebEvent(J.CodeAnalysisCompleted));
   }
}
