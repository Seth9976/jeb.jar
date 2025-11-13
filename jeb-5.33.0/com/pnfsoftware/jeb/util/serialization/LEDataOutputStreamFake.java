package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

class LEDataOutputStreamFake extends LEDataOutputStream {
   int cnt;

   public LEDataOutputStreamFake() {
      super(new ByteArrayOutputStream());
   }

   @Override
   public void close() throws IOException {
   }

   @Override
   public int size() {
      return this.cnt;
   }

   @Override
   public void write(int var1) throws IOException {
      this.cnt++;
   }

   @Override
   public void write(byte[] var1) throws IOException {
      this.cnt += var1.length;
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws IOException {
      this.cnt += var3;
   }

   @Override
   public void writeBoolean(boolean var1) throws IOException {
      this.cnt++;
   }

   @Override
   public void writeByte(int var1) throws IOException {
      this.cnt++;
   }

   @Override
   public void writeBytes(String var1) throws IOException {
      this.cnt = this.cnt + var1.length();
   }

   @Override
   public void writeUTF(String var1) throws IOException {
      this.cnt = this.cnt + var1.length();
   }

   @Override
   public void writeChar(int var1) throws IOException {
      this.cnt += 2;
   }

   @Override
   public void writeChars(String var1) throws IOException {
      this.cnt = this.cnt + 2 * var1.length();
   }

   @Override
   public void writeInt(int var1) throws IOException {
      this.cnt += 4;
   }

   @Override
   public void writeShort(int var1) throws IOException {
      this.cnt += 2;
   }

   @Override
   public void writeLong(long var1) throws IOException {
      this.cnt += 8;
   }

   @Override
   public void writeFloat(float var1) throws IOException {
      this.cnt += 4;
   }

   @Override
   public void writeDouble(double var1) throws IOException {
      this.cnt += 8;
   }

   @Override
   public void writeIntULEB128(int var1) throws IOException {
      this.cnt += 4;
   }

   @Override
   public void writeLongULEB128(long var1) throws IOException {
      this.cnt += 8;
   }
}
