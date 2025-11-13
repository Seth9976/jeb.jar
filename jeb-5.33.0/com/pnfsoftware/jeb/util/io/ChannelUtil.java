package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;

public class ChannelUtil {
   public static ByteBuffer read(SeekableByteChannel var0, long var1, int var3, boolean var4) throws IOException {
      return read(var0, var1, var3, var4, null);
   }

   public static ByteBuffer read(SeekableByteChannel var0, long var1, int var3, boolean var4, ByteBuffer var5) throws IOException {
      return readInternal(var0, var1, var3, var4, var5, false);
   }

   public static ByteBuffer readBestEffort(SeekableByteChannel var0, long var1, int var3, boolean var4, ByteBuffer var5) throws IOException {
      return readInternal(var0, var1, var3, var4, var5, true);
   }

   private static ByteBuffer readInternal(SeekableByteChannel var0, long var1, int var3, boolean var4, ByteBuffer var5, boolean var6) throws IOException {
      if (var3 < 0) {
         throw new IllegalArgumentException("Negative size");
      } else if (var3 == 0) {
         return prepareBuffer(var5, 0, var4);
      } else {
         long var7 = var0.size();
         if (Long.compareUnsigned(var1, var7) >= 0) {
            if (var6) {
               return prepareBuffer(var5, 0, var4);
            } else {
               throw new IOException(Strings.ff("Read 0 bytes at offset %Xh, expected %d", var1, var3));
            }
         } else {
            long var9 = var7 - var1;
            int var11 = var3;
            if (Long.compareUnsigned(var3, var9) > 0) {
               var11 = (int)var9;
            }

            var5 = prepareBuffer(var5, var11, var4);
            var0.position(var1);
            int var12 = var0.read(var5);
            if (var12 != var3) {
               if (!var6) {
                  throw new IOException(Strings.ff("Read %d bytes at offset %Xh, expected %d", var12, var1, var3));
               }

               var5.limit(Math.max(0, var12));
            }

            var5.rewind();
            return var5;
         }
      }
   }

   private static ByteBuffer prepareBuffer(ByteBuffer var0, int var1, boolean var2) {
      if (var0 != null && var0.capacity() >= var1) {
         var0.clear();
         var0.limit(var1);
      } else {
         var0 = ByteBuffer.allocate(var1);
      }

      var0.order(var2 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
      return var0;
   }

   public static byte[] getAllFrom(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      long var3 = var0.size() - var1;
      if (var3 > 2147483647L) {
         throw new IOException("Cannot alocate that many bytes: " + var3);
      } else {
         byte[] var5 = new byte[(int)var3];
         ByteBuffer var6 = ByteBuffer.wrap(var5);
         int var7 = var0.read(var6);
         if (var7 != var5.length) {
            throw new IOException(Strings.ff("Wanted to read %d bytes, got %d only", var5.length, var7));
         } else {
            return var5;
         }
      }
   }

   public static byte get(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      ByteBuffer var3 = ByteBuffer.allocate(1);
      if (var0.read(var3) != 1) {
         throw new IOException(Strings.ff("Expected a byte at offset %Xh", var1));
      } else {
         return var3.get(0);
      }
   }

   public static byte get(SeekableByteChannel var0) throws IOException {
      return get(var0, var0.position());
   }

   public static byte[] getBytes(SeekableByteChannel var0, long var1, int var3) throws IOException {
      var0.position(var1);
      ByteBuffer var4 = ByteBuffer.allocate(var3);
      if (var0.read(var4) != var3) {
         throw new IOException(Strings.ff("Expected %d bytes at offset %Xh", var3, var1));
      } else {
         return var4.array();
      }
   }

   public static byte[] getBytes(SeekableByteChannel var0, int var1) throws IOException {
      return getBytes(var0, var0.position(), var1);
   }

   public static short getShort(SeekableByteChannel var0, long var1, ByteOrder var3) throws IOException {
      return var3 == ByteOrder.BIG_ENDIAN ? getBEShort(var0, var1) : getLEShort(var0, var1);
   }

   public static int getInt(SeekableByteChannel var0, long var1, ByteOrder var3) throws IOException {
      return var3 == ByteOrder.BIG_ENDIAN ? getBEInt(var0, var1) : getLEInt(var0, var1);
   }

   public static long getLong(SeekableByteChannel var0, long var1, ByteOrder var3) throws IOException {
      return var3 == ByteOrder.BIG_ENDIAN ? getBELong(var0, var1) : getLELong(var0, var1);
   }

   public static short getShort(SeekableByteChannel var0, ByteOrder var1) throws IOException {
      return getShort(var0, var0.position(), var1);
   }

   public static int getInt(SeekableByteChannel var0, ByteOrder var1) throws IOException {
      return getInt(var0, var0.position(), var1);
   }

   public static long getLong(SeekableByteChannel var0, ByteOrder var1) throws IOException {
      return getLong(var0, var0.position(), var1);
   }

   public static short getLEShort(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      ByteBuffer var3 = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
      if (var0.read(var3) != 2) {
         throw new IOException(Strings.ff("Expected a short at offset %Xh", var1));
      } else {
         return var3.getShort(0);
      }
   }

   public static int getLEInt(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      ByteBuffer var3 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
      if (var0.read(var3) != 4) {
         throw new IOException(Strings.ff("Expected an int at offset %Xh", var1));
      } else {
         return var3.getInt(0);
      }
   }

   public static long getLELong(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      ByteBuffer var3 = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
      if (var0.read(var3) != 8) {
         throw new IOException(Strings.ff("Expected a long at offset %Xh", var1));
      } else {
         return var3.getLong(0);
      }
   }

   public static short getBEShort(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      ByteBuffer var3 = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN);
      if (var0.read(var3) != 2) {
         throw new IOException(Strings.ff("Expected a short at offset %Xh", var1));
      } else {
         return var3.getShort(0);
      }
   }

