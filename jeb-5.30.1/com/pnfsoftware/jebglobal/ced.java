package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;

class ced extends bwt {
   ced(cec var1, IDMethodContext var2, int var3) {
      super(var2, var3);
      this.Dw = var1;
   }

   @Override
   protected boolean q(IDCallInfo var1) {
      bun var2 = bun.q(var1.getMethodSignature(), this.Dw.g);
      if (var2 == bun.q) {
         return false;
      } else {
         return var1.getInvokeType() != DInvokeType.STATIC ? false : !(var1 instanceof IDNewInfo);
      }
   }
}
