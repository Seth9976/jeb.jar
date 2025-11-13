package com.pnfsoftware.jeb.util.primitives;

public class Characters {
   public static boolean isAsciiCharOrCommonFormat(int var0) {
      return isAsciiChar(var0) || var0 == 13 || var0 == 10 || var0 == 9;
   }

   public static boolean isAsciiChar(int var0) {
      return var0 >= 32 && var0 <= 126;
   }
}
