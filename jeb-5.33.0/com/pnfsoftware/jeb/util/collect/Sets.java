package com.pnfsoftware.jeb.util.collect;

import java.util.HashSet;
import java.util.Set;

public class Sets {
   @SafeVarargs
   public static HashSet newHashSet(Object... var0) {
      HashSet var1 = new HashSet(var0.length);

      for (Object var5 : var0) {
         var1.add(var5);
      }

      return var1;
   }

   @SafeVarargs
   public static HashSet createNonNulls(Object... var0) {
      HashSet var1 = new HashSet(var0.length);

      for (Object var5 : var0) {
         if (var5 != null) {
            var1.add(var5);
         }
      }

      return var1;
   }

   public static Object getEntryByIndex(Set var0, int var1) {
      if (var1 >= 0) {
         int var2 = 0;

         for (Object var4 : var0) {
            if (var2 == var1) {
               return var4;
            }

            var2++;
         }
      }

      throw new IndexOutOfBoundsException("Illegal index: " + var1);
   }
}
