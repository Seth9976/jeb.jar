package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.AbstractOptimizer;

public abstract class AbstractCOptimizer extends AbstractOptimizer implements ICOptimizer {
   protected ICMethod m;
   protected ICGlobalContext g;
   protected ICElementFactory ef;
   protected ICOperatorFactory of;
   protected ICConstantFactory cf;

   public ICMasterOptimizer getMasterOptimizer() {
      return (ICMasterOptimizer)super.getMasterOptimizer();
   }

   protected ICMasterOptimizer getMasterOptimizerSafe() {
      Object var1 = this.getMasterOptimizer();
      if (var1 == null) {
         var1 = CMasterOptimizer.EMPTY;
      }

      return (ICMasterOptimizer)var1;
   }

   public final int performOnTarget(ICMethod var1) {
      this.m = var1;
      this.g = var1.getGlobalContext();
      this.ef = this.g.getElementFactory();
      this.of = this.g.getOperatorFactory();
      this.cf = this.g.getConstantFactory();

      int var2;
      try {
         var2 = this.perform();
      } finally {
         this.m = null;
         this.g = null;
         this.ef = null;
         this.of = null;
         this.cf = null;
      }

      return var2;
   }

   protected abstract int perform();
}
