package com.pnfsoftware.jeb.corei.parsers.sevenzip;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

class CU extends InputStream {
   CU(eo var1, SevenZFile var2) {
      this.RF = var1;
      this.q = var2;
   }

   @Override
   public int read() throws IOException {
      return this.q.read();
   }

   @Override
   public int read(byte[] var1) throws IOException {
      return this.q.read(var1);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.q.read(var1, var2, var3);
   }

   @Override
   public void close() throws IOException {
      this.q.close();
   }
}
