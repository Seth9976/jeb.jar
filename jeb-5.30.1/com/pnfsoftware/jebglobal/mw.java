package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class mw {
   public static final int q = 0;
   public static final int RF = 1;
   public static final int xK = 2;
   public static final int Dw = 3;
   public static final int Uv = 256;
   public static final int oW = 256;
   public static final int gO = 257;
   public static final int nf = 258;
   public static final int gP = 259;
   public static final int za = 260;
   public static final int lm = 383;
   public static final int zz = 384;
   public static final int JY = 512;
   public static final int HF = 513;
   public static final int LK = 514;
   public static final int io = 515;
   public static final int qa = 516;
   public static final int Hk = 517;
   public static final int Me = 518;
   public static final int PV = 255;
   public static final int oQ = 255;
   public static final int xW = 0;
   public static final int KT = 1;
   public static final int Gf = 2;
   public static final int Ef = 3;
   public static final int cC = 4;
   public static final int sH = 5;
   public static final int CE = 6;
   public static final int wF = 7;
   public static final int If = 8;
   public static final int Dz = 16;
   public static final int mI = 16;
   public static final int jq = 17;
   public static final int ui = 18;
   public static final int TX = 28;
   public static final int Rr = 28;
   public static final int EB = 29;
   public static final int Xo = 30;
   public static final int Bu = 31;
   public static final int IN = 31;
   public static final int rL = 31;
   public static final int eJ = 0;
   public static final int YN = 15;
   public static final int Rv = 0;
   public static final int zx = 1;
   public static final int ZT = 2;
   public static final int Ri = 3;
   public static final int GY = 4;
   public static final int Wx = 5;
   public static final int AB = 0;
   public static final int CY = 1;
   public static final int WI = 4;
   public static final int Tq = 3;
   public static final int Yp = 0;
   public static final int Gu = 1;
   public static final int nY = 2;
   public static final int lF = 3;
   public static final int nq = 8;
   public static final int NX = 16777215;
   public static final int br = 0;
   public static final int tW = 1;

   public static boolean q(int var0) {
      switch (var0) {
         case 3:
         case 256:
         case 257:
         case 258:
         case 259:
         case 260:
         case 384:
            return true;
         default:
            return false;
      }
   }

   public static boolean RF(int var0) {
      switch (var0) {
         case 2:
         case 512:
         case 513:
         case 514:
         case 515:
         case 516:
         case 517:
         case 518:
            return true;
         default:
            return false;
      }
   }

   public static String xK(int var0) {
      switch (var0) {
         case 0:
            return "NULL_TYPE";
         case 1:
            return "STRING_POOL_TYPE";
         case 2:
            return "TABLE_TYPE";
         case 3:
            return "XML_TYPE";
         case 256:
            return "XML_START_NAMESPACE_TYPE";
         case 257:
            return "XML_END_NAMESPACE_TYPE";
         case 258:
            return "XML_START_ELEMENT_TYPE";
         case 259:
            return "XML_END_ELEMENT_TYPE";
         case 260:
            return "XML_CDATA_TYPE";
         case 384:
            return "XML_RESOURCE_MAP_TYPE";
         case 512:
            return "TABLE_PACKAGE_TYPE";
         case 513:
            return "TABLE_TYPE_TYPE";
         case 514:
            return "TABLE_TYPE_SPEC_TYPE";
         case 515:
            return "TABLE_LIBRARY_TYPE";
         case 516:
            return "TABLE_OVERLAYABLE_TYPE";
         case 517:
            return "TABLE_OVERLAYABLE_POLICY_TYPE";
         case 518:
            return "TABLE_STAGED_ALIAS_TYPE";
         default:
            return Strings.ff("%Xh(UNKNOWN)", var0);
      }
   }

   public static final boolean Dw(int var0) {
      return var0 != 0;
   }

   public static final boolean Uv(int var0) {
      return (var0 & -65536) != 0;
   }

   public static final int q(int var0, int var1, int var2) {
      return var0 + 1 << 24 | (var1 + 1 & 0xFF) << 16 | var2 & 65535;
   }

   public static final int oW(int var0) {
      return (var0 >> 24) - 1;
   }

   public static final int gO(int var0) {
      return (var0 >> 16 & 0xFF) - 1;
   }

   public static final int nf(int var0) {
      return var0 & 65535;
   }

   public static final boolean gP(int var0) {
      return (var0 & -65536) != 0 && (var0 & 0xFF0000) == 0;
   }

   public static final int za(int var0) {
      return 16777216 | var0 & 65535;
   }

   public static final int lm(int var0) {
      return 33554432 | var0 & 65535;
   }

   public static final String[] q(String var0) {
      String var1 = null;
      String var2 = null;
      int var4 = var0.indexOf(58);
      if (var4 >= 0) {
         var1 = var0.substring(0, var4);
         var0 = var0.substring(var4 + 1);
      }

      var4 = var0.indexOf(47);
      if (var4 >= 0) {
         var2 = var0.substring(0, var4);
         var0 = var0.substring(var4 + 1);
      }

      return new String[]{var1, var2, var0};
   }
}