   public static int getBEInt(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      ByteBuffer var3 = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
      if (var0.read(var3) != 4) {
         throw new IOException(Strings.ff("Expected an int at offset %Xh", var1));
      } else {
         return var3.getInt(0);
      }
   }

   public static long getBELong(SeekableByteChannel var0, long var1) throws IOException {
      var0.position(var1);
      ByteBuffer var3 = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN);
      if (var0.read(var3) != 8) {
         throw new IOException(Strings.ff("Expected a long at offset %Xh", var1));
      } else {
         return var3.getLong(0);
      }
   }

   public static short getLEShort(SeekableByteChannel var0) throws IOException {
      return getLEShort(var0, var0.position());
   }

   public static int getLEInt(SeekableByteChannel var0) throws IOException {
      return getLEInt(var0, var0.position());
   }

   public static long getLELong(SeekableByteChannel var0) throws IOException {
      return getLELong(var0, var0.position());
   }

   public static short getBEShort(SeekableByteChannel var0) throws IOException {
      return getBEShort(var0, var0.position());
   }

   public static int getBEInt(SeekableByteChannel var0) throws IOException {
      return getBEInt(var0, var0.position());
   }

   public static long getBELong(SeekableByteChannel var0) throws IOException {
      return getBELong(var0, var0.position());
   }

   public static byte[] getBytesUntil(SeekableByteChannel var0, int var1, int var2, boolean var3) throws IOException {
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      byte[] var5 = new byte[256];
      ByteBuffer var6 = ByteBuffer.wrap(var5);
      int var7 = var2;
      boolean var8 = false;

      while (var7 > 0 && !var8) {
         var6.position(0);
         if (var7 < var6.limit()) {
            var6.limit(var7);
            var8 = true;
         }

         int var9 = var0.read(var6);
         if (var9 <= 0) {
            if (var3) {
               throw new IOException("Not enough data");
            }
            break;
         }

         int var10 = 0;

         while (true) {
            if (var10 < var9) {
               if (var5[var10] != var1) {
                  var10++;
                  continue;
               }

               var8 = true;
            }

            var4.write(var5, 0, var10);
            var7 -= var10;
            break;
         }
      }

      return var4.toByteArray();
   }
}
