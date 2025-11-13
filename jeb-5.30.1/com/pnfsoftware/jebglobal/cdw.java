package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;

public class cdw extends AbstractDOptimizer {
   private static boolean q = true;

   @Override
   public int perform() {
      if (this.ctx.getExceptionData().isEmpty()) {
         return 0;
      } else {
         int var1 = 0;

         for (BasicBlock var3 : this.cfg) {
            IDInstruction var4;
            if (var3.irrinsize() > 0
               && (var3.size() == 2 || var3.size() == 3)
               && ((IDInstruction)var3.get(0)).isStoreException()
               && (var4 = (IDInstruction)var3.get(1)).isAssignFromVarToVar()
               && (var3.size() == 2 || ((IDInstruction)var3.get(2)).isJump())) {
               IDVar var5 = var4.getAssignDestination().asVar();
               IDVar var6 = var4.getAssignSource().asVar();
               this.analyzeChains();
               Long var7 = this.dfa.checkSingleDef(var4.getOffset(), var6.getId());
               if (var7 != null && var7 >= 0L) {
                  Couple var8 = this.cfg.getInstructionLocation(var7);
                  if (var8 != null) {
                     BasicBlock var9 = (BasicBlock)var8.getFirst();
                     int var10 = (Integer)var8.getSecond();
                     if (var10 != 0 && (var10 + 1 == var9.size() || var10 + 2 == var9.size() && ((IDInstruction)var9.get(var10 + 1)).isJump())) {
                        IDInstruction var11 = (IDInstruction)var9.get(var10);
                        if (var11.isAssignToVar(var6.getId()) && var11.canThrow()) {
                           IDInstruction var12 = (IDInstruction)var9.get(var10 - 1);
                           if (var12.isAssignToVar() && var12.getAssignSource() instanceof IDImm var13 && var13.isZero()) {
                              IDVar var23 = var12.getAssignDestination().asVar();
                              if (var23 != var5 && var23 == var6) {
                                 if (var10 - 2 >= 0) {
                                    IDInstruction var15 = (IDInstruction)var9.get(var10 - 2);
                                    if (var15.isAssignToVar(var5.getId()) && var15.getAssignSource() instanceof IDImm var16 && var16.isZero()) {
                                       continue;
                                    }
                                 }

                                 Long var24 = this.dfa.checkSingleSource(var12.getOffset(), var5.getId());
                                 if (var24 != null) {
                                    IDInstruction var25 = (IDInstruction)this.cfg.getInstruction(var24);
                                    if (this.q(var25, var5, var6) == 1) {
                                       BasicBlock var26 = this.cfg.getBlockFor(var25);
                                       if (bto.q(this.cfg, var26, var9)) {
                                          if (q) {
                                             bub.eo var18 = ((bub)var12).q((var1x, var2, var3x) -> var2.add(var5.getId()));
                                             this.cfg.invalidateDataFlowAnalysis(var12.getOffset());

                                             try {
                                                this.analyzeChains();
                                                if (!this.dfa.getDefUses(var25.getOffset(), var5.getId(), 1).isEmpty()) {
                                                   continue;
                                                }
                                             } finally {
                                                ((bub)var12).q(var18);
                                                this.cfg.invalidateDataFlowAnalysis(var12.getOffset());
                                             }
                                          }

                                          DUtil.modifyInstructionSize(this.ctx, var12, 2);
                                          var12.adjustSize(-1);
                                          long var27 = var12.getOffset();
                                          var12.setOffset(var27 + 1L);
                                          IDInstruction var20 = var25.duplicateWithOffsetAndSize(var27, 1);
                                          var9.add(var10 - 1, var20);
                                          if (q) {
                                             var25.transformToNop();
                                          }

                                          this.cfg.invalidateDataFlowAnalysis();
                                          var1++;
                                       }
                                    }
                                 }
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
   }

   int q(IDInstruction var1, IDVar var2, IDVar var3) {
      if (var1 != null && var1.isAssignToVar(var2.getId())) {
         IDExpression var4 = var1.getAssignSource();
         if (var4 instanceof IDImm var5 && var5.isZero()) {
            return 1;
         }

         if (var4.isVar(var3.getId())) {
            return 2;
         }
      }

      return 0;
   }
}
