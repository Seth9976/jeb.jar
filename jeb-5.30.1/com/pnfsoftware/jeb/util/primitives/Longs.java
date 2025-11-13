package com.pnfsoftware.jeb.util.primitives;

import com.pnfsoftware.jeb.util.base.Assert;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;

public class Longs {
   private static BigInteger _2p64 = BigInteger.ONE.shiftLeft(64);

   public static long fromInts(int var0, int var1) {
      return (long)var1 << 32 | var0 & 4294967295L;
   }

   public static int[] toInts(long var0) {
      return new int[]{(int)var0, (int)(var0 >> 32)};
   }

   public static String toUnsignedString(long var0) {
      long var2 = (var0 >>> 1) / 5L;
      long var4 = var0 - var2 * 10L;
      return var2 != 0L ? Long.toString(var2) + var4 : Long.toString(var4);
   }

   public static BigInteger toUnsignedBigInteger(long var0) {
      BigInteger var2 = BigInteger.valueOf(var0);
      if (var0 < 0L) {
         var2 = var2.add(_2p64);
      }

      return var2;
   }

   public static int compareUnsigned(long var0, long var2) {
      return Long.compare(var0 + Long.MIN_VALUE, var2 + Long.MIN_VALUE);
   }

   public static long maxUnsigned(long var0, long var2) {
      return compareUnsigned(var0, var2) >= 0 ? var0 : var2;
   }

   public static long minUnsigned(long var0, long var2) {
      return compareUnsigned(var0, var2) <= 0 ? var0 : var2;
   }

   public static long divUnsigned(long var0, long var2) {
      if (var2 < 0L) {
         return compareUnsigned(var0, var2) < 0 ? 0L : 1L;
      } else if (var0 >= 0L) {
         return var0 / var2;
      } else {
         long var4 = (var0 >>> 1) / var2 << 1;
         long var6 = var0 - var4 * var2;
         return var4 + (compareUnsigned(var6, var2) >= 0 ? 1 : 0);
      }
   }

   public static long remUnsigned(long var0, long var2) {
      if (var2 < 0L) {
         return compareUnsigned(var0, var2) < 0 ? var0 : var0 - var2;
      } else if (var0 >= 0L) {
         return var0 % var2;
      } else {
         long var4 = (var0 >>> 1) / var2 << 1;
         long var6 = var0 - var4 * var2;
         return var6 - (compareUnsigned(var6, var2) >= 0 ? var2 : 0L);
      }
   }

   public static String formatHexCollection(Collection var0) {
      return formatLongCollection(var0, 16, "0x", null, null);
   }

   public static String formatLongCollection(Collection var0, Integer var1, String var2, String var3, String var4) {
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

      for (Long var8 : var0) {
         if (var6 > 0) {
            var5.append(var4);
         }

         var5.append(var2).append(Long.toString(var8, var1).toUpperCase()).append(var3);
         var6++;
      }

      return var5.toString();
   }

   public static boolean equals(Long var0, Long var1) {
      if (var0 == null) {
         return var1 == null;
      } else {
         return var1 == null ? var0 == null : var0 == var1;
      }
   }

   public static boolean equals(Long var0, long var1) {
      return var0 == null ? false : var0 == var1;
   }

   public static boolean equals(long var0, Long var2) {
      return var2 == null ? false : var0 == var2;
   }

   public static Iterable range(long var0, long var2, long var4) {
      return new Longs$1(var0, var2, var4);
   }

   public static Iterable range(long var0, long var2) {
      return range(var0, var2, 1L);
   }

   public static Iterable range(long var0) {
      return range(0L, var0);
   }

   private static class It implements Iterator {
      long i;
      long end;
      long step;

      It(long var1, long var3, long var5) {
         Assert.a(var5 != 0L);
         this.i = var1;
         this.end = var3;
         this.step = var5;
      }

      @Override
      public boolean hasNext() {
         return this.step > 0L ? this.i < this.end : this.i > this.end;
      }

      public Long next() {
         return this.i = this.i + this.step;
      }
   }
}
