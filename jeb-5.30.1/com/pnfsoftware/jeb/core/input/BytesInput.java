package com.pnfsoftware.jeb.core.input;

import com.pnfsoftware.jeb.util.io.ArraySeekableByteChannel;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

@Ser
public class BytesInput implements IInput {
   @SerId(1)
   private byte[] data;
   @SerId(2)
   private int offset;
   @SerId(3)
   private int length;
   @SerId(4)
   private String name;
   @SerTransient
   private ByteBuffer buffer;

   @SerCustomInitPostGraph
   private void init() {
      if (this.offset == 0 && this.length == 0) {
         this.length = this.data.length;
      }

      this.buffer = ByteBuffer.wrap(this.data, this.offset, this.length);
   }

   public BytesInput(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public BytesInput(byte[] var1, int var2, int var3) {
      this(var1, var2, var3, null);
   }

   public BytesInput(byte[] var1, int var2, int var3, String var4) {
      this.buffer = ByteBuffer.wrap(var1, var2, var3);
      this.data = var1;
      this.offset = var2;
      this.length = var3;
      if (var3 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.name = var4;
      }
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean canRead() {
      return true;
   }

   @Override
   public void close() {
   }

   @Override
   public long getCurrentSize() {
      return this.length;
   }

   @Override
   public ByteBuffer getHeader() {
      return this.buffer.asReadOnlyBuffer();
   }

   @Override
   public SeekableByteChannel getChannel() {
      return new ArraySeekableByteChannel(this.data, this.offset, this.length);
   }

   @Override
   public InputStream getStream() {
      return new ByteArrayInputStream(this.data, this.offset, this.length);
   }
}
