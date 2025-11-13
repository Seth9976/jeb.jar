package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.util.base.Couple;

class ang implements IEVisitor {
   int q;

   ang(ane var1, int var2, IEGeneric var3, int var4, Couple[] var5) {
      this.oW = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      boolean var4;
      switch (this.RF) {
         case 0:
            var4 = var1 == this.xK;
            break;
         case 1:
            var4 = var1.equals(this.xK);
            break;
         case 2:
            var4 = var1.equalsEx(this.xK, false);
            break;
         default:
            return;
      }

      if (var4) {
         if (this.q >= this.Dw) {
            this.Uv[0] = new Couple(var2, var1);
            var3.interrupt(true);
         }

         this.q++;
      }
   }
}
