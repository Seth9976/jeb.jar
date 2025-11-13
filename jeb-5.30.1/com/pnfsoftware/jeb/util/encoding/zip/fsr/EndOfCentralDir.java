package com.pnfsoftware.jeb.util.encoding.zip.fsr;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class EndOfCentralDir {
   int diskNumber;
   int cdDiskStartIndex;
   int cdRecordCountOnThisDisk;
   int cdRecordCountTotal;
   int cdSize;
   int cdOffset;
   byte[] comment = ArrayUtil.NO_BYTE;
   int entrysize;
   boolean corrupted;

   public static EndOfCentralDir parse(SeekableByteChannel var0, int var1) throws IOException {
      EndOfCentralDir var2 = new EndOfCentralDir();

      try {
         var2.diskNumber = ChannelUtil.getLEShort(var0, var1 + 4) & '\uffff';
         var2.cdDiskStartIndex = ChannelUtil.getLEShort(var0, var1 + 6) & '\uffff';
         var2.cdRecordCountOnThisDisk = ChannelUtil.getLEShort(var0, var1 + 8) & '\uffff';
         var2.cdRecordCountTotal = ChannelUtil.getLEShort(var0, var1 + 10) & '\uffff';
         var2.cdSize = ChannelUtil.getLEInt(var0, var1 + 12);
         var2.cdOffset = ChannelUtil.getLEInt(var0, var1 + 16);
         int var3 = ChannelUtil.getLEShort(var0, var1 + 20) & '\uffff';
         if (var3 > 0) {
            ByteBuffer var4 = ChannelUtil.readBestEffort(var0, var1 + 22, var3, false, null);
            if (var4.limit() != var3) {
               var2.corrupted = true;
            }

            var2.comment = var4.array();
         }

         var2.entrysize = 22 + var3;
         return var2;
      } catch (BufferUnderflowException var5) {
         throw new IOException(var5);
      }
   }

   @Override
   public String toString() {
      return Strings.ff(
         "%d/%d/offset=%Xh,size=%Xh,entries=%d/%d%s",
         this.diskNumber,
         this.cdDiskStartIndex,
         this.cdOffset,
         this.cdSize,
         this.cdRecordCountOnThisDisk,
         this.cdRecordCountTotal,
         this.corrupted ? "[BAD!]" : ""
      );
   }
}
