package com.pnfsoftware.jebglobal;

import com.microsoft.z3.Context;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.encoding.zip.GenericZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowser;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.URLUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class avv {
   private static final ILogger q = GlobalLog.getLogger(avv.class);
   private static boolean RF;

   private static void RF() throws Throwable {
      URL var0 = Context.class.getProtectionDomain().getCodeSource().getLocation();
      File var1 = URLUtil.urlToFile(var0);
      File var2 = new File(com.pnfsoftware.jeb.util.io.IO.getHomeFolder(), ".jeb/z3");
      if (!var2.isDirectory()) {
         var2.mkdirs();
      }

      OSType var3 = OSType.determine();
      String[] var4 = new String[]{"libz3", "libz3java"};
      if (var3 == OSType.WIN_ARM64) {
         var4[1] = "z3java";
      }

      ArrayList var5 = new ArrayList(var4.length);
      ZipBrowser var6 = new ZipBrowser(var1);

      try {
         String var7 = var3.getFolderName();
         if (var7 == null) {
            throw new RuntimeException("Missing architecture-specific folder for: " + var3);
         }
         String var8 = switch (var3) {
            case WIN64, WIN_ARM64 -> ".dll";
            case OSX64, OSX_ARM64 -> ".dylib";
            case LINUX64, LINUX_ARM64 -> ".so";
            default -> throw new RuntimeException("Missing architecture-specific library extension for: " + var3);
         };

         for (String var12 : var4) {
            String var13 = var12 + var8;
            String var14 = ".z3libs/" + var7 + "/" + var13;
            GenericZipEntry var15 = var6.getEntry(var14);
            if (var15 == null) {
               throw new IOException("Cannot find entry: " + var14);
            }

            File var16 = new File(var2, var13);
            if (!var16.exists() || var16.length() != var15.getSize()) {
               try (
                  InputStream var17 = var6.getEntryStream(var15.getName());
                  FileOutputStream var18 = new FileOutputStream(var16);
               ) {
                  com.pnfsoftware.jeb.util.io.IO.copyStream(var17, var18);
               }
            }

            var5.add(var16);
         }
      } catch (Throwable var26) {
         try {
            var6.close();
         } catch (Throwable var21) {
            var26.addSuppressed(var21);
         }

         throw var26;
      }

      var6.close();
      if (var3 == OSType.OSX64) {
         File var27 = new File(com.pnfsoftware.jeb.util.io.IO.getOriginalCwd());
         if (var27.isDirectory() && Arrays.asList(var27.list()).contains("jeb_macos.sh")) {
            for (File var32 : var5) {
               File var33 = new File(var27, var32.getName());
               if (!var33.exists()) {
                  com.pnfsoftware.jeb.util.io.IO.copyFile(var32, var33, false);
               }
            }
         }
      } else {
         for (File var31 : var5) {
            System.load(var31.getAbsolutePath());
         }

         System.setProperty("z3.skipLibraryLoad", "true");
      }

      Context var29 = new Context();
      var29.close();
   }

   public static final boolean q() {
      return RF;
   }

   static {
      try {
         RF();
         RF = true;
         q.debug("Z3 initialization is done.");
      } catch (Throwable var1) {
         q.debug("Z3 initialization failed! Some gendec/dexdec optimizers relying on that solver will be disabled.");
         q.catchingSilent(var1);
      }
   }
}
