package com.pnfsoftware.jeb.util.encoding.zip.fsr;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class ZipFailSafeReader implements AutoCloseable {
   private static final ILogger logger = GlobalLog.getLogger(ZipFailSafeReader.class);
   static final byte[] MAGIC_CENTRALDIR_FILEHDR = new byte[]{80, 75, 1, 2};
   static final byte[] MAGIC_LOCAL_FILEHDR = new byte[]{80, 75, 3, 4};
   static final byte[] MAGIC_END_OF_CENTRALDIR = new byte[]{80, 75, 5, 6};
   static final byte[] MAGIC_DATA = new byte[]{80, 75, 7, 8};
   public static final int STORED = 0;
   public static final int DEFLATED = 8;
   private int treatUnsupportedCompressionAsMethod;
   private boolean ignoreEncryption;
   private boolean recoveryMode;
   private SeekableByteChannel channel;
   private int archsize;
   private ByteBuffer tmpPage;
   private EndOfCentralDir eocr;
   private Boolean parsingComplete;
   private List entries = new ArrayList();
   private Map map = new LinkedHashMap();
   private int offsetFirstEntry = Integer.MAX_VALUE;
   private int offsetAppendedData;

   public ZipFailSafeReader(File var1) throws IOException {
      this(var1, -1, false, false, false);
   }

   public ZipFailSafeReader(SeekableByteChannel var1) throws IOException {
      this(var1, -1, false, false, false);
   }

   public ZipFailSafeReader(File var1, int var2, boolean var3, boolean var4, boolean var5) throws IOException {
      this(FileChannel.open(var1.toPath(), StandardOpenOption.READ), var2, var3, var4, var5);
   }

   public ZipFailSafeReader(SeekableByteChannel var1, int var2, boolean var3, boolean var4, boolean var5) throws IOException {
      this.channel = var1;
      this.treatUnsupportedCompressionAsMethod = var2;
      this.ignoreEncryption = var3;
      this.recoveryMode = var4;
      if (var1.size() >= 2147483647L) {
         throw new IOException("Cannot handle archive greater than 2Gb");
      } else {
         this.archsize = (int)var1.size();
         if (this.archsize < 22) {
            throw new IOException("Illegal zip file: too small");
         } else {
            int var6 = -1;
            this.tmpPage = ByteBuffer.allocate(512).order(ByteOrder.LITTLE_ENDIAN);
            int var7 = this.archsize;

            while (var7 > 0) {
               var7 = Math.max(0, var7 - 509);
               var1.position(var7);
               this.tmpPage.clear();
               int var8 = var1.read(this.tmpPage);
               int var9 = find(this.tmpPage.array(), var8, MAGIC_END_OF_CENTRALDIR);
               if (var9 >= 0) {
                  var6 = var7 + var9;
                  break;
               }
            }

            if (var6 < 0) {
               if (!var4) {
                  throw new IOException("The EOCD record was not found");
               } else {
                  this.recoveryModeNoCD();
               }
            } else {
               this.eocr = EndOfCentralDir.parse(var1, var6);
               if (this.eocr.diskNumber != 0 || this.eocr.cdDiskStartIndex != 0) {
                  logger.info(S.L("Zip file: Ignoring multi-disk"));
               }

               this.offsetAppendedData = var6 + this.eocr.entrysize;
               if (!var5) {
                  ZipFailSafeReader.ZipEntriesIterator var12 = new ZipFailSafeReader.ZipEntriesIterator();

                  while (var12.hasNext()) {
                     try {
                        var12.next();
                     } catch (RuntimeException var11) {
                        Throwable var10 = var11.getCause();
                        if (var10 instanceof IOException) {
                           throw (IOException)var10;
                        }

                        throw var11;
                     }
                  }
               }
            }
         }
      }
   }

   public Iterable enumerateEntries() {
      return new ZipFailSafeReader$1(this);
   }

   private void recoveryModeNoCD() throws IOException {
      this.parsingComplete = false;
      this.offsetAppendedData = this.archsize;
      int var8 = 0;

      while (true) {
         var8 = this.findInFile(var8, MAGIC_LOCAL_FILEHDR);
         if (var8 < 0) {
            break;
         }

         LocalFileHeader var2;
         try {
            var2 = LocalFileHeader.parse(this.channel, var8);
         } catch (IOException var7) {
            var8++;
            continue;
         }

         if (this.entries.isEmpty()) {
            this.offsetFirstEntry = var8;
         }

         int var3 = var8 + var2.entrysize;
         Object[] var10000 = new Object[]{var2.getFilename()};
         ZipEntry var4 = new ZipEntry(var2, null, var3);
         this.map.put(var2.getFilename(), var4);
         this.entries.add(var4);
         if (var2.hasDataDescriptor()) {
            if (var2.compressedSize != -1 && var2.compressedSize != 0) {
               int var9 = var3 + var2.compressedSize;
               if (var9 >= this.archsize) {
                  var2.corrupted = true;
                  break;
               }

               int var6 = ChannelUtil.getLEInt(this.channel, var9);
               if (var6 == 134695760) {
                  var9 += 4;
               }

               var2.crc = ChannelUtil.getLEInt(this.channel, var9);
               var2.compressedSize = ChannelUtil.getLEInt(this.channel, var9 + 4);
               var2.filesize = ChannelUtil.getLEInt(this.channel, var9 + 8);
               var8 = var9 + 12;
            } else {
               int var5 = this.findInFile(var3, MAGIC_DATA);
               if (var5 < 0) {
                  var8 = var3;
               } else {
                  if (var5 >= this.archsize) {
                     var2.corrupted = true;
                     break;
                  }

                  var2.crc = ChannelUtil.getLEInt(this.channel, var5 + 4);
                  var2.compressedSize = ChannelUtil.getLEInt(this.channel, var5 + 8);
                  var2.filesize = ChannelUtil.getLEInt(this.channel, var5 + 12);
                  var8 = var5 + 16;
               }
            }
         } else if (var2.compressedSize != -1 && var2.filesize != -1) {
            var8 = var3 + var2.compressedSize;
         } else {
            var8 = var3;
         }

         if (var8 > this.archsize) {
            var2.corrupted = true;
         }
      }

      this.parsingComplete = true;
   }

   private int findInFile(int var1, byte[] var2) throws IOException {
      while (var1 < this.archsize) {
         this.channel.position(var1);
         this.tmpPage.clear();
         int var3 = this.channel.read(this.tmpPage);
         int var4 = find(this.tmpPage.array(), var3, var2);
         if (var4 >= 0) {
            return var1 + var4;
         }

         var1 += 512 - var2.length + 1;
      }

      return -1;
   }

   @Override
   public void close() throws IOException {
      if (this.channel != null) {
         this.channel.close();
         this.channel = null;
      }
   }

   public boolean isClosed() {
      return this.channel == null;
   }

   private void verifyFullyParsed() {
      if (this.parsingComplete == null || !this.parsingComplete) {
         throw new IllegalStateException("Parsing of entries has not completed!");
      }
   }

   public int getOffsetFirstEntry() {
      this.verifyFullyParsed();
      return this.offsetFirstEntry;
   }

   public boolean hasPrependedData() {
      this.verifyFullyParsed();
      return this.offsetFirstEntry > 0;
   }

   public int getOffsetAppendedData() {
      this.verifyFullyParsed();
      return this.offsetAppendedData;
   }

   public boolean hasAppendedData() {
      this.verifyFullyParsed();
      return this.offsetAppendedData < this.archsize;
   }

   public boolean isTruncated() {
      this.verifyFullyParsed();
      return this.offsetAppendedData > this.archsize;
   }

   public int getNumberOfEntries() {
      this.verifyFullyParsed();
      return this.entries.size();
   }

   public List getEntries() {
      return Collections.unmodifiableList(this.entries);
   }

   public ZipEntry getEntry(String var1) {
      return (ZipEntry)this.map.get(var1);
   }

   public boolean hasEntry(String var1) {
      return this.map.containsKey(var1);
   }

   public ZipData readData(String var1) throws IOException {
      ZipEntry var2 = this.getEntry(var1);
      if (var2 == null) {
         throw new IOException("Entry not found: " + var1);
      } else {
         return this.readData(var2);
      }
   }

   public ZipData readData(ZipEntry var1) throws IOException {
      if (this.isClosed()) {
         throw new IllegalStateException("The archive is closed");
      } else {
         int var2 = var1.getCompressedSize();
         if (var2 == 0) {
            return ZipData.EMPTY;
         } else if (var1.isEncrypted() && !this.ignoreEncryption) {
            throw new IOException("Entry is encrypted");
         } else {
            int var3 = var1.getCompressionMethod();
            if (var3 != 0 && var3 != 8) {
               if (this.treatUnsupportedCompressionAsMethod < 0) {
                  throw new IOException("Unsupported compression method: " + var3);
               }

               var3 = this.treatUnsupportedCompressionAsMethod;
            }

            if (var3 == 0 && var2 < var1.getFilesize()) {
               var2 = var1.getFilesize();
            }

            ByteBuffer var4;
            if (this.recoveryMode) {
               var4 = ChannelUtil.readBestEffort(this.channel, var1.getOffsetToData(), var2, false, null);
            } else {
               var4 = ChannelUtil.read(this.channel, var1.getOffsetToData(), var2, false);
            }

            byte[] var5 = null;
            int var6 = 0;

            try {
               if (var3 == 0) {
                  var5 = var4.array();
                  var6 = var5.length;
               } else {
                  if (var3 != 8) {
                     throw new RuntimeException("Unsupported compression method: " + var3);
                  }

                  Inflater var7 = new Inflater(true);
                  var7.setInput(var4.array(), 0, var4.limit());
                  var5 = new byte[var1.getFilesize()];

                  try {
                     var6 = var7.inflate(var5);
                  } catch (DataFormatException var9) {
                     throw new IOException(var9);
                  }

                  var7.end();
               }

               if (var6 != var1.getFilesize()) {
                  throw new IOException(Strings.ff("Corrupted entry: unexpected decompressed size: %d (expected %d)", var6, var1.getFilesize()));
               }

               CRC32 var11 = new CRC32();
               var11.update(var5);
               int var8 = (int)var11.getValue();
               if (var8 != var1.getCrc()) {
                  throw new IOException(Strings.ff("Corrupted entry: unexpected CRC: %Xh (expected %Xh)", var8, var1.getCrc()));
               }
            } catch (IOException var10) {
               return new ZipData(var5, var6, var10);
            }

            return new ZipData(var5, var6);
         }
      }
   }

   private static int find(byte[] var0, int var1, byte[] var2) {
      label30:
      for (int var3 = 0; var3 <= var1 - var2.length; var3++) {
         if (var0[var3] == var2[0]) {
            int var4 = var3 + 1;

            for (int var5 = 1; var4 < var1 && var5 < var2.length; var5++) {
               if (var0[var4] != var2[var5]) {
                  continue label30;
               }

               var4++;
            }

            return var3;
         }
      }

      return -1;
   }

   @Override
   public String toString() {
      return Strings.ff("Zip archive: %d entries", this.getNumberOfEntries());
   }

   private class ZipEntriesIterator implements Iterator {
      int pos;
      int i;
      int cnt;

      ZipEntriesIterator() {
         if (ZipFailSafeReader.this.parsingComplete != null) {
            throw new IllegalStateException("An entries iterator had already been created (parsingComplete=" + ZipFailSafeReader.this.parsingComplete + ")");
         } else {
            ZipFailSafeReader.this.parsingComplete = false;
            this.pos = ZipFailSafeReader.this.eocr.cdOffset;
            this.cnt = Math.min(ZipFailSafeReader.this.eocr.cdRecordCountOnThisDisk, ZipFailSafeReader.this.eocr.cdRecordCountTotal);
         }
      }

      @Override
      public boolean hasNext() {
         if (this.i < this.cnt) {
            return true;
         } else {
            ZipFailSafeReader.this.parsingComplete = true;
            return false;
         }
      }

      public ZipEntry next() {
         int var1 = this.pos;

         CentralDirFileHeader var2;
         try {
            var2 = CentralDirFileHeader.parse(ZipFailSafeReader.this.channel, this.pos);
         } catch (IOException var10) {
            ZipFailSafeReader.logger.error(S.L("Zip file discrepancy: invalid central-directory header"));
            throw new RuntimeException("Failed to read central directory file header at offset " + var1, var10);
         }

         this.pos = this.pos + var2.entrysize;
         var1 = var2.fileHeaderOffset;

         LocalFileHeader var3;
         try {
            var3 = LocalFileHeader.parse(ZipFailSafeReader.this.channel, var2.fileHeaderOffset);
         } catch (IOException var9) {
            ZipFailSafeReader.logger.error(S.L("Zip file discrepancy: invalid local header"));
            throw new RuntimeException("Failed to read local file header at offset " + var1, var9);
         }

         int var4 = var2.fileHeaderOffset + var3.entrysize;
         ZipEntry var5 = new ZipEntry(var3, var2, var4);
         ZipFailSafeReader.this.entries.add(var5);
         ZipFailSafeReader.this.map.put(var5.getFilenameUTF8(), var5);
         if (!Arrays.equals(var3.filename, var2.filename)) {
            String var6 = Strings.decodeUTF8(var3.filename);
            String var7 = Strings.decodeUTF8(var2.filename);
            int var8 = Math.max(255, Math.min(var6.length(), var7.length()));
            var6 = var6.length() > var8 ? var6.substring(0, var8 - 3) + "..." : var6;
            var7 = var7.length() > var8 ? var7.substring(0, var8 - 3) + "..." : var7;
            ZipFailSafeReader.logger.debug("Zip entry: name discrepancy: local_header=%s, center_header=%s", var6, var7);
         }

         if (var3.flags != var2.flags) {
            ZipFailSafeReader.logger
               .debug("Zip entry: %s: flag discrepancy: local_header=0x%X, central_header=0x%X", var5.getFilenameUTF8(), var3.flags, var2.flags);
         }

         if (var3.compressionMethod == 0 && var3.compressedSize != var3.filesize) {
            ZipFailSafeReader.logger
               .debug(
                  "Zip entry: %s: unexpected compressed size for STORED entry (file header): 0x%X (should be 0x%X)",
                  var5.getFilenameUTF8(),
                  var3.compressedSize,
                  var3.filesize
               );
         } else if (var2.compressionMethod == 0 && var2.compressedSize != var2.filesize) {
            ZipFailSafeReader.logger
               .debug(
                  "Zip entry: %s: unexpected compressed size for STORED entry (central dir. header): 0x%X (should be 0x%X)",
                  var5.getFilenameUTF8(),
                  var2.compressedSize,
                  var2.filesize
               );
         }

         if (var2.fileHeaderOffset < ZipFailSafeReader.this.offsetFirstEntry) {
            ZipFailSafeReader.this.offsetFirstEntry = var2.fileHeaderOffset;
         }

         this.i++;
         return var5;
      }
   }
}
