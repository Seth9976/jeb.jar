package com.pnfsoftware.jeb.core.input;

import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowser;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.DeserializerHelper;
import com.pnfsoftware.jeb.util.serialization.SerializerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomRead;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

@Ser
@SerVersion(1)
public final class FileInput implements IInput {
   private static final ILogger logger = GlobalLog.getLogger(FileInput.class);
   public static final int SER_FLAG_FULL_PERSISTENCE = 1;
   @SerId(1)
   private String path;
   @SerId(2)
   private byte[] sha256hash;
   @SerId(3)
   private byte[] fulldata;
   @SerTransient
   private String pathbase;
   @SerTransient
   private File file;
   @SerTransient
   private ByteBuffer hdr;
   @SerTransient
   private List inputStreams = new ArrayList();

   @SerCustomWrite
   private void save(SerializerHelper var1) throws IOException {
      if ((var1.getFlags() & 1) != 0 && this.fulldata == null && this.file != null && this.file.isFile() && this.file.canRead()) {
         try {
            this.fulldata = IO.readFile(this.file);
            this.sha256hash = Hash.calculateSHA256(this.fulldata);
         } catch (IOException var2) {
         }
      }

      var1.saveStandard();
      this.fulldata = null;
   }

   @SerCustomRead
   private void load(DeserializerHelper var1) throws IOException {
      if (var1.getSerializedVersion() == 0) {
         this.path = (String)var1.read();
      } else {
         var1.loadStandard();
      }
   }

   @SerCustomInitPostGraph
   private void init() throws IOException {
      this.inputStreams = new ArrayList();
      this.setup(false);
   }

   public FileInput(File var1) throws IOException {
      this(var1, true, true);
   }

   public FileInput(File var1, boolean var2) throws IOException {
      this(var1, var2, true);
   }

   public FileInput(File var1, boolean var2, boolean var3) throws IOException {
      this(var1.getPath(), var2, var3);
   }

   public FileInput(String var1) throws IOException {
      this(var1, true, true);
   }

   public FileInput(String var1, boolean var2) throws IOException {
      this(var1, var2, true);
   }

