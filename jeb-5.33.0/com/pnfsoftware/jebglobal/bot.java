package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class bot {
   List pC = new ArrayList();

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (bot.Av var4 : this.pC) {
         Strings.ff(var1, "- %d: %s\n", var2, var4);
         var2++;
      }

      return var1.toString();
   }

   static class Av {
      bou pC;
      boy A;

      Av(bou var1, boy var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("Sub-graph: %s | Intervals: %s", this.pC, this.A);
      }
   }
}
