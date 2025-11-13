package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.IntegerList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class akv {
   private static final StructuredLogger RF = aeg.q(akv.class);
   public static int q = 0;
   private List xK = new ArrayList();
   private boolean Dw;

   private akv() {
   }

   public akv(akv var1) {
      this.xK = new ArrayList(var1.xK.size());
      IdentityHashMap var2 = new IdentityHashMap();

      for (ald var4 : var1.xK) {
         ald var5 = new ald(var4.q);
         var2.put(var4, var5);
         this.xK.add(var5);
      }

      for (ald var9 : var1.xK) {
         ald var10 = (ald)var2.get(var9);

         for (ald var7 : var9.xK) {
            var10.xK.add((ald)var2.get(var7));
         }

         for (ald var14 : var9.RF) {
            var10.RF.add((ald)var2.get(var14));
         }

         for (ald var15 : var9.Uv) {
            var10.Uv.add((ald)var2.get(var15));
         }

         for (ald var16 : var9.Dw) {
            var10.Dw.add((ald)var2.get(var16));
         }
      }
   }

   public akv(int[][] var1) {
      this(var1, null);
   }

   public akv(List var1) {
      this(var1, null);
   }

   public akv(int[][] var1, int[][] var2) {
      this.q(var1, var2);
   }

   public akv(List var1, List var2) {
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

      this.q(var3, var6);
   }

   private void q(int[][] var1, int[][] var2) {
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

            this.xK.add(new ald(var14));
         }

         for (int[] var23 : var1) {
            ald var25 = this.q(var23[0]);
            ald var27 = this.q(var23[1]);
            if (var25.xK.contains(var27)) {
               var27.RF.contains(var25);
            }

            var25.xK.add(var27);
            var27.RF.add(var25);
         }

         if (var2 != null) {
            for (int[] var24 : var2) {
               ald var26 = this.q(var24[0]);
               ald var28 = this.q(var24[1]);
               if (var26.Uv.contains(var28)) {
                  var28.Dw.contains(var26);
               }

               var26.Uv.add(var28);
               var28.Dw.add(var26);
            }
         }

         for (ald var20 : this.xK) {
            if (var20.q != 1 && var20.nf() == 0) {
               throw new RuntimeException("A node without predecessor is not the header: " + var20);
            }
         }

         if (!this.zz(var5)) {
            throw new RuntimeException("Fragmented Graph");
         }
      } else {
         this.xK.add(new ald(1));
      }
   }

   private String q(int[][] var1) {
      StringBuilder var2 = new StringBuilder();

      for (int[] var6 : var1) {
         var2.append(var6[0]).append("-").append(var6[1]).append(",");
      }

      return var2.toString();
   }

   private boolean zz(int var1) {
      HashSet var2 = new HashSet();
      this.q((ald)this.xK.get(0), var2);
      return var2.size() == var1;
   }

   private void q(ald var1, Set var2) {
      if (!var2.contains(var1)) {
         var2.add(var1);

         for (ald var4 : var1.xK()) {
            this.q(var4, var2);
         }
      }
   }

   public boolean q(boolean var1) {
      if (this.Dw) {
         return true;
      } else {
         int var2 = 0;

         for (ald var4 : this.xK) {
            if (var4.xK.isEmpty()) {
               var2++;
            }
         }

         if (var2 != 0 && (var2 != 1 || !var1)) {
            ald var6 = new ald(this.xK.size() + 1);

            for (ald var5 : this.xK) {
               if (var5.xK.isEmpty()) {
                  var5.xK.add(var6);
                  var6.RF.add(var5);
               }
            }

            this.xK.add(var6);
            this.Dw = true;
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean q() {
      if (!this.Dw) {
         return true;
      } else {
         ald var1 = (ald)this.xK.get(this.xK.size() - 1);

         for (ald var3 : var1.RF) {
            var3.xK.remove(var1);
         }

         this.xK.remove(var1);
         this.Dw = false;
         return true;
      }
   }

   public boolean RF() {
      return this.Dw;
   }

   public Map xK() {
      return this.q(this.io());
   }

   public Map Dw() {
      return this.q(this.qa());
   }

   public Map q(List var1) {
      HashMap var2 = new HashMap();
      ArrayList var3 = new ArrayList();
      int var4 = 1;

      for (int var6 : var1) {
         ald var7 = (ald)this.xK.get(var6 - 1);
         var3.add(var7);
         var7.q = var4++;
         var2.put(var7.q, var6);
      }

      this.xK = var3;
      return var2;
   }

   ald q(int var1) {
      if (var1 > 0 && var1 <= this.xK.size()) {
         return (ald)this.xK.get(var1 - 1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public int Uv() {
      return 1;
   }

   public List oW() {
      ArrayList var1 = new ArrayList();

      for (ald var3 : this.xK) {
         if (var3.xK.isEmpty()) {
            var1.add(var3.q);
         }
      }

      return var1;
   }

   public int gO() {
      return this.xK.size();
   }

   public int nf() {
      int var1 = 0;

      for (ald var3 : this.xK) {
         var1 += var3.gO();
      }

      return var1;
   }

   public void q(int var1, Object var2) {
      if (var1 > 0 && var1 <= this.xK.size()) {
         ((ald)this.xK.get(var1 - 1)).oW = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Object RF(int var1) {
      if (var1 > 0 && var1 <= this.xK.size()) {
         return ((ald)this.xK.get(var1 - 1)).oW;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public List xK(int var1) {
      ArrayList var2 = new ArrayList();

      for (ald var4 : this.q(var1).RF) {
         var2.add(var4.q);
      }

      return var2;
   }

   public List Dw(int var1) {
      ArrayList var2 = new ArrayList();

      for (ald var4 : this.q(var1).Dw) {
         var2.add(var4.q);
      }

      return var2;
   }

   public List Uv(int var1) {
      ArrayList var2 = new ArrayList();

      for (ald var4 : this.q(var1).RF) {
         var2.add(var4.q);
      }

      for (ald var6 : this.q(var1).Dw) {
         var2.add(var6.q);
      }

      return var2;
   }

   public List q(int var1, boolean var2) {
      return !var2 ? this.oW(var1) : this.nf(var1);
   }

   public List oW(int var1) {
      ArrayList var2 = new ArrayList();

      for (ald var4 : this.q(var1).xK) {
         var2.add(var4.q);
      }

      return var2;
   }

   public List gO(int var1) {
      ArrayList var2 = new ArrayList();

      for (ald var4 : this.q(var1).Uv) {
         var2.add(var4.q);
      }

      return var2;
   }

   public List nf(int var1) {
      ArrayList var2 = new ArrayList();

      for (ald var4 : this.q(var1).xK) {
         var2.add(var4.q);
      }

      for (ald var6 : this.q(var1).Uv) {
         var2.add(var6.q);
      }

      return var2;
   }

   public boolean gP() {
      return this.xK.size() == 1 && ((ald)this.xK.get(0)).RF.size() == 0 && ((ald)this.xK.get(0)).xK.size() == 0;
   }

   public boolean za() {
      ald var1 = (ald)this.xK.get(0);
      if (this.xK.size() == 3 && var1.RF.size() == 0 && var1.xK.size() == 2) {
         ald var2 = (ald)var1.xK.get(0);
         ald var3 = (ald)var1.xK.get(1);
         if (var2.RF.size() == 2
            && var2.RF.contains(var3)
            && var2.RF.contains(var1)
            && var2.xK.size() == 1
            && var2.xK.contains(var3)
            && var3.RF.size() == 2
            && var3.RF.contains(var2)
            && var3.RF.contains(var1)
            && var3.xK.size() == 1
            && var3.xK.contains(var2)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("(").append(this.xK.size()).append(", [");
      int var2 = 0;

      for (ald var4 : this.xK) {
         for (ald var6 : var4.xK) {
            if (var2++ > 0) {
               var1.append(", ");
            }

            var1.append(var4.q).append(">").append(var6.q);
         }

         for (ald var8 : var4.Uv) {
            if (var2++ > 0) {
               var1.append(", ");
            }

            var1.append(var4.q).append("\\").append(var8.q);
         }
      }

      var1.append("])");
      return var1.toString();
   }

   public ala lm() {
      ala var1 = new ala();

      for (List var3 : this.Hk()) {
         ArrayList var4 = new ArrayList();

         for (ald var6 : var3) {
            var4.add(var6.q);
         }

         var1.q.add(var4);
      }

      return var1;
   }

   private List Hk() {
      ald var1 = (ald)this.xK.get(0);
      ArrayList var2 = new ArrayList();
      var2.add(var1);
      ArrayList var3 = new ArrayList(this.xK);
      var3.remove(var1);
      ArrayList var4 = new ArrayList();

      while (!var2.isEmpty()) {
         ArrayList var5 = new ArrayList();
         var5.add((ald)var2.remove(0));

         while (true) {
            ArrayList var6 = new ArrayList();

            for (ald var8 : var3) {
               boolean var9 = true;

               for (ald var11 : var8.oW()) {
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

               for (ald var14 : var3) {
                  for (ald var16 : var14.oW()) {
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

   private int q(List var1, ald var2) {
      int var3 = 0;

      for (List var5 : var1) {
         for (ald var7 : var5) {
            if (var7 == var2) {
               return var3;
            }
         }

         var3++;
      }

      return -1;
   }

   public aku zz() {
      aku var1 = new aku();
      akv var2 = this;

      while (true) {
         ala var3 = var2.lm();
         var1.q.add(new aku.eo(var2, var3));
         if (var3.q() == var2.gO()) {
            return var1;
         }

         List var4 = var2.Hk();
         akv var5 = new akv();
         int var6 = 1;

         for (List var8 : var4) {
            ald var9 = new ald(var6);
            var9.oW = new ArrayList();
            ald var10 = (ald)var8.get(0);

            for (ald var12 : var10.RF) {
               if (!var8.contains(var12)) {
                  var9.RF.add(var12);
               }
            }

            for (ald var27 : var10.Dw) {
               if (!var8.contains(var27)) {
                  var9.Dw.add(var27);
               }
            }

            for (ald var28 : var8) {
               for (ald var14 : var28.xK) {
                  if (!var8.contains(var14) && !var9.xK.contains(var14)) {
                     var9.xK.add(var14);
                  }
               }

               for (ald var38 : var28.Uv) {
                  if (!var8.contains(var38) && !var9.Uv.contains(var38)) {
                     var9.Uv.add(var38);
                  }
               }
            }

            var5.xK.add(var9);
            var6++;
         }

         for (ald var18 : var5.xK) {
            ArrayList var19 = new ArrayList();

            for (ald var24 : var18.RF) {
               int var29 = this.q(var4, var24);
               ald var34 = (ald)var5.xK.get(var29);
               if (!var19.contains(var34)) {
                  var19.add(var34);
               }
            }

            var18.RF = var19;
            ArrayList var21 = new ArrayList();

            for (ald var30 : var18.Dw) {
               int var35 = this.q(var4, var30);
               ald var39 = (ald)var5.xK.get(var35);
               if (!var21.contains(var39)) {
                  var21.add(var39);
               }
            }

            var18.Dw = var21;
            ArrayList var26 = new ArrayList();

            for (ald var36 : var18.xK) {
               int var40 = this.q(var4, var36);
               ald var15 = (ald)var5.xK.get(var40);
               if (!var26.contains(var15)) {
                  var26.add(var15);
               }
            }

            var18.xK = var26;
            ArrayList var32 = new ArrayList();

            for (ald var41 : var18.Uv) {
               int var42 = this.q(var4, var41);
               ald var16 = (ald)var5.xK.get(var42);
               if (!var32.contains(var16)) {
                  var32.add(var16);
               }
            }

            var18.Uv = var32;
         }

         var2 = var5;
      }
   }

   public List JY() {
      for (ald var2 : this.xK) {
         var2.gO.clear();
      }

      ArrayList var5 = new ArrayList();
      this.q((ald)this.xK.get(0), var5);
      ArrayList var6 = new ArrayList();

      for (int var3 = var5.size() - 1; var3 >= 0; var3--) {
         int var4 = ((ald)var5.get(var3)).q;
         if (!var6.contains(var4)) {
            var6.add(var4);
         }
      }

      return var6;
   }

   private void q(ald var1, List var2) {
      if (!var2.contains(var1) || var1.gO.size() != var1.xK.size() + var1.Uv.size()) {
         var2.add(var1);

         for (ald var4 : var1.xK()) {
            if (!var1.gO.contains(var4)) {
               var1.gO.add(var4);
               this.q(var4, var2);
               var2.add(var1);
            }
         }
      }
   }

   public List HF() {
      return this.RF(true);
   }

   public List RF(boolean var1) {
      ald var2 = (ald)this.xK.get(0);
      HashMap var3 = new HashMap();

      for (ald var5 : this.xK) {
         if (var5 == var2) {
            HashSet var6 = new HashSet();
            var6.add(var2);
            var3.put(var2, var6);
         } else {
            var3.put(var5, new HashSet(this.xK));
         }
      }

      boolean var12;
      do {
         var12 = false;

         for (ald var15 : this.xK) {
            if (var15 != var2) {
               HashSet var7 = new HashSet();
               int var8 = 0;

               for (ald var11 : var1 ? var15.oW() : var15.RF) {
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

      for (ald var17 : this.xK) {
         TreeSet var18 = new TreeSet();

         for (ald var20 : (Set)var3.get(var17)) {
            var18.add(var20.q);
         }

         var14.add(var18);
      }

      return var14;
   }

   public List xK(boolean var1) {
      List var2 = this.RF(var1);
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

   public List LK() {
      return this.Dw(true);
   }

   public List Dw(boolean var1) {
      return this.q(var1, false, 0);
   }

   public List q(boolean var1, boolean var2, int var3) {
      return this.xK(var1, var2, var3);
   }

   public List RF(boolean var1, boolean var2, int var3) {
      ArrayList var4 = new ArrayList();

      for (ald var6 : this.xK) {
         if (var6.xK.isEmpty()) {
            var4.add(var6);
         }
      }

      ald var16 = null;
      HashSet var17 = null;
      if (var2) {
         if (var4.isEmpty()) {
            var4.add((ald)this.xK.get(this.xK.size() - 1));
         } else if (var4.size() >= 2) {
            if (var3 <= 0 || var3 > this.xK.size()) {
               throw new IllegalArgumentException("Invalid loopback node id: " + var3);
            }

            var16 = (ald)this.xK.get(var3 - 1);
            var17 = new HashSet();

            while (var4.size() > 1) {
               ald var7 = (ald)var4.remove(0);
               var17.add(var7);
            }
         }
      }

      HashMap var18 = new HashMap();

      for (ald var9 : this.xK) {
         if (var4.contains(var9)) {
            HashSet var10 = new HashSet();
            var10.add(var9);
            var18.put(var9, var10);
         } else {
            var18.put(var9, new HashSet(this.xK));
         }
      }

      boolean var19;
      do {
         var19 = false;

         for (ald var22 : this.xK) {
            if (!var4.contains(var22)) {
               HashSet var11 = new HashSet();
               int var12 = 0;
               List var13 = var1 ? var22.xK() : var22.q();
               if (var16 != null && var17.contains(var22)) {
                  var13.add(var16);
               }

               for (ald var15 : var13) {
                  if (var12 == 0) {
                     var11.addAll((Collection)var18.get(var15));
                  } else {
                     var11.retainAll((Collection)var18.get(var15));
                  }

                  var12++;
               }

               var11.add(var22);
               if (var19) {
                  var18.put(var22, var11);
               } else if (!var11.equals(var18.get(var22))) {
                  var18.put(var22, var11);
                  var19 = true;
               }
            }
         }
      } while (var19);

      ArrayList var21 = new ArrayList();

      for (ald var24 : this.xK) {
         TreeSet var25 = new TreeSet();

         for (ald var27 : (Set)var18.get(var24)) {
            var25.add(var27.q);
         }

         var21.add(var25);
      }

      return var21;
   }

   public List xK(boolean var1, boolean var2, int var3) {
      ArrayList var4 = new ArrayList();

      for (ald var6 : this.xK) {
         if (var6.xK.isEmpty()) {
            var4.add(var6);
         }
      }

      ald var17 = null;
      HashSet var18 = null;
      HashMap var7 = new HashMap();
      if (var2 && !var4.isEmpty() && var4.size() >= 2) {
         if (var3 <= 0 || var3 > this.xK.size()) {
            throw new IllegalArgumentException("Invalid loopback node id: " + var3);
         }

         var17 = (ald)this.xK.get(var3 - 1);
         var18 = new HashSet();

         while (var4.size() > 1) {
            ald var8 = (ald)var4.remove(0);
            var18.add(var8);
            Maps.putMulti(var7, var8, var17);
         }
      }

      Map var19 = this.q(var1, var7);

      boolean var9;
      do {
         var9 = false;

         for (ald var11 : this.xK) {
            if (!var4.contains(var11)) {
               HashSet var12 = new HashSet();
               int var13 = 0;
               List var14 = var1 ? var11.xK() : var11.q();
               if (var17 != null && var18.contains(var11)) {
                  var14.add(var17);
               }

               for (ald var16 : var14) {
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

      for (ald var22 : this.xK) {
         TreeSet var23 = new TreeSet();

         for (ald var25 : (Set)var19.get(var22)) {
            var23.add(var25.q);
         }

         var20.add(var23);
      }

      return var20;
   }

   public List io() {
      Object[] var10000 = new Object[0];
      akv var1 = this;
      ArrayList var2 = new ArrayList();
      int var3 = -1;

      while (true) {
         ale var4 = new ale(var1, var1.lm());
         var2.add(var4);
         if (var3 == 0 || var1.gP() || var1.za()) {
            ArrayList var31 = new ArrayList();
            if (var1.gP()) {
               var31.add(1);
            } else if (var1.za()) {
               var31.add(1);
               var31.add(2);
               var31.add(3);
            } else {
               if (var3 != 0) {
                  throw new RuntimeException("Unexpected case during graph reduction: " + var1);
               }

               for (int var32 = 0; var32 < var1.gO(); var32++) {
                  var31.add(var32 + 1);
               }
            }

            for (int var33 = var2.size() - 1; var33 >= 1; var33--) {
               ale var34 = (ale)var2.get(var33);
               int var35 = 0;

               while (var35 < var31.size()) {
                  int var37 = (Integer)var31.get(var35);
                  ald var39 = (ald)var34.q.xK.get(var37 - 1);
                  List var41 = (List)var39.oW;
                  this.q(var31, var35, var41);
                  var35 += var41.size();
               }
            }

            return var31;
         }

         ala var5 = var4.RF;
         akv var6 = new akv();
         int var7 = 1;
         var3 = 0;

         for (List var9 : var5.q) {
            int var10 = (Integer)var9.get(0);
            boolean var11 = false;

            for (int var13 : var1.Uv(var10)) {
               if (var9.contains(var13)) {
                  var11 = true;
                  break;
               }
            }

            if (!var11 && var5.q() > 1) {
               for (int var53 : var9) {
                  ald var59 = new ald(var7++);
                  var59.nf.addAll(var1.xK(var53));
                  var59.gP.addAll(var1.oW(var53));
                  var59.za.addAll(var1.Dw(var53));
                  var59.lm.addAll(var1.gO(var53));
                  var6.xK.add(var59);
                  var4.Dw.put(var53, var59.q);
                  var4.xK.put(var59.q, IntegerList.buildListFromArray(new int[]{var53}));
                  var59.oW = new ArrayList((Collection)var4.xK.get(var59.q));
               }
            } else {
               var3++;
               HashMap var46 = new HashMap();
               HashMap var52 = new HashMap();
               int var14 = 1;

               for (int var16 : var9) {
                  var46.put(var16, var14);
                  var52.put(var14, var16);
                  var14++;
               }

               ArrayList var64 = new ArrayList();
               ArrayList var65 = new ArrayList();
               HashMap var17 = new HashMap();

               for (int var19 : var9) {
                  for (int var21 : var1.oW(var19)) {
                     if (var21 != var10 && var9.contains(var21)) {
                        var64.add(new int[]{(Integer)var46.get(var19), (Integer)var46.get(var21)});
                     } else if (var21 == var10) {
                        boolean var22 = var1.oW(var19).size() == 1;
                        var14 = (Integer)var46.get(var19);
                        var17.put(var14, var22 ? 1000 : 1);
                     }
                  }

                  for (int var70 : var1.gO(var19)) {
                     if (var70 != var10 && var9.contains(var70)) {
                        var65.add(new int[]{(Integer)var46.get(var19), (Integer)var46.get(var70)});
                     }
                  }
               }

               akv var66 = new akv(var64, var65);
               if (!var17.isEmpty()) {
                  int var68 = (Integer)var46.get(var10);
                  List var71 = var66.nf(var68);
                  if (var71.size() == 2) {
                     int[] var74 = new int[2];
                     int var23 = 0;

                     for (int var25 : var71) {
                        for (int var27 : var17.keySet()) {
                           if (var66.q(var25, var27)) {
                              var74[var23] += var17.get(var27);
                           }
                        }

                        var23++;
                     }

                     if (var74[0] != var74[1]) {
                        int var86 = var74[0] > var74[1] ? (Integer)var71.get(1) : (Integer)var71.get(0);
                        int[] var89 = new int[]{var68, var86};
                        int var92 = var74[0] > var74[1] ? (Integer)var71.get(0) : (Integer)var71.get(1);
                        int[] var93 = new int[]{var68, var92};
                        boolean var28 = false;
                        var23 = 0;

                        for (int[] var30 : var64) {
                           if (Arrays.equals(var30, var89)) {
                              break;
                           }

                           if (!var28 && Arrays.equals(var30, var93)) {
                              var28 = true;
                           }

                           var23++;
                        }

                        if (var28 && var23 < var64.size()) {
                           var64.remove(var23);
                           var64.add(0, var89);
                           var66 = new akv(var64, var65);
                        }
                     }
                  }
               }

               List var69 = var66.JY();

               for (int var72 = 0; var72 < var69.size(); var72++) {
                  var69.set(var72, (Integer)var52.get(var69.get(var72)));
               }

               ald var73 = new ald(var7++);

               for (int var81 : var9) {
                  for (int var90 : var1.oW(var81)) {
                     if (!var9.contains(var90)) {
                        var73.gP.add(var90);
                     }
                  }
               }

               for (int var82 : var9) {
                  for (int var91 : var1.gO(var82)) {
                     if (!var9.contains(var91)) {
                        var73.lm.add(var91);
                     }
                  }
               }

               for (int var83 : var1.xK(var10)) {
                  if (!var9.contains(var83)) {
                     var73.nf.add(var83);
                  }
               }

               for (int var84 : var1.Dw(var10)) {
                  if (!var9.contains(var84)) {
                     var73.za.add(var84);
                  }
               }

               var6.xK.add(var73);

               for (int var85 : var9) {
                  var4.Dw.put(var85, var73.q);
               }

               var4.xK.put(var73.q, new ArrayList(var9));
               var73.oW = var69;
            }
         }

         akv[] var36 = new akv[]{var6, var1, var1, var6, var6, var6, var6, var1, var1, var1};

         for (ald var40 : var6.xK) {
            for (int var48 : var40.gP) {
               int var54 = (Integer)var4.Dw.get(var48);
               ald var60 = (ald)var6.xK.get(var54 - 1);
               if (!var40.xK.contains(var60)) {
                  var40.xK.add(var60);
               }
            }

            for (int var49 : var40.lm) {
               int var55 = (Integer)var4.Dw.get(var49);
               ald var61 = (ald)var6.xK.get(var55 - 1);
               if (!var40.Uv.contains(var61)) {
                  var40.Uv.add(var61);
               }
            }

            for (int var50 : var40.nf) {
               int var56 = (Integer)var4.Dw.get(var50);
               ald var62 = (ald)var6.xK.get(var56 - 1);
               if (!var40.RF.contains(var62)) {
                  var40.RF.add(var62);
               }
            }

            for (int var51 : var40.za) {
               int var57 = (Integer)var4.Dw.get(var51);
               ald var63 = (ald)var6.xK.get(var57 - 1);
               if (!var40.Dw.contains(var63)) {
                  var40.Dw.add(var63);
               }
            }
         }

         var1 = var36[q];
      }
   }

   private void q(List var1, int var2, List var3) {
      var1.remove(var2);

      for (int var4 = 0; var4 < var3.size(); var4++) {
         var1.add(var2 + var4, (Integer)var3.get(var4));
      }
   }

   public boolean q(int var1, int var2) {
      for (ald var4 : this.xK) {
         var4.gO.clear();
      }

      ArrayList var5 = new ArrayList();
      return this.q(this.q(var1), this.q(var2), var5);
   }

   private boolean q(ald var1, ald var2, List var3) {
      if (var3.contains(var1) && var1.gO.size() == var1.gO()) {
         return false;
      } else if (var1 == var2) {
         return true;
      } else {
         var3.add(var1);

         for (ald var5 : var1.xK()) {
            if (!var1.gO.contains(var5)) {
               var1.gO.add(var5);
               if (this.q(var5, var2, var3)) {
                  return true;
               }

               var3.add(var1);
            }
         }

         return false;
      }
   }

   public Set gP(int var1) {
      return this.RF(var1, 0);
   }

   public Set RF(int var1, int var2) {
      return this.q(var1, var2, false);
   }

   public Set q(int var1, int var2, boolean var3) {
      TreeSet var4 = new TreeSet();
      ArrayList var5 = new ArrayList();
      HashSet var6 = new HashSet();
      var5.add(var1);

      while (!var5.isEmpty()) {
         for (int var8 : var5) {
            for (int var10 : this.q(var8, var3)) {
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

   Map q(boolean var1, Map var2) {
      TreeMap var3 = new TreeMap();

      for (ald var5 : this.xK) {
         HashSet var6 = new HashSet();
         var6.add(var5);
         var3.put(var5, var6);
      }

      boolean var11;
      do {
         var11 = false;

         for (ald var13 : this.xK) {
            HashSet var7 = new HashSet();
            var7.add(var13);
            List var8 = var1 ? var13.xK() : var13.q();
            if (var2 != null) {
               List var9 = (List)var2.get(var13);
               if (var9 != null) {
                  var8.addAll(var9);
               }
            }

            for (ald var10 : var8) {
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

   public List Uv(boolean var1) {
      Map var2 = this.q(var1, null);
      ArrayList var3 = new ArrayList();

      for (ald var5 : this.xK) {
         TreeSet var6 = new TreeSet();

         for (ald var8 : (Set)var2.get(var5)) {
            var6.add(var8.q);
         }

         var3.add(var6);
      }

      return var3;
   }

   public void za(int var1) {
      ald var2 = this.q(var1);

      for (ald var4 : var2.RF) {
         if (!var4.xK.remove(var2)) {
            throw new IllegalStateException();
         }
      }

      var2.RF.clear();
   }

   public void lm(int var1) {
      ald var2 = this.q(var1);

      for (ald var4 : var2.xK) {
         if (!var4.RF.remove(var2)) {
            throw new IllegalStateException();
         }
      }

      var2.xK.clear();
   }

   public List qa() {
      return this.q(true, false);
   }

   public List q(boolean var1, boolean var2) {
      List var3 = new akv.eo(var1, var2, false).q();
      ArrayList var4 = new ArrayList(this.xK.size());

      for (int var5 = var3.size() - 1; var5 >= 0; var5--) {
         int var6 = ((ald)var3.get(var5)).q;
         if (!var4.contains(var6)) {
            var4.add(var6);
         }
      }

      return var4;
   }

   private class eo {
      private List RF;
      private List xK;
      private List Dw;
      private List Uv = new ArrayList();
      private Set oW = new HashSet();
      private Map gO = new HashMap();

      eo(boolean var2, boolean var3, boolean var4) {
         this.gO = var4 ? new HashMap() : null;

         for (ald var6 : akv.this.xK) {
            var6.gO.clear();
         }

         this.RF = akv.this.Dw(var2);
         this.xK = new ArrayList(akv.this.xK.size());
         this.Dw = new ArrayList(akv.this.xK.size());

         for (ald var16 : akv.this.xK) {
            ArrayList var7 = new ArrayList(2);

            for (Object var9 : Arrays.asList(var16.xK, var2 ? var16.Uv : Collections.EMPTY_LIST)) {
               if (var9.size() >= 2) {
                  Set var10 = (Set)this.RF.get(var16.q - 1);
                  ArrayList var11 = new ArrayList();
                  ArrayList var12 = new ArrayList();

                  for (ald var14 : var9) {
                     if (var10.contains(var14.q)) {
                        var11.add(var14);
                     } else {
                        var12.add(var14);
                     }
                  }

                  akw var17 = new akw(this, akv.this, var16, var2);
                  var11.sort(var17);
                  if (var3 && var9.size() == 2 && var12.size() == 2) {
                     var12.sort(var17);
                  }

                  var9 = new ArrayList(var9.size());
                  var9.addAll(var11);
                  var9.addAll(var12);
               }

               var7.add(var9);
            }

            this.xK.add((List)var7.get(0));
            this.Dw.add((List)var7.get(1));
         }
      }

      List q() {
         this.q((ald)akv.this.xK.get(0), null);
         return this.Uv;
      }

      private void q(ald var1, ald var2) {
         if (this.gO != null) {
            ald var3 = (ald)this.gO.get(var1);
            if (var3 != null) {
               if (var3 != var2) {
                  return;
               }

               this.gO.remove(var1);
            }
         }

         if (this.oW.add(var1)) {
            List var7 = (List)this.xK.get(var1.q - 1);
            List var4 = (List)this.Dw.get(var1.q - 1);
            if (this.gO != null) {
               var4.forEach(var2x -> this.gO.putIfAbsent(var2x, var1));
            }

            for (ald var6 : var7) {
               this.q(var6, var1);
            }

            for (ald var9 : var4) {
               this.q(var9, var1);
            }

            this.Uv.add(var1);
         }
      }
   }
}
