package com.pnfsoftware.jeb;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;

public class AssetManager {
   private static final ILogger q = GlobalLog.getLogger(AssetManager.class);

   public static boolean q(String var0) {
      return AssetManager.class.getResourceAsStream("assets/" + var0) != null;
   }

   public static InputStream getAsset(String var0) throws IOException {
      InputStream var1 = AssetManager.class.getResourceAsStream("assets/" + var0);
      if (var1 == null) {
         throw new IOException(Strings.ff("Asset \"%s\" does not exist", var0));
      } else {
         return var1;
      }
   }

   public static byte[] RF(String var0) {
      try {
         byte[] var2;
         try (InputStream var1 = getAsset(var0)) {
            var2 = IO.readInputStream(var1);
         }

         return var2;
      } catch (IOException var6) {
         q.catching(var6);
         return null;
      }
   }

   public static int xK(String var0) {
      try {
         int var2;
         try (InputStream var1 = getAsset(var0)) {
            var2 = var1.available();
         }

         return var2;
      } catch (IOException var6) {
         q.catching(var6);
         return -1;
      }
   }
}
