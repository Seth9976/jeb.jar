package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugInfo;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugVariable;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@Ser
public class bik implements IDexDebugInfo {
   @SerId(1)
   private int[] q;
   @SerId(2)
   private Map RF;
   @SerId(3)
   private Map xK;
   @SerId(4)
   private Map Dw;
   @SerId(5)
   private Map Uv;
   @SerId(6)
   private Set oW;
   @SerId(7)
   private Set gO;
   @SerId(8)
   private Map nf;
   @SerTransient
   private volatile SortedMap gP;

   private bik(int var1) {
      this.q = new int[var1];
      this.RF = new HashMap();
      this.xK = new HashMap();
      this.Dw = new HashMap();
      this.Uv = new HashMap();
      this.oW = new HashSet();
      this.gO = new HashSet();
      this.nf = new HashMap();
   }

   public bin q(int var1) {
      bin var2 = new bin();
      Integer var3 = (Integer)this.RF.get(var1);
      var2.q = var3 == null ? -1 : var3;
      var2.RF = (List)this.xK.get(var1);
      var2.xK = (List)this.Dw.get(var1);
      var2.Dw = (List)this.Uv.get(var1);
      var2.Uv = this.oW.contains(var1);
      var2.oW = this.gO.contains(var1);
      var3 = (Integer)this.nf.get(var1);
      var2.gO = var3 == null ? -1 : var3;
      return var2;
   }

   @Override
   public List getVariables(int var1) {
      List var2 = (List)this.xK.get(var1);
      return var2 == null ? Collections.emptyList() : var2;
   }

   @Override
   public int[] getParameterNameIndexes() {
      return this.q;
   }

   public static bik q(bjw var0, byte[] var1, int var2, int var3) {
      int[] var5 = new int[1];
      int var6 = DexUtil.bytearrayULEB128ToInt(var1, var2, var5);
      int var4 = var2 + var5[0];
      int var7 = DexUtil.bytearrayULEB128ToInt(var1, var4, var5);
      var4 += var5[0];
      bik var8 = new bik(var7);

      for (int var9 = 0; var9 < var7; var9++) {
         int var10 = DexUtil.bytearrayULEB128P1ToInt(var1, var4, var5);
         var8.q[var9] = var0.q(var10);
         var4 += var5[0];
      }

      int var25 = 0;
      int var26 = var6;
      int var11 = -2;
      boolean var12 = false;
      boolean var13 = false;

      while (true) {
         int var14 = var1[var4] & 255;
         var4++;
         if (var14 == 0) {
            return var8;
         }

         if (var14 == 1) {
            int var15 = DexUtil.bytearrayULEB128ToInt(var1, var4, var5);
            var4 += var5[0];
            var25 += var15;
         } else if (var14 == 2) {
            int var28 = DexUtil.bytearraySLEB128ToInt(var1, var4, var5);
            var4 += var5[0];
            var26 += var28;
         } else if (var14 == 3) {
            int var29 = DexUtil.bytearrayULEB128ToInt(var1, var4, var5);
            var4 += var5[0];
            int var16 = DexUtil.bytearrayULEB128P1ToInt(var1, var4, var5);
            var16 = var0.q(var16);
            var4 += var5[0];
            int var17 = DexUtil.bytearrayULEB128P1ToInt(var1, var4, var5);
            var17 = var0.RF(var17);
            var4 += var5[0];
            q(var8, new bio(var25, var29, var16, var17, -1));
         } else if (var14 == 4) {
            int var30 = DexUtil.bytearrayULEB128ToInt(var1, var4, var5);
            var4 += var5[0];
            int var36 = DexUtil.bytearrayULEB128P1ToInt(var1, var4, var5);
            var36 = var0.q(var36);
            var4 += var5[0];
            int var41 = DexUtil.bytearrayULEB128P1ToInt(var1, var4, var5);
            var41 = var0.RF(var41);
            var4 += var5[0];
            int var18 = DexUtil.bytearrayULEB128P1ToInt(var1, var4, var5);
            var18 = var0.q(var18);
            var4 += var5[0];
            q(var8, new bio(var25, var30, var36, var41, var18));
         } else if (var14 == 5) {
            int var31 = DexUtil.bytearrayULEB128ToInt(var1, var4, var5);
            var4 += var5[0];
            Object var38 = (List)var8.Dw.get(var25);
            if (var38 == null) {
               var38 = new ArrayList();
               var8.Dw.put(var25, var38);
            }

            var38.add(var31);
         } else if (var14 == 6) {
            int var32 = DexUtil.bytearrayULEB128ToInt(var1, var4, var5);
            var4 += var5[0];
            Object var39 = (List)var8.Uv.get(var25);
            if (var39 == null) {
               var39 = new ArrayList();
               var8.Uv.put(var25, var39);
            }

            var39.add(var32);
         } else if (var14 == 7) {
            var12 = true;
         } else if (var14 == 8) {
            var13 = true;
         } else if (var14 == 9) {
            int var33 = DexUtil.bytearrayULEB128P1ToInt(var1, var4, var5);
            var33 = var0.q(var33);
            var4 += var5[0];
            var11 = var33;
         } else {
            var14 -= 10;
            var26 += -4 + var14 % 15;
            var25 += var14 / 15;
            if (var25 < var3) {
               var8.RF.put(var25, var26);
            }

            if (var12) {
               var8.oW.add(var25);
               var12 = false;
            }

            if (var13) {
               var8.gO.add(var25);
               var13 = false;
            }

            if (var11 >= -1) {
               var8.nf.put(var25, var11);
               var11 = -2;
            }
         }
      }
   }

