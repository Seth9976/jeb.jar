package com.pnfsoftware.jeb.util.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class RotatingFileOutputStream extends OutputStream {
   private File folder;
   private String basename;
   private int rotFileCount;
   private long rotFileSize;
   private boolean buffered;
   private long rotationCount;
   private File sink;
   private long sinksize;
   private OutputStream filestream;

   public RotatingFileOutputStream(File var1, String var2, int var3, long var4) throws IOException {
      this(var1, var2, var3, var4, true, true);
   }

   public RotatingFileOutputStream(File var1, String var2, int var3, long var4, boolean var6, boolean var7) throws IOException {
      if (var1 == null) {
         var1 = new File(System.getProperty("user.dir"));
      }

      if (var1 == null || !var1.isDirectory()) {
         throw new IllegalArgumentException("Need a folder directory, got: " + var1);
      } else if (var2 == null || var2.length() == 0) {
         throw new IllegalArgumentException("Need a base filename, got: " + var2);
      } else if (var3 < 0) {
         throw new IllegalArgumentException("Need a positive rotating file count, got: " + var3);
      } else if (var4 <= 0L) {
         throw new IllegalArgumentException("Need a stricly positive rotating file count, got: " + var3);
      } else {
         this.folder = var1;
         this.basename = var2;
         this.rotFileCount = var3;
         this.rotFileSize = var4;
         this.buffered = var6;
         this.sink = this.gen(0);
         if (!var7) {
            this.sink.delete();
         }

         this.sinksize = this.sink.length();
         this.open();
      }
   }

   public int getRotatingFileCount() {
      return this.rotFileCount;
   }

   public long getRotatingFileSize() {
      return this.rotFileSize;
   }

   public boolean isBuffered() {
      return this.buffered;
   }

   public long getRotationCount() {
      return this.rotationCount;
   }

   public File getOutputFile() {
      return this.sink;
   }

   public File getAdditionalOutputFile(int var1) throws IOException {
      if (var1 > 0 && var1 <= this.rotFileCount) {
         return this.gen(var1);
      } else {
         throw new IOException("Illegal rotating file, index must be in [1," + this.rotFileCount + "]");
      }
   }

   private void open() throws IOException {
      this.filestream = new FileOutputStream(this.sink, true);
      if (this.buffered) {
         this.filestream = new BufferedOutputStream(this.filestream);
      }
   }

   private File gen(int var1) throws IOException {
      return this.gen(var1, true);
   }

   private File gen(int var1, boolean var2) throws IOException {
      return var1 == 0 && var2 ? new File(this.folder, this.basename) : new File(this.folder, this.basename + this.generateFileSuffix(var1));
   }

   protected String generateFileSuffix(int var1) {
      return "." + var1;
   }

   public void rotate() throws IOException {
      if (this.rotationCount == 0L) {
         for (int var1 = 1; var1 <= this.rotFileCount; var1++) {
            this.gen(var1).createNewFile();
         }

         this.rotationCount++;
      }

      this.filestream.close();
      int var3 = 1 + this.rotFileCount;

      for (int var2 = this.rotFileCount; var2 >= 0; var2--) {
         this.gen(var2).renameTo(this.gen((var2 + 1) % var3, false));
      }

      this.gen(0, false).delete();
      this.sink.createNewFile();
      this.sinksize = 0L;
      this.open();
   }

   private void checkRotate(long var1) throws IOException {
      this.sinksize += var1;
      if (this.sinksize >= this.rotFileSize) {
         this.rotate();
      }
   }

   @Override
   public void write(int var1) throws IOException {
      this.checkRotate(1L);
      this.filestream.write(var1);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws IOException {
      this.checkRotate(var3);
      this.filestream.write(var1, var2, var3);
   }

   @Override
   public void flush() throws IOException {
      this.filestream.flush();
   }

   @Override
   public void close() throws IOException {
      this.filestream.close();
      this.filestream = null;
   }
}
