package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.concurrent.atomic.AtomicInteger;

public class aqj extends AbstractEStatementOptimizer {
   public aqj() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   protected IEStatement optimizeStatement(IEStatement var1) {
      AtomicInteger var2 = new AtomicInteger();
      var1.visitDepthPost(new aqk(this, var2));
      return var2.get() > 0 ? var1 : null;
   }
}
