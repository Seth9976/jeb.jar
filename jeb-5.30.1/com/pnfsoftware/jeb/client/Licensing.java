package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.AssetManager;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.URLUtil;
import com.pnfsoftware.jebglobal.FL;
import com.pnfsoftware.jebglobal.cvm;
import java.io.File;

public final class Licensing {
   public static int loaded;
   private static final ILogger logger = GlobalLog.getLogger(Licensing.class);
   private static final String LICENSE_FILENAME = "jeb-license.txt";
   public static boolean hasDecompDex = true;
   public static boolean hasDecompArm = true;
   public static boolean hasDecompX86 = true;
   public static boolean hasDecompMips = true;
   public static boolean hasDecompRiscv = true;
   public static boolean hasDecompWasm = true;
   public static boolean hasDecompEvm = true;
   public static boolean hasDecompS7 = true;
   public static String user_name = "CXV";
   public static String user_group = "PNF";
   public static String user_email = "CXV@CXV.com";
   public static int user_id = 20250809;
   public static long license_id;
   public static int user_count;
   private static int license_ts;
   private static int license_validity = 1;
   private static int real_license_ts;
   public static String buildkey = "Super-Black Edition by CXV";
   public static int build_type = 254;
   private static final int FLAG_DEBUG = 1;
   private static final int FLAG_FULL = 2;
   private static final int FLAG_FLOATING = 4;
   private static final int FLAG_AIRGAP = 8;
   private static final int FLAG_ANYCLIENT = 16;
   private static final int FLAG_COREAPI = 32;
   private static final int FLAG_PERPETUAL = 64;
   private static final int FLAG_JEB2 = 128;

   public static final void setLicenseTimestamp(int real_license_ts) {
      if (real_license_ts > 0 && license_ts == 0) {
         Licensing.real_license_ts = real_license_ts;
      }
   }

   public static final int getLicenseTimestamp() {
      return real_license_ts;
   }

   public static final int getExpirationTimestamp() {
      return 2000000000;
   }

   public static final int getBuildType() {
      return build_type;
   }

   public static final boolean isDebugBuild() {
      return (build_type & 1) != 0;
   }

   public static final boolean isReleaseBuild() {
      return !isDebugBuild();
   }

   public static final boolean isFullBuild() {
      return (build_type & 2) != 0;
   }

   public static final boolean isDemoBuild() {
      return !isFullBuild();
   }

   public static final boolean isFloatingBuild() {
      return (build_type & 4) != 0;
   }

   public static final boolean isIndividualBuild() {
      return !isFloatingBuild();
   }

   public static final boolean isAirgapBuild() {
      return (build_type & 8) != 0;
   }

   public static final boolean isInternetRequired() {
      return !isAirgapBuild();
   }

   public static final boolean allowAnyClient() {
      return (build_type & 16) != 0;
   }

   public static final boolean canUseCoreAPI() {
      return (build_type & 32) != 0;
   }

   public static final boolean isPerpetual() {
      return (build_type & 64) != 0;
   }

   public static final boolean isSubscription() {
      return !isPerpetual();
   }

   public static final boolean isCommunityEdition() {
      return false;
   }

   public static final boolean isFree() {
      return false;
   }

   public static final boolean isPro() {
      return true;
   }

   public static boolean isCommonBuild() {
      return false;
   }

   public static final String getBuildTypeString() {
      StringBuilder sb = new StringBuilder();
      if (isReleaseBuild()) {
         sb.append(cvm.q(new byte[]{-46, 23, 9, 9, 4, 18, 22, 74}, 1, 160));
      } else {
         sb.append(cvm.q(new byte[]{-47, 1, 7, 23, 18, 72}, 1, 181));
      }

      if (isFullBuild()) {
         sb.append(cvm.q(new byte[]{-106, 19, 25, 0, 67}, 1, 240));
      } else {
         sb.append(cvm.q(new byte[]{-116, 1, 8, 2, 64}, 1, 232));
      }

      if (isFloatingBuild()) {
         sb.append(cvm.q(new byte[]{24, 10, 3, 14, 21, 29, 7, 9, 72}, 1, 126));
      } else {
         sb.append(cvm.q(new byte[]{-70, 7, 10, 13, 31, 31, 13, 17, 20, 13, 67}, 1, 211));
      }

      if (isAirgapBuild()) {
         sb.append(cvm.q(new byte[]{40, 8, 27, 95, 74, 6, 17, 95}, 1, 73));
      } else {
         sb.append(cvm.q(new byte[]{-91, 23, 20, 92, 68, 7, 26, 17, 23, 28, 11, 17, 91}, 1, 215));
      }

      if (allowAnyClient()) {
         sb.append(cvm.q(new byte[]{-60, 15, 23, 84, 78, 15, 5, 12, 11, 26, 91}, 1, 165));
      } else {
         sb.append(cvm.q(new byte[]{44, 9, 22, 16, 17, 0, 6, 4, 89, 67, 68, 10, 76, 78, 69, 22}, 2, 62));
      }

      if (canUseCoreAPI()) {
         sb.append(cvm.q(new byte[]{-71, 12, 29, 23, 72, 76, 17, 25, 70}, 1, 218));
      } else {
         sb.append(cvm.q(new byte[]{-11, 1, 66, 78, 12, 29, 23, 72, 76, 17, 25, 70}, 1, 155));
      }

      if (isPerpetual()) {
         sb.append(cvm.q(new byte[]{51, 10, 2, 9, 23, 29, 18, 9, 24}, 2, 26));
      } else {
         sb.append(cvm.q(new byte[]{48, 26, 18, 10, 17, 27, 14, 24, 0, 73, 71, 13}, 2, 183));
      }

      return sb.toString();
   }

   public static String getLicense() {
      byte[] rf = AssetManager.RF("LICENSE.TXT");
      if (rf == null) {
         return null;
      } else {
         String s = Strings.decodeUTF8(rf);
         int index = s.indexOf("ANNEX ");
         if (index >= 0) {
            s = s.substring(0, index);
         }

         return s;
      }
   }

   public static String getChangeList() {
      byte[] rf = AssetManager.RF("CHANGELIST.TXT");
      return rf == null ? null : Strings.decodeUTF8(rf);
   }

   static {
      FL.q[4] = 95;
      FL fl = new FL(new File(URLUtil.urlToFile(Licensing.class.getProtectionDomain().getCodeSource().getLocation()).getParentFile(), "jeb-license.txt"));
      license_id = fl.Uv();
      user_count = fl.oW();
      license_ts = fl.gO();
      real_license_ts = license_ts;
   }
}
