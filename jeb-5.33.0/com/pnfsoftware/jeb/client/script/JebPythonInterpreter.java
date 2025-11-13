package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.client.api.IClientContext;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.base.SystemUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandInterpreter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.python.core.PyCode;
import org.python.core.PyException;
import org.python.core.PyNone;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class JebPythonInterpreter implements ICommandInterpreter {
   private static final ILogger logger = GlobalLog.getLogger(JebPythonInterpreter.class);
   private File scriptsDir;
   private IClientContext ctx;
   private boolean allowAutoImport;
   private TypeInfoProvider typeinfoprv;
   private int status;
   private PythonInterpreter interpreter;
   private LogRedirectionOutputStream out;
   private LogRedirectionOutputStream err;
   private Set autoImportedTypes = new HashSet();
   private static final String _jython = "Jython 2.7";

   public JebPythonInterpreter(File var1, IClientContext var2) throws JebException {
      this.scriptsDir = var1;
      this.ctx = var2;
   }

   public void setAllowAutoImport(boolean var1) {
      this.allowAutoImport = var1;
   }

   public boolean isAllowAutoImport() {
      return this.allowAutoImport;
   }

   public void setTypeInfoProvider(TypeInfoProvider var1) {
      this.typeinfoprv = var1;
   }

   public TypeInfoProvider getTypeInfoProvider() {
      return this.typeinfoprv;
   }

   public boolean addToSystemPath(File var1) {
      this.interpreter.exec(Strings.ff("sys.path.append(u\"%s\")", var1.getAbsolutePath().replace("\\", "\\\\")));
      return true;
   }

   @Override
   public void prepare() {
      if (this.status == 0) {
         this.status = -1;
         Properties var1 = new Properties();
         var1.put("python.console.encoding", "UTF-8");
         if (SystemUtil.getMajorJavaVersion() <= 8) {
            var1.put("python.security.respectJavaAccessibility", "false");
         }

         Properties var2 = System.getProperties();
         PythonInterpreter.initialize(var2, var1, new String[0]);
         long var3 = System.currentTimeMillis();
         JythonUtil.softCheckJavaVersion();
         this.interpreter = new PythonInterpreter(null, new PySystemState());
         Object[] var10000 = new Object[]{System.currentTimeMillis() - var3};
         this.interpreter.exec("import sys");
         if (this.scriptsDir != null) {
            this.addToSystemPath(this.scriptsDir);
         }

         this.out = new LogRedirectionOutputStream(60, true);
         this.interpreter.setOut(this.out);
         this.err = new LogRedirectionOutputStream(60, false);
         this.interpreter.setErr(this.err);
         JebPythonInterpreter$1 var5 = new JebPythonInterpreter$1(this);
         this.interpreter.setIn(var5);
         if (this.ctx != null) {
            this.interpreter.set("jeb", this.ctx);
         }

         this.status = 1;
      }
   }

   public void injectObject(String var1, Object var2) throws ScriptExecutionException {
      this.interpreter.set(var1, var2);
   }

   @Override
   public ExecutionResult executeCommand(String var1) {
      return this.executeInternal(var1, this.isAllowAutoImport());
   }

   public ExecutionResult executeInternal(String var1, boolean var2) {
      if ("help".equals(var1.trim())) {
         String var13 = "";
         var13 = var13 + Strings.ff(S.L("JEB Developers Portal: %s"), "https://www.pnfsoftware.com/jeb/devportal");
         var13 = var13 + Strings.ff(S.L("\nAPI Reference Documention: %s"), "https://www.pnfsoftware.com/jeb/apidoc");
         var13 = var13 + Strings.ff(S.L("\nSample Scripts: %s"), "https://github.com/pnfsoftware/jeb-samplecode/tree/master/scripts");
         return ExecutionResult.success(var13);
      } else {
         try {
            this.prepare();
         } catch (Exception var9) {
            return ExecutionResult.error(var9);
         }

         PyObject var3;
         while (true) {
            try {
               PyCode var17 = this.interpreter.compile(var1);
               var3 = this.interpreter.eval(var17);
               break;
            } catch (PyException var11) {
               PyException var4 = var11;
               String var5 = var11.toString();
               if (var2) {
                  try {
                     String var6 = var4.value.toString();
                     String var7 = this.getUndefinedName(var6);
                     if (var7 != null && this.autoImportedTypes.add(var7)) {
                        TypeInfo var8 = this.findCandidateJebApiType(var7);
                        if (var8 != null) {
                           if (!var8.getSimpleName().equals(var7)) {
                              var5 = var5 + Strings.ff(S.L("# Did you mean %s?"), var8.getSimpleName());
                           } else if (this.loadJebApiType(var8.getName())) {
                              logger.debug(S.L("# Auto-imported %s"), var8.getName());
                              var2 = true;
                              continue;
                           }
                        }
                     }
                  } catch (Exception var10) {
                  }
               }

               return ExecutionResult.error(var5);
            } catch (Exception var12) {
               return ExecutionResult.error(var12);
            }
         }

         String var18;
         if (var3 != null && !(var3 instanceof PyNone)) {
            var18 = var3.toString();
            if (var3 instanceof PyString) {
               var18 = "'" + var18 + "'";
            }
         } else {
            var18 = this.out.pullCurrent();
         }

         return ExecutionResult.success(var18);
      }
   }

   public Collection getAutoImportedTypes() {
      return Collections.unmodifiableSet(this.autoImportedTypes);
   }

   @Override
   public String getName() {
      return "py";
   }

   @Override
   public String getDescription() {
      return Strings.ff(S.L("Python Interpreter (built on %s)"), "Jython 2.7");
   }

   @Override
   public String getBanner() {
      String var1 = "";
      var1 = var1 + "* " + Strings.ff(S.L("JEB Python interpreter (EXPERIMENTAL) - uses %s\n"), "Jython 2.7");
      var1 = var1
         + "* "
         + Strings.ff(
            S.L("The current client context (type: %s) is located in the `%s` global variable\n"), "com.pnfsoftware.jeb.client.api.IClientContext", "jeb"
         );
      if (this.scriptsDir != null) {
         var1 = var1 + "* " + Strings.ff(S.L("The following folder was auto-added to %s: %s\n"), "sys.path", this.scriptsDir);
      }

      return var1 + "* " + S.L("Refer to the API documentation (Help menu) and sample scripts and plugins to get started");
   }

   @Override
   public boolean shouldDisplayRawResults() {
      return true;
   }

   @Override
   public AutocompletionResult autoComplete(String var1) {
      try {
         this.prepare();
      } catch (Exception var9) {
         return AutocompletionResult.EMPTY;
      }

      if (var1 == null) {
         return AutocompletionResult.EMPTY;
      } else {
         int var2 = var1.lastIndexOf(46);
         if (var2 < 0) {
            return AutocompletionResult.EMPTY;
         } else {
            String var3 = var1.substring(var2 + 1);
            String var4 = var1.substring(0, var2);
            var2 = var4.lastIndexOf(46);
            if (var2 >= 0) {
               var4 = var4.substring(var2 + 1);
            }

            var2 = var4.lastIndexOf(32);
            if (var2 >= 0) {
               var4 = var4.substring(var2 + 1);
            }

            var2 = var4.lastIndexOf(9);
            if (var2 >= 0) {
               var4 = var4.substring(var2 + 1);
            }

            if (var4.isEmpty()) {
               return AutocompletionResult.EMPTY;
            } else {
               PyObject var5 = this.interpreter.get(var4);
               if (var5 != null) {
                  Object var6 = var5.__tojava__(Object.class);
                  if (var6 != null) {
                     AutocompletionResult var7 = new AutocompletionResult(null, '.');
                     if (var6 instanceof PyObject) {
                        List var8 = JythonUtil.retrieveMembers((PyObject)var6);
                        var7.addAll(var8);
                     } else {
                        HashSet var13 = new HashSet();
                        var13.add(Object.class);
                        this.recordCandidates(var6.getClass(), var3, var7, var13);
                     }

                     return var7;
                  }
               }

               return AutocompletionResult.EMPTY;
            }
         }
      }
   }

   private void recordCandidates(Class var1, String var2, AutocompletionResult var3, Set var4) {
      if (var4.add(var1)) {
         if (!this.isJebPrivateType(var1)) {
            for (Method var8 : var1.getMethods()) {
               if ((var8.getModifiers() & 1) != 0) {
                  this.attemptRecordName(var8.getName(), var2, var3);
               }
            }
         }

         Class var10 = var1.getSuperclass();
         if (var10 != null) {
            this.recordCandidates(var10, var2, var3, var4);

            for (Class var9 : var1.getInterfaces()) {
               this.recordCandidates(var9, var2, var3, var4);
            }
         }
      }
   }

   private boolean isJebPrivateType(Class var1) {
      String var2 = var1.getName();
      if (var2.startsWith("com.pnfsoftware.jeb.clienti")) {
         return true;
      } else if (var2.startsWith("com.pnfsoftware.jeb.corei")) {
         return true;
      } else {
         return var2.startsWith("com.pnfsoftware.jeb.rcpclient") ? true : var2.startsWith("com.pnfsoftware.jebglobal");
      }
   }

   private void attemptRecordName(String var1, String var2, AutocompletionResult var3) {
      if (var1.startsWith(var2)) {
         if (!var1.equals("this") && !var1.startsWith("this$")) {
            var3.add(var1);
         }
      }
   }

   private String getUndefinedName(String var1) {
      return var1.startsWith("name '") && var1.endsWith("' is not defined") ? var1.substring(6, var1.length() - 16) : null;
   }

   private TypeInfo findCandidateJebApiType(String var1) {
      return this.typeinfoprv == null ? null : this.typeinfoprv.findType(var1, true);
   }

   private boolean loadJebApiType(String var1) {
      int var2 = var1.lastIndexOf(46);
      String var3 = var1.substring(0, var2);
      String var4 = var1.substring(var2 + 1);
      ExecutionResult var5 = this.executeInternal("from " + var3 + " import " + var4, false);
      return var5.isSuccess();
   }
}
