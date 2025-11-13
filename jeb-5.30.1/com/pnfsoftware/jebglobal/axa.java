package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;

class axa implements IEVisitor {
   axa(awy var1, IWildcardTypeManager var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar var4) {
         if (var4.isTypeable() && var4.getType() == null) {
            var4.safelyType(this.q);
         }
      } else if (var1 instanceof IEImm var6) {
         IEImm var5 = var6.normalize();
         if (var5 != null) {
            var2.replaceSubExpression(var6, var5);
         }
      }
   }
}
