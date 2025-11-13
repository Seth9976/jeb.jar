package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.encoding.IntegerLEB128;
import com.pnfsoftware.jeb.util.encoding.LongLEB128;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class LEDataInputStream extends FilterInputStream implements DataInput {
   private final byte[] tmp = new byte[8];

   public LEDataInputStream(InputStream var1) {
      super(new DataInputStream(var1));
   }

   @Override
   public void close() throws IOException {
      this.in.close();
   }

   @Override
   public int read(byte[] var1) throws IOException {
      return this.in.read(var1);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.in.read(var1, var2, var3);
   }

   @Override
   public boolean readBoolean() throws IOException {
      return ((DataInputStream)this.in).readBoolean();
   }

   @Override
   public byte readByte() throws IOException {
      return ((DataInputStream)this.in).readByte();
   }

   @Override
   public char readChar() throws IOException {
      ((DataInputStream)this.in).readFully(this.tmp, 0, 2);
      return (char)((this.tmp[1] & 255) << 8 | this.tmp[0] & 255);
   }

   @Override
   public double readDouble() throws IOException {
      return Double.longBitsToDouble(this.readLong());
   }

   @Override
   public float readFloat() throws IOException {
      return Float.intBitsToFloat(this.readInt());
   }

   @Override
   public void readFully(byte[] var1) throws IOException {
      ((DataInputStream)this.in).readFully(var1, 0, var1.length);
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) throws IOException {
      ((DataInputStream)this.in).readFully(var1, var2, var3);
   }

   @Override
   public int readInt() throws IOException {
      ((DataInputStream)this.in).readFully(this.tmp, 0, 4);
      return this.tmp[3] << 24 | (this.tmp[2] & 0xFF) << 16 | (this.tmp[1] & 0xFF) << 8 | this.tmp[0] & 0xFF;
   }

   @Deprecated
   @Override
   public String readLine() throws IOException {
      return ((DataInputStream)this.in).readLine();
   }

   @Override
   public long readLong() throws IOException {
      ((DataInputStream)this.in).readFully(this.tmp, 0, 8);
      return (long)this.tmp[7] << 56
         | (long)(this.tmp[6] & 255) << 48
         | (long)(this.tmp[5] & 255) << 40
         | (long)(this.tmp[4] & 255) << 32
         | (long)(this.tmp[3] & 255) << 24
         | (long)(this.tmp[2] & 255) << 16
         | (long)(this.tmp[1] & 255) << 8
         | this.tmp[0] & 255;
   }

   @Override
   public short readShort() throws IOException {
      ((DataInputStream)this.in).readFully(this.tmp, 0, 2);
      return (short)((this.tmp[1] & 255) << 8 | this.tmp[0] & 255);
   }

   @Override
   public String readUTF() throws IOException {
      return ((DataInputStream)this.in).readUTF();
   }

   @Override
   public int readUnsignedByte() throws IOException {
      return ((DataInputStream)this.in).readUnsignedByte();
   }

   @Override
   public int readUnsignedShort() throws IOException {
      ((DataInputStream)this.in).readFully(this.tmp, 0, 2);
      return (this.tmp[1] & 0xFF) << 8 | this.tmp[0] & 0xFF;
   }

   @Override
   public int skipBytes(int var1) throws IOException {
      return (int)this.in.skip(var1);
   }

   public void skipExact(long var1) throws IOException {
      if (this.in.skip(var1) != var1) {
         throw new IOException("Not enough bytes to skip");
      }
   }

   public int readIntULEB128() throws IOException {
      return IntegerLEB128.readULEB128(this.in);
   }

   public long readLongULEB128() throws IOException {
      return LongLEB128.readULEB128(this.in);
   }
}
