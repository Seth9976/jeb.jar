package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;

class cdk extends bwt {
   cdk(cdj var1, IDMethodContext var2) {
      super(var2);
      this.Dw = var1;
   }

   @Override
   protected boolean q(IDCallInfo var1) {
      bun var2 = bun.q(var1.getMethodSignature(), this.Dw.g);
      return var2 == bun.xK || var2 == bun.Dw;
   }
}
