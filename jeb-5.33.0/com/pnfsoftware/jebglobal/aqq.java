package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class aqq extends AbstractEOptimizer {
   public aqq() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   protected int perform() {
      ash var1 = new ash(this.ectx, true);
      int var2 = 0;

      for (BasicBlock var4 : this.cfg) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IEStatement var6 = (IEStatement)var4.get(var5);
            IEStatement var7 = (IEStatement)var1.pC(var6);
            if (var7 != null) {
               var4.set(var5, var7);
               var2++;
            }
         }
      }

      return this.postPerform(var2);
   }

   @Override
   public IEGeneric performOnExpression(IEGeneric var1, IERoutineContext var2) {
      ash var3 = new ash(var2, true);
      return var3.pC(var1);
   }
}
