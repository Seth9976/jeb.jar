package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.concurrent.CommandExec;
import com.pnfsoftware.jeb.util.concurrent.ProcessWrapper;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdbWrapper {
   private static final ILogger logger = GlobalLog.getLogger(AdbWrapper.class);
   public static final long STANDARD_TIMEOUT = 5000L;
   private AdbWrapperFactory adbf;
   private String adbPath;
   private long defaultTimeout;
   private String targetDeviceSerial;
   private String runAsPath = "run-as";
   private Map toolNames = new HashMap();

   AdbWrapper(AdbWrapperFactory var1, String var2, long var3) {
      this.adbf = var1;
      this.adbPath = var2;
      this.defaultTimeout = var3;
   }

   public synchronized boolean initialize() {
      return this.adbf.initialize() && this.waitForDevice();
   }

   public String getPath() {
      return this.adbPath;
   }

   public synchronized void setTargetDevice(String var1) {
      this.targetDeviceSerial = var1;
   }

   public synchronized String getTargetDevice() {
      return this.targetDeviceSerial;
   }

   synchronized String determinePreferredTarget(String var1) {
      return var1 != null ? var1 : this.getTargetDevice();
   }

   public synchronized void setRunAs(String var1) {
      this.runAsPath = var1;
   }

   public synchronized String getRunAs() {
      return this.runAsPath;
   }

   public synchronized void setToolName(String var1, String var2) {
      if (var1 != null && var2 != null) {
         this.toolNames.put(var1, var2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public synchronized String getToolName(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return (String)this.toolNames.getOrDefault(var1, var1);
      }
   }

   public synchronized void setDefaultTimeout(long var1) {
      this.defaultTimeout = var1;
   }

   public synchronized long getDefaultTimeout() {
      return this.defaultTimeout;
   }

   public synchronized String getVersion() {
      return this.adbf.getVersion();
   }

   public synchronized boolean startServer() {
      return this.adbf.startServer();
   }

   public synchronized void stopServer() {
      this.adbf.stopServer();
   }

   public synchronized List listDevices() {
      return this.adbf.listDevices();
   }

   public synchronized InputStream logcat() throws IOException {
      return this.logcat(null, null);
   }

   public synchronized InputStream logcat(Integer var1, String var2) throws IOException {
      ArrayList var3 = new ArrayList();
      var3.add("logcat");
      if (var1 != null) {
         var3.add("--pid=" + var1);
      }

      if (var2 != null) {
         var3.add("-t");
         var3.add("\"" + var2 + "\"");
      }

      String var4 = this.determinePreferredTarget(null);
      if (var4 != null) {
         var3.add(0, "-s");
         var3.add(1, var4);
      }

      ProcessWrapper var5 = this.executeAsync(-1L, var3);
      return var5.getProcessOutput();
   }

   public synchronized boolean logcatClear() {
      ArrayList var1 = new ArrayList();
      var1.add("logcat");
      var1.add("-c");
      byte[] var2 = this.executeOn(null, null, var1);
      return var2 != null;
   }

   public synchronized boolean waitForDevice() {
      ArrayList var1 = new ArrayList();
      var1.add("wait-for-device");
      byte[] var2 = this.executeOn(30000L, null, var1);
      return AdbUtil.heurSuccess(var2);
   }

   public synchronized boolean waitForBootCompletionHeur1() {
      long var1 = System.currentTimeMillis();

      while (System.currentTimeMillis() - var1 <= 30000L) {
         String var3 = this.readProperty("dev.bootcomplete");
         if (var3 != null && Strings.trim(var3).equals("1")) {
            return true;
         }

         try {
            Thread.sleep(3000L);
         } catch (InterruptedException var4) {
            return false;
         }
      }

      return false;
   }

   public synchronized boolean waitForBootCompletionHeur2() {
      long var1 = System.currentTimeMillis();

      while (System.currentTimeMillis() - var1 <= 30000L) {
         String var3 = this.readProperty("init.svc.bootanim");
         if (var3 != null && Strings.trim(var3).equals("stopped")) {
            return true;
         }

         try {
            Thread.sleep(3000L);
         } catch (InterruptedException var4) {
            return false;
         }
      }

      return false;
   }

   public synchronized boolean waitForBootCompletionHeur3() {
      long var1 = System.currentTimeMillis();

      while (System.currentTimeMillis() - var1 <= 180000L) {
         ArrayList var3 = new ArrayList(Arrays.asList("pm", "list", "packages", "-f"));
         AdbResult var4 = this.shell(null, null, null, var3);
         if (var4.isSuccess(null, false, "Is the system running?")) {
            return true;
         }

         try {
            Thread.sleep(3000L);
         } catch (InterruptedException var5) {
            return false;
         }
      }

      return false;
   }

   public synchronized boolean installApp(String var1) {
      return this.installApp(var1, false, false, false);
   }

   public synchronized boolean installAppForce(String var1) {
      return this.installApp(var1, true, false, false);
   }

   public synchronized boolean installApp(String var1, boolean var2, boolean var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      var5.add("install");
      if (var2) {
         var5.add("-r");
      }

      if (var3) {
         var5.add("-s");
      }

      if (var4 && AndroidDeviceUtil.getAndroidApiLevel(this) >= 23) {
         var5.add("-g");
      }

      var5.add(var1);
      byte[] var6 = this.executeOn(0L, null, var5);
      return AdbUtil.heurSuccess(var6);
   }

   public synchronized boolean uninstallApp(String var1) {
      return this.uninstallApp(var1, false);
   }

   public synchronized boolean uninstallApp(String var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      var3.add("uninstall");
      if (var2) {
         var3.add("-k");
      }

      var3.add(var1);
      byte[] var4 = this.executeOn(0L, null, var3);
      return AdbUtil.heurSuccess(var4);
   }

   public synchronized boolean pushFile(String var1, String var2) {
      byte[] var3 = this.executeOn(0L, null, Arrays.asList("push", var1, var2));
      if (var3 == null) {
         return false;
      } else {
         String[] var4 = new String[]{"failed ", "error:"};

         for (String var8 : Strings.splitLines(Strings.decodeLocal(var3))) {
            for (String var12 : var4) {
               if (var8.startsWith(var12)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public synchronized boolean pullFile(String var1, String var2) {
      byte[] var3 = this.executeOn(0L, null, Arrays.asList("pull", var1, var2));
      if (var3 == null) {
         return false;
      } else {
         for (String var7 : Strings.splitLines(Strings.decodeLocal(var3))) {
            if (var7.contains("100%") || var7.contains("bytes in")) {
               return true;
            }
         }

         return false;
      }
   }

   public synchronized List listJdwpPids() {
      byte[] var1 = this.executeOn(1000L, null, Arrays.asList("jdwp"));
      if (var1 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String[] var3 = Strings.decodeLocal(var1).split("\\r?\\n");
         if (var3.length != 0) {
            for (int var4 = 0; var4 < var3.length; var4++) {
               String var5 = var3[var4].trim();
               if (!var5.isEmpty()) {
                  int var6 = Conversion.stringToInt(var5, -1);
                  if (var6 >= 0) {
                     var2.add(var6);
                  }
               }
            }
         }

         return var2;
      }
   }

   public synchronized boolean forwardJdwpToTcp(int var1, int var2) {
      byte[] var3 = this.executeOn(null, null, Arrays.asList("forward", "tcp:" + var2, "jdwp:" + var1));
      return var3 != null;
   }

   public synchronized boolean forwardLocalToTcp(String var1, int var2) {
      byte[] var3 = this.executeOn(null, null, Arrays.asList("forward", "tcp:" + var2, "localfilesystem:" + var1));
      return var3 != null;
   }

   public synchronized boolean forwardToTcp(AdbForwardType var1, String var2, int var3) {
      byte[] var4 = this.executeOn(null, null, Arrays.asList("forward", "tcp:" + var3, var1.toString() + ":" + var2));
      return var4 != null;
   }

   public synchronized boolean stopForwardToTcp(int var1) {
      byte[] var2 = this.executeOn(null, null, Arrays.asList("forward", "--remove", "tcp:" + var1));
      return var2 != null;
   }

   public synchronized boolean stopAllForwards(int var1) {
      byte[] var2 = this.executeOn(null, null, Arrays.asList("forward", "--remove-all"));
      return var2 != null;
   }

   public synchronized String readProperty(String var1) {
      AdbResult var2 = this.shell(null, Arrays.asList("getprop", var1));
      return !var2.isSuccess(null, false, "error: no devices found") ? null : var2.getOutputString();
   }

   public synchronized List listProcesses() {
      AdbResult var1 = this.shell(Arrays.asList("ps"));
      if (!var1.isSuccess(null, false)) {
         return null;
      } else {
         List var2 = AdbProcess.parsePsData(var1.getOutput());
         if (heurLooksLikeUserOnlyProcList(var2)) {
            long var3 = Math.max(10000L, this.getDefaultTimeout());
            var1 = this.shell(var3, null, null, Arrays.asList("ps", "-A"), false);
            if (!var1.isSuccess(null, false)) {
               return null;
            }

            List var5 = AdbProcess.parsePsData(var1.getOutput());
            if (var5.size() > var2.size()) {
               var2 = var5;
            }
         }

         return var2;
      }
   }

   private static final boolean heurLooksLikeUserOnlyProcList(List var0) {
      if (var0.size() <= 6) {
         for (AdbProcess var2 : var0) {
            String var3 = var2.getName();
            if (var3.equals("ps")) {
               return true;
            }
         }
      }

      return false;
   }

   public synchronized List listPackages() {
      return this.listPackages(null);
   }

   public synchronized List listPackages(AdbPackageManagerOptions var1) {
      ArrayList var2 = new ArrayList(Arrays.asList("pm", "list", "packages", "-f"));
      if (var1 != null && var1.thirdPartyOnly) {
         var2.add("-3");
      }

      AdbResult var3 = this.shell(null, null, null, var2);
      if (!var3.isSuccess(null, false)) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();
         String[] var5 = var3.getOutputString().split("\\r?\\n");
         if (var5.length == 0) {
            return var4;
         } else {
            Pattern var6 = Pattern.compile("package:([^=]+)=(.+)");

            for (int var7 = 0; var7 < var5.length; var7++) {
               String var8 = var5[var7].trim();
               if (!var8.isEmpty()) {
                  Matcher var9 = var6.matcher(var8);
                  if (var9.matches() && var9.groupCount() == 2) {
                     AdbPackage var10 = new AdbPackage(var9.group(2), var9.group(1));
                     var4.add(var10);
                  }
               }
            }

            return var4;
         }
      }
   }

   public synchronized boolean startActivity(String var1, String var2, boolean var3, boolean var4, boolean var5) {
      ArrayList var6 = new ArrayList(Arrays.asList("am", "start"));
      if (var3) {
         var6.add("-D");
      }

      if (var4) {
         var6.add("-W");
      }

      if (var5) {
         var6.add("-S");
      }

      var6.add("-n");
      var6.add(Strings.ff("%s/.%s", var1, var2));
      AdbResult var7 = this.shell(var6);
      return var7.isSuccess(null, false);
   }

   public synchronized boolean startActivity(String var1, String var2) {
      return this.startActivity(var1, var2, false, false, false);
   }

   public synchronized boolean stopApp(String var1) {
      List var2 = Arrays.asList("am", "force-stop", var1);
      AdbResult var3 = this.shell(var2);
      return var3.isSuccess(null, false) && AdbUtil.heurSuccess(var3.getOutput());
   }

   public synchronized void killProcess(String var1, int var2) {
      this.shellLog(var1, Arrays.asList("kill", Integer.toString(var2)));
   }

   public synchronized boolean stopProcess(String var1, int var2) {
      return this.killProcess(var1, var2, "SIGSTOP", "TSTP");
   }

   public synchronized void killProcess(String var1, int var2, int var3) {
      this.shellLog(var1, Arrays.asList("kill", "-" + var3, Integer.toString(var2)));
   }

   public synchronized boolean killProcess(String var1, int var2, String... var3) {
      for (String var7 : var3) {
         if (!var7.startsWith("-")) {
            var7 = "-" + var7;
         }

         AdbResult var8 = this.shell(var1, Arrays.asList("kill", var7, Integer.toString(var2)));
         if (var8.isSuccess(Boolean.FALSE, false)) {
            return true;
         }

         String var9 = var8.getOutputString();
         if (!var9.toLowerCase().startsWith("invalid signal name")) {
            return true;
         }
      }

      return false;
   }

   public synchronized boolean hasFile(String var1, String var2) {
      AdbResult var3 = this.shell(var1, Arrays.asList("ls", var2));
      return var3.isSuccess(Boolean.TRUE);
   }

   public synchronized boolean touchFile(String var1, String var2) {
      AdbResult var3 = this.shell(var1, Arrays.asList("touch", var2));
      return var3.isSuccess();
   }

   public synchronized boolean deleteFile(String var1, String var2, boolean var3) {
      ArrayList var4 = new ArrayList();
      var4.add("rm");
      var4.add("-f");
      if (var3) {
         var4.add("-r");
      }

      var4.add(var2);
      AdbResult var5 = this.shell(var1, var4);
      return var5.isSuccess();
   }

   public synchronized boolean copyFile(String var1, String var2, String var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      var5.add("cp");
      if (var4) {
         var5.add("-R");
      }

      var5.add(var2);
      var5.add(var3);
      AdbResult var6 = this.shell(var1, var5);
      return var6.isSuccess();
   }

   public synchronized boolean chmod(String var1, String var2, int var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      var5.add("chmod");
      if (var4) {
         var5.add("-R");
      }

      var5.add(Integer.toOctalString(var3));
      var5.add(var2);
      AdbResult var6 = this.shell(var1, var5);
      return var6.isSuccess();
   }

   public synchronized boolean chown(String var1, String var2, String var3, String var4, boolean var5) {
      ArrayList var6 = new ArrayList();
      var6.add("chown");
      if (var5) {
         var6.add("-R");
      }

      var6.add(var3 + (var4 == null ? "" : ":" + var4));
      var6.add(var2);
      AdbResult var7 = this.shell(var1, var6);
      return var7.isSuccess();
   }

   public synchronized String which(String var1, String var2) {
      AdbResult var3 = this.shell(var1, Arrays.asList("which", var2));
      return !var3.isSuccess(Boolean.TRUE) ? null : Strings.replaceNewLines(var3.getOutputString(), "");
   }

   public synchronized long dateEpoch() {
      AdbResult var1 = this.shell(null, Arrays.asList("date", "+%s"));
      return !var1.isSuccess(Boolean.TRUE, false) ? -1L : Conversion.stringToLong(var1.getOutputString(), -1L);
   }

   public synchronized String dateString(boolean var1) {
      String var2;
      if (var1) {
         var2 = "\"+%Y-%m-%d_%H:%M:%S_%z\"";
      } else {
         var2 = "\"+%Y-%m-%d_%H:%M:%S\"";
      }

      AdbResult var3 = this.shell(null, Arrays.asList("date", var2));
      return !var3.isSuccess(Boolean.TRUE) ? null : Strings.trim(var3.getOutputString());
   }

   public synchronized AdbResult shellLog(String var1, List var2) {
      return this.shell(null, null, var1, var2, true);
   }

   public synchronized AdbResult shell(String var1, List var2) {
      return this.shell(null, null, var1, var2, false);
   }

   public synchronized AdbResult shell(Long var1, String var2, String var3, List var4) {
      return this.shell(var1, var2, var3, var4, false);
   }

   public synchronized AdbResult shell(List var1) {
      return this.shell(null, null, null, var1, false);
   }

   public synchronized AdbResult shell(Long var1, String var2, String var3, List var4, boolean var5) {
      ArrayList var6 = new ArrayList(var4);
      if (var3 != null && this.getRunAs() != null) {
         var6.add(0, this.getRunAs());
         var6.add(1, var3);
      }

      var6.add(0, "shell");
      var6.add("|| echo adb_error_sent_to_jeb");
      byte[] var7 = this.executeOn(var1, var2, var6);
      if (var5 && var7 != null && var7.length > 0) {
         logger.info(S.L("Command \"%s %s\" returned: %s)"), "adb", Strings.join(" ", var6), Strings.decodeLocal(var7));
      }

      return new AdbResult(var7);
   }

   public synchronized byte[] executeOn(Long var1, String var2, Collection var3) {
      ArrayList var4 = new ArrayList(var3);
      var2 = this.determinePreferredTarget(var2);
      if (var2 != null) {
         var4.add(0, "-s");
         var4.add(1, var2);
      }

      return this.execute(var1, var4);
   }

   public synchronized byte[] execute(Long var1, Collection var2) {
      ArrayList var3 = new ArrayList(var2);
      var3.add(0, this.adbPath);
      CommandExec var4 = new CommandExec(var1 == null ? this.defaultTimeout : var1);
      var4.execute(var3);
      return var4.getOutput();
   }

   public synchronized ProcessWrapper executeAsync(Long var1, Collection var2) throws IOException {
      ArrayList var3 = new ArrayList(var2);
      var3.add(0, this.adbPath);
      String[] var4 = (String[])var3.toArray(new String[var3.size()]);
      ProcessWrapper var5 = new ProcessWrapper(var1 == null ? this.defaultTimeout : var1, var4);

      try {
         var5.start();
         Object[] var8 = new Object[]{Strings.joinList(var3)};
         return var5;
      } catch (IOException var7) {
         Object[] var10000 = new Object[]{Strings.joinList(var3), var7};
         throw var7;
      }
   }

   public synchronized ProcessWrapper shellAsync(Long var1, String var2, String var3, List var4) throws IOException {
      ArrayList var5 = new ArrayList(var4);
      if (var3 != null && this.getRunAs() != null) {
         var5.add(0, this.getRunAs());
         var5.add(1, var3);
      }

      var5.add(0, "shell");
      var2 = this.determinePreferredTarget(var2);
      if (var2 != null) {
         var5.add(0, "-s");
         var5.add(1, var2);
      }

      return this.executeAsync(var1, var5);
   }

   @Override
   public synchronized String toString() {
      return Strings.ff("adb={%s},defaultTimeout={%dms}", this.adbPath, this.defaultTimeout);
   }
}
