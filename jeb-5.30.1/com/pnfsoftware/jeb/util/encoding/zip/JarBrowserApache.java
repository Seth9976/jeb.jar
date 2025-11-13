package com.pnfsoftware.jeb.util.encoding.zip;

import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;

public class JarBrowserApache extends ZipBrowserApache implements IGenericJarBrowser {
   public JarBrowserApache(File var1) throws IOException {
      super(var1);
   }

   @Override
   public Certificate[] getCertificates(String var1) {
      return null;
   }
}
