package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
   public static final Object[] NO_OBJECT = new Object[0];
   public static final String[] NO_STRING = new String[0];
   public static final boolean[] NO_BOOLEAN = new boolean[0];
   public static final byte[] NO_BYTE = new byte[0];
   public static final char[] NO_CHAR = new char[0];
   public static final short[] NO_SHORT = new short[0];
   public static final int[] NO_INT = new int[0];
   public static final long[] NO_LONG = new long[0];
   public static final float[] NO_FLOAT = new float[0];
   public static final double[] NO_DOUBLE = new double[0];

   public static void copyBytes(byte[] var0, int var1, byte[] var2, int var3, int var4) {
      System.arraycopy(var2, var3, var0, var1, var4);
   }

   public static int[] bytesToIntegers(byte[] var0) {
      int[] var1 = new int[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2];
      }

      return var1;
   }

   public static int[] unsignedBytesToIntegers(byte[] var0) {
      int[] var1 = new int[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2] & 255;
      }

      return var1;
   }

   public static byte[] integersToBytes(int[] var0) {
      byte[] var1 = new byte[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = (byte)var0[var2];
      }

      return var1;
   }

   public static boolean isSled(byte[] var0, byte var1) {
      for (byte var5 : var0) {
         if (var5 != var1) {
            return false;
         }
      }

      return true;
   }

   public static int compareBytes(byte[] var0, int var1, byte[] var2, int var3, int var4) {
      int var5 = var1 + var4;

      while (var1 < var5) {
         int var6 = var0[var1] - var2[var3];
         if (var6 != 0) {
            return var6 > 0 ? 1 : -1;
         }

         var1++;
         var3++;
      }

      return 0;
   }

   public static boolean equalsBytes(byte[] var0, int var1, byte[] var2, int var3, int var4) {
      if (var1 < 0 || var1 + var4 > var0.length) {
         return false;
      } else {
         return var3 >= 0 && var3 + var4 <= var2.length ? compareBytes(var0, var1, var2, var3, var4) == 0 : false;
      }
   }

   public static List asList(boolean[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static List asList(byte[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static List asList(short[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static List asList(char[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static List asList(int[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static List asList(long[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static List asList(float[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static List asList(double[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1.add(var0[var2]);
      }

      return var1;
   }

   public static void swap(boolean[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         boolean var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(boolean[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(byte[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         byte var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(byte[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(short[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         short var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(short[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(char[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         char var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(char[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(int[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         int var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(int[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(long[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         long var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(long[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(float[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         float var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(float[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(double[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         double var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(double[] var0) {
      swap(var0, 0, var0.length);
   }

   public static void swap(Object[] var0, int var1, int var2) {
      int var3 = var1;

      for (int var4 = var1 + var2 - 1; var3 < var4; var4--) {
         Object var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static void swap(Object[] var0) {
      swap(var0, 0, var0.length);
   }

   public static int find(boolean[] var0, boolean var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int find(byte[] var0, byte var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int find(char[] var0, char var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int find(short[] var0, short var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int find(int[] var0, int var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int find(long[] var0, long var1) {
      for (int var3 = 0; var3 < var0.length; var3++) {
         if (var0[var3] == var1) {
            return var3;
         }
      }

      return -1;
   }

   public static int find(float[] var0, float var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int find(double[] var0, double var1) {
      for (int var3 = 0; var3 < var0.length; var3++) {
         if (var0[var3] == var1) {
            return var3;
         }
      }

      return -1;
   }

   public static int findByReference(Object[] var0, Object var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public static int findByEquality(Object[] var0, Object var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var1 == null && var0[var2] == null || var1 != null && var1.equals(var0[var2])) {
            return var2;
         }
      }

      return -1;
   }

   public static void checkOffsetAndCount(int var0, int var1, int var2) {
      if (var1 < 0 || var2 < 0 || var1 > var0 || var1 + var2 > var0) {
         throw new ArrayIndexOutOfBoundsException(Strings.ff("An array of length %d cannot accommodate a sub-range [%d,%d)", var0, var1, var1 + var2));
      }
   }

   public static Object getSafe(Object[] var0, int var1, Object var2) {
      return var1 >= 0 && var1 < var0.length && var0[var1] != null ? var0[var1] : var2;
   }

   public static Object getSafe2(Object[][] var0, int var1, int var2, Object var3) {
      return var1 >= 0 && var1 < var0.length && var0[var1] != null ? getSafe(var0[var1], var2, var3) : var3;
   }

   public static Object getSafe3(Object[][][] var0, int var1, int var2, int var3, Object var4) {
      return var1 >= 0 && var1 < var0.length && var0[var1] != null ? getSafe2(var0[var1], var2, var3, var4) : var4;
   }

   public static List asView(Object[] var0) {
      return new ArrayUtil.OneArrayAsList(var0);
   }

   public static List asView(Object[] var0, Object[] var1) {
      return new ArrayUtil.TwoArraysAsList(var0, var1);
   }

   @SafeVarargs
   public static List asView(Object[]... var0) {
      return new ArrayUtil.MultiArraysAsList(var0);
   }

   private static class MultiArraysAsList extends AbstractList {
      Object[][] arrays;

      MultiArraysAsList(Object[][] var1) {
         this.arrays = var1;
      }

      @Override
      public int size() {
         int var1 = 0;

         for (Object[] var5 : this.arrays) {
            var1 += var5.length;
         }

         return var1;
      }

      @Override
      public Object get(int var1) {
         if (var1 < 0) {
            throw new ArrayIndexOutOfBoundsException();
         } else {
            for (Object[] var5 : this.arrays) {
               if (var1 < var5.length) {
                  return var5[var1];
               }

               var1 -= var5.length;
            }

            throw new ArrayIndexOutOfBoundsException();
         }
      }
   }

   private static class OneArrayAsList extends AbstractList {
      Object[] a;

      OneArrayAsList(Object[] var1) {
         this.a = var1;
      }

      @Override
      public int size() {
         return this.a.length;
      }

      @Override
      public Object get(int var1) {
         if (var1 < 0) {
            throw new ArrayIndexOutOfBoundsException();
         } else if (var1 < this.a.length) {
            return this.a[var1];
         } else {
            throw new ArrayIndexOutOfBoundsException();
         }
      }
   }

   private static class TwoArraysAsList extends AbstractList {
      Object[] a;
      Object[] b;

      TwoArraysAsList(Object[] var1, Object[] var2) {
         this.a = var1;
         this.b = var2;
      }

      @Override
      public int size() {
         return this.a.length + this.b.length;
      }

      @Override
      public Object get(int var1) {
         if (var1 < 0) {
            throw new ArrayIndexOutOfBoundsException();
         } else if (var1 < this.a.length) {
            return this.a[var1];
         } else {
            var1 -= this.a.length;
            if (var1 < this.b.length) {
               return this.b[var1];
            } else {
               throw new ArrayIndexOutOfBoundsException();
            }
         }
      }
   }
}