   public FileInput(String var1, boolean var2, boolean var3) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("Null file path");
      } else {
         this.path = var1;
         this.setup(var2, var3);
      }
   }

   private boolean setup(boolean var1) throws IOException {
      return this.setup(var1, true);
   }

   private boolean setup(boolean var1, boolean var2) throws IOException {
      try {
         this.file = this.processInput(this.getPath(), 0);
      } catch (IOException var10) {
         if (var1) {
            throw var10;
         }

         return false;
      }

      if (var2) {
         try (
            FileInputStream var3 = new FileInputStream(this.file);
            FileChannel var4 = var3.getChannel();
         ) {
            this.hdr = ByteBuffer.allocate(512);
            int var5 = var4.read(this.hdr);
            this.hdr.limit(Math.max(0, var5));
            this.hdr.position(0);
         }
      }

      return true;
   }

   private File processInput(String var1, int var2) throws IOException {
      File var3 = new File(var1);
      boolean var4 = false;
      if (!var3.isFile()) {
         if (var2 == 0 && this.fulldata != null) {
            logger.debug("Restoring missing artifact file from full data (sha256: %s)", Formatter.byteArrayToHex(this.sha256hash));
            String var19 = Formatter.byteArrayToHex(this.sha256hash) + "_" + IO.splitPath(var3.getPath())[1];
            var3 = IO.createTempFile(var19);
            IO.writeFile(var3, this.fulldata);
            return var3;
         }

         var4 = true;
      }

      if (!var4 && !var3.canRead()) {
         throw new IOException("Cannot read input file: " + var3);
      } else {
         if (var4) {
            var1 = var3.getPath();
            int var5 = var1.lastIndexOf(33);
            if (var5 < 0) {
               throw new IOException("Cannot process input file: " + var3);
            }

            String var6 = var1.substring(0, var5);
            String var7 = var1.substring(var5 + 1).replace('\\', '/');
            File var8 = this.processInput(var6, var2 + 1);

            try (
               ZipBrowser var9 = new ZipBrowser(var8);
               InputStream var10 = var9.getEntryStream(var7);
            ) {
               String var11 = var7.substring(var7.lastIndexOf(47) + 1);
               var3 = IO.createTempFileNumbered(var11 + ".EXT");
               var3.deleteOnExit();
               IO.writeFile(var3, IO.readInputStream(var10));
            }
         }

         if (var2 > 0 && IO.getFirstShortLE(var3.getAbsolutePath()) != 19280) {
            throw new IOException("Expected a zip");
         } else {
            return var3;
         }
      }
   }

   @Override
   public String getName() {
      String var1 = this.getPath();
      if (var1 != null) {
         int var2 = Strings.lastIndexOf2(var1, '/', '\\');
         if (var2 >= 0) {
            var1 = var1.substring(var2 + 1);
         }
      }

      return var1;
   }

   public File getFile() {
      return this.file;
   }

   public String getPath() {
      if (this.path == null) {
         return null;
      } else {
         return this.pathbase != null ? this.pathbase + File.separatorChar + this.path : this.path;
      }
   }

   public String getPersistedPath() {
      return this.path;
   }

   public String getPathBase() {
      return this.pathbase;
   }

   public boolean rebase(String var1) {
      if (this.pathbase == null && !new File(this.path).isAbsolute()) {
         this.pathbase = var1;

         try {
            return this.setup(false);
         } catch (IOException var2) {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean setBase(String var1) {
      if (this.file == null) {
         return false;
      } else if (var1 == null) {
         this.pathbase = null;
         this.path = this.file.getPath();
         return true;
      } else {
         String var2 = IO.simplifyPath(this.file.getAbsolutePath());
         String var3 = IO.simplifyPath(var1);
         String[] var4 = var3.split("[\\\\//]");
         String[] var5 = var2.split("[\\\\//]");
         int var6 = 0;

         while (var6 < var4.length && var6 < var5.length && var4[var6].equals(var5[var6])) {
            var6++;
         }

         int var7 = var4.length - var6;
         String var8 = "";

         for (int var9 = 0; var9 < var7; var9++) {
            var8 = var8 + ".." + File.separatorChar;
         }

         for (int var12 = var6; var12 < var5.length; var12++) {
            var8 = var8 + var5[var12] + File.separatorChar;
         }

         var8 = var8.substring(0, var8.length() - 1);

         try {
            if (!this.file.getCanonicalFile().equals(new File(var3 + File.separatorChar + var8).getCanonicalFile())) {
               return false;
            }
         } catch (IOException var10) {
            return false;
         }

         this.pathbase = var3;
         this.path = var8;
         return true;
      }
   }

   public void setFile(File var1) throws IOException {
      if (this.file != null) {
         throw new IOException("A file is already connected to this input object");
      } else {
         String var2 = this.pathbase;
         this.pathbase = null;
         this.path = var1.getPath();
         this.setup(true);
         if (var2 != null) {
            this.setBase(var2);
         }
      }
   }

   @Override
   public boolean canRead() {
      return this.file != null && this.file.canRead();
   }

   @Override
   public void close() {
      for (InputStream var2 : this.inputStreams) {
         try {
            var2.close();
         } catch (IOException var3) {
         }
      }
   }

   @Override
   public long getCurrentSize() {
      return this.file == null ? 0L : this.file.length();
   }

   @Override
   public ByteBuffer getHeader() {
      return this.file == null ? null : this.hdr.asReadOnlyBuffer();
   }

   @Override
   public SeekableByteChannel getChannel() throws IOException {
      if (this.file == null) {
         throw new IOException("The file does not exist: " + this.file);
      } else {
         FileInputStream var1 = new FileInputStream(this.file);
         this.inputStreams.add(var1);
         return var1.getChannel();
      }
   }

   @Override
   public InputStream getStream() throws IOException {
      if (this.file == null) {
         throw new IOException("The file does not exist: " + this.file);
      } else {
         FileInputStream var1 = new FileInputStream(this.file);
         this.inputStreams.add(var1);
         return var1;
      }
   }

   @Override
   public String toString() {
      return this.file + "";
   }
}
