package com.pnfsoftware.jeb.util.encoding.cbor;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class CborDecoder {
   protected final PushbackInputStream m_is;

   public CborDecoder(InputStream var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("InputStream cannot be null!");
      } else {
         this.m_is = var1 instanceof PushbackInputStream ? (PushbackInputStream)var1 : new PushbackInputStream(var1);
      }
   }

   private static void fail(String var0, Object... var1) throws IOException {
      throw new IOException(String.format(var0, var1));
   }

   private static String lengthToString(int var0) {
      return var0 < 0
         ? "no payload"
         : (var0 == 24 ? "one byte" : (var0 == 25 ? "two bytes" : (var0 == 26 ? "four bytes" : (var0 == 27 ? "eight bytes" : "(unknown)"))));
   }

   public CborType peekType() throws IOException {
      int var1 = this.m_is.read();
      if (var1 < 0) {
         return null;
      } else {
         this.m_is.unread(var1);
         return CborType.valueOf(var1);
      }
   }

   public long readArrayLength() throws IOException {
      return this.readMajorTypeWithSize(4);
   }

   public boolean readBoolean() throws IOException {
      int var1 = this.readMajorType(7);
      if (var1 != 20 && var1 != 21) {
         fail("Unexpected boolean value: %d!", var1);
      }

      return var1 == 21;
   }

   public Object readBreak() throws IOException {
      this.readMajorTypeExact(7, 31);
      return null;
   }

   public byte[] readByteString() throws IOException {
      long var1 = this.readMajorTypeWithSize(2);
      if (var1 < 0L) {
         fail("Infinite-length byte strings not supported!");
      }

      if (var1 > 2147483647L) {
         fail("String length too long!");
      }

      return this.readFully(new byte[(int)var1]);
   }

   public long readByteStringLength() throws IOException {
      return this.readMajorTypeWithSize(2);
   }

   public double readDouble() throws IOException {
      this.readMajorTypeExact(7, 27);
      return Double.longBitsToDouble(this.readUInt64());
   }

   public float readFloat() throws IOException {
      this.readMajorTypeExact(7, 26);
      return Float.intBitsToFloat((int)this.readUInt32());
   }

   public double readHalfPrecisionFloat() throws IOException {
      this.readMajorTypeExact(7, 25);
      int var1 = this.readUInt16();
      int var2 = var1 >> 10 & 31;
      int var3 = var1 & 1023;
      double var4;
      if (var2 == 0) {
         var4 = var3 * Math.pow(2.0, -24.0);
      } else if (var2 != 31) {
         var4 = (var3 + 1024) * Math.pow(2.0, var2 - 25);
      } else if (var3 != 0) {
         var4 = Double.NaN;
      } else {
         var4 = Double.POSITIVE_INFINITY;
      }

      return (var1 & 32768) == 0 ? var4 : -var4;
   }

   public long readInt() throws IOException {
      int var1 = this.m_is.read();
      long var2 = this.expectIntegerType(var1);
      return var2 ^ this.readUInt(var1 & 31, false);
   }

   public int readInt16() throws IOException {
      int var1 = this.m_is.read();
      long var2 = this.expectIntegerType(var1);
      return (int)(var2 ^ this.readUIntExact(25, var1 & 31));
   }

   public long readInt32() throws IOException {
      int var1 = this.m_is.read();
      long var2 = this.expectIntegerType(var1);
      return var2 ^ this.readUIntExact(26, var1 & 31);
   }

   public long readInt64() throws IOException {
      int var1 = this.m_is.read();
      long var2 = this.expectIntegerType(var1);
      return var2 ^ this.readUIntExact(27, var1 & 31);
   }

   public int readInt8() throws IOException {
      int var1 = this.m_is.read();
      long var2 = this.expectIntegerType(var1);
      return (int)(var2 ^ this.readUIntExact(24, var1 & 31));
   }

   public long readMapLength() throws IOException {
      return this.readMajorTypeWithSize(5);
   }

   public Object readNull() throws IOException {
      this.readMajorTypeExact(7, 22);
      return null;
   }

   public byte readSimpleValue() throws IOException {
      this.readMajorTypeExact(7, 24);
      return (byte)this.readUInt8();
   }

   public int readSmallInt() throws IOException {
      int var1 = this.m_is.read();
      long var2 = this.expectIntegerType(var1);
      return (int)(var2 ^ this.readUIntExact(-1, var1 & 31));
   }

   public long readTag() throws IOException {
      return this.readUInt(this.readMajorType(6), false);
   }

   public String readTextString() throws IOException {
      long var1 = this.readMajorTypeWithSize(3);
      if (var1 < 0L) {
         fail("Infinite-length text strings not supported!");
      }

      if (var1 > 2147483647L) {
         fail("String length too long!");
      }

      return new String(this.readFully(new byte[(int)var1]), "UTF-8");
   }

   public long readTextStringLength() throws IOException {
      return this.readMajorTypeWithSize(3);
   }

   public Object readUndefined() throws IOException {
      this.readMajorTypeExact(7, 23);
      return null;
   }

   protected long expectIntegerType(int var1) throws IOException {
      int var2 = (var1 & 0xFF) >>> 5;
      if (var2 != 0 && var2 != 1) {
         fail("Unexpected type: %s, expected type %s or %s!", CborType.getName(var2), CborType.getName(0), CborType.getName(1));
      }

      return -var2;
   }

   protected int readMajorType(int var1) throws IOException {
      int var2 = this.m_is.read();
      if (var1 != (var2 >>> 5 & 7)) {
         fail("Unexpected type: %s, expected: %s!", CborType.getName(var2), CborType.getName(var1));
      }

      return var2 & 31;
   }

   protected void readMajorTypeExact(int var1, int var2) throws IOException {
      int var3 = this.readMajorType(var1);
      if ((var3 ^ var2) != 0) {
         fail("Unexpected subtype: %d, expected: %d!", var3, var2);
      }
   }

   protected long readMajorTypeWithSize(int var1) throws IOException {
      return this.readUInt(this.readMajorType(var1), true);
   }

   protected long readUInt(int var1, boolean var2) throws IOException {
      long var3 = -1L;
      if (var1 < 24) {
         var3 = var1;
      } else if (var1 == 24) {
         var3 = this.readUInt8();
      } else if (var1 == 25) {
         var3 = this.readUInt16();
      } else if (var1 == 26) {
         var3 = this.readUInt32();
      } else if (var1 == 27) {
         var3 = this.readUInt64();
      } else if (var2 && var1 == 31) {
         return -1L;
      }

      if (var3 < 0L) {
         fail("Not well-formed CBOR integer found, invalid length: %d!", var3);
      }

      return var3;
   }

   protected int readUInt16() throws IOException {
      byte[] var1 = this.readFully(new byte[2]);
      return (var1[0] & 0xFF) << 8 | var1[1] & 0xFF;
   }

   protected long readUInt32() throws IOException {
      byte[] var1 = this.readFully(new byte[4]);
      return ((var1[0] & 255) << 24 | (var1[1] & 255) << 16 | (var1[2] & 255) << 8 | var1[3] & 255) & 4294967295L;
   }

   protected long readUInt64() throws IOException {
      byte[] var1 = this.readFully(new byte[8]);
      return (var1[0] & 255L) << 56
         | (var1[1] & 255L) << 48
         | (var1[2] & 255L) << 40
         | (var1[3] & 255L) << 32
         | (var1[4] & 255L) << 24
         | (var1[5] & 255L) << 16
         | (var1[6] & 255L) << 8
         | var1[7] & 255L;
   }

   protected int readUInt8() throws IOException {
      return this.m_is.read() & 0xFF;
   }

   protected long readUIntExact(int var1, int var2) throws IOException {
      if (var1 == -1 && var2 >= 24 || var1 >= 0 && var2 != var1) {
         fail("Unexpected payload/length! Expected %s, but got %s.", lengthToString(var1), lengthToString(var2));
      }

      return this.readUInt(var2, false);
   }

   private byte[] readFully(byte[] var1) throws IOException {
      int var2 = var1.length;
      int var3 = 0;
      byte var4 = 0;

      while (var3 < var2) {
         int var5 = this.m_is.read(var1, var4 + var3, var2 - var3);
         if (var5 < 0) {
            throw new EOFException();
         }

         var3 += var5;
      }

      return var1;
   }
}
