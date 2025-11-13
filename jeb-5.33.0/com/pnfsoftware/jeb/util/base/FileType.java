package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.io.IO;
import java.io.IOException;

public enum FileType {
   UNKNOWN,
   TEXT,
   HTML,
   XML,
   IMAGE,
   AUDIO,
   VIDEO;

   public static FileType determine(String var0) {
      String var1 = null;
      String var2 = var0.toLowerCase();
      int var3 = var2.lastIndexOf(46);
      if (var3 >= 0) {
         var1 = var2.substring(var3 + 1);
      }

      FileType var4 = UNKNOWN;
      if (var1 != null) {
         if (var1.equals("xml")) {
            var4 = XML;
         } else if (var1.equals("txt") || var1.equals("csv")) {
            var4 = TEXT;
         } else if (var1.equals("html") || var1.equals("htm")) {
            var4 = HTML;
         } else if (var1.equals("png")
            || var1.equals("gif")
            || var1.equals("jpg")
            || var1.equals("jpeg")
            || var1.equals("bmp")
            || var1.equals("tif")
            || var1.equals("tiff")
            || var1.equals("ico")) {
            var4 = IMAGE;
         } else if (var1.equals("wav") || var1.equals("mp3")) {
            var4 = AUDIO;
         }
      }

      if (var4 == UNKNOWN) {
         int var5 = IO.getFirstIntLE(var0);
         if (var5 != 0 && (var5 & -2139062144) == 0) {
            try {
               byte[] var6 = IO.readFile(var0);
               if (isAscii(var6)) {
                  var4 = TEXT;
               }
            } catch (IOException var7) {
            }
         }
      }

      return var4;
   }

   private static boolean isAscii(byte[] var0) {
      for (byte var4 : var0) {
         if (var4 <= 0) {
            return false;
         }
      }

      return true;
   }
}
