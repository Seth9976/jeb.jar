package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Bu {
   private final List q;
   private List RF;

   public Bu(List var1) {
      this.q = var1;
      this.RF = new ArrayList();
   }

   public void q(Nt var1) {
      this.RF.add(var1);
   }

   public List q() {
      ArrayList var1 = new ArrayList();

      for (Nt var3 : this.RF) {
         var1.add(var3.q(this.q));
      }

      return var1;
   }

   public static enum CU {
      q,
      RF,
      xK,
      Dw;
   }

   public static class eo {
      int q;
      String RF;

      public eo(int var1, String var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.q;
         return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
            Bu.eo var2 = (Bu.eo)var1;
            if (this.q != var2.q) {
               return false;
            } else {
               if (this.RF == null) {
                  if (var2.RF != null) {
                     return false;
                  }
               } else if (!this.RF.equals(var2.RF)) {
                  return false;
               }

               return true;
            }
         }
      }

      @Override
      public String toString() {
         return "opti=" + this.q + ":" + this.RF;
      }
   }

   public static class nI {
      final Bu.CU q;
      final int RF;
      Map xK;
      Map Dw;

      public nI(Bu.CU var1, int var2) {
         this.q = var1;
         this.RF = var2;
         this.xK = new HashMap();
         this.Dw = new HashMap();
      }

      public void q(Bu.eo var1) {
         Integer var2 = (Integer)this.xK.get(var1);
         if (var2 == null) {
            this.xK.put(var1, 1);
         } else {
            this.xK.put(var1, var2 + 1);
         }
      }

      public void RF(Bu.eo var1) {
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("#pipelines:");
         var1.append(this.RF);
         var1.append(Strings.LINESEP);
         var1.append("----------------");
         var1.append(Strings.LINESEP);
         var1.append("pattern:");
         var1.append(this.q);
         var1.append(Strings.LINESEP);
         var1.append("----------------");
         var1.append(Strings.LINESEP);

         for (Couple var4 : this.q()) {
            Strings.ff(var1, "%-40s", var4.getFirst());
            var1.append(" => ");
            var1.append(var4.getSecond());
            Strings.ff(var1, "   (%d%%)", (int)(((Integer)var4.getSecond()).intValue() * 100.0F) / this.RF);
            var1.append(Strings.LINESEP);
         }

         return var1.toString();
      }

      private List q() {
         ArrayList var1 = new ArrayList();

         for (Entry var3 : this.xK.entrySet()) {
            var1.add(new Couple((Bu.eo)var3.getKey(), (Integer)var3.getValue()));
         }

         Collections.sort(var1, new bK(this));
         return var1;
      }
   }
}
