package com.pnfsoftware.jeb.util.serialization;

import java.io.IOException;
import java.io.InputStream;

class Deserializer_v5$1 extends InputStream {
   Deserializer_v5$1(Deserializer_v5 var1) {
      this.this$0 = var1;
   }

   @Override
   public int read() throws IOException {
      return this.this$0.in.readByte();
   }

   @Override
   public int read(byte[] var1) throws IOException {
      return this.this$0.in.read(var1);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.this$0.in.read(var1, var2, var3);
   }
}
