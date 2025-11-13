package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.base.Env;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class AndroidSdkUtil {
   private static final ILogger logger = GlobalLog.getLogger(AndroidSdkUtil.class);
   public static final String legacySdkLocationVar = "ANDROID_SDK_ROOT";
   public static final String currentSdkLocationVar = "ANDROID_HOME";
   private static final List sdkLocationVars = Arrays.asList("ANDROID_SDK_ROOT", "ANDROID_HOME");
   private static final String singleNdkDir = "ndk-bundle";
   private static final String multiNdksDir = "ndk";
   private static File sdkFolder = null;
   private static SortedMap ndkFolders = null;
   private static File bestNdkFolder = null;

   public static File locateSDK(boolean var0) throws IOException {
      if (sdkFolder != null) {
         if (var0) {
            return sdkFolder;
         }

         sdkFolder = null;
      }

      OSType var1 = OSType.determine();
      ArrayList var2 = new ArrayList();

      for (String var4 : sdkLocationVars) {
         String var5 = Env.get(var4);
         if (!Strings.isBlank(var5)) {
            logger.debug("%s: Environment variable added to list of candidate Android SDK locations", var4);
            var2.add(new File(IO.expandPath(var5)).getAbsolutePath());
         } else {
            logger.debug("%s: Environment variable NOT found", var4);
         }
      }

      if (var2.isEmpty()) {
         String var7 = null;
         if (var1.isWindows()) {
            var7 = Env.getsafe("LOCALAPPDATA") + "\\Android\\sdk";
         } else if (var1.isMac()) {
            var7 = "~/Library/Android/sdk";
         } else if (var1.isLinux()) {
            var7 = "~/Android/Sdk";
         }

         if (var7 != null) {
            logger.debug("%s: OS-specific location found and added to list of candidate Android SDK locations", var7);
            var2.add(new File(IO.expandPath(var7)).getAbsolutePath());
         }
      }

      if (var2.isEmpty()) {
         String var8 = "ANDROID_SDK_HOME";
         String var10 = Env.getsafe(var8);
         if (!Strings.isBlank(var10)) {
            logger.debug(
               "%s: WARNING: Unofficial environment variable added to list of candidate Android SDK locations. (You should use one of the following instead: %s.)",
               var8,
               sdkLocationVars
            );
            var2.add(new File(IO.expandPath(var10)).getAbsolutePath());
         }
      }

      String var9 = Env.get("PATH");
      if (!Strings.isBlank(var9)) {
         logger.debug("PATH: added to list of searched folders: %s", var9);
         var2.addAll(Arrays.asList(var9.split(File.pathSeparator)));
      }

      logger.debug("Checking candidate SDK folders, by order: %s", var2);

      for (String var12 : var2) {
         File var6 = new File(var12);
         if (var6.isDirectory()) {
            logger.debug("Found Android SDK folder: %s", var6);
            sdkFolder = var6;
            return var6;
         }
      }

      throw new IOException(
         "The Android Debug Bridge tool (ADB) was not found. Make sure the Android SDK is installed and reference it via the $ANDROID_HOME environment variable."
      );
   }

   public static SortedMap listNDKFolders(boolean var0) throws IOException {
      if (ndkFolders != null && var0) {
         return Collections.unmodifiableSortedMap(ndkFolders);
      } else {
         File var1 = locateSDK(var0);
         TreeMap var2 = new TreeMap();
         File var3 = new File(var1, "ndk-bundle");
         if (var3.isDirectory()) {
            var2.put("", var3);
         }

         File var4 = new File(var1, "ndk");
         if (var4.isDirectory()) {
            for (String var8 : var4.list()) {
               if (!var8.isEmpty()) {
                  var3 = new File(var4, var8);
                  if (var3.isDirectory() && Character.isDigit(var8.charAt(0))) {
                     var2.put(var8, var3);
                  }
               }
            }
         }

         ndkFolders = var2;
         return var2;
      }
   }

   public static File locateBestNDK(boolean var0) throws IOException {
      if (bestNdkFolder != null) {
         if (var0) {
            return bestNdkFolder;
         }

         bestNdkFolder = null;
      }

      SortedMap var1 = listNDKFolders(var0);
      if (var1.isEmpty()) {
         throw new IOException();
      } else {
         bestNdkFolder = (File)var1.get(var1.lastKey());
         return bestNdkFolder;
      }
   }
}
