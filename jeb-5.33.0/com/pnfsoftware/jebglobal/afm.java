package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class afm {
   public static List pC(ICElement... var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for (ICElement var5 : var0) {
         if (var5 != null) {
            var1.add(var5);
         }
      }

      return var1;
   }

   public static List pC(Collection var0) {
      return pC(new ArrayList(), var0);
   }

   public static List pC(List var0, ICElement var1) {
      if (var1 != null) {
         var0.add(var1);
      }

      return var0;
   }

   public static List pC(List var0, Collection var1) {
      if (var1 != null) {
         for (ICElement var3 : var1) {
            if (var3 != null) {
               var0.add(var3);
            }
         }
      }

      return var0;
   }
}
