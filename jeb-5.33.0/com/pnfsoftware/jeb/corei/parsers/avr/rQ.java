package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class rQ extends AbstractEBlockOptimizer {
   public rQ() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setPriority(1.0);
   }

   @Override
   protected int optimizeBlock(BasicBlock var1) {
      int var2 = 0;
      int var3 = 0;

      for (AddressableInstruction var5 : var1.addressableInstructions()) {
         if (var3 >= 1 && var5.getInstruction() instanceof IENop) {
            IEStatement var6 = (IEStatement)var1.get(var3 - 1);
            if (var6 instanceof IEAssign && ((IEAssign)var6).getRightOperand() instanceof IEImm && ((IEAssign)var6).getLeftOperand() instanceof IEMem) {
               IEMem var7 = (IEMem)((IEAssign)var6).getLeftOperand();
               IEImm var8 = (IEImm)((IEAssign)var6).getRightOperand();
               if (var8.getBitsize() == 16 && EUtil.isVar(var7.getReference(), this.ectx.getStackPointerId())) {
                  Long var9 = this.ectx.convertIntermediateOffset((int)var5.getOffset() + 1);
                  if (var9 != null && var9 == var8.getValueAsLong()) {
                     var1.set(var3 - 1, this.ectx.createNop(var6));
                     var2++;
                  }
               }
            }
         }

         var3++;
      }

      return var2;
   }
}
