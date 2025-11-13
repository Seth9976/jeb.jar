package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AndroidDeviceUtil {
   private static final ILogger logger = GlobalLog.getLogger(AndroidDeviceUtil.class);

   public static String uploadFileToDeviceTemp(AdbWrapper var0, String var1, AndroidPlatformABI var2) throws IOException {
      return uploadFileToDeviceTemp(var0, var1, var2, null, null);
   }

   public static String uploadFileToDeviceTemp(AdbWrapper var0, String var1, AndroidPlatformABI var2, String var3, String var4) throws IOException {
      if (var0 != null && var1 != null) {
         AndroidPlatformABI var5 = null;
         if (var2 != null) {
            switch (var2) {
               case ARM:
                  var5 = AndroidPlatformABI.ARM7;
            }
         }

         try {
            return __uploadFileToDeviceTemp(var0, var1, var2, var3, var4);
         } catch (IOException var8) {
            if (var5 != null) {
               try {
                  return __uploadFileToDeviceTemp(var0, var1, var5, var3, var4);
               } catch (IOException var7) {
               }
            }

            throw var8;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   private static String __uploadFileToDeviceTemp(AdbWrapper var0, String var1, AndroidPlatformABI var2, String var3, String var4) throws IOException {
      File var5 = null;

      String var9;
      try {
         String var6 = "android-bin/";
         if (var2 != null) {
            var6 = var6 + var2.toString() + "/";
         }

         var6 = var6 + var1;
         if (!Strings.isBlank(var3)) {
            var6 = var6 + "-" + var3;
         }

         if (!Strings.isBlank(var4)) {
            var6 = var6 + "." + var4;
         }

         byte[] var7 = AssetManager.getAssetBytes(var6);
         if (var7 == null) {
            throw new IOException("Could not retrieve Android asset: " + var6);
         }

         var5 = File.createTempFile("android-tmpfile-", null);
         IO.writeFile(var5, var7);
         String var8 = "/data/local/tmp/" + var1;
         if (!var0.pushFile(var5.getAbsolutePath(), var8)) {
            throw new IOException(Strings.ff("Could not upload file to device: src=%s, dst=%s", var6, var8));
         }

         var9 = var8;
      } finally {
         if (var5 != null) {
            var5.delete();
         }
      }

      return var9;
   }

   public static boolean isRootDevice(AdbWrapper var0) {
      String var1 = var0.getToolName("su");
      if (var0.hasFile(null, "/system/xbin/" + var1)) {
         return true;
      } else if (var0.which(null, var1) != null) {
         return true;
      } else {
         String[] var2 = new String[]{
            "/bin/" + var1,
            "/sbin/" + var1,
            "/system/bin/" + var1,
            "/system/xbin/" + var1,
            "/data/local/" + var1,
            "/data/local/bin/" + var1,
            "/data/local/xbin/" + var1,
            "/system/sd/xbin/" + var1,
            "/system/bin/failsafe/" + var1,
            "/su/bin/" + var1
         };

         for (String var6 : var2) {
            if (var0.hasFile(null, var6)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean executeCommandAsRoot(AdbWrapper var0, String var1) throws AdbException {
      return executeCommandAsRoot(var0, true, var1);
   }

   public static boolean executeCommandAsRoot(AdbWrapper var0, boolean var1, String var2) throws AdbException {
      return executeCommandsAsRoot(var0, var1, new String[]{var2});
   }

   public static boolean executeCommandsAsRoot(AdbWrapper var0, String[] var1) throws AdbException {
      return executeCommandsAsRoot(var0, true, var1);
   }

   public static boolean executeCommandsAsRoot(AdbWrapper var0, boolean var1, String[] var2) throws AdbException {
      if (var1 && !isRootDevice(var0)) {
         throw new AdbException("The device is not rooted: unable to find 'su'");
      } else {
         byte var3 = 0;
         AdbResult var4 = null;

         for (int var5 = 0; var5 < var2.length; var5++) {
            String var6 = "\"" + var2[var5] + "\"";
            String var7 = var2[var5].contains(" ") ? var2[var5].substring(0, var2[var5].indexOf(32)) : var2[var5];
            if (var3 == 0) {
               String var8 = var0.getToolName("su");
               var4 = var0.shell(null, null, null, Arrays.asList(var8, "-c", var6), !hasCommandResult(var7));
               if (!var4.isSuccess(null, true, "invalid uid/gid '-c'")) {
                  var3 = 1;
               }
            }

            if (var3 == 1) {
               String var10 = var0.getToolName("su");
               var4 = var0.shell(null, null, null, Arrays.asList(Strings.join(" ", Arrays.asList(var10, "0", var6))), !hasCommandResult(var7));
               if (!var4.isSuccess(null, true)) {
                  var3 = 2;
               }
            }

            if (var3 == 2) {
               var4 = var0.shell(null, null, null, Arrays.asList(var2[var5].split(" ")), !hasCommandResult(var7));
            }

            String var11 = var4.getOutputString();
            if (var11.contains("Read-only file system")) {
               String var13 = "/system can not be made writable on current device.";
               var13 = var13 + "\nIf working with an emulator, try to add the option '-writable-system' to your emulator";
               throw new AdbException(var13);
            }

            if (var11.contains("not user mountable") || var11.contains("Operation not permitted")) {
               String var9 = "The device is not rooted";
               var9 = var9 + "\nIf working with an emulator, you may need to execute 'adb root' to force your device being rooted.";
               throw new AdbException(var9);
            }

            if (!var4.isSuccess(null, true)) {
               return false;
            }
         }

         return true;
      }
   }

   private static boolean hasCommandResult(String var0) {
      switch (var0) {
         case "chmod":
         case "chown":
         case "rm":
         case "cp":
         case "mount":
            return false;
         default:
            return true;
      }
   }

   public static boolean pullFile(AdbWrapper var0, String var1, String var2) {
      boolean var3 = var0.pullFile(var1, var2);
      if (var3) {
         return true;
      } else {
         String var5 = null;
         if (var1.startsWith("/data/data/")) {
            var5 = var1.substring("/data/data/".length());
            int var6 = var5.indexOf(47);
            if (var6 >= 0) {
               var5 = var5.substring(0, var6);
            }
         }

         if (var5 != null && var5.length() > 0) {
            String var10 = "/data/local/tmp/tempfile-" + System.nanoTime();
            if (var0.copyFile(var5, var1, var10, false)) {
               var3 = var0.pullFile(var10, var2);
               if (var3) {
                  return true;
               }
            }

            if (var0.chmod(var5, var1, 365, false)) {
               var3 = var0.pullFile(var1, var2);
               if (var3) {
                  return true;
               }

               if (var0.copyFile(null, var1, var10, false) && var0.pullFile(var10, var2)) {
                  return true;
               }
            }
         }

         if (!isRootDevice(var0)) {
            return false;
         } else {
            try {
               String var4 = "/data/local/tmp/tempfile-" + System.nanoTime();
               if (executeCommandAsRoot(var0, false, Strings.ff("cp %s %s", var1, var4))) {
                  executeCommandAsRoot(var0, false, "chown shell:shell " + var4);
                  executeCommandAsRoot(var0, false, "chmod 555 " + var4);
                  if (var0.pullFile(var4, var2)) {
                     return true;
                  }
               }

               return false;
            } catch (AdbException var7) {
               return false;
            }
         }
      }
   }

   public static List getSupportedABIs(AdbWrapper var0) {
      String var1 = var0.readProperty("ro.product.cpu.abilist").trim();
      if (Strings.isBlank(var1)) {
         var1 = var0.readProperty("ro.product.cpu.abi").trim();
      }

      ArrayList var2 = new ArrayList();

      for (String var6 : var1.split(",")) {
         AndroidPlatformABI var7 = AndroidPlatformABI.fromName(var6);
         if (var7 != null) {
            var2.add(var7);
         }
      }

      return var2;
   }

   public static AndroidPlatformABI getPreferredABI(AdbWrapper var0) {
      return (AndroidPlatformABI)Lists.getFirst(getSupportedABIs(var0));
   }

   public static AndroidPlatformABI getBestABIForApp(AdbWrapper var0, IApkUnit var1) {
      IUnit var2 = UnitUtil.findFirstChildByName(var1, "Libraries");
      if (var2 != null) {
         for (AndroidPlatformABI var5 : getSupportedABIs(var0)) {
            if (UnitUtil.findFirstChildByName(var2, var5.toString()) != null) {
               return var5;
            }
         }
      }

      return getPreferredABI(var0);
   }

   public static List getProcessesByName(AdbWrapper var0, String var1) {
      ArrayList var2 = new ArrayList();
      List var3 = var0.listProcesses();
      if (var3 != null) {
         for (AdbProcess var5 : var0.listProcesses()) {
            if (Strings.equals(var5.getName(), var1)) {
               var2.add(var5.getPid());
            }
         }
      }

      return var2;
   }

   public static int getAndroidApiLevel(AdbWrapper var0) {
      String var1 = var0.readProperty("ro.build.version.sdk");
      return Conversion.stringToInt(var1);
   }

   public static boolean isDebuggableApp(AdbWrapper var0, String var1) {
      AdbResult var2 = var0.shell(Arrays.asList("dumpsys", "package", var1));
      if (!var2.isSuccess()) {
         return false;
      } else {
         String var3 = var2.getOutputString();

         for (String var7 : Strings.splitLines(var3)) {
            if (var7.contains("flags")) {
               return var7.contains("DEBUGGABLE");
            }
         }

         return false;
      }
   }
}
