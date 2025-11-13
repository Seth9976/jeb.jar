package com.pnfsoftware.jeb.util.encoding.zip.fsr;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;

public class LocalFileHeader {
   int versionRequired;
   int flags;
   int compressionMethod;
   int modTime;
   int modDate;
   int crc;
   int compressedSize;
   int filesize;
   byte[] filename = ArrayUtil.NO_BYTE;
   byte[] extra = ArrayUtil.NO_BYTE;
   int entrysize;
   boolean corrupted;

   public static LocalFileHeader parse(SeekableByteChannel var0, int var1) throws IOException {
      LocalFileHeader var2 = new LocalFileHeader();

      try {
         var2.versionRequired = ChannelUtil.getLEShort(var0, var1 + 4) & '\uffff';
         var2.flags = ChannelUtil.getLEShort(var0, var1 + 6) & '\uffff';
         var2.compressionMethod = ChannelUtil.getLEShort(var0, var1 + 8) & '\uffff';
         var2.modTime = ChannelUtil.getLEShort(var0, var1 + 10) & '\uffff';
         var2.modDate = ChannelUtil.getLEShort(var0, var1 + 12) & '\uffff';
         var2.crc = ChannelUtil.getLEInt(var0, var1 + 14);
         var2.compressedSize = ChannelUtil.getLEInt(var0, var1 + 18);
         var2.filesize = ChannelUtil.getLEInt(var0, var1 + 22);
         int var3 = ChannelUtil.getLEShort(var0, var1 + 26) & '\uffff';
         int var4 = ChannelUtil.getLEShort(var0, var1 + 28) & '\uffff';
         if (var3 > 0) {
            ByteBuffer var5 = ChannelUtil.readBestEffort(var0, var1 + 30, var3, false, null);
            if (var5.limit() != var3) {
               var2.corrupted = true;
            }

            var2.filename = var5.array();
         }

         if (var4 > 0) {
            ByteBuffer var7 = ChannelUtil.readBestEffort(var0, var1 + 30 + var3, var4, false, null);
            if (var7.limit() != var4) {
               var2.corrupted = true;
            }

            var2.extra = var7.array();
         }

         var2.entrysize = 30 + var3 + var4;
         return var2;
      } catch (BufferUnderflowException var6) {
         throw new IOException(var6);
      }
   }

   public String getFilename() {
      return new String(this.filename, Charset.forName("UTF-8"));
   }

   public boolean isEncrypted() {
      return (this.flags & 1) != 0;
   }

   public boolean hasDataDescriptor() {
      return (this.flags & 8) != 0;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d(%d)%s", this.getFilename(), this.filesize, this.compressionMethod, this.corrupted ? "[BAD!]" : "");
   }
}
