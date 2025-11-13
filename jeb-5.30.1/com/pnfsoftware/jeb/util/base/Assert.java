package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;

public abstract class Assert {
   private static final ILogger logger = GlobalLog.getLogger(Assert.class);
   private static final String assertClassName = Assert.class.getName();

   private static RuntimeException adjustException(RuntimeException var0) {
      if (var0 == null) {
         throw new RuntimeException();
      } else {
         try {
            StackTraceElement[] var1 = var0.getStackTrace();
            int var2 = 0;

            while (var2 < var1.length && assertClassName.equals(var1[var2].getClassName())) {
               var2++;
            }

            if (var2 > 0) {
               var1 = (StackTraceElement[])Arrays.copyOfRange((Object[])var1, var2, var1.length);
               var0.setStackTrace(var1);
            }
         } catch (Exception var3) {
         }

         return var0;
      }
   }

   public static void a(boolean var0, String var1) {
      if (!var0) {
         String var2 = "FAIL - unexpected condition" + (var1 == null ? "" : ": " + var1);
         RuntimeException var3 = new RuntimeException(var2);
         throw adjustException(var3);
      }
   }

   public static void a(boolean var0, String var1, Object... var2) {
      if (!var0) {
         String var3 = "FAIL - unexpected condition" + (var1 == null ? "" : ": " + Strings.f(var1, var2));
         RuntimeException var4 = new RuntimeException(var3);
         throw adjustException(var4);
      }
   }

   public static void a(boolean var0) {
      a(var0, null);
   }

   public static void fail(String var0) {
      String var1 = "FAIL" + (var0 == null ? "" : ": " + var0);
      RuntimeException var2 = new RuntimeException(var1);
      throw adjustException(var2);
   }

   public static void fail() {
      fail(null);
   }

   public static void debugFail(String var0) {
      String var1 = "[DEBUG] FAIL" + (var0 == null ? "" : ": " + var0);
      logger.error(var1);
   }

   public static void debugFail() {
      debugFail((String)null);
   }

   public static void debugFail(Throwable var0) {
      logger.catchingSilent(var0);
   }
}
