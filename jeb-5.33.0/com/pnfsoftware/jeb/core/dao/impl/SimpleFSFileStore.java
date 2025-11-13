package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SimpleFSFileStore implements IFileStore {
   private static final ILogger logger = GlobalLog.getLogger(SimpleFSFileStore.class);
   private File base;

   public SimpleFSFileStore(String var1) {
      this.base = new File(var1);
      if (!this.base.exists() && !this.base.mkdirs()) {
         throw new RuntimeException("Cannot create directory");
      } else if (!this.base.isDirectory()) {
         throw new RuntimeException("The specified path is not a directory");
      }
   }

   @Override
   public String getStoreLocation() {
      return this.base.getAbsolutePath();
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
   public boolean has(String var1) {
      File var2 = this.keyToFile(var1);
      return var2.exists();
   }

   @Override
   public boolean remove(String var1) {
      File var2 = this.keyToFile(var1);
      return var2.delete();
   }

   @Override
   public byte[] get(String var1) {
      File var2 = this.keyToFile(var1);
      if (!var2.exists()) {
         Object[] var10000 = new Object[]{var2.getAbsolutePath()};
         return null;
      } else {
         try {
            byte[] var7;
            try (DataInputStream var3 = new DataInputStream(new FileInputStream(var2))) {
               long var4 = var2.length();
               byte[] var6 = new byte[(int)var4];
               var3.readFully(var6);
               var7 = var6;
            }

            return var7;
         } catch (IOException var10) {
            logger.catchingSilent(var10);
            return null;
         }
      }
   }

   @Override
   public String put(byte[] var1) {
      return this.put(null, var1);
   }

   @Override
   public String put(String var1, byte[] var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Input data cannot be null");
      } else {
         if (var1 == null) {
            var1 = Formatter.byteArrayToHexString(Hash.calculateSHA256(var2));
         }

         File var3 = this.keyToFile(var1);

         try {
            String var5;
            try (DataOutputStream var4 = new DataOutputStream(new FileOutputStream(var3))) {
               var4.write(var2);
               var5 = var1;
            }

            return var5;
         } catch (IOException var9) {
            logger.catchingSilent(var9);
            return null;
         }
      }
   }

   @Override
   public List list() {
      throw new RuntimeException("Not implemented");
   }
}
