package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugInfo;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugVariable;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
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

@SerDisabled
public class bil implements IDexDebugInfo {
   private static final ILogger RF = GlobalLog.getLogger(bil.class);
   bjw q;
   private byte[] xK;
   private int Dw;
   private int Uv;
   private int oW;
   private int[] gO;
   private int nf;
   private int[] gP;
   private Map za;
   private Map lm;
   private Map zz;
   private Map JY;
   private Set HF;
   private Set LK;
   private Map io;
   private volatile SortedMap qa;

   public bil(bjw var1, byte[] var2, int var3, int var4, boolean var5) {
      this.q = var1;
      this.xK = var2;
      this.Dw = var4;
      this.oW = var3;
      this.gO = new int[1];
      if (var5) {
         this.q();
      }
   }

   private void q() {
      this.RF();
      this.xK();
   }

   private void RF() {
      if (this.Uv < 1) {
         if (this.Uv != 0) {
            throw new IllegalStateException();
         } else {
            this.Uv = 1;
            this.nf = 0;
            this.gP = ArrayUtil.NO_INT;
            this.nf = DexUtil.bytearrayULEB128ToInt(this.xK, this.oW, this.gO);
            this.oW = this.oW + this.gO[0];
            int var1 = DexUtil.bytearrayULEB128ToInt(this.xK, this.oW, this.gO);
            this.oW = this.oW + this.gO[0];
            this.gP = new int[var1];

            for (int var2 = 0; var2 < var1; var2++) {
               int var3 = DexUtil.bytearrayULEB128P1ToInt(this.xK, this.oW, this.gO);
               this.gP[var2] = this.q.q(var3);
               this.oW = this.oW + this.gO[0];
            }
         }
      }
   }

   private void xK() {
      if (this.Uv < 2) {
         if (this.Uv != 1) {
            throw new IllegalStateException();
         } else {
            this.Uv = 2;
            this.za = new HashMap();
            this.lm = new HashMap();
            this.zz = new HashMap();
            this.JY = new HashMap();
            this.HF = new HashSet();
            this.LK = new HashSet();
            this.io = new HashMap();
            int var1 = 0;
            int var2 = this.nf;
            int var3 = -2;
            boolean var4 = false;
            boolean var5 = false;

            while (true) {
               int var6 = this.xK[this.oW] & 255;
               this.oW++;
               if (var6 == 0) {
                  return;
               }

               if (var6 == 1) {
                  int var7 = DexUtil.bytearrayULEB128ToInt(this.xK, this.oW, this.gO);
                  this.oW = this.oW + this.gO[0];
                  var1 += var7;
               } else if (var6 == 2) {
                  int var12 = DexUtil.bytearraySLEB128ToInt(this.xK, this.oW, this.gO);
                  this.oW = this.oW + this.gO[0];
                  var2 += var12;
               } else if (var6 == 3) {
                  int var13 = DexUtil.bytearrayULEB128ToInt(this.xK, this.oW, this.gO);
                  this.oW = this.oW + this.gO[0];
                  int var8 = DexUtil.bytearrayULEB128P1ToInt(this.xK, this.oW, this.gO);
                  var8 = this.q.q(var8);
                  this.oW = this.oW + this.gO[0];
                  int var9 = DexUtil.bytearrayULEB128P1ToInt(this.xK, this.oW, this.gO);
                  var9 = this.q.RF(var9);
                  this.oW = this.oW + this.gO[0];
                  this.q(new bio(var1, var13, var8, var9, -1));
               } else if (var6 == 4) {
                  int var14 = DexUtil.bytearrayULEB128ToInt(this.xK, this.oW, this.gO);
                  this.oW = this.oW + this.gO[0];
                  int var20 = DexUtil.bytearrayULEB128P1ToInt(this.xK, this.oW, this.gO);
                  var20 = this.q.q(var20);
                  this.oW = this.oW + this.gO[0];
                  int var25 = DexUtil.bytearrayULEB128P1ToInt(this.xK, this.oW, this.gO);
                  var25 = this.q.RF(var25);
                  this.oW = this.oW + this.gO[0];
                  int var10 = DexUtil.bytearrayULEB128P1ToInt(this.xK, this.oW, this.gO);
                  var10 = this.q.q(var10);
                  this.oW = this.oW + this.gO[0];
                  this.q(new bio(var1, var14, var20, var25, var10));
               } else if (var6 == 5) {
                  int var15 = DexUtil.bytearrayULEB128ToInt(this.xK, this.oW, this.gO);
                  this.oW = this.oW + this.gO[0];
                  Object var22 = (List)this.zz.get(var1);
                  if (var22 == null) {
                     var22 = new ArrayList();
                     this.zz.put(var1, var22);
                  }

                  var22.add(var15);
               } else if (var6 == 6) {
                  int var16 = DexUtil.bytearrayULEB128ToInt(this.xK, this.oW, this.gO);
                  this.oW = this.oW + this.gO[0];
                  Object var23 = (List)this.JY.get(var1);
                  if (var23 == null) {
                     var23 = new ArrayList();
                     this.JY.put(var1, var23);
                  }

                  var23.add(var16);
               } else if (var6 == 7) {
                  var4 = true;
               } else if (var6 == 8) {
                  var5 = true;
               } else if (var6 == 9) {
                  int var17 = DexUtil.bytearrayULEB128P1ToInt(this.xK, this.oW, this.gO);
                  var17 = this.q.q(var17);
                  this.oW = this.oW + this.gO[0];
                  var3 = var17;
               } else {
                  var6 -= 10;
                  var2 += -4 + var6 % 15;
                  var1 += var6 / 15;
                  if (var1 < this.Dw) {
                     this.za.put(var1, var2);
                  }

                  if (var4) {
                     this.HF.add(var1);
                     var4 = false;
                  }

                  if (var5) {
                     this.LK.add(var1);
                     var5 = false;
                  }

                  if (var3 >= -1) {
                     this.io.put(var1, var3);
                     var3 = -2;
                  }
               }
            }
         }
      }
   }

