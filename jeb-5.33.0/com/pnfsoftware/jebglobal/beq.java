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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@Ser
public class beq implements IDexDebugInfo {
   @SerId(1)
   private int[] pC;
   @SerId(2)
   private Map A;
   @SerId(3)
   private Map kS;
   @SerId(4)
   private Map wS;
   @SerId(5)
   private Map UT;
   @SerId(6)
   private Set E;
   @SerId(7)
   private Set sY;
   @SerId(8)
   private Map ys;
   @SerTransient
   private volatile SortedMap ld;

   public bet pC(int var1) {
      bet var2 = new bet();
      Integer var3 = (Integer)this.A.get(var1);
      var2.pC = var3 == null ? -1 : var3;
      var2.A = (List)this.kS.get(var1);
      var2.kS = (List)this.wS.get(var1);
      var2.wS = (List)this.UT.get(var1);
      var2.UT = this.E.contains(var1);
      var2.E = this.sY.contains(var1);
      var3 = (Integer)this.ys.get(var1);
      var2.sY = var3 == null ? -1 : var3;
      return var2;
   }

   @Override
   public List getVariables(int var1) {
      List var2 = (List)this.kS.get(var1);
      return var2 == null ? Collections.emptyList() : var2;
   }

   @Override
   public int[] getParameterNameIndexes() {
      return this.pC;
   }

   @Override
   public SortedMap getKnownVariablesMap(IDexMethod var1) {
      if (this.ld == null) {
         synchronized (this) {
            if (this.ld == null) {
               try {
                  SortedMap var3 = this.pC(var1);
                  this.ld = var3;
               } catch (Exception var5) {
                  JebCoreService.notifySilentExceptionToClient(var5);
                  this.ld = Collections.emptySortedMap();
               }
            }
         }
      }

      return this.ld;
   }

   private SortedMap pC(IDexMethod var1) {
      IDexCodeItem var2 = var1.getData().getCodeItem();
      Assert.a(var2.getDebugInfo() == this);
      int[] var3 = DexUtil.getMethodParameterIndices(var1);
      int var4 = var2.getInstructionsSize();
      ArrayList var5 = new ArrayList(var4 / 2);
      ArrayList var6 = new ArrayList();

      for (int var10 : var3) {
         var6.add(new beu(-1, var10, -1, -1, -1));
      }

      HashMap var16 = new HashMap();

      for (int var17 = 0; var17 < var4 / 2; var17++) {
         List var19 = (List)this.kS.getOrDefault(var17, Collections.emptyList());
         List var21 = (List)this.wS.getOrDefault(var17, Collections.emptyList());
         List var11 = (List)this.UT.getOrDefault(var17, Collections.emptyList());
         if (var19.isEmpty() && var21.isEmpty() && var11.isEmpty()) {
            var5.add(var6);
         } else {
            ArrayList var12 = new ArrayList(var6);
            if (!var21.isEmpty()) {
               for (int var14 : var21) {
                  beu var15 = this.pC(var12, var14);
                  if (var15 == null) {
                     throw new IllegalStateException("Attempt to end a var that was not defined");
                  }

                  var16.put(var14, var15);
               }
            }

            for (int var26 : var11) {
               beu var28 = (beu)var16.remove(var26);
               if (var28 == null) {
                  throw new IllegalStateException("Attempt to restart a var that was not ended");
               }

               var12.add(var28);
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
