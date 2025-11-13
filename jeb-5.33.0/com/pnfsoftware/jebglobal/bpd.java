package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class bpd {
   private final int pC;
   private int A;
   private TreeMap kS = new TreeMap();
   private Set wS = new HashSet();

   public bpd(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.A;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + this.pC;
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
         bpd var2 = (bpd)var1;
         if (this.A != var2.A) {
            return false;
         } else {
            if (this.wS == null) {
               if (var2.wS != null) {
                  return false;
               }
            } else if (!this.wS.equals(var2.wS)) {
               return false;
            }

            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            return this.pC == var2.pC;
         }
      }
   }

   public boolean pC(int var1, int var2) {
      if (!this.A(var1, var2)) {
         return false;
      } else {
         Object var3 = (List)this.kS.get(var1);
         if (var3 == null) {
            var3 = new ArrayList(1);
            this.kS.put(var1, var3);
         }

         int var4 = Collections.binarySearch((List)var3, var2);
         if (var4 < 0) {
            var3.add(-var4 - 1, var2);
            this.wS.add(var2);
            this.A++;
         }

         return true;
      }
   }

   public boolean A(int var1, int var2) {
      if (var1 >= 0 && var1 <= var2 && var2 < this.pC) {
         int var3 = this.pC;
         Integer var4 = (Integer)this.kS.lowerKey(var1);
         if (var4 != null) {
            for (int var7 : (List)this.kS.get(var4)) {
               if (var7 >= var1) {
                  var3 = var7;
                  break;
               }
            }
         }

         if (var2 > var3) {
            return false;
         } else {
            if (var1 + 1 < var3) {
               for (int var9 : this.kS.subMap(var1 + 1, var3).keySet()) {
                  if (var2 < var9) {
                     break;
                  }

                  if (var2 < this.pC(var9)) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public void pC(int var1, boolean var2, Collection var3) {
      var3.clear();
      if (var1 >= 0 && var1 < this.pC) {
         int var4 = this.pC - 1;
         Integer var5 = var2 ? (Integer)this.kS.floorKey(var1) : (Integer)this.kS.lowerKey(var1);
         if (var5 != null) {
            for (int var8 : (List)this.kS.get(var5)) {
               if (var8 >= var1) {
                  var4 = var8;
                  break;
               }
            }
         }

         HashSet var10 = new HashSet();
         if (var1 + 1 < var4) {
            for (int var13 : this.kS.subMap(var1 + 1, var4).keySet()) {
               for (int var9 = var13; var9 < this.pC(var13); var9++) {
                  var10.add(var9);
               }
            }
         }

         for (int var12 = var1; var12 <= var4; var12++) {
            if (!var10.contains(var12)) {
               var3.add(var12);
            }
         }
      }
   }

   private int pC(int var1) {
      List var2 = (List)this.kS.get(var1);
      Assert.a(var2 != null && !var2.isEmpty());
      return (Integer)var2.get(var2.size() - 1);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (int var3 : this.kS.keySet()) {
         for (int var6 : (List)this.kS.get(var3)) {
            Strings.ff(var1, "%d-%d ", var3, var6);
         }
      }

      return var1.toString();
   }
}