   private void q(bio var1) {
      Object var2 = (List)this.lm.get(var1.getAddress());
      if (var2 == null) {
         var2 = new ArrayList();
         this.lm.put(var1.getAddress(), var2);
      }

      var2.add(var1);
   }

   public bin q(int var1) {
      this.q();
      bin var2 = new bin();
      Integer var3 = (Integer)this.za.get(var1);
      var2.q = var3 == null ? -1 : var3;
      var2.RF = (List)this.lm.get(var1);
      var2.xK = (List)this.zz.get(var1);
      var2.Dw = (List)this.JY.get(var1);
      var2.Uv = this.HF.contains(var1);
      var2.oW = this.LK.contains(var1);
      var3 = (Integer)this.io.get(var1);
      var2.gO = var3 == null ? -1 : var3;
      return var2;
   }

   @Override
   public List getVariables(int var1) {
      this.q();
      List var2 = (List)this.lm.get(var1);
      return var2 == null ? Collections.emptyList() : var2;
   }

   @Override
   public int[] getParameterNameIndexes() {
      this.RF();
      return this.gP;
   }

   @Override
   public SortedMap getKnownVariablesMap(IDexMethod var1) {
      if (this.qa == null) {
         synchronized (this) {
            if (this.qa == null) {
               try {
                  SortedMap var3 = this.q(var1);
                  this.qa = var3;
               } catch (Exception var5) {
                  JebCoreService.notifySilentExceptionToClient(var5);
                  this.qa = Collections.emptySortedMap();
               }
            }
         }
      }

      return this.qa;
   }

   private SortedMap q(IDexMethod var1) {
      this.q();
      IDexCodeItem var2 = var1.getData().getCodeItem();
      int[] var3 = DexUtil.getMethodParameterIndices(var1);
      int var4 = var2.getInstructionsSize();
      ArrayList var5 = new ArrayList(var4 / 2);
      ArrayList var6 = new ArrayList();

      for (int var10 : var3) {
         var6.add(new bio(-1, var10, -1, -1, -1));
      }

      HashMap var16 = new HashMap();

      for (int var17 = 0; var17 < var4 / 2; var17++) {
         List var19 = (List)this.lm.getOrDefault(var17, Collections.emptyList());
         List var21 = (List)this.zz.getOrDefault(var17, Collections.emptyList());
         List var11 = (List)this.JY.getOrDefault(var17, Collections.emptyList());
         if (var19.isEmpty() && var21.isEmpty() && var11.isEmpty()) {
            var5.add(var6);
         } else {
            ArrayList var12 = new ArrayList(var6);
            if (!var21.isEmpty()) {
               for (int var14 : var21) {
                  bio var15 = this.q(var12, var14);
                  if (var15 != null) {
                     var16.put(var14, var15);
                  }
               }
            }

            for (int var26 : var11) {
               bio var28 = (bio)var16.remove(var26);
               if (var28 != null) {
                  var12.add(var28);
               }
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
