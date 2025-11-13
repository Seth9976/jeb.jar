package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.base.SystemUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class JebPythonPluginService {
   private static final ILogger logger = GlobalLog.getLogger(JebPythonPluginService.class);
   private File pyLibDir;
   private ClassLoader cl;
   private boolean initialized;
   private long creatorThreadId;
   private PythonInterpreter singleInterpreter;
   private Set syspaths = new LinkedHashSet();
   private Set loadedPluginFiles = new LinkedHashSet();
   private Set loadedPluginNames = new LinkedHashSet();

   public JebPythonPluginService(File var1) throws JebException {
      this(var1, null);
   }

   public JebPythonPluginService(File var1, ClassLoader var2) throws JebException {
      if (var1 != null && var1.isDirectory()) {
         this.pyLibDir = var1;
         this.cl = var2;
      } else {
         throw new JebException(Strings.ff("Path \"%s\" is not a directory", var1));
      }
   }

   public JebPythonPluginService(String var1) throws JebException {
      if (Strings.isBlank(var1)) {
         throw new IllegalArgumentException("The Python plugins library path cannot be null or empty");
      } else {
         this.pyLibDir = new File(var1);
         if (!this.pyLibDir.exists()) {
            if (!this.pyLibDir.mkdir()) {
               throw new JebException(Strings.ff("Cannot create directory \"%s\"", var1));
            }
         } else if (!this.pyLibDir.isDirectory()) {
            throw new JebException(Strings.ff("Path \"%s\" is not a directory", var1));
         }
      }
   }

   private synchronized void initJython() throws JebException {
      if (!this.initialized) {
         logger.info(S.L("JEB Python plugins are detected: Initializing Jython..."));
         this.creatorThreadId = Thread.currentThread().getId();
         Properties var1 = new Properties();
         var1.put("python.console.encoding", "UTF-8");
         if (SystemUtil.getMajorJavaVersion() <= 8) {
            var1.put("python.security.respectJavaAccessibility", "false");
         }

         Properties var2 = System.getProperties();
         PythonInterpreter.initialize(var2, var1, new String[0]);
         this.initialized = true;
      }
   }

   public synchronized JebPythonPluginFactory createFactory(String var1) throws JebException {
      File var2 = new File(var1);
      if (!var2.exists()) {
         if (!var2.isAbsolute()) {
            var2 = new File(this.pyLibDir, var1);
         }

         if (!var2.exists()) {
            throw new JebException("The plugin was not found: " + var1);
         }
      }

      String var3 = var2.getName();
      if (!var3.toLowerCase().endsWith(".py")) {
         throw new JebException("Expected a '.py' extension for Python plugin");
      } else {
         this.initJython();
         JebPythonPluginFactory var4 = null;

         try {
            String var5 = var3.substring(0, var3.length() - 3);
            return var4 = new JebPythonPluginFactory(var2.getAbsoluteFile().getParent(), var5);
         } catch (RuntimeException var7) {
            try {
               if (var4 != null) {
                  var4.close();
               }
            } catch (Exception var6) {
            }

            throw new JebException(var7.getMessage());
         }
      }
   }

   public synchronized Class getPluginClass(String var1) throws JebException {
      File var2 = new File(var1);
      if (!var2.exists()) {
         if (!var2.isAbsolute()) {
            var2 = new File(this.pyLibDir, var1);
         }

         if (!var2.exists()) {
            throw new JebException("The plugin was not found: " + var1);
         }
      }

      String var3 = var2.getParent();
      String var4 = var2.getName().replaceFirst("\\.py", "");
      long var5 = System.currentTimeMillis();
      this.initJython();
      if (this.singleInterpreter == null) {
         JythonUtil.softCheckJavaVersion();
         PySystemState var7 = new PySystemState();
         if (this.cl != null) {
            var7.setClassLoader(this.cl);
         }

         this.singleInterpreter = new PythonInterpreter(null, var7);
         LogRedirectionOutputStream var8 = new LogRedirectionOutputStream();
         this.singleInterpreter.setOut(var8);
         this.singleInterpreter.exec("import sys");
      }

      if (!this.syspaths.contains(var3)) {
         this.syspaths.add(var3);
         this.singleInterpreter.exec(Strings.ff("sys.path.append(u\"%s\")", var3.replace("\\", "\\\\")));
      }

      if (this.loadedPluginFiles.contains(var1)) {
         this.singleInterpreter.exec(Strings.ff("import %s", var4));
         this.singleInterpreter.exec(Strings.ff("reload(%s)", var4));
      }

      this.singleInterpreter.exec(Strings.ff("from %s import %s", var4, var4));
      PyObject var9 = this.singleInterpreter.get(var4);
      Class var10 = (Class)var9.__tojava__(Class.class);
      this.loadedPluginFiles.add(var1);
      this.loadedPluginNames.add(var4);
      logger.info(S.L("%s: Python plugin loaded in %dms"), var4, System.currentTimeMillis() - var5);
      return var10;
   }

   public IPlugin createPlugin(Class var1) throws Exception {
      Thread var2 = Thread.currentThread();
      ClassLoader var3 = var2.getContextClassLoader();

      IPlugin var4;
      try {
         if (var2.getId() != this.creatorThreadId && this.cl != null) {
            var2.setContextClassLoader(this.cl);
         }

         var4 = (IPlugin)var1.getDeclaredConstructor().newInstance();
      } finally {
         var2.setContextClassLoader(var3);
      }

      return var4;
   }
}
