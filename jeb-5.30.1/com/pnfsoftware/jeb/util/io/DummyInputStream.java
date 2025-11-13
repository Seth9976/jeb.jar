package com.pnfsoftware.jeb.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class DummyInputStream extends InputStream {
   private static final byte DUMMY = 0;
   private long totalSize;

   @Override
   public int read() throws IOException {
      this.totalSize++;
      return 0;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws IOException {
      Arrays.fill(var1, var2, var2 + var3, (byte)0);
      this.totalSize += var3;
      return var3;
   }

   public long getTotalSize() {
      return this.totalSize;
   }
}
