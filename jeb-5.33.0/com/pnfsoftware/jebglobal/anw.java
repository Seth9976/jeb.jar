package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class anw {
   long pC;
   INativeMethodItem A;
   List kS = new ArrayList();
   TreeMap wS = new TreeMap();
   ant UT;

   anw(long var1, INativeMethodItem var3) {
      this.pC = var1;
      this.A = var3;
   }

   Collection pC() {
      return this.wS.values();
   }

   Collection pC(Map var1) {
      ArrayList var2 = new ArrayList();

      for (long var4 : this.wS.values()) {
         aod var6 = (aod)var1.get(var4);
         if (var6 == null) {
            throw new RuntimeException();
         }

         var2.add(var6);
      }

      return var2;
   }

   int A() {
      return this.wS.size();
   }

   public String pC(ILabelManager var1) {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "Constructor@%s", aoc.pC(var1, this.pC));
      if (this.kS != null && !this.kS.isEmpty()) {
         var2.append("{Accesses=" + this.kS + "}");
      }

      if (this.wS != null) {
         var2.append("{");
         int var3 = 0;

         for (int var5 : this.wS.keySet()) {
            long var6 = (Long)this.wS.get(var5);
            if (var3 > 0) {
               var2.append(",");
            }

            Strings.ff(var2, "+%Xh:%s", var5, aoc.pC(var1, var6));
            var3++;
         }

         var2.append("}");
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return this.pC(null);
   }
}
