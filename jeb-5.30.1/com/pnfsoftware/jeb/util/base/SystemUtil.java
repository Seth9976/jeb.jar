package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class SystemUtil {
   private static final ILogger logger = GlobalLog.getLogger(SystemUtil.class);
   public static final String javavendor = System.getProperty("java.vendor");
   public static final String javaversion = System.getProperty("java.version");
   public static final String javaspecversion = System.getProperty("java.specification.version");
   public static final String osname = System.getProperty("os.name");
   public static final String osarch = System.getProperty("os.arch");
   public static final String osversion = System.getProperty("os.version");

   public static String getJavaVendor() {
      return javavendor;
   }

   public static String getJavaVersion() {
      return javaversion;
   }

   public static String getJavaSpecificationVersion() {
      return javaspecversion;
   }

   public static String getOsName() {
      return osname;
   }

   public static String getOsArchitecture() {
      return osarch;
   }

   public static String getOsVersion() {
      return osversion;
   }

   public static int getMajorJavaVersion() {
      int var0 = parseMajorJavaVersion(javaspecversion);
      if (var0 == 0) {
         var0 = parseMajorJavaVersion(javaversion);
         if (var0 == 0) {
            logger.warn(S.L("Unsupported Java version string: \"%s\""), javaversion);
         }
      }

      return var0;
   }

   public static int parseMajorJavaVersion(String var0) {
      String var1 = var0.trim();
      if (var1.startsWith("1.")) {
         var1 = var1.substring(2);
      }

      int var2 = var1.indexOf(46);
      if (var2 >= 0) {
         var1 = var1.substring(0, var2);
      }

      var2 = var1.indexOf(45);
      if (var2 >= 0) {
         var1 = var1.substring(0, var2);
      }

      try {
         return Integer.parseInt(var1);
      } catch (NumberFormatException var3) {
         return 0;
      }
   }
}
