package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;

class amx implements IEVisitor {
   amx(amw var1, long var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IESlice var4
         && var4.getWholeExpression() instanceof IEVar var5
         && var5 != amw.pC(this.A).getProgramCounter()
         && var5 != amw.A(this.A).getStackPointer()) {
         IERange var9 = var4.getRange();
         int var7 = 0;
         if (var9.getBegin() == 0) {
            var7 = var9.getEnd();
         } else if (var9.getEnd() == var5.getBitsize()) {
            var7 = var9.getBegin();
         }

         if (var7 > 0) {
            IWildcardType var8 = var5.getType();
            if ((var8 == null || var8.getBitsize() == var7) && this.A.pC(this.pC, var5, var7, var1.getType())) {
               var3.interrupt(false);
            }
         }
      }
   }
}
