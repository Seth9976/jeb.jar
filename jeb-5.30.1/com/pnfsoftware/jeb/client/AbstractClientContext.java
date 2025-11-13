package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.AssetManager;
import com.pnfsoftware.jeb.client.api.IClientContext;
import com.pnfsoftware.jeb.client.telemetry.ITelemetryDatabase;
import com.pnfsoftware.jeb.client.telemetry.MootTelemetryDatabase;
import com.pnfsoftware.jeb.client.telemetry.StandardTelemetryDatabase;
import com.pnfsoftware.jeb.client.telemetry.StandardTelemetryEndpoint;
import com.pnfsoftware.jeb.client.telemetry.TelemetryException;
import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.CoreOptions;
import com.pnfsoftware.jeb.core.ICoreContext;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.JebClientInformation;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.dao.impl.DataProvider;
import com.pnfsoftware.jeb.core.dao.impl.JDB2Manager;
import com.pnfsoftware.jeb.core.dao.impl.SimpleFSFileStore;
import com.pnfsoftware.jeb.core.events.ControllerNotification;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.PropertyChangeNotification;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.CommonsConfigurationWrapper;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypePath;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.SizeFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jeb.util.net.NetProxyInfo;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jebglobal.PY;
import com.pnfsoftware.jebglobal.bK;
import com.pnfsoftware.jebglobal.cvm;
import com.pnfsoftware.jebglobal.ej;
import com.pnfsoftware.jebglobal.tl;
import com.pnfsoftware.jebglobal.vb;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.net.InetSocketAddress;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;

@SerDisabled
public abstract class AbstractClientContext extends AbstractContext implements IClientContext {
   private static final ILogger logger = GlobalLog.getLogger(AbstractClientContext.class);
   public static final String defaultClientConfigPath = "jeb-client.cfg";
   public static final String defaultEnginesConfigPath = "jeb-engines.cfg";
   public static final String defaultTelemetryDatabasePath = "jt.db";
   public static final String defaultScriptsFolderName = "scripts";
   public static final String defaultPluginsFolderName = "coreplugins";
   private static final String UPDATE_FILENAME = cvm.q(new byte[]{-121, 5, 20, 5, 21, 17, 75, 84, 19, 25}, 1, 242);
   private static final String UPDATE_PASS_FILENAME = cvm.q(new byte[]{54, 31, 20, 24, 6, 12, 73, 24, 3, 68}, 2, 136);
   protected CoreOptions coreOptions = CoreOptions.getDefault();
   protected String[] args = new String[0];
   private String configpath;
   private String engconfigpath;
   protected String scriptpath;
   protected String inputpath;
   private boolean shouldInitEngines;
   protected boolean basicChecksPassed;
   protected String ugerrstr;
   private IPropertyDefinitionManager pdm;
   private IPropertyManager pm;
   private PropertiesConfiguration cfg;
   private String teledbpath;
   private ITelemetryDatabase teledb;
   private static Net net;
   private static PY pinger;
   public static final String CLIENT_RUNNING_FILENAME_PREFIX = ".jebc_ts";
   public static final String INSTALLER_LOCK_FILENAME = ".jebi_lock";
   private ICoreContext corectx;
   private IEnginesContext engctx;
   private ConcurrentHashMap transientStore = new ConcurrentHashMap();

   protected AbstractClientContext() {
      this.dumpProgramError("");
   }

   protected boolean dumpProgramError(String s) {
      try {
         IO.writeFile(new File(this.getBaseDirectory(), "program_errors.log"), s);
         return true;
      } catch (IOException var3) {
         return false;
      }
   }

   private boolean checkRunningClients() {
      boolean b = false;
      String baseDirectory = this.getBaseDirectory();

      for (String child : new File(baseDirectory).list()) {
         if (child.startsWith(".jebc_ts")) {
            File file = new File(baseDirectory, child);
            if (System.currentTimeMillis() - file.lastModified() <= 3000L) {
               b = true;
            } else {
               file.delete();
            }
         }
      }

      return b;
   }

