package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class JebPythonPluginFactory {
   private static final ILogger logger = GlobalLog.getLogger(JebPythonPluginFactory.class);
   private String directory;
   private String classname;
   private PythonInterpreter interpreter;
   private PyObject pluginClass;

   public JebPythonPluginFactory(String var1, String var2) {
      this.directory = var1;
      this.classname = var2;
      this.init();
   }

   private void init() {
      long var1 = System.currentTimeMillis();
      JythonUtil.softCheckJavaVersion();
      this.interpreter = new PythonInterpreter(null, new PySystemState());
      this.interpreter.exec("import sys");
      this.interpreter.exec(Strings.ff("sys.path.append(\"%s\")", this.directory.replace("\\", "\\\\")));
      this.interpreter.exec(Strings.ff("from %s import %s", this.classname, this.classname));
      LogRedirectionOutputStream var3 = new LogRedirectionOutputStream();
      this.interpreter.setOut(var3);
      this.pluginClass = this.interpreter.get(this.classname);
      logger.debug(S.L("%s: Python plugin loaded in %dms"), this.classname, System.currentTimeMillis() - var1);
   }

   public void close() {
      this.interpreter.cleanup();
      this.interpreter = null;
   }

   public Class reload() {
      this.init();
      return this.getPluginClass();
   }

   public Class getPluginClass() {
      if (this.interpreter == null) {
         throw new IllegalStateException("The factory was closed");
      } else {
         return (Class)this.pluginClass.__tojava__(Class.class);
      }
   }
}
