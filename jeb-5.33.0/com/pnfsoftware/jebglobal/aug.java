package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;

class aug implements IEVisitor {
   aug(aue var1, IWildcardTypeManager var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar var4) {
         if (var4.isTypeable() && var4.getType() == null) {
            var4.safelyType(this.pC);
         }
      } else if (var1 instanceof IEImm var5) {
         IEImm var6 = var5.normalize();
         if (var6 != null) {
            var2.replaceSubExpression(var5, var6);
         }
      }
   }
}
