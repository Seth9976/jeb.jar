package com.pnfsoftware.jeb.util.io;

import java.nio.ByteOrder;

public class EndianUtil {
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

   public static void swapByGroup(byte[] var0, int var1) {
      int var2 = 0;

      while (var2 < var0.length) {
         for (int var3 = 0; var3 < var1 / 2; var3++) {
            byte var4 = var0[var2 + var3];
            var0[var2 + var3] = var0[var2 + (var1 - (var3 + 1))];
            var0[var2 + (var1 - (var3 + 1))] = var4;
         }

         var2 += var1;
      }
   }

   public static short swapShort(short var0) {
      return (short)((var0 & 255) << 8 | var0 >> 8 & 0xFF);
   }

   public static int swapInt(int var0) {
      return (var0 & 0xFF) << 24 | (var0 >> 8 & 0xFF) << 16 | (var0 >> 16 & 0xFF) << 8 | var0 >> 24 & 0xFF;
   }

   public static long swapLong(long var0) {
      return (var0 & 255L) << 56
         | (var0 >> 8 & 255L) << 48
         | (var0 >> 16 & 255L) << 40
         | (var0 >> 24 & 255L) << 32
         | (var0 >> 32 & 255L) << 24
         | (var0 >> 40 & 255L) << 16
         | (var0 >> 48 & 255L) << 8
         | var0 >> 56 & 255L;
   }

   public static byte[] shortToLEBytes(short var0) {
      return new byte[]{(byte)var0, (byte)(var0 >> 8)};
   }

   public static void shortToLEBytes(short var0, byte[] var1) {
      shortToLEBytes(var0, var1, 0);
   }

   public static void shortToLEBytes(short var0, byte[] var1, int var2) {
      var1[var2] = (byte)var0;
      var1[var2 + 1] = (byte)(var0 >> 8);
   }

   public static byte[] intToLEBytes(int var0) {
      return new byte[]{(byte)var0, (byte)(var0 >> 8), (byte)(var0 >> 16), (byte)(var0 >> 24)};
   }

   public static void intToLEBytes(int var0, byte[] var1) {
      intToLEBytes(var0, var1, 0);
   }

   public static void intToLEBytes(int var0, byte[] var1, int var2) {
      var1[var2] = (byte)var0;
      var1[var2 + 1] = (byte)(var0 >> 8);
      var1[var2 + 2] = (byte)(var0 >> 16);
      var1[var2 + 3] = (byte)(var0 >> 24);
   }

   public static byte[] longToLEBytes(long var0) {
      byte[] var2 = new byte[8];
      longToLEBytes(var0, var2);
      return var2;
   }

   public static void longToLEBytes(long var0, byte[] var2) {
      longToLEBytes(var0, var2, 0);
   }

   public static void longToLEBytes(long var0, byte[] var2, int var3) {
      var2[var3] = (byte)var0;
      var2[var3 + 1] = (byte)(var0 >> 8);
      var2[var3 + 2] = (byte)(var0 >> 16);
      var2[var3 + 3] = (byte)(var0 >> 24);
      var2[var3 + 4] = (byte)(var0 >> 32);
      var2[var3 + 5] = (byte)(var0 >> 40);
      var2[var3 + 6] = (byte)(var0 >> 48);
      var2[var3 + 7] = (byte)(var0 >> 56);
   }

   public static byte[] shortToBEBytes(short var0) {
      return new byte[]{(byte)(var0 >> 8), (byte)var0};
   }

   public static void shortToBEBytes(short var0, byte[] var1) {
      shortToBEBytes(var0, var1, 0);
   }

   public static void shortToBEBytes(short var0, byte[] var1, int var2) {
      var1[var2 + 1] = (byte)var0;
      var1[var2] = (byte)(var0 >> 8);
   }

   public static byte[] intToBEBytes(int var0) {
      return new byte[]{(byte)(var0 >> 24), (byte)(var0 >> 16), (byte)(var0 >> 8), (byte)var0};
   }

   public static void intToBEBytes(int var0, byte[] var1) {
      intToBEBytes(var0, var1, 0);
   }

   public static void intToBEBytes(int var0, byte[] var1, int var2) {
      var1[var2 + 3] = (byte)var0;
      var1[var2 + 2] = (byte)(var0 >> 8);
      var1[var2 + 1] = (byte)(var0 >> 16);
      var1[var2] = (byte)(var0 >> 24);
   }

   public static byte[] longToBEBytes(long var0) {
      byte[] var2 = new byte[8];
      longToBEBytes(var0, var2, 0);
      return var2;
   }

   public static void longToBEBytes(long var0, byte[] var2) {
      longToBEBytes(var0, var2, 0);
   }

   public static void longToBEBytes(long var0, byte[] var2, int var3) {
      var2[var3 + 7] = (byte)var0;
      var2[var3 + 6] = (byte)(var0 >> 8);
      var2[var3 + 5] = (byte)(var0 >> 16);
      var2[var3 + 4] = (byte)(var0 >> 24);
      var2[var3 + 3] = (byte)(var0 >> 32);
      var2[var3 + 2] = (byte)(var0 >> 40);
      var2[var3 + 1] = (byte)(var0 >> 48);
      var2[var3] = (byte)(var0 >> 56);
   }

   public static short littleEndianBytesToShort(byte[] var0) {
      return littleEndianBytesToShort(var0, 0);
   }

   public static short littleEndianBytesToShort(byte[] var0, int var1) {
      return (short)(var0[var1] & 255 | var0[var1 + 1] << 8 & 0xFF00);
   }

