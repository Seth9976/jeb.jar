package com.pnfsoftware.jeb.util.encoding.zip;

import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zips {
   public static void zipFile(File var0, File var1) throws IOException {
      if (var0 != null && var1 != null) {
         try (ZipOutputStream var2 = new ZipOutputStream(new FileOutputStream(var0))) {
            ZipEntry var3 = new ZipEntry(var1.getName());
            var2.putNextEntry(var3);
            var2.write(IO.readFile(var1));
            var2.closeEntry();
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static void zipBytes(File var0, String var1, byte[] var2) throws IOException {
      zipBytes(var0, var1, var2, 0, var2.length);
   }

   public static void zipBytes(File var0, String var1, byte[] var2, int var3, int var4) throws IOException {
      if (var0 != null && var2 != null) {
         try (ZipOutputStream var5 = new ZipOutputStream(new FileOutputStream(var0))) {
            ZipEntry var6 = new ZipEntry(var1);
            var5.putNextEntry(var6);
            var5.write(var2, var3, var4);
            var5.closeEntry();
         }
      } else {
         throw new IllegalArgumentException();
      }
   }
}
