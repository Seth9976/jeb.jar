package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

public class bgq {
   public static boolean pC(IJLSTypeAdapter var0, String var1, String var2) {
      if (!var2.equals("Ljava/lang/Object;") && !var1.equals(var2)) {
         ArrayDeque var3 = new ArrayDeque();
         var3.add(var1);
         HashSet var4 = new HashSet();
         var4.add("Ljava/lang/Object;");

         while (!var3.isEmpty()) {
            String var5 = (String)var3.remove();
            if (var4.add(var5)) {
               List var6 = var0.getParentTypes(var5);
               if (var6 == null) {
                  return false;
               }

               if (var6.contains(var2)) {
                  return true;
               }

               var3.addAll(var6);
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public static List pC(IJLSTypeAdapter var0, String var1) {
      throw new RuntimeException("TBI");
   }
}
