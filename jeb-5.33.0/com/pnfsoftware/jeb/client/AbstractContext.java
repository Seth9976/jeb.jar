package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.events.EventSource;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jeb.util.net.NetProxyInfo;
import com.pnfsoftware.jeb.util.net.URLUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import com.pnfsoftware.jebglobal.ckx;
import com.pnfsoftware.jebglobal.pB;
import java.io.File;

@SerDisabled
public abstract class AbstractContext extends EventSource {
   private static final ILogger logger = GlobalLog.getLogger(AbstractContext.class);
   @SerStaticOk
   public static final Version app_ver = new Version(5, 33, 0, 202510201957L, 0);
   public static final String app_name = "JEB";
   public static final String app_company = "PNF Software, Inc.";
   public static final String app_dates = "2015-2025";
   public static final String app_domain = "pnfsoftware.com";
   public static final String app_website = "https://www.pnfsoftware.com";
   public static final String app_licensing_backup = "https://lise.pnfsoftware.com";
   public static final String app_url_purchase = "https://www.pnfsoftware.com/jeb/buy";
   public static final String app_url_donate = "https://www.pnfsoftware.com/jeb/donate";
   public static final String app_url_manual = "https://www.pnfsoftware.com/jeb/manual";
   public static final String app_url_faq = "https://www.pnfsoftware.com/jeb/manual/faq";
   public static final String app_url_faqmem = "https://www.pnfsoftware.com/jeb/faqmem";
   public static final String app_url_apidoc = "https://www.pnfsoftware.com/jeb/apidoc";
   public static final String app_url_devportal = "https://www.pnfsoftware.com/jeb/devportal";
   public static final String app_url_changelog = "https://www.pnfsoftware.com/jeb/changelog";
   public static final String app_url_motd = "https://www.pnfsoftware.com/jeb/motd";
   public static final String app_url_uploaderrorlog = "https://www.pnfsoftware.com/upload_errorlog";
   public static final String app_url_fileuploader = "https://www.pnfsoftware.com/u";
   public static final String app_url_github = "https://github.com/pnfsoftware";
   public static final String app_url_github_samples = "https://github.com/pnfsoftware/jeb-samplecode";
   public static final String app_url_github_samples_scripts = "https://github.com/pnfsoftware/jeb-samplecode/tree/master/scripts";
   public static final String app_email_support = "support@pnfsoftware.com";
   public static final String app_email_licensing = "licensing@pnfsoftware.com";
   public static final String app_email_updates = "software@pnfsoftware.com";
   public static final String app_url_forum = "https://www.pnfsoftware.com/forum";
   public static final String app_url_chat = "https://www.pnfsoftware.com/chat";
   public static final String app_url_check_update = "https://www.pnfsoftware.com/jps/checkupdate";
   public static final String app_url_check_update_backup = "https://lise.pnfsoftware.com/jps/checkupdate";
   public static final String app_url_genlk = "https://www.pnfsoftware.com/genlk";
   public static final String app_url_genkey = "https://www.pnfsoftware.com/jps/genkey";
   public static final String app_url_genkey_backup = "https://lise.pnfsoftware.com/jps/genkey";
   public static final String app_uri = "jeb:";
   private int start_ts = (int)(System.currentTimeMillis() / 1000L);
   private String basedir;
   private String prgdir;
   private String appdir;
   private File jebClassesLocation;
   protected boolean just_updated;
   protected boolean integrity_failed;
   public static boolean demo = false;

   public static boolean isPreRelease() {
      return app_ver.getChannel() != 0;
   }

   public static String getChannelName() {
      int channel = app_ver.getChannel();
      if (channel == 1) {
         return "Beta";
      } else {
         return channel == 2 ? "Alpha" : null;
      }
   }

   public AbstractContext() {
      if (this.getCurrentDirectory() == null) {
         throw new RuntimeException(S.L("Could not determine the current directory"));
      } else {
         try {
            File jebClassesLocation = URLUtil.urlToFile(AbstractContext.class.getProtectionDomain().getCodeSource().getLocation());
            if (jebClassesLocation.isFile()) {
               this.appdir = jebClassesLocation.getParent();
               this.prgdir = new File(this.appdir).getParent();
               this.jebClassesLocation = jebClassesLocation;
            } else if (jebClassesLocation.isDirectory()) {
               if (jebClassesLocation.getName().equals("bin")) {
                  jebClassesLocation = jebClassesLocation.getParentFile();
               }

               this.prgdir = jebClassesLocation.getAbsolutePath();
               this.appdir = this.prgdir;
               this.jebClassesLocation = new File(this.prgdir, "bin");
            } else {
               logger.warn(S.L("Unusual program location: \"%s\" ... start-up may fail"), jebClassesLocation);
               this.prgdir = jebClassesLocation.getAbsolutePath();
               this.appdir = this.prgdir;
            }

            this.basedir = new File(this.prgdir).getParent();
         } catch (Exception var2) {
            var2.printStackTrace();
         }

         if (this.prgdir == null) {
            throw new RuntimeException("Could not determine the program directory");
         } else if (this.basedir == null) {
            throw new RuntimeException("Could not determine the base directory");
         } else {
            OSType.determine();
            Licensing.loaded = 1;
            pB.pC[4] = 95;
         }
      }
   }

   public final int getStartTimestamp() {
      return this.start_ts;
   }

   public final File getJebClassesLocation() {
      return this.jebClassesLocation;
   }

   public final String getCurrentDirectory() {
      return SystemInformation.getCurrentDirectory();
   }

   public final String getBaseDirectory() {
      return this.basedir;
   }

   public final String getProgramDirectory() {
      return this.prgdir;
   }

   public final String getAppDirectory() {
      return this.appdir;
   }

   public final Version getSoftwareVersion() {
      return app_ver;
   }

   public static final void terminate() {
      try {
         Class.forName(ckx.pC(new byte[]{-112, 11, 23, 23, 79, 66, 13, 15, 9, 73, 125, 42, 10, 7, 17, 8}, 1, 250))
            .getMethod(ckx.pC(new byte[]{52, 29, 17, 29}, 1, 81), int.class)
            .invoke(null, 0);
      } catch (Exception var1) {
         throw new RuntimeException();
      }
   }

   protected static Net initNetworkUtility(String s) {
      Net.setGlobalProxyInformation(NetProxyInfo.parse(s));
      Net net = new Net();
      net.setUserAgent("JEB " + app_ver);
      return net;
   }
}
