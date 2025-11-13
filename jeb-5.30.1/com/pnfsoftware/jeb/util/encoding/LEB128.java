package com.pnfsoftware.jeb.util.encoding;

import com.pnfsoftware.jeb.util.math.MathUtil;

public class LEB128 {
   public static LEB128.Value read_int7(byte[] var0, int var1) throws BadEncodingException {
      LEB128.Value var2 = readSLEB128(var0, var1);
      if (var2.bytelen > 1) {
         throw new BadEncodingException("Invalid varint7", var1);
      } else {
         var2.value = MathUtil.signExtend(var2.value, 7);
         return var2;
      }
   }

   public static LEB128.Value read_int16(byte[] var0, int var1) throws BadEncodingException {
      LEB128.Value var2 = readSLEB128(var0, var1);
      if (var2.bytelen > 3) {
         throw new BadEncodingException("Invalid varint16", var1);
      } else {
         var2.value = MathUtil.signExtend(var2.value, 16);
         return var2;
      }
   }

   public static LEB128.Value read_int32(byte[] var0, int var1) throws BadEncodingException {
      LEB128.Value var2 = readSLEB128(var0, var1);
      if (var2.bytelen > 5) {
         throw new BadEncodingException("Invalid varint32", var1);
      } else {
         var2.value = MathUtil.signExtend(var2.value, 32);
         return var2;
      }
   }

   public static LEB128.Value read_int64(byte[] var0, int var1) throws BadEncodingException {
      return readSLEB128(var0, var1);
   }

   public static LEB128.Value read_uint1(byte[] var0, int var1) throws BadEncodingException {
      LEB128.Value var2 = readULEB128(var0, var1);
      if (var2.bytelen <= 1 && var2.value <= 1L) {
         return var2;
      } else {
         throw new BadEncodingException("Invalid varuint1", var1);
      }
   }

   public static LEB128.Value read_uint7(byte[] var0, int var1) throws BadEncodingException {
      LEB128.Value var2 = readULEB128(var0, var1);
      if (var2.bytelen <= 1 && var2.value <= 127L) {
         return var2;
      } else {
         throw new BadEncodingException("Invalid varuint7", var1);
      }
   }

   public static LEB128.Value read_uint16(byte[] var0, int var1) throws BadEncodingException {
      LEB128.Value var2 = readULEB128(var0, var1);
      if (var2.bytelen <= 3 && var2.value <= 65535L) {
         return var2;
      } else {
         throw new BadEncodingException("Invalid varuint16", var1);
      }
   }

   public static LEB128.Value read_uint32(byte[] var0, int var1) throws BadEncodingException {
      LEB128.Value var2 = readULEB128(var0, var1);
      if (var2.bytelen <= 5 && var2.value <= 4294967295L) {
         return var2;
      } else {
         throw new BadEncodingException("Invalid varuint32", var1);
      }
   }

   public static LEB128.Value read_uint64(byte[] var0, int var1) throws BadEncodingException {
      return readULEB128(var0, var1);
   }

   private static LEB128.Value readULEB128(byte[] var0, int var1) throws BadEncodingException {
      long var2 = 0L;
      byte var4 = -1;
      byte var5 = 0;

      int var6;
      for (var6 = 0; var4 < 0; var6++) {
         if (var6 == 10) {
            throw new BadEncodingException("Illegal ULEB128-encoded integer", var1 + var6);
         }

         var4 = var0[var1 + var6];
         var2 |= (long)(var4 & 127) << var5;
         var5 += 7;
      }

      return new LEB128.Value(var2, var6);
   }

   private static LEB128.Value readSLEB128(byte[] var0, int var1) throws BadEncodingException {
      long var2 = 0L;
      byte var4 = -1;
      int var5 = 0;

      for (byte var6 = 0; var4 < 0; var5++) {
         if (var5 == 10) {
            throw new BadEncodingException("Illegal SLEB128-encoded integer", var1 + var5);
         }

         var4 = var0[var1 + var5];
         var2 |= (long)(var4 & 127) << var6;
         var6 += 7;
      }

      if ((var4 & 64) != 0) {
         if (var5 == 1) {
            var2 |= -128L;
         } else if (var5 == 2) {
            var2 |= -16384L;
         } else if (var5 == 3) {
            var2 |= -2097152L;
         } else if (var5 == 4) {
            var2 |= -268435456L;
         } else if (var5 == 5) {
            var2 |= -34359738368L;
         } else if (var5 == 6) {
            var2 |= -4398046511104L;
         } else if (var5 == 7) {
            var2 |= -562949953421312L;
         } else if (var5 == 8) {
            var2 |= -72057594037927936L;
         } else if (var5 == 9) {
            var2 |= Long.MIN_VALUE;
         }
      }

      return new LEB128.Value(var2, var5);
   }

   public static class Value {
      long value;
      int bytelen;

      public Value(long var1, int var3) {
         this.value = var1;
         this.bytelen = var3;
      }

      public long get() {
         return this.value;
      }

      public int getEncodingLength() {
         return this.bytelen;
      }
   }
}
