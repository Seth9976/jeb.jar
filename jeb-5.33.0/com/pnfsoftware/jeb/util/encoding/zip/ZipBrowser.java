package com.pnfsoftware.jeb.util.encoding.zip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ZipBrowser implements IGenericZipBrowser {
   private IGenericZipBrowser browser;

   public ZipBrowser(File var1) throws IOException {
      try {
         this.browser = new ZipBrowserOracle(var1);
      } catch (Exception var2) {
         this.browser = new ZipBrowserApache(var1);
      }
   }

   @Override
   public void close() throws IOException {
      this.browser.close();
   }

   @Override
   public Map getEntries() {
      return this.browser.getEntries();
   }

   @Override
   public GenericZipEntry getEntry(String var1) {
      return this.browser.getEntry(var1);
   }

   @Override
   public byte[] readEntry(String var1) throws IOException {
      return this.browser.readEntry(var1);
   }

   @Override
   public InputStream getEntryStream(String var1) throws IOException {
      return this.browser.getEntryStream(var1);
   }
}
