package com.pnfsoftware.jeb.util.encoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LongLEB128 {
   public static void writeULEB128(OutputStream var0, long var1) throws IOException {
      if ((var1 & -128L) == 0L) {
         var0.write((int)var1);
      } else if ((var1 & -16384L) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write((int)(var1 >>> 7) & 127);
      } else if ((var1 & -2097152L) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write((int)(var1 >>> 14) & 127);
      } else if ((var1 & -268435456L) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write(128 | (int)(var1 >>> 14 & 127L));
         var0.write((int)(var1 >>> 21) & 127);
      } else if ((var1 & -34359738368L) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write(128 | (int)(var1 >>> 14 & 127L));
         var0.write(128 | (int)(var1 >>> 21 & 127L));
         var0.write((int)(var1 >>> 28) & 127);
      } else if ((var1 & -4398046511104L) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write(128 | (int)(var1 >>> 14 & 127L));
         var0.write(128 | (int)(var1 >>> 21 & 127L));
         var0.write(128 | (int)(var1 >>> 28 & 127L));
         var0.write((int)(var1 >>> 35) & 127);
      } else if ((var1 & -562949953421312L) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write(128 | (int)(var1 >>> 14 & 127L));
         var0.write(128 | (int)(var1 >>> 21 & 127L));
         var0.write(128 | (int)(var1 >>> 28 & 127L));
         var0.write(128 | (int)(var1 >>> 35 & 127L));
         var0.write((int)(var1 >>> 42) & 127);
      } else if ((var1 & -72057594037927936L) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write(128 | (int)(var1 >>> 14 & 127L));
         var0.write(128 | (int)(var1 >>> 21 & 127L));
         var0.write(128 | (int)(var1 >>> 28 & 127L));
         var0.write(128 | (int)(var1 >>> 35 & 127L));
         var0.write(128 | (int)(var1 >>> 42 & 127L));
         var0.write((int)(var1 >>> 49) & 127);
      } else if ((var1 & Long.MIN_VALUE) == 0L) {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write(128 | (int)(var1 >>> 14 & 127L));
         var0.write(128 | (int)(var1 >>> 21 & 127L));
         var0.write(128 | (int)(var1 >>> 28 & 127L));
         var0.write(128 | (int)(var1 >>> 35 & 127L));
         var0.write(128 | (int)(var1 >>> 42 & 127L));
         var0.write(128 | (int)(var1 >>> 49 & 127L));
         var0.write((int)(var1 >>> 56) & 127);
      } else {
         var0.write(128 | (int)(var1 & 127L));
         var0.write(128 | (int)(var1 >>> 7 & 127L));
         var0.write(128 | (int)(var1 >>> 14 & 127L));
         var0.write(128 | (int)(var1 >>> 21 & 127L));
         var0.write(128 | (int)(var1 >>> 28 & 127L));
         var0.write(128 | (int)(var1 >>> 35 & 127L));
         var0.write(128 | (int)(var1 >>> 42 & 127L));
         var0.write(128 | (int)(var1 >>> 49 & 127L));
         var0.write(128 | (int)(var1 >>> 56 & 127L));
         var0.write((int)(var1 >>> 63) & 127);
      }
   }

   public static long readULEB128(InputStream var0) throws IOException {
      long var1 = 0L;
      int var3 = -1;
      byte var4 = 0;

      for (int var5 = 0; (var3 & 128) != 0; var5++) {
         if (var5 == 10) {
            throw new IOException("Illegal ULEB128-encoded integer");
         }

         var3 = var0.read();
         if (var3 == -1L) {
            throw new IOException("Not enough data");
         }

         var1 |= (long)(var3 & 127) << var4;
         var4 += 7;
      }

      return var1;
   }

   public static LongLEB128.DecodedLong decodeULEB128(InputStream var0) throws IOException {
      LongLEB128.DecodedLong var1 = new LongLEB128.DecodedLong();
      long var2 = 0L;
      int var4 = -1;
      byte var5 = 0;

      for (int var6 = 0; (var4 & 128) != 0; var6++) {
         if (var6 == 10) {
            throw new IOException("Illegal ULEB128-encoded integer");
         }

         var4 = var0.read();
         var1.encodedSize++;
         if (var4 == -1L) {
            throw new IOException("Not enough data");
         }

         var2 |= (long)(var4 & 127) << var5;
         var5 += 7;
      }

      var1.value = var2;
      return var1;
   }

   public static void writeSLEB128(OutputStream var0, long var1) throws IOException {
      boolean var3 = true;

      while (var3) {
         int var4 = (int)(var1 & 127L);
         var1 >>= 7;
         if ((var1 != 0L || (var4 & 64) != 0) && (var1 != -1L || (var4 & 64) == 0)) {
            var4 |= 128;
         } else {
            var3 = false;
         }

         var0.write(var4);
      }
   }

   public static class DecodedLong {
      public long value;
      public int encodedSize;
   }
}
