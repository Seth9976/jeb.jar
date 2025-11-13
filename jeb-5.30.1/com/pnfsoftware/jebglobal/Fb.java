package com.pnfsoftware.jebglobal;

public class Fb {
   public static final byte q = -3;
   public static final byte RF = -2;
   public static final byte xK = -1;
   public static final byte Dw = 1;
   public static final byte Uv = 2;
   public static final byte oW = 3;

   public static void q(byte var0) {
      if (var0 < 1 || var0 > 3) {
         throw new si("Illegal TypeTag: " + var0);
      }
   }

   public static String q(int var0) {
      switch (var0) {
         case -3:
            return "_METHOD";
         case -2:
            return "_FIELD";
         case -1:
            return "_TYPE";
         case 0:
         default:
            throw new RuntimeException("Unknown type tag: " + var0);
         case 1:
            return "CLASS";
         case 2:
            return "INTERFACE";
         case 3:
            return "ARRAY";
      }
   }
}
