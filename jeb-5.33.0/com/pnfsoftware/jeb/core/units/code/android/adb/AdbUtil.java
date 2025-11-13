package com.pnfsoftware.jeb.core.units.code.android.adb;

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
import java.util.List;

public class AdbUtil {
   private static final ILogger logger = GlobalLog.getLogger(AdbUtil.class);
   public static final String adbName = "adb";
   public static final String legacySdkLocationVar = "ANDROID_SDK_ROOT";
   public static final String currentSdkLocationVar = "ANDROID_HOME";
   private static final List sdkLocationVars = Arrays.asList("ANDROID_SDK_ROOT", "ANDROID_HOME");
   private static final String sdkToolsDir = "platform-tools";

   public static File findAdbOnCurrentSystem() throws IOException {
      OSType var0 = OSType.determine();
      ArrayList var1 = new ArrayList();

      for (String var3 : sdkLocationVars) {
         String var4 = Env.get(var3);
         if (!Strings.isBlank(var4)) {
            logger.debug("[adb] %s: Environment variable added to list of candidate Android SDK locations", var3);
            var1.add(new File(IO.expandPath(var4), "platform-tools").getAbsolutePath());
         } else {
            logger.debug("[adb] %s: Environment variable NOT found", var3);
         }
      }

      if (var1.isEmpty()) {
         String var7 = null;
         if (var0.isWindows()) {
            var7 = Env.getsafe("LOCALAPPDATA") + "\\Android\\sdk";
         } else if (var0.isMac()) {
            var7 = "~/Library/Android/sdk";
         } else if (var0.isLinux()) {
            var7 = "~/Android/Sdk";
         }

         if (var7 != null) {
            logger.debug("[adb] %s: OS-specific location found and added to list of candidate Android SDK locations", var7);
            var1.add(new File(IO.expandPath(var7), "platform-tools").getAbsolutePath());
         }
      }

      if (var1.isEmpty()) {
         String var8 = "ANDROID_SDK_HOME";
         String var10 = Env.getsafe(var8);
         if (!Strings.isBlank(var10)) {
            logger.debug(
               "[adb] %s: WARNING: Unofficial environment variable added to list of candidate Android SDK locations. (You should use one of the following instead: %s.)",
               var8,
               sdkLocationVars
            );
            var1.add(new File(IO.expandPath(var10), "platform-tools").getAbsolutePath());
         }
      }

      String var9 = Env.get("PATH");
      if (!Strings.isBlank(var9)) {
         logger.debug("[adb] PATH: added to list of searched folders: %s", var9);
         var1.addAll(Arrays.asList(var9.split(File.pathSeparator)));
      }

      String var11 = "adb";
      if (var0.isWindows()) {
         var11 = var11 + ".exe";
      }

      logger.debug("[adb] Searching for '%s' in folders, by order: %s", var11, var1);

      for (String var5 : var1) {
         File var6 = new File(var5, var11);
         if (var6.isFile()) {
            logger.debug("[adb] Found and using: %s", var6);
            return var6;
         }
      }

      throw new IOException(
         "The Android Debug Bridge tool (ADB) was not found. Make sure the Android SDK is installed and reference it via the $ANDROID_HOME environment variable."
      );
   }

   static boolean heurSuccess(byte[] var0) {
      if (var0 == null) {
         return false;
      } else {
         String var1 = Strings.decodeLocal(var0);
         return var1.contains("adb: error") ? false : !var1.contains("No such file");
      }
   }
}
