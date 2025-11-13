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

public class bsr {
   private static final ILogger oW = GlobalLog.getLogger(bsr.class);
   private IDMethodContext gO;
   private boolean nf;
   Map q;
   private CFG gP;
   private int za;
   Map RF;
   btf xK;
   Set Dw;
   MultiMap Uv;

   public bsr(IDMethodContext var1, boolean var2) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.gO = var1;
         this.nf = var2;
      }
   }

   public void q() {
      this.q = null;
      this.gP = null;
      this.za = 0;
      this.RF = null;
      this.xK = null;
      this.Dw = null;
      this.Uv = null;
   }

   public void RF() {
      this.q = new TreeMap();
      this.gP = this.gO.getCfg();
      this.za = this.gP.size() - 1;
      if (this.nf) {
         btb var1 = new btb(this.gO);
         Assert.a(var1.oW());
         this.RF = var1.Uv;
         this.xK = var1.oW;
         this.Dw = var1.gO;
         this.Uv = var1.q;
      } else {
         this.RF = bti.q(this.gP);
         this.xK = new btf(this.gP.size());
         this.Dw = new HashSet();
         this.Uv = new MultiMap();
      }
   }

   public void q(Map var1, btf var2, Set var3, MultiMap var4) {
      this.q = new TreeMap();
      this.gP = this.gO.getCfg();
      this.za = this.gP.size() - 1;
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
   }

   public int q(long var1) {
      return (Integer)this.RF.get(var1);
   }

   public boolean xK() {
      while (this.Uv() != null) {
      }

      return true;
   }

   public Collection Dw() {
      this.xK();
      return this.q.values();
   }

   public bss Uv() {
      if (this.q == null) {
         this.RF();
         Assert.a(this.q != null);
      }

      for (; this.za >= 0; this.za--) {
         if (!this.Dw.contains(this.za)) {
            BasicBlock var1 = this.gP.get(this.za);
            IDInstruction var2 = (IDInstruction)var1.getLast();
            if (var2.isJcondOrSwitch()) {
               int var3 = this.za;
               int var4 = -1;
               LinkedHashSet var5 = new LinkedHashSet();
               this.xK.q(var3, true, var5);

               for (btc var7 : this.Uv.values()) {
                  if (var3 >= var7.q() && var3 >= var7.q() && var3 <= var7.RF()) {
                     var5.remove(var7.RF());
                  }
               }

               Object var16 = new ArrayList();

               for (BasicBlock var8 : var1.getOutputs()) {
                  int var9 = (Integer)this.RF.get(var8.getBase());
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

                  while (var11 < this.gP.size()) {
                     label116: {
                        if (var5.contains(var11 - 1)) {
                           int var12 = 0;
                           BasicBlock var13 = this.gP.get(var11);

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
                     var4 = this.RF(var3, var21, var5);
                  } else {
                     Assert.a(var2.isJcond());
                  }

                  if (var4 >= this.gP.size()) {
                     var4 = this.gP.size() - 1;
                  }
               }

               if (this.xK.RF(var3, var4 - 1)) {
                  bst var22 = var2.isSwitch() ? bst.RF : bst.q;
                  bss var23 = new bss(var22, var3, var4);
                  var23.Dw = var19;
                  this.q.put(var3, var23);
                  this.za--;
                  return var23;
               }
            }
         }
      }

      this.Dw.addAll(this.q.keySet());
      return null;
   }

   private int q(int var1, int var2, Collection var3) {
      BasicBlock var4 = this.gP.get(var2);
      if (var4.insize() >= 2) {
         return var2;
      } else if (var3.isEmpty()) {
         return var2;
      } else {
         TreeSet var5 = new TreeSet(var3);
         int var6 = (Integer)var5.last();
         HashSet var7 = new HashSet();
         var7.add(var2);
         HashSet var8 = new HashSet();
         int var9 = var2;

         for (int var10 = var2 + 1; var10 <= var6; var10++) {
            var7.add(var10);
            var4 = this.gP.get(var10);

            for (BasicBlock var12 : var4.getInputs()) {
               int var13 = (Integer)this.RF.get(var12.getBase());
               if (var13 < var2 || var13 > var6) {
                  return var9 < 0 ? var2 : var9 + 1;
               }

               var8.add(var13);
            }

            if (var3.contains(var10) && var7.containsAll(var8)) {
               var9 = var10;
            }
         }

         return var9 < 0 ? var2 : var9 + 1;
      }
   }

   private int RF(int var1, int var2, Collection var3) {
      BasicBlock var4 = this.gP.get(var2);
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
            var4 = this.gP.get(var10);

            for (BasicBlock var12 : var4.getInputs()) {
               int var13 = (Integer)this.RF.get(var12.getBase());
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

   private boolean q(int var1) {
      for (bss var3 : this.q.values()) {
         if (var3.xK == var1) {
            return true;
         }
      }

      return false;
   }
}
