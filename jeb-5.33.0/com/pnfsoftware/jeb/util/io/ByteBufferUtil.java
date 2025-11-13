package com.pnfsoftware.jeb.util.io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferUtil {
   public static boolean equalsByteArray(ByteBuffer var0, int var1, byte[] var2, int var3, int var4) {
      for (int var5 = 0; var5 < var4; var5++) {
         if (var0.get(var1 + var5) != var2[var3 + var5]) {
            return false;
         }
      }

      return true;
   }

   public static byte[] getBytes(ByteBuffer var0, int var1, int var2) {
      byte[] var3 = new byte[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = var0.get(var1 + var4);
      }

      return var3;
   }

   public static byte[] getBytes(ByteBuffer var0) {
      byte[] var1 = new byte[var0.remaining()];
      var0.get(var1);
      return var1;
   }

   public static void skip(ByteBuffer var0, int var1) {
      var0.position(var0.position() + var1);
   }

   public static int skipAttempt(ByteBuffer var0, int var1) {
      int var2 = var0.position();
      int var3 = var0.remaining();
      if (var1 > var3) {
         var1 = var3;
      } else if (var1 < 0 && var1 + var2 < 0) {
         var1 = -var2;
      }

      var0.position(var2 + var1);
      return var1;
   }

   public static void skipToEnd(ByteBuffer var0) {
      int var1 = var0.remaining();
      if (var1 > 0) {
         var0.position(var0.position() + var1);
      }
   }

   public static long getUnsignedInt(ByteBuffer var0) {
      return var0.getInt() & -1;
   }

   public static long getUnsignedInt(ByteBuffer var0, int var1) {
      return var0.getInt(var1) & -1;
   }

   public static ByteBuffer wrapLE(byte[] var0) {
      ByteBuffer var1 = ByteBuffer.wrap(var0);
      var1.order(ByteOrder.LITTLE_ENDIAN);
      return var1;
   }

   public static ByteBuffer wrapBE(byte[] var0) {
      ByteBuffer var1 = ByteBuffer.wrap(var0);
      var1.order(ByteOrder.BIG_ENDIAN);
      return var1;
   }

   public static ByteBuffer wrapLE(byte[] var0, int var1, int var2) {
      ByteBuffer var3 = ByteBuffer.wrap(var0, var1, var2);
      var3.order(ByteOrder.LITTLE_ENDIAN);
      return var3;
   }

   public static ByteBuffer wrapBE(byte[] var0, int var1, int var2) {
      ByteBuffer var3 = ByteBuffer.wrap(var0, var1, var2);
      var3.order(ByteOrder.BIG_ENDIAN);
      return var3;
   }

   public static int align(ByteBuffer var0, int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Invalid alignment: " + var1);
      } else {
         int var2 = var0.position();
         int var3 = var1 - var2 % var1;
         if (var3 != var1) {
            var2 += var3;
            var0.position(var2);
         }

         return var2;
      }
   }
}
