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

public class cjx {
   private static final ILogger gO = GlobalLog.getLogger(cjx.class);
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 7;
   SegmentMap oW = new SegmentMap();

   static int q(String var0) {
      if (var0.length() != 4) {
         throw new IllegalArgumentException("The mode string must have 4 characters");
      } else {
         int var1 = 0;
         var1 |= q(var1, var0.charAt(0), 'r', 1, '-');
         var1 |= q(var1, var0.charAt(1), 'w', 2, '-');
         var1 |= q(var1, var0.charAt(2), 'x', 4, '-');
         return var1 | q(var1, var0.charAt(3), (char)115, 8, (char)112);
      }
   }

   private static int q(int var0, char var1, char var2, int var3, char var4) {
      if (var1 == var2) {
         return var3;
      } else if (var1 == var4) {
         return 0;
      } else {
         throw new RuntimeException("Unkown character in mode: " + var1);
      }
   }

   static String q(int var0) {
      StringBuilder var1 = new StringBuilder();
      q(var1, var0, 1, 'r', '-');
      q(var1, var0, 2, 'w', '-');
      q(var1, var0, 4, 'x', '-');
      q(var1, var0, 8, 's', 'p');
      return var1.toString();
   }

   private static void q(StringBuilder var0, int var1, int var2, char var3, char var4) {
      if ((var1 & var2) != 0) {
         var0.append(var3);
      } else {
         var0.append(var4);
      }
   }

   public static cjx RF(String var0) {
      cjx var1 = new cjx();

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
            int var13 = q(var12);
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
            var1.oW.add(new cjx.eo(var8, var10, var13, var14, var18, var19, var20, var22));
         }
      }

      return var1;
   }

   public void q() {
      this.oW.clear();
   }

   public int RF() {
      return this.oW.size();
   }

   public void q(long var1, long var3, int var5, long var6, String var8) {
      cjx.eo var9 = new cjx.eo(var1, var3, var5, var6, 0, 0, 0L, var8);
      this.oW.add(var9);
   }

   public cjx.eo q(long var1) {
      return (cjx.eo)this.oW.get(var1);
   }

   public cjx.eo RF(long var1) {
      return (cjx.eo)this.oW.getSegmentContaining(var1);
   }

   public SortedMap xK() {
      return Collections.unmodifiableSortedMap(this.oW);
   }

   public cjx.eo xK(long var1) {
      return (cjx.eo)this.oW.remove(var1);
   }

   public void q(Function var1) {
      Iterator var2 = this.oW.keySet().iterator();

      while (var2.hasNext()) {
         long var3 = (Long)var2.next();
         Boolean var5 = (Boolean)var1.apply((cjx.eo)this.oW.get(var3));
         if (Boolean.FALSE.equals(var5)) {
            var2.remove();
         }
      }
   }

   public String Dw() {
      StringBuilder var1 = new StringBuilder();

      for (cjx.eo var3 : this.oW.values()) {
         Strings.ff(var1, "%s\n", var3);
      }

      return var1.toString();
   }

   @SerDisabled
   public static class eo extends LongSegment {
      long q;
      long RF;
      int xK;
      long Dw;
      int Uv;
      int oW;
      long gO;
      String nf;

      public eo(long var1, long var3, int var5, long var6, int var8, int var9, long var10, String var12) {
         super(var1, var3 - var1);
         this.q = var1;
         this.RF = var3;
         this.xK = var5;
         this.Dw = var6;
         this.Uv = var8;
         this.oW = var9;
         this.gO = var10;
         this.nf = var12;
      }

      public int q() {
         return this.xK;
      }

      public long RF() {
         return this.Dw;
      }

      public int xK() {
         return this.Uv;
      }

      public int Dw() {
         return this.oW;
      }

      public long Uv() {
         return this.gO;
      }

      public String oW() {
         return this.nf;
      }

      @Override
      public String toString() {
         return Strings.ff("%x-%x %s %08x %02x:%02x %d %s", this.q, this.RF, cjx.q(this.xK), this.Dw, this.Uv, this.oW, this.gO, this.nf);
      }
   }
}
