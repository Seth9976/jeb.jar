package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;

public class bxo {
   public int q;
   public bxn[] RF;

   public bxo(int var1, bxn... var2) {
      if (var1 >= 0 && var1 < 1000) {
         this.q = var1;
         if (var2 == null) {
            throw new NullPointerException("Need operators");
         } else {
            for (bxn var6 : var2) {
               if (!var6.Uv()) {
                  throw new IllegalArgumentException("Illegal operator for group");
               }
            }

            this.RF = var2;
         }
      } else {
         throw new IllegalArgumentException("Invalid id for operator group: " + var1);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("O%d{%s}", this.q, Arrays.toString((Object[])this.RF));
   }
}
