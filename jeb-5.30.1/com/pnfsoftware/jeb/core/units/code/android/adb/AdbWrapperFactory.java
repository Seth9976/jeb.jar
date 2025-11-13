package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.util.concurrent.CommandExec;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AdbWrapperFactory {
   private static final ILogger logger = GlobalLog.getLogger(AdbWrapperFactory.class);
   public static final long STANDARD_TIMEOUT = 5000L;
   private String adbPath;
   private boolean initialized;
   private long defaultTimeout;

   public static synchronized AdbWrapper createStandardWrapper() throws IOException {
      return new AdbWrapperFactory().createWrapper(null);
   }

   public AdbWrapperFactory() throws IOException {
      this(null);
   }

   public AdbWrapperFactory(String var1) throws IOException {
      this(var1, 5000L);
   }

   public AdbWrapperFactory(String var1, long var2) throws IOException {
      File var4 = var1 != null ? new File(var1) : AdbUtil.findAdbOnCurrentSystem();
      this.adbPath = var4.getPath();
      this.defaultTimeout = var2;
   }

   public synchronized boolean initialize() {
      if (this.initialized) {
         return true;
      } else {
         this.initialized = this.startServer();
         return this.initialized;
      }
   }

   public String getPath() {
      return this.adbPath;
   }

   public synchronized void setDefaultTimeout(long var1) {
      this.defaultTimeout = var1;
   }

   public synchronized long getDefaultTimeout() {
      return this.defaultTimeout;
   }

   public synchronized String getVersion() {
      byte[] var1 = this.execute(null, Arrays.asList("version"));
      if (var1 == null) {
         return null;
      } else {
         for (String var5 : Strings.splitLines(Strings.decodeLocal(var1))) {
            if (var5.startsWith("Android Debug Bridge version ")) {
               return var5.substring("Android Debug Bridge version ".length());
            }
         }

         return Strings.decodeLocal(var1).trim();
      }
   }

   public synchronized boolean startServer() {
      long var1 = Math.max(15000L, this.defaultTimeout);
      byte[] var3 = this.execute(var1, Arrays.asList("start-server"));
      return AdbUtil.heurSuccess(var3);
   }

   public synchronized void stopServer() {
      this.execute(null, Arrays.asList("stop-server"));
   }

   public synchronized List listDevices() {
      byte[] var1 = this.execute(null, Arrays.asList("devices", "-l"));
      if (var1 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String[] var3 = Strings.decodeLocal(var1).split("\\r?\\n");

         for (int var4 = 1; var4 < var3.length; var4++) {
            String var5 = Strings.trim(var3[var4]);
            if (!var5.isEmpty() && !var5.startsWith("* daemon") && !var5.startsWith("List of devices")) {
               String[] var6 = var5.split("\\s+");
               String var7 = this.extractToken(var6, 0, null);
               String var8 = this.extractToken(var6, 1, null);
               String var9 = this.extractToken(var6, 2, "product:");
               String var10 = this.extractToken(var6, 3, "model:");
               String var11 = this.extractToken(var6, 4, "device:");
               var2.add(new AdbDevice(var7, AdbDeviceStatus.fromString(var8), var9, var10, var11));
            }
         }

         return var2;
      }
   }

   private String extractToken(String[] var1, int var2, String var3) {
      if (var1 != null && var2 >= 0 && var2 < var1.length) {
         String var4 = var1[var2].trim();
         if (var3 != null) {
            if (!var4.startsWith(var3)) {
               return null;
            }

            var4 = var4.substring(var3.length());
         }

         return var4;
      } else {
         return null;
      }
   }

   public synchronized AdbWrapper createWrapper(String var1) {
      return this.createWrapper(var1, this.defaultTimeout);
   }

   public synchronized AdbWrapper createWrapper(String var1, long var2) {
      AdbWrapper var4 = new AdbWrapper(this, this.adbPath, var2);
      var4.setTargetDevice(var1);
      return var4;
   }

   private synchronized byte[] execute(Long var1, Collection var2) {
      ArrayList var3 = new ArrayList(var2);
      var3.add(0, this.adbPath);
      CommandExec var4 = new CommandExec(var1 == null ? this.defaultTimeout : var1);
      var4.execute(var3);
      return var4.getOutput();
   }

   @Override
   public synchronized String toString() {
      return Strings.ff("adbFactory={%s},defaultTimeout={%dms}", this.adbPath, this.defaultTimeout);
   }
}
