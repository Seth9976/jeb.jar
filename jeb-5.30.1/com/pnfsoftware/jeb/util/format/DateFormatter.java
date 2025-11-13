package com.pnfsoftware.jeb.util.format;

import java.util.Calendar;
import java.util.TimeZone;

public class DateFormatter {
   public static String formatLocalDate(String var0) {
      Calendar var1 = Calendar.getInstance();
      var0 = var0.replace("YYYY", Strings.ff("%04d", var1.get(1)));
      var0 = var0.replace("MM", Strings.ff("%02d", 1 + var1.get(2)));
      var0 = var0.replace("DD", Strings.ff("%02d", var1.get(5)));
      var0 = var0.replace("YY", Strings.ff("%02d", var1.get(1) % 100));
      var0 = var0.replace("M", Strings.ff("%d", 1 + var1.get(2)));
      return var0.replace("D", Strings.ff("%d", var1.get(5)));
   }

   public static String currentDate() {
      Calendar var0 = Calendar.getInstance();
      return Strings.ff("%04d/%02d/%02d", var0.get(1), 1 + var0.get(2), var0.get(5));
   }

   public static String currentDateGMT() {
      Calendar var0 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
      return Strings.ff("%04d/%02d/%02d", var0.get(1), 1 + var0.get(2), var0.get(5));
   }
}
