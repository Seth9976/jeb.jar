package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.encoding.IntegerLEB128;
import com.pnfsoftware.jeb.util.encoding.LongLEB128;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LEDataOutputStream extends FilterOutputStream implements DataOutput {
   public LEDataOutputStream(OutputStream var1) {
      super(new DataOutputStream(var1));
   }

   @Override
   public void close() throws IOException {
      this.out.close();
   }

   public int size() {
      return ((DataOutputStream)this.out).size();
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws IOException {
      this.out.write(var1, var2, var3);
   }

   @Override
   public void writeBoolean(boolean var1) throws IOException {
      ((DataOutputStream)this.out).writeBoolean(var1);
   }

   @Override
   public void writeByte(int var1) throws IOException {
      ((DataOutputStream)this.out).writeByte(var1);
   }

   @Deprecated
   @Override
   public void writeBytes(String var1) throws IOException {
      ((DataOutputStream)this.out).writeBytes(var1);
   }

   @Override
   public void writeUTF(String var1) throws IOException {
      ((DataOutputStream)this.out).writeUTF(var1);
   }

   @Override
   public void writeChar(int var1) throws IOException {
      this.writeShort(var1);
   }

   @Override
   public void writeChars(String var1) throws IOException {
      for (int var2 = 0; var2 < var1.length(); var2++) {
         this.writeChar(var1.charAt(var2));
      }
   }

   @Override
   public void writeInt(int var1) throws IOException {
      this.out.write(0xFF & var1);
      this.out.write(0xFF & var1 >> 8);
      this.out.write(0xFF & var1 >> 16);
      this.out.write(0xFF & var1 >> 24);
   }

   @Override
   public void writeShort(int var1) throws IOException {
      this.out.write(0xFF & var1);
      this.out.write(0xFF & var1 >> 8);
   }

   @Override
   public void writeLong(long var1) throws IOException {
      this.out.write(0xFF & (int)var1);
      this.out.write(0xFF & (int)(var1 >> 8));
      this.out.write(0xFF & (int)(var1 >> 16));
      this.out.write(0xFF & (int)(var1 >> 24));
      this.out.write(0xFF & (int)(var1 >> 32));
      this.out.write(0xFF & (int)(var1 >> 40));
      this.out.write(0xFF & (int)(var1 >> 48));
      this.out.write(0xFF & (int)(var1 >> 56));
   }

   @Override
   public void writeFloat(float var1) throws IOException {
      this.writeInt(Float.floatToIntBits(var1));
   }

   @Override
   public void writeDouble(double var1) throws IOException {
      this.writeLong(Double.doubleToLongBits(var1));
   }

   public void writeIntULEB128(int var1) throws IOException {
      IntegerLEB128.writeULEB128(this.out, var1);
   }

   public void writeLongULEB128(long var1) throws IOException {
      LongLEB128.writeULEB128(this.out, var1);
   }
}
