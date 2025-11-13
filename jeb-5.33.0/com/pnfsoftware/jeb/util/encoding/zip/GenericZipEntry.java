package com.pnfsoftware.jeb.util.encoding.zip;

import java.util.zip.ZipEntry;

public class GenericZipEntry {
   private ZipEntry internalEntry;

   GenericZipEntry(ZipEntry var1) {
      this.internalEntry = var1;
   }

   public ZipEntry getInternalEntry() {
      return this.internalEntry;
   }

   public String getName() {
      return this.internalEntry.getName();
   }

   public String getSimpleName() {
      String var1 = this.internalEntry.getName();
      int var2 = var1.lastIndexOf(47);
      return var2 < 0 ? var1 : var1.substring(var2 + 1);
   }

   public long getTime() {
      return this.internalEntry.getTime();
   }

   public long getSize() {
      return this.internalEntry.getSize();
   }

   public long getCompressedSize() {
      return this.internalEntry.getCompressedSize();
   }

   public long getCrc() {
      return this.internalEntry.getCrc();
   }

   public int getMethod() {
      return this.internalEntry.getMethod();
   }

   public byte[] getExtra() {
      return this.internalEntry.getExtra();
   }

   public String getComment() {
      return this.internalEntry.getComment();
   }

   public boolean isDirectory() {
      return this.internalEntry.isDirectory();
   }
}
