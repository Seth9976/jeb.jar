package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.client.api.IScript;
import com.pnfsoftware.jeb.util.base.SystemUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Properties;
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class PythonScriptFactory {
   private static final ILogger logger = GlobalLog.getLogger(PythonScriptFactory.class);
   private static int globalCreationCount;
   private String directory;
   private String filenameNoExt;
   private String classname;
   private PythonInterpreter interpreter;
   private PyObject scriptClass;

   public PythonScriptFactory(String var1, String var2, String var3) throws ScriptInitializationException {
      this.directory = var1;
      this.filenameNoExt = var2;
      this.classname = var3;
      this.init(null);
   }

   private void init(String var1) throws ScriptInitializationException {
      if (globalCreationCount++ == 0) {
         logger.info(S.L("Initializing Jython, please wait..."));
      }

      Properties var2 = new Properties();
      var2.put("python.console.encoding", "UTF-8");
      if (SystemUtil.getMajorJavaVersion() <= 8) {
         var2.put("python.security.respectJavaAccessibility", "false");
      }

      Properties var3 = System.getProperties();
      PythonInterpreter.initialize(var3, var2, new String[0]);
      long var4 = System.currentTimeMillis();
      JythonUtil.softCheckJavaVersion();
      this.interpreter = new PythonInterpreter(null, new PySystemState());
      Object[] var8 = new Object[]{System.currentTimeMillis() - var4};
      this.interpreter.exec("import sys");
      this.interpreter.exec(Strings.ff("sys.path.append(u\"%s\")", this.directory.replace("\\", "\\\\")));
      String var6 = Strings.ff("%s = __import__('%s', fromlist=['%s']).%s", this.classname, this.filenameNoExt, this.classname, this.classname);
      this.interpreter.exec(var6);
      LogRedirectionOutputStream var7 = new LogRedirectionOutputStream();
      this.interpreter.setOut(var7);
      this.scriptClass = this.interpreter.get(this.classname);
   }

   public void close() {
      this.interpreter.cleanup();
      this.interpreter = null;
   }

   public IScript create() throws ScriptInitializationException {
      if (this.interpreter == null) {
         throw new IllegalStateException("The script interpreter is not initialized");
      } else {
         PyObject var1 = this.scriptClass.__call__();
         return (IScript)var1.__tojava__(IScript.class);
      }
   }
}
