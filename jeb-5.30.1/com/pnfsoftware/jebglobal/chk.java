package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;

public class chk extends AbstractDOptimizer {
   @Override
   public int perform() {
      if (this.ctx.getExceptionData().isEmpty()) {
         return 0;
      } else {
         int var1 = 0;

         for (BasicBlock var3 : this.cfg) {
            if (var3.irrinsize() == 1 && var3.insize() == 0 && this.q(var3)) {
               this.cfg.invalidateDataFlowAnalysis();
               var1++;
            }
         }

         return var1;
      }
   }

   boolean q(BasicBlock var1) {
      BasicBlock var2 = var1.getIrregularInputBlock(0);
      if (var2.irroutsize() != 1) {
         return false;
      } else {
         IDInstruction var3 = null;

         for (int var4 = var2.size() - 1; var4 > 0; var4--) {
            IDInstruction var5 = (IDInstruction)var2.get(var4);
            if (var5.canThrow()) {
               if (var5.isAssignToVar()) {
                  IDVar var6 = var5.getDefinedVariable();
                  IDInstruction var7 = (IDInstruction)var2.get(var4 - 1);
                  if (var7.isAssignToVar(var6.getId()) && var7.getAssignSource() instanceof IDImm) {
                     var3 = var7;
                  }
               } else if (var5.isReturn()) {
                  IDInstruction var11 = (IDInstruction)var2.get(var4 - 1);
                  if (var11.isAssignToVar() && var11.getAssignSource() instanceof IDImm) {
                     var3 = var11;
                  }
               }
               break;
            }
         }

         if (var3 == null) {
            return false;
         } else {
            IDInstruction var10 = (IDInstruction)var1.get(0);
            int var12 = var10.isStoreException() ? 1 : 0;
            DUtil.modifyInstructionSize(this.ctx, var10, 2);
            var10.adjustSize(-1);
            long var13;
            if (var12 == 0) {
               var13 = var10.getOffset();
               var10.setOffset(var13 + 1L);
            } else {
               var13 = var10.getOffsetEnd();
            }

            IDInstruction var9 = var3.duplicateWithOffsetAndSize(var13, 1);
            var9.setData("DO_NOT_HOIST", true);
            var1.add(var12, var9);
            var3.transformToNop();
            return true;
         }
      }
   }
}
