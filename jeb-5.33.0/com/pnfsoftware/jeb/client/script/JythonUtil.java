package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.base.SystemUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.python.core.PyClass;
import org.python.core.PyFunction;
import org.python.core.PyObject;

public class JythonUtil {
   private static final ILogger logger = GlobalLog.getLogger(JythonUtil.class);
   public static final int FLAG_CLASS = 1;
   public static final int FLAG_FUNCTION = 2;

   public static void softCheckJavaVersion() {
      int var0 = SystemUtil.getMajorJavaVersion();
      if (var0 < 8) {
         logger.warn(S.L("Jython requires JDK 8 or above. You are currently using JDK %d."), var0);
      }
   }

   public static File findJythonJar(File var0) throws JebException {
      for (String var4 : var0.list()) {
         if (var4.startsWith("jython") && var4.toLowerCase().endsWith(".jar")) {
            return new File(var0, var4);
         }
      }

      throw new JebException("Cannot find Jython: Copy your Jython JAR package to the folder: " + var0);
   }

   public static List retrieveMembers(PyObject var0) {
      return retrieveMembers(var0, 3);
   }

   public static List retrieveMembers(PyObject var0, int var1) {
      ArrayList var2 = new ArrayList();

      try {
         collectMethodsFromDict(var0, var2, var1);
      } catch (ReflectiveOperationException var5) {
      }

      try {
         PyObject var3 = (PyObject)var0.getClass().getField("instclass").get(var0);
         collectMethodsFromDict(var3, var2, var1);
      } catch (ReflectiveOperationException var4) {
      }

      return var2;
   }

   private static void collectMethodsFromDict(PyObject var0, List var1, int var2) throws ReflectiveOperationException {
      Object var3 = var0.getClass().getField("__dict__").get(var0);
      Map var4 = (Map)var3.getClass().getDeclaredMethod("getMap").invoke(var3);

      for (Entry var6 : var4.entrySet()) {
         String var7 = (String)var6.getKey();
         Object var8 = var6.getValue();
         if ((var2 & 1) != 0 && var8 instanceof PyClass) {
            var1.add(var7);
         } else if ((var2 & 2) != 0 && var8 instanceof PyFunction) {
            var1.add(var7);
         }
      }
   }
}
