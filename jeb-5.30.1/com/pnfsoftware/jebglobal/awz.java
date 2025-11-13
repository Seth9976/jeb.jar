package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStackManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;

class awz implements IEVisitor {
   awz(awy var1, IEStackManager var2, IWildcardTypeManager var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar && ((IEVar)var1).isStackReference()) {
         IEVar var4 = (IEVar)var1;
         long var5 = var4.getAddress();
         IEVar var7 = this.q.getVariable(var5);
         if (var7 == null && this.q.canCreateVariable(var5, 8)) {
            IEVar var8 = this.q.createStackItem(var5, 8);
            var8.setType(this.RF.createWithEffectiveBitsize(8));
         }
      }
   }
}
