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

public class aq implements uL {
   private static final ILogger q = GlobalLog.getLogger(aq.class);
   private ByteBuffer RF;
   private List xK = new ArrayList();

   public aq(ByteBuffer var1) {
      if (var1.order() != ByteOrder.LITTLE_ENDIAN) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   public aq(byte[] var1, int var2, int var3) {
      this.RF = ByteBuffer.wrap(var1, var2, var3);
      this.RF.order(ByteOrder.LITTLE_ENDIAN);
   }

   @Override
   public int q() {
      return this.RF.position();
   }

   public void RF(int var1) {
      this.RF.position(var1);
   }

   @Override
   public int RF() {
      return this.RF.remaining();
   }

   @Override
   public void readFully(byte[] var1) throws IOException {
      this.RF.get(var1);
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) throws IOException {
      this.RF.get(var1, var2, var3);
   }

   @Override
   public int skipBytes(int var1) throws IOException {
      return zR.q(this, var1, true, false);
   }

   @Override
   public int q(int var1) throws IOException {
      return ByteBufferUtil.skipAttempt(this.RF, var1);
   }

   @Override
   public boolean readBoolean() throws IOException {
      return this.RF.get() != 0;
   }

   @Override
   public byte readByte() throws IOException {
      return this.RF.get();
   }

   @Override
   public int readUnsignedByte() throws IOException {
      return this.RF.get() & 0xFF;
   }

   @Override
   public short readShort() throws IOException {
      return this.RF.getShort();
   }

   @Override
   public int readUnsignedShort() throws IOException {
      return this.RF.getShort() & 65535;
   }

   @Override
   public char readChar() throws IOException {
      return this.RF.getChar();
   }

   @Override
   public int readInt() throws IOException {
      return this.RF.getInt();
   }

   @Override
   public long readLong() throws IOException {
      return this.RF.getLong();
   }

   @Override
   public float readFloat() throws IOException {
      return this.RF.getFloat();
   }

   @Override
   public double readDouble() throws IOException {
      return this.RF.getDouble();
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
      return Strings.ff("Reader@%Xh", this.q());
   }

   @Override
   public void q(String var1, Object... var2) {
      xn var3 = new xn(this.q(), Strings.ff(var1, var2));
      this.xK.add(var3);
   }

   @Override
   public List xK() {
      return this.xK;
   }
}
