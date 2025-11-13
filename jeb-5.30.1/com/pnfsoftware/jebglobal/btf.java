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

public class btf {
   private final int q;
   private int RF;
   private TreeMap xK = new TreeMap();
   private Set Dw = new HashSet();

   public btf(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.RF;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      return 31 * var1 + this.q;
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
         btf var2 = (btf)var1;
         if (this.RF != var2.RF) {
            return false;
         } else {
            if (this.Dw == null) {
               if (var2.Dw != null) {
                  return false;
               }
            } else if (!this.Dw.equals(var2.Dw)) {
               return false;
            }

            if (this.xK == null) {
               if (var2.xK != null) {
                  return false;
               }
            } else if (!this.xK.equals(var2.xK)) {
               return false;
            }

            return this.q == var2.q;
         }
      }
   }

   public void q() {
      this.RF = 0;
      this.xK.clear();
      this.Dw.clear();
   }

   public int RF() {
      return this.RF;
   }

   public boolean q(int var1, int var2) {
      List var3 = (List)this.xK.get(var1);
      if (var3 == null) {
         return false;
      } else {
         int var4 = Collections.binarySearch(var3, var2);
         return var4 >= 0;
      }
   }

   public boolean RF(int var1, int var2) {
      if (!this.xK(var1, var2)) {
         return false;
      } else {
         Object var3 = (List)this.xK.get(var1);
         if (var3 == null) {
            var3 = new ArrayList(1);
            this.xK.put(var1, var3);
         }

         int var4 = Collections.binarySearch((List)var3, var2);
         if (var4 < 0) {
            var3.add(-var4 - 1, var2);
            this.Dw.add(var2);
            this.RF++;
         }

         return true;
      }
   }

   public boolean xK(int var1, int var2) {
      if (var1 >= 0 && var1 <= var2 && var2 < this.q) {
         int var3 = this.q;
         Integer var4 = (Integer)this.xK.lowerKey(var1);
         if (var4 != null) {
            for (int var7 : (List)this.xK.get(var4)) {
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
               for (int var9 : this.xK.subMap(var1 + 1, var3).keySet()) {
                  if (var2 < var9) {
                     break;
                  }

                  if (var2 < this.RF(var9)) {
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

   public void q(int var1, boolean var2, Collection var3) {
      var3.clear();
      if (var1 >= 0 && var1 < this.q) {
         int var4 = this.q - 1;
         Integer var5 = var2 ? (Integer)this.xK.floorKey(var1) : (Integer)this.xK.lowerKey(var1);
         if (var5 != null) {
            for (int var8 : (List)this.xK.get(var5)) {
               if (var8 >= var1) {
                  var4 = var8;
                  break;
               }
            }
         }

         HashSet var10 = new HashSet();
         if (var1 + 1 < var4) {
            for (int var13 : this.xK.subMap(var1 + 1, var4).keySet()) {
               for (int var9 = var13; var9 < this.RF(var13); var9++) {
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

   public List q(int var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      this.q(var1, var2, var3);
      return var3;
   }

   public List q(int var1) {
      return this.q(var1, false);
   }

   private int RF(int var1) {
      List var2 = (List)this.xK.get(var1);
      Assert.a(var2 != null && !var2.isEmpty());
      return (Integer)var2.get(var2.size() - 1);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (int var3 : this.xK.keySet()) {
         for (int var6 : (List)this.xK.get(var3)) {
            Strings.ff(var1, "%d-%d ", var3, var6);
         }
      }

      return var1.toString();
   }
}
