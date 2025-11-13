package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.util.base.Couple;

class apg implements IEVisitor {
   apg(apd var1, IEVar var2, int var3, apd.nI var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      boolean var4 = false;
      if (var1 instanceof IESlice) {
         if (((IESlice)var1).getWholeExpression() == this.q) {
            if (((IESlice)var1).getBitStart() >= 0 && ((IESlice)var1).getBitEnd() <= this.RF) {
               this.xK.Uv.add(new Couple(var2, (IESlice)var1));
               var3.skipChildren();
            } else {
               var4 = true;
            }
         }
      } else if (var1 == this.q) {
         var4 = true;
      }

      if (var4) {
         var3.interrupt(false);
      }
   }
}
