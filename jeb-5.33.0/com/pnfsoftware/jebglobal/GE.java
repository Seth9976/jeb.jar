package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX86;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class GE extends So {
   private static final ILogger A = GlobalLog.getLogger(GE.class);

   public GE(aI var1) {
      super(var1, 32);
      this.ld = new RegisterLayoutBridge(var1.ED(), RegisterBankX86.getInstance());
   }

   public li A(Tl var1) {
      try {
         return new li(var1, this.ld);
      } catch (IOException var3) {
         A.catching(var3);
         return null;
      }
   }
}
