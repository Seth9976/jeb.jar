package com.pnfsoftware.jeb.util.base;

import java.util.ArrayList;
import java.util.List;

public class Tuples {
   public static List generateIntegerTuples(int var0) {
      if (var0 <= 0) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(var0);
         ArrayList var2 = new ArrayList(var0);

         for (int var3 = 0; var3 < var0; var3++) {
            var2.add(var3);
         }

         ArrayList var4 = new ArrayList();
         generateTuplesRecurse(var2, var1, var4);
         return var4;
      }
   }

   private static void generateTuplesRecurse(ArrayList var0, ArrayList var1, List var2) {
      if (var0.isEmpty()) {
         var2.add((List)var1.clone());
      } else {
         for (int var3 = 0; var3 < var0.size(); var3++) {
            int var4 = (Integer)var0.get(var3);
            ArrayList var5 = new ArrayList(var0);
            var5.remove(var3);
            var1.add(var4);
            generateTuplesRecurse(var5, var1, var2);
            var1.remove(var1.size() - 1);
         }
      }
   }
}
