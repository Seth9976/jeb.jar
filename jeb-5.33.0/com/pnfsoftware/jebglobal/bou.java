package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class bou {
   private static final ILogger A = GlobalLog.getLogger(bou.class);
   public static int pC = 0;
   private List kS = new ArrayList();

   private bou() {
   }

   public bou(bou var1) {
      this.kS = new ArrayList(var1.kS.size());
      IdentityHashMap var2 = new IdentityHashMap();

      for (bpc var4 : var1.kS) {
         bpc var5 = new bpc(var4.pC);
         var2.put(var4, var5);
         this.kS.add(var5);
      }

      for (bpc var9 : var1.kS) {
         bpc var10 = (bpc)var2.get(var9);

         for (bpc var7 : var9.kS) {
            var10.kS.add((bpc)var2.get(var7));
         }

         for (bpc var14 : var9.A) {
            var10.A.add((bpc)var2.get(var14));
         }

         for (bpc var15 : var9.UT) {
            var10.UT.add((bpc)var2.get(var15));
         }

         for (bpc var16 : var9.wS) {
            var10.wS.add((bpc)var2.get(var16));
         }
      }
   }

   public bou(List var1, List var2) {
      int[][] var3 = new int[var1.size()][];

      for (int var4 = 0; var4 < var3.length; var4++) {
         var3[var4] = (int[])var1.get(var4);
      }

      int[][] var6 = null;
      if (var2 != null) {
         var6 = new int[var2.size()][];

         for (int var5 = 0; var5 < var6.length; var5++) {
            var6[var5] = (int[])var2.get(var5);
         }
      }

      this.pC(var3, var6);
   }

   private void pC(int[][] var1, int[][] var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var1.length != 0 || var2 != null && var2.length != 0) {
         int[][][] var3 = var2 != null ? new int[][][]{var1, var2} : new int[][][]{var1};
         HashSet var4 = new HashSet();
         int var5 = 0;

         for (int[][] var9 : var3) {
            for (int[] var13 : var9) {
               if (var13.length != 2) {
                  throw new RuntimeException("Invalid edge definition: " + Arrays.toString(var13));
               }

               var4.add(var13[0]);
               var4.add(var13[1]);
               if (var13[0] > var5) {
                  var5 = var13[0];
               }

               if (var13[1] > var5) {
                  var5 = var13[1];
               }
            }
         }

         for (int var14 = 1; var14 <= var5; var14++) {
            if (!var4.contains(var14)) {
               throw new RuntimeException("A node is missing: " + var14);
            }

            this.kS.add(new bpc(var14));
         }

         for (int[] var23 : var1) {
            bpc var25 = this.pC(var23[0]);
            bpc var27 = this.pC(var23[1]);
            if (var25.kS.contains(var27)) {
               var27.A.contains(var25);
            }

            var25.kS.add(var27);
            var27.A.add(var25);
         }

         if (var2 != null) {
            for (int[] var24 : var2) {
               bpc var26 = this.pC(var24[0]);
               bpc var28 = this.pC(var24[1]);
               if (var26.UT.contains(var28)) {
                  var28.wS.contains(var26);
               }

               var26.UT.add(var28);
               var28.wS.add(var26);
            }
         }

         for (bpc var20 : this.kS) {
            if (var20.pC != 1 && var20.UT() == 0) {
               throw new RuntimeException("A node without predecessor is not the header: " + var20);
            }
         }

         if (!this.ys(var5)) {
            throw new RuntimeException("Fragmented Graph");
         }
      } else {
         this.kS.add(new bpc(1));
      }
   }

   private boolean ys(int var1) {
      HashSet var2 = new HashSet();
      this.pC((bpc)this.kS.get(0), var2);
      return var2.size() == var1;
   }

   private void pC(bpc var1, Set var2) {
      if (!var2.contains(var1)) {
         var2.add(var1);

         for (bpc var4 : var1.A()) {
            this.pC(var4, var2);
         }
      }
   }

   public Map pC(List var1) {
      HashMap var2 = new HashMap();
      ArrayList var3 = new ArrayList();
      int var4 = 1;

      for (int var6 : var1) {
         bpc var7 = (bpc)this.kS.get(var6 - 1);
         var3.add(var7);
         var7.pC = var4++;
         var2.put(var7.pC, var6);
      }

      this.kS = var3;
      return var2;
   }

   bpc pC(int var1) {
      if (var1 > 0 && var1 <= this.kS.size()) {
         return (bpc)this.kS.get(var1 - 1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public List pC() {
      ArrayList var1 = new ArrayList();

      for (bpc var3 : this.kS) {
         if (var3.kS.isEmpty()) {
            var1.add(var3.pC);
         }
      }

      return var1;
   }

   public int A() {
      return this.kS.size();
   }

   public int kS() {
      int var1 = 0;

      for (bpc var3 : this.kS) {
         var1 += var3.wS();
      }

      return var1;
   }

   public List A(int var1) {
      ArrayList var2 = new ArrayList();

      for (bpc var4 : this.pC(var1).A) {
         var2.add(var4.pC);
      }

      return var2;
   }

   public List kS(int var1) {
      ArrayList var2 = new ArrayList();

      for (bpc var4 : this.pC(var1).A) {
         var2.add(var4.pC);
      }

      for (bpc var6 : this.pC(var1).wS) {
         var2.add(var6.pC);
      }

      return var2;
   }

   public List pC(int var1, boolean var2) {
      return !var2 ? this.wS(var1) : this.UT(var1);
   }

   public List wS(int var1) {
      ArrayList var2 = new ArrayList();

      for (bpc var4 : this.pC(var1).kS) {
         var2.add(var4.pC);
      }

      return var2;
   }

   public List UT(int var1) {
      ArrayList var2 = new ArrayList();

      for (bpc var4 : this.pC(var1).kS) {
         var2.add(var4.pC);
      }

      for (bpc var6 : this.pC(var1).UT) {
         var2.add(var6.pC);
      }

      return var2;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("(").append(this.kS.size()).append(", [");
      int var2 = 0;

      for (bpc var4 : this.kS) {
         for (bpc var6 : var4.kS) {
            if (var2++ > 0) {
               var1.append(", ");
            }

            var1.append(var4.pC).append(">").append(var6.pC);
         }

         for (bpc var8 : var4.UT) {
            if (var2++ > 0) {
               var1.append(", ");
            }

            var1.append(var4.pC).append("\\").append(var8.pC);
         }
      }

      var1.append("])");
      return var1.toString();
   }

   public boy wS() {
      boy var1 = new boy();

      for (List var3 : this.sY()) {
         ArrayList var4 = new ArrayList();

         for (bpc var6 : var3) {
            var4.add(var6.pC);
         }

         var1.pC.add(var4);
      }

      return var1;
   }

   private List sY() {
      bpc var1 = (bpc)this.kS.get(0);
      ArrayDeque var2 = new ArrayDeque();
      var2.add(var1);
      LinkedList var3 = new LinkedList(this.kS);
      var3.remove(var1);
      ArrayList var4 = new ArrayList();

      while (!var2.isEmpty()) {
         ArrayList var5 = new ArrayList();
         var5.add((bpc)var2.remove());

         while (true) {
            ArrayList var6 = new ArrayList();

            for (bpc var8 : var3) {
               boolean var9 = true;

               for (bpc var11 : var8.kS()) {
                  if (!var5.contains(var11)) {
                     var9 = false;
                     break;
                  }
               }

               if (var9) {
                  var5.add(var8);
                  var6.add(var8);
               }
            }

            if (var6.isEmpty()) {
               var6 = new ArrayList();

               for (bpc var14 : var3) {
                  for (bpc var16 : var14.kS()) {
                     if (var5.contains(var16)) {
                        var6.add(var14);
                        break;
                     }
                  }
               }

               var2.addAll(var6);
               var3.removeAll(var6);
               var4.add(var5);
               break;
            }

            var3.removeAll(var6);
         }
      }

      return var4;
   }

   private int pC(List var1, bpc var2) {
      int var3 = 0;

      for (List var5 : var1) {
         for (bpc var7 : var5) {
            if (var7 == var2) {
               return var3;
            }
         }

         var3++;
      }

      return -1;
   }

   public bot UT() {
      bot var1 = new bot();
      bou var2 = this;

      while (true) {
         boy var3 = var2.wS();
         var1.pC.add(new bot.Av(var2, var3));
         if (var3.pC() == var2.A()) {
            return var1;
         }

         List var4 = var2.sY();
         bou var5 = new bou();
         int var6 = 1;

         for (List var8 : var4) {
            bpc var9 = new bpc(var6);
            var9.E = new ArrayList();
            bpc var10 = (bpc)var8.get(0);

            for (bpc var12 : var10.A) {
               if (!var8.contains(var12)) {
                  var9.A.add(var12);
               }
            }

            for (bpc var27 : var10.wS) {
               if (!var8.contains(var27)) {
                  var9.wS.add(var27);
               }
            }

            for (bpc var28 : var8) {
               for (bpc var14 : var28.kS) {
                  if (!var8.contains(var14) && !var9.kS.contains(var14)) {
                     var9.kS.add(var14);
                  }
               }

               for (bpc var38 : var28.UT) {
                  if (!var8.contains(var38) && !var9.UT.contains(var38)) {
                     var9.UT.add(var38);
                  }
               }
            }

            var5.kS.add(var9);
            var6++;
         }

         for (bpc var18 : var5.kS) {
            ArrayList var19 = new ArrayList();

            for (bpc var24 : var18.A) {
               int var29 = this.pC(var4, var24);
               bpc var34 = (bpc)var5.kS.get(var29);
               if (!var19.contains(var34)) {
                  var19.add(var34);
               }
            }

            var18.A = var19;
            ArrayList var21 = new ArrayList();

            for (bpc var30 : var18.wS) {
               int var35 = this.pC(var4, var30);
               bpc var39 = (bpc)var5.kS.get(var35);
               if (!var21.contains(var39)) {
                  var21.add(var39);
               }
            }

            var18.wS = var21;
            ArrayList var26 = new ArrayList();

            for (bpc var36 : var18.kS) {
               int var40 = this.pC(var4, var36);
               bpc var15 = (bpc)var5.kS.get(var40);
               if (!var26.contains(var15)) {
                  var26.add(var15);
               }
            }

            var18.kS = var26;
            ArrayList var32 = new ArrayList();

            for (bpc var41 : var18.UT) {
               int var42 = this.pC(var4, var41);
               bpc var16 = (bpc)var5.kS.get(var42);
               if (!var32.contains(var16)) {
                  var32.add(var16);
               }
            }

            var18.UT = var32;
         }

         var2 = var5;
      }
   }

   public List pC(boolean var1) {
      bpc var2 = (bpc)this.kS.get(0);
      HashMap var3 = new HashMap();

      for (bpc var5 : this.kS) {
         if (var5 == var2) {
            HashSet var6 = new HashSet();
            var6.add(var2);
            var3.put(var2, var6);
         } else {
            var3.put(var5, new HashSet(this.kS));
         }
      }

      boolean var12;
      do {
         var12 = false;

         for (bpc var15 : this.kS) {
            if (var15 != var2) {
               HashSet var7 = new HashSet();
               int var8 = 0;

               for (bpc var11 : var1 ? var15.kS() : var15.A) {
                  if (var8 == 0) {
                     var7.addAll((Collection)var3.get(var11));
                  } else {
                     var7.retainAll((Collection)var3.get(var11));
                  }

                  var8++;
               }

               var7.add(var15);
               if (var12) {
                  var3.put(var15, var7);
               } else if (!var7.equals(var3.get(var15))) {
                  var3.put(var15, var7);
                  var12 = true;
               }
            }
         }
      } while (var12);

      ArrayList var14 = new ArrayList();

      for (bpc var17 : this.kS) {
         TreeSet var18 = new TreeSet();

         for (bpc var20 : (Set)var3.get(var17)) {
            var18.add(var20.pC);
         }

         var14.add(var18);
      }

      return var14;
   }

   public List A(boolean var1) {
      List var2 = this.pC(var1);
      ArrayList var3 = new ArrayList();
      int var4 = 1;

      for (Set var6 : var2) {
         int var7 = -1;

         for (int var9 : var6) {
            if (var9 != var4) {
               if (var7 < 0) {
                  var7 = var9;
               } else if (((Set)var2.get(var9 - 1)).contains(var7)) {
                  var7 = var9;
               }
            }
         }

         var3.add(var7);
         var4++;
      }

      return var3;
   }

   public List E() {
      return this.kS(true);
   }

   public List kS(boolean var1) {
      return this.pC(var1, false, 0);
   }

   public List pC(boolean var1, boolean var2, int var3) {
      return this.A(var1, var2, var3);
   }

   public List A(boolean var1, boolean var2, int var3) {
      ArrayList var4 = new ArrayList();

      for (bpc var6 : this.kS) {
         if (var6.kS.isEmpty()) {
            var4.add(var6);
         }
      }

      bpc var17 = null;
      HashSet var18 = null;
      HashMap var7 = new HashMap();
      if (var2 && !var4.isEmpty() && var4.size() >= 2) {
         if (var3 <= 0 || var3 > this.kS.size()) {
            throw new IllegalArgumentException("Invalid loopback node id: " + var3);
         }

         var17 = (bpc)this.kS.get(var3 - 1);
         var18 = new HashSet();

         while (var4.size() > 1) {
            bpc var8 = (bpc)var4.remove(0);
            var18.add(var8);
            Maps.putMulti(var7, var8, var17);
         }
      }

      Map var19 = this.pC(var1, var7);

      boolean var9;
      do {
         var9 = false;

         for (bpc var11 : this.kS) {
            if (!var4.contains(var11)) {
               HashSet var12 = new HashSet();
               int var13 = 0;
               List var14 = var1 ? var11.A() : var11.pC();
               if (var17 != null && var18.contains(var11)) {
                  var14.add(var17);
               }

               for (bpc var16 : var14) {
                  if (var13 == 0) {
                     var12.addAll((Collection)var19.get(var16));
                  } else {
                     var12.retainAll((Collection)var19.get(var16));
                  }

                  var13++;
               }

               var12.add(var11);
               if (var9) {
                  var19.put(var11, var12);
               } else if (!var12.equals(var19.get(var11))) {
                  var19.put(var11, var12);
                  var9 = true;
               }
            }
         }
      } while (var9);

      ArrayList var20 = new ArrayList();

      for (bpc var22 : this.kS) {
         TreeSet var23 = new TreeSet();

         for (bpc var25 : (Set)var19.get(var22)) {
            var23.add(var25.pC);
         }

         var20.add(var23);
      }

      return var20;
   }

   public boolean pC(int var1, int var2) {
      return this.pC(this.pC(var1), this.pC(var2), new HashSet());
   }

   private boolean pC(bpc var1, bpc var2, Set var3) {
      if (var1 == var2) {
         return true;
      } else if (!var3.add(var1.pC)) {
         return false;
      } else {
         for (bpc var5 : var1.kS) {
            if (this.pC(var5, var2, var3)) {
               return true;
            }
         }

         for (bpc var7 : var1.UT) {
            if (this.pC(var7, var2, var3)) {
               return true;
            }
         }

         return false;
      }
   }

   public Set E(int var1) {
      return this.A(var1, 0);
   }

   public Set A(int var1, int var2) {
      return this.pC(var1, var2, false);
   }

   public Set pC(int var1, int var2, boolean var3) {
      TreeSet var4 = new TreeSet();
      ArrayList var5 = new ArrayList();
      HashSet var6 = new HashSet();
      var5.add(var1);

      while (!var5.isEmpty()) {
         for (int var8 : var5) {
            for (int var10 : this.pC(var8, var3)) {
               if (!var4.contains(var10) && (var2 <= 0 || var2 != var10)) {
                  var6.add(var10);
               }
            }
         }

         var4.addAll(var6);
         var5 = new ArrayList(var6);
         var6.clear();
      }

      return var4;
   }

   Map pC(boolean var1, Map var2) {
      TreeMap var3 = new TreeMap();

      for (bpc var5 : this.kS) {
         HashSet var6 = new HashSet();
         var6.add(var5);
         var3.put(var5, var6);
      }

      boolean var11;
      do {
         var11 = false;

         for (bpc var13 : this.kS) {
            HashSet var7 = new HashSet();
            var7.add(var13);
            List var8 = var1 ? var13.A() : var13.pC();
            if (var2 != null) {
               List var9 = (List)var2.get(var13);
               if (var9 != null) {
                  var8.addAll(var9);
               }
            }

            for (bpc var10 : var8) {
               var7.addAll((Collection)var3.get(var10));
            }

            if (var11) {
               var3.put(var13, var7);
            } else if (!var7.equals(var3.get(var13))) {
               var3.put(var13, var7);
               var11 = true;
            }
         }
      } while (var11);

      return var3;
   }

   public void sY(int var1) {
      bpc var2 = this.pC(var1);

      for (bpc var4 : var2.A) {
         if (!var4.kS.remove(var2)) {
            throw new IllegalStateException();
         }
      }

      var2.A.clear();
   }

   public List A(List var1) {
      return this.pC(true, false, false, var1);
   }

   private List pC(boolean var1, boolean var2, boolean var3, List var4) {
      List var5 = new bou.Av(var1, var2, var3, var4).pC();
      LinkedHashSet var6 = new LinkedHashSet(this.kS.size());

      for (int var7 = var5.size() - 1; var7 >= 0; var7--) {
         int var8 = ((bpc)var5.get(var7)).pC;
         var6.add(var8);
      }

      return new ArrayList(var6);
   }

   private class Av {
      private List A;
      private List kS;
      private List wS;
      private List UT = new ArrayList();
      private Set E = new HashSet();
      private Map sY = new HashMap();

      Av(boolean var2, boolean var3, boolean var4, List var5) {
         this.sY = var4 ? new HashMap() : null;

         for (bpc var7 : bou.this.kS) {
            var7.sY.clear();
         }

         this.A = bou.this.kS(var2);
         this.kS = new ArrayList(bou.this.kS.size());
         this.wS = new ArrayList(bou.this.kS.size());

         for (bpc var17 : bou.this.kS) {
            List var8 = var17.kS;
            if (var8.size() >= 2) {
               Set var9 = (Set)this.A.get(var17.pC - 1);
               ArrayList var10 = new ArrayList();
               ArrayList var11 = new ArrayList();

               for (bpc var13 : var8) {
                  if (var9.contains(var13.pC)) {
                     var10.add(var13);
                  } else {
                     var11.add(var13);
                  }
               }

               if (var8.size() == 2 && var10.isEmpty() && var11.size() == 2 && !var17.UT.isEmpty()) {
                  bpc var19 = this.pC(var17);
                  if (var19 != null) {
                     var10.add(var19);
                     var11.remove(var19);
                  }
               }

               if (var10.isEmpty() && var5 != null) {
                  bou.Sv var20 = (bou.Sv)var5.get(var17.pC - 1);
                  if (var20.pC != null && !var20.pC.isEmpty() && var20.pC.size() < var11.size()) {
                     int var22 = var11.size();
                     int var14 = 0;

                     while (var22-- >= 1) {
                        bpc var15 = (bpc)var11.get(var14);
                        if (var20.pC.contains(var15.pC)) {
                           var11.remove(var15);
                           var11.add(var15);
                        } else {
                           var14++;
                        }
                     }
                  }
               }

               bov var21 = new bov(this, bou.this, var17, var2);
               var10.sort(var21);
               if (var3 && var8.size() == 2 && var11.size() == 2) {
                  var11.sort(var21);
               }

               var8 = new ArrayList(var8.size());
               var8.addAll(var10);
               var8.addAll(var11);
            }

            this.kS.add(var8);
            if (!var2) {
               this.wS.add(Collections.emptyList());
            } else {
               var8 = var17.UT;
               this.wS.add(var8);
            }
         }
      }

      private bpc pC(bpc var1) {
         if (!var1.UT.isEmpty() && var1.kS.size() == 2) {
            bpc var2 = (bpc)var1.kS.get(0);
            bpc var3 = (bpc)var1.kS.get(1);
            ArrayDeque var4 = new ArrayDeque();
            var4.add(var2);
            ArrayDeque var5 = new ArrayDeque();
            var5.add(var3);
            HashSet var6 = new HashSet();

            while (!var4.isEmpty() || !var5.isEmpty()) {
               ArrayDeque var7 = new ArrayDeque();

               while (!var4.isEmpty()) {
                  bpc var8 = (bpc)var4.remove();
                  if (var6.add(var8)) {
                     if (!CollectionUtil.compare(var8.UT, var1.UT, false)) {
                        return var2;
                     }

                     var7.addAll(var8.kS);
                  }
               }

               var4 = var7;
               var7 = new ArrayDeque();

               while (!var5.isEmpty()) {
                  bpc var10 = (bpc)var5.remove();
                  if (var6.add(var10)) {
                     if (!CollectionUtil.compare(var10.UT, var1.UT, false)) {
                        return var3;
                     }

                     var7.addAll(var10.kS);
                  }
               }

               var5 = var7;
            }

            return null;
         } else {
            return null;
         }
      }

      List pC() {
         this.pC((bpc)bou.this.kS.get(0), null);
         return this.UT;
      }

      private void pC(bpc var1, bpc var2) {
         if (this.sY != null) {
            bpc var3 = (bpc)this.sY.get(var1);
            if (var3 != null) {
               if (var3 != var2) {
                  return;
               }

               this.sY.remove(var1);
            }
         }

         if (this.E.add(var1)) {
            List var7 = (List)this.kS.get(var1.pC - 1);
            List var4 = (List)this.wS.get(var1.pC - 1);
            if (this.sY != null) {
               var4.forEach(var2x -> this.sY.putIfAbsent(var2x, var1));
            }

            for (bpc var6 : var7) {
               this.pC(var6, var1);
            }

            for (bpc var9 : var4) {
               this.pC(var9, var1);
            }

            this.UT.add(var1);
         }
      }
   }

   public static class Sv {
      Set pC;

      public Sv(int var1) {
         if (var1 < 1) {
            throw new IllegalArgumentException();
         }
      }

      public void pC(int var1) {
         if (this.pC == null) {
            this.pC = new LinkedHashSet();
         }

         this.pC.add(var1);
      }
   }
}
