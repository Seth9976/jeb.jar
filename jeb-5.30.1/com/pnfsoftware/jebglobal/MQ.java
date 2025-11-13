package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm64;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public class MQ extends az {
   private static final ILogger za = GlobalLog.getLogger(MQ.class);

   public MQ(oH var1) {
      super(var1, 64);
      this.gP = new RegisterLayoutBridge(var1.Hk(), RegisterBankArm64.getInstance());
   }

   @Override
   public long xK(long var1) {
      return var1 & -2L;
   }

   public gE RF(kW var1) {
      try {
         return new gE(var1, this.gP);
      } catch (IOException var3) {
         za.catching(var3);
         return null;
      }
   }
}
