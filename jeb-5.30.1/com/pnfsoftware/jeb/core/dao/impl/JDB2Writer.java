package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseWriter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.io.StreamWrappers;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.zip.CRC32;

public class JDB2Writer implements IFileDatabaseWriter {
   private static final ILogger logger = GlobalLog.getLogger(JDB2Writer.class);
   private File file;
   private RandomAccessFile fa;
   private Deque marks = new ArrayDeque();

   public JDB2Writer(File var1) throws IOException {
      this(var1, false);
   }

   public JDB2Writer(File var1, boolean var2) throws IOException {
      if (var2 && var1.exists()) {
         this.file = var1;
         this.fa = new RandomAccessFile(var1, "rw");
         long var6 = this.fa.length();
         if (var6 < 32L) {
            throw new IOException("Truncated JDB2 header");
         }

         int var5 = this.readIntLE();
         if (var5 != 843203658) {
            throw new IOException("Missing JDB2 marker");
         }

         this.fa.seek(var6);
      } else {
         if (var1.exists()) {
            var1.delete();
         }

         IO.createFoldersForFile(var1);
         this.file = var1;
         this.fa = new RandomAccessFile(var1, "rw");
         this.writeIntLE(843203658);
         this.writeIntLE(AbstractContext.app_ver.toInt());

         for (int var3 = 2; var3 < 8; var3++) {
            this.writeIntLE(0);
         }
      }
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
   public OutputStream beginRecord(int var1, int var2) throws IOException {
      if (var1 < 0 || var1 > 16777215) {
         throw new IllegalArgumentException("Invalid record type");
      } else if ((var2 & -8) != 0) {
         throw new IllegalArgumentException("Invalid record flags");
      } else {
         boolean var3 = (var2 & 1) != 0;
         boolean var4 = (var2 & 2) != 0;
         boolean var5 = (var2 & 4) != 0;
         int var6 = var1 | var2 << 24;
         this.writeIntLE(var6);
         this.writeIntLE(0);
         this.writeIntLE(0);

         for (int var7 = 3; var7 < 8; var7++) {
            this.writeIntLE(0);
         }

         JDB2Writer.Mark var10 = new JDB2Writer.Mark(this.fa.getFilePointer(), var5);
         this.marks.push(var10);
         JDB2Writer$1 var8 = new JDB2Writer$1(this, var5, var10);
         Object var11 = new BufferedOutputStream(var8, getFileBufferSize());
         if (var4) {
            byte[] var9 = getRecordEncryptionKey(var6);
            var11 = StreamWrappers.getEncryptedStream((OutputStream)var11, var9);
         }

         if (var3) {
            var11 = StreamWrappers.getCompressedStream((OutputStream)var11);
         }

         return (OutputStream)var11;
      }
   }

   static int getFileBufferSize() {
      return 1048576;
   }

   static byte[] getRecordEncryptionKey(int var0) {
      String var1 = Strings.ff("rk_%08X", var0);
      return Strings.encodeASCII(var1);
   }

   @Override
   public void endRecord(OutputStream var1) throws IOException {
      var1.flush();
      var1.close();
      JDB2Writer.Mark var2 = (JDB2Writer.Mark)this.marks.pop();
      long var3 = this.fa.getFilePointer();
      long var5 = var2.ptr;
      int var7 = (int)(var3 - var5);
      Object[] var10000 = new Object[]{var7};
      this.fa.seek(var5 - 28L);
      this.writeIntLE(var7);
      if (var2.crc != null) {
         this.writeIntLE((int)var2.crc.getValue());
      }

      this.fa.seek(var3);
   }

   @Override
   public void verify() {
      if (!this.marks.isEmpty()) {
         throw new IllegalStateException("Not all records were closed");
      }
   }

   @Override
   public void close() throws IOException {
      this.fa.close();
      this.fa = null;
   }

   private void writeIntLE(int var1) throws IOException {
      var1 = var1 << 24 | var1 << 8 & 0xFF0000 | var1 >>> 8 & 0xFF00 | var1 >>> 24;
      this.fa.writeInt(var1);
   }

   private int readIntLE() throws IOException {
      int var1 = this.fa.readInt();
      return EndianUtil.swapInt(var1);
   }

   private static class Mark {
      long ptr;
      CRC32 crc;

      Mark(long var1, boolean var3) {
         if (var1 < 0L) {
            throw new IllegalArgumentException();
         } else {
            this.ptr = var1;
            if (var3) {
               this.crc = new CRC32();
            }
         }
      }
   }
}
