package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class avr {
   public static String pC(String var0) {
      int var1 = var0.lastIndexOf("::");
      if (var1 < 0) {
         return var0;
      } else {
         int var2 = var1 + 3;
         if (var0.charAt(var1 + 2) == 'h' && var0.length() - var2 >= 16 && (var0.length() - var2 == 16 || var0.charAt(var2 + 16) == ' ')) {
            if (var0.length() - var2 == 16) {
               if (!Strings.isHexNumber(var0.substring(var2))) {
                  return var0;
               }

               var0 = var0.substring(0, var1);
            } else {
               if (var0.charAt(var2 + 16) != ' ' || !Strings.isHexNumber(var0.substring(var2, var2 + 16))) {
                  return var0;
               }

               var0 = var0.substring(0, var1) + var0.substring(var2 + 16);
            }

            return A(var0);
         } else {
            return var0;
         }
      }
   }

   public static String A(String var0) {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         if (var0.charAt(var2) == '.' && var2 + 1 < var0.length() && var0.charAt(var2 + 1) == '.') {
            var1.append("::");
            var2++;
         } else if (var0.charAt(var2) != '$') {
            var1.append(var3);
         } else if (var2 + 1 < var0.length()) {
            int var4 = var0.indexOf(36, var2 + 1);
            if (var4 < 0) {
               var1.append(var3);
            } else {
               String var5 = var0.substring(var2 + 1, var4);
               var2 = var4;
               switch (var5) {
                  case "SP":
                     var1.append('@');
                     break;
                  case "BP":
                     var1.append('*');
                     break;
                  case "RF":
                     var1.append('&');
                     break;
                  case "LT":
                     var1.append('<');
                     break;
                  case "GT":
                     var1.append('>');
                     break;
                  case "LP":
                     var1.append('(');
                     break;
                  case "RP":
                     var1.append('(');
                     break;
                  case "C":
                     var1.append(',');
                     break;
                  default:
                     if (var5.startsWith("u")) {
                        var5 = var5.substring(1);
                        if (Strings.isHexNumber(var5)) {
                           var1.append((char)Integer.parseInt(var5, 16));
                        } else {
                           var1.append('$').append(var5).append('$');
                        }
                     } else {
                        var1.append('$').append(var5).append('$');
                     }
               }
            }
         } else {
            var1.append(var3);
         }
      }

      return var1.toString();
   }
}
