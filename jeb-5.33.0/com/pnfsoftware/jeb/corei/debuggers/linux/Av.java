package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.ZB;
import com.pnfsoftware.jebglobal.rH;

class Av implements ZB {
   Av(LinuxDbgClient var1) {
      this.pC = var1;
   }

   @Override
   public void pC(rH var1, int var2, int var3, int var4, long var5) {
      LinuxDbgClient.pC.info("[STOP] type=%s signal=%d tid=%d @ %Xh", var1, var4, var3, var5);
      if (var1 == rH.kS) {
         LinuxDbgClient.pC.info("Done.");
         System.exit(0);
      }
   }

   @Override
   public void pC(byte[] var1) {
      LinuxDbgClient.pC.info("[OUTPUT] %s", Strings.decodeASCII(var1));
   }
}
