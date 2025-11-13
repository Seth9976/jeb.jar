package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.util.collect.ArrayList1;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class bhd {
   public static final bhd pC = new bhd();
   private com.pnfsoftware.jeb.corei.parsers.dex.vi A;
   private Map kS;

   private bhd() {
      this.kS = new HashMap();
   }

   public bhd(IDexUnit var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1;
         this.kS = this.pC();
      }
   }

   public bhe pC(int var1) {
      return (bhe)this.kS.get(var1);
   }

   private Map pC() {
      HashMap var1 = new HashMap();
      int var2 = this.A.ld().E("Ljava/lang/Object;");
      if (var2 < 0) {
         throw new IllegalStateException();
      } else {
         HashSet var3 = new HashSet();

         for (bfs var5 : this.A.getClasses()) {
            bhe var6 = new bhe(var5.getClassTypeIndex());
            var6.A = var5.getSuperTypeIndex();
            if (var6.A < 0) {
               var6.A = var2;
            }

            var3.add(var6.A);

            for (int var10 : var5.getInterfaceTypeIndexes()) {
               var6.kS.add(var10);
               var3.add(var10);
            }

            var1.put(var6.pC, var6);
         }

         for (int var14 : var3) {
            if (!var1.containsKey(var14)) {
               var1.put(var14, new bhe(var14));
            }
         }

         for (Entry var15 : var1.entrySet()) {
            int var17 = (Integer)var15.getKey();
            bhe var19 = (bhe)var15.getValue();
            bhe var22 = (bhe)var1.get(var19.A);
            if (var22 != null && var22 != var19) {
               var22.wS.add(var17);
            }

            for (int var29 : var19.kS) {
               var22 = (bhe)var1.get(var29);
               if (var22 != null && var22 != var19) {
                  var22.wS.add(var17);
               }
            }
         }

         for (bfs var16 : this.A.getClasses()) {
            bfp var18 = var16.sY();
            if (var18 != null) {
               for (bgd var24 : var18.kS()) {
                  bhe var27 = (bhe)var1.get(var16.getClassTypeIndex());
                  if (var27 != null) {
                     var27.UT.add(var24.getMethodIndex());
                  }
               }

               for (bfz var25 : var18.pC()) {
                  bhe var28 = (bhe)var1.get(var16.getClassTypeIndex());
                  if (var28 != null) {
                     var28.E.add(var25.getFieldIndex());
                  }
               }
            }
         }

         return var1;
      }
   }

   public bhd.Av pC(boolean var1, IDexMethod var2, boolean var3, boolean var4, boolean var5) {
      Object var6;
      if (var1) {
         var6 = new bhd.K(var2, var3, var5, var4);
         ((bhd.K)var6).kS();
      } else {
         var6 = new bhd.Sv(var2, var3);
         ((bhd.Sv)var6).wS();
      }

      return (bhd.Av)var6;
   }

   public interface Av {
      boolean pC();

      Collection A();
   }

   class K implements bhd.Av {
      IDexMethod pC;
      boolean A;
      boolean kS;
      boolean wS;
      int UT;
      int E;
      String sY;
      int[] ys;
      boolean ld;
      Map gp;
      Set oT;

      public K(IDexMethod var2, boolean var3, boolean var4, boolean var5) {
         if (var2 == null) {
            throw new IllegalArgumentException("Null method ref");
         } else {
            this.pC = var2;
            this.A = var3;
            this.kS = var4;
            this.wS = var5;
            this.UT = var2.getClassTypeIndex();
            this.E = var2.getIndex();
            this.sY = var2.getName(false);
            this.ys = var2.getPrototype().getParameterTypeIndexes();
         }
      }

      @Override
      public boolean pC() {
         if (this.gp == null) {
            throw new IllegalStateException();
         } else {
            return !this.ld && !this.oT.isEmpty();
         }
      }

      @Override
      public Collection A() {
         if (this.gp == null) {
            throw new IllegalStateException();
         } else {
            return this.oT;
         }
      }

      public void kS() {
         if (this.gp != null) {
            throw new IllegalStateException();
         } else {
            this.gp = new HashMap();
            this.oT = new HashSet();
            this.wS();
         }
      }

      private boolean wS() {
         int var1 = this.pC.getClassTypeIndex();
         bhe var2 = (bhe)bhd.this.kS.get(var1);
         if (var2 == null) {
            return false;
         } else {
            if (var2.pC()) {
               this.ld = true;
               if (this.A) {
                  return false;
               }
            }

            HashSet var3 = new HashSet();
            ArrayDeque var4 = new ArrayDeque();
            var4.add(var1);

            while (!var4.isEmpty()) {
               int var5 = (Integer)var4.remove();
               if (var3.add(var5)) {
                  bhe var6 = (bhe)bhd.this.kS.get(var5);
                  if (var6 != null) {
                     if (this.pC(var5) && this.wS && this.oT.size() >= 2) {
                        this.ld = true;
                        return false;
                     }

                     if (this.kS) {
                        break;
                     }

                     var4.addAll(var6.wS());
                  }
               }
            }

            return true;
         }
      }

      private boolean pC(int var1) {
         bhe var2 = (bhe)bhd.this.kS.get(var1);
         if (var2 == null) {
            return false;
         } else {
            if (var2.pC()) {
               this.ld = true;
               if (this.A) {
                  return false;
               }
            }

            HashSet var3 = new HashSet();
            ArrayDeque var4 = new ArrayDeque();
            var4.add(var1);

            while (!var4.isEmpty()) {
               int var5 = (Integer)var4.remove();
               if (var3.add(var5)) {
                  bhe var6 = (bhe)bhd.this.kS.get(var5);
                  if (var6 != null) {
                     Integer var7 = (Integer)this.gp.get(var5);
                     if (var7 != null) {
                        this.gp.put(var1, var7);
                        return false;
                     }

                     int var8 = this.pC(var6);
                     if (var8 >= 0 && bhd.this.A.sY(var8).isInternal()) {
                        this.gp.put(var1, var8);
                        return this.oT.add(var8);
                     }

                     var4.addAll(var6.UT());
                  }
               }
            }

            return false;
         }
      }

      private int pC(bhe var1) {
         if (this.UT == var1.pC) {
            return this.E;
         } else {
            for (int var3 : var1.UT) {
               bfu var4 = bhd.this.A.sY(var3);
               if (!var4.A().isPrivate() && !var4.A().isStatic()) {
                  String var5 = var4.getName(false);
                  if (this.sY.equals(var5)) {
                     int[] var6 = var4.UT().getParameterTypeIndexes();
                     if (Arrays.equals(this.ys, var6)) {
                        return var3;
                     }
                  }
               }
            }

            return -1;
         }
      }
   }

   class Sv implements bhd.Av {
      IDexMethod pC;
      boolean A;
      int kS;
      int wS;
      String UT;
      int[] E;
      boolean sY;
      Integer ys;

      public Sv(IDexMethod var2, boolean var3) {
         if (var2 == null) {
            throw new IllegalArgumentException("Null method ref");
         } else {
            this.pC = var2;
            this.A = var3;
            this.kS = var2.getClassTypeIndex();
            this.wS = var2.getIndex();
            this.UT = var2.getName(false);
            this.E = var2.getPrototype().getParameterTypeIndexes();
         }
      }

      @Override
      public boolean pC() {
         if (this.ys == null) {
            throw new IllegalStateException();
         } else {
            return !this.sY && this.kS() >= 0;
         }
      }

      @Override
      public Collection A() {
         int var1 = this.kS();
         if (var1 < 0) {
            return Collections.emptyList();
         } else {
            ArrayList1 var2 = new ArrayList1();
            var2.add(var1);
            return var2;
         }
      }

      public int kS() {
         if (this.ys == null) {
            throw new IllegalStateException();
         } else {
            return this.ys;
         }
      }

      public void wS() {
         if (this.ys != null) {
            throw new IllegalStateException();
         } else {
            this.ys = -1;
            this.UT();
         }
      }

      private boolean UT() {
         int var1 = this.pC.getClassTypeIndex();
         bhe var2 = (bhe)bhd.this.kS.get(var1);
         if (var2 == null) {
            return false;
         } else {
            if (var2.pC()) {
               this.sY = true;
               if (this.A) {
                  return false;
               }
            }

            HashSet var3 = new HashSet();
            ArrayDeque var4 = new ArrayDeque();
            var4.add(var1);

            while (!var4.isEmpty()) {
               int var5 = (Integer)var4.remove();
               if (var3.add(var5)) {
                  bhe var6 = (bhe)bhd.this.kS.get(var5);
                  if (var6 != null) {
                     int var7 = this.pC(var6);
                     if (var7 >= 0 && bhd.this.A.sY(var7).isInternal()) {
                        this.ys = var7;
                        return true;
                     }

                     var4.addAll(var6.UT());
                  }
               }
            }

            return false;
         }
      }

      private int pC(bhe var1) {
         if (this.kS == var1.pC) {
            return this.wS;
         } else {
            for (int var3 : var1.UT) {
               bfu var4 = bhd.this.A.sY(var3);
               if (var4.A().isStatic()) {
                  String var5 = var4.getName(false);
                  if (this.UT.equals(var5)) {
                     int[] var6 = var4.UT().getParameterTypeIndexes();
                     if (Arrays.equals(this.E, var6)) {
                        return var3;
                     }
                  }
               }
            }

            return -1;
         }
      }
   }
}
