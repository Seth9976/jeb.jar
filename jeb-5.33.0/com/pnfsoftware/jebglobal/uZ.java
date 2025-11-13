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

class uZ implements Runnable {
   uZ(C var1) {
      this.pC = var1;
   }

   @Override
   public void run() {
      if (this.pC.wS != null) {
         this.pC.wS.reload(this.pC);
      }

      if (this.pC.ld()) {
         ace var1 = (ace)DecompilerHelper.getDecompiler(this.pC, true);
         if (var1 != null) {
            IGlobalAnalyzer var2 = var1.kS();
            if (var2 != null) {
               var2.perform();
            }
         }
      }

      if (this.pC.ys()) {
         cgp var6 = cgq.pC(this.pC);
         if (var6 != null) {
            GlobalLog.status(S.L("RTTI memory scanning started..."));
            if (var6.pC(true, true, true)) {
               this.pC.logInfo(true, var6.kS());
               if (var6.wS() != 0) {
                  JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("RTTI memory scanner error"));
               }

               if (this.pC.getCodeObjectContainer() != null) {
                  cgn var9 = var6.pC();
                  if (var9 != null && !var9.pC()) {
                     IUnit var3 = this.pC
                        .getUnitProcessor()
                        .process("run-time type information", new BytesInput(Strings.encodeUTF8(var6.A())), this.pC.getCodeObjectContainer());
                     if (var3 != null) {
                        this.pC.getCodeObjectContainer().addChild(var3);
                     }
                  }
               }
            } else {
               this.pC.logTrace(S.L("No RTTI information recovered"));
            }

            GlobalLog.status(S.L("RTTI memory scanning completed"));
         }
      }

      try (ACLock var7 = this.pC.ah.a()) {
         this.pC.pC.vP().pC(this.pC.getVirtualImageBase(), this.pC.getImageSize());
         this.pC.ah();
      }

      if (this.pC.ld instanceof AbstractProcessor) {
         ((AbstractProcessor)this.pC.ld).clearInstructionCache();
      }

      GlobalLog.status(S.L("Initial analysis completed"));
      int var8 = this.pC.oT.ys();
      String var10;
      if (var8 <= 1) {
         var10 = S.L("Initial analysis created %d routine");
      } else {
         var10 = S.L("Initial analysis created %d routines");
      }

      this.pC.logInfo(true, var10, var8);
      this.pC.notifyListeners(new JebEvent(J.CodeAnalysisCompleted));
   }
}
