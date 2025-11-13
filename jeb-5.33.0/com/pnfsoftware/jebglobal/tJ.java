package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class tJ extends Pi {
   private static final ILogger gp = GlobalLog.getLogger(tJ.class);

   public tJ(aI var1) {
      super(var1, 32);
      this.ld = new RegisterLayoutBridge(var1.ED(), RegisterBankArm.getInstance());
   }

   public yO A(Tl var1) {
      try {
         return new yO(var1, this.ld);
      } catch (IOException var3) {
         gp.catching(var3);
         return null;
      }
   }
}
