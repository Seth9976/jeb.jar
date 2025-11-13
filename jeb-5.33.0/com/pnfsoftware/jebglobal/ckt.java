package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ckt {
   private Map pC = new HashMap();

   public void pC(Map var1, List var2, boolean var3) throws IOException {
      int var5;
      for (var5 = 0; var5 < var2.size() - 1; var5++) {
         Object var4 = var1.get(var2.get(var5));
         if (var4 == null) {
            var4 = this.A((String)var2.get(var5));
            var1.put((String)var2.get(var5), var4);
         } else if (!this.pC(var4)) {
            var4 = this.pC(var1, (String)var2.get(var5), var4);
            var1.put((String)var2.get(var5), var4);
         }

         var1 = this.A(var4);
      }

      Object var6 = var1.get(var2.get(var5));
      Object var7;
      if (var3) {
         if (var6 != null) {
            if (this.pC(var6)) {
               return;
            }

            var7 = this.pC(var1, (String)var2.get(var5), var6);
            var1.put((String)var2.get(var5), var7);
            return;
         }

         var7 = this.A((String)var2.get(var5));
      } else {
         if (var6 != null) {
            var7 = this.A((String)var2.get(var5), this.kS(var6));
            var1.put(this.pC(var1, (String)var2.get(var5)), var7);
            return;
         }

         var7 = this.A((String)var2.get(var5), 0);
      }

      var1.put((String)var2.get(var5), var7);
   }

   private Object pC(Map var1, String var2, Object var3) throws IOException {
      var1.put(this.pC(var1, var2), var3);
      Object var4 = this.A(var2);
      Integer var5 = (Integer)this.pC.get(var3);
      if (var5 == null) {
         this.pC.put(var4, 0);
      } else {
         this.pC.remove(var3);
         this.pC.put(var4, var5);
      }

      return var4;
   }

   private int kS(Object var1) {
      Integer var2 = (Integer)this.pC.get(var1);
      if (var2 == null) {
         if (this.pC(var1)) {
            var2 = 0;
         } else {
            var2 = 1;
         }

         this.pC.put(var1, var2);
      } else {
         this.pC.put(var1, var2 = var2 + 1);
      }

      return var2;
   }

   private String pC(Map var1, String var2) {
      int var3 = 1;

      while (var1.containsKey(var2 + "-" + var3)) {
         var3++;
      }

      return var2 + "-" + var3;
   }

   public abstract Object A(String var1) throws IOException;

   public abstract Object A(String var1, int var2) throws IOException;

   public abstract Map A(Object var1) throws IOException;

   public abstract boolean pC(Object var1);
}
