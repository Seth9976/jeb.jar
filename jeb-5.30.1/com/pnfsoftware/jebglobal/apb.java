package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import java.util.Set;

class apb implements IEVisitor {
   apb(apa var1, Set var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEVar && ((IEVar)var1).isRegister()) {
         int var4 = ((IEVar)var1).getId();
         if (this.RF.q(var4)) {
            this.q.add(var4);
         }
      }
   }
}
