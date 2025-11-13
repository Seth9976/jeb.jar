package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.Map;

public class Env {
   private static final ILogger logger = GlobalLog.getLogger(Env.class);
   private static OSType ostype = OSType.determine();
   private static boolean useCacheForQuickRetrieval = false;
   private static Map envmap = new HashMap();

   private static boolean record(String var0, String var1, int var2) {
      String var3 = (String)envmap.put(var0, var1);
      if ((var3 != null || var1 != null) && (var3 == null || !var3.equals(var1))) {
         if (var2 >= 0) {
            String var4 = "Environment variable";
            if (var2 == 0) {
               var4 = "System environment variable";
            } else if (var2 == 1) {
               var4 = "Shell environment variable";
            }

            if (var1 == null) {
               logger.debug("%s not found: %s", var4, var0);
            } else {
               logger.debug("%s found: %s=%s", var4, var0, var1);
            }
         }

         return var1 != null;
      } else {
         return var1 != null;
      }
   }

   public static String get(String var0) {
      String var1 = System.getenv(var0);
      if (record(var0, var1, 0)) {
         return var1;
      } else {
         if (!ostype.isWindows()) {
            if (useCacheForQuickRetrieval && envmap.containsKey(var0)) {
               return (String)envmap.get(var0);
            }

            var1 = BashHelper.env(var0);
            if (record(var0, var1, 1)) {
               return var1;
            }
         }

         return null;
      }
   }

   public static String getsafe(String var0) {
      return Strings.safe(get(var0));
   }
}
