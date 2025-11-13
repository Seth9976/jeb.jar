package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;

class ape implements IEVisitor {
   ape(apd var1, long var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IESlice && ((IESlice)var1).getWholeExpression() instanceof IEVar) {
         IEVar var4 = (IEVar)((IESlice)var1).getWholeExpression();
         if (var4 != apd.q(this.RF).getProgramCounter() && var4 != apd.RF(this.RF).getStackPointer()) {
            IERange var5 = ((IESlice)var1).getRange();
            int var6 = 0;
            if (var5.getBegin() == 0) {
               var6 = var5.getEnd();
            } else if (var5.getEnd() == var4.getBitsize()) {
               var6 = var5.getBegin();
            }

            if (var6 > 0) {
               IWildcardType var7 = var4.getType();
               if ((var7 == null || var7.getBitsize() == var6) && this.RF.q(this.q, var4, var6, var1.getType())) {
                  var3.interrupt(false);
               }
            }
         }
      }
   }
}
