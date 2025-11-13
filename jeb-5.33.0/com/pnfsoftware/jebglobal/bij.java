package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class bij implements bow {
   int pC;
   int A;
   List kS = new ArrayList();
   boolean wS;

   public bij(int var1, int var2) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else if (var2 < var1) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   @Override
   public int pC() {
      return this.A;
   }

   public bho A() {
      return this.kS.isEmpty() ? null : (bho)this.kS.get(this.kS.size() - 1);
   }

   public int kS() {
      if (this.kS.isEmpty()) {
         return this.A;
      } else {
         bho var1 = (bho)this.kS.get(this.kS.size() - 1);
         return var1.A == -1 ? var1.pC : var1.A;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "try=(%d..%d) catches=[", this.pC, this.A);
      int var2 = 0;

      for (bho var4 : this.kS) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.append(var4.toString());
      }

      var1.append("]");
      return var1.toString();
   }
}
