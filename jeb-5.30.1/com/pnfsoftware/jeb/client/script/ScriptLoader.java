package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.client.api.IClientContext;
import com.pnfsoftware.jeb.client.api.IScript;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import org.python.core.PyException;

public class ScriptLoader {
   private static final ILogger logger = GlobalLog.getLogger(ScriptLoader.class);
   private ScriptType scripttype;
   private IScript script;
   private File libdir;

   public ScriptLoader(String var1, String var2) throws ScriptException {
      if (var1 != null && var2 != null) {
         this.libdir = new File(var2);
         if (!this.libdir.exists()) {
            if (!this.libdir.mkdir()) {
               throw new ScriptPreparationException(Strings.ff("Cannot create directory \"%s\"", var2));
            }
         } else if (!this.libdir.isDirectory()) {
            throw new ScriptPreparationException(Strings.ff("Path \"%s\" is not a directory", var2));
         }

         this.load(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public ScriptType getScriptType() {
      return this.scripttype;
   }

   private void load(String var1) throws ScriptException {
      File var2 = new File(var1);
      if (!var2.exists()) {
         var2 = new File(this.libdir, var1);
         if (!var2.exists()) {
            throw new ScriptPreparationException(S.L("Script file was not found"));
         }
      }

      String var3 = var2.getName();
      if (var3.endsWith(".py")) {
         String var4 = var3.substring(0, var3.length() - 3);
         String var5 = ScriptUtil.filenameToClassname(var4);
         if (var5 == null) {
            throw new ScriptPreparationException("Illegal script name");
         } else {
            this.loadPython(var2, var4, var5);
         }
      } else {
         throw new ScriptPreparationException("Unsupported script extension");
      }
   }

   private void loadPython(File var1, String var2, String var3) throws ScriptException {
      PythonScriptFactory var4 = null;

      try {
         var4 = new PythonScriptFactory(var1.getAbsoluteFile().getParent(), var2, var3);
         this.script = var4.create();
      } finally {
         if (var4 != null) {
            var4.close();
         }
      }

      this.scripttype = ScriptType.PYTHON;
   }

   private void initJython() throws ScriptException {
      String var1 = null;

      for (String var5 : this.libdir.list()) {
         if (var5.startsWith("jython") && var5.toLowerCase().endsWith(".jar")) {
            var1 = new File(this.libdir, var5).getAbsolutePath();
            break;
         }
      }

      if (var1 == null) {
         throw new ScriptPreparationException("Jython is required to run client scripts, refer to SCRIPTS.TXT for setup instructions");
      } else {
         logger.debug(S.L("Found Jython package: %s"), var1);
      }
   }

   public void execute(IClientContext var1) throws ScriptException {
      try {
         this.script.run(var1);
      } catch (PyException var3) {
         throw new ScriptExecutionException(var3);
      } catch (Exception var4) {
         throw new ScriptException(var4);
      }
   }
}
