package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.LinkedHashMap;
import java.util.Map;

public class FlagsFormatter {
   private String sep;
   private Map map;

   private FlagsFormatter(String var1, Map var2) {
      this.sep = var1;
      this.map = var2;
   }

   public String format(int var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var4 : this.map.keySet()) {
         if ((var1 & var4) == var4) {
            if (!var2.isEmpty()) {
               var2.append(this.sep);
            }

            var2.append((String)this.map.get(var4));
            var1 &= ~var4;
            if (var1 == 0) {
               break;
            }
         }
      }

      if (var1 != 0) {
         if (!var2.isEmpty()) {
            var2.append(this.sep);
         }

         Strings.ff(var2, "0x%X", var1);
      }

      return var2.toString();
   }

   public static class Builder {
      private String sep = "|";
      private Map map = new LinkedHashMap();

      public static FlagsFormatter.Builder create() {
         return new FlagsFormatter.Builder();
      }

      public FlagsFormatter.Builder setSeparator(String var1) {
         this.sep = var1;
         return this;
      }

      public FlagsFormatter.Builder add(int var1, String var2) {
         Assert.a(var1 != 0);
         Assert.a(!Strings.isBlank(var2));
         this.map.put(var1, var2);
         return this;
      }

      public FlagsFormatter build() {
         return new FlagsFormatter(this.sep, this.map);
      }
   }
}
