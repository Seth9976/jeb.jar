package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX64;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class CM extends So {
   private static final ILogger A = GlobalLog.getLogger(CM.class);

   public CM(aI var1) {
      super(var1, 64);
      this.ld = new RegisterLayoutBridge(var1.ED(), RegisterBankX64.getInstance());
   }

   public EJ A(Tl var1) {
      try {
         return new EJ(var1, this.ld);
      } catch (IOException var3) {
         A.catching(var3);
         return null;
      }
   }
}
