package com.pnfsoftware.jeb.util.encoding.zip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Map;

public class JarBrowser implements IGenericJarBrowser {
   private IGenericJarBrowser browser;

   public JarBrowser(File var1, boolean var2) throws IOException {
      try {
         this.browser = new JarBrowserOracle(var1, var2);
      } catch (IOException var3) {
         this.browser = new JarBrowserApache(var1);
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

   @Override
   public Certificate[] getCertificates(String var1) {
      return this.browser.getCertificates(var1);
   }
}
