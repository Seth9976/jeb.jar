package com.pnfsoftware.jeb.corei.parsers.classfile;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.bai;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarOutputStream;

public class bO {
   private static final ILogger pC = GlobalLog.getLogger(bO.class);
   private K A;
   private K kS;

   public bO() {
      this(true, 29, true);
   }

   public bO(boolean var1, int var2, boolean var3) {
      if (var1) {
         this.A = new Av(var2, var3);
         this.kS = new Sv();
      } else {
         this.A = new Sv();
         this.kS = new Av(var2, var3);
      }
   }

   public List pC(File var1) throws IOException, JebException {
      File var2 = IO.createTempFile(null, ".jar");
      var2.deleteOnExit();

      try (JarOutputStream var3 = new JarOutputStream(new FileOutputStream(var2))) {
         String var4 = new bai(var1).ld() + ".class";
         IO.addFileToJar(var3, var1, var4);
      }

      return this.A(var2);
   }

   public List A(File var1) throws IOException, JebException {
      try {
         List var2;
         try {
            var2 = this.A.pC(var1);
         } catch (Exception var3) {
            var2 = this.kS.pC(var1);
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

   public File kS(File var1) throws IOException, JebException {
      List var2 = this.pC(var1);
      if (var2.size() != 1) {
         pC.warn(S.L("Unexpected: Transpilation of a single classfile generated multiple files: %s"), var2);
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

   public File wS(File var1) throws IOException, JebException {
      List var2 = this.A(var1);
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
