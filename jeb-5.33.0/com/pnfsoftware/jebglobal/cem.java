package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.Collections;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.function.Function;

public class cem {
   private static final ILogger A = GlobalLog.getLogger(cem.class);
   SegmentMap pC = new SegmentMap();

   static int pC(String var0) {
      if (var0.length() != 4) {
         throw new IllegalArgumentException("The mode string must have 4 characters");
      } else {
         int var1 = 0;
         var1 |= pC(var1, var0.charAt(0), 'r', 1, '-');
         var1 |= pC(var1, var0.charAt(1), 'w', 2, '-');
         var1 |= pC(var1, var0.charAt(2), 'x', 4, '-');
         return var1 | pC(var1, var0.charAt(3), (char)115, 8, (char)112);
      }
   }

   private static int pC(int var0, char var1, char var2, int var3, char var4) {
      if (var1 == var2) {
         return var3;
      } else if (var1 == var4) {
         return 0;
      } else {
         throw new RuntimeException("Unkown character in mode: " + var1);
      }
   }

   static String pC(int var0) {
      StringBuilder var1 = new StringBuilder();
      pC(var1, var0, 1, 'r', '-');
      pC(var1, var0, 2, 'w', '-');
      pC(var1, var0, 4, 'x', '-');
      pC(var1, var0, 8, 's', 'p');
      return var1.toString();
   }

   private static void pC(StringBuilder var0, int var1, int var2, char var3, char var4) {
      if ((var1 & var2) != 0) {
         var0.append(var3);
      } else {
         var0.append(var4);
      }
   }

   public static cem A(String var0) {
      cem var1 = new cem();

      for (String var5 : Strings.splitLines(var0)) {
         if (!var5.isEmpty() && !var5.startsWith("#")) {
            int var6 = 0;
            int var7 = var5.indexOf(45, var6);
            long var8 = Long.parseLong(var5.substring(var6, var7).trim(), 16);
            var6 = var7 + 1;
            var7 = var5.indexOf(32, var6);
            long var10 = Long.parseLong(var5.substring(var6, var7).trim(), 16);
            var6 = var7 + 1;
            var7 = var5.indexOf(32, var6);
            String var12 = var5.substring(var6, var7).trim();
            Assert.a(var12.length() == 4);
            int var13 = pC(var12);
            var6 = var7 + 1;
            var7 = var5.indexOf(32, var6);
            long var14 = Long.parseLong(var5.substring(var6, var7).trim(), 16);
            var6 = var7 + 1;
            var7 = var5.indexOf(32, var6);
            String var16 = var5.substring(var6, var7).trim();
            String[] var17 = var16.split(":");
            int var18 = Integer.parseInt(var17[0], 16);
            int var19 = Integer.parseInt(var17[1], 16);
            var6 = var7 + 1;
            var7 = var5.indexOf(32, var6);
            long var20 = Long.parseLong(var5.substring(var6, var7).trim());
            var6 = var7 + 1;
            String var22 = var5.substring(var6).trim();
            var1.pC.add(new cem.Av(var8, var10, var13, var14, var18, var19, var20, var22));
         }
      }

      return var1;
   }

   public void pC(long var1, long var3, int var5, long var6, String var8) {
      cem.Av var9 = new cem.Av(var1, var3, var5, var6, 0, 0, 0L, var8);
      this.pC.add(var9);
   }

   public cem.Av pC(long var1) {
      return (cem.Av)this.pC.getSegmentContaining(var1);
   }

   public SortedMap pC() {
      return Collections.unmodifiableSortedMap(this.pC);
   }

   public void pC(Function var1) {
      Iterator var2 = this.pC.keySet().iterator();

      while (var2.hasNext()) {
         long var3 = (Long)var2.next();
         Boolean var5 = (Boolean)var1.apply((cem.Av)this.pC.get(var3));
         if (Boolean.FALSE.equals(var5)) {
            var2.remove();
         }
      }
   }

   public String A() {
      StringBuilder var1 = new StringBuilder();

      for (cem.Av var3 : this.pC.values()) {
         Strings.ff(var1, "%s\n", var3);
      }

      return var1.toString();
   }

   @SerDisabled
   public static class Av extends LongSegment {
      long pC;
      long A;
      int kS;
      long wS;
      int UT;
      int E;
      long sY;
      String ys;

      public Av(long var1, long var3, int var5, long var6, int var8, int var9, long var10, String var12) {
         super(var1, var3 - var1);
         this.pC = var1;
         this.A = var3;
         this.kS = var5;
         this.wS = var6;
         this.UT = var8;
         this.E = var9;
         this.sY = var10;
         this.ys = var12;
      }

      public String pC() {
         return this.ys;
      }

      @Override
      public String toString() {
         return Strings.ff("%x-%x %s %08x %02x:%02x %d %s", this.pC, this.A, cem.pC(this.kS), this.wS, this.UT, this.E, this.sY, this.ys);
      }
   }
}
