package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class c extends az {
   private static final ILogger za = GlobalLog.getLogger(c.class);

   public c(oH var1) {
      super(var1, 32);
      this.gP = new RegisterLayoutBridge(var1.Hk(), RegisterBankArm.getInstance());
   }

   @Override
   public long xK(long var1) {
      return var1 & 4294967294L;
   }

   public Kc RF(kW var1) {
      try {
         return new Kc(var1, this.gP);
      } catch (IOException var3) {
         za.catching(var3);
         return null;
      }
   }
}
