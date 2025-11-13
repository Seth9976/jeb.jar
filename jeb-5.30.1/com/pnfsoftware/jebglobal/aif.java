package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IMasterOptimizerInstrumenter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;

public class aif implements IMasterOptimizerInstrumenter {
   com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer.EE q;
   adw RF;

   public aif(adw var1) {
      this.RF = var1;
   }

   public void q(ICMethod var1) {
      this.q = new com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer.EE(this.RF.getParent().getParent().getName(), var1.getName());
   }

   public void q(ICMethod var1, OptimizerEntry var2) {
   }

   public void RF(ICMethod var1) {
      this.q.q();
   }

   public void q(ICMethod var1, OptimizerEntry var2, int var3, long var4) {
      this.q.q(var2, var3, var4);
   }
}
