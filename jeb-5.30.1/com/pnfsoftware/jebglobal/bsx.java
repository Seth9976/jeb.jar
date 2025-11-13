package com.pnfsoftware.jebglobal;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class bsx {
   public static Collection q(bsv var0, List var1, int var2) {
      TreeSet var3 = new TreeSet();
      int var4 = 1;

      for (Set var6 : var1) {
         if (var6.contains(var2)) {
            var3.add(var4);
         }

         var4++;
      }

      return var3;
   }

   public static Collection RF(bsv var0, List var1, int var2) {
      TreeSet var3 = new TreeSet();
      int var4 = 1;

      for (Set var6 : var1) {
         if (var6.contains(var2)) {
            var3.add(var4);
         }

         var4++;
      }

      return var3;
   }

   public static Collection xK(bsv var0, List var1, int var2) {
      TreeSet var3 = new TreeSet();
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var2);
      HashSet var5 = new HashSet();
      var5.add(var2);

      while (!var4.isEmpty()) {
         int var6 = (Integer)var4.remove();

         for (int var8 : var0.oW(var6)) {
            if (var5.add(var8)) {
               if (((Set)var1.get(var8 - 1)).contains(var2)) {
                  var4.add(var8);
               } else {
                  var3.add(var8);
               }
            }
         }
      }

      return var3;
   }

   public static Collection Dw(bsv var0, List var1, int var2) {
      throw new RuntimeException("TBI");
   }
}
