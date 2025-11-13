package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class ArraySeekableByteChannel implements SeekableByteChannel {
   private byte[] data;
   private int arrayBaseOffset;
   private int length;
   private int position;

   public ArraySeekableByteChannel(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public ArraySeekableByteChannel(byte[] var1, int var2, int var3) {
      this.data = var1;
      this.arrayBaseOffset = var2;
      this.length = var3;
   }

   @Override
   public boolean isOpen() {
      return true;
   }

   @Override
   public void close() throws IOException {
   }

   @Override
   public int read(ByteBuffer var1) throws IOException {
      int var2 = this.length - this.position;
      if (var2 <= 0) {
         return -1;
      } else {
         int var3 = var1.remaining();
         int var4 = var3 <= var2 ? var3 : var2;
         var1.put(this.data, this.arrayBaseOffset + this.position, var4);
         this.position += var4;
         return var4;
      }
   }

   @Override
   public int write(ByteBuffer var1) throws IOException {
      throw new IOException("Buffer is not writeable");
   }

   @Override
   public long position() throws IOException {
      return this.position;
   }

   @Override
   public SeekableByteChannel position(long var1) throws IOException {
      if (var1 < 0L) {
         throw new IOException(Strings.ff("Negative position: %d", var1));
      } else if (var1 > 2147483647L) {
         throw new IOException(Strings.ff("Position is too large: %d", var1));
      } else {
         this.position = (int)var1;
         return this;
      }
   }

   @Override
   public long size() throws IOException {
      return this.length;
   }

   @Override
   public SeekableByteChannel truncate(long var1) throws IOException {
      throw new IOException("Buffer cannot be truncated");
   }
}
