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
public class ber implements IDexDebugInfo {
   private static final ILogger A = GlobalLog.getLogger(ber.class);
   bgb pC;
   private byte[] kS;
   private int wS;
   private int UT;
   private int E;
   private int[] sY;
   private int ys;
   private int[] ld;
   private Map gp;
   private Map oT;
   private Map fI;
   private Map WR;
   private Set NS;
   private Set vP;
   private Map xC;
   private volatile SortedMap ED;

   public ber(bgb var1, byte[] var2, int var3, int var4, boolean var5) {
      this.pC = var1;
      this.kS = var2;
      this.wS = var4;
      this.E = var3;
      this.sY = new int[1];
      if (var5) {
         this.pC();
      }
   }

   private void pC() {
      this.A();
      this.kS();
   }

   private void A() {
      if (this.UT < 1) {
         if (this.UT != 0) {
            throw new IllegalStateException();
         } else {
            this.UT = 1;
            this.ys = 0;
            this.ld = ArrayUtil.NO_INT;
            this.ys = DexUtil.bytearrayULEB128ToInt(this.kS, this.E, this.sY);
            this.E = this.E + this.sY[0];
            int var1 = DexUtil.bytearrayULEB128ToInt(this.kS, this.E, this.sY);
            this.E = this.E + this.sY[0];
            this.ld = new int[var1];

            for (int var2 = 0; var2 < var1; var2++) {
               int var3 = DexUtil.bytearrayULEB128P1ToInt(this.kS, this.E, this.sY);
               this.ld[var2] = this.pC.pC(var3);
               this.E = this.E + this.sY[0];
            }
         }
      }
   }

   private void kS() {
      if (this.UT < 2) {
         if (this.UT != 1) {
            throw new IllegalStateException();
         } else {
            this.UT = 2;
            this.gp = new HashMap();
            this.oT = new HashMap();
            this.fI = new HashMap();
            this.WR = new HashMap();
            this.NS = new HashSet();
            this.vP = new HashSet();
            this.xC = new HashMap();
            int var1 = 0;
            int var2 = this.ys;
            int var3 = -2;
            boolean var4 = false;
            boolean var5 = false;

            while (true) {
               int var6 = this.kS[this.E] & 255;
               this.E++;
               if (var6 == 0) {
                  return;
               }

               if (var6 == 1) {
                  int var7 = DexUtil.bytearrayULEB128ToInt(this.kS, this.E, this.sY);
                  this.E = this.E + this.sY[0];
                  var1 += var7;
               } else if (var6 == 2) {
                  int var12 = DexUtil.bytearraySLEB128ToInt(this.kS, this.E, this.sY);
                  this.E = this.E + this.sY[0];
                  var2 += var12;
               } else if (var6 == 3) {
                  int var13 = DexUtil.bytearrayULEB128ToInt(this.kS, this.E, this.sY);
                  this.E = this.E + this.sY[0];
                  int var8 = DexUtil.bytearrayULEB128P1ToInt(this.kS, this.E, this.sY);
                  var8 = this.pC.pC(var8);
                  this.E = this.E + this.sY[0];
                  int var9 = DexUtil.bytearrayULEB128P1ToInt(this.kS, this.E, this.sY);
                  var9 = this.pC.A(var9);
                  this.E = this.E + this.sY[0];
                  this.pC(new beu(var1, var13, var8, var9, -1));
               } else if (var6 == 4) {
                  int var14 = DexUtil.bytearrayULEB128ToInt(this.kS, this.E, this.sY);
                  this.E = this.E + this.sY[0];
                  int var20 = DexUtil.bytearrayULEB128P1ToInt(this.kS, this.E, this.sY);
                  var20 = this.pC.pC(var20);
                  this.E = this.E + this.sY[0];
                  int var25 = DexUtil.bytearrayULEB128P1ToInt(this.kS, this.E, this.sY);
                  var25 = this.pC.A(var25);
                  this.E = this.E + this.sY[0];
                  int var10 = DexUtil.bytearrayULEB128P1ToInt(this.kS, this.E, this.sY);
                  var10 = this.pC.pC(var10);
                  this.E = this.E + this.sY[0];
                  this.pC(new beu(var1, var14, var20, var25, var10));
               } else if (var6 == 5) {
                  int var15 = DexUtil.bytearrayULEB128ToInt(this.kS, this.E, this.sY);
                  this.E = this.E + this.sY[0];
                  Object var22 = (List)this.fI.get(var1);
                  if (var22 == null) {
                     var22 = new ArrayList();
                     this.fI.put(var1, var22);
                  }

                  var22.add(var15);
               } else if (var6 == 6) {
                  int var16 = DexUtil.bytearrayULEB128ToInt(this.kS, this.E, this.sY);
                  this.E = this.E + this.sY[0];
                  Object var23 = (List)this.WR.get(var1);
                  if (var23 == null) {
                     var23 = new ArrayList();
                     this.WR.put(var1, var23);
                  }

                  var23.add(var16);
               } else if (var6 == 7) {
                  var4 = true;
               } else if (var6 == 8) {
                  var5 = true;
               } else if (var6 == 9) {
                  int var17 = DexUtil.bytearrayULEB128P1ToInt(this.kS, this.E, this.sY);
                  var17 = this.pC.pC(var17);
                  this.E = this.E + this.sY[0];
                  var3 = var17;
               } else {
                  var6 -= 10;
                  var2 += -4 + var6 % 15;
                  var1 += var6 / 15;
                  if (var1 < this.wS) {
                     this.gp.put(var1, var2);
                  }

                  if (var4) {
                     this.NS.add(var1);
                     var4 = false;
                  }

                  if (var5) {
                     this.vP.add(var1);
                     var5 = false;
                  }

                  if (var3 >= -1) {
                     this.xC.put(var1, var3);
                     var3 = -2;
                  }
               }
            }
         }
      }
   }

