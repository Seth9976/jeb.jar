package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.core.dao.IFileDatabase;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseReader;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseWriter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JDB2Manager implements IFileDatabase {
   private static final ILogger logger = GlobalLog.getLogger(JDB2Manager.class);
   public static final String EXTENSION = ".jdb2";
   public static final int MARKER = 843203658;
   public static final int TYPE_PROJECT = 4870736;
   public static final int TYPE_QSTATES = 4932433;
   public static final int TYPE_ARTIFACTS = 5526081;
   public static final int TYPE_PROJECT_CONFIGURATION = 4411472;
   public static final int TYPE_PROJECT_METADATA = 5066832;
   public static final int FLAG_NONE = 0;
   public static final int FLAG_COMPRESS = 1;
   public static final int FLAG_ENCRYPT = 2;
   public static final int FLAG_CRCCHECK = 4;
   static final int FLAGS_MASK = -8;
   private File base;

   public JDB2Manager(String var1) {
      this.base = new File(var1);
      if (!this.base.exists() && !this.base.mkdirs()) {
         throw new RuntimeException("Cannot create directory");
      } else if (!this.base.isDirectory()) {
         throw new RuntimeException("The specified path is not a directory");
      }
   }

   private String buildStoreKey(String var1, int var2) {
      return Strings.ff("%s", var1);
   }

   private File keyToFile(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Key should not be null");
      } else {
         File var2 = new File(var1);
         if (!var2.isAbsolute()) {
            var2 = new File(this.base, var1);
         }

         return var2;
      }
   }

   @Override
   public boolean saveFile(String var1, byte[] var2) {
      File var3 = this.keyToFile(var1);

      try {
         boolean var10;
         try (LEDataOutputStream var4 = new LEDataOutputStream(new DataOutputStream(new FileOutputStream(var3)))) {
            var4.writeInt(843203658);
            var4.writeInt(AbstractContext.app_ver.toInt());

            for (int var5 = 2; var5 < 8; var5++) {
               var4.writeInt(0);
            }

            var4.writeInt(4870736);
            var4.writeInt(var2.length);
            var4.write(var2);
            var10 = true;
         }

         return var10;
      } catch (IOException var9) {
         logger.catching(var9);
         return false;
      }
   }

   @Override
   public byte[] loadFile(String var1) {
      File var2 = this.keyToFile(var1);
      if (!var2.exists()) {
         return null;
      } else {
         try {
            byte[] var7;
            try (LEDataInputStream var3 = new LEDataInputStream(new DataInputStream(new FileInputStream(var2)))) {
               if (var3.readInt() != 843203658) {
                  throw new IOException("Illegal JDB2 marker");
               }

               var3.skipBytes(4);
               var3.skipBytes(24);
               if (var3.readInt() != 4870736) {
                  throw new IOException("Illegal JDB2 block: a project entry was expected");
               }

               int var4 = var3.readInt();
               byte[] var5 = new byte[var4];
               int var6 = var3.read(var5, 0, var4);
               if (var6 != var4) {
                  throw new IOException(Strings.ff("Illegal JDB2 block size: %d bytes were expected, got %d", var4, var6));
               }

               var7 = var5;
            }

            return var7;
         } catch (Exception var10) {
            logger.catching(var10);
            return null;
         }
      }
   }

   @Override
   public boolean hasFile(String var1) {
      File var2 = this.keyToFile(var1);
      return var2.exists();
   }

   @Override
   public File getFileObject(String var1) {
      return this.keyToFile(var1);
   }

   @Override
   public boolean deleteFile(String var1) {
      File var2 = this.keyToFile(var1);
      return var2.delete();
   }

   @Override
   public InputStream getFileReader(String var1) throws FileNotFoundException {
      String var2 = this.buildStoreKey(var1, 0);
      File var3 = this.keyToFile(var2);
      return new FileInputStream(var3);
   }

   @Override
   public OutputStream getFileWriter(String var1) throws FileNotFoundException {
      String var2 = this.buildStoreKey(var1, 0);
      File var3 = this.keyToFile(var2);
      return new FileOutputStream(var3);
   }

   @Override
   public IFileDatabaseWriter getDatabaseWriter(String var1) throws IOException {
      String var2 = this.buildStoreKey(var1, 0);
      File var3 = this.keyToFile(var2);
      return new JDB2Writer(var3);
   }

   @Override
   public IFileDatabaseReader getDatabaseReader(String var1) throws IOException {
      String var2 = this.buildStoreKey(var1, 0);
      File var3 = this.keyToFile(var2);
      return new JDB2Reader(var3);
   }

   public static String projectTypeToName(int var0) {
      switch (var0) {
         case 4411472:
            return "PROJECT_CONFIGURATION";
         case 4870736:
            return "PROJECT";
         case 4932433:
            return "QSTATES";
         case 5066832:
            return "PROJECT_METADATA";
         case 5526081:
            return "ARTIFACTS";
         default:
            return Strings.ff("0x%X", var0);
      }
   }
}
