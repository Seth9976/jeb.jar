package com.pnfsoftware.jeb.core.input;

import com.pnfsoftware.jeb.util.io.SubSeekableByteChannel;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;

@Ser
public class SubInput implements IInput {
   @SerId(1)
   private IInput masterInput;
   @SerId(2)
   private long base;
   @SerId(3)
   private long maxsize;
   @SerTransient
   private ByteBuffer hdr;

   @SerCustomInitPostGraph
   private void init() {
      try {
         this.setup(this.masterInput, this.base, this.maxsize);
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public SubInput(IInput var1, long var2, long var4) throws IOException {
      this.setup(var1, var2, var4);
   }

   private void setup(IInput var1, long var2, long var4) throws IOException {
      if (var2 >= 0L && var4 >= 0L && var2 + var4 <= var1.getCurrentSize()) {
         this.masterInput = var1;
         this.base = var2;
         this.maxsize = var4;
         SeekableByteChannel var6 = null;

         try {
            var6 = var1.getChannel();
            this.hdr = ByteBuffer.allocate(512);
            int var7 = var6.position(var2).read(this.hdr);
            if (var7 < 0) {
               throw new IOException("Unexpected end of stream");
            }

            this.hdr.position(0);
            this.hdr.limit(var7);
         } finally {
            if (var6 != null) {
               var6.close();
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   IInput getMasterInput() {
      return this.masterInput;
   }

   @Override
   public String getName() {
      String var1 = this.masterInput.getName();
      return var1 == null ? null : "Sub of " + var1;
   }

   @Override
   public boolean canRead() {
      return this.masterInput.canRead();
   }

   @Override
   public void close() {
   }

   @Override
   public long getCurrentSize() {
      return this.maxsize;
   }

   @Override
   public ByteBuffer getHeader() {
      return this.hdr.asReadOnlyBuffer();
   }

   @Override
   public SeekableByteChannel getChannel() throws IOException {
      return new SubSeekableByteChannel(this.masterInput.getChannel(), this.base, this.maxsize);
   }

   @Override
   public InputStream getStream() throws IOException {
      return Channels.newInputStream(this.getChannel());
   }
}
