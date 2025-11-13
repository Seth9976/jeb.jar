package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class oI {
   public int q;
   public HK RF;
   public static final int xK = 16777216;
   public static final int Dw = 16777217;
   public static final int Uv = 16777218;
   public static final int oW = 16777219;
   public static final int gO = 16777220;
   public static final int nf = 16777221;
   public static final int gP = 16777222;
   public static final int za = 16777223;
   public static final int lm = 16777224;
   public static final int zz = 16777225;
   public static List JY = Arrays.asList(16777221, 16777222, 16777223, 16777224, 16777225, 16777220);
   public static final int HF = 65535;
   public static final int LK = 1;
   public static final int io = 2;
   public static final int qa = 4;
   public static final int Hk = 8;
   public static final int Me = 16;
   public static final int PV = 32;
   public static final int oQ = 64;
   public static final int xW = 128;
   public static final int KT = 65536;
   public static final int Gf = 131072;
   public static final int Ef = 0;
   public static final int cC = 1;

   public oI(int var1, HK var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.q, this.RF);
   }

   public static String q(int var0) {
      int var1 = var0 & 65535;
      ArrayList var2 = new ArrayList();
      if (var1 == 65535) {
         var2.add("any");
      } else {
         if ((var1 & 1) != 0) {
            var2.add("reference");
         }

         if ((var1 & 2) != 0) {
            var2.add("string");
         }

         if ((var1 & 4) != 0) {
            var2.add("integer");
         }

         if ((var1 & 8) != 0) {
            var2.add("boolean");
         }

         if ((var1 & 16) != 0) {
            var2.add("color");
         }

         if ((var1 & 32) != 0) {
            var2.add("float");
         }

         if ((var1 & 64) != 0) {
            var2.add("dimension");
         }

         if ((var1 & 128) != 0) {
            var2.add("fraction");
         }
      }

      int var3 = var0 & -65536;
      if ((var3 & 65536) != 0) {
         var2.add("enum");
      }

      if ((var3 & 131072) != 0) {
         var2.add("flags");
      }

      StringBuilder var4 = new StringBuilder();
      int var5 = 0;

      for (String var7 : var2) {
         if (var5 >= 1) {
            var4.append("|");
         }

         var4.append(var7);
         var5++;
      }

      return var4.toString();
   }

   public static String RF(int var0) {
      switch (var0) {
         case 16777216:
            return "type";
         case 16777217:
            return "min";
         case 16777218:
            return "max";
         case 16777219:
            return "l10n";
         case 16777220:
            return "other";
         case 16777221:
            return "zero";
         case 16777222:
            return "one";
         case 16777223:
            return "two";
         case 16777224:
            return "few";
         case 16777225:
            return "many";
         default:
            return "attr_unknown_" + var0;
      }
   }
}
