package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class bix {
   private List q;
   private List RF;

   public bix(List var1) {
      int var2 = 0;

      for (biw var4 : var1) {
         if (var4.getTryAddress() < var2) {
            throw new IllegalArgumentException();
         }

         var2 = var4.RF();
      }

      this.q = var1;
   }

   public bix(biw[] var1) {
      this(Arrays.asList(var1));
   }

   public boolean q() {
      return this.q.isEmpty();
   }

   public List RF() {
      return Collections.unmodifiableList(this.q);
   }

   public List xK() {
      if (this.RF == null) {
         this.RF = this.oW();
      }

      return this.RF;
   }

   public List Dw() {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (List var4 : this.xK()) {
         for (biw var6 : var4) {
            var1.add(new bix.eo(var6, var2));
         }

         var2++;
      }

      var1.sort(null);
      ArrayList var7 = new ArrayList();

      for (bix.eo var9 : var1) {
         var7.add(var9.q);
      }

      return var7;
   }

   public Set Uv() {
      HashSet var1 = new HashSet();

      for (biw var3 : this.q) {
         var1.addAll(var3.nf());
      }

      return var1;
   }

   public Set q(int var1) {
      HashSet var2 = new HashSet();

      for (biw var4 : this.q) {
         for (biv var6 : var4.xK) {
            if (var6.getAddress() == var1) {
               var2.add(var6.getTypeIndex());
            }
         }
      }

      if (var2.isEmpty()) {
         throw new IllegalArgumentException(Strings.ff("No handler found at address: %Xh", var1));
      } else {
         return var2;
      }
   }

   public int RF(int var1) {
      Integer var2 = null;

      for (biw var4 : this.q) {
         for (biv var6 : var4.xK) {
            if (var6.getAddress() == var1) {
               int var7 = var6.RF();
               if (var2 == null) {
                  var2 = var7;
               } else if (var7 != var2) {
                  return -1;
               }
            }
         }
      }

      if (var2 == null) {
         throw new IllegalArgumentException(Strings.ff("No handler found at address: %Xh", var1));
      } else {
         return var2;
      }
   }

   private List oW() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();

      for (biw var4 : this.q) {
         var2.add(var4.q());
      }

      ArrayList var16 = new ArrayList();
      var16.add(var2);

      while (!var16.isEmpty()) {
         List var15 = (List)var16.remove(0);
         ArrayList var17 = new ArrayList();
         var1.add(var17);

         while (!var15.isEmpty()) {
            biw var5 = (biw)var15.get(0);
            int var6 = var5.getTryAddress();
            int var7 = var5.RF();
            List var8 = Lists.reverse(var5.getHandlers());

            int var9;
            for (var9 = 1; var9 < var15.size(); var9++) {
               biw var10 = (biw)var15.get(var9);
               if (var10.getTryAddress() != var7) {
                  break;
               }

               int var11 = Math.min(var8.size(), var10.gO());
               int var12 = 0;

               for (biv var14 : var8) {
                  if (var12 >= var11 || !var14.equals(var10.xK(var10.gO() - 1 - var12))) {
                     break;
                  }

                  var12++;
               }

               if (var12 == 0) {
                  break;
               }

               var8 = var8.subList(0, var12);
               var7 = var10.RF();
            }

            int var18 = var9;
            if (var9 == 1) {
               var17.add(var5);
               var15.remove(0);
            } else {
               Lists.reverse(var8);
               biw var19 = new biw(var6, var7 - var6, var8);
               var17.add(var19);
               ArrayList var20 = new ArrayList();

               while (var18-- > 0) {
                  biw var21 = (biw)var15.remove(0);
                  if (!this.RF(var21, var8.size())) {
                     var20.add(var21);
                  }
               }

               if (!var20.isEmpty()) {
                  var16.add(var20);
               }
            }
         }
      }

      return var1;
   }

   private boolean RF(biw var1, int var2) {
      int var3 = var1.xK.size() - var2;

      while (var2-- > 0) {
         var1.xK.remove(var3);
      }

      return var1.xK.isEmpty();
   }

   @Override
   public String toString() {
      return this.q.toString();
   }

   private Couple xK(biw var1) {
      int var2 = 0;

      for (List var4 : this.RF) {
         int var5 = var4.indexOf(var1);
         if (var5 >= 0) {
            return new Couple(var2, var5);
         }

         var2++;
      }

      return null;
   }

   private Couple q(Couple var1) {
      if ((Integer)var1.getFirst() == 0) {
         return null;
      } else {
         biw var2 = (biw)((List)this.RF.get((Integer)var1.getFirst())).get((Integer)var1.getSecond());
         int var3 = (Integer)var1.getFirst() - 1;
         int var4 = 0;

         for (biw var6 : (List)this.RF.get(var3)) {
            if (var6.getTryAddress() <= var2.getTryAddress() && var6.RF() >= var2.RF()) {
               return new Couple(var3, var4);
            }

            var4++;
         }

         throw new RuntimeException();
      }
   }

   private biw RF(Couple var1) {
      return (biw)((List)this.RF.get((Integer)var1.getFirst())).get((Integer)var1.getSecond());
   }

   public int q(biw var1) {
      Couple var2 = this.xK(var1);
      if ((Integer)var2.getSecond() >= 1) {
         List var3 = (List)this.RF.get((Integer)var2.getFirst());
         return ((biw)var3.get((Integer)var2.getSecond() - 1)).RF();
      } else if ((Integer)var2.getFirst() >= 1) {
         var2 = this.q(var2);
         return this.RF(var2).getTryAddress();
      } else {
         return 0;
      }
   }

   public int q(biw var1, int var2) {
      Couple var3 = this.xK(var1);
      List var4 = (List)this.RF.get((Integer)var3.getFirst());
      if ((Integer)var3.getSecond() + 1 < var4.size()) {
         return ((biw)var4.get((Integer)var3.getSecond() + 1)).getTryAddress();
      } else if ((Integer)var3.getFirst() >= 1) {
         var3 = this.q(var3);
         return this.RF(var3).RF();
      } else {
         return var2;
      }
   }

   public List RF(biw var1) {
      ArrayList var2 = new ArrayList();
      int var3 = var1.getTryAddress();
      Couple var4 = this.xK(var1);
      int var5 = (Integer)var4.getFirst() + 1;
      if (var5 < this.RF.size()) {
         for (biw var8 : (List)this.RF.get(var5)) {
            if (var8.getTryAddress() >= var1.getTryAddress() && var8.getTryAddress() < var1.RF()) {
               if (var8.getTryAddress() > var3) {
                  var2.add(new bix.CU(var3, var8.getTryAddress()));
               }

               var3 = var8.RF();
            }
         }

         if (var3 < var1.RF()) {
            var2.add(new bix.CU(var3, var1.RF()));
         }
      }

      if (var2.isEmpty()) {
         var2.add(new bix.CU(var1.getTryAddress(), var1.RF()));
      }

      return var2;
   }

   public List q(TreeSet var1) {
      ArrayList var2 = new ArrayList();
      if (this.q()) {
         return var2;
      } else {
         TreeMap var3 = new TreeMap();

         for (List var5 : this.RF) {
            for (biw var7 : var5) {
               int var8 = var7.getTryAddress();
               Object var9 = (List)var3.get(var8);
               if (var9 == null) {
                  var9 = new ArrayList(var7.gO());
                  var3.put(var8, var9);
               }

               var9.addAll(var7.getHandlers());
            }
         }

         List var16 = (List)this.RF.get(0);
         int var17 = ((biw)var16.get(var16.size() - 1)).RF();
         ArrayList var18 = new ArrayList(var3.keySet());

         for (int var19 = 0; var19 < var18.size(); var19++) {
            int var20 = (Integer)var18.get(var19);
            List var21 = (List)var3.get(var20);
            int var10 = var19 + 1 < var18.size() ? (Integer)var18.get(var19 + 1) : var17;
            SortedSet var11 = var1.subSet(var20, var10);
            Assert.a(var11.size() >= 1);
            Assert.a(var1.contains(var20));
            Assert.a(var1.contains(var10));
            Iterator var12 = var11.iterator();
            int var13 = (Integer)var12.next();

            while (var12.hasNext()) {
               int var15 = (Integer)var12.next();
               var2.add(new biw(var13, var15 - var13, biv.q(var21)));
               var13 = var15;
            }

            var2.add(new biw(var13, var10 - var13, biv.q(var21)));
         }

         return var2;
      }
   }

   public static class CU {
      public int q;
      public int RF;

      public CU(int var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      public int q() {
         return this.q;
      }

      public int RF() {
         return this.RF;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.RF;
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
            bix.CU var2 = (bix.CU)var1;
            return this.RF != var2.RF ? false : this.q == var2.q;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("[%Xh,%Xh[", this.q, this.RF);
      }
   }

   private static class eo implements Comparable {
      biw q;
      int RF;

      public eo(biw var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      public int q(bix.eo var1) {
         return this.q.q != var1.q.q ? Integer.compare(this.q.q, var1.q.q) : Integer.compare(this.RF, var1.RF);
      }
   }
}
