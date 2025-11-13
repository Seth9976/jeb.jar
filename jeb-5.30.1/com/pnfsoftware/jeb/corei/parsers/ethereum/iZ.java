package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class iZ extends AbstractEExpressionOptimizer {
   private static final ILogger q = GlobalLog.getLogger(iZ.class);

   public iZ() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      tl var2 = (tl)this.ectx.getGlobalContext().getConverter();
      if (var1 instanceof IEMem && var1.getBitsize() == 256) {
         IEMem var3 = (IEMem)var1;
         if (EUtil.isImmZero(var3.getReference())) {
            return AbstractEExpressionOptimizer.EOR.create(var2.cC, true);
         }

         if (EUtil.isImmValue(var3.getReference(), 32L)) {
            return AbstractEExpressionOptimizer.EOR.create(var2.sH, true);
         }
      }

      return null;
   }
}
