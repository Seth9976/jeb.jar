package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class baj {
   List pC = new ArrayList();

   public boolean pC(File var1) {
      if (var1.isDirectory()) {
         this.pC.add(var1);
      } else {
         if (!var1.isFile() || !var1.getName().endsWith(".jar")) {
            return false;
         }

         this.pC.add(var1);
      }

      return true;
   }

   public int pC(String var1) {
      int var2 = 0;

      for (String var6 : var1.trim().split(File.pathSeparator, -1)) {
         File var7 = new File(var6);
         if (this.pC(var7)) {
            var2++;
         }
      }

      return var2;
   }

   public List pC() {
      return this.pC;
   }

   public bai A(String var1) throws IOException {
      if (var1.endsWith(";")) {
         if (!var1.startsWith("L")) {
            throw new IllegalArgumentException();
         }

         var1 = var1.substring(1, var1.length() - 1);
      }

      String var2 = var1 + ".class";
      byte[] var3 = null;

      for (File var5 : this.pC) {
         if (var5.isDirectory()) {
            File var12 = new File(var5, var2);
            if (var12.exists()) {
               var3 = IO.readFile(var12);
               break;
            }
         } else {
            try (JarFile var6 = new JarFile(var5, false)) {
               try {
                  JarEntry var7 = var6.getJarEntry(var2);
                  if (var7 != null) {
                     var3 = IO.readInputStream(var6.getInputStream(var7));
                     break;
                  }
               } catch (Exception var10) {
                  throw new IOException("Cannot process jar entry: " + var2, var10);
               }
            }
         }
      }

      return var3 == null ? null : new bai(ByteBuffer.wrap(var3));
   }

   @Override
   public String toString() {
      return "ClassfileFinder:Locations=" + this.pC;
   }
}
