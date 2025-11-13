package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.encoding.BadEncodingException;
import com.pnfsoftware.jeb.util.encoding.LEB128;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;

public class ByteArray {
   byte[] bytes;
   int index;
   int maxindex;

   public ByteArray(byte[] var1) {
      this(var1, 0);
   }

   public ByteArray(byte[] var1, int var2) {
      this(var1, var2, var1.length);
   }

   public ByteArray(byte[] var1, int var2, int var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null byte array");
      } else {
         this.bytes = var1;
         this.position(var2);
         this.maxPosition(var3);
      }
   }

   public ByteArray sub(int var1) {
      return new ByteArray(this.bytes, this.index, this.index + var1);
   }

   public ByteArray copy() {
      return this.copy(this.index);
   }

   public ByteArray copy(int var1) {
      return this.copy(var1, this.maxindex);
   }

   public ByteArray copy(int var1, int var2) {
      return new ByteArray(this.bytes, var1, var2);
   }

   public byte[] bytes() {
      return this.bytes;
   }

   public int position(int var1) {
      if (var1 >= 0 && var1 <= this.bytes.length) {
         int var2 = this.index;
         this.index = var1;
         return var2;
      } else {
         throw new IllegalArgumentException("Illegal position: " + var1);
      }
   }

   public int position() {
      return this.index;
   }

   public int maxPosition(int var1) {
      if (var1 >= 0 && var1 <= this.bytes.length) {
         int var2 = this.maxindex;
         this.maxindex = var1;
         return var2;
      } else {
         throw new IllegalArgumentException("Illegal max position: " + var1);
      }
   }

   public int maxPosition() {
      return this.maxindex;
   }

   public int available() {
      return this.maxindex - this.index;
   }

   private void updateIndex(int var1) {
      this.index += var1;
      if (this.index > this.maxindex) {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public void skip(int var1) {
      this.updateIndex(var1);
   }

   public byte get() {
      byte var1 = this.bytes[this.index];
      this.updateIndex(1);
      return var1;
   }

   public byte[] get(int var1) {
      byte[] var2 = Arrays.copyOfRange(this.bytes, this.index, this.index + var1);
      this.updateIndex(var1);
      return var2;
   }

   public int i8() {
      byte var1 = this.bytes[this.index];
      this.updateIndex(1);
      return var1;
   }

   public int u8() {
      int var1 = this.bytes[this.index] & 255;
      this.updateIndex(1);
      return var1;
   }

   public int i16() {
      short var1 = EndianUtil.littleEndianBytesToShort(this.bytes, this.index);
      this.updateIndex(2);
      return var1;
   }

   public int u16() {
      int var1 = EndianUtil.littleEndianBytesToShort(this.bytes, this.index) & '\uffff';
      this.updateIndex(2);
      return var1;
   }

   public int u31() {
      int var1 = EndianUtil.littleEndianBytesToInt(this.bytes, this.index);
      if (var1 < 0) {
         throw new BadEncodingException(Strings.ff("Expected a positive 4-byte signed integer, got 0x%X", var1), this.index);
      } else {
         this.updateIndex(4);
         return var1;
      }
   }

   public int i32() {
      int var1 = EndianUtil.littleEndianBytesToInt(this.bytes, this.index);
      this.updateIndex(4);
      return var1;
   }

   public long u32() {
      int var1 = EndianUtil.littleEndianBytesToInt(this.bytes, this.index);
      this.updateIndex(4);
      return var1 & 4294967295L;
   }

   public long u63() {
      long var1 = EndianUtil.littleEndianBytesToLong(this.bytes, this.index);
      if (var1 < 0L) {
         throw new BadEncodingException(Strings.ff("Expected a positive 8-byte signed integer, got 0x%X", var1), this.index);
      } else {
         this.updateIndex(8);
         return var1;
      }
   }

   public long i64() {
      long var1 = EndianUtil.littleEndianBytesToLong(this.bytes, this.index);
      this.updateIndex(8);
      return var1;
   }

   public int varu16() {
      LEB128.Value var1 = LEB128.read_uint16(this.bytes, this.index);
      this.updateIndex(var1.getEncodingLength());
      return (int)var1.get();
   }

   public int vari32() {
      LEB128.Value var1 = LEB128.read_int32(this.bytes, this.index);
      this.updateIndex(var1.getEncodingLength());
      return (int)var1.get();
   }

   public long varu32() {
      LEB128.Value var1 = LEB128.read_uint32(this.bytes, this.index);
      this.updateIndex(var1.getEncodingLength());
      return var1.get();
   }

   public long vari64() {
      LEB128.Value var1 = LEB128.read_int64(this.bytes, this.index);
      this.updateIndex(var1.getEncodingLength());
      return var1.get();
   }

   @Override
   public String toString() {
      return Strings.ff("pos:0x%X (max:0x%X)", this.index, this.maxindex);
   }
}
