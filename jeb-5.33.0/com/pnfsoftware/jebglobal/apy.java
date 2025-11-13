package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.Set;

public class apy extends AbstractEOptimizer {
   public apy() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   public int perform() {
      int var1 = 0;

      label94:
      for (BasicBlock var3 : this.cfg.getBlocks()) {
         IEStatement var4 = (IEStatement)var3.getLast();
         if (EUtil.isConditionalJump(var4)) {
            IEJump var5 = (IEJump)var4;
            BasicBlock var6 = var3.getOutputBlock(0);
            if (var6.allinsize() == 1 && var6.outsize() != 0) {
               BasicBlock var7 = var3.getOutputBlock(1);
               if (var7.size() == 1 && var7.allinsize() == 2 && var7 == var6.getOutputBlock(0) && var7.outsize() == 2) {
                  BasicBlock var8 = var7.getOutputBlock(0);
                  if (var8.allinsize() == 1 && EUtil.isConditionalJump((IEStatement)var7.get(0))) {
                     IEJump var9 = (IEJump)var7.get(0);
                     IEGeneric var10 = var5.getCondition();
                     IEGeneric var11 = var9.getCondition();
                     if (var10 != null && var10.equalsEx(var11, false)) {
                        Set var12 = EUtil.getUsedVarIds(var10);
                        if (var12.size() == 1) {
                           IDFA var13 = this.cfg.doDataFlowAnalysis();

                           for (Integer var15 : var12) {
                              Long var16 = var13.checkSingleDef(var3.getLastAddress(), var15);
                              if (var16 == null || !var16.equals(var13.checkSingleDef(var7.getLastAddress(), var15))) {
                                 continue label94;
                              }
                           }

                           BasicBlock var17 = var7.getOutputBlock(1);
                           this.cfg.reconnectEdge(var3, var7, var17);
                           var5.setBranchAddress((int)var17.getFirstAddress());
                           this.cfg.deleteEdge(var7, var17);
                           var9.setCondition(null);
                           var9.setBranchAddress((int)var8.getFirstAddress());
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
