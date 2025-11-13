package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class blo {
   public static List q() {
      return new ArrayList(0);
   }

   public static List q(IJavaElement... var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (IJavaElement var5 : var0) {
         if (var5 != null) {
            var1.add(var5);
         }
      }

      return var1;
   }

   public static List q(Collection var0) {
      return q(new ArrayList(), var0);
   }

   public static List q(List var0, IJavaElement var1) {
      if (var1 != null) {
         var0.add(var1);
      }

      return var0;
   }

   public static List q(List var0, Collection var1) {
      if (var1 != null) {
         for (IJavaElement var3 : var1) {
            if (var3 != null) {
               var0.add(var3);
            }
         }
      }

      return var0;
   }

   public static List q(List var0, boolean var1) {
      if (!var1) {
         Iterator var2 = var0.iterator();

         while (var2.hasNext()) {
            IJavaElement var3 = (IJavaElement)var2.next();
            if (var3 instanceof IJavaClass || var3 instanceof IJavaField || var3 instanceof IJavaMethod) {
               var2.remove();
            }
         }
      }

      return var0;
   }
}
