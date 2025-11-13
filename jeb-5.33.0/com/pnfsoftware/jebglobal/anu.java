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

class anu implements Iterable {
   private List pC = new ArrayList();
   private Map A = new HashMap();
   private boolean kS;

   int pC() {
      return this.pC.size();
   }

   ant pC(Collection var1, Collection var2) {
      if (this.kS) {
         throw new IllegalStateException();
      } else {
         ant var3 = new ant(this, var1, var2);
         this.pC.add(var3);
         return var3;
      }
   }

   void A() {
      if (this.kS) {
         throw new IllegalStateException();
      } else {
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList(this.pC);
         HashSet var3 = new HashSet();
         HashSet var4 = new HashSet();
         int var5 = -1;

         while (!var2.isEmpty() && var5 != 0) {
            var5 = 0;
            int var6 = 0;

            while (var6 < var2.size()) {
               ant var7 = (ant)var2.get(var6);
               Collection var8 = var7.wS.values();
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
                        for (ant var11 : var8) {
                           var9 += var11.kS.size();
                        }
                     }

                     if (var7.kS.size() != var9) {
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
            this.pC.clear();
            this.pC.addAll(var1);

            for (ant var14 : this.pC) {
               for (ant var18 : var14.wS.values()) {
                  if (!var18.E.contains(var14)) {
                     var18.E.add(var14);
                  }
               }
            }

            this.pC.clear();
            this.pC.addAll(var1);
            int var13 = 0;

            for (ant var17 : this.pC) {
               var17.UT = var13++;
            }

            this.kS = true;
         }
      }
   }

   @Override
   public Iterator iterator() {
      return this.pC.iterator();
   }

   List pC(ant var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      this.pC(var1, var2, var3, var4, var5);
      ArrayList var6 = new ArrayList(var4.size() + var5.size());

      for (int var7 = var4.size() - 1; var7 >= 0; var7--) {
         var6.add((aoa)var4.get(var7));
      }

      var6.addAll(var5);
      return var6;
   }

   void pC(ant var1, int var2, int var3, List var4, List var5) {
      if (var4 != null) {
         this.pC(var1, var2, var3, var4);
      }

      if (var5 != null) {
         List var6 = this.A(var1, var2);
         this.pC(var1, var6, var3, var5);
      }
   }

   private void pC(ant var1, int var2, int var3, List var4) {
      if (var2 >= 0 && var2 < var1.kS.size()) {
         aod var5 = (aod)var1.kS.get(var2);
         if (var3 < var5.kS.size()) {
            long var6 = (Long)var5.kS.get(var3);
            if (var6 != 0L) {
               var4.add(new aoa(var1, var2, var3));
            }

            List var8 = this.pC(var1);
            if (var2 < var8.size()) {
               List var9 = (List)var8.get(var2);
               if (var9.size() == 1) {
                  return;
               }

               Assert.a(var9.get(0) == var1);
               ant var10 = (ant)var9.get(1);
               var9.remove(0);
               var2 = this.pC(var10, var9);
               this.pC(var10, var2, var3, var4);
            }
         }
      }
   }

   private void pC(ant var1, List var2, int var3, List var4) {
      for (ant var6 : var1.E) {
         ArrayList var7 = new ArrayList(var2);
         var7.add(0, var6);
         int var8 = this.pC(var6, var7);
         if (var8 >= 0) {
            long var9 = A(var6, var8, var3);
            if (var9 != 0L) {
               var4.add(new aoa(var6, var8, var3));
               this.pC(var6, var7, var3, var4);
            }
         }
      }
   }

   List pC(ant var1, int var2) {
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      this.pC(var1, var2, var3, var4);
      ArrayList var5 = new ArrayList(var3.size() + var4.size());

      for (int var6 = var3.size() - 1; var6 >= 0; var6--) {
         var5.add((aob)var3.get(var6));
      }

      var5.addAll(var4);
      return var5;
   }

   void pC(ant var1, int var2, List var3, List var4) {
      if (var3 != null) {
         this.pC(var1, var2, var3, null);
      }

      if (var4 != null) {
         List var5 = this.A(var1, var2);
         this.pC(var1, var5, var4, var3 == null ? null : (aob)var3.get(0));
      }
   }

   private void pC(ant var1, int var2, List var3, aob var4) {
      if (var2 >= 0 && var2 < var1.kS.size()) {
         aob var5 = new aob(var1, var2);
         var3.add(var5);
         if (var4 != null) {
            var4.kS = var5;
         }

         List var6 = this.pC(var1);
         if (var2 < var6.size()) {
            List var7 = (List)var6.get(var2);
            if (var7.size() == 1) {
               return;
            }

            Assert.a(var7.get(0) == var1);
            ant var8 = (ant)var7.get(1);
            var7.remove(0);
            var2 = this.pC(var8, var7);
            this.pC(var8, var2, var3, var5);
         }
      }
   }

   private void pC(ant var1, List var2, List var3, aob var4) {
      for (ant var6 : var1.E) {
         ArrayList var7 = new ArrayList(var2);
         var7.add(0, var6);
         int var8 = this.pC(var6, var7);
         if (var8 >= 0) {
            aob var9 = new aob(var6, var8, var4);
            var3.add(var9);
            this.pC(var6, var7, var3, var9);
         }
      }
   }

   List pC(ant var1) {
      List var2 = (List)this.A.get(var1);
      if (var2 == null) {
         var2 = new ClassVtablePaths(var1, new anv(this)).determineFull();
      }

      return var2;
   }

   List A(ant var1, int var2) {
      List var3 = this.pC(var1);
      return var2 >= 0 && var2 < var3.size() ? (List)var3.get(var2) : null;
   }

   int pC(ant var1, List var2) {
      int var3 = 0;

      for (List var5 : this.pC(var1)) {
         if (pC(var5, var2)) {
            return var3;
         }

         var3++;
      }

      return -1;
   }

   private static boolean pC(List var0, List var1) {
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

   private static long A(ant var0, int var1, int var2) {
      if (var1 >= 0 && var1 < var0.kS.size()) {
         aod var3 = (aod)var0.kS.get(var1);
         if (var2 >= 0 && var2 < var3.kS.size()) {
            return (Long)var3.kS.get(var2);
         }
      }

      return 0L;
   }
}
