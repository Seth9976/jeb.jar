package com.pnfsoftware.jeb.util.format;

import java.nio.charset.Charset;

public class Charsets {
   private static final String _UTF_8 = "UTF-8";
   public static final Charset UTF_8 = Charset.forName("UTF-8");

   public static Charset findCharset(String var0) {
      try {
         return Charset.forName(var0);
      } catch (Exception var1) {
         if (var0.equalsIgnoreCase("utf8")) {
            return Charset.forName("UTF-8");
         } else if (var0.equalsIgnoreCase("utf16")) {
            return Charset.forName("UTF-16");
         } else if (var0.equalsIgnoreCase("ascii")) {
            return Charset.forName("US-ASCII");
         } else {
            return !var0.equalsIgnoreCase("8859") && !var0.equalsIgnoreCase("latin") ? null : Charset.forName("ISO-8859-1");
         }
      }
   }
}
