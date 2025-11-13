package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.reflect.ClasspathCollector;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class cvn {
   static File q = qx.q();
   private JavaCompiler RF;
   private String xK;

   public cvn(File var1) {
      ClasspathCollector var2 = new ClasspathCollector();
      if (var1 != null) {
         var2.add(var1.getAbsolutePath());
      }

      if (q.isFile()) {
         var2.addFromJar(q);
      } else if (q.isDirectory()) {
         var2.add(q.getAbsolutePath());
      }

      this.xK = var2.toString();
      this.RF = ToolProvider.getSystemJavaCompiler();
      if (this.RF == null) {
         throw new RuntimeException("javac not found");
      }
   }

   public String q() {
      return this.xK;
   }

   public JavaCompiler RF() {
      return this.RF;
   }

   public void q(File var1) {
      String var2 = this.xK;
      if (!var2.isEmpty()) {
         var2 = var2 + File.pathSeparatorChar;
      }

      var2 = var2 + var1.getParent();
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      ByteArrayOutputStream var4 = new ByteArrayOutputStream();
      int var5 = this.RF.run(System.in, var3, var4, "-g", "-cp", var2, var1.getPath());
      if (var5 != 0) {
         byte[] var6 = var4.toByteArray();
         String var7 = Strings.decodeLocal(var6);
         throw new IllegalArgumentException(Strings.ff("javac failed (%d): %s", var5, var7));
      }
   }

   static {
      if (q == null) {
         throw new RuntimeException("Cannot retrieve JEB classes");
      }
   }
}
