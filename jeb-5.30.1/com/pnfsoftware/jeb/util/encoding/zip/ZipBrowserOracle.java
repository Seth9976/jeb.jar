package com.pnfsoftware.jeb.util.encoding.zip;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipBrowserOracle implements IGenericZipBrowser {
   public static final String[] csnamesTrylist = new String[]{"UTF-8", "ISO-8859-1"};
   private ZipFile zip;
   private LinkedHashMap entries = new LinkedHashMap();

   public ZipBrowserOracle(File var1) throws IOException {
      for (String var5 : csnamesTrylist) {
         Charset var6 = Charset.forName(var5);

         try {
            this.zip = new ZipFile(var1, 1, var6);
            break;
         } catch (IllegalArgumentException var8) {
            if (!"MALFORMED".equals(var8.getMessage())) {
               throw var8;
            }
         }
      }

      this.parseEntries();
   }

   public ZipBrowserOracle(File var1, Charset var2) throws IOException {
      this.zip = new ZipFile(var1, 1, var2);
   }

   private void parseEntries() {
      Enumeration var1 = this.zip.entries();

      while (var1.hasMoreElements()) {
         ZipEntry var2 = (ZipEntry)var1.nextElement();
         this.entries.put(var2.getName(), new GenericZipEntry(var2));
      }
   }

   @Override
   public void close() throws IOException {
      this.zip.close();
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
         byte[] var4;
         try (InputStream var3 = this.zip.getInputStream(var2.getInternalEntry())) {
            var4 = IO.readInputStream(var3);
         }

         return var4;
      } else {
         throw new IOException(Strings.ff("Entry %s is not a valid file entry", var1));
      }
   }

   @Override
   public InputStream getEntryStream(String var1) throws IOException {
      GenericZipEntry var2 = (GenericZipEntry)this.entries.get(var1);
      if (var2 != null && !var2.isDirectory()) {
         return this.zip.getInputStream(var2.getInternalEntry());
      } else {
         throw new IOException(Strings.ff("Entry %s is not a valid file entry", var1));
      }
   }
}
