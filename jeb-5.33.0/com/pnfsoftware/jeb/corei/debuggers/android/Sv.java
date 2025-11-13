package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.ZB;
import com.pnfsoftware.jebglobal.rH;
import com.pnfsoftware.jebglobal.yv;

class Sv implements ZB {
   Sv(AndroidDbgClient var1) {
      this.pC = var1;
   }

   @Override
   public void pC(rH var1, int var2, int var3, int var4, long var5) {
      AndroidDbgClient.pC.info("[STOP] type=%s signal=%d(%s) tid=%d @ %Xh", var1, var4, yv.pC(var4), var3, var5);
      if (var1 == rH.kS) {
         AndroidDbgClient.pC.info("Done.");
         System.exit(0);
      }
   }

   @Override
   public void pC(byte[] var1) {
      AndroidDbgClient.pC.info("[OUTPUT] %s", Strings.decodeASCII(var1));
   }
}