   private void pC(beu var1) {
      Object var2 = (List)this.oT.get(var1.getAddress());
      if (var2 == null) {
         var2 = new ArrayList();
         this.oT.put(var1.getAddress(), var2);
      }

      var2.add(var1);
   }

   public bet pC(int var1) {
      this.pC();
      bet var2 = new bet();
      Integer var3 = (Integer)this.gp.get(var1);
      var2.pC = var3 == null ? -1 : var3;
      var2.A = (List)this.oT.get(var1);
      var2.kS = (List)this.fI.get(var1);
      var2.wS = (List)this.WR.get(var1);
      var2.UT = this.NS.contains(var1);
      var2.E = this.vP.contains(var1);
      var3 = (Integer)this.xC.get(var1);
      var2.sY = var3 == null ? -1 : var3;
      return var2;
   }

   @Override
   public List getVariables(int var1) {
      this.pC();
      List var2 = (List)this.oT.get(var1);
      return var2 == null ? Collections.emptyList() : var2;
   }

   @Override
   public int[] getParameterNameIndexes() {
      this.A();
      return this.ld;
   }

   @Override
   public SortedMap getKnownVariablesMap(IDexMethod var1) {
      if (this.ED == null) {
         synchronized (this) {
            if (this.ED == null) {
               try {
                  SortedMap var3 = this.pC(var1);
                  this.ED = var3;
               } catch (Exception var5) {
                  JebCoreService.notifySilentExceptionToClient(var5);
                  this.ED = Collections.emptySortedMap();
               }
            }
         }
      }

      return this.ED;
   }

   private SortedMap pC(IDexMethod var1) {
      this.pC();
      IDexCodeItem var2 = var1.getData().getCodeItem();
      int[] var3 = DexUtil.getMethodParameterIndices(var1);
      int var4 = var2.getInstructionsSize();
      ArrayList var5 = new ArrayList(var4 / 2);
      ArrayList var6 = new ArrayList();

      for (int var10 : var3) {
         var6.add(new beu(-1, var10, -1, -1, -1));
      }

      HashMap var16 = new HashMap();

      for (int var17 = 0; var17 < var4 / 2; var17++) {
         List var19 = (List)this.oT.getOrDefault(var17, Collections.emptyList());
         List var21 = (List)this.fI.getOrDefault(var17, Collections.emptyList());
         List var11 = (List)this.WR.getOrDefault(var17, Collections.emptyList());
         if (var19.isEmpty() && var21.isEmpty() && var11.isEmpty()) {
            var5.add(var6);
         } else {
            ArrayList var12 = new ArrayList(var6);
            if (!var21.isEmpty()) {
               for (int var14 : var21) {
                  beu var15 = this.pC(var12, var14);
                  if (var15 != null) {
                     var16.put(var14, var15);
                  }
               }
            }

            for (int var26 : var11) {
               beu var28 = (beu)var16.remove(var26);
               if (var28 != null) {
                  var12.add(var28);
               }
            }

            for (IDexDebugVariable var27 : var19) {
               var12.add((beu)var27);
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

   private beu pC(Collection var1, int var2) {
      Iterator var3 = var1.iterator();

      while (var3.hasNext()) {
         beu var4 = (beu)var3.next();
         if (var4.A == var2) {
            var3.remove();
            return var4;
         }
      }

      return null;
   }
}
