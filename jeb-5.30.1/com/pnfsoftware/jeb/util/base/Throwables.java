package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Formatter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Throwables {
   public static String formatStacktraceAlt(Throwable var0) {
      return new Throwables.ThrowableFormatter(true).format(var0);
   }

   public static String formatStacktrace(Throwable var0) {
      StringWriter var1 = new StringWriter();
      PrintWriter var2 = new PrintWriter(var1);
      var0.printStackTrace(var2);
      return var1.toString();
   }

   public static String formatStacktraceShort(Throwable var0) {
      StringBuilder var1 = new StringBuilder("EXCEPTION: ");

      while (true) {
         var1.append(var0.toString());
         if (var0.getStackTrace().length > 0) {
            var1.append(" in " + var0.getStackTrace()[0]);
         }

         var0 = var0.getCause();
         if (var0 == null) {
            return var1.toString();
         }

         var1.append(" / caused by: ");
      }
   }

   public static void rethrowUnchecked(Throwable var0) {
      if (var0 instanceof RuntimeException) {
         throw (RuntimeException)var0;
      } else {
         throw new RuntimeException(var0);
      }
   }

   public static Throwable getRootCause(Throwable var0) {
      Throwable var1;
      while ((var1 = var0.getCause()) != null) {
         var0 = var1;
      }

      return var0;
   }

   public static class ThrowableFormatter {
      private boolean escapeTokens;
      private StringBuilder sb;
      private int depth;

      public ThrowableFormatter(boolean var1) {
         this.escapeTokens = var1;
      }

      public String format(Throwable var1) {
         this.sb = new StringBuilder();
         this.depth = 0;
         this.formatRecurse(var1);
         return this.sb.toString();
      }

      private void formatRecurse(Throwable var1) {
         if (this.depth > 50) {
            this.sb.append("[FORMATTING STOPPED, STACKTRACE IS TOO LARGE]");
         } else {
            this.depth++;
            this.app(var1.getClass().getName());
            this.sb.append(":");
            String var2 = var1.getMessage();
            if (var2 != null) {
               this.sb.append(" ");
               this.app(var2);
            }

            StackTraceElement[] var3 = var1.getStackTrace();

            for (int var4 = 0; var4 < var3.length; var4++) {
               this.sb.append("\n    at ");
               StackTraceElement var5 = var3[var4];
               this.app(var5.getClassName());
               this.sb.append(".");
               this.app(var5.getMethodName());
               this.sb.append("(");
               this.app(var5.getFileName());
               this.sb.append(":");
               this.sb.append(var5.getLineNumber());
               this.sb.append(")");
            }

            Throwable var6 = var1.getCause();
            if (var6 != null) {
               this.sb.append("\nCaused by: ");
               this.formatRecurse(var6);
            }

            this.depth--;
         }
      }

      private void app(String var1) {
         if (this.escapeTokens) {
            var1 = Formatter.escapeString(var1, false);
         }

         this.sb.append(var1);
      }
   }
}
