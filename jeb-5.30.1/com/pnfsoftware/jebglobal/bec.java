package com.pnfsoftware.jebglobal;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class bec {
   List q = new ArrayList();

   public boolean q(File var1) {
      if (var1.isDirectory()) {
         this.q.add(var1);
      } else {
         if (!var1.isFile() || !var1.getName().endsWith(".jar")) {
            return false;
         }

         this.q.add(var1);
      }

      return true;
   }

   public int q(String var1) {
      int var2 = 0;

      for (String var6 : var1.trim().split(File.pathSeparator, -1)) {
         File var7 = new File(var6);
         if (this.q(var7)) {
            var2++;
         }
      }

      return var2;
   }

   public List q() {
      return this.q;
   }

   public beb RF(String var1) throws IOException {
      if (var1.endsWith(";")) {
         if (!var1.startsWith("L")) {
            throw new IllegalArgumentException();
         }

         var1 = var1.substring(1, var1.length() - 1);
      }

      String var2 = var1 + ".class";
      byte[] var3 = null;

      for (File var5 : this.q) {
         if (var5.isDirectory()) {
            File var12 = new File(var5, var2);
            if (var12.exists()) {
               var3 = com.pnfsoftware.jeb.util.io.IO.readFile(var12);
               break;
            }
         } else {
            try (JarFile var6 = new JarFile(var5, false)) {
               try {
                  JarEntry var7 = var6.getJarEntry(var2);
                  if (var7 != null) {
                     var3 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var6.getInputStream(var7));
                     break;
                  }
               } catch (Exception var10) {
                  throw new IOException("Cannot process jar entry: " + var2, var10);
               }
            }
         }
      }

      return var3 == null ? null : new beb(ByteBuffer.wrap(var3));
   }

   @Override
   public String toString() {
      return "ClassfileFinder:Locations=" + this.q;
   }
}
