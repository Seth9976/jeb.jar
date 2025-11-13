package com.pnfsoftware.jeb.client.script;

public class ScriptUtil {
   public static String filenameToClassname(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         if (var0.endsWith(".py")) {
            var0 = var0.substring(0, var0.length() - 3);
         }

         String var1 = var0;
         if (var0.length() >= 1) {
            int var2;
            for (var2 = 0; var2 < var1.length(); var2++) {
               char var3 = var1.charAt(var2);
               if (var3 != '-' && var3 != '_' && !Character.isDigit(var3)) {
                  break;
               }
            }

            int var5;
            for (var5 = var1.length() - 1; var5 >= 0; var5--) {
               char var4 = var1.charAt(var5);
               if (var4 != '-' && var4 != '_') {
                  break;
               }
            }

            var1 = var1.substring(var2, var5 + 1);
         }

         return !var1.isEmpty() && !var1.equals(".") && !var1.equals("..") ? var1 : null;
      } else {
         return null;
      }
   }
}
