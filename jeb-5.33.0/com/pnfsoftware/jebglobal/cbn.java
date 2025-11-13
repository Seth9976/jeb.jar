package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import java.util.List;

public class cbn extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;
      int var2 = this.pC();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.E();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.A();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.kS();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.wS();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.UT();
      if (var2 > 0) {
         var1 += var2;
      }

      var2 = this.sY();
      if (var2 > 0) {
         var1 += var2;
      }

      return var1;
   }

   private int pC() {
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

   private int A() {
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

   private int kS() {
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

   private int wS() {
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

   private int UT() {
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

   private int E() {
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

   private int sY() {
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
                  IDExceptionHandler var7 = this.pC(var4);
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

   private IDExceptionHandler pC(BasicBlock var1) {
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
}
