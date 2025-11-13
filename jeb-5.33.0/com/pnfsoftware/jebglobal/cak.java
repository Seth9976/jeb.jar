package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class cak extends AbstractDOptimizer {
   private int pC = 0;

   public cak() {
      this.addTag("reorderer");
   }

   @Override
   public int perform() {
      if (this.pC >= 1) {
         return 0;
      } else {
         this.pC++;
         IDTryData var1 = this.ctx.getExceptionData();
         if (var1.isEmpty()) {
            return 0;
         } else {
            if (bpl.pC(this.ctx)) {
               this.assignLocalFields(this.ctx);
            }

            return this.pC();
         }
      }
   }

   int pC() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;
         int var3 = 5;

         while (var3-- > 0) {
            int var4 = 0;

            for (int var5 = 0; var5 < this.cfg.size(); var5++) {
               BasicBlock var6 = this.cfg.get(var5);
               if (var6.irrinsize() > 0 && this.A(var6)) {
                  var4++;
               }
            }

            if (var4 == 0) {
               break;
            }

            var2 += var4;
         }

         var3 = 5;

         while (var3-- > 0) {
            int var12 = 0;

            for (int var15 = 0; var15 < this.cfg.size(); var15++) {
               BasicBlock var18 = this.cfg.get(var15);
               if (var18.outsize() >= 2 && var18.irroutsize() == 0 && this.kS(var18)) {
                  var12++;
               }
            }

            if (var12 == 0) {
               break;
            }

            var2 += var12;
         }

         var3 = 5;

         while (var3-- > 0) {
            byte var13 = 0;

            for (int var16 = 0; var16 < this.cfg.size(); var16++) {
               BasicBlock var19 = this.cfg.get(var16);
               if (var19.outsize() >= 2 && var19.irroutsize() >= 1 && this.UT(var19)) {
                  var2++;
               }
            }

            if (var13 == 0) {
               break;
            }

            var2 += var13;
         }

         var3 = 5;

         while (var3-- > 0) {
            byte var14 = 0;

            for (int var17 = 0; var17 < this.cfg.size(); var17++) {
               BasicBlock var20 = this.cfg.get(var17);
               if (var20.outsize() >= 2 && var20.irroutsize() >= 1 && this.wS(var20)) {
                  var2++;
               }
            }

            if (var14 == 0) {
               break;
            }

            var2 += var14;
         }

         var2 += this.kS();
         var2 += this.A();
         if (var2 > 0) {
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private boolean A(BasicBlock var1) {
      if (this.ctx.getExceptionData().getHandledExceptionTypesAt((int)var1.getBase()).size() != 1) {
         return false;
      } else {
         HashSet var2 = new HashSet();

         for (BasicBlock var4 : var1.getIrregularInputs()) {
            if (var4.irroutsize() != 1) {
               return false;
            }

            var2.add(var4.getBase());
         }

         int var19 = (Integer)this.ctx.getExceptionData().getHandledExceptionTypesAt((int)var1.getBase()).iterator().next();
         Set var20 = bpl.pC(this.cfg, var1);
         if (!var20.contains(var1.getBase())) {
            return false;
         } else {
            BasicBlock var5 = null;

            for (long var7 : var20) {
               for (BasicBlock var10 : this.cfg.getBlockAt(var7).getOutputs()) {
                  if (!var20.contains(var10.getBase())) {
                     if (var5 != null) {
                        return false;
                     }

                     if (var10.insize() != 2) {
                        return false;
                     }

                     var5 = var10;
                  }
               }

               for (BasicBlock var26 : this.cfg.getBlockAt(var7).getIrregularOutputs()) {
                  if (!var20.contains(var26.getBase())) {
                     return false;
                  }
               }
            }

            if (var5 == null) {
               return false;
            } else {
               BasicBlock var21 = var5.getInputBlock(0);
               if (var20.contains(var21.getBase())) {
                  var21 = var5.getInputBlock(1);
                  Assert.a(!var20.contains(var21.getBase()));
               }

               HashMap var22 = new HashMap();
               int var8 = 0;

               for (BasicBlock var27 : this.cfg) {
                  var22.put(var27.getBase(), var8);
                  var8++;
               }

               int var25 = (Integer)var22.get(var5.getBase());
               int var28 = (Integer)var22.get(var1.getBase());
               if (var28 >= var25) {
                  return false;
               } else {
                  int var11 = (Integer)var22.get(var21.getBase());
                  if (var11 >= var25) {
                     return false;
                  } else {
                     Set var12 = null;

                     for (int var13 = var11; var13 >= 0; var13--) {
                        BasicBlock var14 = this.cfg.get(var13);
                        bru var15 = new bru(this.cfg, var14.getBase(), Sets.newHashSet(var5.getBase(), var1.getBase()));
                        var15.pC(true);
                        if (var15.kS()) {
                           Set var16 = var15.pC();
                           if (var16.containsAll(var2)) {
                              var16.removeAll(var2);
                              if (var12 == null || var16.size() > var12.size()) {
                                 var12 = var16;
                              }
                           }
                        }
                     }

                     if (var12 != null && !var12.isEmpty()) {
                        for (long var31 : var12) {
                           if (var31 >= var5.getBase()) {
                              return false;
                           }

                           BasicBlock var17 = this.cfg.getBlockAt(var31);
                           if (var17.irroutsize() != 0 || var17.canThrow()) {
                              return false;
                           }
                        }

                        int var30 = 0;

                        for (long var33 : var12) {
                           BasicBlock var18 = this.cfg.getBlockAt(var33);
                           if (this.ctx.getExceptionData().protectBlock((int)var18.getBase(), var19, (int)var1.getBase(), -1)) {
                              this.cfg.addIrregularEdge(var18, var1, -1);
                              var30++;
                           }
                        }

                        return var30 > 0;
                     } else {
                        return false;
                     }
                  }
               }
            }
         }
      }
   }

   boolean pC(BasicBlock var1) {
      if (var1.outsize() < 2) {
         return false;
      } else {
         for (BasicBlock var3 : var1.getOutputs()) {
            if (var3.getBase() <= var1.getBase()) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean kS(BasicBlock var1) {
      IDTryData var2 = this.ctx.getExceptionData();
      if (var2.isProtectedBlock((int)var1.getBase())) {
         return false;
      } else {
         IDInstruction var3 = (IDInstruction)var1.getLast();
         if (!var3.isJcondOrSwitch()) {
            return false;
         } else if (var3.canThrow()) {
            return false;
         } else if (!this.pC(var1)) {
            return false;
         } else {
            int var4 = 0;
            int var5 = -1;

            for (BasicBlock var7 : var1.getOutputs()) {
               if (var7.size() != 1 || !((IDInstruction)var7.getLast()).isJump()) {
                  int var8 = (int)var7.getBase();
                  if (var2.isProtectedBlock(var8)) {
                     if (var5 == -1) {
                        var5 = var8;
                     } else if (!var2.compareHandlers(var5, var8)) {
                        return false;
                     }

                     var4++;
                  }
               }
            }

            if (var4 < 2) {
               return false;
            } else {
               if (var1.canThrow()) {
                  var1 = DUtil.splitBlock(this.ctx, var1, var1.size() - 1);
               }

               var2.copyProtectedBlock(var5, (int)var1.getBase());

               for (BasicBlock var10 : this.cfg.getBlockAt((long)var5).getIrregularOutputs()) {
                  this.cfg.addIrregularEdge(var1, var10, -1);
               }

               return true;
            }
         }
      }
   }

   private boolean wS(BasicBlock var1) {
      IDTryData var2 = this.ctx.getExceptionData();
      if (!var2.isProtectedBlock((int)var1.getBase())) {
         return false;
      } else {
         IDInstruction var3 = (IDInstruction)var1.getLast();
         if (!var3.isJcond()) {
            return false;
         } else if (var3.canThrow()) {
            return false;
         } else if (!this.pC(var1)) {
            return false;
         } else {
            boolean var4 = false;

            for (BasicBlock var6 : var1.getOutputs()) {
               if (var6.irroutsize() == 0) {
                  var4 = true;
                  break;
               }
            }

            if (!var4) {
               return false;
            } else {
               if (var1.canThrow()) {
                  var1 = DUtil.splitBlock(this.ctx, var1, var1.size() - 1);
               }

               var2.unprotectBlock((int)var1.getBase());
               this.cfg.deleteIrregularOutEdges(var1);
               return true;
            }
         }
      }
   }

   private boolean UT(BasicBlock var1) {
      IDTryData var2 = this.ctx.getExceptionData();
      if (!var2.isProtectedBlock((int)var1.getBase())) {
         return false;
      } else {
         IDInstruction var3 = (IDInstruction)var1.getLast();
         if (!var3.isJcond()) {
            return false;
         } else if (!this.pC(var1)) {
            return false;
         } else {
            BasicBlock var4 = var1.getOutputBlock(0);
            BasicBlock var5 = var1.getOutputBlock(1);
            BasicBlock var6 = null;
            if (var5.irroutsize() == 0 && var2.compareHandlers((int)var1.getBase(), (int)var4.getBase()) && !var5.canThrow()) {
               var6 = var5;
            } else if (var4.irroutsize() == 0 && var2.compareHandlers((int)var1.getBase(), (int)var5.getBase()) && !var4.canThrow()) {
               var6 = var4;
            }

            if (var6 == null) {
               return false;
            } else {
               for (BasicBlock var8 : var1.getIrregularOutputs()) {
                  if (CFGUtil.canReach(var8, var6)) {
                     return false;
                  }
               }

               var2.copyProtectedBlock((int)var1.getBase(), (int)var6.getBase());

               for (BasicBlock var10 : var1.getIrregularOutputs()) {
                  this.cfg.addIrregularEdge(var6, var10, -1);
               }

               return true;
            }
         }
      }
   }

   private int kS() {
      int var1 = 0;

      for (int var2 = 1; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         BasicBlock var4 = this.cfg.get(var2 - 1);
         if (var3.irroutsize() > 0
            && var4.irroutsize() == var3.irroutsize()
            && var4.outsize() > 0
            && var4.getOutputBlock(0) == var3
            && this.ctx.getExceptionData().compareHandlers((int)var4.getBase(), (int)var3.getBase())) {
            ArrayList var5 = null;

            for (BasicBlock var7 : var3.getInputBlocks()) {
               if (var7.getBase() > var3.getBase()) {
                  if (var7.irroutsize() > 0 || var7.canThrow()) {
                     var5 = null;
                     break;
                  }

                  if (var5 == null) {
                     var5 = new ArrayList();
                  }

                  var5.add(var7);
               }
            }

            if (var5 != null) {
               for (BasicBlock var11 : var5) {
                  this.ctx.getExceptionData().copyProtectedBlock((int)var3.getBase(), (int)var11.getBase());

                  for (BasicBlock var9 : var3.getIrregularOutputs()) {
                     this.cfg.addIrregularEdge(var11, var9, -1);
                  }

                  var1++;
               }
            }
         }
      }

      return var1;
   }

   int A() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.irrinsize() != 0) {
            BasicBlock var4 = E(var3);
            if (var4 != null && var4.getBase() < var3.getBase() && ((IDInstruction)var4.getLast()).isJcond()) {
               BasicBlock var5 = this.cfg.getBlockAt((long)((IDInstruction)var4.getLast()).getBranchTarget());
               if (var5.getBase() > var3.getBase()) {
                  bru var6 = new bru(this.cfg, var3.getBase(), var5.getBase());
                  if (var6.kS()) {
                     BasicBlock var7 = this.cfg.getBlockAt(var4.getEndAddress());
                     if (!var7.canThrow()) {
                        var6 = new bru(this.cfg, var7.getBase(), var5.getBase());
                        if (var6.kS()) {
                           ArrayList var8 = new ArrayList();

                           for (long var10 : var6.pC()) {
                              BasicBlock var12 = this.cfg.getBlockAt(var10);
                              if (var12 == var4 || var12.canThrow() || var12.irroutsize() > 0 && !var12.getIrregularOutputs().contains(var3)) {
                                 var8 = null;
                                 break;
                              }

                              if (!var12.getIrregularOutputs().contains(var3)) {
                                 var8.add(var12);
                              }
                           }

                           if (var8 != null && !var8.isEmpty()) {
                              for (BasicBlock var15 : var8) {
                                 bpl.pC(var4, var15);
                              }

                              var1++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return var1;
   }

   private static BasicBlock E(BasicBlock var0) {
      BasicBlock var1 = null;

      for (BasicBlock var3 : var0.getIrregularInputs()) {
         if (var1 == null || var3.getBase() > var1.getBase()) {
            var1 = var3;
         }
      }

      return var1;
   }
}
