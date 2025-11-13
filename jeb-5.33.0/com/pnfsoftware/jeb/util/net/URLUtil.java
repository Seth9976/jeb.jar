package com.pnfsoftware.jeb.util.net;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URLUtil {
   public static File urlToFile(URL var0) {
      try {
         String var1 = var0.toExternalForm();
         var1 = var1.replace(" ", "%20").replace("\"", "%22").replace("#", "%23").replace("<", "%3C").replace(">", "%3E");
         URI var2 = new URI(var1);
         return new File(var2.getSchemeSpecificPart());
      } catch (URISyntaxException var3) {
         return new File(var0.getPath());
      }
   }
}
