package com.pnfsoftware.jeb.util.encoding.zip;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipBrowserApache implements IGenericZipBrowser {
   private ZipFile zip;
   private LinkedHashMap entries = new LinkedHashMap();

   public ZipBrowserApache(File var1) throws IOException {
      this.zip = new ZipFile(var1);
      this.parseEntries();
   }

   private void parseEntries() {
      Enumeration var1 = this.zip.getEntries();

      while (var1.hasMoreElements()) {
         ZipArchiveEntry var2 = (ZipArchiveEntry)var1.nextElement();
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
         try (InputStream var3 = this.zip.getInputStream((ZipArchiveEntry)var2.getInternalEntry())) {
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
         return this.zip.getInputStream((ZipArchiveEntry)var2.getInternalEntry());
      } else {
         throw new IOException(Strings.ff("Entry %s is not a valid file entry", var1));
      }
   }
}
