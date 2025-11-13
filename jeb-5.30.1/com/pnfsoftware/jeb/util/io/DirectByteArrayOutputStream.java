package com.pnfsoftware.jeb.util.io;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class DirectByteArrayOutputStream extends ByteArrayOutputStream {
   public byte[] getRawBytes() {
      return this.buf;
   }

   public synchronized ByteBuffer getBytes() {
      return ByteBuffer.wrap(this.buf, 0, this.count);
   }
}
