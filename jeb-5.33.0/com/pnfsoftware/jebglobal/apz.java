package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class apz extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(apz.class);

   public apz() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg.getBlocks()) {
         AddressableInstruction var4 = var3.getLast2();
         if (var4.getInstruction() instanceof IEAssign) {
            IEAssign var5 = (IEAssign)var4.getInstruction();
            if (var4.getBreakingFlow().isBroken() && var5.getLeftOperand() instanceof IEVar) {
               IEVar var6 = (IEVar)var5.getLeftOperand();
               if (var6.getId() == this.ectx.getProgramCounterId()) {
                  IEJump var7 = null;
                  if (var5.getRightOperand() instanceof IEImm) {
                     long var8 = ((IEImm)var5.getRightOperand()).getValueAsAddress();
                     Long var10 = this.ectx.convertNativeAddress(var8);
                     if (var10 == null) {
                        continue;
                     }

                     var7 = this.ectx.createJump(var10.intValue(), null);
                     BasicBlock var11 = this.cfg.getBlockAt(var10);
                     if (var11 != null && this.cfg.addEdge(var3, var11)) {
                        this.cfg.invalidateDataFlowAnalysis();
                     }
                  } else if (var5.getRightOperand() instanceof IECond) {
                     IECond var22 = (IECond)var5.getRightOperand();
                     if (var22.getExpressionTrue() instanceof IEImm && var22.getExpressionFalse() instanceof IEImm) {
                        long var9 = var3.getEndAddress() - var5.getSize();
                        long var23 = ((IEImm)var22.getExpressionTrue()).getValueAsAddress();
                        long var13 = ((IEImm)var22.getExpressionFalse()).getValueAsAddress();
                        long var15 = var9 + var5.getSize();
                        Long var19 = this.ectx.convertNativeAddress(var23);
                        Long var20 = this.ectx.convertNativeAddress(var13);
                        long var17;
                        if (var19 != null && var20 != null) {
                           if (var20 == var15) {
                              var17 = var19;
                              var7 = this.ectx.createJump((int)var17, var22.getCondition());
                           } else {
                              if (var19 != var15) {
                                 continue;
                              }

                              var17 = var20;
                              var7 = this.ectx.createJump((int)var17, EUtil.reversePredicate(var22.getCondition()));
                           }

                           BasicBlock var24 = this.cfg.getBlockAt(var15);
                           if (var24 != null && this.cfg.addEdge(var3, var24)) {
                              this.cfg.invalidateDataFlowAnalysis();
                           }
                        } else {
                           if (!(var22.getCondition() instanceof IEImm)) {
                              continue;
                           }

                           boolean var21 = !var22.getCondition().asImm().isZero();
                           if (var21 && var19 != null) {
                              var17 = var19;
                              var7 = this.ectx.createJump((int)var17);
                           } else {
                              if (var21 || var20 == null) {
                                 continue;
                              }

                              var17 = var20;
                              var7 = this.ectx.createJump((int)var17);
                           }
                        }

                        BasicBlock var25 = this.cfg.getBlockAt(var17);
                        if (var25 != null && this.cfg.addEdge(var3, var25)) {
                           this.cfg.invalidateDataFlowAnalysis();
                        }
                     }
                  }

                  if (var7 != null) {
                     var7.copyProperties(var5);
                     var3.set(var3.size() - 1, var7);
                     var1++;
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
