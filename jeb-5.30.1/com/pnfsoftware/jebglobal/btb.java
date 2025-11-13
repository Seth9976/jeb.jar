package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.CollectionOrder;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class btb {
   private static final ILogger nf = GlobalLog.getLogger(btb.class);
   private IDMethodContext gP;
   private boolean za;
   private boolean lm;
   MultiMap q;
   Set RF;
   Set xK;
   Set Dw;
   private CFG zz;
   private int JY;
   Map Uv;
   btf oW;
   Set gO;
   private TreeMap HF;

   public btb(IDMethodContext var1) {
      this(var1, false, false);
   }

   public btb(IDMethodContext var1, boolean var2, boolean var3) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.gP = var1;
         this.za = var2;
         this.lm = var3;
      }
   }

   public boolean q() {
      return this.za;
   }

   public boolean RF() {
      return this.lm;
   }

   public void xK() {
      this.q = null;
      this.RF = null;
      this.xK = null;
      this.Dw = null;
      this.zz = null;
      this.JY = 0;
      this.HF = null;
      this.Uv = null;
      this.oW = null;
      this.gO = null;
   }

   public void Dw() {
      if (this.lm) {
         bto.q(this.gP);
      }

      this.q = new MultiMap(CollectionOrder.NATURAL);
      this.RF = new HashSet();
      this.xK = new HashSet();
      this.Dw = new HashSet();
      this.zz = this.gP.getCfg();
      this.JY = 0;
      this.HF = new TreeMap();
      this.Uv = bti.q(this.zz);
      this.oW = new btf(this.zz.size());
      this.gO = new HashSet();
   }

   public void q(Map var1, btf var2, Set var3) {
      this.q = new MultiMap(CollectionOrder.NATURAL);
      this.RF = new HashSet();
      this.xK = new HashSet();
      this.Dw = new HashSet();
      this.zz = this.gP.getCfg();
      this.JY = 0;
      this.HF = new TreeMap();
      this.Uv = var1;
      this.oW = var2;
      this.gO = var3;
   }

   public int q(long var1) {
      return (Integer)this.Uv.get(var1);
   }

   public boolean q(btc var1) {
      if (this.q != null) {
         for (btc var3 : this.q.values()) {
            if (var3.equals(var1)) {
               return true;
            }
         }
      }

      btc var4;
      do {
         var4 = this.nf();
         if (var4 == null) {
            return false;
         }
      } while (!var4.equals(var1));

      return true;
   }

   public Set Uv() {
      return Collections.unmodifiableSet(this.RF);
   }

   public boolean oW() {
      while (this.nf() != null) {
      }

      return true;
   }

   public Collection gO() {
      this.oW();
      return this.q.values();
   }

   public btc nf() {
      if (this.q == null) {
         this.Dw();
         Assert.a(this.q != null);
      }

      for (; this.JY < this.zz.size(); this.JY++) {
         BasicBlock var1 = this.zz.get(this.JY);
         IDInstruction var2 = (IDInstruction)var1.getLast();
         if (var2.isJumpOrJcond()) {
            int var3 = var2.getBranchTarget();
            if (var3 <= var1.getBase()) {
               int var4 = (Integer)this.Uv.get((long)var3);
               BasicBlock var5 = this.zz.get(var4);
               boolean var6 = var5.size() == 1 && ((IDInstruction)var5.get(0)).isJcond();
               BasicBlock var8 = q(var5);
               if (var6
                  ? var8 == var1
                  : (!((IDInstruction)var1.getLast()).isJump() || var8 == var1)
                     && (!((IDInstruction)var5.getLast()).isSwitch() || var8 == var1)
                     && (var8 == null || !((IDInstruction)var8.getLast()).isJump() || var8 == var1)) {
                  int var9 = (Integer)this.Uv.get(var5.getBase());
                  int var10 = (Integer)this.Uv.get(var1.getBase());
                  Assert.a(var10 >= var9);
                  int var11 = -1;
                  if (this.za) {
                     boolean var12 = true;
                     if (var6) {
                        int var13 = ((IDInstruction)var5.getLast()).getBranchTarget();
                        int var14 = (Integer)this.Uv.get((long)var13);
                        if (var14 <= var10) {
                           var6 = false;
                        } else if (var14 == var10 + 1) {
                           var12 = false;
                        } else {
                           var11 = var14;
                           var12 = false;
                        }
                     }

                     if (var12) {
                        var11 = this.RF(var9, var10);
                     }
                  }

                  if (var6) {
                     for (BasicBlock var20 : var5.getIrregularOutputs()) {
                        if ((Integer)this.Uv.get(var20.getBase()) <= var10) {
                           var6 = false;
                           break;
                        }
                     }
                  }

                  if (!this.Dw.contains(var10)) {
                     if (this.xK.contains(var9)) {
                        List var17 = this.q.get(var9);
                        if (var17 != null && !var17.isEmpty() && var6) {
                           continue;
                        }
                     }

                     if (this.HF.get(var9) == null) {
                        Integer var18 = (Integer)this.HF.lowerKey(var9);
                        if (var18 != null && var9 <= ((btc)this.HF.get(var18)).xK) {
                           this.RF.add(var9);
                           continue;
                        }
                     }

                     if (this.oW.RF(var9, var10)) {
                        btd var19;
                        if (var5 == var1) {
                           if (var2.isJump()) {
                              var19 = btd.xK;
                           } else {
                              Assert.a(var2.isJcond());
                              var19 = btd.RF;
                           }
                        } else if (var6) {
                           var19 = btd.q;
                        } else if (var2.isJump()) {
                           var19 = btd.xK;
                        } else {
                           Assert.a(var2.isJcond());
                           var19 = btd.RF;

                           for (BasicBlock var23 : var5.getInputs()) {
                              int var15 = (Integer)this.Uv.get(var23.getBase());
                              if (var15 >= var9 && var15 <= var10 && var15 != var10 && !this.q(var9, var15)) {
                                 var19 = btd.xK;
                                 break;
                              }
                           }
                        }

                        btc var22 = new btc(var19, var9, var10, var11);
                        this.q.put(var9, var22);
                        this.RF.remove(var9);
                        this.xK.add(var9);
                        this.Dw.add(var10);
                        this.HF.subMap(var9, true, var10, true).clear();
                        this.HF.put(var9, var22);
                        if (var19 == btd.q) {
                           this.gO.add(var9);
                        } else if (var19 == btd.RF) {
                           this.gO.add(var10);
                        } else if (var19 == btd.xK) {
                           this.gO.add(var10);
                        }

                        this.JY++;
                        return var22;
                     }

                     if (Licensing.isDebugBuild()) {
                        throw new RuntimeException("Unexpected region partial overlap?");
                     }

                     this.RF.add(var9);
                  }
               }
            }
         }
      }

      return null;
   }

   public static BasicBlock q(BasicBlock var0) {
      BasicBlock var1 = null;

      for (BasicBlock var3 : var0.getInputs()) {
         if (var1 == null || var3.getBase() > var1.getBase()) {
            var1 = var3;
         }
      }

      return var1;
   }

   private boolean q(int var1, int var2) {
      Assert.a(this.q != null);

      for (btc var4 : this.q.getSafe(var1)) {
         if (var4.xK == var2) {
            return true;
         }
      }

      return false;
   }

   private int RF(int var1, int var2) {
      int var3 = var2 + 1;
      int var4 = -1;

      for (int var5 = var1; var5 < var2; var5++) {
         BasicBlock var6 = this.zz.get(var5);

         for (BasicBlock var8 : var6.getOutputs()) {
            int var9 = (Integer)this.Uv.get(var8.getBase());
            if (var9 > var3) {
               if (var4 == -1) {
                  var4 = var9;
               } else if (var4 != var9) {
                  return -1;
               }
            }
         }
      }

      if (var4 == -1) {
         return -1;
      } else {
         for (int var10 = var2; var10 < var4; var10++) {
            BasicBlock var12 = this.zz.get(var10);

            for (BasicBlock var16 : var12.getOutputs()) {
               int var18 = (Integer)this.Uv.get(var16.getBase());
               if (var18 > var4) {
                  return -1;
               }
            }
         }

         for (int var11 = var2 + 1; var11 < var4; var11++) {
            BasicBlock var13 = this.zz.get(var11);

            for (BasicBlock var17 : var13.getInputs()) {
               int var19 = (Integer)this.Uv.get(var17.getBase());
               if (var19 < var1 || var19 >= var4) {
                  return -1;
               }
            }
         }

         return var4;
      }
   }
}
