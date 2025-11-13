package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class anh implements IEVisitor {
   int q;

   anh(ane var1, Class var2, int var3, IEGeneric[] var4) {
      this.Uv = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (this.RF.isInstance(var1)) {
         if (this.q >= this.xK) {
            this.Dw[0] = var1;
            var3.interrupt(true);
         }

         this.q++;
      }
   }
}
