package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX86;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class ml extends Ls {
   private static final ILogger RF = GlobalLog.getLogger(ml.class);

   public ml(oH var1) {
      super(var1, 32);
      this.gP = new RegisterLayoutBridge(var1.Hk(), RegisterBankX86.getInstance());
   }

   public KQ RF(kW var1) {
      try {
         return new KQ(var1, this.gP);
      } catch (IOException var3) {
         RF.catching(var3);
         return null;
      }
   }
}
