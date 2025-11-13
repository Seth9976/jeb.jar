package com.pnfsoftware.jeb.corei.debuggers.linux;

import java.util.ArrayList;
import java.util.List;

public class K {
   private List pC;

   public K(List var1) {
      this(var1, false);
   }

   public K(List var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var2) {
            long var3 = 0L;

            for (Ws var6 : var1) {
               if (var6.pC < var3 || var6.pC >= var6.A) {
                  throw new IllegalArgumentException("Invalid range: " + var6);
               }

               var3 = var6.A;
            }
         }

         this.pC = var1;
      }
   }

   private int A(long var1) {
      int var3 = 0;

      for (Ws var5 : this.pC) {
         if (var5.A > var1) {
            if (var1 >= var5.pC && var1 < var5.A) {
               return var3;
            }

            return -1;
         }

         var3++;
      }

      return -1;
   }

   public List pC() {
      return this.pC;
   }

   public Ws pC(long var1) {
      int var3 = this.A(var1);
      return var3 < 0 ? null : (Ws)this.pC.get(var3);
   }

   public long pC(long var1, long var3, int var5) {
      long var6 = var1;
      long var8 = var1 + var3;

      for (int var10 = this.A(var1); var10 >= 0 && var10 < this.pC.size(); var10++) {
         Ws var11 = (Ws)this.pC.get(var10);
         if (var6 < var11.pC || var6 >= var11.A || (var11.kS & var5) != var5) {
            break;
         }

         var6 = var11.A;
         if (var6 >= var8) {
            break;
         }
      }

      return Math.min(var6 - var1, var3);
   }

   public List A() {
      ArrayList var1 = new ArrayList();
      String var2 = null;

      for (Ws var4 : this.pC) {
         if (var4.ys != null && var4.ys.toLowerCase().endsWith(".so") && !var4.ys.equalsIgnoreCase(var2)) {
            var1.add(new Sv(var4.ys, var4.pC));
            var2 = var4.ys;
         }
      }

      return var1;
   }
}
