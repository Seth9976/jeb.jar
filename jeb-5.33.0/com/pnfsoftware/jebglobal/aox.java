package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aox extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(aox.class);

   public aox() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      int var18;
      do {
         acj.pC();
         var18 = 0;

         for (BasicBlock var4 : this.cfg) {
            boolean var5 = false;
            if (var4.size() == 1
               && var4.outsize() == 1
               && var4.insize() == 1
               && var4.irrinsize() == 0
               && EUtil.isUnconditionalJump((IEStatement)var4.get(0))
               && !var4.isSelfReferencing()) {
               BasicBlock var20 = var4.getInputBlock(0);
               BasicBlock var21 = var4.getOutputBlock(0);
               if (EUtil.isConditionalJump((IEStatement)var20.getLast())) {
                  IEJump var22 = (IEJump)var20.getLast();
                  if (var20.getOutputBlock(0) == var4) {
                     BasicBlock var23 = var20.getOutputBlock(1);
                     if (var23 != var21 && var4.getEndAddress() == var23.getFirstAddress()) {
                        this.cfg.reconnectEdge(var20, var23, var21);
                        this.cfg.reconnectEdge(var20, var4, var23);
                        this.cfg.deleteEdge(var4, var21);
                        var5 = akt.pC(this.cfg, var4);
                        IEGeneric var24 = var22.getCondition();
                        var22.setCondition(EUtil.notL(var24));
                        var22.setBranchAddress((int)var21.getFirstAddress());
                        Object[] var31 = new Object[0];
                        var18++;
                     }
                  }
               }
            } else if (var4.size() == 1
               && var4.outsize() == 2
               && var4.insize() == 1
               && var4.irrinsize() == 0
               && EUtil.isConditionalJump((IEStatement)var4.get(0))
               && !var4.isSelfReferencing()) {
               IEJump var6 = (IEJump)var4.get(0);
               BasicBlock var7 = var4.getInputBlock(0);
               BasicBlock var8 = var4.getOutputBlock(0);
               IEStatement var9 = (IEStatement)var7.getLast();
               if (EUtil.isConditionalJump(var9)) {
                  IEJump var10 = (IEJump)var9;
                  IEGeneric var11 = var10.getCondition();
                  IEGeneric var12 = var6.getCondition();
                  BasicBlock var13 = var4.getOutputBlock(1);
                  BasicBlock var14 = var7.getOutputBlock(0);
                  BasicBlock var15 = var7.getOutputBlock(1);
                  if (var14 == var4) {
                     if (var15 != var8 && var15 == var13 && var8.irrinsize() == 0) {
                        if (var7.irrinsize() == 0 && CollectionUtil.compare(var7.getIrregularOutputs(), var4.getIrregularOutputs(), false)) {
                           this.cfg.reconnectEdge(var7, var4, var8);
                           this.cfg.deleteEdge(var4, var13);
                           this.cfg.deleteEdge(var4, var8);
                           var5 = akt.pC(this.cfg, var4);
                           IEOperation var27 = EUtil.orL(var11, var12);
                           var10.setCondition(var27);
                           var10.setBranchAddress(var6.getBranchAddress());
                           Object[] var30 = new Object[0];
                           var18++;
                        }
                     } else if (var15 != var8 || var13 == var8 || var8.irrinsize() != 0) {
                        if (var8.size() == 1 && EUtil.isUnconditionalJump((IEStatement)var8.get(0))) {
                           BasicBlock var26 = var8.getOutputBlock(0);
                           if (var26 == var15 && var26 != var8 && var8.getEndAddress() != var13.getFirstAddress()) {
                              this.cfg.reconnectEdge(var7, var4, var8);
                              this.cfg.reconnectEdge(var7, var15, var13);
                              this.cfg.deleteEdge(var4, var13);
                              this.cfg.deleteEdge(var4, var8);
                              var5 = akt.pC(this.cfg, var4);
                              IEOperation var28 = EUtil.andL(EUtil.notL(var11), var12);
                              var10.setCondition(var28);
                              var10.setBranchAddress(var6.getBranchAddress());
                              Object[] var29 = new Object[0];
                              var18++;
                           }
                        } else if (var8 != var15
                           && var15 != var13
                           && var8.size() == 1
                           && var8.alloutsize() == 0
                           && var15.size() == 1
                           && var15.alloutsize() == 0
                           && var13.size() == 1
                           && var13.alloutsize() == 0) {
                           IEStatement var25 = (IEStatement)var8.get(0);
                           IEStatement var17 = (IEStatement)var15.get(0);
                           if (var17.equalsEx(var25, false)) {
                              var10.setBranchAddress((int)var8.getBase());
                              this.cfg.reconnectEdge(var7, var15, var8);
                              var5 = this.cfg.removeUnreachableBlocks() > 0;
                              akt.A(this.cfg, false, true);
                              var18++;
                           }
                        }
                     } else if (var7.irrinsize() == 0 && CollectionUtil.compare(var7.getIrregularOutputs(), var4.getIrregularOutputs(), false)) {
                        this.cfg.reconnectEdge(var7, var8, var13);
                        this.cfg.reconnectEdge(var7, var4, var8);
                        this.cfg.deleteEdge(var4, var4.getOutputBlock(1));
                        this.cfg.deleteEdge(var4, var8);
                        var5 = akt.pC(this.cfg, var4);
                        IEOperation var16 = EUtil.andL(EUtil.notL(var11), var12);
                        var10.setCondition(var16);
                        var10.setBranchAddress(var6.getBranchAddress());
                        Object[] var10000 = new Object[0];
                        var18++;
                     }
                  }
               }
            }

            if (var5) {
               break;
            }
         }

         int var19 = this.cfg.simplify();
         var18 += var19;
         var1 += var18;
      } while (var18 > 0);

      return this.postPerform(var1);
   }
}
