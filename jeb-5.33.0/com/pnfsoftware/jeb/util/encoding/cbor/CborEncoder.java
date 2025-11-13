package com.pnfsoftware.jeb.util.encoding.cbor;

import java.io.IOException;
import java.io.OutputStream;

public class CborEncoder {
   private static final int NEG_INT_MASK = 32;
   private final OutputStream m_os;

   public CborEncoder(OutputStream var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("OutputStream cannot be null!");
      } else {
         this.m_os = var1;
      }
   }

   static int halfPrecisionToRawIntBits(float var0) {
      int var1 = Float.floatToIntBits(var0);
      int var2 = var1 >>> 16 & 32768;
      int var3 = (var1 & 2147483647) + 4096;
      if (var3 >= 1199570944) {
         if ((var1 & 2147483647) >= 1199570944) {
            return var3 < 2139095040 ? var2 | 31744 : var2 | 31744 | (var1 & 8388607) >>> 13;
         } else {
            return var2 | 31743;
         }
      } else if (var3 >= 947912704) {
         return var2 | var3 - 939524096 >>> 13;
      } else if (var3 < 855638016) {
         return var2;
      } else {
         var3 = (var1 & 2147483647) >>> 23;
         return var2 | (var1 & 8388607 | 8388608) + (8388608 >>> var3 - 102) >>> 126 - var3;
      }
   }

   public void writeArrayStart() throws IOException {
      this.writeSimpleType(4, 31);
   }

   public void writeArrayStart(int var1) throws IOException {
      if (var1 < 0) {
         throw new IllegalArgumentException("Invalid array-length!");
      } else {
         this.writeType(4, var1);
      }
   }

   public void writeBoolean(boolean var1) throws IOException {
      this.writeSimpleType(7, var1 ? 21 : 20);
   }

   public void writeBreak() throws IOException {
      this.writeSimpleType(7, 31);
   }

   public void writeByteString(byte[] var1) throws IOException {
      this.writeString(2, var1);
   }

   public void writeByteStringStart() throws IOException {
      this.writeSimpleType(2, 31);
   }

   public void writeDouble(double var1) throws IOException {
      this.writeUInt64(224, Double.doubleToRawLongBits(var1));
   }

   public void writeFloat(float var1) throws IOException {
      this.writeUInt32(224, Float.floatToRawIntBits(var1));
   }

   public void writeHalfPrecisionFloat(float var1) throws IOException {
      this.writeUInt16(224, halfPrecisionToRawIntBits(var1));
   }

   public void writeInt(long var1) throws IOException {
      long var3 = var1 >> 63;
      int var5 = (int)(var3 & 32L);
      var1 = var3 ^ var1;
      this.writeUInt(var5, var1);
   }

   public void writeInt16(int var1) throws IOException {
      int var2 = var1 >> 31;
      int var3 = var2 & 32;
      this.writeUInt16(var3, (var2 ^ var1) & 65535);
   }

   public void writeInt32(long var1) throws IOException {
      long var3 = var1 >> 63;
      int var5 = (int)(var3 & 32L);
      this.writeUInt32(var5, (int)((var3 ^ var1) & 4294967295L));
   }

   public void writeInt64(long var1) throws IOException {
      long var3 = var1 >> 63;
      int var5 = (int)(var3 & 32L);
      this.writeUInt64(var5, var3 ^ var1);
   }

   public void writeInt8(int var1) throws IOException {
      int var2 = var1 >> 31;
      int var3 = var2 & 32;
      this.writeUInt8(var3, (var2 ^ var1) & 0xFF);
   }

   public void writeMapStart() throws IOException {
      this.writeSimpleType(5, 31);
   }

   public void writeMapStart(int var1) throws IOException {
      if (var1 < 0) {
         throw new IllegalArgumentException("Invalid length of map!");
      } else {
         this.writeType(5, var1);
      }
   }

   public void writeNull() throws IOException {
      this.writeSimpleType(7, 22);
   }

   public void writeSimpleValue(byte var1) throws IOException {
      int var2 = var1 & 255;
      this.writeType(7, var2);
   }

   public void writeSmallInt(int var1) throws IOException {
      int var2 = var1 >> 31;
      int var3 = var2 & 32;
      var1 = Math.min(23, var2 ^ var1);
      this.m_os.write(var3 | var1);
   }

   public void writeTag(long var1) throws IOException {
      if (var1 < 0L) {
         throw new IllegalArgumentException("Invalid tag specification, cannot be negative!");
      } else {
         this.writeType(6, var1);
      }
   }

   public void writeTextString(String var1) throws IOException {
      this.writeString(3, var1 == null ? null : var1.getBytes("UTF-8"));
   }

   public void writeTextStringStart() throws IOException {
      this.writeSimpleType(3, 31);
   }

   public void writeUndefined() throws IOException {
      this.writeSimpleType(7, 23);
   }

   protected void writeSimpleType(int var1, int var2) throws IOException {
      this.m_os.write(var1 << 5 | var2 & 31);
   }

   protected void writeString(int var1, byte[] var2) throws IOException {
      int var3 = var2 == null ? 0 : var2.length;
      this.writeType(var1, var3);

      for (int var4 = 0; var4 < var3; var4++) {
         this.m_os.write(var2[var4]);
      }
   }

   protected void writeType(int var1, long var2) throws IOException {
      this.writeUInt(var1 << 5, var2);
   }

   protected void writeUInt(int var1, long var2) throws IOException {
      if (var2 < 24L) {
         this.m_os.write((int)(var1 | var2));
      } else if (var2 < 256L) {
         this.writeUInt8(var1, (int)var2);
      } else if (var2 < 65536L) {
         this.writeUInt16(var1, (int)var2);
      } else if (var2 < 4294967296L) {
         this.writeUInt32(var1, (int)var2);
      } else {
         this.writeUInt64(var1, var2);
      }
   }

   protected void writeUInt16(int var1, int var2) throws IOException {
      this.m_os.write(var1 | 25);
      this.m_os.write(var2 >> 8);
      this.m_os.write(var2 & 0xFF);
   }

   protected void writeUInt32(int var1, int var2) throws IOException {
      this.m_os.write(var1 | 26);
      this.m_os.write(var2 >> 24);
      this.m_os.write(var2 >> 16);
      this.m_os.write(var2 >> 8);
      this.m_os.write(var2 & 0xFF);
   }

   protected void writeUInt64(int var1, long var2) throws IOException {
      this.m_os.write(var1 | 27);
      this.m_os.write((int)(var2 >> 56));
      this.m_os.write((int)(var2 >> 48));
      this.m_os.write((int)(var2 >> 40));
      this.m_os.write((int)(var2 >> 32));
      this.m_os.write((int)(var2 >> 24));
      this.m_os.write((int)(var2 >> 16));
      this.m_os.write((int)(var2 >> 8));
      this.m_os.write((int)(var2 & 255L));
   }

   protected void writeUInt8(int var1, int var2) throws IOException {
      this.m_os.write(var1 | 24);
      this.m_os.write(var2 & 0xFF);
   }
}
