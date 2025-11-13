package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.reflect.ClasspathCollector;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class GA extends Nz {
   JavaCompiler Uv;
   String oW;

   public GA(File var1, File var2) throws JebException {
      super(var1);
      if (var2 == null) {
         var2 = qx.q();
         if (var2 == null) {
            throw new IllegalArgumentException();
         }
      }

      ClasspathCollector var3 = new ClasspathCollector();
      var3.add(var1.getAbsolutePath());
      if (var2.isFile()) {
         var3.addFromJar(var2);
      } else if (var2.isDirectory()) {
         var3.add(var2.getAbsolutePath());
      }

      this.oW = var3.toString();
      this.Uv = ToolProvider.getSystemJavaCompiler();
      if (this.Uv == null) {
         throw new JebException("Java script plugins cannot be loaded (javac compiler unavailable)");
      }
   }

   @Override
   protected String xK() {
      return "*.java";
   }

   @Override
   protected String Dw() {
      return "//";
   }

   @Override
   protected Class q(File var1) throws Exception {
      ClassLoader var2 = this.q();
      String var3 = this.oW;
      if (!var3.isEmpty()) {
         var3 = var3 + File.pathSeparatorChar;
      }

      var3 = var3 + var1.getParent();
      q.debug("Compiling Java script plugin using classpath: %s", var3);
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      ByteArrayOutputStream var5 = new ByteArrayOutputStream();
      int var6 = this.Uv.run(System.in, var4, var5, "-g", "-cp", var3, var1.getPath());
      if (var6 != 0) {
         byte[] var13 = var5.toByteArray();
         String var14 = Strings.decodeLocal(var13);
         throw new IllegalArgumentException(Strings.ff("javac failed (%d): %s", var6, var14));
      } else {
         String var7 = var1.getName();
         var7 = var7.trim();
         var7 = var7.replaceAll("\\.[jJ][aA][vV][aA]", "");
         Class var8 = var2.loadClass(var7);
         if (var8.isAssignableFrom(IPlugin.class)) {
            throw new IllegalArgumentException("The class does not implement IPlugin");
         } else {
            return var8;
         }
      }
   }
}
