package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Map;

public class Stacktraces {
   public static String getCallerString(String... var0) {
      return getCallerString(null, var0);
   }

   public static String getCallerString(StackTraceElement[] var0, String... var1) {
      if (var1.length == 0) {
         return null;
      } else {
         if (var0 == null) {
            var0 = Thread.currentThread().getStackTrace();
         }

         int var2 = 0;

         for (boolean var3 = false; var2 < var0.length; var2++) {
            String var4 = var0[var2].getClassName();
            if (Strings.startsWith(var4, var1)) {
               var3 = true;
            } else if (var3) {
               break;
            }
         }

         if (var2 >= var0.length) {
            return null;
         } else {
            StackTraceElement var10 = var0[var2];
            String var5 = var10.getClassName();
            int var6 = var5.lastIndexOf(46);
            String var7 = var6 < 0 ? var5 : var5.substring(var6 + 1);
            String var8 = var10.getMethodName();
            int var9 = var10.getLineNumber();
            return Strings.ff("%s.%s:%d", var7, var8, var9);
         }
      }
   }

   public static String formatCallerOriginString() {
      return formatCallerOriginString(null);
   }

   public static String formatCallerOriginString(StackTraceElement[] var0) {
      return formatCallerOriginString(var0, 0);
   }

   public static String formatCallerOriginString(StackTraceElement[] var0, int var1) {
      if (var0 == null) {
         var0 = Thread.currentThread().getStackTrace();
      }

      StringBuilder var2 = new StringBuilder();

      for (int var3 = var1; var3 < var0.length; var3++) {
         StackTraceElement var4 = var0[var3];
         String var5 = var4.getClassName();
         int var6 = var5.lastIndexOf(46);
         String var7 = var6 < 0 ? var5 : var5.substring(var6 + 1);
         String var8 = var4.getMethodName();
         int var9 = var4.getLineNumber();
         Strings.ff(var2, "%s.%s:%d < ", var7, var8, var9);
      }

      return var2.toString();
   }

   public static String formatThreadStacktraces() {
      return formatThreadStacktraces(false, false);
   }

   public static String formatThreadStacktraces(boolean var0, boolean var1) {
      Map var2 = Thread.getAllStackTraces();
      ArrayList var3 = new ArrayList(var2.keySet());
      var3.sort((var0x, var1x) -> Long.compare(var0x.getId(), var1x.getId()));
      Thread var4 = Thread.currentThread();
      int var5 = 0;
      StringBuilder var6 = new StringBuilder();

      for (Thread var8 : var3) {
         if (var8 != var4 || !var0) {
            if (!var8.isAlive()) {
               if (!var1) {
                  continue;
               }

               var6.append("Dead ");
            }

            Strings.ff(var6, "Thread '%s' (%d)", var8.getName(), var8.getId());
            if (var8.isInterrupted()) {
               var6.append(" [INTERRUPTED]");
            }

            formatStacktrace(var6, (StackTraceElement[])var2.get(var8));
            var6.append('\n');
            var5++;
         }
      }

      var6.insert(0, var5 + " threads:\n");
      return var6.toString();
   }

   private static void formatStacktrace(StringBuilder var0, StackTraceElement[] var1) {
      if (var1 != null && var1.length != 0) {
         for (int var2 = 0; var2 < var1.length; var2++) {
            var0.append("\n    ");
            StackTraceElement var3 = var1[var2];
            var0.append(var3.getClassName());
            var0.append(".");
            var0.append(var3.getMethodName());
            var0.append("(");
            var0.append(var3.getFileName());
            var0.append(":");
            var0.append(var3.getLineNumber());
            var0.append(")");
         }
      }
   }
}