   private static void q(bik var0, bio var1) {
      Object var2 = (List)var0.xK.get(var1.getAddress());
      if (var2 == null) {
         var2 = new ArrayList();
         var0.xK.put(var1.getAddress(), var2);
      }

      var2.add(var1);
   }

   @Override
   public SortedMap getKnownVariablesMap(IDexMethod var1) {
      if (this.gP == null) {
         synchronized (this) {
            if (this.gP == null) {
               try {
                  SortedMap var3 = this.q(var1);
                  this.gP = var3;
               } catch (Exception var5) {
                  JebCoreService.notifySilentExceptionToClient(var5);
                  this.gP = Collections.emptySortedMap();
               }
            }
         }
      }

      return this.gP;
   }

   private SortedMap q(IDexMethod var1) {
      IDexCodeItem var2 = var1.getData().getCodeItem();
      Assert.a(var2.getDebugInfo() == this);
      int[] var3 = DexUtil.getMethodParameterIndices(var1);
      int var4 = var2.getInstructionsSize();
      ArrayList var5 = new ArrayList(var4 / 2);
      ArrayList var6 = new ArrayList();

      for (int var10 : var3) {
         var6.add(new bio(-1, var10, -1, -1, -1));
      }

      HashMap var16 = new HashMap();

      for (int var17 = 0; var17 < var4 / 2; var17++) {
         List var19 = (List)this.xK.getOrDefault(var17, Collections.emptyList());
         List var21 = (List)this.Dw.getOrDefault(var17, Collections.emptyList());
         List var11 = (List)this.Uv.getOrDefault(var17, Collections.emptyList());
         if (var19.isEmpty() && var21.isEmpty() && var11.isEmpty()) {
            var5.add(var6);
         } else {
            ArrayList var12 = new ArrayList(var6);
            if (!var21.isEmpty()) {
               for (int var14 : var21) {
                  bio var15 = this.q(var12, var14);
                  if (var15 == null) {
                     throw new IllegalStateException("Attempt to end a var that was not defined");
                  }

                  var16.put(var14, var15);
               }
            }

            for (int var26 : var11) {
               bio var28 = (bio)var16.remove(var26);
               if (var28 == null) {
                  throw new IllegalStateException("Attempt to restart a var that was not ended");
               }

               var12.add(var28);
            }

            for (IDexDebugVariable var27 : var19) {
               var12.add((bio)var27);
            }

            var5.add(var12);
            var6 = var12;
         }
      }

      TreeMap var18 = new TreeMap();

      for (IDalvikInstruction var22 : var1.getData().getCodeItem().getInstructions()) {
         int var23 = (int)var22.getOffset();
         var18.put(var23, Collections.unmodifiableList((List)var5.get(var23 / 2)));
      }

      return Collections.unmodifiableSortedMap(var18);
   }

   private bio q(Collection var1, int var2) {
      Iterator var3 = var1.iterator();

      while (var3.hasNext()) {
         bio var4 = (bio)var3.next();
         if (var4.RF == var2) {
            var3.remove();
            return var4;
         }
      }

      return null;
   }
}
