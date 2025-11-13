package com.pnfsoftware.jeb.util.encoding.zip;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarBrowserOracle implements IGenericJarBrowser {
   private JarFile jar;
   private LinkedHashMap entries = new LinkedHashMap();

   public JarBrowserOracle(File var1, boolean var2) throws IOException {
      this.jar = new JarFile(var1, var2);
      this.parseEntries();
   }

   private void parseEntries() {
      Enumeration var1 = this.jar.entries();

      while (var1.hasMoreElements()) {
         JarEntry var2 = (JarEntry)var1.nextElement();
         this.entries.put(var2.getName(), new GenericZipEntry(var2));
      }
   }

   @Override
   public void close() throws IOException {
      this.jar.close();
   }

   @Override
   public Map getEntries() {
      LinkedHashMap var1 = new LinkedHashMap();

      for (String var3 : this.entries.keySet()) {
         var1.put(var3, (GenericZipEntry)this.entries.get(var3));
      }

      return var1;
   }

   @Override
   public GenericZipEntry getEntry(String var1) {
      return (GenericZipEntry)this.entries.get(var1);
   }

   @Override
   public byte[] readEntry(String var1) throws IOException {
      GenericZipEntry var2 = (GenericZipEntry)this.entries.get(var1);
      if (var2 != null && !var2.isDirectory()) {
         try {
            byte[] var5;
            try (InputStream var3 = this.jar.getInputStream(var2.getInternalEntry())) {
               byte[] var4 = IO.readInputStream(var3);
               if (var4.length != var2.getSize()) {
                  throw new IOException();
               }

               var5 = var4;
            }

            return var5;
         } catch (IOException var8) {
            return null;
         }
      } else {
         throw new IOException(Strings.ff("Entry %s is not a valid file entry", var1));
      }
   }

   @Override
   public InputStream getEntryStream(String var1) throws IOException {
      GenericZipEntry var2 = (GenericZipEntry)this.entries.get(var1);
      return var2 != null && !var2.isDirectory() ? this.jar.getInputStream(var2.getInternalEntry()) : null;
   }

   @Override
   public Certificate[] getCertificates(String var1) {
      GenericZipEntry var2 = (GenericZipEntry)this.entries.get(var1);
      if (var2 != null && !var2.isDirectory()) {
         try {
            Certificate[] var5;
            try (InputStream var3 = this.jar.getInputStream(var2.getInternalEntry())) {
               byte[] var4 = IO.readInputStream(var3);
               if (var4.length != var2.getSize()) {
                  throw new IOException("Unexpected decompressed size");
               }

               var5 = ((JarEntry)var2.getInternalEntry()).getCertificates();
            }

            return var5;
         } catch (IOException var8) {
            return null;
         }
      } else {
         return null;
      }
   }
}
