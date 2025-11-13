package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class boq {
   private static final ILogger E = GlobalLog.getLogger(boq.class);
   private IDMethodContext sY;
   private boolean ys;
   Map pC;
   private CFG ld;
   private int gp;
   Map A;
   bpd kS;
   Set wS;
   MultiMap UT;

   public boq(IDMethodContext var1, boolean var2) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.sY = var1;
         this.ys = var2;
      }
   }

   public void pC() {
      this.pC = null;
      this.ld = null;
      this.gp = 0;
      this.A = null;
      this.kS = null;
      this.wS = null;
      this.UT = null;
   }

   public void A() {
      this.pC = new TreeMap();
      this.ld = this.sY.getCfg();
      this.gp = this.ld.size() - 1;
      if (this.ys) {
         boz var1 = new boz(this.sY);
         Assert.a(var1.wS());
         this.A = var1.UT;
         this.kS = var1.E;
         this.wS = var1.sY;
         this.UT = var1.pC;
      } else {
         this.A = bpf.pC(this.ld);
         this.kS = new bpd(this.ld.size());
         this.wS = new HashSet();
         this.UT = new MultiMap();
      }
   }

   public void pC(Map var1, bpd var2, Set var3, MultiMap var4) {
      this.pC = new TreeMap();
      this.ld = this.sY.getCfg();
      this.gp = this.ld.size() - 1;
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
   }

   public int pC(long var1) {
      return (Integer)this.A.get(var1);
   }

   public bor kS() {
      if (this.pC == null) {
         this.A();
         Assert.a(this.pC != null);
      }

      for (; this.gp >= 0; this.gp--) {
         if (!this.wS.contains(this.gp)) {
            BasicBlock var1 = this.ld.get(this.gp);
            IDInstruction var2 = (IDInstruction)var1.getLast();
            if (var2.isJcondOrSwitch()) {
               int var3 = this.gp;
               int var4 = -1;
               LinkedHashSet var5 = new LinkedHashSet();
               this.kS.pC(var3, true, var5);

               for (bpa var7 : this.UT.values()) {
                  if (var3 >= var7.kS() && var3 >= var7.kS() && var3 <= var7.pC()) {
                     var5.remove(var7.pC());
                  }
               }

               Object var16 = new ArrayList();

               for (BasicBlock var8 : var1.getOutputs()) {
                  int var9 = (Integer)this.A.get(var8.getBase());
                  if (var5.contains(var9 - 1)) {
                     var16.add(var9);
                  }
               }

               var16.sort((var0, var1x) -> -Integer.compare(var0, var1x));
               if (var16.size() > 2) {
                  var16 = var16.subList(0, 2);
               }

               label121:
               for (int var20 : var16) {
                  int var10 = -1;
                  int var11 = var20;

                  while (var11 < this.ld.size()) {
                     label116: {
                        if (var5.contains(var11 - 1)) {
                           int var12 = 0;
                           BasicBlock var13 = this.ld.get(var11);

                           for (BasicBlock var15 : var1.getOutputs()) {
                              if (var15 == var13 || CFGUtil.canReach(var15, var13, false, Arrays.asList(var1))) {
                                 var12++;
                              }
                           }

                           if (var12 >= 2) {
                              if (var10 != -1 && var12 <= var10) {
                                 break label116;
                              }

                              var10 = var12;
                              var4 = var11;
                           } else if (var10 != -1) {
                              break label116;
                           }
                        }

                        var11++;
                        continue;
                     }

                     if (var4 != -1) {
                        break label121;
                     }
                     continue label121;
                  }
                  break;
               }

               boolean var19 = false;
               if (var4 == -1) {
                  var19 = true;
                  int var21 = var16.isEmpty() ? var3 + 1 : (Integer)var16.get(0);
                  var4 = var21;
                  if (var2.isSwitch()) {
                     var4 = this.pC(var3, var21, var5);
                  } else {
                     Assert.a(var2.isJcond());
                  }

                  if (var4 >= this.ld.size()) {
                     var4 = this.ld.size() - 1;
                  }
               }

               if (this.kS.pC(var3, var4 - 1)) {
                  bos var22 = var2.isSwitch() ? bos.A : bos.pC;
                  bor var23 = new bor(var22, var3, var4);
                  var23.wS = var19;
                  this.pC.put(var3, var23);
                  this.gp--;
                  return var23;
               }
            }
         }
      }

      this.wS.addAll(this.pC.keySet());
      return null;
   }

   private int pC(int var1, int var2, Collection var3) {
      BasicBlock var4 = this.ld.get(var2);
      if (var4.insize() >= 2) {
         return var2;
      } else if (var3.isEmpty()) {
         return var2;
      } else {
         TreeSet var5 = new TreeSet(var3);
         int var6 = (Integer)var5.last();
         HashSet var7 = new HashSet();

         for (int var8 = var1; var8 <= var2; var8++) {
            var7.add(var8);
         }

         HashSet var15 = new HashSet();
         int var9 = var2;

         for (int var10 = var2 + 1; var10 <= var6; var10++) {
            var7.add(var10);
            var4 = this.ld.get(var10);

            for (BasicBlock var12 : var4.getInputs()) {
               int var13 = (Integer)this.A.get(var12.getBase());
               if (var13 < var1 || var13 > var6) {
                  return var9 < 0 ? var2 : var9 + 1;
               }

               var15.add(var13);
            }

            if (var3.contains(var10) && var7.containsAll(var15)) {
               var9 = var10;
            }
         }

         return var9 < 0 ? var2 : var9 + 1;
      }
   }
}
