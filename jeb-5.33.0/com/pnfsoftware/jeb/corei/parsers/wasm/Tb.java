package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;

public class Tb {
   public static String pC(int var0) {
      switch (var0) {
         case 1:
            return "TYPE";
         case 2:
            return "IMPORT";
         case 3:
            return "FUNCTION";
         case 4:
            return "TABLE";
         case 5:
            return "MEMORY";
         case 6:
            return "GLOBAL";
         case 7:
            return "EXPORT";
         case 8:
            return "START";
         case 9:
            return "ELEMENT";
         case 10:
            return "CODE";
         case 11:
            return "DATA";
         default:
            return "SECTION_ID_" + var0;
      }
   }

   public static String A(int var0) {
      switch (var0) {
         case 0:
            return "function";
         case 1:
            return "table";
         case 2:
            return "memory";
         case 3:
            return "global";
         default:
            throw new RuntimeException("unknown kind: " + var0);
      }
   }

   public static String kS(int var0) {
      switch (var0) {
         case 64:
            return "empty_block_type";
         case 111:
            return "externref";
         case 112:
            return "funcref";
         case 124:
            return "f64";
         case 125:
            return "f32";
         case 126:
            return "i64";
         case 127:
            return "i32";
         default:
            throw new RuntimeException(Strings.ff("unknown type: 0x%X", var0));
      }
   }

   public static boolean wS(int var0) {
      switch (var0) {
         case 124:
         case 125:
         case 126:
         case 127:
            return true;
         default:
            return false;
      }
   }

   public static boolean UT(int var0) {
      switch (var0) {
         case 111:
         case 112:
            return true;
         default:
            return false;
      }
   }

   public static boolean E(int var0) {
      return wS(var0) || UT(var0);
   }

   public static int sY(int var0) {
      switch (var0) {
         case 124:
            return 8;
         case 125:
            return 4;
         case 126:
            return 8;
         case 127:
            return 4;
         default:
            throw new RuntimeException(Strings.ff("Size unknown for type: %Xh", var0));
      }
   }

   public static String ys(int var0) {
      switch (var0) {
         case 124:
            return "double";
         case 125:
            return "float";
         case 126:
            return "__int64";
         case 127:
            return "__int32";
         default:
            return null;
      }
   }

   public static int ld(int var0) {
      if (var0 == 32) {
         return 125;
      } else if (var0 == 64) {
         return 124;
      } else {
         throw new RuntimeException();
      }
   }
}