   public static int littleEndianBytesToInt(byte[] var0) {
      return littleEndianBytesToInt(var0, 0);
   }

   public static int littleEndianBytesToInt(byte[] var0, int var1) {
      return var0[var1] & 0xFF | var0[var1 + 1] << 8 & 0xFF00 | var0[var1 + 2] << 16 & 0xFF0000 | var0[var1 + 3] << 24 & 0xFF000000;
   }

   public static long littleEndianBytesToLong(byte[] var0) {
      return littleEndianBytesToLong(var0, 0);
   }

   public static long littleEndianBytesToLong(byte[] var0, int var1) {
      return var0[var1] & 255L
         | (long)var0[var1 + 1] << 8 & 65280L
         | (long)var0[var1 + 2] << 16 & 16711680L
         | (long)var0[var1 + 3] << 24 & 4278190080L
         | (long)var0[var1 + 4] << 32 & 1095216660480L
         | (long)var0[var1 + 5] << 40 & 280375465082880L
         | (long)var0[var1 + 6] << 48 & 71776119061217280L
         | (long)var0[var1 + 7] << 56 & -72057594037927936L;
   }

   public static short bigEndianBytesToShort(byte[] var0) {
      return bigEndianBytesToShort(var0, 0);
   }

   public static short bigEndianBytesToShort(byte[] var0, int var1) {
      return (short)(var0[var1 + 1] & 255 | var0[var1] << 8 & 0xFF00);
   }

   public static int bigEndianBytesToInt(byte[] var0) {
      return bigEndianBytesToInt(var0, 0);
   }

   public static int bigEndianBytesToInt(byte[] var0, int var1) {
      return var0[var1 + 3] & 0xFF | var0[var1 + 2] << 8 & 0xFF00 | var0[var1 + 1] << 16 & 0xFF0000 | var0[var1] << 24 & 0xFF000000;
   }

   public static long bigEndianBytesToLong(byte[] var0) {
      return bigEndianBytesToLong(var0, 0);
   }

   public static long bigEndianBytesToLong(byte[] var0, int var1) {
      return var0[var1 + 7] & 255L
         | (long)var0[var1 + 6] << 8 & 65280L
         | (long)var0[var1 + 5] << 16 & 16711680L
         | (long)var0[var1 + 4] << 24 & 4278190080L
         | (long)var0[var1 + 3] << 32 & 1095216660480L
         | (long)var0[var1 + 2] << 40 & 280375465082880L
         | (long)var0[var1 + 1] << 48 & 71776119061217280L
         | (long)var0[var1] << 56 & -72057594037927936L;
   }

   public static void numberToBytes(ByteOrder var0, long var1, byte[] var3) {
      if (var3 != null && var3.length != 0) {
         if (var3.length >= 8) {
            if (var0 == ByteOrder.LITTLE_ENDIAN) {
               longToLEBytes(var1, var3);
            } else {
               longToBEBytes(var1, var3);
            }
         } else if (var3.length >= 4) {
            if (var0 == ByteOrder.LITTLE_ENDIAN) {
               intToLEBytes((int)var1, var3);
            } else {
               intToBEBytes((int)var1, var3);
            }
         } else if (var3.length >= 2) {
            if (var0 == ByteOrder.LITTLE_ENDIAN) {
               shortToLEBytes((short)var1, var3);
            } else {
               shortToBEBytes((short)var1, var3);
            }
         } else {
            var3[0] = (byte)var1;
         }
      }
   }

   public static long bytesToNumberSigned(ByteOrder var0, byte[] var1) {
      if (var1 != null && var1.length != 0) {
         if (var1.length < 8) {
            if (var1.length < 4) {
               if (var1.length < 2) {
                  return var1[0];
               } else {
                  return var0 == ByteOrder.LITTLE_ENDIAN ? littleEndianBytesToShort(var1, 0) : bigEndianBytesToShort(var1, 0);
               }
            } else {
               return var0 == ByteOrder.LITTLE_ENDIAN ? littleEndianBytesToInt(var1, 0) : bigEndianBytesToInt(var1, 0);
            }
         } else {
            return var0 == ByteOrder.LITTLE_ENDIAN ? littleEndianBytesToLong(var1, 0) : bigEndianBytesToLong(var1, 0);
         }
      } else {
         throw new IllegalArgumentException("bytesToNumber with invalid parameters: byte[] is null or empty");
      }
   }

   public static long bytesToNumberUnsigned(ByteOrder var0, byte[] var1) {
      long var2 = bytesToNumberSigned(var0, var1);
      if (var1.length >= 8) {
         return var2;
      } else if (var1.length >= 4) {
         return var2 & -1L;
      } else {
         return var1.length >= 2 ? var2 & 65535L : var2 & 255L;
      }
   }

   public static short bytesToShort(byte[] var0, int var1, ByteOrder var2) {
      return var2 == ByteOrder.LITTLE_ENDIAN ? littleEndianBytesToShort(var0, var1) : bigEndianBytesToShort(var0, var1);
   }

   public static int bytesToInt(byte[] var0, int var1, ByteOrder var2) {
      return var2 == ByteOrder.LITTLE_ENDIAN ? littleEndianBytesToInt(var0, var1) : bigEndianBytesToInt(var0, var1);
   }

   public static long bytesToLong(byte[] var0, int var1, ByteOrder var2) {
      return var2 == ByteOrder.LITTLE_ENDIAN ? littleEndianBytesToLong(var0, var1) : bigEndianBytesToLong(var0, var1);
   }
}
