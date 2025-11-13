package com.pnfsoftware.jeb.util.format;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Locale;

public class CharSequences {
   private static StringBuilder f(String var0, Object... var1) {
      return f(new StringBuilder(), var0, var1);
   }

   private static StringBuilder f(StringBuilder var0, String var1, Object... var2) {
      try (java.util.Formatter var3 = new java.util.Formatter(var0, Locale.US)) {
         var3.format(var1, var2);
      }

      return var0;
   }

   private static byte[] toByteArray(char[] var0, Charset var1) {
      if (var1 == null) {
         var1 = Charset.defaultCharset();
      }

      CharBuffer var2 = CharBuffer.wrap(var0);
      ByteBuffer var3 = var1.encode(var2);
      byte[] var4 = new byte[var3.remaining()];
      var3.get(var4);
      return var4;
   }

   public static byte[] toByteArray(char[] var0) {
      return toByteArray(var0, Charset.defaultCharset());
   }

   public static boolean isBlank(CharSequence var0) {
      if (var0 != null) {
         for (int var1 = 0; var1 < var0.length(); var1++) {
            if (!Character.isWhitespace(var0.charAt(var1))) {
               return false;
            }
         }
      }

      return true;
   }

   public static int indexOf(CharSequence var0, char var1) {
      return indexOf(var0, var1, 0);
   }

   public static int indexOf(CharSequence var0, char var1, int var2) {
      for (int var3 = var2; var3 < var0.length(); var3++) {
         if (var0.charAt(var3) == var1) {
            return var3;
         }
      }

      return -1;
   }

   public static int indexOf2(CharSequence var0, char var1, char var2) {
      for (int var3 = 0; var3 < var0.length(); var3++) {
         char var4 = var0.charAt(var3);
         if (var4 == var1 || var4 == var2) {
            return var3;
         }
      }

      return -1;
   }

   public static int indexOfn(CharSequence var0, char... var1) {
      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);

         for (char var7 : var1) {
            if (var3 == var7) {
               return var2;
            }
         }
      }

      return -1;
   }

   public static boolean startsWith(CharSequence var0, String var1) {
      return startsWith(var0, var1, false);
   }

   public static boolean startsWith(CharSequence var0, String var1, boolean var2) {
      if (var0 != null && var0.length() >= var1.length()) {
         int var3 = 0;

         while (var0.charAt(var3) <= ' ' && var3 < var0.length()) {
            var3++;
         }

         while (var3 < var1.length()) {
            if (var0.charAt(var3) != var1.charAt(var3)) {
               return false;
            }

            var3++;
         }

         return true;
      } else {
         return false;
      }
   }
}
