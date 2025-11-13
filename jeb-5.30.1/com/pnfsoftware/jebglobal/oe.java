package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.input.CountingInputStream;

public class oe implements uL, Closeable {
   private static final ILogger q = GlobalLog.getLogger(oe.class);
   private CountingInputStream RF;
   private LEDataInputStream xK;
   private List Dw = new ArrayList();

   public oe(InputStream var1) {
      this.RF = new CountingInputStream(var1);
      this.xK = new LEDataInputStream(this.RF);
   }

   @Override
   public void close() throws IOException {
      this.xK.close();
   }

   @Override
   public int q() {
      return this.RF.getCount();
   }

   @Override
   public int RF() throws IOException {
      return this.xK.available();
   }

   @Override
   public void readFully(byte[] var1) throws IOException {
      this.xK.readFully(var1);
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) throws IOException {
      this.xK.readFully(var1, var2, var3);
   }

   @Override
   public int skipBytes(int var1) throws IOException {
      return zR.q(this, var1, true, false);
   }

   @Override
   public int q(int var1) throws IOException {
      return this.xK.skipBytes(var1);
   }

   @Override
   public boolean readBoolean() throws IOException {
      return this.xK.readBoolean();
   }

   @Override
   public byte readByte() throws IOException {
      return this.xK.readByte();
   }

   @Override
   public int readUnsignedByte() throws IOException {
      return this.xK.readUnsignedByte();
   }

   @Override
   public short readShort() throws IOException {
      return this.xK.readShort();
   }

   @Override
   public int readUnsignedShort() throws IOException {
      return this.xK.readUnsignedShort();
   }

   @Override
   public char readChar() throws IOException {
      return this.xK.readChar();
   }

   @Override
   public int readInt() throws IOException {
      return this.xK.readInt();
   }

   @Override
   public long readLong() throws IOException {
      return this.xK.readLong();
   }

   @Override
   public float readFloat() throws IOException {
      return this.xK.readFloat();
   }

   @Override
   public double readDouble() throws IOException {
      return this.xK.readDouble();
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
      this.Dw.add(var3);
   }

   @Override
   public List xK() {
      return this.Dw;
   }
}
