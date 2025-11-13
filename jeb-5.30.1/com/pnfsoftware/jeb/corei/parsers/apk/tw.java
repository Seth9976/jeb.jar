package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.N;

@Ser
public class tw extends AbstractAnalyzerExtension {
   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      if (var1 == 0 && this.getUnit().getPropertyManager().getBoolean("ApplyAndroidNativePrototypes")) {
         try {
            N var2 = new N(this.getUnit());
            if (var2.q()) {
               var2.RF();
               return ChainedOperationResult.TRUE_CONTINUE;
            }
         } catch (Exception var3) {
            JebCoreService.notifySilentExceptionToClient(var3, this.getUnit());
         }
      }

      return ChainedOperationResult.FALSE_CONTINUE;
   }
}
