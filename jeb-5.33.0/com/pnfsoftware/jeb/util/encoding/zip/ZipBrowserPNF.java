package com.pnfsoftware.jeb.util.encoding.zip;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipData;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipFailSafeReader;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class ZipBrowserPNF implements IGenericZipBrowser {
   private static final ILogger logger = GlobalLog.getLogger(ZipBrowserPNF.class);
   private ZipFailSafeReader zip;
   private LinkedHashMap entries = new LinkedHashMap();

   public ZipBrowserPNF(File var1, int var2, boolean var3) throws IOException {
      this.zip = new ZipFailSafeReader(var1, var2, var3, false, false);
      this.parseEntries();
   }

   private void parseEntries() {
      for (ZipEntry var2 : this.zip.getEntries()) {
         java.util.zip.ZipEntry var3 = new java.util.zip.ZipEntry(var2.getFilenameUTF8());
         var3.setSize(var2.getFilesize());
         var3.setCompressedSize(var2.getCompressedSize());
         this.entries.put(var3.getName(), new GenericZipEntry(var3));
      }
   }

   @Override
   public void close() throws IOException {
      this.zip.close();
   }

   @Override
   public Map getEntries() {
      return this.entries;
   }

   @Override
   public GenericZipEntry getEntry(String var1) {
      return (GenericZipEntry)this.entries.get(var1);
   }

   @Override
   public byte[] readEntry(String var1) throws IOException {
      GenericZipEntry var2 = (GenericZipEntry)this.entries.get(var1);
      if (var2 == null) {
         throw new IOException(Strings.ff("Entry %s is not a valid file entry", var1));
      } else {
         ZipData var3 = this.zip.readData(var1);
         if (var3.getException() != null) {
            logger.error(S.L("Entry: %s: ERROR: %s"), var1, var3.getException());
         }

         return var3.getUncompressedData();
      }
   }

   @Override
   public InputStream getEntryStream(String var1) throws IOException {
      return new ByteArrayInputStream(this.readEntry(var1));
   }
}
