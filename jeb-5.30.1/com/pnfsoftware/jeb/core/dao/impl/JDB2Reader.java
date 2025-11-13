package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseReader;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.StreamWrappers;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

public class JDB2Reader implements IFileDatabaseReader {
   private static final ILogger logger = GlobalLog.getLogger(JDB2Reader.class);
   private File file;
   private RandomAccessFile fa;
   private int jebVersionInt;
   private List records = new ArrayList();

   public JDB2Reader(File var1) throws IOException {
      this.file = var1;
      this.fa = new RandomAccessFile(var1, "r");
      if (this.readIntLE() != 843203658) {
         throw new IOException();
      } else {
         this.jebVersionInt = this.readIntLE();
         this.fa.skipBytes(24);

         try {
            while (this.fa.getFilePointer() + 8L <= this.fa.length()) {
               int var2 = this.readIntLE();
               int var3 = var2 & 16777215;
               int var4 = var2 >> 24 & 0xFF;
               int var5 = this.readIntLE();
               Integer var6 = null;
               if (this.jebVersionInt >= 263424) {
                  var6 = this.readIntLE();
                  this.fa.skipBytes(20);
               }

               long var7 = this.fa.getFilePointer();
               if (var6 != null && (var4 & 4) != 0) {
                  Integer var9 = null;
                  CRC32 var10 = new CRC32();

                  try {
                     Hash.checksumBlockFileUpdate(var10, this.fa, var5);
                     var9 = (int)var10.getValue();
                  } catch (Exception var12) {
                     logger.catchingSilent(var12);
                  }

                  this.fa.seek(var7);
                  if (var9 == null || !var9.equals(var6)) {
                     logger.error(S.L("JDB2 record '%s' is corrupted! Will attempt project reload anyway..."), JDB2Manager.projectTypeToName(var3));
                  }
               }

               this.records.add(new JDB2Reader.RecordDescription(var3, var4, var6, var7, var5));
               this.fa.skipBytes(var5);
            }
         } catch (IOException var13) {
            logger.catching(var13);
         }

         this.fa.seek(32L);
      }
   }

   public int getJebVersionInt() {
      return this.jebVersionInt;
   }

   @Override
   public boolean hasBackingFile() {
      return true;
   }

   @Override
   public File getBackingFile() {
      return this.file;
   }

   @Override
   public List getRecordDescriptions() {
      return this.records;
   }

   @Override
   public JDB2Reader.RecordDescription getRecordDescription(int var1) {
      for (JDB2Reader.RecordDescription var3 : this.records) {
         if (var3.getType() == var1) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public InputStream getRecord(int var1) throws IOException {
      JDB2Reader.RecordDescription var2 = this.getRecordDescription(var1);
      if (var2 == null) {
         throw new IOException(Strings.ff("A record of type %X was not found", var1));
      } else {
         return this.getRecord(var2);
      }
   }

   @Override
   public InputStream getRecord(JDB2Reader.RecordDescription var1) throws IOException {
      boolean var2 = (var1.flags & 1) != 0;
      boolean var3 = (var1.flags & 2) != 0;
      int var4 = var1.dataSize;
      this.fa.seek(var1.dataOffset);
      long var5 = this.fa.getFilePointer() + var4;
      JDB2Reader$1 var7 = new JDB2Reader$1(this, var5);
      Object var10 = new BufferedInputStream(var7, JDB2Writer.getFileBufferSize());
      if (var3) {
         int var8 = var1.type & 16777215 | (var1.flags & 0xFF) << 24;
         byte[] var9 = JDB2Writer.getRecordEncryptionKey(var8);
         var10 = StreamWrappers.getDecryptedStream((InputStream)var10, var9);
      }

      if (var2) {
         var10 = StreamWrappers.getUncompressedStream((InputStream)var10);
      }

      return (InputStream)var10;
   }

   @Override
   public void close() throws IOException {
      this.fa.close();
      this.fa = null;
   }

   private int readIntLE() throws IOException {
      int var1 = this.fa.readInt();
      return var1 << 24 | var1 << 8 & 0xFF0000 | var1 >>> 8 & 0xFF00 | var1 >>> 24;
   }

   public static class RecordDescription {
      int type;
      int flags;
      Integer crcValue;
      long dataOffset;
      int dataSize;

      public RecordDescription(int var1, int var2, Integer var3, long var4, int var6) {
         this.type = var1;
         this.flags = var2;
         this.crcValue = var3;
         this.dataOffset = var4;
         this.dataSize = var6;
      }

      public int getType() {
         return this.type;
      }

      public int getFlags() {
         return this.flags;
      }

      public int getCrcValue() {
         return this.crcValue;
      }

      public long getOffsetToData() {
         return this.dataOffset;
      }

      public int getSizeOfData() {
         return this.dataSize;
      }
   }
}
