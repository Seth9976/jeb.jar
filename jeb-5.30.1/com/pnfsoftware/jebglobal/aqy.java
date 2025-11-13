package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class aqy extends AbstractEOptimizer {
   public aqy() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   public int perform() {
      int var1 = 0;

      int var2;
      do {
         var2 = 0;

         for (BasicBlock var4 : this.cfg.getBlocks()) {
            if (var4.size() == 1
               && var4.outsize() == 1
               && var4.irrinsize() == 0
               && EUtil.isUnconditionalJump((IEStatement)var4.get(0))
               && !var4.isSelfReferencing()) {
               IEJump var5 = (IEJump)var4.get(0);
               int var6 = var5.getBranchAddress();
               if (var6 < 0) {
                  throw new RuntimeException("Illegal target offset: " + var6);
               }

               boolean var7 = false;

               for (BasicBlock var9 : var4.getInputBlocks()) {
                  IEStatement var10 = (IEStatement)var9.getLast();
                  if (EUtil.isUnconditionalJump(var10)) {
                     IEJump var11 = (IEJump)var10;
                     var11.setBranchAddress(var5.getBranchAddress());
                     if (this.cfg.reconnectEdge(var9, var4, var4.getOutputBlock(0)) != 1) {
                        throw new RuntimeException("Edge reconnect failed");
                     }

                     var7 = amw.q(this.cfg, var4);
                     var2++;
                  } else if (EUtil.isConditionalJump(var10) && var9.outsize() == 2) {
                     IEJump var26 = (IEJump)var10;
                     BasicBlock var12 = var9.getOutputBlock(0);
                     BasicBlock var13 = var4.getOutputBlock(0);
                     if (var12 == var4 && var13 == var9.getOutputBlock(1)) {
                        EUtil.makeUncond(var26);
                        this.cfg.deleteEdge(var9, var4);
                        var7 = amw.q(this.cfg, var4);
                        var2++;
                     } else if (var9.getOutputBlock(1) == var4 && var12 != var4) {
                        if (var13 == var12) {
                           if (!EUtil.hasSideEffect(var26.getCondition())) {
                              var9.set(var9.size() - 1, this.ectx.createNop(var26));
                              this.cfg.deleteEdge(var9, var4);
                              var7 = amw.q(this.cfg, var4);
                              var2++;
                           }
                        } else {
                           if (this.cfg.reconnectEdge(var9, var4, var13) != 1) {
                              throw new RuntimeException("Edge reconnect failed");
                           }

                           var26.setBranchAddress(var5.getBranchAddress());
                           var7 = amw.q(this.cfg, var4);
                           var2++;
                        }
                     } else if (var12 == var4
                        && var4.getEndAddress() == var9.getOutputBlock(1).getBase()
                        && var13 != var9.getOutputBlock(1)
                        && var4.insize() == 1) {
                        BasicBlock var14 = var9.getOutputBlock(1);
                        var26.setBranchAddress(var5.getBranchAddress());
                        var5.setBranchAddress((int)var14.getBase());
                        var26.setCondition(EUtil.reversePredicate(var26.getCondition()));
                        if (this.cfg.reconnectEdge(var9, var14, var13) != 1 || this.cfg.reconnectEdge(var4, var13, var14) != 1) {
                           throw new RuntimeException("Edge reconnect failed");
                        }

                        var2++;
                     }
                  } else if (var10 instanceof IESwitch var25 && amw.q(var25, this.cfg, var9, var4, var4.getOutputBlock(0))) {
                     var2++;
                  }

                  if (var7) {
                     break;
                  }
               }
            }

            if (this.getMasterOptimizerSafe().getMode().isAggressive()
               && var4.size() == 1
               && var4.outsize() == 0
               && var4.irroutsize() == 0
               && var4.irrinsize() == 0
               && (var4.get(0) instanceof IEReturn || var4.get(0) instanceof IEJumpFar)) {
               IEStatement var17 = (IEStatement)var4.get(0);

               for (BasicBlock var21 : var4.getInputBlocks()) {
                  IEStatement var23 = (IEStatement)var21.getLast();
                  if (var21.outsize() == 1 && var21.irroutsize() == 0 && var21.irrinsize() == 0 && EUtil.isUnconditionalJump(var23)) {
                     IEStatement var24 = (IEStatement)var17.duplicate();
                     var24.copyProperties(var23);
                     var21.setLast(var24);
                     this.cfg.deleteEdge(var21, var4);
                     var2++;
                  }
               }
            }
         }

         if (var2 > 0) {
            amw.q(this.cfg);
         }

         for (BasicBlock var16 : this.cfg) {
            AddressableInstruction var18 = var16.getLast2();
            if (EUtil.isUnconditionalJump((IEStatement)var18.getInstruction())) {
               IEJump var20 = (IEJump)var18.getInstruction();
               if (var20.getBranchAddress() == var18.getOffset() + var20.getSize()) {
                  if (var16.size() >= 2) {
                     if (amw.q(this.ectx, var16, var16.size() - 1) != 0) {
                        var2++;
                     }
                  } else if (var16.size() == 1) {
                     IENop var22 = this.ectx.createNop(var20);
                     var16.set(var16.size() - 1, var22);
                     var2++;
                  }
               }
            }
         }

         var1 += var2;
      } while (var2 > 0);

      return this.postPerform(var1);
   }
}
