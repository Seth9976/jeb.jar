package com.pnfsoftware.jeb.util.primitives;

import java.util.ArrayList;
import java.util.List;

public class IntegerList {
   public static String format(int[][] var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (int[] var6 : var0) {
         if (var2++ > 0) {
            var1.append(", ");
         }

         var1.append('{');
         var1.append(format(var6));
         var1.append('}');
      }

      return var1.toString();
   }

   public static String format(int[] var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (int var6 : var0) {
         if (var2++ > 0) {
            var1.append(", ");
         }

         var1.append(var6);
      }

      return var1.toString();
   }

   public static String format(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (int var4 : var0) {
         if (var2++ > 0) {
            var1.append(", ");
         }

         var1.append(var4);
      }

      return var1.toString();
   }

   public static boolean compareToArray(List var0, int[] var1) {
      if (var0.size() != var1.length) {
         return false;
      } else {
         int var2 = 0;

         for (int var4 : var0) {
            if (var4 != var1[var2]) {
               return false;
            }

            var2++;
         }

         return true;
      }
   }

   public static List buildListFromArray(int[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var5 : var0) {
         var1.add(var5);
      }

      return var1;
   }

   public static List buildList(int... var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (int var5 : var0) {
         var1.add(var5);
      }

      return var1;
   }

   public static List buildFromArray(int[] var0, List var1) {
      var1.clear();

      for (int var5 : var0) {
         var1.add(var5);
      }

      return var1;
   }
}
