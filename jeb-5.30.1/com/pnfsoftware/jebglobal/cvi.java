package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class cvi {
   private Map q = new HashMap();

   public void q(Map var1, List var2, boolean var3) throws IOException {
      int var5;
      for (var5 = 0; var5 < var2.size() - 1; var5++) {
         Object var4 = var1.get(var2.get(var5));
         if (var4 == null) {
            var4 = this.RF((String)var2.get(var5));
            var1.put((String)var2.get(var5), var4);
         } else if (!this.q(var4)) {
            var4 = this.q(var1, (String)var2.get(var5), var4);
            var1.put((String)var2.get(var5), var4);
         }

         var1 = this.RF(var4);
      }

      Object var6 = var1.get(var2.get(var5));
      Object var7;
      if (var3) {
         if (var6 != null) {
            if (this.q(var6)) {
               return;
            }

            var7 = this.q(var1, (String)var2.get(var5), var6);
            var1.put((String)var2.get(var5), var7);
            return;
         }

         var7 = this.RF((String)var2.get(var5));
      } else {
         if (var6 != null) {
            var7 = this.RF((String)var2.get(var5), this.xK(var6));
            var1.put(this.q(var1, (String)var2.get(var5)), var7);
            return;
         }

         var7 = this.RF((String)var2.get(var5), 0);
      }

      var1.put((String)var2.get(var5), var7);
   }

   private Object q(Map var1, String var2, Object var3) throws IOException {
      var1.put(this.q(var1, var2), var3);
      Object var4 = this.RF(var2);
      Integer var5 = (Integer)this.q.get(var3);
      if (var5 == null) {
         this.q.put(var4, 0);
      } else {
         this.q.remove(var3);
         this.q.put(var4, var5);
      }

      return var4;
   }

   private int xK(Object var1) {
      Integer var2 = (Integer)this.q.get(var1);
      if (var2 == null) {
         if (this.q(var1)) {
            var2 = 0;
         } else {
            var2 = 1;
         }

         this.q.put(var1, var2);
      } else {
         this.q.put(var1, var2 = var2 + 1);
      }

      return var2;
   }

   private String q(Map var1, String var2) {
      int var3 = 1;

      while (var1.containsKey(var2 + "-" + var3)) {
         var3++;
      }

      return var2 + "-" + var3;
   }

   public abstract Object RF(String var1) throws IOException;

   public abstract Object RF(String var1, int var2) throws IOException;

   public abstract Map RF(Object var1) throws IOException;

   public abstract boolean q(Object var1);
}
