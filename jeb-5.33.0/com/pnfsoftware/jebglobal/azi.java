package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;

public class azi {
   private static final ILogger A = GlobalLog.getLogger(azi.class);
   azj pC;

   public azi(azj var1) {
      if (!var1.pC()) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   public void pC(azk var1) {
      ArrayList var2 = new ArrayList();

      for (azh var4 : this.pC.wS) {
         if (var4.pC instanceof ays var6) {
            auu var7 = new auu(this.pC.kS, var4.A, var6, null);
            if (var6.getParameterNames() != null) {
               var7.setParameterNames(var6.getParameterNames());
            }

            var2.add(var7);
            Object[] var10000 = new Object[]{var7.getSignature(true)};
         } else {
            Object[] var8 = new Object[]{var4.A, var4.pC};
         }
      }

      var1.kS = var2;
   }
}
