package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bz {
   public int pC;
   public fp A;
   public static List kS = Arrays.asList(16777221, 16777222, 16777223, 16777224, 16777225, 16777220);

   public Bz(int var1, fp var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.pC, this.A);
   }

   public static String pC(int var0) {
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

   public static String A(int var0) {
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
