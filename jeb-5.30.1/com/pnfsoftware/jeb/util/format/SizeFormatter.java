package com.pnfsoftware.jeb.util.format;

public class SizeFormatter {
   public static String formatByteSize(long var0) {
      if (var0 < 1024L) {
         return Long.toString(var0);
      } else if (var0 < 1048576L) {
         return Strings.ff("%.1fK", var0 / 1024.0);
      } else if (var0 < 1073741824L) {
         return Strings.ff("%.1fM", var0 / 1048576.0);
      } else {
         return var0 < 1099511627776L ? Strings.ff("%.1fG", var0 / 1.0737418E9F) : Strings.ff("%.1fT", var0 / 1.0995116E12F);
      }
   }
}
