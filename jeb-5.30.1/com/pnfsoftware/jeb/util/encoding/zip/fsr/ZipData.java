package com.pnfsoftware.jeb.util.encoding.zip.fsr;

public class ZipData {
   public static final ZipData EMPTY = new ZipData(new byte[0]);
   byte[] uncompressedData;
   int uncompressedDataSize;
   Exception exception;

   ZipData(byte[] var1, int var2, Exception var3) {
      this.uncompressedData = var1;
      this.uncompressedDataSize = var2;
      this.exception = var3;
   }

   ZipData(byte[] var1, int var2) {
      this(var1, var2, null);
   }

   ZipData(byte[] var1) {
      this(var1, var1.length, null);
   }

   public byte[] getUncompressedData() {
      return this.uncompressedData;
   }

   public Exception getException() {
      return this.exception;
   }
}
