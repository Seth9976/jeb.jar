package com.pnfsoftware.jeb.corei.parsers.apk;

import java.util.HashMap;
import java.util.Map;

public class RC {
   private static Map pC = new HashMap();
   private static Map A = new HashMap();

   public static zp pC(String var0) {
      zp var1 = (zp)pC.get(var0);
      if (var1 == null) {
         for (String var3 : pC.keySet()) {
            if (var3.contains(var0)) {
               if (var1 != null) {
                  return null;
               }

               var1 = (zp)pC.get(var3);
            }
         }
      }

      return var1;
   }

   static {
      for (zp var3 : zp.values()) {
         pC.put(var3.pC(), var3);
      }

      for (KD var7 : KD.values()) {
         A.put(var7.pC(), var7);
      }
   }
}
