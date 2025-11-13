package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class bhr {
   public static List pC() {
      return new ArrayList(0);
   }

   public static List pC(IJavaElement... var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (IJavaElement var5 : var0) {
         if (var5 != null) {
            var1.add(var5);
         }
      }

      return var1;
   }

   public static List pC(Collection var0) {
      return pC(new ArrayList(), var0);
   }

   public static List pC(List var0, IJavaElement var1) {
      if (var1 != null) {
         var0.add(var1);
      }

      return var0;
   }

   public static List pC(List var0, Collection var1) {
      if (var1 != null) {
         for (IJavaElement var3 : var1) {
            if (var3 != null) {
               var0.add(var3);
            }
         }
      }

      return var0;
   }
}
