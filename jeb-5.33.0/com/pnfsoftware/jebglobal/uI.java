package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class uI implements EX {
   private static final ILogger pC = GlobalLog.getLogger(uI.class);
   private ByteBuffer A;
   private List kS = new ArrayList();

   public uI(byte[] var1, int var2, int var3) {
      this.A = ByteBuffer.wrap(var1, var2, var3);
      this.A.order(ByteOrder.LITTLE_ENDIAN);
   }

   @Override
   public int pC() {
      return this.A.position();
   }

   public void A(int var1) {
      this.A.position(var1);
   }

   @Override
   public int A() {
      return this.A.remaining();
   }

   @Override
   public void readFully(byte[] var1) throws IOException {
      this.A.get(var1);
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) throws IOException {
      this.A.get(var1, var2, var3);
   }

   @Override
   public int skipBytes(int var1) throws IOException {
      return bM.pC(this, var1, true, false);
   }

   @Override
   public int pC(int var1) throws IOException {
      return ByteBufferUtil.skipAttempt(this.A, var1);
   }

   @Override
   public boolean readBoolean() throws IOException {
      return this.A.get() != 0;
   }

   @Override
   public byte readByte() throws IOException {
      return this.A.get();
   }

   @Override
   public int readUnsignedByte() throws IOException {
      return this.A.get() & 0xFF;
   }

   @Override
   public short readShort() throws IOException {
      return this.A.getShort();
   }

   @Override
   public int readUnsignedShort() throws IOException {
      return this.A.getShort() & 65535;
   }

   @Override
   public char readChar() throws IOException {
      return this.A.getChar();
   }

   @Override
   public int readInt() throws IOException {
      return this.A.getInt();
   }

   @Override
   public long readLong() throws IOException {
      return this.A.getLong();
   }

   @Override
   public float readFloat() throws IOException {
      return this.A.getFloat();
   }

   @Override
   public double readDouble() throws IOException {
      return this.A.getDouble();
   }

   @Override
   public String readLine() throws IOException {
      throw new RuntimeException();
   }

   @Override
   public String readUTF() throws IOException {
      throw new RuntimeException();
   }

   @Override
   public String toString() {
      return Strings.ff("Reader@%Xh", this.pC());
   }

   @Override
   public void pC(String var1, Object... var2) {
      oe var3 = new oe(this.pC(), Strings.ff(var1, var2));
      this.kS.add(var3);
   }

   @Override
   public List kS() {
      return this.kS;
   }
}
