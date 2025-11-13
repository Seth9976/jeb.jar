package com.pnfsoftware.jebglobal;

public class ul {
   public static void pC(byte var0) {
      if (var0 < 1 || var0 > 3) {
         throw new yb("Illegal TypeTag: " + var0);
      }
   }

   public static String pC(int var0) {
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
