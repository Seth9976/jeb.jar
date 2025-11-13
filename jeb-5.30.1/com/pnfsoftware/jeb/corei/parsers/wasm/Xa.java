package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;

public class Xa {
   public static final int q = 1836278016;
   public static final int RF = 65536;
   public static final int xK = 1;
   public static final int Dw = 2;
   public static final int Uv = 3;
   public static final int oW = 4;
   public static final int gO = 5;
   public static final int nf = 6;
   public static final int gP = 7;
   public static final int za = 8;
   public static final int lm = 9;
   public static final int zz = 10;
   public static final int JY = 11;
   public static final int HF = 12;
   public static final int LK = 0;
   public static final int io = 1;
   public static final int qa = 2;
   public static final int Hk = 3;
   public static final int Me = 127;
   public static final int PV = 126;
   public static final int oQ = 125;
   public static final int xW = 124;
   public static final int KT = 112;
   public static final int Gf = 111;
   public static final int Ef = 64;
   public static final int cC = 96;

   public static String q(int var0) {
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

   public static String RF(int var0) {
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

   public static String xK(int var0) {
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

   public static boolean Dw(int var0) {
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

   public static boolean Uv(int var0) {
      switch (var0) {
         case 111:
         case 112:
            return true;
         default:
            return false;
      }
   }

   public static boolean oW(int var0) {
      return Dw(var0) || Uv(var0);
   }

   public static int gO(int var0) {
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

   public static String nf(int var0) {
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

   public static int gP(int var0) {
      if (var0 == 32) {
         return 125;
      } else if (var0 == 64) {
         return 124;
      } else {
         throw new RuntimeException();
      }
   }

   public static int za(int var0) {
      if (var0 == 32) {
         return 127;
      } else if (var0 == 64) {
         return 126;
      } else {
         throw new RuntimeException();
      }
   }
}
