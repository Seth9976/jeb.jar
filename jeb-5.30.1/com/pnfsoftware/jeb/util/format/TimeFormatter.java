package com.pnfsoftware.jeb.util.format;

import com.pnfsoftware.jeb.client.S;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeFormatter {
   public static String formatTimestamp(long var0) {
      return formatTimestamp(var0, "UTC");
   }

   public static String formatTimestamp(long var0, String var2) {
      Calendar var3 = Calendar.getInstance();
      var3.setTimeInMillis(var0);
      var3.setTimeZone(TimeZone.getTimeZone(var2));
      return Strings.ff("%04d/%02d/%02d-%02d:%02d:%02d", var3.get(1), 1 + var3.get(2), var3.get(5), var3.get(11), var3.get(12), var3.get(13));
   }

   public static String formatTimestampLocal(long var0) {
      DateFormat var2 = DateFormat.getDateTimeInstance(2, 2, S.getCurrentLocale());
      String var3 = var2.format(new Date(var0));
      String var4 = var2.getTimeZone().getDisplayName(false, 0);
      return var3 + " " + var4;
   }

   public static String formatTimestampDelta(long var0) {
      String var2 = "";
      if (var0 < 0L) {
         var2 = "-";
         var0 = -var0;
      }

      long var3 = var0 / 1000L;
      if (var3 >= 86400L) {
         long var5 = var3 / 86400L;
         var3 %= 86400L;
         var2 = var2 + Strings.ff("%dd ", var5);
      }

      if (var3 >= 3600L) {
         long var7 = var3 / 3600L;
         var3 %= 3600L;
         var2 = var2 + Strings.ff("%dh ", var7);
      }

      if (var3 >= 60L) {
         long var8 = var3 / 60L;
         var3 %= 60L;
         var2 = var2 + Strings.ff("%dm ", var8);
      }

      if (var3 != 0L || var2.isEmpty() || var2.equals("-")) {
         var2 = var2 + Strings.ff("%ds", var3);
      }

      return var2.trim();
   }

   public static String formatExecutionTime(long var0) {
      String var2 = "";
      if (var0 < 0L) {
         var2 = "-";
         var0 = -var0;
      }

      long var3 = var0;
      if (var0 >= 86400000L) {
         long var5 = var0 / 86400000L;
         var3 = var0 % 86400000L;
         var2 = var2 + Strings.ff("%dd", var5);
      }

      if (var3 >= 3600000L) {
         long var7 = var3 / 3600000L;
         var3 %= 3600000L;
         var2 = var2 + Strings.ff("%dh", var7);
      }

      if (var3 >= 60000L) {
         long var8 = var3 / 60000L;
         var3 %= 60000L;
         var2 = var2 + Strings.ff("%dm", var8);
      }

      if (var3 >= 1000L) {
         long var9 = var3 / 1000L;
         var3 %= 1000L;
         var2 = var2 + Strings.ff("%ds", var9);
      }

      if (var3 != 0L || var2.isEmpty() || var2.equals("-")) {
         var2 = var2 + Strings.ff("%dms", var3);
      }

      return var2.trim();
   }
}
