package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm64;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class Is extends Pi {
   private static final ILogger gp = GlobalLog.getLogger(Is.class);

   public Is(aI var1) {
      super(var1, 64);
      this.ld = new RegisterLayoutBridge(var1.ED(), RegisterBankArm64.getInstance());
   }

   public au A(Tl var1) {
      try {
         return new au(var1, this.ld);
      } catch (IOException var3) {
         gp.catching(var3);
         return null;
      }
   }
}
