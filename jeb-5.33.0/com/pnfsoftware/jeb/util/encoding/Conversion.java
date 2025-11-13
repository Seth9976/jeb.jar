package com.pnfsoftware.jeb.util.encoding;

import com.pnfsoftware.jeb.util.format.Strings;

public class Conversion {
   private static final int DEFAULT_BASE = 10;

   public static int toInt(Object var0) {
      return toInt(var0, 0);
   }

   public static int toInt(Object var0, int var1) {
      if (var0 instanceof Integer) {
         return (Integer)var0;
      } else {
         return var0 instanceof String ? stringToInt((String)var0, var1) : var1;
      }
   }

   public static long toLong(Object var0) {
      return toLong(var0, 0L);
   }

   public static long toLong(Object var0, long var1) {
      if (var0 instanceof Long) {
         return (Long)var0;
      } else {
         return var0 instanceof String ? stringToLong((String)var0, var1) : var1;
      }
   }

   private static Long stringToLongUnsafe(String var0, int var1) throws NumberFormatException {
      if (var0 != null && !var0.isEmpty()) {
         var0 = var0.trim();
         if (var0.endsWith("L")) {
            var0 = var0.substring(0, var0.length() - 1);
         }

         if (var0.length() > 1 && var0.charAt(0) == '-' && var0.charAt(1) != '-') {
            var0 = var0.substring(1).trim();
            Long var10 = stringToLongUnsafe(var0, var1);
            return var10 == null ? null : -1L * var10;
         } else {
            try {
               String var2 = var0;
               int var11 = var1;
               if (var0.startsWith("0x")) {
                  var2 = var0.substring(2);
                  var11 = 16;
               } else if (var0.endsWith("h")) {
                  var2 = var0.substring(0, var0.length() - 1);
                  var11 = 16;
               } else if (var1 == 10 && var0.length() >= 2 && var0.startsWith("0")) {
                  var2 = var0.substring(1);
                  var11 = 8;
               }

               if (var11 == 16 && var2.length() >= 10 && var2.charAt(var2.length() - 9) == '\'') {
                  var2 = var2.substring(0, var2.length() - 9) + var2.substring(var2.length() - 8);
               }

               return Long.parseLong(var2, var11);
            } catch (Exception var7) {
               try {
                  if (isNegativeHexLong(var0)) {
                     String var3 = getHexRaw(var0);
                     long var4 = Long.parseLong(var3.substring(0, 1), 16);
                     return var4 << 60 | Long.parseLong(var3.substring(1), 16);
                  } else {
                     return null;
                  }
               } catch (Exception var6) {
                  return null;
               }
            }
         }
      } else {
         return null;
      }
   }

   public static Long stringToLongUnsafe(String var0) {
      return stringToLongUnsafe(var0, 10);
   }

   public static long stringToLong(String var0, long var1, int var3) {
      Long var4 = stringToLongUnsafe(var0, var3);
      return var4 == null ? var1 : var4;
   }

   public static long stringToLong(String var0, long var1) {
      return stringToLong(var0, var1, 10);
   }

   public static long stringToLong(String var0) {
      return stringToLong(var0, 0L);
   }

   private static Integer stringToIntUnsafe(String var0, int var1) throws NumberFormatException {
      if (var0 != null && !var0.isEmpty()) {
         var0 = var0.trim();
         if (var0.length() > 1 && var0.charAt(0) == '-' && var0.charAt(1) != '-') {
            var0 = var0.substring(1).trim();
            Integer var2 = stringToIntUnsafe(var0, var1);
            return var2 == null ? null : -1 * var2;
         } else {
            try {
               if (var0.startsWith("0x")) {
                  return Integer.parseInt(var0.substring(2), 16);
               } else if (var0.endsWith("h")) {
                  return Integer.parseInt(var0.substring(0, var0.length() - 1), 16);
               } else {
                  return var1 == 10 && var0.length() >= 2 && var0.startsWith("0") ? Integer.parseInt(var0.substring(1), 8) : Integer.parseInt(var0, var1);
               }
            } catch (Exception var6) {
               try {
                  if (isNegativeHexInt(var0)) {
                     String var3 = getHexRaw(var0);
                     int var4 = Integer.parseInt(var3.substring(0, 1), 16);
                     return var4 << 28 | Integer.parseInt(var3.substring(1), 16);
                  } else {
                     return null;
                  }
               } catch (Exception var5) {
                  return null;
               }
            }
         }
      } else {
         return null;
      }
   }

   public static Integer stringToIntUnsafe(String var0) throws NumberFormatException {
      return stringToIntUnsafe(var0, 10);
   }

   public static int stringToInt(String var0, int var1, int var2) {
      Integer var3 = stringToIntUnsafe(var0, var2);
      return var3 == null ? var1 : var3;
   }

   public static int stringToInt(String var0, int var1) {
      return stringToInt(var0, var1, 10);
   }

   public static int stringToInt(String var0) {
      return stringToInt(var0, 0, 10);
   }

   private static boolean isNegativeHexLong(String var0) {
      return isNegativeHex(var0, 16);
   }

   private static boolean isNegativeHexInt(String var0) {
      return isNegativeHex(var0, 8);
   }

   private static boolean isNegativeHex(String var0, int var1) {
      String var2 = getHexRaw(var0);
      return var2 != null && var2.length() != var0.length() && !Strings.isBlank(var2) ? isNegative(var2, var1) : false;
   }

   private static boolean isNegative(String var0, int var1) {
      var0 = var0.toLowerCase();
      char var2 = var0.charAt(0);
      return var0.length() == var1 && (var2 == '8' || var2 == '9' || var2 >= 'a' && var2 <= 'f');
   }

   private static String getHexRaw(String var0) {
      var0 = var0.toLowerCase();
      if (var0.startsWith("0x")) {
         var0 = var0.substring(2);
      } else if (var0.endsWith("h")) {
         var0 = var0.substring(0, var0.length() - 1);
      }

      for (int var1 = 0; var1 < var0.length(); var1++) {
         char var2 = var0.charAt(var1);
         if (!Character.isDigit(var2) && (var2 < 'a' || var2 > 'f')) {
            return null;
         }
      }

      return var0;
   }

   public static int getHexPossibility(String var0) {
      if (var0.isEmpty()) {
         return 0;
      } else {
         int var1 = 0;

         for (int var2 = 0; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            if (var3 >= '0' && var3 <= '9') {
               var1 = Math.max(1, var1);
            } else if ((var3 < 'a' || var3 > 'f') && (var3 < 'A' || var3 > 'F')) {
               if ((var3 != '\'' || var0.length() - var2 - 1 != 8) && (var3 != 'l' && var3 != 'L' || var2 != var0.length() - 1)) {
                  return 0;
               }
            } else {
               var1 = Math.max(2, var1);
            }
         }

         return var1;
      }
   }
}
