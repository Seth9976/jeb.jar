package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.IntegerList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class aiw {
   private static final StructuredLogger A = aco.pC(aiw.class);
   public static int pC = 0;
   private List kS = new ArrayList();

   private aiw() {
   }

   public aiw(aiw var1) {
      this.kS = new ArrayList(var1.kS.size());
      IdentityHashMap var2 = new IdentityHashMap();

      for (ajb var4 : var1.kS) {
         ajb var5 = new ajb(var4.pC);
         var2.put(var4, var5);
         this.kS.add(var5);
      }

      for (ajb var9 : var1.kS) {
         ajb var10 = (ajb)var2.get(var9);

         for (ajb var7 : var9.kS) {
            var10.kS.add((ajb)var2.get(var7));
         }

         for (ajb var14 : var9.A) {
            var10.A.add((ajb)var2.get(var14));
         }

         for (ajb var15 : var9.UT) {
            var10.UT.add((ajb)var2.get(var15));
         }

         for (ajb var16 : var9.wS) {
            var10.wS.add((ajb)var2.get(var16));
         }
      }
   }

   public aiw(List var1, List var2) {
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

            this.kS.add(new ajb(var14));
         }

         for (int[] var23 : var1) {
            ajb var25 = this.pC(var23[0]);
            ajb var27 = this.pC(var23[1]);
            if (var25.kS.contains(var27)) {
               var27.A.contains(var25);
            }

            var25.kS.add(var27);
            var27.A.add(var25);
         }

         if (var2 != null) {
            for (int[] var24 : var2) {
               ajb var26 = this.pC(var24[0]);
               ajb var28 = this.pC(var24[1]);
               if (var26.UT.contains(var28)) {
                  var28.wS.contains(var26);
               }

               var26.UT.add(var28);
               var28.wS.add(var26);
            }
         }

         for (ajb var20 : this.kS) {
            if (var20.pC != 1 && var20.UT() == 0) {
               throw new RuntimeException("A node without predecessor is not the header: " + var20);
            }
         }

         if (!this.gp(var5)) {
            throw new RuntimeException("Fragmented Graph");
         }
      } else {
         this.kS.add(new ajb(1));
      }
   }

   private boolean gp(int var1) {
      HashSet var2 = new HashSet();
      this.pC((ajb)this.kS.get(0), var2);
      return var2.size() == var1;
   }

   private void pC(ajb var1, Set var2) {
      if (!var2.contains(var1)) {
         var2.add(var1);

         for (ajb var4 : var1.A()) {
            this.pC(var4, var2);
         }
      }
   }

   public Map pC() {
      return this.pC(this.oT());
   }

   public Map pC(List var1) {
      HashMap var2 = new HashMap();
      ArrayList var3 = new ArrayList();
      int var4 = 1;

      for (int var6 : var1) {
         ajb var7 = (ajb)this.kS.get(var6 - 1);
         var3.add(var7);
         var7.pC = var4++;
         var2.put(var7.pC, var6);
      }

      this.kS = var3;
      return var2;
   }

   ajb pC(int var1) {
      if (var1 > 0 && var1 <= this.kS.size()) {
         return (ajb)this.kS.get(var1 - 1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public List A() {
      ArrayList var1 = new ArrayList();

      for (ajb var3 : this.kS) {
         if (var3.kS.isEmpty()) {
            var1.add(var3.pC);
         }
      }

      return var1;
   }

   public int kS() {
      return this.kS.size();
   }

   public int wS() {
      int var1 = 0;

      for (ajb var3 : this.kS) {
         var1 += var3.wS();
      }

      return var1;
   }

   public List A(int var1) {
      ArrayList var2 = new ArrayList();

      for (ajb var4 : this.pC(var1).A) {
         var2.add(var4.pC);
      }

      return var2;
   }

   public List kS(int var1) {
      ArrayList var2 = new ArrayList();

      for (ajb var4 : this.pC(var1).wS) {
         var2.add(var4.pC);
      }

      return var2;
   }

   public List wS(int var1) {
      ArrayList var2 = new ArrayList();

      for (ajb var4 : this.pC(var1).A) {
         var2.add(var4.pC);
      }

      for (ajb var6 : this.pC(var1).wS) {
         var2.add(var6.pC);
      }

      return var2;
   }

   public List pC(int var1, boolean var2) {
      return !var2 ? this.UT(var1) : this.sY(var1);
   }

   public List UT(int var1) {
      ArrayList var2 = new ArrayList();

      for (ajb var4 : this.pC(var1).kS) {
         var2.add(var4.pC);
      }

      return var2;
   }

   public List E(int var1) {
      ArrayList var2 = new ArrayList();

      for (ajb var4 : this.pC(var1).UT) {
         var2.add(var4.pC);
      }

      return var2;
   }

   public List sY(int var1) {
      ArrayList var2 = new ArrayList();

      for (ajb var4 : this.pC(var1).kS) {
         var2.add(var4.pC);
      }

      for (ajb var6 : this.pC(var1).UT) {
         var2.add(var6.pC);
      }

      return var2;
   }

   public boolean UT() {
      return this.kS.size() == 1 && ((ajb)this.kS.get(0)).A.size() == 0 && ((ajb)this.kS.get(0)).kS.size() == 0;
   }

   public boolean E() {
      ajb var1 = (ajb)this.kS.get(0);
      if (this.kS.size() == 3 && var1.A.size() == 0 && var1.kS.size() == 2) {
         ajb var2 = (ajb)var1.kS.get(0);
         ajb var3 = (ajb)var1.kS.get(1);
         if (var2.A.size() == 2
            && var2.A.contains(var3)
            && var2.A.contains(var1)
            && var2.kS.size() == 1
            && var2.kS.contains(var3)
            && var3.A.size() == 2
            && var3.A.contains(var2)
            && var3.A.contains(var1)
            && var3.kS.size() == 1
            && var3.kS.contains(var2)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("(").append(this.kS.size()).append(", [");
      int var2 = 0;

      for (ajb var4 : this.kS) {
         for (ajb var6 : var4.kS) {
            if (var2++ > 0) {
               var1.append(", ");
            }

            var1.append(var4.pC).append(">").append(var6.pC);
         }

         for (ajb var8 : var4.UT) {
            if (var2++ > 0) {
               var1.append(", ");
            }

            var1.append(var4.pC).append("\\").append(var8.pC);
         }
      }

      var1.append("])");
      return var1.toString();
   }

   public aiy sY() {
      aiy var1 = new aiy();

      for (List var3 : this.fI()) {
         ArrayList var4 = new ArrayList();

         for (ajb var6 : var3) {
            var4.add(var6.pC);
         }

         var1.pC.add(var4);
      }

      return var1;
   }

   private List fI() {
      ajb var1 = (ajb)this.kS.get(0);
      ArrayList var2 = new ArrayList();
      var2.add(var1);
      ArrayList var3 = new ArrayList(this.kS);
      var3.remove(var1);
      ArrayList var4 = new ArrayList();

      while (!var2.isEmpty()) {
         ArrayList var5 = new ArrayList();
         var5.add((ajb)var2.remove(0));

         while (true) {
            ArrayList var6 = new ArrayList();

            for (ajb var8 : var3) {
               boolean var9 = true;

               for (ajb var11 : var8.kS()) {
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

               for (ajb var14 : var3) {
                  for (ajb var16 : var14.kS()) {
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

   private int pC(List var1, ajb var2) {
      int var3 = 0;

      for (List var5 : var1) {
         for (ajb var7 : var5) {
            if (var7 == var2) {
               return var3;
            }
         }

         var3++;
      }

      return -1;
   }

   public aiv ys() {
      aiv var1 = new aiv();
      aiw var2 = this;

      while (true) {
         aiy var3 = var2.sY();
         var1.pC.add(new aiv.Av(var2, var3));
         if (var3.pC() == var2.kS()) {
            return var1;
         }

         List var4 = var2.fI();
         aiw var5 = new aiw();
         int var6 = 1;

         for (List var8 : var4) {
            ajb var9 = new ajb(var6);
            var9.E = new ArrayList();
            ajb var10 = (ajb)var8.get(0);

            for (ajb var12 : var10.A) {
               if (!var8.contains(var12)) {
                  var9.A.add(var12);
               }
            }

            for (ajb var27 : var10.wS) {
               if (!var8.contains(var27)) {
                  var9.wS.add(var27);
               }
            }

            for (ajb var28 : var8) {
               for (ajb var14 : var28.kS) {
                  if (!var8.contains(var14) && !var9.kS.contains(var14)) {
                     var9.kS.add(var14);
                  }
               }

               for (ajb var38 : var28.UT) {
                  if (!var8.contains(var38) && !var9.UT.contains(var38)) {
                     var9.UT.add(var38);
                  }
               }
            }

            var5.kS.add(var9);
            var6++;
         }

         for (ajb var18 : var5.kS) {
            ArrayList var19 = new ArrayList();

            for (ajb var24 : var18.A) {
               int var29 = this.pC(var4, var24);
               ajb var34 = (ajb)var5.kS.get(var29);
               if (!var19.contains(var34)) {
                  var19.add(var34);
               }
            }

            var18.A = var19;
            ArrayList var21 = new ArrayList();

            for (ajb var30 : var18.wS) {
               int var35 = this.pC(var4, var30);
               ajb var39 = (ajb)var5.kS.get(var35);
               if (!var21.contains(var39)) {
                  var21.add(var39);
               }
            }

            var18.wS = var21;
            ArrayList var26 = new ArrayList();

            for (ajb var36 : var18.kS) {
               int var40 = this.pC(var4, var36);
               ajb var15 = (ajb)var5.kS.get(var40);
               if (!var26.contains(var15)) {
                  var26.add(var15);
               }
            }

            var18.kS = var26;
            ArrayList var32 = new ArrayList();

            for (ajb var41 : var18.UT) {
               int var42 = this.pC(var4, var41);
               ajb var16 = (ajb)var5.kS.get(var42);
               if (!var32.contains(var16)) {
                  var32.add(var16);
               }
            }

            var18.UT = var32;
         }

         var2 = var5;
      }
   }

   public List ld() {
      for (ajb var2 : this.kS) {
         var2.sY.clear();
      }

      ArrayList var5 = new ArrayList();
      this.pC((ajb)this.kS.get(0), var5);
      ArrayList var6 = new ArrayList();

      for (int var3 = var5.size() - 1; var3 >= 0; var3--) {
         int var4 = ((ajb)var5.get(var3)).pC;
         if (!var6.contains(var4)) {
            var6.add(var4);
         }
      }

      return var6;
   }

   private void pC(ajb var1, List var2) {
      if (!var2.contains(var1) || var1.sY.size() != var1.kS.size() + var1.UT.size()) {
         var2.add(var1);

         for (ajb var4 : var1.A()) {
            if (!var1.sY.contains(var4)) {
               var1.sY.add(var4);
               this.pC(var4, var2);
               var2.add(var1);
            }
         }
      }
   }

   public List pC(boolean var1) {
      ajb var2 = (ajb)this.kS.get(0);
      HashMap var3 = new HashMap();

      for (ajb var5 : this.kS) {
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

         for (ajb var15 : this.kS) {
            if (var15 != var2) {
               HashSet var7 = new HashSet();
               int var8 = 0;

               for (ajb var11 : var1 ? var15.kS() : var15.A) {
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

      for (ajb var17 : this.kS) {
         TreeSet var18 = new TreeSet();

         for (ajb var20 : (Set)var3.get(var17)) {
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

   public List gp() {
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

      for (ajb var6 : this.kS) {
         if (var6.kS.isEmpty()) {
            var4.add(var6);
         }
      }

      ajb var17 = null;
      HashSet var18 = null;
      HashMap var7 = new HashMap();
      if (var2 && !var4.isEmpty() && var4.size() >= 2) {
         if (var3 <= 0 || var3 > this.kS.size()) {
            throw new IllegalArgumentException("Invalid loopback node id: " + var3);
         }

         var17 = (ajb)this.kS.get(var3 - 1);
         var18 = new HashSet();

         while (var4.size() > 1) {
            ajb var8 = (ajb)var4.remove(0);
            var18.add(var8);
            Maps.putMulti(var7, var8, var17);
         }
      }

      Map var19 = this.pC(var1, var7);

      boolean var9;
      do {
         var9 = false;

         for (ajb var11 : this.kS) {
            if (!var4.contains(var11)) {
               HashSet var12 = new HashSet();
               int var13 = 0;
               List var14 = var1 ? var11.A() : var11.pC();
               if (var17 != null && var18.contains(var11)) {
                  var14.add(var17);
               }

               for (ajb var16 : var14) {
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

      for (ajb var22 : this.kS) {
         TreeSet var23 = new TreeSet();

         for (ajb var25 : (Set)var19.get(var22)) {
            var23.add(var25.pC);
         }

         var20.add(var23);
      }

      return var20;
   }

   public List oT() {
      Object[] var10000 = new Object[0];
      aiw var1 = this;
      ArrayList var2 = new ArrayList();
      int var3 = -1;

      while (true) {
         ajc var4 = new ajc(var1, var1.sY());
         var2.add(var4);
         if (var3 == 0 || var1.UT() || var1.E()) {
            ArrayList var31 = new ArrayList();
            if (var1.UT()) {
               var31.add(1);
            } else if (var1.E()) {
               var31.add(1);
               var31.add(2);
               var31.add(3);
            } else {
               if (var3 != 0) {
                  throw new RuntimeException("Unexpected case during graph reduction: " + var1);
               }

               for (int var32 = 0; var32 < var1.kS(); var32++) {
                  var31.add(var32 + 1);
               }
            }

            for (int var33 = var2.size() - 1; var33 >= 1; var33--) {
               ajc var34 = (ajc)var2.get(var33);
               int var35 = 0;

               while (var35 < var31.size()) {
                  int var37 = (Integer)var31.get(var35);
                  ajb var39 = (ajb)var34.pC.kS.get(var37 - 1);
                  List var41 = (List)var39.E;
                  this.pC(var31, var35, var41);
                  var35 += var41.size();
               }
            }

            return var31;
         }

         aiy var5 = var4.A;
         aiw var6 = new aiw();
         int var7 = 1;
         var3 = 0;

         for (List var9 : var5.pC) {
            int var10 = (Integer)var9.get(0);
            boolean var11 = false;

            for (int var13 : var1.wS(var10)) {
               if (var9.contains(var13)) {
                  var11 = true;
                  break;
               }
            }

            if (!var11 && var5.pC() > 1) {
               for (int var53 : var9) {
                  ajb var59 = new ajb(var7++);
                  var59.ys.addAll(var1.A(var53));
                  var59.ld.addAll(var1.UT(var53));
                  var59.gp.addAll(var1.kS(var53));
                  var59.oT.addAll(var1.E(var53));
                  var6.kS.add(var59);
                  var4.wS.put(var53, var59.pC);
                  var4.kS.put(var59.pC, IntegerList.buildListFromArray(new int[]{var53}));
                  var59.E = new ArrayList((Collection)var4.kS.get(var59.pC));
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
                  for (int var21 : var1.UT(var19)) {
                     if (var21 != var10 && var9.contains(var21)) {
                        var64.add(new int[]{(Integer)var46.get(var19), (Integer)var46.get(var21)});
                     } else if (var21 == var10) {
                        boolean var22 = var1.UT(var19).size() == 1;
                        var14 = (Integer)var46.get(var19);
                        var17.put(var14, var22 ? 1000 : 1);
                     }
                  }

                  for (int var70 : var1.E(var19)) {
                     if (var70 != var10 && var9.contains(var70)) {
                        var65.add(new int[]{(Integer)var46.get(var19), (Integer)var46.get(var70)});
                     }
                  }
               }

               aiw var66 = new aiw(var64, var65);
               if (!var17.isEmpty()) {
                  int var68 = (Integer)var46.get(var10);
                  List var71 = var66.sY(var68);
                  if (var71.size() == 2) {
                     int[] var74 = new int[2];
                     int var23 = 0;

                     for (int var25 : var71) {
                        for (int var27 : var17.keySet()) {
                           if (var66.pC(var25, var27)) {
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
                           var66 = new aiw(var64, var65);
                        }
                     }
                  }
               }

               List var69 = var66.ld();

               for (int var72 = 0; var72 < var69.size(); var72++) {
                  var69.set(var72, (Integer)var52.get(var69.get(var72)));
               }

               ajb var73 = new ajb(var7++);

               for (int var81 : var9) {
                  for (int var90 : var1.UT(var81)) {
                     if (!var9.contains(var90)) {
                        var73.ld.add(var90);
                     }
                  }
               }

               for (int var82 : var9) {
                  for (int var91 : var1.E(var82)) {
                     if (!var9.contains(var91)) {
                        var73.oT.add(var91);
                     }
                  }
               }

               for (int var83 : var1.A(var10)) {
                  if (!var9.contains(var83)) {
                     var73.ys.add(var83);
                  }
               }

               for (int var84 : var1.kS(var10)) {
                  if (!var9.contains(var84)) {
                     var73.gp.add(var84);
                  }
               }

               var6.kS.add(var73);

               for (int var85 : var9) {
                  var4.wS.put(var85, var73.pC);
               }

               var4.kS.put(var73.pC, new ArrayList(var9));
               var73.E = var69;
            }
         }

         aiw[] var36 = new aiw[]{var6, var1, var1, var6, var6, var6, var6, var1, var1, var1};

         for (ajb var40 : var6.kS) {
            for (int var48 : var40.ld) {
               int var54 = (Integer)var4.wS.get(var48);
               ajb var60 = (ajb)var6.kS.get(var54 - 1);
               if (!var40.kS.contains(var60)) {
                  var40.kS.add(var60);
               }
            }

            for (int var49 : var40.oT) {
               int var55 = (Integer)var4.wS.get(var49);
               ajb var61 = (ajb)var6.kS.get(var55 - 1);
               if (!var40.UT.contains(var61)) {
                  var40.UT.add(var61);
               }
            }

            for (int var50 : var40.ys) {
               int var56 = (Integer)var4.wS.get(var50);
               ajb var62 = (ajb)var6.kS.get(var56 - 1);
               if (!var40.A.contains(var62)) {
                  var40.A.add(var62);
               }
            }

            for (int var51 : var40.gp) {
               int var57 = (Integer)var4.wS.get(var51);
               ajb var63 = (ajb)var6.kS.get(var57 - 1);
               if (!var40.wS.contains(var63)) {
                  var40.wS.add(var63);
               }
            }
         }

         var1 = var36[pC];
      }
   }

   private void pC(List var1, int var2, List var3) {
      var1.remove(var2);

      for (int var4 = 0; var4 < var3.size(); var4++) {
         var1.add(var2 + var4, (Integer)var3.get(var4));
      }
   }

   public boolean pC(int var1, int var2) {
      for (ajb var4 : this.kS) {
         var4.sY.clear();
      }

      ArrayList var5 = new ArrayList();
      return this.pC(this.pC(var1), this.pC(var2), var5);
   }

   private boolean pC(ajb var1, ajb var2, List var3) {
      if (var3.contains(var1) && var1.sY.size() == var1.wS()) {
         return false;
      } else if (var1 == var2) {
         return true;
      } else {
         var3.add(var1);

         for (ajb var5 : var1.A()) {
            if (!var1.sY.contains(var5)) {
               var1.sY.add(var5);
               if (this.pC(var5, var2, var3)) {
                  return true;
               }

               var3.add(var1);
            }
         }

         return false;
      }
   }

   public Set ys(int var1) {
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

      for (ajb var5 : this.kS) {
         HashSet var6 = new HashSet();
         var6.add(var5);
         var3.put(var5, var6);
      }

      boolean var11;
      do {
         var11 = false;

         for (ajb var13 : this.kS) {
            HashSet var7 = new HashSet();
            var7.add(var13);
            List var8 = var1 ? var13.A() : var13.pC();
            if (var2 != null) {
               List var9 = (List)var2.get(var13);
               if (var9 != null) {
                  var8.addAll(var9);
               }
            }

            for (ajb var10 : var8) {
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

   public void ld(int var1) {
      ajb var2 = this.pC(var1);

      for (ajb var4 : var2.A) {
         if (!var4.kS.remove(var2)) {
            throw new IllegalStateException();
         }
      }

      var2.A.clear();
   }
}
