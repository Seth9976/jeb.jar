package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;

public class OGroup {
   public int id;
   public O[] operators;

   public OGroup(int var1, O... var2) {
      if (var1 >= 0 && var1 < 1000) {
         this.id = var1;
         if (var2 == null) {
            throw new NullPointerException("Need operators");
         } else {
            for (O var6 : var2) {
               if (!var6.isNormal()) {
                  throw new IllegalArgumentException("Illegal operator for group");
               }
            }

            this.operators = var2;
         }
      } else {
         throw new IllegalArgumentException("Invalid id for operator group: " + var1);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("O%d{%s}", this.id, Arrays.toString((Object[])this.operators));
   }
}
