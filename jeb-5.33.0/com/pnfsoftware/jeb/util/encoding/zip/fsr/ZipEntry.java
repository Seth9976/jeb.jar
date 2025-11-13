package com.pnfsoftware.jeb.util.encoding.zip.fsr;

import java.nio.charset.Charset;

public class ZipEntry {
   private LocalFileHeader hdr;
   private CentralDirFileHeader cdhdr;
   private int offsetToData;

   ZipEntry(LocalFileHeader var1, CentralDirFileHeader var2, int var3) {
      this.hdr = var1;
      this.cdhdr = var2;
      this.offsetToData = var3;
   }

   public boolean isHeaderCorrupted() {
      return this.hdr.corrupted || this.cdhdr != null && this.cdhdr.corrupted;
   }

   public String getFilenameUTF8() {
      return this.getFilename(Charset.forName("UTF-8"));
   }

   public String getFilename(Charset var1) {
      if (var1 == null) {
         var1 = Charset.defaultCharset();
      }

      return this.cdhdr != null ? new String(this.cdhdr.filename, var1) : new String(this.hdr.filename, var1);
   }

   public int getFilesize() {
      return this.cdhdr != null ? this.cdhdr.filesize : this.hdr.filesize;
   }

   public int getCompressedSize() {
      int var1 = this.getCompressionMethod();
      if (var1 == 0) {
         return this.getFilesize();
      } else {
         return this.cdhdr != null && this.cdhdr.compressedSize >= 0 ? this.cdhdr.compressedSize : this.hdr.compressedSize;
      }
   }

   public boolean isEncrypted() {
      return this.cdhdr == null ? this.hdr.isEncrypted() : this.cdhdr.isEncrypted() && this.hdr.isEncrypted();
   }

   private static boolean heurLooksLikeValidCompressionMethod(int var0) {
      return var0 >= 0 && var0 <= 20 || var0 >= 93 && var0 <= 99;
   }

   public int getCompressionMethod() {
      if (this.cdhdr != null) {
         return !heurLooksLikeValidCompressionMethod(this.cdhdr.compressionMethod) ? 0 : this.cdhdr.compressionMethod;
      } else {
         return !heurLooksLikeValidCompressionMethod(this.hdr.compressionMethod) ? 0 : this.hdr.compressionMethod;
      }
   }

   public int getCrc() {
      return this.cdhdr != null ? this.cdhdr.crc : this.hdr.crc;
   }

   public boolean isDirectory() {
      if (this.cdhdr != null) {
         return this.cdhdr.filename.length == 0 ? false : this.cdhdr.filename[this.cdhdr.filename.length - 1] == 47;
      } else {
         return this.hdr.filename.length == 0 ? false : this.hdr.filename[this.hdr.filename.length - 1] == 47;
      }
   }

   public int getOffsetToData() {
      return this.offsetToData;
   }

   @Override
   public String toString() {
      return this.getFilenameUTF8();
   }
}
