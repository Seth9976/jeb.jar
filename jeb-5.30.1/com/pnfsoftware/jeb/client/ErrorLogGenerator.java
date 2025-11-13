package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IEnginesPlugin;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJOptimizer;
import com.pnfsoftware.jeb.util.base.Env;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.base.Stacktraces;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.format.SizeFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class ErrorLogGenerator {
   public static final int ERRORLOG_VERSION = 2;
   public static final String KEY_URLENCODED_THREADS = "urlencoded-threads";
   private Throwable t;
   private LinkedHashMap records = new LinkedHashMap();
   private String timestamp;

   public static String generateUrlencodedThreadsRecordValue() {
      String var0 = Stacktraces.formatThreadStacktraces();
      return Strings.urlencodeUTF8(var0);
   }

   public ErrorLogGenerator(Throwable var1) {
      Runtime var2 = Runtime.getRuntime();
      this.t = var1;
      this.timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
      this.addRecord("logversion", 2);
      this.addRecord("product", "JEB");
      this.addRecord("timestamp", this.timestamp);
      this.addRecord("version", AbstractClientContext.app_ver);
      this.addRecord("userid", Licensing.user_id);
      this.addRecord("licenseid", Licensing.license_id);
      this.addRecord("build", Licensing.buildkey);
      this.addRecord("system", SystemInformation.osname + " " + SystemInformation.osversion + " " + SystemInformation.osarch);
      if (SystemInformation.osfullname != null && SystemInformation.osfullname.length() > 0) {
         this.addRecord("system-fullname", SystemInformation.osfullname);
      }

      if (OSType.determine().isLinux()) {
         this.addRecord(
            "linux-xdg-env", Strings.ff("%s; %s; %s", Env.getsafe("XDG_CURRENT_DESKTOP"), Env.getsafe("XDG_SESSION_DESKTOP"), Env.getsafe("XDG_SESSION_TYPE"))
         );
      }

      this.addRecord("java", SystemInformation.javavendor + " " + SystemInformation.javaversion);
      this.addRecord("locale", SystemInformation.localeinfo);
      this.addRecord(
         "memory-usage",
         Strings.ff(
            "used=%s,alloc=%s,max=%s",
            SizeFormatter.formatByteSize(var2.totalMemory() - var2.freeMemory()),
            SizeFormatter.formatByteSize(var2.totalMemory()),
            SizeFormatter.formatByteSize(var2.maxMemory())
         )
      );
      byte var3 = 0;
      String var4 = Throwables.formatStacktrace(var1);
      if (Strings.splitLines(Strings.trim(var4)).length == 1) {
         var4 = Throwables.formatStacktraceAlt(var1);
         var3 = 1;
      }

      this.addRecord("stacktrace", var4);
      if (var3 != 0) {
         this.addRecord("stacktrace-type", Integer.valueOf(var3));
      }
   }

   public void recordEnginesInformation(IEnginesContext var1) {
      List var2 = this.generatePluginsList(var1.getPluginManager().getPlugins(IEnginesPlugin.class, false));
      this.addRecord("backend-plugins-generic", Strings.joinList(var2));
      var2 = this.generatePluginsList(var1.getUnitIdentifiers());
      this.addRecord("backend-plugins-parsers", Strings.joinList(var2));
      var2 = this.generatePluginsList(var1.getPluginManager().getPlugins(IDOptimizer.class, false));
      this.addRecord("backend-plugins-dexdec-ir", Strings.joinList(var2));
      var2 = this.generatePluginsList(var1.getPluginManager().getPlugins(IJOptimizer.class, false));
      this.addRecord("backend-plugins-dexdec-ast", Strings.joinList(var2));
   }

   private List generatePluginsList(List var1) {
      ArrayList var2 = new ArrayList();

      for (IPlugin var4 : var1) {
         IPluginInformation var5 = var4.getPluginInformation();
         if (var5 == null) {
            var2.add("<unspecified-plugin>");
         } else if (var5.getVersion() == null) {
            var2.add(Strings.safe(var5.getName(), "<no-name>"));
         } else {
            var2.add(Strings.ff("%s:%s", Strings.safe(var5.getName(), "<no-name>"), Strings.safe(var5.getVersion(), "no-version")));
         }
      }

      return var2;
   }

   public LinkedHashMap getRecords() {
      return this.records;
   }

   public String addRecord(String var1, Object var2) {
      return this.addRecord(var1, var2, true);
   }

   public String addRecord(String var1, Object var2, boolean var3) {
      int var4 = 0;

      String var5;
      for (var5 = var1; this.records.containsKey(var5); var5 = var1 + "-" + ++var4) {
         if (!var3) {
            return null;
         }
      }

      this.records.put(var5, Strings.toString(var2));
      return var5;
   }

   public boolean setRecord(String var1, Object var2) {
      return this.records.put(var1, Strings.toString(var2)) == null;
   }

   public boolean removeRecord(String var1) {
      return this.records.remove(var1) != null;
   }

   public Throwable getThrowable() {
      return this.t;
   }

   public String getLog() {
      return this.getLog(null, false);
   }

   public String getLog(Collection var1, boolean var2) {
      if (var1 == null) {
         var1 = this.records.keySet();
      }

      StringBuilder var3 = new StringBuilder();

      for (String var5 : var1) {
         String var6 = (String)this.records.get(var5);
         if (var6 != null) {
            this.writeEntry(var3, var5, var6, var2);
         }
      }

      return var3.toString();
   }

   private void writeEntry(StringBuilder var1, String var2, String var3, boolean var4) {
      if (var4) {
         var2 = Strings.urlencodeUTF8(var2);
         var3 = Strings.urlencodeUTF8(var3);
      }

      Strings.ff(var1, "%s: %s\n", var2, var3);
   }

   @Override
   public String toString() {
      return this.getLog();
   }

   public String dumpTo(String var1) {
      return this.dumpTo(var1, null, false);
   }

   public String dumpTo(String var1, String var2, boolean var3) {
      if (var2 == null) {
         var2 = Strings.ff("jeb_errorlog_%s.txt", this.timestamp);
      }

      File var4 = new File(var1, var2);

      try {
         IO.writeFile(var4, Strings.encodeUTF8(this.getLog(null, var3)));
         return IO.abs(var4);
      } catch (IOException var5) {
         return null;
      }
   }
}
