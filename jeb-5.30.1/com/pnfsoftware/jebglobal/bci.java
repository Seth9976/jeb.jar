package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;

public class bci {
   private static final ILogger RF = GlobalLog.getLogger(bci.class);
   bcj q;

   public bci(bcj var1) {
      if (!var1.q()) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   public void q(bck var1) {
      ArrayList var2 = new ArrayList();

      for (bch var4 : this.q.Uv) {
         if (var4.q instanceof bbs var6) {
            axp var7 = new axp(this.q.Dw, var4.RF, var6, null);
            if (var6.getParameterNames() != null) {
               var7.setParameterNames(var6.getParameterNames());
            }

            var2.add(var7);
            Object[] var10000 = new Object[]{var7.getSignature(true)};
         } else {
            Object[] var8 = new Object[]{var4.RF, var4.q};
         }
      }

      var1.xK = var2;
   }
}
