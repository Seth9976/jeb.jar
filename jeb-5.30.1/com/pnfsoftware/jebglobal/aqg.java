package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.ClassVtablePaths;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;

class aqg implements Iterable {
   private List q = new ArrayList();
   private Map RF = new HashMap();
   private boolean xK;

   int q() {
      return this.q.size();
   }

   aqf q(Collection var1, Collection var2) {
      if (this.xK) {
         throw new IllegalStateException();
      } else {
         aqf var3 = new aqf(this, var1, var2);
         this.q.add(var3);
         return var3;
      }
   }

   void RF() {
      if (this.xK) {
         throw new IllegalStateException();
      } else {
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList(this.q);
         HashSet var3 = new HashSet();
         HashSet var4 = new HashSet();
         int var5 = -1;

         while (!var2.isEmpty() && var5 != 0) {
            var5 = 0;
            int var6 = 0;

            while (var6 < var2.size()) {
               aqf var7 = (aqf)var2.get(var6);
               Collection var8 = var7.Dw.values();
               if (CollectionUtils.containsAll(var3, var8)) {
                  var2.remove(var6);
                  var3.add(var7);
                  if (CollectionUtils.containsAny(var4, var8)) {
                     var4.add(var7);
                  } else {
                     int var9 = 0;
                     if (var8.isEmpty()) {
                        var9 = 1;
                     } else {
                        for (aqf var11 : var8) {
                           var9 += var11.xK.size();
                        }
                     }

                     if (var7.xK.size() != var9) {
                        var4.add(var7);
                     } else {
                        var1.add(var7);
                     }
                  }

                  var5++;
               } else {
                  var6++;
               }
            }
         }

         if (!var2.isEmpty() && var5 == 0) {
            throw new RuntimeException("TBE: Cycles in reconstructed class graph");
         } else {
            this.q.clear();
            this.q.addAll(var1);

            for (aqf var14 : this.q) {
               for (aqf var18 : var14.Dw.values()) {
                  if (!var18.oW.contains(var14)) {
                     var18.oW.add(var14);
                  }
               }
            }

            this.q.clear();
            this.q.addAll(var1);
            int var13 = 0;

            for (aqf var17 : this.q) {
               var17.Uv = var13++;
            }

            this.xK = true;
         }
      }
   }

   @Override
   public Iterator iterator() {
      return this.q.iterator();
   }

   List q(aqf var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      this.q(var1, var2, var3, var4, var5);
      ArrayList var6 = new ArrayList(var4.size() + var5.size());

      for (int var7 = var4.size() - 1; var7 >= 0; var7--) {
         var6.add((aqm)var4.get(var7));
      }

      var6.addAll(var5);
      return var6;
   }

   void q(aqf var1, int var2, int var3, List var4, List var5) {
      if (var4 != null) {
         this.q(var1, var2, var3, var4);
      }

      if (var5 != null) {
         List var6 = this.RF(var1, var2);
         this.q(var1, var6, var3, var5);
      }
   }

   private void q(aqf var1, int var2, int var3, List var4) {
      if (var2 >= 0 && var2 < var1.xK.size()) {
         aqp var5 = (aqp)var1.xK.get(var2);
         if (var3 < var5.xK.size()) {
            long var6 = (Long)var5.xK.get(var3);
            if (var6 != 0L) {
               var4.add(new aqm(var1, var2, var3));
            }

            List var8 = this.q(var1);
            if (var2 < var8.size()) {
               List var9 = (List)var8.get(var2);
               if (var9.size() == 1) {
                  return;
               }

               Assert.a(var9.get(0) == var1);
               aqf var10 = (aqf)var9.get(1);
               var9.remove(0);
               var2 = this.q(var10, var9);
               this.q(var10, var2, var3, var4);
            }
         }
      }
   }

   private void q(aqf var1, List var2, int var3, List var4) {
      for (aqf var6 : var1.oW) {
         ArrayList var7 = new ArrayList(var2);
         var7.add(0, var6);
         int var8 = this.q(var6, var7);
         if (var8 >= 0) {
            long var9 = RF(var6, var8, var3);
            if (var9 != 0L) {
               var4.add(new aqm(var6, var8, var3));
               this.q(var6, var7, var3, var4);
            }
         }
      }
   }

   List q(aqf var1, int var2) {
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      this.q(var1, var2, var3, var4);
      ArrayList var5 = new ArrayList(var3.size() + var4.size());

      for (int var6 = var3.size() - 1; var6 >= 0; var6--) {
         var5.add((aqn)var3.get(var6));
      }

      var5.addAll(var4);
      return var5;
   }

   void q(aqf var1, int var2, List var3, List var4) {
      if (var3 != null) {
         this.q(var1, var2, var3, null);
      }

      if (var4 != null) {
         List var5 = this.RF(var1, var2);
         this.q(var1, var5, var4, var3 == null ? null : (aqn)var3.get(0));
      }
   }

   private void q(aqf var1, int var2, List var3, aqn var4) {
      if (var2 >= 0 && var2 < var1.xK.size()) {
         aqn var5 = new aqn(var1, var2);
         var3.add(var5);
         if (var4 != null) {
            var4.xK = var5;
         }

         List var6 = this.q(var1);
         if (var2 < var6.size()) {
            List var7 = (List)var6.get(var2);
            if (var7.size() == 1) {
               return;
            }

            Assert.a(var7.get(0) == var1);
            aqf var8 = (aqf)var7.get(1);
            var7.remove(0);
            var2 = this.q(var8, var7);
            this.q(var8, var2, var3, var5);
         }
      }
   }

   private void q(aqf var1, List var2, List var3, aqn var4) {
      for (aqf var6 : var1.oW) {
         ArrayList var7 = new ArrayList(var2);
         var7.add(0, var6);
         int var8 = this.q(var6, var7);
         if (var8 >= 0) {
            aqn var9 = new aqn(var6, var8, var4);
            var3.add(var9);
            this.q(var6, var7, var3, var9);
         }
      }
   }

   List q(aqf var1) {
      List var2 = (List)this.RF.get(var1);
      if (var2 == null) {
         var2 = new ClassVtablePaths(var1, new aqh(this)).determineFull();
      }

      return var2;
   }

   List RF(aqf var1, int var2) {
      List var3 = this.q(var1);
      return var2 >= 0 && var2 < var3.size() ? (List)var3.get(var2) : null;
   }

   int q(aqf var1, List var2) {
      int var3 = 0;

      for (List var5 : this.q(var1)) {
         if (q(var5, var2)) {
            return var3;
         }

         var3++;
      }

      return -1;
   }

   private static boolean q(List var0, List var1) {
      if (var0.size() != var1.size()) {
         return false;
      } else {
         for (int var2 = 0; var2 < var0.size(); var2++) {
            if (var0.get(var2) != var1.get(var2)) {
               return false;
            }
         }

         return true;
      }
   }

   private static long RF(aqf var0, int var1, int var2) {
      if (var1 >= 0 && var1 < var0.xK.size()) {
         aqp var3 = (aqp)var0.xK.get(var1);
         if (var2 >= 0 && var2 < var3.xK.size()) {
            return (Long)var3.xK.get(var2);
         }
      }

      return 0L;
   }

   static long q(aqn var0) {
      return ((aqp)var0.q.xK.get(var0.RF)).RF();
   }
}
