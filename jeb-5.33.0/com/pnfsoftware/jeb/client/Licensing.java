package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.AssetManager;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.URLUtil;
import com.pnfsoftware.jebglobal.ckx;
import com.pnfsoftware.jebglobal.hR;
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
   public static int user_id = 20251021;
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
         sb.append(ckx.pC(new byte[]{103, 23, 9, 9, 4, 18, 22, 74}, 1, 21));
      } else {
         sb.append(ckx.pC(new byte[]{124, 1, 7, 23, 18, 72}, 1, 24));
      }

      if (isFullBuild()) {
         sb.append(ckx.pC(new byte[]{37, 26, 28, 21, 93}, 2, 48));
      } else {
         sb.append(ckx.pC(new byte[]{75, 1, 8, 2, 64}, 1, 47));
      }

      if (isFloatingBuild()) {
         sb.append(ckx.pC(new byte[]{40, 10, 3, 14, 21, 29, 7, 9, 72}, 1, 78));
      } else {
         sb.append(ckx.pC(new byte[]{42, 1, 20, 16, 4, 0, 3, 29, 21, 76, 7}, 2, 156));
      }

      if (isAirgapBuild()) {
         sb.append(ckx.pC(new byte[]{34, 6, 2, 84, 21, 8, 23, 71}, 2, 208));
      } else {
         sb.append(ckx.pC(new byte[]{49, 10, 1, 84, 27, 7, 19, 13, 6, 78, 77, 23, 6}, 2, 206));
      }

      if (allowAnyClient()) {
         sb.append(ckx.pC(new byte[]{34, 1, 9, 84, 17, 5, 14, 13, 26, 84, 7}, 2, 64));
      } else {
         sb.append(ckx.pC(new byte[]{-32, 9, 0, 15, 10, 10, 8, 13, 65, 78, 15, 5, 12, 11, 26, 91}, 1, 143));
      }

      if (canUseCoreAPI()) {
         sb.append(ckx.pC(new byte[]{32, 0, 2, 28, 95, 8, 23, 1, 91}, 2, 99));
      } else {
         sb.append(ckx.pC(new byte[]{-73, 1, 66, 78, 12, 29, 23, 72, 76, 17, 25, 70}, 1, 217));
      }

      if (isPerpetual()) {
         sb.append(ckx.pC(new byte[]{-91, 21, 23, 2, 21, 17, 1, 20, 13}, 1, 213));
      } else {
         sb.append(ckx.pC(new byte[]{48, 26, 18, 10, 17, 27, 14, 24, 0, 73, 71, 13}, 2, 2));
      }

      return sb.toString();
   }

   public static String getLicense() {
      byte[] a = AssetManager.A("LICENSE.TXT");
      if (a == null) {
         return null;
      } else {
         String s = Strings.decodeUTF8(a);
         int index = s.indexOf("ANNEX ");
         if (index >= 0) {
            s = s.substring(0, index);
         }

         return s;
      }
   }

   public static String getChangeLog() {
      byte[] a = AssetManager.A("CHANGELOG.TXT");
      return a == null ? null : Strings.decodeUTF8(a);
   }

   static {
      hR.pC[4] = 95;
      hR hr = new hR(new File(URLUtil.urlToFile(Licensing.class.getProtectionDomain().getCodeSource().getLocation()).getParentFile(), "jeb-license.txt"));
      license_id = hr.UT();
      user_count = hr.E();
      license_ts = hr.sY();
      real_license_ts = license_ts;
   }
}
