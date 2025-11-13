package com.pnfsoftware.jeb.core.units.code.asm.processor.memory;

import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.math.MathUtil;

public class EncodedMemoryAreaUtil {
   public static int signExtendInt(byte[] var0, IEncodedMemoryArea var1) {
      int var2 = var1.getLength();
      int var3 = zeroExtend(var0, var1);
      return MathUtil.signExtend32(var3, var2);
   }

   public static long signExtendLong(byte[] var0, IEncodedMemoryArea var1) {
      int var2 = var1.getLength();
      long var3 = zeroExtendLong(var0, var1);
      return MathUtil.signExtend(var3, var2);
   }

   public static int zeroExtend(byte[] var0, IEncodedMemoryArea var1) {
      byte var2 = 0;
      return var1 == null ? var2 : (int)var1.decode(var0);
   }

   public static long zeroExtendLong(byte[] var0, IEncodedMemoryArea... var1) {
      long var2 = 0L;
      if (var1 != null && var1.length != 0) {
         for (IEncodedMemoryArea var7 : var1) {
            var2 = var2 << var7.getLength() | var7.decode(var0);
         }

         return var2;
      } else {
         return var2;
      }
   }

   public static long zeroExtend(byte[] var0, int var1, int var2) {
      long var3 = getValue(var0, var1);
      return MathUtil.zeroExtend(var3, var2);
   }

   private static long getValue(byte[] var0, int var1) {
      return getValue(var0) >>> var1;
   }

   private static long getValue(byte[] var0) {
      if (var0.length == 2) {
         return EndianUtil.bigEndianBytesToShort(var0) & -1;
      } else if (var0.length == 4) {
         return EndianUtil.bigEndianBytesToInt(var0) & -1;
      } else {
         return var0.length == 8 ? EndianUtil.bigEndianBytesToLong(var0) : 0L;
      }
   }
}
