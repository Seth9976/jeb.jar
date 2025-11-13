package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import java.util.Collection;

public class aoc {
   static String pC(ILabelManager var0, long var1) {
      return var0 == null ? Long.toUnsignedString(var1, 16).toUpperCase() + "h" : var0.getLabel(var1, 0L, AutoLabelPolicy.ON);
   }

   static String pC(ILabelManager var0, Collection var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (long var5 : var1) {
         if (var3 >= 1) {
            var2.append(",");
         }

         var2.append(var0 == null ? Long.toUnsignedString(var5, 16).toUpperCase() + "h" : var0.getLabel(var5, 0L, AutoLabelPolicy.ON));
         var3++;
      }

      return var2.toString();
   }
}
