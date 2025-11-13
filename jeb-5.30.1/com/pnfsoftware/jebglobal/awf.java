package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import java.util.List;

public class awf {
   public static String q(awd var0) {
      List var1 = var0.RF();
      long var2 = 0L;

      for (awd.eo var5 : var1) {
         var2 += var5.RF.q;
      }

      StringBuilder var8 = new StringBuilder();

      for (awd.eo var6 : var1) {
         int var7 = var2 == 0L ? 0 : (int)(var6.RF.q * 100L / var2);
         Strings.ff(var8, "- %s: core=%s full=%s (%d%%)\n", var6.q.RF(), var6.RF.RF, var6.RF.q, var7);
      }

      Strings.ff(var8, "Total: %s", TimeFormatter.formatTimestampDelta(var2));
      return var8.toString();
   }
}