   public boolean checkRunningInstaller() {
      File file = new File(this.getBaseDirectory(), ".jebi_lock");
      if (!file.exists()) {
         return false;
      } else if (System.currentTimeMillis() - file.lastModified() <= 3000L) {
         return true;
      } else {
         file.delete();
         return false;
      }
   }

   public void initialize(String[] array) {
      this.assignCommandlineArguments(array);
      if (this.configpath == null) {
         this.configpath = Strings.ff("%s/%s", this.getProgramDirectory(), "jeb-client.cfg");
      }

      this.cfg = JebContextUtil.createPropertiesConfiguration(this.configpath);
      String string = this.cfg.getString(".PreferredLanguage", null);
      if (string != null) {
         S.setCurrentLanguage(string);
      }

      boolean checkRunningClients = this.checkRunningClients();
      if (this.checkRunningInstaller()) {
         if (!checkRunningClients) {
            File file = new File(this.getBaseDirectory(), ".jebi_lock");
            System.out.println(S.L("The JEB installer is locking execution!"));
            System.out.println(S.L("An update may be about to be installed. Please wait a few seconds, JEB will attempt to start again automatically."));
            System.out.format(S.L("If you wish to bypass the lock, delete the following file: %s\n"), file);
            System.exit(-1);
         }
      } else {
         try {
            File file2 = new File(this.getAppDirectory(), "jebi.jar.new");
            if (file2.isFile()) {
               IO.renameFile(file2, new File(this.getAppDirectory(), "jebi.jar"), 1);
            }
         } catch (Exception var8) {
         }
      }

      ThreadUtil.start("jeb-client-lockfiles-watcher", new Runnable() {
         @Override
         public void run() {
            String s = AbstractClientContext.this.getBaseDirectory() + File.separatorChar + ".jebc_ts";
            int n = 0;
            String pathname = s;

            File file;
            while ((file = new File(pathname)).exists()) {
               pathname = s + "_" + ++n;
            }

            try {
               file.createNewFile();
               file.deleteOnExit();
            } catch (IOException var6) {
               return;
            }

            while (true) {
               file.setLastModified(System.currentTimeMillis());

               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException var5) {
               }
            }
         }
      });
      (this.pdm = new PropertyDefinitionManager()).addInternalDefinition(".Uuid", PropertyTypeString.create());
      this.pdm.addInternalDefinition(".FirstRun", PropertyTypeInteger.createPositiveOrZero(0));
      this.pdm.addInternalDefinition(".LastRun", PropertyTypeInteger.createPositiveOrZero(0));
      this.pdm.addInternalDefinition(".RunCount", PropertyTypeInteger.createPositiveOrZero(0));
      this.pdm.addInternalDefinition(".LastVersionRun", PropertyTypeString.create());
      this.pdm.addInternalDefinition(".EulaAccepted", PropertyTypeBoolean.create(false));
      this.pdm.addInternalDefinition(".LicenseKey", PropertyTypeString.create());
      this.pdm.addInternalDefinition(".SupportExpired", PropertyTypeBoolean.create());
      this.pdm.addInternalDefinition(".PreferredLanguage", PropertyTypeString.create(""));
      this.pdm.addInternalDefinition(".DebugEnabled", PropertyTypeBoolean.create(false));
      this.pdm.addInternalDefinition(".CheckMOTD", PropertyTypeBoolean.create(true));
      this.pdm.addInternalDefinition(".LastMOTDId", PropertyTypeInteger.create());
      this.pdm.addInternalDefinition(".TelemetryReporting", PropertyTypeBoolean.create(false));
      this.pdm.addInternalDefinition(".LatestAvailableUpdate", PropertyTypeString.create());
      this.pdm.addInternalDefinition(".WriteUpdateToDiskOnlyIfValid", PropertyTypeBoolean.create(false));
      this.pdm.addInternalDefinition(".UploadErrorFiles", PropertyTypeBoolean.create(true));
      this.pdm.addInternalDefinition(".IncludeExtraDataInErrorLogs", PropertyTypeBoolean.create(Licensing.isDemoBuild()));
      this.pdm
         .addDefinition(
            ".NetworkProxy",
            PropertyTypeString.create(),
            S.L(
               "Proxy settings for JEB front-end components. The format of this property is:\n'type|hostname|port|user|pass|whitelist'\n- type can be direct (no proxy), http, or socks\n- hostname and port are mandatory for non-direct proxy types\n- user and password are optional (needed only if your proxy requires authentication)\n- whitelist is an optional list of domains/IPs (with support for the wildcard character *) for which no proxy connection should be used (always direct connect)"
            ),
            8
         );
      this.pdm
         .addDefinition(
            ".CheckUpdates", PropertyTypeBoolean.create(true), S.L("Let JEB automatically check for software updates (requires an Internet connection)")
         );
      this.pdm
         .addDefinition(
            ".UpdateChannel",
            PropertyTypeSelection.Builder.create()
               .addEntry(0, "Release", S.L("Release builds only"))
               .addEntry(1, "Beta", S.L("Release and Beta builds)"))
               .addEntry(2, "Alpha", S.L("All builds (stable and unstable)"))
               .setDefault(!Licensing.isDemoBuild() && !Licensing.isDebugBuild() ? app_ver.getChannel() : 2)
               .build(),
            S.L("Channel from which software updates are pulled from")
         );
      this.pdm.addDefinition(".UploadErrorLogs", PropertyTypeBoolean.create(true), S.L("Securely upload error logs to PNF Software error server"));
      this.pdm
         .addDefinition(
            ".DevelopmentMode",
            PropertyTypeBoolean.create(false),
            S.L("Recommended when developing JEB extensions (scripts, plugins). Increase logger levels separately via the `LogLevel` property.")
         );
      this.pdm
         .addDefinition(
            ".LogLevel",
            PropertyTypeSelection.Builder.create()
               .addEntry(0, "ALL", null)
               .addEntry(10, "TRACE", null)
               .addEntry(20, "DEBUG", null)
               .addEntry(30, "INFO", null)
               .addEntry(40, "WARN", null)
               .addEntry(50, "ERROR", null)
               .addEntry(60, "CATCHING", null)
               .setDefault(GlobalLog.STANDARD_CUTOFF_LEVEL)
               .build(),
            S.L(
               "Set the global cutoff level for loggers, which will control what gets output in the 'Logger' view.\nIf `DevelopmentMode` is enabled, this value is disregarded and assumed to be ALL (log everything)."
            ),
            8
         );
      this.pdm
         .addDefinition(
            ".ScriptsFolder",
            PropertyTypePath.create(new File(this.getBaseDirectory(), "scripts").getAbsolutePath()),
            S.L("Folder containing JEB client scripts written in Python"),
            8
         );
      (this.pm = new PropertyManager(this.pdm, new CommonsConfigurationWrapper(this.cfg)))
         .addListener(
            new IEventListener() {
               @Override
               public void onEvent(IEvent event) {
                  if (event instanceof JebEvent && event.getType() == J.PropertyChange && event.getData() instanceof PropertyChangeNotification) {
                     PropertyChangeNotification propertyChangeNotification = (PropertyChangeNotification)event.getData();
                     if (propertyChangeNotification.has(".NetworkProxy")) {
                        String proxyString = AbstractClientContext.this.getProxyString();

                        try {
                           Net.setGlobalProxyInformation(NetProxyInfo.parse(proxyString));
                        } catch (Exception var5) {
                           AbstractClientContext.logger.catching(var5);
                        }
                     }

                     if (propertyChangeNotification.has(".LogLevel")) {
                        AbstractClientContext.this.checkLogLevel();
                     }

                     if (propertyChangeNotification.has(".DevelopmentMode")) {
                        AbstractClientContext.logger
                           .info(Strings.ff(S.L("The property %s was modified.\n\nPlease restart JEB to apply this change."), ".DevelopmentMode"));
                     }
                  }
               }
            }
         );
      long uuid = this.getUuid();
      if (uuid == 0L) {
         uuid = new SecureRandom().nextLong() & Long.MAX_VALUE;
         this.pm.setString(".Uuid", Long.toString(uuid));
      }

