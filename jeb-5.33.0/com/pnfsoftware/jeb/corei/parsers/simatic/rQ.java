package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.util.base.Assert;

public class rQ extends AbstractEOptimizer {
   @Override
   protected int perform() {
      if (this.ectx.usesCopyVars()) {
         return 0;
      } else {
         int var1 = 0;
         IEVar var2 = ((Sv)this.ectx.getConverter()).rl;
         Assert.a(var2 != null);
         byte var3 = 0;
         IEGeneric var4 = null;
         IEAssign var5 = null;

         for (BasicBlock var7 : this.cfg) {
            for (IEStatement var9 : var7) {
               if (var3 == 0) {
                  if (var9 instanceof IEAssign && ((IEAssign)var9).getSrcOperand() == var2 && ((IEAssign)var9).getDstOperand() instanceof IEMem) {
                     var5 = var9.asAssign();
                     var4 = var5.getDstOperand();
                     var3 = 1;
                  }
               } else if (var3 == 1) {
                  if (var9 instanceof IECall || var9.isAssignTo(this.ectx.getProgramCounter()) && var9.asAssign().isRoutineCall()) {
                     var3 = 2;
                  }
               } else if (var3 == 2
                  && var9 instanceof IEAssign
                  && ((IEAssign)var9).getDstOperand() == var2
                  && ((IEAssign)var9).getSrcOperand() instanceof IEMem) {
                  IEGeneric var10 = ((IEAssign)var9).getSrcOperand();
                  if (var10.equalsEx(var4, false)) {
                     IEVar var11 = this.pC(32);
                     if (var5.replaceSubExpression(var4, var11) && var9.replaceSubExpression(var10, var11)) {
                        var1++;
                     }

                     var3 = 0;
                  }
               }
            }
         }

         if (var1 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }

   private IEVar pC(int var1) {
      int var3 = 0;

      while (true) {
         String var2 = "LocalVar" + var3;
         IEVar var4 = this.ectx.getVariableByName(var2);
         if (var4 == null) {
            return this.ectx.createVirtualVar(var2, 32);
         }

         var3++;
      }
   }
}
