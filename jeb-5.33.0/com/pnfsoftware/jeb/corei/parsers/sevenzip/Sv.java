package com.pnfsoftware.jeb.corei.parsers.sevenzip;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

class Sv extends InputStream {
   Sv(Av var1, SevenZFile var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public int read() throws IOException {
      return this.pC.read();
   }

   @Override
   public int read(byte[] var1) throws IOException {
      return this.pC.read(var1);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.pC.read(var1, var2, var3);
   }

   @Override
   public void close() throws IOException {
      this.pC.close();
   }
}
