package com.pnfsoftware.jebglobal;

import java.util.ArrayList;
import java.util.List;

public class aiy {
   List pC = new ArrayList();

   aiy() {
   }

   public int pC() {
      return this.pC.size();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (List var4 : this.pC) {
         if (var2 >= 1) {
            var1.append(" ");
         }

         var1.append(var2).append("=").append(var4);
         var2++;
      }

      return var1.toString();
   }
}
