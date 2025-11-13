package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.AbstractMasterOptimizer;

public class eo extends AbstractMasterOptimizer implements ICMasterOptimizer {
   public eo(ICMethod var1) {
      this(var1, -1);
   }

   public eo(ICMethod var1, int var2) {
      super(var1, var2);
   }

   protected String q(ICMethod var1) {
      return var1.getAddress();
   }
}
