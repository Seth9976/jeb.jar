package com.pnfsoftware.jeb.util.primitives;

public class Shorts {
   public static String toUnsignedString(short var0) {
      return Longs.toUnsignedString(var0 & 65535L);
   }
}
