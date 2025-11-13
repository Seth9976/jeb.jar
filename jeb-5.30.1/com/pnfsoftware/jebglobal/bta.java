package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

public class bta {
   List q = new ArrayList();

   public int q() {
      return this.q.size();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (List var4 : this.q) {
         if (var2 >= 1) {
            var1.append(" ");
         }

         var1.append(var2).append("=").append(var4);
         var2++;
      }

      return var1.toString();
   }
}
