package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jebglobal.yP;

public class GK extends AbstractEOptimizer {
   public GK() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg.getBlocks()) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            if (var5 instanceof IECall) {
               if (((IECall)var5).getCallSite() instanceof yP) {
                  yP var6 = (yP)((IECall)var5).getCallSite();
                  if (var6.kS() instanceof IEImm) {
                     if (var5.replaceSubExpression(var6, var6.kS())) {
                        var1++;
                     }
                  } else if (this.getMasterOptimizerSafe().getMode().isAggressive()
                     && var6.kS() instanceof IECond
                     && ((IECond)var6.kS()).getCondition() instanceof IESlice var8
                     && var8.getRange().getRangeLength() == 1) {
                     IEGeneric var9 = var8.getWholeExpression();
                     if (var5.replaceSubExpression(var6, var9)) {
                        var1++;
                     }
                  }
               }
            } else if (var5 instanceof IEJumpFar) {
               if (((IEJumpFar)var5).getJumpsite() instanceof yP) {
               }
            } else if (var5 instanceof IEAssign && ((IEAssign)var5).getRightOperand() instanceof yP) {
               yP var10 = (yP)((IEAssign)var5).getRightOperand();
               if (var10.kS() instanceof IEImm && var5.replaceSubExpression(var10, var10.kS())) {
                  var1++;
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
