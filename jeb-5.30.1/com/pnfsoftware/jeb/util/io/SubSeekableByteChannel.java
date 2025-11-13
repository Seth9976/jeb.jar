package com.pnfsoftware.jeb.util.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class SubSeekableByteChannel implements SeekableByteChannel {
   private SeekableByteChannel channel;
   private long base;
   private long maxsize;
   private long end;

   public SubSeekableByteChannel(SeekableByteChannel var1, long var2, long var4) throws IOException {
      if (var2 >= 0L && var4 >= 0L && var2 + var4 <= var1.size()) {
         this.channel = var1;
         this.base = var2;
         this.maxsize = var4;
         this.end = var2 + var4;
         var1.position(var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public boolean isOpen() {
      return this.channel.isOpen();
   }

   @Override
   public void close() throws IOException {
      this.channel.close();
   }

   @Override
   public int read(ByteBuffer var1) throws IOException {
      long var2 = this.channel.position();
      if (var2 >= this.base && var2 < this.end) {
         int var4 = var1.limit();
         int var5 = var1.position();
         long var6 = (long)var4 - var5;
         long var8 = this.end - var2;
         if (var6 > var8) {
            var1.limit((int)(var5 + var8));
         }

         int var10 = this.channel.read(var1);
         var1.limit(var4);
         return var10;
      } else {
         return -1;
      }
   }

   @Override
   public int write(ByteBuffer var1) throws IOException {
      throw new IOException("Unsupported operation");
   }

   @Override
   public long position() throws IOException {
      return this.channel.position() - this.base;
   }

   @Override
   public SeekableByteChannel position(long var1) throws IOException {
      this.channel.position(this.base + var1);
      return this;
   }

   @Override
   public long size() throws IOException {
      return this.maxsize;
   }

   @Override
   public SeekableByteChannel truncate(long var1) throws IOException {
      throw new IOException("Unsupported operation");
   }
}
