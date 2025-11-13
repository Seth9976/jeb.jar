package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.acj;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class OptimizersPerformanceCounters {
   public SortedMap map = new TreeMap();

   public SortedMap getCounters() {
      return Collections.unmodifiableSortedMap(this.map);
   }

   public String format() {
      StringBuilder var1 = new StringBuilder();
      long var2 = 0L;

      for (OptimizersPerformanceCounters.E var5 : this.map.values()) {
         var2 += var5.exectime;
      }

      for (Entry var10 : this.map.entrySet()) {
         String var6 = (String)var10.getKey();
         OptimizersPerformanceCounters.E var7 = (OptimizersPerformanceCounters.E)var10.getValue();
         int var8 = var2 == 0L ? 0 : (int)(var7.exectime * 100L / var2);
         Strings.ff(var1, "- \"%s\": rc=%d oc=%d t=%s (%d%%)\n", var6, var7.runcnt, var7.optcnt, acj.pC(var7.exectime), var8);
      }

      Strings.ff(var1, "Total: %s", acj.pC(var2));
      return var1.toString();
   }

   public static class E {
      public long exectime;
      public int runcnt;
      public int optcnt;
   }
}
