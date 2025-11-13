package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import java.util.Collections;
import java.util.SortedMap;
import java.util.Map.Entry;

public class awb {
   SortedMap q;

   public SortedMap q() {
      return Collections.unmodifiableSortedMap(this.q);
   }

   public String RF() {
      StringBuilder var1 = new StringBuilder();
      long var2 = 0L;

      for (Entry var5 : this.q.entrySet()) {
         var2 += ((awc)var5.getValue()).q;
      }

      for (Entry var10 : this.q.entrySet()) {
         avy var6 = avy.q((Integer)var10.getKey());
         awc var7 = (awc)var10.getValue();
         int var8 = var2 == 0L ? 0 : (int)(var7.q * 100L / var2);
         Strings.ff(var1, "- %s: core=%s full=%s (%d%%)\n", var6, var7.RF, var7.q, var8);
      }

      Strings.ff(var1, "Total: %s", TimeFormatter.formatTimestampDelta(var2));
      return var1.toString();
   }
}
