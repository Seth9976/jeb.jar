package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class aku {
   List q = new ArrayList();

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (aku.eo var4 : this.q) {
         Strings.ff(var1, "- %d: %s\n", var2, var4);
         var2++;
      }

      return var1.toString();
   }

   public String q() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (aku.eo var4 : this.q) {
         if (var2 >= 1) {
            var1.append(" / ");
         }

         var1.append(var4.RF);
         var2++;
      }

      return var1.toString();
   }

   static class eo {
      akv q;
      ala RF;

      eo(akv var1, ala var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("Sub-graph: %s | Intervals: %s", this.q, this.RF);
      }
   }
}
