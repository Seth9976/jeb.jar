package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.HJ;
import com.pnfsoftware.jebglobal.af;

class eo implements HJ {
   eo(LinuxDbgClient var1) {
      this.q = var1;
   }

   @Override
   public void q(af var1, int var2, int var3, int var4, long var5) {
      LinuxDbgClient.RF.info("[STOP] type=%s signal=%d tid=%d @ %Xh", var1, var4, var3, var5);
      if (var1 == af.xK) {
         LinuxDbgClient.RF.info("Done.");
         System.exit(0);
      }
   }

   @Override
   public void q(byte[] var1) {
      LinuxDbgClient.RF.info("[OUTPUT] %s", Strings.decodeASCII(var1));
   }
}
