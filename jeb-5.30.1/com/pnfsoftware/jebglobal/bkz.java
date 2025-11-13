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

public class bkz {
   public static final bkz q = new bkz();
   private com.pnfsoftware.jeb.corei.parsers.dex.bK RF;
   private Map xK;

   private bkz() {
      this.xK = new HashMap();
   }

   public bkz(IDexUnit var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = (com.pnfsoftware.jeb.corei.parsers.dex.bK)var1;
         this.xK = this.q();
      }
   }

   public bla q(int var1) {
      return (bla)this.xK.get(var1);
   }

   public boolean q(int var1, int var2) {
      ArrayDeque var3 = new ArrayDeque();
      var3.add(var1);
      HashSet var4 = new HashSet();

      while (!var3.isEmpty()) {
         int var5 = (Integer)var3.remove();
         bla var6 = (bla)this.xK.get(var5);
         if (var6 != null) {
            for (int var9 : var6.oW()) {
               if (var2 == var9) {
                  return true;
               }

               if (var4.add(var9)) {
                  var3.add(var9);
               }
            }
         }
      }

      return false;
   }

   private Map q() {
      HashMap var1 = new HashMap();
      int var2 = this.RF.io().oW("Ljava/lang/Object;");
      if (var2 < 0) {
         throw new IllegalStateException();
      } else {
         HashSet var3 = new HashSet();

         for (bjn var5 : this.RF.getClasses()) {
            bla var6 = new bla(var5.getClassTypeIndex());
            var6.RF = var5.getSuperTypeIndex();
            if (var6.RF < 0) {
               var6.RF = var2;
            }

            var3.add(var6.RF);

            for (int var10 : var5.getInterfaceTypeIndexes()) {
               var6.xK.add(var10);
               var3.add(var10);
            }

            var1.put(var6.q, var6);
         }

         for (int var14 : var3) {
            if (!var1.containsKey(var14)) {
               var1.put(var14, new bla(var14));
            }
         }

         for (Entry var15 : var1.entrySet()) {
            int var17 = (Integer)var15.getKey();
            bla var19 = (bla)var15.getValue();
            bla var22 = (bla)var1.get(var19.RF);
            if (var22 != null && var22 != var19) {
               var22.Dw.add(var17);
            }

            for (int var29 : var19.xK) {
               var22 = (bla)var1.get(var29);
               if (var22 != null && var22 != var19) {
                  var22.Dw.add(var17);
               }
            }
         }

         for (bjn var16 : this.RF.getClasses()) {
            bjk var18 = var16.gP();
            if (var18 != null) {
               for (bjy var24 : var18.xK()) {
                  bla var27 = (bla)var1.get(var16.getClassTypeIndex());
                  if (var27 != null) {
                     var27.Uv.add(var24.getMethodIndex());
                  }
               }

               for (bju var25 : var18.q()) {
                  bla var28 = (bla)var1.get(var16.getClassTypeIndex());
                  if (var28 != null) {
                     var28.oW.add(var25.getFieldIndex());
                  }
               }
            }
         }

         return var1;
      }
   }

   public bkz.eo q(boolean var1, IDexMethod var2, boolean var3, boolean var4, boolean var5) {
      Object var6;
      if (var1) {
         var6 = new bkz.nI(var2, var3, var5, var4);
         ((bkz.nI)var6).xK();
      } else {
         var6 = new bkz.CU(var2, var3);
         ((bkz.CU)var6).Dw();
      }

      return (bkz.eo)var6;
   }

   class CU implements bkz.eo {
      IDexMethod q;
      boolean RF;
      int xK;
      int Dw;
      String Uv;
      int[] oW;
      boolean gO;
      Integer nf;

      public CU(IDexMethod var2, boolean var3) {
         if (var2 == null) {
            throw new IllegalArgumentException("Null method ref");
         } else {
            this.q = var2;
            this.RF = var3;
            this.xK = var2.getClassTypeIndex();
            this.Dw = var2.getIndex();
            this.Uv = var2.getName(false);
            this.oW = var2.getPrototype().getParameterTypeIndexes();
         }
      }

      @Override
      public boolean q() {
         if (this.nf == null) {
            throw new IllegalStateException();
         } else {
            return !this.gO && this.xK() >= 0;
         }
      }

      @Override
      public Collection RF() {
         int var1 = this.xK();
         if (var1 < 0) {
            return Collections.emptyList();
         } else {
            ArrayList1 var2 = new ArrayList1();
            var2.add(var1);
            return var2;
         }
      }

      public int xK() {
         if (this.nf == null) {
            throw new IllegalStateException();
         } else {
            return this.nf;
         }
      }

      public void Dw() {
         if (this.nf != null) {
            throw new IllegalStateException();
         } else {
            this.nf = -1;
            this.Uv();
         }
      }

      private boolean Uv() {
         int var1 = this.q.getClassTypeIndex();
         bla var2 = (bla)bkz.this.xK.get(var1);
         if (var2 == null) {
            return false;
         } else {
            if (var2.q()) {
               this.gO = true;
               if (this.RF) {
                  return false;
               }
            }

            HashSet var3 = new HashSet();
            ArrayDeque var4 = new ArrayDeque();
            var4.add(var1);

            while (!var4.isEmpty()) {
               int var5 = (Integer)var4.remove();
               if (var3.add(var5)) {
                  bla var6 = (bla)bkz.this.xK.get(var5);
                  if (var6 != null) {
                     int var7 = this.q(var6);
                     if (var7 >= 0 && bkz.this.RF.gO(var7).isInternal()) {
                        this.nf = var7;
                        return true;
                     }

                     var4.addAll(var6.oW());
                  }
               }
            }

            return false;
         }
      }

      private int q(bla var1) {
         if (this.xK == var1.q) {
            return this.Dw;
         } else {
            for (int var3 : var1.Uv) {
               bjp var4 = bkz.this.RF.gO(var3);
               if (var4.RF().isStatic()) {
                  String var5 = var4.getName(false);
                  if (this.Uv.equals(var5)) {
                     int[] var6 = var4.Uv().getParameterTypeIndexes();
                     if (Arrays.equals(this.oW, var6)) {
                        return var3;
                     }
                  }
               }
            }

            return -1;
         }
      }
   }

   public interface eo {
      boolean q();

      Collection RF();
   }

   class nI implements bkz.eo {
      IDexMethod q;
      boolean RF;
      boolean xK;
      boolean Dw;
      int Uv;
      int oW;
      String gO;
      int[] nf;
      boolean gP;
      Map za;
      Set lm;

      public nI(IDexMethod var2, boolean var3, boolean var4, boolean var5) {
         if (var2 == null) {
            throw new IllegalArgumentException("Null method ref");
         } else {
            this.q = var2;
            this.RF = var3;
            this.xK = var4;
            this.Dw = var5;
            this.Uv = var2.getClassTypeIndex();
            this.oW = var2.getIndex();
            this.gO = var2.getName(false);
            this.nf = var2.getPrototype().getParameterTypeIndexes();
         }
      }

      @Override
      public boolean q() {
         if (this.za == null) {
            throw new IllegalStateException();
         } else {
            return !this.gP && !this.lm.isEmpty();
         }
      }

      @Override
      public Collection RF() {
         if (this.za == null) {
            throw new IllegalStateException();
         } else {
            return this.lm;
         }
      }

      public void xK() {
         if (this.za != null) {
            throw new IllegalStateException();
         } else {
            this.za = new HashMap();
            this.lm = new HashSet();
            this.Dw();
         }
      }

      private boolean Dw() {
         int var1 = this.q.getClassTypeIndex();
         bla var2 = (bla)bkz.this.xK.get(var1);
         if (var2 == null) {
            return false;
         } else {
            if (var2.q()) {
               this.gP = true;
               if (this.RF) {
                  return false;
               }
            }

            HashSet var3 = new HashSet();
            ArrayDeque var4 = new ArrayDeque();
            var4.add(var1);

            while (!var4.isEmpty()) {
               int var5 = (Integer)var4.remove();
               if (var3.add(var5)) {
                  bla var6 = (bla)bkz.this.xK.get(var5);
                  if (var6 != null) {
                     if (this.q(var5) && this.Dw && this.lm.size() >= 2) {
                        this.gP = true;
                        return false;
                     }

                     if (this.xK) {
                        break;
                     }

                     var4.addAll(var6.Uv());
                  }
               }
            }

            return true;
         }
      }

      private boolean q(int var1) {
         bla var2 = (bla)bkz.this.xK.get(var1);
         if (var2 == null) {
            return false;
         } else {
            if (var2.q()) {
               this.gP = true;
               if (this.RF) {
                  return false;
               }
            }

            HashSet var3 = new HashSet();
            ArrayDeque var4 = new ArrayDeque();
            var4.add(var1);

            while (!var4.isEmpty()) {
               int var5 = (Integer)var4.remove();
               if (var3.add(var5)) {
                  bla var6 = (bla)bkz.this.xK.get(var5);
                  if (var6 != null) {
                     Integer var7 = (Integer)this.za.get(var5);
                     if (var7 != null) {
                        this.za.put(var1, var7);
                        return false;
                     }

                     int var8 = this.q(var6);
                     if (var8 >= 0 && bkz.this.RF.gO(var8).isInternal()) {
                        this.za.put(var1, var8);
                        return this.lm.add(var8);
                     }

                     var4.addAll(var6.oW());
                  }
               }
            }

            return false;
         }
      }

      private int q(bla var1) {
         if (this.Uv == var1.q) {
            return this.oW;
         } else {
            for (int var3 : var1.Uv) {
               bjp var4 = bkz.this.RF.gO(var3);
               if (!var4.RF().isPrivate() && !var4.RF().isStatic()) {
                  String var5 = var4.getName(false);
                  if (this.gO.equals(var5)) {
                     int[] var6 = var4.Uv().getParameterTypeIndexes();
                     if (Arrays.equals(this.nf, var6)) {
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
