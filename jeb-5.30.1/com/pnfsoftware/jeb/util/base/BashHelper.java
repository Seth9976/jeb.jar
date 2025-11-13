package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BashHelper {
   private static final ILogger logger = GlobalLog.getLogger(BashHelper.class);
   private static final long bashWaitTimeSeconds = 5L;

   public static Map env() {
      HashMap var0 = new HashMap();
      String var1 = exec("env");
      if (var1 != null) {
         for (String var5 : Strings.splitLines(var1)) {
            var5 = Strings.trim(var5);
            if (!var5.isEmpty()) {
               String[] var6 = var5.split("=", 2);
               if (var6.length == 2) {
                  var0.put(var6[0], var6[1]);
               }
            }
         }
      }

      return var0;
   }

   public static String env(String var0) {
      if (var0 == null) {
         throw new NullPointerException();
      } else {
         String var1 = exec("echo $" + var0);
         if (var1 == null) {
            return null;
         } else {
            var1 = Strings.trim(var1);
            return var1.isEmpty() ? null : var1;
         }
      }
   }

   private static String exec(String var0) {
      try {
         ProcessBuilder var1 = new ProcessBuilder("bash", "-ic", var0);
         Process var2 = var1.start();
         var2.waitFor(5L, TimeUnit.SECONDS);
         byte[] var3 = IO.readInputStream(var2.getInputStream());
         return Strings.decodeLocal(var3);
      } catch (Exception var4) {
         logger.catching(var4);
         return null;
      }
   }
}
