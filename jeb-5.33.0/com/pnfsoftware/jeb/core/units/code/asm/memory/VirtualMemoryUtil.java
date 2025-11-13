package com.pnfsoftware.jeb.core.units.code.asm.memory;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Bytes;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jebglobal.awo;
import com.pnfsoftware.jebglobal.awp;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class VirtualMemoryUtil {
   private static final ILogger logger = GlobalLog.getLogger(VirtualMemoryUtil.class);

   public static IVirtualMemory createMemory(int var0, int var1, Endianness var2) {
      return new awp(var0, var1, var2);
   }

   public static IVirtualMemoryShim getCopyOnWriteShim(IVirtualMemory var0) {
      if (var0 instanceof awo) {
         return ((awo)var0).pC();
      } else {
         throw new IllegalArgumentException("The input memory object does not support copy-on-write shims");
      }
   }

   public static boolean isPageAllocated(IVirtualMemory var0, long var1) {
      return var0.check(var1, 1, 0) != 0;
   }

   public static long findAvailableRange(IVirtualMemory param0, long param1, int param3) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:359)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:504)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.insertSemaphore(FinallyProcessor.java:350)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:99)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:178)
      //
      // Bytecode:
      // 00: aload 0
      // 01: dup
      // 02: astore 4
      // 04: monitorenter
      // 05: aload 0
      // 06: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/memory/IVirtualMemory.getPageSize ()I 1
      // 0b: i2l
      // 0c: lstore 5
      // 0e: iload 3
      // 0f: i2l
      // 10: ldc2_w 4294967295
      // 13: land
      // 14: lstore 7
      // 16: aload 0
      // 17: lload 1
      // 18: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/memory/IVirtualMemory.roundToPage (J)J 3
      // 1d: lstore 9
      // 1f: ldc2_w -1
      // 22: lstore 11
      // 24: lload 9
      // 26: lstore 13
      // 28: lload 13
      // 2a: lload 9
      // 2c: invokestatic java/lang/Long.compareUnsigned (JJ)I
      // 2f: iflt 6f
      // 32: lload 13
      // 34: lload 5
      // 36: ladd
      // 37: lstore 15
      // 39: aload 0
      // 3a: lload 13
      // 3c: invokestatic com/pnfsoftware/jeb/core/units/code/asm/memory/VirtualMemoryUtil.isPageAllocated (Lcom/pnfsoftware/jeb/core/units/code/asm/memory/IVirtualMemory;J)Z
      // 3f: ifne 63
      // 42: lload 11
      // 44: ldc2_w -1
      // 47: lcmp
      // 48: ifne 52
      // 4b: lload 13
      // 4d: lstore 11
      // 4f: goto 68
      // 52: lload 15
      // 54: lload 11
      // 56: lsub
      // 57: lload 7
      // 59: lcmp
      // 5a: iflt 68
      // 5d: lload 11
      // 5f: aload 4
      // 61: monitorexit
      // 62: lreturn
      // 63: ldc2_w -1
      // 66: lstore 11
      // 68: lload 15
      // 6a: lstore 13
      // 6c: goto 28
      // 6f: ldc2_w -1
      // 72: aload 4
      // 74: monitorexit
      // 75: lreturn
      // 76: astore 17
      // 78: aload 4
      // 7a: monitorexit
      // 7b: aload 17
      // 7d: athrow
   }

   public static long allocate(IVirtualMemory var0, long var1, int var3, int var4) {
      synchronized (var0) {
         long var6 = findAvailableRange(var0, var1, var3);
         if (var6 != -1L) {
            try {
               var0.allocate(var6, var3, var4);
            } catch (MemoryException var9) {
               Object[] var10000 = new Object[]{var3, var6};
            }
         }

         return var6;
      }
   }

   public static int allocateFillGaps(IVirtualMemory var0, long var1, int var3, int var4) {
      synchronized (var0) {
         long var6 = var0.roundToPage(var1);
         long var8 = var0.roundToSize(var1 + (var3 & 4294967295L));
         long var10 = var0.getPageSize();

         int var12;
         for (var12 = 0; var6 != var8; var6 += var10) {
            if (var0.check(var6, 1, 0) == 0) {
               try {
                  var0.allocate(var6, 1, var4);
               } catch (MemoryException var15) {
                  throw new RuntimeException(var15);
               }

               var12++;
            }
         }

         return var12;
      }
   }

   public static boolean deallocate(IVirtualMemory var0, long var1, int var3) {
      synchronized (var0) {
         boolean var10000;
         try {
            var0.free(var1, var3);
            var10000 = true;
         } catch (MemoryException var6) {
            return false;
         }

         return var10000;
      }
   }

   public static int readBytesSafe(IVirtualMemory var0, long var1, int var3, byte[] var4, int var5, int var6) {
      if ((var6 & 1) == 0) {
         throw new IllegalArgumentException("Expected at least READ access");
      } else {
         try {
            synchronized (var0) {
               int var8 = var0.check(var1, var3, var6);
               var0.read(var1, var8, var4, var5);
               return var8;
            }
         } catch (MemoryException var11) {
            return -1;
         }
      }
   }

   public static int readBytesSafe(IVirtualMemory var0, long var1, int var3, byte[] var4, int var5, boolean var6) {
      try {
         int var7 = var6 ? 0 : 1;
         synchronized (var0) {
            int var9 = var0.check(var1, var3, var7);
            var0.read(var1, var9, var4, var5, var6);
            return var9;
         }
      } catch (MemoryException var12) {
         return -1;
      }
   }

   public static int convertSegmentFlagsToVMFlags(int var0) {
      byte var1 = 0;
      if ((var0 & 2) != 0) {
         var1 |= 1;
      }

      if ((var0 & 1) != 0) {
         var1 |= 2;
      }

      if ((var0 & 4) != 0) {
         var1 |= 4;
      }

      return var1;
   }

   public static boolean readSafe(IVirtualMemory var0, long var1, byte[] var3) {
      return readBytes(var0, var1, var3, 0, var3.length);
   }

   public static boolean readByteSafe(IVirtualMemory var0, long var1, byte[] var3) {
      try {
         byte[] var4 = new byte[1];
         if (var0.read(var1, 1, var4, 0, true) == 1) {
            var3[0] = var4[0];
         }
      } catch (MemoryException var5) {
      }

      return false;
   }

   public static boolean readLEShortSafe(IVirtualMemory var0, long var1, short[] var3) {
      try {
         byte[] var4 = new byte[2];
         if (var0.read(var1, 2, var4, 0, true) == 2) {
            var3[0] = EndianUtil.littleEndianBytesToShort(var4);
            return true;
         }
      } catch (MemoryException var5) {
      }

      return false;
   }

   public static boolean readLEIntSafe(IVirtualMemory var0, long var1, int[] var3) {
      try {
         byte[] var4 = new byte[4];
         if (var0.read(var1, 4, var4, 0, true) == 4) {
            var3[0] = EndianUtil.littleEndianBytesToInt(var4);
            return true;
         }
      } catch (MemoryException var5) {
      }

      return false;
   }

   public static boolean readLELongSafe(IVirtualMemory var0, long var1, long[] var3) {
      try {
         byte[] var4 = new byte[8];
         if (var0.read(var1, 8, var4, 0, true) == 8) {
            var3[0] = EndianUtil.littleEndianBytesToLong(var4);
            return true;
         }
      } catch (MemoryException var5) {
      }

      return false;
   }

   public static boolean readBEShortSafe(IVirtualMemory var0, long var1, short[] var3) {
      try {
         byte[] var4 = new byte[2];
         if (var0.read(var1, 2, var4, 0, true) == 2) {
            var3[0] = EndianUtil.bigEndianBytesToShort(var4);
            return true;
         }
      } catch (MemoryException var5) {
      }

      return false;
   }

   public static boolean readBEIntSafe(IVirtualMemory var0, long var1, int[] var3) {
      try {
         byte[] var4 = new byte[4];
         if (var0.read(var1, 4, var4, 0, true) == 4) {
            var3[0] = EndianUtil.bigEndianBytesToInt(var4);
            return true;
         }
      } catch (MemoryException var5) {
      }

      return false;
   }

   public static boolean readBELongSafe(IVirtualMemory var0, long var1, long[] var3) {
      try {
         byte[] var4 = new byte[8];
         if (var0.read(var1, 8, var4, 0, true) == 8) {
            var3[0] = EndianUtil.bigEndianBytesToLong(var4);
            return true;
         }
      } catch (MemoryException var5) {
      }

      return false;
   }

   public static boolean writeSafe(IVirtualMemory var0, long var1, byte[] var3) {
      return writeBytes(var0, var1, var3, 0, var3.length);
   }

   public static boolean writeByteSafe(IVirtualMemory var0, long var1, byte var3) {
      try {
         return var0.write(var1, 1, new byte[]{var3}, 0, true) == 1;
      } catch (MemoryException var4) {
         return false;
      }
   }

   public static boolean writeLEShortSafe(IVirtualMemory var0, long var1, short var3) {
      try {
         return var0.write(var1, 2, EndianUtil.shortToLEBytes(var3), 0, true) == 2;
      } catch (MemoryException var4) {
         return false;
      }
   }

   public static boolean writeLEIntSafe(IVirtualMemory var0, long var1, int var3) {
      try {
         return var0.write(var1, 4, EndianUtil.intToLEBytes(var3), 0, true) == 4;
      } catch (MemoryException var4) {
         return false;
      }
   }

   public static boolean writeLELongSafe(IVirtualMemory var0, long var1, long var3) {
      try {
         return var0.write(var1, 8, EndianUtil.longToLEBytes(var3), 0, true) == 8;
      } catch (MemoryException var5) {
         return false;
      }
   }

   public static boolean writeBooleans(IVirtualMemory var0, long var1, boolean[] var3, int var4, int var5) {
      byte[] var6 = new byte[var5];
      int var7 = 0;

      for (int var8 = var4; var8 < var4 + var5; var8++) {
         boolean var9 = var3[var8];
         var6[var7++] = (byte)(var9 ? 1 : 0);
      }

      return writeSafe(var0, var1, var6);
   }

   public static boolean readBooleans(IVirtualMemory var0, long var1, boolean[] var3, int var4, int var5) {
      byte[] var6 = new byte[var5];
      if (!readSafe(var0, var1, var6)) {
         return false;
      } else {
         int var7 = 0;

         for (int var8 = var4; var8 < var4 + var5; var8++) {
            byte var9 = var6[var7++];
            var3[var8] = (var9 & 1) == 1;
         }

         return true;
      }
   }

   public static boolean writeBytes(IVirtualMemory var0, long var1, byte[] var3, int var4, int var5) {
      try {
         return var0.write(var1, var5, var3, var4, true) == var5;
      } catch (MemoryException var6) {
         return false;
      }
   }

   public static boolean readBytes(IVirtualMemory var0, long var1, byte[] var3, int var4, int var5) {
      try {
         return var0.read(var1, var5, var3, var4, true) == var5;
      } catch (MemoryException var6) {
         return false;
      }
   }

   public static boolean writeChars(IVirtualMemory var0, long var1, char[] var3, int var4, int var5) {
      boolean var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = true;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = false;
      }

      byte[] var7 = new byte[var5 * 2];
      int var8 = 0;

      for (int var9 = var4; var9 < var4 + var5; var9++) {
         char var10 = var3[var9];
         if (var6) {
            var7[var8++] = (byte)var10;
            var7[var8++] = (byte)(var10 >> '\b');
         } else {
            var7[var8++] = (byte)(var10 >> '\b');
            var7[var8++] = (byte)var10;
         }
      }

      return writeSafe(var0, var1, var7);
   }

   public static boolean readChars(IVirtualMemory var0, long var1, char[] var3, int var4, int var5) {
      ByteOrder var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = ByteOrder.LITTLE_ENDIAN;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = ByteOrder.BIG_ENDIAN;
      }

      byte[] var7 = new byte[var5 * 2];
      if (!readSafe(var0, var1, var7)) {
         return false;
      } else {
         byte var8 = 0;

         for (int var9 = var4; var9 < var4 + var5; var9++) {
            var3[var9] = (char)EndianUtil.bytesToShort(var7, var8, var6);
            var8 += 2;
         }

         return true;
      }
   }

   public static boolean writeShorts(IVirtualMemory var0, long var1, short[] var3, int var4, int var5) {
      boolean var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = true;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = false;
      }

      byte[] var7 = new byte[var5 * 2];
      int var8 = 0;

      for (int var9 = var4; var9 < var4 + var5; var9++) {
         short var10 = var3[var9];
         if (var6) {
            var7[var8++] = (byte)var10;
            var7[var8++] = (byte)(var10 >> 8);
         } else {
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)var10;
         }
      }

      return writeSafe(var0, var1, var7);
   }

   public static boolean readShorts(IVirtualMemory var0, long var1, short[] var3, int var4, int var5) {
      ByteOrder var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = ByteOrder.LITTLE_ENDIAN;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = ByteOrder.BIG_ENDIAN;
      }

      byte[] var7 = new byte[var5 * 2];
      if (!readSafe(var0, var1, var7)) {
         return false;
      } else {
         byte var8 = 0;

         for (int var9 = var4; var9 < var4 + var5; var9++) {
            var3[var9] = EndianUtil.bytesToShort(var7, var8, var6);
            var8 += 2;
         }

         return true;
      }
   }

   public static boolean writeInts(IVirtualMemory var0, long var1, int[] var3, int var4, int var5) {
      boolean var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = true;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = false;
      }

      byte[] var7 = new byte[var5 * 4];
      int var8 = 0;

      for (int var9 = var4; var9 < var4 + var5; var9++) {
         int var10 = var3[var9];
         if (var6) {
            var7[var8++] = (byte)var10;
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 24);
         } else {
            var7[var8++] = (byte)(var10 >> 24);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)var10;
         }
      }

      return writeSafe(var0, var1, var7);
   }

   public static boolean readInts(IVirtualMemory var0, long var1, int[] var3, int var4, int var5) {
      ByteOrder var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = ByteOrder.LITTLE_ENDIAN;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = ByteOrder.BIG_ENDIAN;
      }

      byte[] var7 = new byte[var5 * 4];
      if (!readSafe(var0, var1, var7)) {
         return false;
      } else {
         byte var8 = 0;

         for (int var9 = var4; var9 < var4 + var5; var9++) {
            var3[var9] = EndianUtil.bytesToInt(var7, var8, var6);
            var8 += 4;
         }

         return true;
      }
   }

   public static boolean writeLongs(IVirtualMemory var0, long var1, long[] var3, int var4, int var5) {
      boolean var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = true;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = false;
      }

      byte[] var7 = new byte[var5 * 8];
      int var8 = 0;

      for (int var9 = var4; var9 < var4 + var5; var9++) {
         long var10 = var3[var9];
         if (var6) {
            var7[var8++] = (byte)var10;
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 24);
            var7[var8++] = (byte)(var10 >> 32);
            var7[var8++] = (byte)(var10 >> 40);
            var7[var8++] = (byte)(var10 >> 48);
            var7[var8++] = (byte)(var10 >> 56);
         } else {
            var7[var8++] = (byte)(var10 >> 56);
            var7[var8++] = (byte)(var10 >> 48);
            var7[var8++] = (byte)(var10 >> 40);
            var7[var8++] = (byte)(var10 >> 32);
            var7[var8++] = (byte)(var10 >> 24);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)var10;
         }
      }

      return writeSafe(var0, var1, var7);
   }

   public static boolean readLongs(IVirtualMemory var0, long var1, long[] var3, int var4, int var5) {
      ByteOrder var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = ByteOrder.LITTLE_ENDIAN;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = ByteOrder.BIG_ENDIAN;
      }

      byte[] var7 = new byte[var5 * 8];
      if (!readSafe(var0, var1, var7)) {
         return false;
      } else {
         byte var8 = 0;

         for (int var9 = var4; var9 < var4 + var5; var9++) {
            var3[var9] = EndianUtil.bytesToLong(var7, var8, var6);
            var8 += 8;
         }

         return true;
      }
   }

   public static boolean writeFloats(IVirtualMemory var0, long var1, float[] var3, int var4, int var5) {
      boolean var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = true;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = false;
      }

      byte[] var7 = new byte[var5 * 4];
      int var8 = 0;

      for (int var9 = var4; var9 < var4 + var5; var9++) {
         int var10 = Float.floatToIntBits(var3[var9]);
         if (var6) {
            var7[var8++] = (byte)var10;
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 24);
         } else {
            var7[var8++] = (byte)(var10 >> 24);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)var10;
         }
      }

      return writeSafe(var0, var1, var7);
   }

   public static boolean readFloats(IVirtualMemory var0, long var1, float[] var3, int var4, int var5) {
      ByteOrder var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = ByteOrder.LITTLE_ENDIAN;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = ByteOrder.BIG_ENDIAN;
      }

      byte[] var7 = new byte[var5 * 4];
      if (!readSafe(var0, var1, var7)) {
         return false;
      } else {
         byte var8 = 0;

         for (int var9 = var4; var9 < var4 + var5; var9++) {
            var3[var9] = Float.intBitsToFloat(EndianUtil.bytesToInt(var7, var8, var6));
            var8 += 4;
         }

         return true;
      }
   }

   public static boolean writeDoubles(IVirtualMemory var0, long var1, double[] var3, int var4, int var5) {
      boolean var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = true;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = false;
      }

      byte[] var7 = new byte[var5 * 8];
      int var8 = 0;

      for (int var9 = var4; var9 < var4 + var5; var9++) {
         long var10 = Double.doubleToLongBits(var3[var9]);
         if (var6) {
            var7[var8++] = (byte)var10;
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 24);
            var7[var8++] = (byte)(var10 >> 32);
            var7[var8++] = (byte)(var10 >> 40);
            var7[var8++] = (byte)(var10 >> 48);
            var7[var8++] = (byte)(var10 >> 56);
         } else {
            var7[var8++] = (byte)(var10 >> 56);
            var7[var8++] = (byte)(var10 >> 48);
            var7[var8++] = (byte)(var10 >> 40);
            var7[var8++] = (byte)(var10 >> 32);
            var7[var8++] = (byte)(var10 >> 24);
            var7[var8++] = (byte)(var10 >> 16);
            var7[var8++] = (byte)(var10 >> 8);
            var7[var8++] = (byte)var10;
         }
      }

      return writeSafe(var0, var1, var7);
   }

   public static boolean readDoubles(IVirtualMemory var0, long var1, double[] var3, int var4, int var5) {
      ByteOrder var6;
      if (var0.getStandardEndianess().isLittle()) {
         var6 = ByteOrder.LITTLE_ENDIAN;
      } else {
         if (!var0.getStandardEndianess().isBig()) {
            return false;
         }

         var6 = ByteOrder.BIG_ENDIAN;
      }

      byte[] var7 = new byte[var5 * 8];
      if (!readSafe(var0, var1, var7)) {
         return false;
      } else {
         byte var8 = 0;

         for (int var9 = var4; var9 < var4 + var5; var9++) {
            var3[var9] = Double.longBitsToDouble(EndianUtil.bytesToLong(var7, var8, var6));
            var8 += 8;
         }

         return true;
      }
   }

   public static String readNullTerminatedStringSafe(IVirtualMemory var0, long var1, int var3) {
      StringBuilder var4 = new StringBuilder();
      int var5 = 0;
      long var6 = var1;

      try {
         while (var5 < var3) {
            byte var8 = var0.readByte(var6);
            if (var8 == 0) {
               break;
            }

            var4.append((char)var8);
            var5++;
            var6++;
         }
      } catch (MemoryException var9) {
         return null;
      }

      return var5 == var3 ? null : var4.toString();
   }

   public static long findByte(IVirtualMemory var0, long var1, long var3, int var5) {
      byte[] var6 = new byte[]{(byte)var5};
      return findBytes(var0, false, var1, var3, var6, null);
   }

   public static long findBytes(IVirtualMemory var0, boolean var1, long var2, long var4, byte[] var6, byte[] var7) {
      if (var6.length == 0) {
         return var2;
      } else {
         long var8 = var0.getPageSize();
         long var10 = ~(var8 - 1L);
         if (var6.length > var8) {
            return -1L;
         } else {
            boolean var12 = Longs.compareUnsigned(var2, var4) > 0;
            long var13 = var2 & var10;
            long var15 = var2 - var13;
            int var17 = var12 ? -1 : 1;
            TreeSet var19 = new TreeSet(var0.getAllocatedPageBases());
            Long var18;
            if (var12) {
               var18 = (Long)var19.floor(var13);
            } else {
               var18 = (Long)var19.ceiling(var13);
            }

            if (var18 == null) {
               return -1L;
            } else {
               var13 = var18;
               ArrayList var20 = new ArrayList(var19);
               int var21 = Collections.binarySearch(var20, var13);

               for (byte[] var22 = new byte[(int)var8 + var6.length - 1]; var21 >= 0 && var21 < var20.size(); var21 += var17) {
                  var13 = (Long)var20.get(var21);
                  if (!var12) {
                     if (Longs.compareUnsigned(var13, var4) > 0) {
                        return -1L;
                     }
                  } else if (Longs.compareUnsigned(var13 + var8, var4) < 0) {
                     return -1L;
                  }

                  int var23 = readBytesSafe(var0, var13, var22.length, var22, 0, var1);
                  if (var23 > 0) {
                     if (!var12) {
                        int var24 = Bytes.indexOf(var22, (int)var15, var23, var6, 0, var6.length, var7);
                        if (var24 >= 0) {
                           return var13 + var24;
                        }

                        var15 = 0L;
                     } else {
                        int var27 = Bytes.lastIndexOf(var22, (int)var15, var6, var7);
                        if (var27 >= 0) {
                           return var13 + var27;
                        }

                        var15 = (int)var8;
                     }
                  }
               }

               return -1L;
            }
         }
      }
   }

   public static Long readAsLongSafe(IVirtualMemory var0, long var1, int var3) {
      return readAsLongSafe(var0, var0.getStandardEndianess(), var1, var3);
   }

   public static Long readAsLongSafe(IVirtualMemory var0, Endianness var1, long var2, int var4) {
      if (var4 <= 0) {
         return null;
      } else {
         byte[] var5 = new byte[var4];
         int var6 = 0;

         try {
            var6 = var0.read(var2, var4, var5, 0, true);
         } catch (MemoryException var7) {
         }

         if (var6 != var4) {
            return null;
         } else {
            switch (var4) {
               case 1:
                  return (long)var5[0];
               case 2:
                  return (long)EndianUtil.bytesToShort(var5, 0, var1.toByteOrder());
               case 3:
               case 5:
               case 6:
               case 7:
               default:
                  return null;
               case 4:
                  return (long)EndianUtil.bytesToInt(var5, 0, var1.toByteOrder());
               case 8:
                  return EndianUtil.bytesToLong(var5, 0, var1.toByteOrder());
            }
         }
      }
   }

   public static BigInteger readAsBigIntegerSafe(IVirtualMemory var0, long var1) {
      return readAsBigIntegerSafe(var0, var0.getStandardEndianess(), var1);
   }

   public static BigInteger readAsBigIntegerSafe(IVirtualMemory var0, Endianness var1, long var2) {
      byte var4 = 16;
      byte[] var5 = new byte[var4];
      int var6 = 0;

      try {
         var6 = var0.read(var2, var4, var5, 0, true);
      } catch (MemoryException var7) {
      }

      if (var6 != var4) {
         return null;
      } else {
         if (var1 == Endianness.LITTLE_ENDIAN) {
            EndianUtil.swap(var5, 0, 16);
         }

         return new BigInteger(var5);
      }
   }

   public static Long readAsUnsignedLongSafe(IVirtualMemory var0, long var1, int var3) {
      return readAsUnsignedLongSafe(var0, var0.getStandardEndianess(), var1, var3);
   }

   public static Long readAsUnsignedLongSafe(IVirtualMemory var0, Endianness var1, long var2, int var4) {
      if (var4 <= 0) {
         return null;
      } else {
         byte[] var5 = new byte[var4];
         int var6 = 0;

         try {
            var6 = var0.read(var2, var4, var5, 0, true);
         } catch (MemoryException var7) {
         }

         if (var6 != var4) {
            return null;
         } else {
            switch (var4) {
               case 1:
                  return var5[0] & 255L;
               case 2:
                  return EndianUtil.bytesToShort(var5, 0, var1.toByteOrder()) & 65535L;
               case 3:
               case 5:
               case 6:
               case 7:
               default:
                  return null;
               case 4:
                  return EndianUtil.bytesToInt(var5, 0, var1.toByteOrder()) & 4294967295L;
               case 8:
                  return EndianUtil.bytesToLong(var5, 0, var1.toByteOrder());
            }
         }
      }
   }

   public static byte[] dump(IVirtualMemory var0, long var1, long var3) {
      int var5 = (int)(var3 - var1);
      byte[] var6 = new byte[var5];
      dump(var0, var1, var3, var6, 0);
      return var6;
   }

   public static void dump(IVirtualMemory var0, long var1, long var3, byte[] var5, int var6) {
      int var7 = var0.getPageSize();
      long var8 = var1;
      int var10 = var6;
      long var11 = var0.roundToSize(var1);
      if (Long.compareUnsigned(var11, var1) > 0) {
         int var13 = (int)(var11 - var1);
         long var14 = var0.roundToPage(var1);
         if (var0.isAllocatedPage(var14)) {
            try {
               var0.read(var8, var13, var5, var10, true);
            } catch (MemoryException var19) {
               throw new RuntimeException(var19);
            }
         }

         var10 = var6 + var13;
         var8 = var1 + var13;
      }

      long var20;
      for (var20 = var0.roundToPage(var3); Long.compareUnsigned(var8, var20) < 0; var8 += var7) {
         if (var0.isAllocatedPage(var8)) {
            try {
               var0.read(var8, var7, var5, var10, true);
            } catch (MemoryException var18) {
               throw new RuntimeException(var18);
            }
         }

         var10 += var7;
      }

      if (Long.compareUnsigned(var20, var3) < 0) {
         int var15 = (int)(var3 - var20);
         if (var0.isAllocatedPage(var20)) {
            try {
               var0.read(var8, var15, var5, var10, true);
            } catch (MemoryException var17) {
               throw new RuntimeException(var17);
            }
         }
      }
   }

   public static void dumpToImageFiles(IVirtualMemory var0, File var1) throws IOException {
      dumpToImageFiles(var0, var1, null, null);
   }

   public static void dumpToImageFiles(IVirtualMemory var0, File var1, String var2, String var3) throws IOException {
      IO.createDirectory(var1);
      if (var2 == null) {
         var2 = "";
      }

      if (var3 == null) {
         var3 = "";
      }

      int var4 = var0.getPageSize();
      ArrayList var5 = new ArrayList(var0.getAllocatedPageBases());
      int var6 = 0;

      while (var6 < var5.size()) {
         long var7 = (Long)var5.get(var6);
         var6++;

         long var9;
         for (var9 = var7 + var4; var5.contains(var9); var6++) {
            var9 += var4;
         }

         int var11 = (int)(var9 - var7);
         byte[] var12 = new byte[var11];

         try {
            var0.read(var7, var12.length, var12, 0, true);
         } catch (MemoryException var14) {
            throw new IOException(var14);
         }

         IO.writeFile(new File(var1, Strings.ff("%s%X%s", var2, var7, var3)), var12);
      }
   }
}
