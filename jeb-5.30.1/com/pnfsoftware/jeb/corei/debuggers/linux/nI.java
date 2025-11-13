package com.pnfsoftware.jeb.corei.debuggers.linux;

import java.util.ArrayList;
import java.util.List;

public class nI {
   private List q;

   public nI(List var1) {
      this(var1, false);
   }

   public nI(List var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var2) {
            long var3 = 0L;

            for (ej var6 : var1) {
               if (var6.Uv < var3 || var6.Uv >= var6.oW) {
                  throw new IllegalArgumentException("Invalid range: " + var6);
               }

               var3 = var6.oW;
            }
         }

         this.q = var1;
      }
   }

   private int RF(long var1) {
      int var3 = 0;

      for (ej var5 : this.q) {
         if (var5.oW > var1) {
            if (var1 >= var5.Uv && var1 < var5.oW) {
               return var3;
            }

            return -1;
         }

         var3++;
      }

      return -1;
   }

   public List q() {
      return this.q;
   }

   public ej q(long var1) {
      int var3 = this.RF(var1);
      return var3 < 0 ? null : (ej)this.q.get(var3);
   }

   public long q(long var1, long var3, int var5) {
      long var6 = var1;
      long var8 = var1 + var3;

      for (int var10 = this.RF(var1); var10 >= 0 && var10 < this.q.size(); var10++) {
         ej var11 = (ej)this.q.get(var10);
         if (var6 < var11.Uv || var6 >= var11.oW || (var11.gO & var5) != var5) {
            break;
         }

         var6 = var11.oW;
         if (var6 >= var8) {
            break;
         }
      }

      return Math.min(var6 - var1, var3);
   }

   public List RF() {
      ArrayList var1 = new ArrayList();
      String var2 = null;

      for (ej var4 : this.q) {
         if (var4.zz != null && var4.zz.toLowerCase().endsWith(".so") && !var4.zz.equalsIgnoreCase(var2)) {
            var1.add(new CU(var4.zz, var4.Uv));
            var2 = var4.zz;
         }
      }

      return var1;
   }
}
