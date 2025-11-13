package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;

class amm implements IEVisitor {
   amm(aml var1, IEGeneric var2, IWildcardType var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.equalsEx(this.q, false)) {
         var1.setType(this.RF);
      }
   }
}
