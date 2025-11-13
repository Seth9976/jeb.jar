package com.pnfsoftware.jeb.corei.parsers.classfile;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.beb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarOutputStream;

public class oM {
   private static final ILogger q = GlobalLog.getLogger(oM.class);
   private nI RF;
   private nI xK;

   public oM() {
      this(true, 29, true);
   }

   public oM(boolean var1, int var2, boolean var3) {
      if (var1) {
         this.RF = new eo(var2, var3);
         this.xK = new CU();
      } else {
         this.RF = new CU();
         this.xK = new eo(var2, var3);
      }
   }

   public List q(File var1) throws IOException, JebException {
      File var2 = IO.createTempFile(null, ".jar");
      var2.deleteOnExit();

      try (JarOutputStream var3 = new JarOutputStream(new FileOutputStream(var2))) {
         String var4 = new beb(var1).lm() + ".class";
         IO.addFileToJar(var3, var1, var4);
      }

      return this.RF(var2);
   }

   public List RF(File var1) throws IOException, JebException {
      try {
         List var2;
         try {
            var2 = this.RF.q(var1);
         } catch (Exception var3) {
            var2 = this.xK.q(var1);
         }

         if (var2.isEmpty()) {
            throw new JebException("No output");
         } else {
            return var2;
         }
      } catch (JebException | IOException var4) {
         throw var4;
      }
   }

   public File xK(File var1) throws IOException, JebException {
      List var2 = this.q(var1);
      if (var2.size() != 1) {
         q.warn(S.L("Unexpected: Transpilation of a single classfile generated multiple files: %s"), var2);
      }

      File var3 = null;

      for (File var5 : var2) {
         if (var5.getName().equals("classes.dex")) {
            var3 = var5;
            break;
         }
      }

      if (var3 == null) {
         throw new IOException("Dex file not found");
      } else {
         return var3;
      }
   }

   public File Dw(File var1) throws IOException, JebException {
      List var2 = this.RF(var1);
      File var3 = IO.createTempFile(null, ".apk");
      var3.deleteOnExit();

      try (JarOutputStream var4 = new JarOutputStream(new FileOutputStream(var3))) {
         for (File var6 : var2) {
            String var7 = var6.getName();
            IO.addFileToJar(var4, var6, var7);
         }

         File var10 = IO.createTempFile();
         var10.deleteOnExit();
         IO.writeFile(var10, new byte[0]);
         IO.addFileToJar(var4, var10, "AndroidManifest.xml");
      }

      return var3;
   }
}
