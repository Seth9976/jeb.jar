package com.pnfsoftware.jeb.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ScriptMetadataParser {
   public static ScriptMetadata extract(File var0) throws IOException {
      return extract(var0, null);
   }

   public static ScriptMetadata extract(File var0, String var1) throws IOException {
      if (var1 == null) {
         if (var0.getName().endsWith(".py")) {
            var1 = "#";
         } else {
            var1 = "//";
         }
      }

      String var2 = var1 + "?";

      ScriptMetadata var6;
      try (BufferedReader var3 = new BufferedReader(new InputStreamReader(new FileInputStream(var0), Charset.defaultCharset()))) {
         ScriptMetadata var4 = new ScriptMetadata();

         String var5;
         while ((var5 = var3.readLine()) != null) {
            var5 = var5.trim();
            if (var5.length() != 0) {
               if (!var5.startsWith(var1)) {
                  break;
               }

               if (var5.startsWith(var2)) {
                  processEntry(var0, var5.substring(var2.length()).trim(), var4);
               }
            }
         }

         var6 = var4;
      }

      return var6;
   }

   private static boolean processEntry(File var0, String var1, ScriptMetadata var2) {
      String var3 = "type=";
      if (var1.startsWith(var3)) {
         String var13 = var1.substring(var3.length()).trim();
         if (!var13.isEmpty()) {
            var2.type = var13;
         }

         return true;
      } else {
         var3 = "description=";
         if (var1.startsWith(var3)) {
            String var12 = var1.substring(var3.length()).trim();
            if (!var12.isEmpty()) {
               var2.description = var12;
            }

            return true;
         } else {
            var3 = "author=";
            if (var1.startsWith(var3)) {
               String var11 = var1.substring(var3.length()).trim();
               if (!var11.isEmpty()) {
                  var2.author = var11;
               }

               return true;
            } else {
               var3 = "version=";
               if (var1.startsWith(var3)) {
                  String var10 = var1.substring(var3.length()).trim();
                  if (!var10.isEmpty()) {
                     Version var5 = Version.parseFromString(var10);
                     if (var5 == null) {
                        return false;
                     }

                     var2.version = var5;
                  }

                  return true;
               } else {
                  var3 = "shortcut=";
                  if (var1.startsWith(var3)) {
                     String var4 = var1.substring(var3.length()).trim();
                     if (!var4.isEmpty()) {
                        var2.shortcut = var4;
                     }

                     return true;
                  } else if (var1.startsWith("deprecated=") || var1.equals("deprecated")) {
                     var2.deprecated = true;
                     return true;
                  } else if (!var1.startsWith("nolist=") && !var1.equals("nolist")) {
                     return false;
                  } else {
                     var2.nolist = true;
                     return true;
                  }
               }
            }
         }
      }
   }
}
