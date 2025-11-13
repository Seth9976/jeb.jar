package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.concurrent.atomic.AtomicInteger;

public class aso extends AbstractEOptimizer {
   public aso() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      AtomicInteger var1 = new AtomicInteger(0);
      IWildcardTypeManager var2 = this.ectx.getWildcardTypeManager();

      for (BasicBlock var4 : this.cfg) {
         for (IEStatement var6 : var4) {
            var6.visitDepthPost(new asp(this, var2, var1));
         }
      }

      return this.postPerform(var1.get());
   }

   private static Couple q(IEGeneric var0) {
      if (RF(var0)) {
         return new Couple(var0.asVar(), 0L);
      } else {
         if (var0.isOperation(OperationType.ADD)) {
            IEGeneric var1 = var0.asOperation().getOperand1();
            IEGeneric var2 = var0.asOperation().getOperand2();
            if (RF(var1) && var2.isImm()) {
               return new Couple(var1.asVar(), var2.asImm().getValueAsLong());
            }
         }

         return null;
      }
   }

   private static boolean RF(IEGeneric var0) {
      return var0.isVar() && var0.asVar().isStackReference();
   }
}
