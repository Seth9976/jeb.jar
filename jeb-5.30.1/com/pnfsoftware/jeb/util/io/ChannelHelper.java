package com.pnfsoftware.jeb.util.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class ChannelHelper {
   SeekableByteChannel channel;
   Endianness endianness;
   byte[] array;
   ByteBuffer b;

   public ChannelHelper(SeekableByteChannel var1, Endianness var2) {
      this.channel = var1;
      this.endianness = var2;
      this.array = new byte[8];
      this.b = ByteBuffer.wrap(this.array);
      this.b.order(var2.toByteOrder());
   }

   private void prep(int var1, long var2) throws IOException {
      this.channel.position(var2);
      this.prep(var1);
   }

   private void prep(int var1) throws IOException {
      this.b.position(0);
      this.b.limit(var1);
   }

   public byte get() throws IOException {
      this.prep(1);
      if (this.channel.read(this.b) != 1) {
         throw new IOException("Cannot read 1 byte");
      } else {
         return this.array[0];
      }
   }

   public byte get(long var1) throws IOException {
      this.prep(1, var1);
      if (this.channel.read(this.b) != 1) {
         throw new IOException("Cannot read 1 byte");
      } else {
         return this.array[0];
      }
   }

   public short getShort() throws IOException {
      this.prep(2);
      if (this.channel.read(this.b) != 2) {
         throw new IOException("Cannot read 2 bytes");
      } else {
         return this.b.getShort(0);
      }
   }

   public short getShort(long var1) throws IOException {
      this.prep(2, var1);
      if (this.channel.read(this.b) != 2) {
         throw new IOException("Cannot read 2 bytes");
      } else {
         return this.b.getShort(0);
      }
   }

   public int getInt() throws IOException {
      this.prep(4);
      if (this.channel.read(this.b) != 4) {
         throw new IOException("Cannot read 4 bytes");
      } else {
         return this.b.getInt(0);
      }
   }

   public int getInt(long var1) throws IOException {
      this.prep(4, var1);
      if (this.channel.read(this.b) != 4) {
         throw new IOException("Cannot read 4 bytes");
      } else {
         return this.b.getInt(0);
      }
   }

   public long getLong() throws IOException {
      this.prep(8);
      if (this.channel.read(this.b) != 8) {
         throw new IOException("Cannot read 8 bytes");
      } else {
         return this.b.getLong(0);
      }
   }

   public long getLong(long var1) throws IOException {
      this.prep(8, var1);
      if (this.channel.read(this.b) != 8) {
         throw new IOException("Cannot read 8 bytes");
      } else {
         return this.b.getLong(0);
      }
   }
}
