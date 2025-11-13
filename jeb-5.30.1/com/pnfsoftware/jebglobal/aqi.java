package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class aqi {
   long q;
   INativeMethodItem RF;
   List xK = new ArrayList();
   TreeMap Dw = new TreeMap();
   aqf Uv;

   aqi(long var1, INativeMethodItem var3) {
      this.q = var1;
      this.RF = var3;
   }

   Collection q() {
      return this.Dw.values();
   }

   Collection q(Map var1) {
      ArrayList var2 = new ArrayList();

      for (long var4 : this.Dw.values()) {
         aqp var6 = (aqp)var1.get(var4);
         if (var6 == null) {
            throw new RuntimeException();
         }

         var2.add(var6);
      }

      return var2;
   }

   int RF() {
      return this.Dw.size();
   }

   public String q(ILabelManager var1) {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "Constructor@%s", aqo.q(var1, this.q));
      if (this.xK != null && !this.xK.isEmpty()) {
         var2.append("{Accesses=" + this.xK + "}");
      }

      if (this.Dw != null) {
         var2.append("{");
         int var3 = 0;

         for (int var5 : this.Dw.keySet()) {
            long var6 = (Long)this.Dw.get(var5);
            if (var3 > 0) {
               var2.append(",");
            }

            Strings.ff(var2, "+%Xh:%s", var5, aqo.q(var1, var6));
            var3++;
         }

         var2.append("}");
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return this.q(null);
   }
}
