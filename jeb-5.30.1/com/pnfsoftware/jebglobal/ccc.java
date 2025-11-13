package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;

public class ccc extends AbstractDOptimizer {
   boolean q = true;
   boolean RF = false;

   @Override
   public int perform() {
      int var1 = 0;
      if (this.q) {
         for (BasicBlock var3 : this.cfg) {
            if (var3.size() == 1) {
               IDInstruction var4 = (IDInstruction)var3.get(0);
               if (var4.isReturn()) {
                  IDExpression var5 = var4.getReturnExpression();
                  Assert.a(var3.outsize() == 0);
                  if (var3.irroutsize() == 0 || !var4.canThrow()) {
                     for (BasicBlock var7 : var3.getInputBlocks()) {
                        IDInstruction var8 = (IDInstruction)var7.getLast();
                        if (var8.isJump()) {
                           IDInstruction var10 = var4.duplicateForReplacement(var8);
                           var7.set(var7.size() - 1, var10);
                           this.cfg.deleteEdge(var7, var3);
                           var1++;
                        } else if (var5 instanceof IDVar var9 && var8.isAssignToVar(var9.getId())) {
                           IDInstruction var21 = var4.duplicateForReplacement(var8);
                           var21.setOperand2(var8.getOperand2().duplicate());
                           var7.set(var7.size() - 1, var21);
                           this.cfg.deleteEdge(var7, var3);
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.RF) {
         long var14 = this.cfg.getEndAddress();

         for (BasicBlock var16 : this.cfg.getBlocks()) {
            if (var16.size() == 1) {
               IDInstruction var17 = (IDInstruction)var16.get(0);
               if (var17.isReturn() && var16.allinsize() > 1) {
                  boolean var18 = var16.canThrow();
                  int var19 = var16.allinsize() - 1;

                  for (BasicBlock var22 : var16.getAllInputBlocks()) {
                     if (var19 <= 0) {
                        break;
                     }

                     IDInstruction var11 = (IDInstruction)var22.getLast();
                     if (var11.isJcondTo((int)var16.getAddress())) {
                        BasicBlock var12 = new BasicBlock();
                        IDInstruction var13 = var17.duplicateWithOffsetAndSize(var14, 1);
                        var12.add(var13);
                        var11.setBranchTarget((int)var14);
                        var14++;
                        this.cfg.addBlock(var12);
                        this.cfg.reconnectEdge(var22, var16, var12);
                        if (var18) {
                           bto.q(var16, var12);
                        }

                        var19--;
                        var1++;
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
