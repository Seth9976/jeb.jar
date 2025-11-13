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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class boz {
   private static final ILogger ys = GlobalLog.getLogger(boz.class);
   private IDMethodContext ld;
   private boolean gp;
   private boolean oT;
   MultiMap pC;
   Set A;
   Set kS;
   Set wS;
   private CFG fI;
   private int WR;
   Map UT;
   bpd E;
   Set sY;
   private TreeMap NS;

   public boz(IDMethodContext var1) {
      this(var1, false, false);
   }

   public boz(IDMethodContext var1, boolean var2, boolean var3) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.ld = var1;
         this.gp = var2;
         this.oT = var3;
      }
   }

   public void pC() {
      this.pC = null;
      this.A = null;
      this.kS = null;
      this.wS = null;
      this.fI = null;
      this.WR = 0;
      this.NS = null;
      this.UT = null;
      this.E = null;
      this.sY = null;
   }

   public void A() {
      if (this.oT) {
         bpl.pC(this.ld);
      }

      this.pC = new MultiMap(CollectionOrder.NATURAL);
      this.A = new HashSet();
      this.kS = new HashSet();
      this.wS = new HashSet();
      this.fI = this.ld.getCfg();
      this.WR = 0;
      this.NS = new TreeMap();
      this.UT = bpf.pC(this.fI);
      this.E = new bpd(this.fI.size());
      this.sY = new HashSet();
   }

   public void pC(Map var1, bpd var2, Set var3) {
      this.pC = new MultiMap(CollectionOrder.NATURAL);
      this.A = new HashSet();
      this.kS = new HashSet();
      this.wS = new HashSet();
      this.fI = this.ld.getCfg();
      this.WR = 0;
      this.NS = new TreeMap();
      this.UT = var1;
      this.E = var2;
      this.sY = var3;
   }

   public int pC(long var1) {
      return (Integer)this.UT.get(var1);
   }

   public Set kS() {
      return Collections.unmodifiableSet(this.A);
   }

   public boolean wS() {
      while (this.UT() != null) {
      }

      return true;
   }

   public bpa UT() {
      if (this.pC == null) {
         this.A();
         Assert.a(this.pC != null);
      }

      for (; this.WR < this.fI.size(); this.WR++) {
         BasicBlock var1 = this.fI.get(this.WR);
         IDInstruction var2 = (IDInstruction)var1.getLast();
         if (var2.isJumpOrJcond()) {
            int var3 = var2.getBranchTarget();
            if (var3 <= var1.getBase()) {
               int var4 = (Integer)this.UT.get((long)var3);
               BasicBlock var5 = this.fI.get(var4);
               boolean var6 = var5.size() == 1 && ((IDInstruction)var5.get(0)).isJcond();
               BasicBlock var8 = pC(var5);
               if (var6
                  ? var8 == var1
                  : (!((IDInstruction)var1.getLast()).isJump() || var8 == var1)
                     && (!((IDInstruction)var5.getLast()).isSwitch() || var8 == var1)
                     && (var8 == null || !((IDInstruction)var8.getLast()).isJump() || var8 == var1)) {
                  int var9 = (Integer)this.UT.get(var5.getBase());
                  int var10 = (Integer)this.UT.get(var1.getBase());
                  Assert.a(var10 >= var9);
                  int var11 = -1;
                  if (this.gp) {
                     boolean var12 = true;
                     if (var6) {
                        int var13 = ((IDInstruction)var5.getLast()).getBranchTarget();
                        int var14 = (Integer)this.UT.get((long)var13);
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
                        var11 = this.A(var9, var10);
                     }
                  }

                  if (var6) {
                     for (BasicBlock var20 : var5.getIrregularOutputs()) {
                        if ((Integer)this.UT.get(var20.getBase()) <= var10) {
                           var6 = false;
                           break;
                        }
                     }
                  }

                  if (!this.wS.contains(var10)) {
                     if (this.kS.contains(var9)) {
                        List var17 = this.pC.get(var9);
                        if (var17 != null && !var17.isEmpty() && var6) {
                           continue;
                        }
                     }

                     if (this.NS.get(var9) == null) {
                        Integer var18 = (Integer)this.NS.lowerKey(var9);
                        if (var18 != null && var9 <= ((bpa)this.NS.get(var18)).kS) {
                           this.A.add(var9);
                           continue;
                        }
                     }

                     if (this.E.pC(var9, var10)) {
                        bpb var19;
                        if (var5 == var1) {
                           if (var2.isJump()) {
                              var19 = bpb.kS;
                           } else {
                              Assert.a(var2.isJcond());
                              var19 = bpb.A;
                           }
                        } else if (var6) {
                           var19 = bpb.pC;
                        } else if (var2.isJump()) {
                           var19 = bpb.kS;
                        } else {
                           Assert.a(var2.isJcond());
                           var19 = bpb.A;

                           for (BasicBlock var23 : var5.getInputs()) {
                              int var15 = (Integer)this.UT.get(var23.getBase());
                              if (var15 >= var9 && var15 <= var10 && var15 != var10 && !this.pC(var9, var15)) {
                                 var19 = bpb.kS;
                                 break;
                              }
                           }
                        }

                        bpa var22 = new bpa(var19, var9, var10, var11);
                        this.pC.put(var9, var22);
                        this.A.remove(var9);
                        this.kS.add(var9);
                        this.wS.add(var10);
                        this.NS.subMap(var9, true, var10, true).clear();
                        this.NS.put(var9, var22);
                        if (var19 == bpb.pC) {
                           this.sY.add(var9);
                        } else if (var19 == bpb.A) {
                           this.sY.add(var10);
                        } else if (var19 == bpb.kS) {
                           this.sY.add(var10);
                        }

                        this.WR++;
                        return var22;
                     }

                     if (Licensing.isDebugBuild()) {
                        throw new RuntimeException("Unexpected region partial overlap?");
                     }

                     this.A.add(var9);
                  }
               }
            }
         }
      }

      return null;
   }

   public static BasicBlock pC(BasicBlock var0) {
      BasicBlock var1 = null;

      for (BasicBlock var3 : var0.getInputs()) {
         if (var1 == null || var3.getBase() > var1.getBase()) {
            var1 = var3;
         }
      }

      return var1;
   }

   private boolean pC(int var1, int var2) {
      Assert.a(this.pC != null);

      for (bpa var4 : this.pC.getSafe(var1)) {
         if (var4.kS == var2) {
            return true;
         }
      }

      return false;
   }

   private int A(int var1, int var2) {
      int var3 = var2 + 1;
      int var4 = -1;

      for (int var5 = var1; var5 < var2; var5++) {
         BasicBlock var6 = this.fI.get(var5);

         for (BasicBlock var8 : var6.getOutputs()) {
            int var9 = (Integer)this.UT.get(var8.getBase());
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
            BasicBlock var12 = this.fI.get(var10);

            for (BasicBlock var16 : var12.getOutputs()) {
               int var18 = (Integer)this.UT.get(var16.getBase());
               if (var18 > var4) {
                  return -1;
               }
            }
         }

         for (int var11 = var2 + 1; var11 < var4; var11++) {
            BasicBlock var13 = this.fI.get(var11);

            for (BasicBlock var17 : var13.getInputs()) {
               int var19 = (Integer)this.UT.get(var17.getBase());
               if (var19 < var1 || var19 >= var4) {
                  return -1;
               }
            }
         }

         return var4;
      }
   }
}
