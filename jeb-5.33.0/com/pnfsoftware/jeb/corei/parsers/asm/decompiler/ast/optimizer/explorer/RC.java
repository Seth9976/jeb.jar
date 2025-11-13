package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RC {
   private final List pC;
   private List A;

   public RC(List var1) {
      this.pC = var1;
      this.A = new ArrayList();
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer.K var1) {
      this.A.add(var1);
   }

   public List pC() {
      ArrayList var1 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer.K var3 : this.A) {
         var1.add(var3.pC(this.pC));
      }

      return var1;
   }

   public static class Av {
      int pC;
      String A;

      public Av(int var1, String var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.pC;
         return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            RC.Av var2 = (RC.Av)var1;
            if (this.pC != var2.pC) {
               return false;
            } else {
               if (this.A == null) {
                  if (var2.A != null) {
                     return false;
                  }
               } else if (!this.A.equals(var2.A)) {
                  return false;
               }

               return true;
            }
         }
      }

      @Override
      public String toString() {
         return "opti=" + this.pC + ":" + this.A;
      }
   }

   public static class K {
      final RC.Sv pC;
      final int A;
      Map kS;
      Map wS;

      public K(RC.Sv var1, int var2) {
         this.pC = var1;
         this.A = var2;
         this.kS = new HashMap();
         this.wS = new HashMap();
      }

      public void pC(RC.Av var1) {
         Integer var2 = (Integer)this.kS.get(var1);
         if (var2 == null) {
            this.kS.put(var1, 1);
         } else {
            this.kS.put(var1, var2 + 1);
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("#pipelines:");
         var1.append(this.A);
         var1.append(Strings.LINESEP);
         var1.append("----------------");
         var1.append(Strings.LINESEP);
         var1.append("pattern:");
         var1.append(this.pC);
         var1.append(Strings.LINESEP);
         var1.append("----------------");
         var1.append(Strings.LINESEP);

         for (Couple var4 : this.pC()) {
            Strings.ff(var1, "%-40s", var4.getFirst());
            var1.append(" => ");
            var1.append(var4.getSecond());
            Strings.ff(var1, "   (%d%%)", (int)(((Integer)var4.getSecond()).intValue() * 100.0F) / this.A);
            var1.append(Strings.LINESEP);
         }

         return var1.toString();
      }

      private List pC() {
         ArrayList var1 = new ArrayList();

         for (Entry var3 : this.kS.entrySet()) {
            var1.add(new Couple((RC.Av)var3.getKey(), (Integer)var3.getValue()));
         }

         Collections.sort(var1, new sy(this));
         return var1;
      }
   }

   public static enum Sv {
      pC,
      A,
      kS,
      wS;
   }
}
