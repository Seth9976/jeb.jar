package com.pnfsoftware.jeb.util.io;

import java.io.IOException;
import java.io.OutputStream;

public class DummyOutputStream extends OutputStream {
   private long totalSize;

   @Override
   public void write(int var1) throws IOException {
      this.totalSize++;
   }

   @Override
   public void write(byte[] var1) throws IOException {
      this.totalSize += var1.length;
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws IOException {
      this.totalSize += var3;
   }

   public long getTotalSize() {
      return this.totalSize;
   }
}
