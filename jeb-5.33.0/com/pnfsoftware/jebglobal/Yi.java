package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.JebClientInformation;

public class Yi {
   public static boolean pC(JebClientInformation var0) {
      if (Licensing.allowAnyClient()) {
         return true;
      } else if (var0 == null) {
         return false;
      } else {
         String var1 = var0.getClientSignature();
         return var1 != null
            && var1.equals(
               ckx.pC(
                  new byte[]{
                     19, 33, 54, 89, 33, 6, 1, 28, 3, 65, 90, 6, 9, 13, 17, 115, 124, 113, 12, 111, 84, 86, 91, 86, 69, 65, 35, 82, 34, 15, 5, 0, 78, 21
                  },
                  2,
                  65
               )
            );
      }
   }
}
