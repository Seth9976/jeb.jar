package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class cgj extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;
      int var2 = this.q();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.gO();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.RF();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.Dw();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.Uv();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.oW();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.gP();
      if (var2 > 0) {
         var1 += var2;
      }

      return var1;
   }

   private int q() {
      int var1 = 0;

      for (int var3 = 1; var3 < this.cfg.size(); var3++) {
         BasicBlock var4 = this.cfg.get(var3);

         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            if (var6.isMonitorEnter()) {
               if (var5 + 1 < var4.size()) {
                  IDInstruction var7 = (IDInstruction)var4.get(var5 + 1);
                  if (var7.isMonitorExit() && var6.getOperand2() != null && var6.getOperand2().equals(var7.getOperand2())) {
                     if (!DUtil.removeInstruction(var4, var5)) {
                        var5++;
                     }

                     if (!DUtil.removeInstruction(var4, var5)) {
                        var5++;
                     }

                     var1++;
                  }
               } else {
                  BasicBlock var2;
                  if (var4.outsize() == 1 && var3 + 1 < this.cfg.size() && (var2 = this.cfg.get(var3 + 1)).insize() == 1 && var2 == var4.getOutputBlock(0)) {
                     IDInstruction var8 = (IDInstruction)var2.get(0);
                     if (var8.isMonitorExit() && var6.getOperand2() != null && var6.getOperand2().equals(var8.getOperand2())) {
                        if (!DUtil.removeInstruction(var4, var5)) {
                           var5++;
                        }

                        DUtil.removeInstruction(var2, 0);
                        var1++;
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private int RF() {
      int var1 = 0;
      int var2 = 0;

      while (var2 < this.cfg.size() - 1) {
         BasicBlock var3 = this.cfg.get(var2);
         BasicBlock var4;
         if (var3.outsize() == 1
            && var3.size() == 1
            && ((IDInstruction)var3.get(0)).isStoreException()
            && (var4 = var3.getOutputBlock(0)) == this.cfg.get(var2 + 1)
            && var4.insize() == 1
            && var4.irrinsize() == 0
            && DUtil.mergeBlocks(this.ctx, var3)) {
            this.cfg.invalidateDataFlowAnalysis();
            var1++;
         } else {
            var2++;
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private int xK() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;

         for (int var3 = 0; var3 < this.cfg.size(); var3++) {
            BasicBlock var4 = this.cfg.get(var3);
            if (var4.irrinsize() > 0
               && var4.irroutsize() == 0
               && var4.size() >= 2
               && ((IDInstruction)var4.get(0)).isStoreException()
               && ((IDInstruction)var4.get(1)).isMonitorExit()) {
               if (var4.size() > 2) {
                  DUtil.splitBlock(this.ctx, var4, 2);
               }

               if (var1.protectBlock((int)var4.getBase(), -1, (int)var4.getBase(), -1)) {
                  this.cfg.addIrregularEdge(var4, var4, -1);
                  var2++;
               }
            }
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private int Dw() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;

         for (BasicBlock var4 : this.cfg) {
            if (var4.insize() == 0 && var4.irrinsize() >= 2 && var4.irroutsize() == 1) {
               IDInstruction var5 = (IDInstruction)var4.get(0);
               if (var4.size() == 1) {
                  if (!var5.isMonitorExit()) {
                     continue;
                  }
               } else if (var4.size() == 2) {
                  if (!var5.isStoreException()) {
                     continue;
                  }

                  var5 = (IDInstruction)var4.get(1);
                  if (!var5.isMonitorExit()) {
                     continue;
                  }
               }

               if (var4.getIrregularOutputBlock(0) == var4) {
                  var1.unprotectBlock((int)var4.getBase());
                  this.cfg.deleteIrregularOutEdges(var4);
                  var2++;
               }
            }
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private int Uv() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;

         for (int var3 = 0; var3 < this.cfg.size() - 1; var3++) {
            BasicBlock var4 = this.cfg.get(var3);
            if (var4.size() == 2
               && var4.insize() == 0
               && var4.irrinsize() >= 1
               && var4.outsize() == 1
               && var4.irroutsize() == 0
               && ((IDInstruction)var4.get(0)).isStoreException()
               && ((IDInstruction)var4.get(1)).isMonitorExit()) {
               BasicBlock var5 = this.cfg.get(var3 + 1);
               if (var5.size() == 1
                  && var5.insize() == 1
                  && var5.irrinsize() == 0
                  && var5.outsize() == 0
                  && ((IDInstruction)var5.get(0)).isThrow()
                  && ((IDInstruction)var5.get(0)).getThrowExpression() == ((IDInstruction)var4.get(0)).getStoredExceptionVariable()) {
                  var1.copyProtectedBlock((int)var5.getBase(), (int)var4.getBase());

                  for (BasicBlock var7 : var5.getIrregularOutputs()) {
                     this.cfg.addIrregularEdge(var4, var7, -1);
                  }

                  DUtil.mergeBlocks(this.ctx, var4);
                  var2++;
               }
            }
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private int oW() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;

         for (int var3 = 0; var3 < this.cfg.size(); var3++) {
            BasicBlock var4 = this.cfg.get(var3);
            if (var4.irroutsize() == 1 && ((IDExceptionHandler)var1.getBlockHandlers((int)var4.getBase()).get(0)).isCatchAll(this.ctx)) {
               int var5 = var4.size() - 1;
               IDInstruction var6 = (IDInstruction)var4.get(var5);
               if (var6.getOpcode().isAnyOf(DOpcodeType.IR_RETURN, DOpcodeType.IR_JUMP, DOpcodeType.IR_JCOND)) {
                  if (var4.size() == 1) {
                     continue;
                  }

                  var6 = (IDInstruction)var4.get(--var5);
               }

               if (var6.isMonitorExit()) {
                  IDExpression var7 = (IDExpression)var6.getOperand2();
                  if (var5 - 1 < 0 || !((IDInstruction)var4.get(var5 - 1)).isStoreException()) {
                     BasicBlock var8 = var4.getIrregularOutputBlock(0);
                     if (var8.size() == 3
                        && ((IDInstruction)var8.get(0)).isStoreException()
                        && ((IDInstruction)var8.get(1)).isMonitorExit()
                        && ((IDInstruction)var8.get(2)).isThrow()
                        && var7.equals(((IDInstruction)var8.get(1)).getOperand2())) {
                        if (var5 > 0) {
                           var4 = DUtil.splitBlock(this.ctx, var4, var5);
                        }

                        DUtil.unprotectBlock(this.cfg, var1, (int)var4.getBase());
                        if (var1.copyProtectedBlock((int)var8.getBase(), (int)var4.getBase())) {
                           for (BasicBlock var10 : var8.getIrregularOutputs()) {
                              this.cfg.addIrregularEdge(var4, var10, -1);
                           }
                        }

                        var2++;
                     }
                  }
               }
            }
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private int gO() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;
         int var3 = 0;

         while (var3 < this.cfg.size()) {
            BasicBlock var4 = this.cfg.get(var3);
            BasicBlock var5;
            BasicBlock var6;
            if (var4.insize() == 0
               && var4.outsize() == 1
               && var4.size() <= 2
               && ((IDInstruction)var4.get(0)).isStoreException()
               && (var4.size() == 1 || ((IDInstruction)var4.get(1)).isJump())
               && (var5 = var4.getOutputBlock(0)) != null
               && var5.insize() == 1
               && var5.irrinsize() == 0
               && var5.size() <= 2
               && ((IDInstruction)var5.get(0)).isMonitorExit()
               && (var5.size() == 1 || ((IDInstruction)var5.get(1)).isJump())
               && (var6 = var5.getOutputBlock(0)) != null
               && var6.insize() == 1
               && var6.outsize() == 0
               && var6.irrinsize() == 0
               && var6.size() == 1
               && ((IDInstruction)var6.get(0)).isThrow()
               && ((IDInstruction)var4.get(0)).getStoredExceptionVariable().equals(((IDInstruction)var6.get(0)).getThrowExpression())) {
               if (var4.size() == 1 && var5.size() == 1) {
                  var3++;
                  continue;
               }

               if (var5.irroutsize() > 0 && (var5.irroutsize() != 1 || var5.getIrregularOutputBlock(0) != var4)) {
                  var3++;
                  continue;
               }

               if (var1.unprotectBlock((int)var5.getBase())) {
                  this.cfg.deleteIrregularOutEdges(var5);
                  var2++;
               }

               if (var1.unprotectBlock((int)var4.getBase())) {
                  this.cfg.deleteIrregularOutEdges(var4);
                  var2++;
               }

               IDInstruction var7 = (IDInstruction)var4.get(0);
               IDInstruction var8 = (IDInstruction)var5.get(0);
               IDInstruction var9 = (IDInstruction)var6.get(0);
               if (var4.size() == 1) {
                  if (DUtil.modifyInstructionSize(this.ctx, var7, 3)) {
                     var4 = this.cfg.getBlockAt(var7.getOffset());
                     this.cfg.getBlockAt(var8.getOffset());
                     var6 = this.cfg.getBlockAt(var9.getOffset());
                  }

                  var7.adjustSize(-2);
                  var8 = var8.duplicateWithOffsetAndSize(var7.getOffset() + 1L, 1);
                  var9 = var9.duplicateWithOffsetAndSize(var7.getOffset() + 2L, 1);
               } else {
                  if (var4.size() != 2) {
                     var3++;
                     continue;
                  }

                  IDInstruction var10 = (IDInstruction)var4.get(1);
                  if (DUtil.modifyInstructionSize(this.ctx, var10, 2)) {
                     var4 = this.cfg.getBlockAt(var7.getOffset());
                     this.cfg.getBlockAt(var8.getOffset());
                     var6 = this.cfg.getBlockAt(var9.getOffset());
                  }

                  var8 = var8.duplicateWithOffsetAndSize(var10.getOffset(), 1);
                  var9 = var9.duplicateWithOffsetAndSize(var10.getOffset() + 1L, var10.getSize() - 1);
                  var4.remove(1);
               }

               var4.add(1, var8);
               var4.add(2, var9);
               this.cfg.deleteOutEdges(var4);
               if (var1.copyProtectedBlock((int)var6.getBase(), (int)var4.getBase())) {
                  for (BasicBlock var11 : var6.getIrregularOutputs()) {
                     this.cfg.addIrregularEdge(var4, var11, -1);
                  }
               }

               this.cleanGraph(true, false);
               this.cfg.invalidateDataFlowAnalysis();
               var2++;
            }

            var3++;
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private int nf() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         HashMap var2 = new HashMap();

         for (BasicBlock var19 : this.cfg) {
            byte var20 = 0;
            IDInstruction var21 = (IDInstruction)var19.getLast();
            if (var21.isMonitorEnter()) {
               var20 = 1;
            } else if (var21.isThrow()) {
               if (var19.irrinsize() > 0
                  && var19.size() == 3
                  && ((IDInstruction)var19.get(0)).isStoreException()
                  && ((IDInstruction)var19.get(1)).isMonitorExit()) {
                  var21 = (IDInstruction)var19.get(1);
                  var20 = 3;
               }
            } else {
               if (var19.size() > 1) {
                  var21 = (IDInstruction)var19.get(0);
               }

               if (var21.isMonitorExit()) {
                  var20 = 2;
               }
            }

            if (var20 != 0) {
               if (!(var21.getOperand2() instanceof IDVar)) {
                  return 0;
               }

               long var22 = var21.getOffset();
               IDVar var23 = (IDVar)var21.getOperand2();
               int var25 = var23.getId();
               IDFA var27 = this.cfg.doDataFlowAnalysis();
               boolean var12 = var27.setAlwaysExamineIrregularFlows(true);
               Collection var28 = var27.getUseDefs(var22, var25);
               var27.setAlwaysExamineIrregularFlows(var12);
               if (var28 == null || var28.size() != 1) {
                  return 0;
               }

               long var29 = (Long)var28.iterator().next();
               cgj.eo var30 = new cgj.eo(var29, var25);
               cgj.eo var17 = (cgj.eo)var2.putIfAbsent(var30, var30);
               if (var17 != null) {
                  var30 = var17;
               }

               if (var20 == 1) {
                  if (var30.xK != -1L) {
                     return 0;
                  }

                  var30.xK = var22;
                  var30.Dw = var19.getBase();
               } else if (var20 == 2) {
                  var30.Uv.add(var22);
                  var30.oW.add(var19.getBase());
               } else if (var20 == 3) {
                  if (var30.gO != -1L) {
                     return 0;
                  }

                  var30.gO = var22;
                  var30.nf = var19.getBase();
               }
            }
         }

         int var18 = 0;

         for (cgj.eo var5 : var2.keySet()) {
            if (var5.xK != -1L && !var5.Uv.isEmpty() && var5.gO != -1L) {
               Set var6 = new cgj.CU(this.cfg, var5.Dw, var5.oW).q();
               if (var6 != null) {
                  biv var7 = new biv(-1, (int)var5.nf);
                  ArrayList var8 = new ArrayList();
                  BasicBlock var9 = this.cfg.getBlockAt((long)var7.getAddress());

                  for (long var11 : var6) {
                     if (var11 != var5.Dw && var11 != var5.nf) {
                        List var13 = var1.getBlockHandlers((int)var11);
                        if (!var13.contains(var7)) {
                           BasicBlock var14 = this.cfg.getBlockAt(var11);
                           if (var14.canThrow()) {
                              if (var5.Uv.contains(var11)) {
                              }
                           } else {
                              for (IDExceptionHandler var16 : var13) {
                                 if (var16.isCatchAll(this.ctx)) {
                                 }
                              }

                              var8.add(var14);
                           }
                        }
                     }
                  }

                  for (BasicBlock var26 : var8) {
                     if (var1.protectBlock((int)var26.getBase(), var7.getTypeIndex(), var7.getAddress(), -1)) {
                        this.cfg.addIrregularEdge(var26, var9, -1);
                        var18++;
                     }
                  }
               }
            }
         }

         if (var18 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var18;
      }
   }

   private int gP() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1.isEmpty()) {
         return 0;
      } else {
         int var2 = 0;

         for (int var3 = 0; var3 < this.cfg.size(); var3++) {
            BasicBlock var4 = this.cfg.get(var3);

            for (int var5 = 0; var5 < var4.size() - 1; var5++) {
               IDInstruction var6 = (IDInstruction)var4.get(var5);
               if (var6.isMonitorEnter() && var4.outsize() > 0 && !DUtil.canThrow(var4, var5 + 1)) {
                  IDExceptionHandler var7 = this.q(var4);
                  if (var7 != null) {
                     BasicBlock var8 = DUtil.splitBlock(this.ctx, var4, var5 + 1);
                     var6.setData("KEEP_LAST", Boolean.TRUE);
                     var1.unprotectBlock((int)var8.getBase());
                     this.cfg.deleteIrregularOutEdges(var8);
                     var1.protectBlock((int)var8.getBase(), var7.getTypeIndex(), var7.getAddress(), -1);
                     this.cfg.addIrregularEdge(var8, this.cfg.getBlockAt((long)var7.getAddress()), -1);
                     var2++;
                  }
               }
            }
         }

         if (var2 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      }
   }

   private IDExceptionHandler q(BasicBlock var1) {
      for (BasicBlock var3 : var1.getOutputs()) {
         List var4 = this.ctx.getExceptionData().getBlockHandlers((int)var3.getBase());
         if (!var4.isEmpty()) {
            IDExceptionHandler var5 = (IDExceptionHandler)var4.get(var4.size() - 1);
            if (var5.isCatchAll(this.ctx)) {
               return var5;
            }
         }
      }

      return null;
   }

   private static class CU {
      CFG q;
      long RF;
      Set xK;

      CU(CFG var1, long var2, Set var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var4;
      }

      Set q() {
         ArrayDeque var1 = new ArrayDeque();
         var1.add(this.RF);
         HashSet var2 = new HashSet();
         HashSet var3 = new HashSet();

         while (!var1.isEmpty()) {
            long var4 = (Long)var1.remove();
            if (var2.add(var4) && !this.xK.contains(var4)) {
               BasicBlock var6 = this.q.getBlockAt(var4);

               for (BasicBlock var8 : var6.getAllOutputs()) {
                  var1.add(var8.getAddress());

                  for (BasicBlock var10 : var8.getAllInputs()) {
                     var3.add(var10.getAddress());
                  }
               }
            }
         }

         if (!var2.containsAll(var3)) {
            var3.removeAll(var2);

            for (long var5 : var3) {
               BasicBlock var12 = this.q.getBlockAt(var5);
               if (var12.outsize() != 0 || var12.irroutsize() != 1 || var12.canThrow()) {
                  return null;
               }
            }
         }

         return var2;
      }
   }

   private static class eo {
      long q;
      int RF;
      long xK = -1L;
      long Dw;
      Set Uv = new HashSet();
      Set oW = new HashSet();
      long gO = -1L;
      long nf;

      eo(long var1, int var3) {
         this.q = var1;
         this.RF = var3;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "lock@0x%X on var#%d enter=0x%X", this.q, this.RF, this.xK);
         return var1.toString();
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
         return 31 * var1 + this.RF;
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            cgj.eo var2 = (cgj.eo)var1;
            return this.q != var2.q ? false : this.RF == var2.RF;
         }
      }
   }
}
