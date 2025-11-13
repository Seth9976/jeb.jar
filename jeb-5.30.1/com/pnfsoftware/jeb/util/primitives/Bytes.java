package com.pnfsoftware.jeb.util.primitives;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class Bytes {
   public static String toUnsignedString(byte var0) {
      return Longs.toUnsignedString(var0 & 255L);
   }

   public static int indexOf(byte[] var0, byte[] var1) {
      if (var1.length == 0) {
         return 0;
      } else {
         label28:
         for (int var2 = 0; var2 < var0.length - var1.length + 1; var2++) {
            for (int var3 = 0; var3 < var1.length; var3++) {
               if (var0[var2 + var3] != var1[var3]) {
                  continue label28;
               }
            }

            return var2;
         }

         return -1;
      }
   }

   public static int indexOf(byte[] var0, int var1, byte[] var2) {
      return indexOf(var0, var1, var0.length, var2, 0, var2.length, null);
   }

   public static int indexOf(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6) {
      if (var1 < var0.length && var2 >= var1) {
         if (var3.length == 0) {
            return var1;
         } else if (var6 == null) {
            label48:
            for (int var11 = var1; var11 < var2 - (var5 - var4) + 1; var11++) {
               int var12 = var4;

               for (int var13 = 0; var12 < var5; var13++) {
                  if (var0[var11 + var13] != var3[var12]) {
                     continue label48;
                  }

                  var12++;
               }

               return var11;
            }

            return -1;
         } else if (var6.length != var3.length) {
            throw new IllegalArgumentException("The mask must have the same length as the target");
         } else {
            int var7 = var2 - (var5 - var4) + 1;

            label60:
            for (int var8 = var1; var8 < var7; var8++) {
               int var9 = var4;

               for (int var10 = 0; var9 < var5; var10++) {
                  if ((var0[var8 + var10] & var6[var9]) != var3[var9]) {
                     continue label60;
                  }

                  var9++;
               }

               return var8;
            }

            return -1;
         }
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(byte[] var0, byte[] var1) {
      return lastIndexOf(var0, var0.length, var1);
   }

   public static int lastIndexOf(byte[] var0, int var1, byte[] var2) {
      int var3 = Math.min(var1, var0.length - var2.length);
      if (var3 < 0) {
         return -1;
      } else if (var2.length == 0) {
         return var3;
      } else {
         label32:
         for (int var4 = var3; var4 >= 0; var4--) {
            for (int var5 = var2.length - 1; var5 >= 0; var5--) {
               if (var0[var4 + var5] != var2[var5]) {
                  continue label32;
               }
            }

            return var4;
         }

         return -1;
      }
   }

   public static int lastIndexOf(byte[] var0, int var1, byte[] var2, byte[] var3) {
      if (var3 == null) {
         return lastIndexOf(var0, var1, var2);
      } else if (var3.length != var2.length) {
         throw new IllegalArgumentException("The mask must have the same length as the target");
      } else {
         int var4 = Math.min(var1, var0.length - var2.length);
         if (var4 < 0) {
            return -1;
         } else if (var2.length == 0) {
            return var4;
         } else {
            label40:
            for (int var5 = var4; var5 >= 0; var5--) {
               for (int var6 = var2.length - 1; var6 >= 0; var6--) {
                  if ((var0[var5 + var6] & var3[var6]) != var2[var6]) {
                     continue label40;
                  }
               }

               return var5;
            }

            return -1;
         }
      }
   }

   public static boolean equals(byte[] var0, int var1, byte[] var2, int var3, int var4) {
      return ArrayUtil.equalsBytes(var0, var1, var2, var3, var4);
   }

   public static boolean equals(byte[] var0, byte[] var1) {
      if (var0 == null) {
         return var1 == null;
      } else if (var1 == null) {
         return false;
      } else {
         return var0.length != var1.length ? false : equals(var0, 0, var1, 0, var0.length);
      }
   }
}
