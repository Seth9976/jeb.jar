package com.pnfsoftware.jeb.util.encoding.zip.fsr;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;

public class CentralDirFileHeader {
   int versionCreated;
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
   byte[] comment = ArrayUtil.NO_BYTE;
   int diskNumber;
   int internalFileAttr;
   int externalFileAttr;
   int fileHeaderOffset;
   int entrysize;
   boolean corrupted;

   public static CentralDirFileHeader parse(SeekableByteChannel var0, int var1) throws IOException {
      CentralDirFileHeader var2 = new CentralDirFileHeader();

      try {
         var2.versionCreated = ChannelUtil.getLEShort(var0, var1 + 4) & '\uffff';
         var2.versionRequired = ChannelUtil.getLEShort(var0, var1 + 6) & '\uffff';
         var2.flags = ChannelUtil.getLEShort(var0, var1 + 8) & '\uffff';
         var2.compressionMethod = ChannelUtil.getLEShort(var0, var1 + 10) & '\uffff';
         var2.modTime = ChannelUtil.getLEShort(var0, var1 + 12) & '\uffff';
         var2.modDate = ChannelUtil.getLEShort(var0, var1 + 14) & '\uffff';
         var2.crc = ChannelUtil.getLEInt(var0, var1 + 16);
         var2.compressedSize = ChannelUtil.getLEInt(var0, var1 + 20);
         var2.filesize = ChannelUtil.getLEInt(var0, var1 + 24);
         int var3 = ChannelUtil.getLEShort(var0, var1 + 28) & '\uffff';
         int var4 = ChannelUtil.getLEShort(var0, var1 + 30) & '\uffff';
         int var5 = ChannelUtil.getLEShort(var0, var1 + 32) & '\uffff';
         var2.diskNumber = ChannelUtil.getLEShort(var0, var1 + 34) & '\uffff';
         var2.internalFileAttr = ChannelUtil.getLEShort(var0, var1 + 36) & '\uffff';
         var2.externalFileAttr = ChannelUtil.getLEInt(var0, var1 + 38);
         var2.fileHeaderOffset = ChannelUtil.getLEInt(var0, var1 + 42);
         if (var3 > 0) {
            ByteBuffer var6 = ChannelUtil.readBestEffort(var0, var1 + 46, var3, false, null);
            if (var6.limit() != var3) {
               var2.corrupted = true;
            }

            var2.filename = var6.array();
         }

         if (var4 > 0) {
            ByteBuffer var8 = ChannelUtil.readBestEffort(var0, var1 + 46 + var3, var4, false, null);
            if (var8.limit() != var4) {
               var2.corrupted = true;
            }

            var2.extra = var8.array();
         }

         if (var5 > 0) {
            ByteBuffer var9 = ChannelUtil.readBestEffort(var0, var1 + 46 + var3 + var4, var5, false, null);
            if (var9.limit() != var5) {
               var2.corrupted = true;
            }

            var2.extra = var9.array();
         }

         var2.entrysize = 46 + var3 + var4 + var5;
         return var2;
      } catch (BufferUnderflowException var7) {
         throw new IOException(var7);
      }
   }

   public String getFilename() {
      return new String(this.filename, Charset.forName("UTF-8"));
   }

   public boolean isEncrypted() {
      return (this.flags & 1) != 0;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d(%d)%s", this.getFilename(), this.filesize, this.compressionMethod, this.corrupted ? "[BAD!]" : "");
   }
}
