package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class auh implements IEVisitor {
   auh(aue var1) {
      this.pC = var1;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEImm var4
         && var4.getType() != null
         && var4.getType().isPointer()
         && var4.getCustomAST() == null
         && var4.canReadAsAddress()
         && var4.getBitsize() == this.pC.sY.getAddressBitsize()) {
         if (var2 instanceof IECall var5 && var5.getReturnLocation() == var4) {
            return;
         }

         long var8 = var4.getValueAsAddress();
         if (var8 != 0L) {
            IEVar var7 = this.pC.sY.createGlobalReference(null, var8);
            var2.replaceSubExpression(var1, var7);
         }
      }
   }
}
