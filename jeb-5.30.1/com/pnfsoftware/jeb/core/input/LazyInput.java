package com.pnfsoftware.jeb.core.input;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ArraySeekableByteChannel;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

@Ser
public class LazyInput implements IInput {
   @SerId(1)
   private IDataProvider prv;
   @SerId(2)
   private String entryName;
   @SerId(3)
   private long hintEntrySize;
   @SerId(4)
   private byte[] data;
   @SerId(5)
   private int id;
   @SerTransient
   private ByteBuffer hdrbuf;

   public LazyInput(IDataProvider var1, String var2, long var3) {
      this(var1, var2, var3, 0);
   }

   public LazyInput(IDataProvider var1, String var2, long var3, int var5) {
      if (var1 != null && var2 != null) {
         this.prv = var1;
         this.entryName = var2;
         this.hintEntrySize = var3;
         this.id = var5;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public String getName() {
      return this.entryName;
   }

   @Override
   public boolean canRead() {
      return this.prv.getDataSize(this.entryName, this.id) >= 0L;
   }

   @Override
   public void close() {
   }

   @Override
   public long getCurrentSize() {
      if (this.hintEntrySize < 0L) {
         synchronized (this) {
            if (this.hintEntrySize < 0L) {
               this.hintEntrySize = this.prv.getDataSize(this.entryName, this.id);
            }
         }
      }

      return this.hintEntrySize;
   }

   private byte[] getData() throws IOException {
      if (this.data == null) {
         synchronized (this) {
            if (this.data == null) {
               byte[] var2 = this.prv.getDataBytes(this.entryName, this.id);
               if (var2 == null) {
                  throw new IOException("No input for key: " + this.entryName);
               }

               if (this.hintEntrySize < 0L) {
                  this.hintEntrySize = var2.length;
               } else if (this.hintEntrySize != var2.length) {
                  throw new IOException(Strings.ff("Illegal input (key: %s): expected %d bytes, got %d", this.entryName, this.hintEntrySize, var2.length));
               }

               this.data = var2;
            }
         }
      }

      return this.data;
   }

   @Override
   public ByteBuffer getHeader() {
      if (this.hdrbuf == null) {
         synchronized (this) {
            if (this.hdrbuf == null) {
               byte[] var2 = new byte[512];
               int var3 = this.prv.getDataBytes(this.entryName, this.id, var2, 0, var2.length);
               if (var3 >= 0) {
                  this.hdrbuf = ByteBuffer.wrap(var2, 0, var3);
               }
            }
         }
      }

      if (this.hdrbuf != null) {
         return this.hdrbuf.asReadOnlyBuffer();
      } else {
         try {
            byte[] var1 = this.getData();
            return ByteBuffer.wrap(var1);
         } catch (IOException var5) {
            return null;
         }
      }
   }

   @Override
   public SeekableByteChannel getChannel() throws IOException {
      return new ArraySeekableByteChannel(this.getData());
   }

   @Override
   public InputStream getStream() throws IOException {
      InputStream var1 = this.prv.openDataStream(this.entryName, this.id);
      return (InputStream)(var1 != null ? var1 : new ByteArrayInputStream(this.getData()));
   }
}
