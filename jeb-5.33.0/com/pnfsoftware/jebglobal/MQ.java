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

public class MQ implements EX, Closeable {
   private static final ILogger pC = GlobalLog.getLogger(MQ.class);
   private CountingInputStream A;
   private LEDataInputStream kS;
   private List wS = new ArrayList();

   public MQ(InputStream var1) {
      this.A = new CountingInputStream(var1);
      this.kS = new LEDataInputStream(this.A);
   }

   @Override
   public void close() throws IOException {
      this.kS.close();
   }

   @Override
   public int pC() {
      return this.A.getCount();
   }

   @Override
   public int A() throws IOException {
      return this.kS.available();
   }

   @Override
   public void readFully(byte[] var1) throws IOException {
      this.kS.readFully(var1);
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) throws IOException {
      this.kS.readFully(var1, var2, var3);
   }

   @Override
   public int skipBytes(int var1) throws IOException {
      return bM.pC(this, var1, true, false);
   }

   @Override
   public int pC(int var1) throws IOException {
      return this.kS.skipBytes(var1);
   }

   @Override
   public boolean readBoolean() throws IOException {
      return this.kS.readBoolean();
   }

   @Override
   public byte readByte() throws IOException {
      return this.kS.readByte();
   }

   @Override
   public int readUnsignedByte() throws IOException {
      return this.kS.readUnsignedByte();
   }

   @Override
   public short readShort() throws IOException {
      return this.kS.readShort();
   }

   @Override
   public int readUnsignedShort() throws IOException {
      return this.kS.readUnsignedShort();
   }

   @Override
   public char readChar() throws IOException {
      return this.kS.readChar();
   }

   @Override
   public int readInt() throws IOException {
      return this.kS.readInt();
   }

   @Override
   public long readLong() throws IOException {
      return this.kS.readLong();
   }

   @Override
   public float readFloat() throws IOException {
      return this.kS.readFloat();
   }

   @Override
   public double readDouble() throws IOException {
      return this.kS.readDouble();
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
      this.wS.add(var3);
   }

   @Override
   public List kS() {
      return this.wS;
   }
}