      this.checkLogLevel();
      if (this.isDevelopmentMode()) {
         logger.info(S.L("Development mode is ON"));
      }

      net = AbstractContext.initNetworkUtility(this.getProxyString());
      pinger = new PY(net);
      if (this.teledbpath == null) {
         this.teledbpath = Strings.ff("%s/%s", this.getProgramDirectory(), "jt.db");
      }

      if (!Licensing.isDebugBuild() && this.pm.getBoolean(".TelemetryReporting")) {
         try {
            StandardTelemetryDatabase teledb = new StandardTelemetryDatabase(this.teledbpath, new StandardTelemetryEndpoint(net, uuid));
            teledb.startDumperThread();
            this.teledb = teledb;
         } catch (Exception var7) {
            this.teledb = new MootTelemetryDatabase();
            logger.catchingSilent(var7);
         }
      } else {
         this.teledb = new MootTelemetryDatabase();
      }

      this.coreOptions.setDevelopmentMode(this.isDevelopmentMode());
      this.coreOptions.setStandardProxyInfo(NetProxyInfo.parse(this.getProxyString()));
      this.coreOptions.setJebClassesLocation(this.getJebClassesLocation());
   }

   private boolean checkLogLevel() {
      GlobalLog.setCutoffLevel(this.getLogLevel());
      return true;
   }

   public void start() throws JebException {
      logger.info(
         "%s %s (%s) ...",
         "JEB",
         app_ver.toString(),
         cvm.q(new byte[]{16, 26, 0, 28, 0, 68, 37, 4, 21, 67, 67, 67, 108, 68, 88, 77, 80, 92, 66, 0, 80, 73, 18, 119, 116, 118}, 2, 176)
      );
      logger.info("%s: %s", S.L("Current directory"), this.getCurrentDirectory());
      logger.info("%s: %s", S.L("Base directory"), this.getBaseDirectory());
      logger.debug("%s: %s", S.L("Program directory"), this.getProgramDirectory());
      logger.info(
         "%s: %s %s (%s) %s", S.L("System"), SystemInformation.osname, SystemInformation.osversion, SystemInformation.osarch, SystemInformation.localeinfo
      );
      logger.info("%s: %s %s", S.L("Java"), SystemInformation.javavendor, SystemInformation.javaversion);
      this.logMemoryUsage();
      Version fromString = Version.parseFromString(this.pm.getString(".LastVersionRun"));
      this.pm.setBoolean(".EulaAccepted", true);
      if (this.pm.getInteger(".FirstRun") == 0) {
         this.pm.setInteger(".FirstRun", this.getStartTimestamp());
      }

      this.pm.setInteger(".LastRun", this.getStartTimestamp());
      this.pm.setInteger(".RunCount", this.pm.getInteger(".RunCount") + 1);
      this.pm.setBoolean(".CheckUpdates", false);
      this.pm.setBoolean(".UploadErrorLogs", false);
      this.pm.setBoolean(".TelemetryReporting", false);
      this.pm.setString(".LicenseKey", "4282388837555933555Z8832119605");
      if (fromString == null || fromString.compareTo(app_ver) < 0) {
         this.just_updated = true;
         this.onUpdatedSoftware(Strings.decodeUTF8(AssetManager.RF("CHANGELIST.TXT")), fromString);
      }

      this.pm.setString(".LastVersionRun", app_ver.toString());
      boolean b = this instanceof ej;
      boolean floatingBuild = Licensing.isFloatingBuild();
      if (b && !floatingBuild) {
         throw new RuntimeException();
      } else {
         this.prepareCheckLicenseKey();
         this.corectx = JebCoreService.getInstance(this.pm.getString(".LicenseKey"), this.coreOptions);
         this.shouldInitEngines = !b;
         this.basicChecksPassed = true;
      }
   }

   public void stop() {
      try {
         if (this.teledb != null) {
            this.teledb.close();
         }
      } catch (TelemetryException var3) {
         logger.catchingSilent(var3);
      }

      try {
         if (this.corectx != null) {
            this.corectx.close();
         }
      } catch (Exception var2) {
         logger.catchingSilent(var2);
      }
   }

   protected void assignCommandlineArguments(String[] array) {
      int length = array.length;

      for (int i = 0; i < length; i++) {
         Object[] var10000 = new Object[]{array[i]};
      }

      length = 0;

      for (String s : array) {
         if (s.equals("--")) {
            length++;
            break;
         }

         if (!s.startsWith("-")) {
            break;
         }

         if (s.startsWith("--config=")) {
            this.configpath = s.substring(s.indexOf(61) + 1);
         } else if (s.startsWith("--engconfig=")) {
            this.engconfigpath = s.substring(s.indexOf(61) + 1);
         } else if (s.startsWith("--cd=")) {
            String currentDirectory = s.substring(s.indexOf(61) + 1);
            if (currentDirectory.startsWith("\"") && currentDirectory.endsWith("\"")) {
               currentDirectory = currentDirectory.substring(1, currentDirectory.length() - 1);
            }

            SystemInformation.setCurrentDirectory(currentDirectory);
         } else if (s.startsWith("--script=")) {
            this.scriptpath = s.substring(s.indexOf(61) + 1);
         }

         length++;
      }

      this.args = new String[array.length - length];

      for (int k = length; k < array.length; k++) {
         this.args[k - length] = array[k];
      }

      if (this.args.length > 0) {
         this.inputpath = this.args[0];
      }
   }

   @Override
   public String[] getArguments() {
      return this.args;
   }

   public String getInputpath() {
      return this.inputpath;
   }

   public String getScriptpath() {
      return this.scriptpath;
   }

   public Net getNetworkUtility() {
      return net;
   }

   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.pdm;
   }

   public IPropertyManager getPropertyManager() {
      return this.pm;
   }

   public Configuration getConfiguration() {
      return this.cfg;
   }

   PY getPinger() {
      return pinger;
   }

   public ITelemetryDatabase getTelemetry() {
      return this.teledb;
   }

   public boolean isDevelopmentMode() {
      return this.pm.getBoolean(".DevelopmentMode");
   }

   public void setDevelopmentMode(boolean b) {
      this.pm.setBoolean(".DevelopmentMode", b);
   }

   public int getLogLevel() {
      return this.pm.getInteger(".LogLevel");
   }

   public void setLogLevel(int i) {
      this.pm.setInteger(".LogLevel", i);
   }

   public final long getUuid() {
      try {
         return Long.parseLong(this.pm.getString(".Uuid"));
      } catch (Exception var2) {
         return 0L;
      }
   }

   public final String getPreferredLanguage() {
      return this.pm.getString(".PreferredLanguage");
   }

   public final void setPreferredLanguage(String s) {
      this.pm.setString(".PreferredLanguage", s);
   }

   public final boolean shouldCheckUpdates() {
      return false;
   }

   public final int getUpdateChannel() {
      return this.pm.getInteger(".UpdateChannel");
   }

   public final void setUpdateChannel(int i) {
      this.pm.setInteger(".UpdateChannel", i);
   }

   public final Version getLatestAvailableUpdate() {
      return Version.parseFromString(this.pm.getString(".LatestAvailableUpdate"));
   }

   public final void setLatestAvailableUpdate(Version version) {
      this.pm.setString(".LatestAvailableUpdate", version.toString());
   }

   public final boolean shouldCheckPublicAnnouncements() {
      return false;
   }

   public final String getProxyString() {
      return this.pm.getString(".NetworkProxy");
   }

   public final void setProxyString(String s) {
      this.pm.setString(".NetworkProxy", s);
   }

   public final int getLastPublicAnnouncementId() {
      return this.pm.getInteger(".LastMOTDId");
   }

   public final void setLastPublicAnnouncementId(int i) {
      this.pm.setInteger(".LastMOTDId", i);
   }

   public boolean isHeadless() {
      return true;
   }

   public abstract boolean checkUpdate();

   public abstract boolean displayEula(String var1);

   public abstract void displayDemoInformation(String var1);

   public abstract void onUpdatedSoftware(String var1, Version var2);

   public abstract String retrieveLicenseKey(String var1);

   public abstract void notifySupportExpired();

   public abstract boolean setupController();

   public abstract void notifyFloatingClient(ControllerNotification var1);

   private void prepareCheckLicenseKey() {
      String string = this.pm.getString(".LicenseKey");
      int[] array = new int[]{0};
      boolean b = false;
      if (!Strings.isBlank(string)) {
         long[] xk = tl.xK();
         int length = xk.length;

         for (int i = 0; i < length; i++) {
            if (new bK(xk[i]).q(string, array)) {
               b = true;
               break;
            }
         }

         this.ugerrstr = tl.Dw();
      }

      if (!b) {
         vb.RF();
         bK bk = new bK(tl.RF());
         String retrieveLicenseKey = this.retrieveLicenseKey(bk.q());
         if (!bk.q(retrieveLicenseKey, array)) {
            logger.info(S.L("License key error"));
            terminate();
         }

         this.pm.setString(".LicenseKey", retrieveLicenseKey.trim());
      }

      Licensing.setLicenseTimestamp(array[0]);
      int expirationTimestamp = Licensing.getExpirationTimestamp();
      if (expirationTimestamp != 0) {
         if (expirationTimestamp < 0 || this.getStartTimestamp() >= expirationTimestamp) {
            this.pm.setBoolean(".SupportExpired", true);
            this.notifySupportExpired();
         } else if (this.pm.getBoolean(".SupportExpired")) {
            this.pm.setBoolean(".SupportExpired", false);
         }
      }
   }

   public static final String generateLicenseInformation() {
      StringBuilder sb = new StringBuilder();
      Strings.ff(sb, "JEB %s", app_ver);
      if (!Licensing.isCommonBuild()) {
         Strings.ff(sb, "\n");
         Strings.ff(sb, S.L("Licensed to %s"), Strings.ff("%s (%s)", cvm.q(new byte[]{0, 55, 38}, 2, 176), cvm.q(new byte[]{19, 33, 54}, 2, 176)));
         Strings.ff(sb, "\n%s: %s", S.L("Email address"), Licensing.user_email);
      }

      Strings.ff(sb, "\n%s: %d\n%s: %d", S.L("User ID"), Licensing.user_id, S.L("License ID"), Licensing.license_id);
      int user_count = Licensing.user_count;
      if (user_count >= 1) {
         sb.append('\n');
         if (Licensing.isFloatingBuild()) {
            if (user_count == 1) {
               Strings.ff(sb, S.L("Valid for 1 floating seat"));
            } else {
               Strings.ff(sb, S.L("Valid for %d floating seats"), user_count);
            }
         } else if (user_count == 1) {
            Strings.ff(sb, S.L("Valid for 1 user"));
         } else {
            Strings.ff(sb, S.L("Valid for %d users"), user_count);
         }
      }

      Strings.ff(
         sb,
         "\n%s: %s\n(%s)",
         S.L("Build type"),
         cvm.q(new byte[]{16, 26, 0, 28, 0, 68, 37, 4, 21, 67, 67, 67, 108, 68, 88, 77, 80, 92, 66, 0, 80, 73, 18, 119, 116, 118}, 2, 176),
         Licensing.getBuildTypeString()
      );
      if (Licensing.isSubscription() || !Licensing.isFree()) {
         int expirationTimestamp = Licensing.getExpirationTimestamp();
         if (expirationTimestamp > 0) {
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            instance.setTime(new Date(1000L * expirationTimestamp));
            String ff = Strings.ff("%04d-%02d-%02d", instance.get(1), 1 + instance.get(2), instance.get(5));
            if (Licensing.isSubscription()) {
               Strings.ff(sb, S.L("\nSubscription expires on: %s"), ff);
            } else {
               Strings.ff(sb, S.L("\nReceiving updates until: %s"), ff);
            }
         }
      }

      return sb.toString();
   }

   public final int ping(int n, int n2, SoftwareBuildInfo softwareBuildInfo, IProgressCallback progressCallback, boolean b) {
      return 1;
   }

   public final void dumpUpdateToDisk(byte[] array, String s) throws IOException {
      IO.writeFile(new File(this.getBaseDirectory(), UPDATE_FILENAME), array, false);
      if (s != null && s.length() > 0) {
         IO.writeFile(new File(this.getBaseDirectory(), UPDATE_PASS_FILENAME), Strings.encodeUTF8(s), false);
      }
   }

   public final PublicAnnouncement retrieveLatestPublicAnnouncement() {
      byte[] queryBinary;
      try {
         queryBinary = net.queryBinary("https://www.pnfsoftware.com/jeb/motd");
      } catch (IOException var4) {
         logger.catchingSilent(var4);
         return null;
      }

      try {
         return PublicAnnouncement.parse(Strings.decodeUTF8(queryBinary));
      } catch (Exception var3) {
         logger.catchingSilent(var3);
         return null;
      }
   }

   public final String getScriptsDirectory() {
      String s = this.pm.getString(".ScriptsFolder");
      if (s == null) {
         s = new File(this.getBaseDirectory(), "scripts").getAbsolutePath();
      }

      return s;
   }

   public final File getScriptsDirfile() {
      File file = new File(this.getScriptsDirectory());
      if (!file.exists()) {
         file.mkdirs();
      }

      if (!file.isDirectory()) {
         throw new RuntimeException("Illegal script directory: %s" + file);
      } else {
         return file;
      }
   }

   public final void setScriptsDirectory(String s) {
      this.pm.setString(".ScriptsFolder", s);
   }

   @Override
   public long getUsedMemory() {
      Runtime runtime = Runtime.getRuntime();
      return runtime.totalMemory() - runtime.freeMemory();
   }

   @Override
   public long getMaxMemory() {
      return Runtime.getRuntime().maxMemory();
   }

   @Override
   public long getProcessId() {
      return ProcessHandle.current().pid();
   }

   public final void logMemoryUsage() {
      logger.info("%s", formatMemoryUsage());
   }

   public static String formatMemoryUsage() {
      return formatProcessInfo(false, true, false, false);
   }

   public static String formatProcessInfo(boolean b, boolean b2, boolean b3, boolean b4) {
      String s = "";
      ProcessHandle current = ProcessHandle.current();
      Info info = current.info();
      if (b) {
         String l = S.L("N/A");
         Optional user = info.user();
         if (user.isPresent()) {
            l = (String)user.get();
         }

         if (!s.isEmpty()) {
            s = s;
         }

         s = s + Strings.ff(S.L("Process ID: %d (User: %s)"), current.pid(), l);
      }

      if (b2) {
         if (!s.isEmpty()) {
            s = s;
         }

         Runtime runtime = Runtime.getRuntime();
         long maxMemory = runtime.maxMemory();
         long totalMemory = runtime.totalMemory();
         long freeMemory = runtime.freeMemory();
         s = s
            + Strings.ff(
               S.L("Memory Usage: %s used (%s free, %s max)"),
               SizeFormatter.formatByteSize(totalMemory - freeMemory),
               SizeFormatter.formatByteSize(freeMemory),
               SizeFormatter.formatByteSize(maxMemory)
            );
      }

      if (b3) {
         String s2 = S.L("N/A");
         Optional totalCpuDuration = info.totalCpuDuration();
         if (totalCpuDuration.isPresent()) {
            s2 = TimeFormatter.formatTimestampDelta(((Duration)totalCpuDuration.get()).getSeconds() * 1000L);
         }

         String s3 = S.L("N/A");
         Optional startInstant = info.startInstant();
         if (startInstant.isPresent()) {
            s3 = TimeFormatter.formatTimestampLocal(((Instant)startInstant.get()).getEpochSecond() * 1000L);
         }

         if (!s.isEmpty()) {
            s = s;
         }

         s = s + Strings.ff(S.L("Start time: %s (CPU time: %s)"), s3, s2);
      }

      if (b4) {
         String i = S.L("N/A");
         Optional commandLine = info.commandLine();
         if (commandLine.isPresent()) {
            i = (String)commandLine.get();
         }

         if (!s.isEmpty()) {
            s = s;
         }

         s = s + Strings.ff(S.L("Command-line: %s"), i);
      }

      return s;
   }

   protected final void initializeEngines() throws JebException {
      if (this.shouldInitEngines) {
         long currentTimeMillis = System.currentTimeMillis();
         String baseDirectory = this.getBaseDirectory();
         SimpleFSFileStore simpleFSFileStore = new SimpleFSFileStore(baseDirectory);
         SimpleFSFileStore simpleFSFileStore2 = new SimpleFSFileStore(baseDirectory + File.separator + "coreplugins");
         JDB2Manager jdb2Manager = new JDB2Manager(baseDirectory);
         if (this.engconfigpath == null) {
            this.engconfigpath = this.getProgramDirectory() + File.separator + "jeb-engines.cfg";
         }

         this.engctx = this.corectx
            .createEnginesContext(
               new DataProvider(
                  null,
                  jdb2Manager,
                  simpleFSFileStore,
                  simpleFSFileStore2,
                  null,
                  new CommonsConfigurationWrapper(JebContextUtil.createPropertiesConfiguration(this.engconfigpath))
               ),
               new JebClientInformation(
                  cvm.q(
                     new byte[]{
                        19, 33, 54, 89, 33, 6, 1, 28, 3, 65, 90, 6, 9, 13, 17, 115, 124, 113, 12, 111, 84, 86, 91, 87, 69, 65, 35, 82, 34, 15, 5, 0, 78, 21
                     },
                     2,
                     136
                  )
               )
            );
         logger.debug("Engines initialization took %s", TimeFormatter.formatExecutionTime(System.currentTimeMillis() - currentTimeMillis));
      }
   }

   public final ICoreContext getCoreContext() {
      return this.corectx;
   }

   @Override
   public final IEnginesContext getEnginesContext() {
      return this.engctx;
   }

   public final boolean hasOpenedProject() {
      return this.getOpenedProject() != null;
   }

   public final IRuntimeProject getOpenedProject() {
      IEnginesContext enginesContext = this.getEnginesContext();
      return enginesContext != null && !CollectionUtils.isEmpty(enginesContext.getProjects()) ? (IRuntimeProject)enginesContext.getProjects().get(0) : null;
   }

   public final boolean closeOpenedProject() {
      IRuntimeProject openedProject = this.getOpenedProject();
      return openedProject != null && this.getEnginesContext().unloadProject(openedProject.getKey());
   }

   @Override
   public IRuntimeProject getMainProject() {
      return this.getOpenedProject();
   }

   @Override
   public boolean closeMainProject() {
      return this.closeOpenedProject();
   }

   @Override
   public IUnit open(String pathname) throws IOException {
      File file = new File(pathname);
      if (!file.getName().endsWith(".jdb2")) {
         IRuntimeProject runtimeProject = this.getMainProject();
         if (runtimeProject == null) {
            runtimeProject = this.engctx.loadProject(file.getName());
         }

         return runtimeProject.processArtifact(new Artifact(file.getName(), new FileInput(file))).getMainUnit();
      } else if (this.hasOpenedProject()) {
         throw new IllegalStateException("A project is already loaded");
      } else {
         return this.engctx.loadProject(file.getAbsolutePath()).getLiveArtifact(0).getMainUnit();
      }
   }

   @Override
   public Map getTransientStore() {
      return this.transientStore;
   }

   public String getControllerInterface() {
      return this.pm.getString(".ControllerInterface");
   }

   public void setControllerInterface(String s) {
      this.pm.setString(".ControllerInterface", s);
   }

   public int getControllerPort() {
      return this.pm.getInteger(".ControllerPort");
   }

   public void setControllerPort(int i) {
      this.pm.setInteger(".ControllerPort", i);
   }

   public int getControllerProtocol() {
      return this.pm.getInteger(".ControllerProtocol");
   }

   public void setControllerProtocol(int i) {
      this.pm.setInteger(".ControllerProtocol", i);
   }

   public String getControllerMessage() {
      return this.pm.getString(".ControllerMessage");
   }

   private InetSocketAddress getFloatingControllerAddress() {
      if (this.getControllerInterface().isEmpty() && !this.setupController()) {
         logger.info(S.L("Invalid controller"));
         terminate();
      }

      return new InetSocketAddress(this.getControllerInterface(), this.getControllerPort());
   }
}
