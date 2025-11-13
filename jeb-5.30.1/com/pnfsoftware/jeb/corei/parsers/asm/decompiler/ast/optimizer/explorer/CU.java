package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jebglobal.adw;

public class CU extends iZ {
   private int Uv = -1;

   public CU(adw var1, Vj var2, int var3) {
      this(var1, var2);
      this.Uv = var3;
   }

   public CU(adw var1, Vj var2) {
      super(var2);
   }

   public ICMasterOptimizer q(ICMethod var1) {
      this.xK = var1;
      this.Dw = ICMethod.class;
      this.RF = this.RF(var1);
      super.q();
      return (ICMasterOptimizer)this.RF;
   }

   private eo RF(ICMethod var1) {
      return new eo(var1, this.Uv);
   }
}
