package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStackManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;

class auf implements IEVisitor {
   auf(aue var1, IEStackManager var2, IWildcardTypeManager var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar var4 && var4.isStackReference()) {
         long var5 = var4.getAddress();
         IEVar var7 = this.pC.getVariable(var5);
         if (var7 == null && this.pC.canCreateVariable(var5, 8)) {
            IEVar var8 = this.pC.createStackItem(var5, 8);
            var8.setType(this.A.createWithEffectiveBitsize(8));
         }
      }
   }
}
