package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX64;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class j extends Ls {
   private static final ILogger RF = GlobalLog.getLogger(j.class);

   public j(oH var1) {
      super(var1, 64);
      this.gP = new RegisterLayoutBridge(var1.Hk(), RegisterBankX64.getInstance());
   }

   public ix RF(kW var1) {
      try {
         return new ix(var1, this.gP);
      } catch (IOException var3) {
         RF.catching(var3);
         return null;
      }
   }
}
