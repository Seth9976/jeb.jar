package com.pnfsoftware.jeb.util.primitives;

import com.pnfsoftware.jeb.util.base.Assert;
import java.util.Collection;
import java.util.Iterator;

public class Integers {
   public static String toUnsignedString(int var0) {
      return Longs.toUnsignedString(var0 & 4294967295L);
   }

   public static int safeInt(Integer var0) {
      return safeInt(var0, 0);
   }

   public static Integer safeInt(Integer var0, int var1) {
      return var0 == null ? var1 : var0;
   }

   public static Integer min(Collection var0) {
      Integer var1 = null;

      for (Integer var3 : var0) {
         if (var1 == null || var3.compareTo(var1) < 0) {
            var1 = var3;
         }
      }

      return var1;
   }

   public static Integer max(Collection var0) {
      Integer var1 = null;

      for (Integer var3 : var0) {
         if (var1 == null || var3.compareTo(var1) > 0) {
            var1 = var3;
         }
      }

      return var1;
   }

   public static String formatIntegerCollection(Collection var0, Integer var1, String var2, String var3) {
      return formatIntegerCollection(var0, var1, var2, var3, null);
   }

   public static String formatHexIntegerCollection(Collection var0) {
      return formatIntegerCollection(var0, 16, "0x", null);
   }

   public static String formatIntegerCollection(Collection var0, Integer var1, String var2, String var3, String var4) {
      if (var1 == null) {
         var1 = 10;
      }

      if (var2 == null) {
         var2 = "";
      }

      if (var3 == null) {
         var3 = "";
      }

      if (var4 == null) {
         var4 = ", ";
      }

      StringBuilder var5 = new StringBuilder();
      int var6 = 0;

      for (int var8 : var0) {
         if (var6 > 0) {
            var5.append(var4);
         }

         var5.append(var2).append(Integer.toString(var8, var1).toUpperCase()).append(var3);
         var6++;
      }

      return var5.toString();
   }

   public static Iterable range(int var0, int var1, int var2) {
      return new Integers$1(var0, var1, var2);
   }

   public static Iterable range(int var0, int var1) {
      return range(var0, var1, 1);
   }

   public static Iterable range(int var0) {
      return range(0, var0);
   }

   private static class It implements Iterator {
      int i;
      int end;
      int step;

      It(int var1, int var2, int var3) {
         Assert.a(var3 != 0);
         this.i = var1;
         this.end = var2;
         this.step = var3;
      }

      @Override
      public boolean hasNext() {
         return this.step > 0 ? this.i < this.end : this.i > this.end;
      }

      public Integer next() {
         return this.i++;
      }
   }
}
