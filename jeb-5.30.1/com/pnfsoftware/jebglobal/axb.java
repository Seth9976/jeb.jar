package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class axb implements IEVisitor {
   axb(awy var1) {
      this.q = var1;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEImm var4
         && var4.getType() != null
         && var4.getType().isPointer()
         && var4.getCustomAST() == null
         && var4.canReadAsAddress()
         && var4.getBitsize() == this.q.gO.getAddressBitsize()) {
         if (var2 instanceof IECall && var4 == ((IECall)var2).getReturnLocation()) {
            return;
         }

         long var5 = var4.getValueAsAddress();
         if (var5 != 0L) {
            IEVar var7 = this.q.gO.createGlobalReference(null, var5);
            var2.replaceSubExpression(var1, var7);
         }
      }
   }
}
