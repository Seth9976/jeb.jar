package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PathProcessor {
   public static final PathProcessor ENV = new PathProcessor();
   private Map smap;

   public PathProcessor() {
   }

   public PathProcessor(Map var1) {
      this.smap = var1;
   }

   private String getenv(String var1) {
      return this.smap != null ? (String)this.smap.get(var1) : Env.get(var1);
   }

   public List decodeFlexiMultiPath(String var1) {
      ArrayList var2 = new ArrayList();

      for (String var6 : var1.split(";")) {
         String var7 = this.decodeFlexiPath(var6);
         if (var7 != null) {
            var2.add(var7);
         }
      }

      return var2;
   }

   public String decodeFlexiPath(String var1) {
      while (true) {
         int var2 = var1.indexOf(40);
         if (var2 < 0) {
            return this.substituteEnvironmentVariables(var1);
         }

         String var3 = null;
         int var4 = var1.indexOf(41, var2);
         if (var4 < 0) {
            return null;
         }

         String var5 = var1.substring(var2 + 1, var4);

         for (String var9 : var5.split("\\|")) {
            String var10 = this.substituteEnvironmentVariables(var9);
            if (var10 != null) {
               var3 = var10;
               break;
            }
         }

         if (var3 == null) {
            return null;
         }

         var1 = var1.substring(0, var2) + var3 + var1.substring(var4 + 1);
      }
   }

   public String decodeSinglePath(String var1) {
      if (var1 != null && var1.length() != 0) {
         if (var1.length() >= 2 && var1.charAt(0) == '~' && var1.charAt(1) == '/') {
            var1 = System.getProperty("user.home") + var1.substring(1);
         }

         return this.substituteEnvironmentVariables(var1);
      } else {
         return var1;
      }
   }

   public String substituteEnvironmentVariables(String var1) {
      int var2;
      while ((var2 = var1.indexOf("$")) >= 0) {
         if (var2 + 1 >= var1.length()) {
            return null;
         }

         boolean var3 = var1.charAt(var2 + 1) == '{';
         int var4 = -1;

         for (int var5 = var3 ? var2 + 2 : var2 + 1; var5 < var1.length(); var5++) {
            char var6 = var1.charAt(var5);
            if (var3) {
               if (var6 == '}') {
                  var4 = var5;
                  break;
               }
            } else if (var5 == var2 + 1) {
               if (!Character.isLowerCase(var6) && !Character.isUpperCase(var6) && var6 != '_') {
                  return null;
               }
            } else if (!Character.isLowerCase(var6) && !Character.isUpperCase(var6) && !Character.isDigit(var6) && var6 != '_') {
               var4 = var5;
               break;
            }
         }

         if (var3 && var4 == -1) {
            return null;
         }

         String var10 = var4 == -1 ? this.getenv(var1.substring(var2 + 1)) : this.getenv(var1.substring(var3 ? var2 + 2 : var2 + 1, var4));
         if (Strings.isBlank(var10)) {
            return null;
         }

         var1 = var1.substring(0, var2) + var10 + (var4 == -1 ? "" : var1.substring(var3 ? var4 + 1 : var4));
      }

      while ((var2 = var1.indexOf("%")) >= 0) {
         int var8 = var1.indexOf(37, var2 + 1);
         if (var8 < 0) {
            return null;
         }

         String var9 = this.getenv(var1.substring(var2 + 1, var8));
         if (Strings.isBlank(var9)) {
            return null;
         }

         var1 = var1.substring(0, var2) + var9 + var1.substring(var8 + 1);
      }

      return var1;
   }

   private static boolean testPathElement(String var0) {
      return !var0.startsWith("http://") && !var0.startsWith("https://") ? new File(var0).exists() : true;
   }
}
